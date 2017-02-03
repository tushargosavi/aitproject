package com.ait.apex.row;

import java.lang.reflect.Field;

public class GetFieldValues {
	String getString(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field f = o.getClass().getField(fieldName);
		return (String) f.get(o);
	}
	
	int getInteger(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field f = o.getClass().getField(fieldName);
		return (Integer) f.get(o);
	}
	
	boolean getBoolean(Object o, String fieldName) throws IllegalAccessException, NoSuchFieldException {
		Field f = o.getClass().getField(fieldName);
		return (boolean) f.get(o);
	}
	
	long getLong(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field f = o.getClass().getField(fieldName);
		return (long) f.get(o);
	}
	
	char getCharacter(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field f = o.getClass().getField(fieldName);
		return (char) f.get(o);
	}
}
