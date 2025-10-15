package org.coding.generics;

import java.util.List;

public class IntegerToStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        return Integer.toString(source);
    }

    @Override
    public List<String> convertAll(List<Integer> sources) {
        return sources.stream()
                //.map(arg -> Integer.toString(arg))
                .map(this::convert)
                .toList();


                // referencja do metody: np: Number::doubleValue
    }
}
