package org.zk.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 税号：文本输入框，可输入数字和英文字，限制只可输入15位、18位、20位，其余不可
 */
public class TaxNoValidator implements ConstraintValidator<TaxNo, String> {

	private Pattern pattern = Pattern.compile("^[\\da-zA-Z]{15,20}$");

	public void initialize(TaxNo constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().equals("")) {
			return true;
		}
		value = value.trim();
		Matcher matcher = pattern.matcher(value);
		if (!matcher.find()) {
			return false;
		}
		if (value.length() != 15 && value.length() != 18 && value.length() != 20) {
			return false;
		}
		return true;
	}
}
