package org.iclass.mvc.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//String 으로 전달된 파라미터를 LocalDate 타입으로 변환하기
public class LocalDateFormatter implements Formatter<LocalDate> {
	
	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		
		return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	@Override
	public String print(LocalDate object, Locale locale) {
		// TODO Auto-generated method stub
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
	}


}
