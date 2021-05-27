package com.hdiaza.calculator.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdiaza.calculator.CalculatorApplication;
import com.hdiaza.calculator.services.CalculatorService;
import com.hdiaza.calculator.services.CalculatorTracerService;

@SpringBootTest
class CalculatorApplicationTests {

	@Autowired
	private CalculatorService calculatorService;

	@Autowired
	private CalculatorTracerService calculatorTracerService;

	@Test
	void contextLoads() {
		CalculatorApplication.main(new String[] { "test1", "test2" });
		assertThat(calculatorService).isNotNull();
		assertThat(calculatorTracerService).isNotNull();

	}
}