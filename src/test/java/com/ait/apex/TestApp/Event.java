package com.ait.apex.TestApp;

import it.unimi.dsi.fastutil.Hash;

public class Event
{
	public static interface Aggregator<Row, Aggregate extends Event> extends Hash.Strategy<Row>
	{
		
	}
}
