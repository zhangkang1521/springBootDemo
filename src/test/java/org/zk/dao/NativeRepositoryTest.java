package org.zk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zk.dto.UserDto;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NativeRepositoryTest {

	@Autowired
	NativeRepository nativeRepository;

	@Test
	public void queryList() {
		String sql = "select id, username from tb_user";
		List<UserDto> list = nativeRepository.queryList(sql, null, UserDto.class);
	}
}