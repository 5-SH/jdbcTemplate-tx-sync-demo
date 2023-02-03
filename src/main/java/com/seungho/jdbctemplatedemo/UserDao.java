package com.seungho.jdbctemplatedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

  @Autowired
  JdbcTemplate jdbcTemplate;

  void addUser(String name) {
    int seq = jdbcTemplate.queryForObject("select count(*) from test_user", Integer.class) + 1;
    jdbcTemplate.execute("insert into test_user values (" + seq + ", '" + name + "')");
  }

  void addUserWithException(String name) {
    int seq = jdbcTemplate.queryForObject("select count(*) from test_user", Integer.class) + 1;
    jdbcTemplate.execute("insert into test_user values (" + seq + ", '" + name + "')");

    throw new IllegalArgumentException("Query Exception" + name);
  }

  int getUserCount() { return jdbcTemplate.queryForObject("select count(*) from test_user", Integer.class); }

  void deleteAll() { jdbcTemplate.execute("delete from test_user"); }

  void updateUser(int seq, String name) {
    jdbcTemplate.execute("update test_user set name = '" + name + "' where id = " + seq);
  }

  void updateUserWithException(int seq, String name) {
    jdbcTemplate.execute("update test_user set name = '" + name + "' where id = " + seq);

    throw new IllegalArgumentException("Query Exception" + name);
  }

  void deleteUser(int seq) {
    jdbcTemplate.execute("delete from test_user where id = " + seq);
  }
}
