package com.hdiaza.calculator.services;

import java.math.BigDecimal;

public interface ICalculatorService {

	public BigDecimal operate(BigDecimal op1, BigDecimal op2);

}