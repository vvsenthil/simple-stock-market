package com.jpmorgan.api.calculator;

import java.io.Serializable;
import java.math.MathContext;
import java.math.RoundingMode;

public interface Calculator extends Serializable {
	int PRECISION = 6;
	MathContext DEFAULT_MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_EVEN);
}
