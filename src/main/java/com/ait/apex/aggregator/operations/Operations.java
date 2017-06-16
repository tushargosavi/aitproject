package com.ait.apex.aggregator.operations;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.Row;
import com.ait.apex.row.RowValueFunctions;

import java.util.Map;

public class Operations
{
	long max;
	public Map<Row, Row> operations(Map.Entry<Row, Row> byteEntry, AggregationMetrics metrics, AggregationSchema schema, Map<Row, Row> resultMap)
	{
		Row keyRow;
		Row valRow;
		RowValueFunctions rowValueFunctions = new RowValueFunctions();
		switch (metrics.getAggTypes())
		{
			case MAX:
				if(resultMap.containsKey(byteEntry.getKey()))
				{
					long currentVal = rowValueFunctions.readLong(byteEntry.getValue(), schema.valueSchema);
					valRow = resultMap.get(byteEntry.getKey());
					long currentMapVal = rowValueFunctions.readLong(valRow, schema.valueSchema);
					
					if(currentVal > currentMapVal)
					{
						valRow = rowValueFunctions.updateLong(resultMap.get(byteEntry.getKey()), schema.valueSchema, currentVal);
						resultMap.put(byteEntry.getKey(), valRow);
					}
				}
				else
				{
					resultMap.put(byteEntry.getKey(), byteEntry.getValue());
				}
				break;
			case MIN:
				if(resultMap.containsKey(byteEntry.getKey()))
				{
					long currentVal = rowValueFunctions.readLong(byteEntry.getValue(), schema.valueSchema);
					valRow = resultMap.get(byteEntry.getKey());
					long currentMapVal = rowValueFunctions.readLong(valRow, schema.valueSchema);
					
					if(currentVal < currentMapVal)
					{
						valRow = rowValueFunctions.updateLong(resultMap.get(byteEntry.getKey()), schema.valueSchema, currentVal);
						resultMap.put(byteEntry.getKey(), valRow);
					}
				}
				else
				{
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
