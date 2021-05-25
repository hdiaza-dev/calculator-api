package com.hdiaza.calculator.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The Class SubService.
 */
@Service
@Qualifier("sub")
public class SubService implements ICalculatorService {

	/**
	 * Operate.
	 *
	 * @param op1       the op 1
	 * @param op2       the op 2
	 * @param precision the precision
	 * @return the big decimal
	 */
	@Override
	public BigDecimal operate(BigDecimal op1, BigDecimal op2) {
		return op1.subtract(op2);
	}

}
