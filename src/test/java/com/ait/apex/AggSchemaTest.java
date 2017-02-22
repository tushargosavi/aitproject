package com.ait.apex;

import com.ait.apex.aggregator.*;
import com.ait.apex.row.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class AggSchemaTest {
	@Test
	
	public void aggTestWithOne() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		String[] keys = {"name","age"};
		AggregationMetrics metrics = new AggregationMetrics(keys, AggregationTypes.COUNT);
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(metrics);
		
		
		AggregationHelper aggregationHelper = new AggregationHelper();
		AggregationSchema aggregationSchema = aggregationHelper.createAggregationSchema(rowMeta, metricsList);
		
		System.out.println(aggregationSchema.toString());
	}
	
	@Test
	public void aggTestWithTwo()
	{
		
	}
}
