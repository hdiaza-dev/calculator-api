package com.hdiaza.calculator.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * The Class CalculatorService.
 */
@Service
public class CalculatorService {

	/** The context. */
	@Autowired
	private ApplicationContext context;

	/**
	 * Operate.
	 *
	 * @param op1       the op 1
	 * @param op2       the op 2
	 * @param operation the operation
	 * @return the big decimal
	 * @throws NoSuchBeanDefinitionException the no such bean definition exception
	 */
	public BigDecimal operate(BigDecimal op1, BigDecimal op2, String operation) throws NoSuchBeanDefinitionException {
		return this.getCalculatorService(operation).operate(op1, op2);
	}

	/**
	 * Gets the calculator service.
	 *
	 * @param operation the operation
	 * @return the calculator service
	 * @throws NoSuchBeanDefinitionException the no such bean definition exception
	 */
	private ICalculatorService getCalculatorService(String operation) throws NoSuchBeanDefinitionException {
		return BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getAutowireCapableBeanFactory(),
				ICalculatorService.class, operation);
	}
}
