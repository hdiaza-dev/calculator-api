package com.hdiaza.calculator.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import io.corp.calculator.TracerAPI;
import lombok.extern.slf4j.Slf4j;

/** The Constant log. */
@Slf4j
@Service
public class CalculatorTracerService implements TracerAPI {

	@Override
	public <T> void trace(T result) {
		String resultString = "result :: ".concat("Resultado ".concat(result.toString()));
		Pattern.compile(":").splitAsStream(resultString).skip(2).findFirst().ifPresent(log::info);
	}

}
