package com.sattv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.sattv.model.Channel;

public class AddChannel {

	public static void addChannel()
	{
		List<Channel> channelList = new ArrayList<>();
		
		channelList.add(new Channel(1L,10.00,"SONY"));
		channelList.add(new Channel(2L,20.00,"Z TV"));
		channelList.add(new Channel(3L,15.00,"SET MAX"));
		channelList.add(new Channel(4L,18.00,"STAR SPORT"));
		channelList.add(new Channel(5L,25.00,"Z CINEMA"));
		channelList.add(new Channel(6L,08.00,"COLORS"));
		channelList.add(new Channel(7L,14.00,"SONY MAX"));
		channelList.add(new Channel(8L,18.00,"Z MARATHI"));
		channelList.add(new Channel(9L,19.00,"GOLD"));
		channelList.add(new Channel(10L,22.00,"STAR PLUS"));
		channelList.add(new Channel(11L,21.00,"STAR PRAVAH"));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("DummyData/channel.json"), channelList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
