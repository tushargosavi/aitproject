package com.ait.apex.aggregator;

import com.ait.apex.row.DataType;
import com.ait.apex.row.RowMeta;

import java.util.List;

public class AggregationHelper {
	
	public AggregationSchema createAggregationSchema(RowMeta originalSchema, List<AggregationMetrics> aggregationMetrics) throws NoSuchFieldException, IllegalAccessException {
		
		AggregationSchema schema = new AggregationSchema();
		
		for (AggregationMetrics aggregationMeta : aggregationMetrics) {
			for (String key : aggregationMeta.getKeys()) {
				if (originalSchema.isPresent(originalSchema, key)) {
					schema.keySchema.addField(key, originalSchema.getDataType(originalSchema, key));
				}
			}
			switch (aggregationMeta.getAggTypes()) {
				case COUNT:
					schema.valueSchema.addField("count", DataType.LONG);
					break;
				
				case SUM:
					schema.valueSchema.addField("sum", DataType.LONG);
					break;
				
				case MIN:
					schema.valueSchema.addField("min", DataType.LONG);
					break;
				
				case MAX:
					schema.valueSchema.addField("max", DataType.LONG);
					break;
			}
		}
		return schema;
	}
}
