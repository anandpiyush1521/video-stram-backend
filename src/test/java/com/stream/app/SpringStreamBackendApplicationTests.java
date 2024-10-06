package com.stream.app;

import com.stream.app.services.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStreamBackendApplicationTests {

	@Autowired
	private VideoService videoService;
	@Test
	void contextLoads() {
		//videoService.processVideo("2ff6ce54-c489-4191-a4c1-45e5b85da9ba", null);
	}

}
