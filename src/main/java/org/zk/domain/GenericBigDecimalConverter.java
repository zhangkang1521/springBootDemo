package org.zk.domain;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 6/16/2018.
 */
public class GenericBigDecimalConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.add(new ConvertiblePair(Number.class, BigDecimal.class));
        convertiblePairs.add(new ConvertiblePair(String.class, BigDecimal.class));
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == BigDecimal.class) {
            return source;
        }

        if(sourceType.getType() == String.class) {
            String number = (String) source;
            return new BigDecimal(number);
        } else {
            Number number = (Number) source;
            BigDecimal converted = new BigDecimal(number.doubleValue());
            return converted.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        }
    }
}
