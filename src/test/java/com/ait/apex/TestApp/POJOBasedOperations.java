package com.ait.apex.TestApp;


import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.aggregator.meta.AggregationSchema;
import com.ait.apex.row.EntryField;
import com.ait.apex.row.Row;

import java.util.Map;

public class POJOBasedOperations implements OperationsTest {
	
	public void test_Operations(EntryField entryField, AggregationMetrics metrics, AggregationSchema schema, Map<Row, Row> resultMap)
	{
		switch (metrics.getAggTypes())
		{
			case SUM:
				
				break;
		}
	}
	
	@Override
	public int hashCode(Row row) {
		
		return row.dataBytes.hashCode();
	}
	
	@Override
	public boolean equals(Row a, Row b) {
		if(a.dataBytes.hashCode() == b.dataBytes.hashCode())
			return true;
		return false;
	}
}
