package org.zk.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抬头：可输入中英文字和中英文括号，其余不可
 */
public class TitleValidator implements ConstraintValidator<Title, String> {

	private Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z()（）]+$");

	public void initialize(Title constraintAnnotation) {
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
		return true;
	}
}
