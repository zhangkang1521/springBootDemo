package org.zk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void findUser() {
		userService.findUser(Arrays.asList(1, 2));
//		userService.findUser(Arrays.asList(1, 2));
	}
}