package com.ait.apex;

import java.lang.reflect.Field;

/**
 * Created by Akshay on 1/26/2017.
 */
public class MetaTest
{
	public static class TestClass {
		public int intField;
		public char charField;
		public String name;
		public int age;
		
		public TestClass(int intField, char charField, String name, int age)
		{
			this.intField = intField;
			this.charField = charField;
			this.name = name;
			this.age = age;
		}
		
		@Override
		public String toString()
		{
			return "TestClass{" +
					"intField=" + intField +
					", charField=" + charField +
					", name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
	
	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
		Class clazz = TestClass.class;
		
		for (Field field : clazz.getFields()) {
			System.out.println("field name " + field.getName() + " type " + field.getType() + " access type " + field.getModifiers());
		}
		
		TestClass obj = new TestClass(10, 'a', "Tushar", 34);
		System.out.println(obj.toString());
		
		// now try to get the value of fields using reflection
		System.out.println("Extraction values from Pojo using reflection");
		for (Field field : clazz.getFields()) {
			System.out.println("Field name " + field.getName() + " value " + field.get(obj));
		}
		
		// extract value of name from the TestClass
		Field field = clazz.getField("name");
		System.out.println("name is " + field.get(obj));
		
	}
}
