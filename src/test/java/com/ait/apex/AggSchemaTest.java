package com.ait.apex;

import com.ait.apex.aggregator.AggregationHelper;
import com.ait.apex.aggregator.AggregationMetrics;
import com.ait.apex.aggregator.AggregationTypes;
import com.ait.apex.row.DataType;
import com.ait.apex.row.RowMeta;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AggSchemaTest {
	@Test
	
	public void aggTest()
	{
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
		aggregationHelper.createAggregationSchema(rowMeta, metricsList);
	}
}
