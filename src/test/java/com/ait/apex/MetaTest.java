package com.ait.apex;

import com.ait.apex.row.Coder;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by Akshay on 1/26/2017.
 */
public class MetaTest {
	public static class TestClass {
		public int intField;
		public char charField;
		public String name;
		public int age;
		
		public TestClass(int intField, char charField, String name, int age) {
			this.intField = intField;
			this.charField = charField;
			this.name = name;
			this.age = age;
		}
		
		@Override
		public String toString() {
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
		
		System.out.println("Extraction values from Pojo using reflection");
		for (Field field : clazz.getFields()) {
			Type type = field.getType();
			if (type.equals(int.class)) {
				System.out.println("Field name : " + field.getName() + " value : " + field.get(obj));
			} else if (type.equals(char.class)) {
				System.out.println("Field name : " + field.getName() + " value : " + field.get(obj));
			} else if (type.equals(String.class)) {
				System.out.println("Field name : " + field.getName() + " value : " + field.get(obj));
			}
			
		}
		
		Field field = clazz.getField("name");
		System.out.println("name is " + field.get(obj));
	}
}
