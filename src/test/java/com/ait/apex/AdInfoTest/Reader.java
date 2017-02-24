package com.ait.apex.AdInfoTest;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;


public class Reader
{
	public void reader(AdInfo adInfo)throws IOException
	{
		String path = "C:\\Users\\Akshay\\Documents\\InputFile.csv";
		CSVReader csvReader = new CSVReader(new FileReader(path), ',');
		
		String[] nextLine;
		
		while((nextLine = csvReader.readNext()) != null)
		{
			
			if(nextLine != null)
			{
				adInfo.setPublisher(nextLine[0]);
				adInfo.setAdvertiser(nextLine[1]);
				adInfo.setLocation((nextLine[2]));
				adInfo.setCost(Long.parseLong(nextLine[3]));
				adInfo.setImpressions((Long.parseLong(nextLine[4])));
				adInfo.setClicks(Boolean.parseBoolean(nextLine[5]));
			}
		}
	}
}
