package com.ait.apex.aggregator.operations;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.RowValueFunctions;
import com.ait.apex.row.Row;

import java.util.HashMap;
import java.util.Map;

public class Operations
{
	long max = 0;
	public Map<Row, Row> operations(Map.Entry<Row,Row> byteEntry, AggregationMetrics metrics, AggregationSchema schema, Map<Row, Row> resultMap)
	{
		RowValueFunctions rowValueFunctions = new RowValueFunctions();
		switch (metrics.getAggTypes())
		{
			case MAX:
				Row valRow = byteEntry.getValue();
				long longVal = rowValueFunctions.readInteger(valRow, schema.valueSchema);
				if(longVal >= max){
					max = longVal;
					resultMap.put(byteEntry.getKey(), byteEntry.getValue());
				}
				break;
			case MIN:
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
