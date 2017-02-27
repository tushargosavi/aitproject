package com.ait.apex.aggregator.operations;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.RowValueFunctions;
import com.ait.apex.row.Row;

import java.util.HashMap;
import java.util.Map;

public class Operations
{
	long max = 0, min = 99;
	public Map<Row, Row> operations(Map.Entry<Row,Row> byteEntry, AggregationMetrics metrics, AggregationSchema schema, Map<Row, Row> resultMap)
	{
		RowValueFunctions rowValueFunctions = new RowValueFunctions();
		switch (metrics.getAggTypes())
		{
			case MAX:
				if(resultMap.containsKey(byteEntry.getKey()))
				{
					long maxVal = rowValueFunctions.readInteger(byteEntry.getValue(), schema.valueSchema);
					if(maxVal > max){
						max = maxVal;
						resultMap.put(byteEntry.getKey(), byteEntry.getValue());
					}
				}
				else
				{
					resultMap.put(byteEntry.getKey(), byteEntry.getValue());
				}
				break;
			case MIN:
				long minVal = rowValueFunctions.readInteger(byteEntry.getValue(), schema.valueSchema);
				if(minVal < min){
					min = minVal;
					resultMap.put(byteEntry.getKey(), byteEntry.getValue());
				}
				break;
			case COUNT:
				break;
			case SUM:
				break;
			case AVG:
				break;
		}
		return resultMap;
	}
}
