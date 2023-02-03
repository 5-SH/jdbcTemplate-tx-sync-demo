package com.seungho.jdbctemplatedemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class JdbctemplatedemoApplicationTests {

	@Autowired UserDao userDao;

	@Autowired UserService userService;

	@BeforeEach
	public void afterEach() {
		userDao.deleteAll();
	}

	@Test
	void addUser() {
		userDao.addUser("test1");
		Assertions.assertThat(userDao.getUserCount()).isEqualTo(1);
	}

	@Test
	void addAll() {
		userService.addUserAll(Arrays.asList("test1", "test2", "test3", "test4"));
		Assertions.assertThat(userDao.getUserCount()).isEqualTo(4);
	}

	@Test
	void addAllAndUpdate() {
		userService.addAllAndUpdateUser(Arrays.asList("test1", "test2", "test3", "test4"));
		Assertions.assertThat(userDao.getUserCount()).isEqualTo(3);
	}

	@Test
	void addAllOrNothing() {
		try {
			userService.addUserAllWithException(Arrays.asList("test1", "test2", "test3", "test4"));
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(0);
		} catch (IllegalArgumentException e) {
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(0);
		}
	}

	@Test
	void addAllOrNothingWithoutTransaction() {
		try {
			userService.addUserAllWithExceptionNotAppliedTx(Arrays.asList("test1", "test2", "test3", "test4"));
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(4);
		} catch (IllegalArgumentException e) {
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(4);
		}
	}

	@Test
	void addAllAndUpdateOrNothing() {
		try {
			userService.addAllAndUpdateUserWithException(Arrays.asList("test1", "test2", "test3", "test4"));
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(0);
		} catch (IllegalArgumentException e) {
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(0);
		}
	}

	@Test
	void addAllAndUpdateOrNothingWithoutTransaction() {
		try {
			userService.addAllAndUpdateUserWithExceptionNotAppliedTx(Arrays.asList("test1", "test2", "test3", "test4"));
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(3);
		} catch (IllegalArgumentException e) {
			Assertions.assertThat(userDao.getUserCount()).isEqualTo(3);
		}
	}
}
