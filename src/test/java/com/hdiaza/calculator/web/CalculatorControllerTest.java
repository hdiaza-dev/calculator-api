package com.hdiaza.calculator.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hdiaza.calculator.services.AddService;

/**
 * The Class CalculatorControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class CalculatorControllerTest {

	/** The Constant OPERATE. */
	private static final String OPERATE = "/operate";

	/** The calculator service. */
	@MockBean
	private AddService addService;

	/** The wac. */
	@Autowired
	private WebApplicationContext webApplicationContext;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Setup.
	 */
	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	/**
	 * When operation is not valid then returns 400.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void whenParametersAreNotValidThenReturns400() throws Exception {
		when(addService.operate(new BigDecimal("0"), new BigDecimal("0"))).thenThrow(NoSuchBeanDefinitionException.class);
		mockMvc.perform(get(OPERATE).param("op1", "0.000").param("op2", "0.000").param("operation", "312312"))
				.andExpect(status().isBadRequest());
		mockMvc.perform(get(OPERATE).param("op", "0.000").param("op2", "0.000").param("operation", "add"))
				.andExpect(status().isBadRequest());
		mockMvc.perform(get(OPERATE).param("op1", "0.000").param("op", "0.000").param("operation", "add"))
				.andExpect(status().isBadRequest());
		mockMvc.perform(get(OPERATE).param("op1", "0.000").param("op2", "0.000").param("opera", "add"))
				.andExpect(status().isBadRequest());
	}

	/**
	 * When operation is valid then returns 200.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void whenParametersAreValidThenReturns200() throws Exception {
		when(addService.operate(new BigDecimal(0), new BigDecimal(0))).thenReturn(new BigDecimal(0.000));
		mockMvc.perform(get(OPERATE).param("op1", "0.000").param("op2", "0.000").param("operation", "add"))
				.andExpect(status().isOk());
	}

}
