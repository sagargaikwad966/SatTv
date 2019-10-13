package com.sattv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.sattv.model.Channel;
import com.sattv.model.Pack;

public class AddPack 
{
	public static void addPack()
	{

		
		List<Pack> packList = new ArrayList<>();
		List<Channel> cl1 = new ArrayList<>();
		cl1.add(new Channel(1L,10.00,"SONY"));
		cl1.add(new Channel(2L,20.00,"Z TV"));
		cl1.add(new Channel(3L,15.00,"SET MAX"));
		
		packList.add(new Pack(3L,150.00,"SILVER",cl1));
		
		cl1.add(new Channel(5L,25.00,"Z CINEMA"));
		
		packList.add(new Pack(2L,200.00,"GOLD",cl1));
		
		cl1.add(new Channel(4L,18.00,"STAR SPORT"));
		
		packList.add(new Pack(1L,250.00,"PLATINUM",cl1));
		
		

		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("DummyData/pack.json"), packList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
