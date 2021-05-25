package com.hdiaza.calculator.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The Class AddServiceTest.
 */
@SpringBootTest
class AddServiceTest {

	/** The add service. */
	@InjectMocks
	private AddService addService;

	/**
	 * When add operation return correct value.
	 */
	@Test
	void whenAddOperationReturnCorrectValue() {
		BigDecimal result = new BigDecimal("6.28318");
		BigDecimal op1 = new BigDecimal("3.14159");
		BigDecimal op2 = new BigDecimal("3.14159");
		assertThat(addService.operate(op1, op2)).isEqualTo(result);
	}

	/**
	 * When add operation with scale and precision return correct value.
	 */
	@Test
	void whenAddOperationWithScaleAndPrecisionReturnCorrectValue() {
		BigDecimal result = new BigDecimal("16.291");
		BigDecimal op1 = new BigDecimal("13.14159").setScale(2, RoundingMode.CEILING);
		BigDecimal op2 = new BigDecimal("3.14160").setScale(3, RoundingMode.FLOOR);
		assertThat(addService.operate(op1, op2)).isEqualTo(result);
	}

}
