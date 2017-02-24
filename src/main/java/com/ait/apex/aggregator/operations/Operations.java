package com.ait.apex.aggregator.operations;

import com.ait.apex.aggregator.meta.AggregationMetrics;
import com.ait.apex.row.Row;
import com.ait.apex.row.RowMeta;

public class Operations
{
	public Row operations(RowMeta rowMeta, Object object, AggregationMetrics metrics)
	{
		switch (metrics.getAggTypes())
		{
			case MAX:
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
	}
}
