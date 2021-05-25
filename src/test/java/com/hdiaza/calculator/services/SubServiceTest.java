package com.hdiaza.calculator.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * The Class SubServiceTest.
 */
@SpringBootTest
class SubServiceTest {

	/** The sub service. */
	@InjectMocks
	private SubService subService;

	/**
	 * When sub operation return correct value.
	 */
	@Test
	void whenSubOperationReturnCorrectValue() {
		BigDecimal result = new BigDecimal("-9.99999");
		BigDecimal op1 = new BigDecimal("13.14160");
		BigDecimal op2 = new BigDecimal("23.14159");
		assertThat(subService.operate(op1, op2)).isEqualTo(result);
	}

	/**
	 * When sub operation with scale and precision return correct value.
	 */
	@Test
	void whenSubOperationWithScaleAndPrecisionReturnCorrectValue() {
		BigDecimal result = new BigDecimal("0.009");
		BigDecimal op1 = new BigDecimal("3.14160").setScale(2, RoundingMode.CEILING);
		BigDecimal op2 = new BigDecimal("3.14159").setScale(3, RoundingMode.FLOOR);
		assertThat(subService.operate(op1, op2)).isEqualTo(result);
	}

}
