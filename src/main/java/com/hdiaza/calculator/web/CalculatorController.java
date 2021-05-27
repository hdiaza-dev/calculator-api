package com.hdiaza.calculator.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hdiaza.calculator.services.CalculatorService;
import com.hdiaza.calculator.services.CalculatorTracerService;

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
	private CalculatorService calculatorService;

	/**
	 * Operate.
	 *
	 * @param operator the operator
	 * @return the response entity
	 */
	@GetMapping(value = CALCULATOR_ADD, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BigDecimal> operate(@RequestParam("op1") BigDecimal op1, @RequestParam("op2") BigDecimal op2,
			@RequestParam("operation") String operation) {
		return new ResponseEntity<>(this.calculatorService.operate(op1, op2, operation), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ NoSuchBeanDefinitionException.class })
	public @ResponseBody void handleNoSuchBeanDefinitionException(NoSuchBeanDefinitionException ex) {
		calculatorTracerService.trace("Operacion no soportada ");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public @ResponseBody void handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		calculatorTracerService.trace("Nombre de parametros de la operacion no válidos ".concat(ex.getMessage()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public @ResponseBody void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		calculatorTracerService.trace("Valor de parametros de la operacion no válidos  ".concat(ex.getMessage()));
	}

}
