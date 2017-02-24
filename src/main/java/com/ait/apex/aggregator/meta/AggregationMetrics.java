package com.ait.apex.aggregator.meta;

import java.util.Arrays;

public class AggregationMetrics
{
	String[] keys;
	AggregationTypes aggTypes;
	
	public AggregationMetrics(String[] keys, AggregationTypes aggTypes) {
		this.keys = keys;
		this.aggTypes = aggTypes;
	}
	
	public String[] getKeys()
	{
		return keys;
	}

	public void setKeys(String[] keys)
	{
		this.keys = keys;
	}

	public AggregationTypes getAggTypes()
	{
		return aggTypes;
	}

	public void setAggTypes(AggregationTypes aggTypes)
	{
		this.aggTypes = aggTypes;
	}

	@Override
	public String toString()
	{
		return "AggregationMetrics{" +
			"keys=" + Arrays.toString(keys) +
			", aggTypes=" + aggTypes +
			'}';
	}
}
