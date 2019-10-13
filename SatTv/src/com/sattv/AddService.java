package com.sattv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.sattv.model.Service;

public class AddService {
	public static void addService() {
		List<Service> serviceList = new ArrayList<>();

		serviceList.add(new Service(1L, 100.00, "LEARN ENGLISH SERVICE"));
		serviceList.add(new Service(2L, 150.00, "LEARN SPORT SERVICE"));
		serviceList.add(new Service(3L, 200.00, "LEARN COOKING SERVICE"));
		serviceList.add(new Service(4L, 150.00, "LEARN HINDI SERVICE"));
		serviceList.add(new Service(5L, 150.00, "LEARN DANCE SERVICE"));

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("DummyData/service.json"), serviceList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
