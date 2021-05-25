package com.hdiaza.calculator.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hdiaza.calculator.services.CalculatorTracerService;
import com.hdiaza.calculator.services.ICalculatorService;

/**
 * The Class CalculatorController.
 */
@Controller
public class CalculatorController {

	/** The Constant CALCULATOR_ADD. */
	public static final String CALCULATOR_ADD = "/operate";

	/** The calculator tracer service. */
	@Autowired
	private CalculatorTracerService calculatorTracerService;

	@Autowired
	private ApplicationContext context;

	/**
	 * Operate.
	 *
	 * @param operator the operator
	 * @return the response entity
	 */ 
	@GetMapping(value = CALCULATOR_ADD, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> operate(@RequestParam("op1") BigDecimal op1, @RequestParam("op2") BigDecimal op2,
			@RequestParam("operation") String operation) {
		return new ResponseEntity<>(this.getCalculatorService(operation).operate(op1, op2), HttpStatus.OK);
	}

	private ICalculatorService getCalculatorService(String operation) {
		return BeanFactoryAnnotationUtils.qualifiedBeanOfType(context.getAutowireCapableBeanFactory(),
				ICalculatorService.class, operation);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoSuchBeanDefinitionException.class)
	public @ResponseBody void handleNoSuchBeanDefinitionException(NoSuchBeanDefinitionException ex) {
		calculatorTracerService.trace("Operacion no soportada");

	}

}
