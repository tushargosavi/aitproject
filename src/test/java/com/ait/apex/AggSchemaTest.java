package com.ait.apex;

import com.ait.apex.aggregator.meta.AggregationHelper;
import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.aggregator.meta.AggregationTypes;
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
		String vals[] = {"number"};
		AggregationMetrics metrics = new AggregationMetrics(keys, vals,  AggregationTypes.MAX);
		List<AggregationMetrics> metricsList = new ArrayList<>();
		metricsList.add(metrics);
		
		
		AggregationHelper aggregationHelper = new AggregationHelper();
		List<AggregationSchema> aggregationSchema = aggregationHelper.createAggregationSchema(rowMeta, metricsList);
		
		System.out.println(aggregationSchema.toString());
	}
	
	@Test
	public void aggTestWithTwo() throws NoSuchFieldException, IllegalAccessException {
		RowMeta rowMeta = new RowMeta();
		rowMeta.addField("name", DataType.STRING);
		rowMeta.addField("age", DataType.INTEGER);
		rowMeta.addField("number", DataType.LONG);
		rowMeta.addField("character", DataType.CHARACTER);
		
		List<AggregationMetrics> metricsList = new ArrayList<>();
		String[] keys = {"number"};
		String vals[] = {"cost"};
		
		metricsList.add(new AggregationMetrics(new String[]{"name"}, vals, AggregationTypes.COUNT));
		metricsList.add(new AggregationMetrics(new String[]{"name"}, vals,AggregationTypes.MAX));
		metricsList.add(new AggregationMetrics(new String[]{"name","age","number"}, vals,AggregationTypes.MAX));
		
		AggregationHelper aggregationHelper = new AggregationHelper();
		List<AggregationSchema> schemaList = aggregationHelper.createAggregationSchema(rowMeta, metricsList);
		
		for(AggregationSchema schema : schemaList)
		{
			System.out.println(schema.toString());
		}
	}
}
