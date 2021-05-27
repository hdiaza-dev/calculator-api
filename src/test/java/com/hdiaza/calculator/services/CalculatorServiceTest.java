package com.hdiaza.calculator.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The Class CalculatorServiceTest.
 */
@SpringBootTest
class CalculatorServiceTest {

	/** The calculator service. */
	@Autowired
	private CalculatorService calculatorService;

	/**
	 * When sub operation return correct value.
	 */
	@Test
	void whenValidOperationReturnCorrectValue() {
		BigDecimal result = new BigDecimal("1.002");
		BigDecimal op1 = new BigDecimal("0.001");
		BigDecimal op2 = new BigDecimal("1.001");
		String operation = "add";
		assertThat(calculatorService.operate(op1, op2, operation)).isEqualTo(result);
	}

	/**
	 * When invalid operation return no such bean definition exception.
	 */
	@Test
	void whenInvalidOperationReturnNoSuchBeanDefinitionException() {
		BigDecimal op1 = new BigDecimal("0.001");
		BigDecimal op2 = new BigDecimal("1.001");
		String operation = "invalid_op";
		assertThrows(NoSuchBeanDefinitionException.class, () -> calculatorService.operate(op1, op2, operation));
	}

}
