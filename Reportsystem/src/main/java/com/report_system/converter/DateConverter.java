package com.report_system.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converter<S, T>
 *     S:页面传递过来的类型 2019-06-15 11:00:00
 *     T:转换之后的类型 Date
 */
public class DateConverter implements Converter<String ,Date> {
    @Override
    public Date convert(String str) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        try {
             date = simpleDateFormat.parse( str );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
