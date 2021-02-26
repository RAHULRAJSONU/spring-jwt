package io.github.rahulrajsonu.springsecurityjwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Pattern;

@Component
public class Validator {

    @Autowired
    @Qualifier("validation-regex")
    private Map<String, String> regexMap;

    @Autowired
    @Qualifier("validation-error")
    private Map<String, String> errorMap;

    public boolean validate(Fields field, String value){
        String regex = regexMap.get(field.toString());
        if(null==regex) return Boolean.TRUE;
        String error = errorMap.get(field.toString());
        final Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(value).matches()){
            return Boolean.TRUE;
        }else {
            throw new RuntimeException(error);
        }
    }

    public enum Fields{
        segment,id
    }
}
