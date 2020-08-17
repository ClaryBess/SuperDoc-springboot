package com.example.test;

import com.example.test.controller.BrowseController;
import com.example.test.service.BrowseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class TestApplicationTests {

	@Autowired
	DataSource dataSource;
	@Autowired
	BrowseService browseService;
	@Autowired
	BrowseController browseController;

	@Test
	public void contextLoads() throws SQLException {
		System.out.println(dataSource.getClass());
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}

	@Test
	public void testBrowse(){
		System.out.println(browseService.getBrowseByUser(1));
//		System.out.println(browseService.deleteBrowseByDocAndUser(1,1));
        System.out.println(browseController.deleteBrowse(2,1));
	}

}
