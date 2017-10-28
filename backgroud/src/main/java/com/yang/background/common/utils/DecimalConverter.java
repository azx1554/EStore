package com.yang.background.common.utils;

import java.math.BigInteger;

public class DecimalConverter {

	private static final String[] NUM_TABLE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private static final int MAX_RADIX = 62;
	private static final int MIN_RADIX = 2;

	public static String fromDecimal(String decimalText, int radix) throws NumberFormatException, Exception {
		if (decimalText == null || decimalText.trim().length() < 1) {
			return decimalText;
		}

		BigInteger radixInteger = new BigInteger(Integer.toString(radix));
		BigInteger decimalInteger = new BigInteger(decimalText);
		return fromDecimal(decimalInteger, radixInteger);
	}

	public static String fromDecimal(BigInteger decimalValue, BigInteger radixInteger) throws Exception {

		if (radixInteger.intValue() < MIN_RADIX || radixInteger.intValue() > MAX_RADIX) {
			throw new Exception("只能转换为2-62进制");
		}

		if (decimalValue.compareTo(BigInteger.ZERO) < 0) {
			return "-" + fromDecimal(decimalValue.negate(), radixInteger);
		}

		if (decimalValue.compareTo(radixInteger) < 0) {
			return NUM_TABLE[decimalValue.intValue()];
		} else {
			BigInteger[] result = decimalValue.divideAndRemainder(radixInteger);
			BigInteger suffix = result[1];// 整除的余数
			String prefix = fromDecimal(result[0], radixInteger);
			return prefix + NUM_TABLE[suffix.intValue()];
		}
	}

}
