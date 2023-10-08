package org.iclass.mvc.controller.formatter;

import org.springframework.format.Formatter;

import java.sql.Date;
import java.text.ParseException;
import java.util.Locale;

//String 으로 전달된 파라미터를 java.sql.Date 타입으로 변환하기  . dto 에 애노테이션으로 해결할 수 있음.
public class DateFormatter implements Formatter<Date> {
	
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		
		return Date.valueOf(text);
	}
	@Override
	public String print(Date object, Locale locale) {
		// TODO Auto-generated method stub
		return String.valueOf(object);
	}

}
