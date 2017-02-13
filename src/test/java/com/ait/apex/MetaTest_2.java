package com.ait.apex;

import java.util.Arrays;

import com.ait.apex.platform.Platform;
import com.ait.apex.row.*;
import org.junit.Test;

/**
 * Created by Akshay on 1/27/2017.
 */
public class MetaTest_2 {
	public class Person {
		public String name;
		public int age;
		public long number;
		public char character;

		public Person()
		{
		}

		public Person(String name, int age, long number, char character) {
			this.name = name;
			this.age= age;
			this.number = number;
			this.character = character;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					", number=" + number +
					", character=" + character +
					'}';
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			
			Person person = (Person) o;
			
			if (age != person.age) return false;
			if (number != person.number) return false;
			if (character != person.character) return false;
			return name != null ? name.equals(person.name) : person.name == null;
		}
		
		@Override
		public int hashCode() {
			int result = name != null ? name.hashCode() : 0;
			result = 31 * result + age;
			result = 31 * result + (int) (number ^ (number >>> 32));
			result = 31 * result + (int) character;
			return result;
		}
	}
	
	@Test
	public void testLength() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		Person person = new Person("akshay", 1234, 9890712L, 'a');
		
		ByteLength length = new ByteLength();
		
		int le = length.getByteLength(rowMeta,person);
		
		System.out.println("ByteLength is : "+le);
		
		Class personClass = person.getClass();
		for (FieldInfo fieldInfo : rowMeta.getFieldInfoList())
		{
			switch (fieldInfo.getDataType())
			{
				case STRING:
					System.out.println(fieldInfo.getName() +" "+ fieldInfo.getDataType() +" "+ personClass.getField(fieldInfo.getName()).get(person));
					break;
				case INTEGER:
					System.out.println(fieldInfo.getName() +" "+ fieldInfo.getDataType() +" "+ personClass.getField(fieldInfo.getName()).get(person));
					break;
				case CHARACTER:
					System.out.println(fieldInfo.getName() +" "+ fieldInfo.getDataType() +" "+ personClass.getField(fieldInfo.getName()).get(person));
					break;
				case LONG:
					System.out.println(fieldInfo.getName() +" "+ fieldInfo.getDataType() +" "+ personClass.getField(fieldInfo.getName()).get(person));
					break;
			}
		}
	}
	
	@Test
	public void testEncoder() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		Person person = new Person("akshay", 1234, 9890712L, 'a');
		
		ByteLength length = new ByteLength();
		int size = length.getByteLength(rowMeta, person);
		System.out.println(size);
		Row row = new Row();
		row.dataBytes = new byte[size];
		
		PojoBasedCoder coder = new PojoBasedCoder();
		row = coder.encoder(rowMeta, person);

		System.out.println(length.getVarOffset(rowMeta));

		System.out.println(Arrays.toString(row.getDataBytes()));
	}

	@Test
	public void encodingTest() throws NoSuchFieldException, IllegalAccessException
	{
		RowMeta rowMeta = new RowMeta();

		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		

		Person person = new Person("akshay", 1234, 9890712L, 'a');

		ByteLength length = new ByteLength();
		int len = length.getByteLength(rowMeta, person);
		int varoffset = length.getVarOffset(rowMeta);
		int rev_varoffset = length.getVarOffset(rowMeta);

		System.out.println(len);
		System.out.println(varoffset + " 	" + rev_varoffset);

		Row row = new Row();
		row.dataBytes = new byte[len];

		PojoBasedCoder enCoder = new PojoBasedCoder();

		row = enCoder.encoder(rowMeta, person);
		System.out.println(Arrays.toString(row.getDataBytes()));

		Person person1 = new Person();

		person1 = (Person)enCoder.decoder(rowMeta, row, person1);
		System.out.println(person1.toString());
	}
	
	@Test
	public void encodeChar()
	{
		char ch = 'a';
		byte chByte = (byte) ch;
		
		System.out.println(chByte);
	}
}
