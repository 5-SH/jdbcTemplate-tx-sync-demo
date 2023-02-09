package com.seungho.jdbctemplatedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class UserService {

  @Autowired UserDao userDao;
  private TransactionTemplate transactionTemplate;

  @Autowired
  public void init(PlatformTransactionManager transactionManager) {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
  }

  public void addUserAll(List<String> nameList) {
    this.transactionTemplate.execute(status -> {
      for (String name : nameList) {
        userDao.addUser(name);
      }
      int count = userDao.getUserCount();
      System.out.println("count  " + count);

      return null;
    });
  }

  public void addAllAndUpdateUser(List<String> nameList) {
    this.transactionTemplate.execute(status -> {
      for (String name : nameList) {
        userDao.addUser(name);
      }
      int count1 = userDao.getUserCount();
      System.out.println("after add user all count : " + count1);

      userDao.updateUser(2, "update2");

      userDao.deleteUser(3);

      int count2 = userDao.getUserCount();
      System.out.println("after update and delete user count : " + count2);

      return null;
    });
  }

  public void addUserAllWithException(List<String> nameList) {
    this.transactionTemplate.execute(status -> {
      String exceptionName = nameList.get(nameList.size() - 1);
      for (String name : nameList) {
        if (name.equals(exceptionName)) {
          userDao.addUserWithException(name);
        } else {
          userDao.addUser(name);
        }

        System.out.println("after add user count : " + userDao.getUserCount());
      }


      return null;
    });
  }

  public void addAllAndUpdateUserWithException(List<String> nameList) {
    this.transactionTemplate.execute(status -> {
      for (String name : nameList) {
        userDao.addUser(name);
      }
      int count1 = userDao.getUserCount();
      System.out.println("after add user all count : " + count1);

      userDao.deleteUser(3);

      int count2 = userDao.getUserCount();
      System.out.println("after delete user count : " + count2);

      userDao.updateUserWithException(2, "update2");

      return null;
    });
  }

  public void addUserAllWithExceptionNotAppliedTx(List<String> nameList) {
    String exceptionName = nameList.get(nameList.size() - 1);
    for (String name : nameList) {
      if (name.equals(exceptionName)) {
        userDao.addUserWithException(name);
      } else {
        userDao.addUser(name);
      }
      System.out.println("after add user count : " + userDao.getUserCount());
    }
  }

  public void addAllAndUpdateUserWithExceptionNotAppliedTx(List<String> nameList) {
    for (String name : nameList) {
      userDao.addUser(name);
    }
    int count1 = userDao.getUserCount();
    System.out.println("after add user all count : " + count1);

    userDao.deleteUser(3);

    int count2 = userDao.getUserCount();
    System.out.println("after delete user count : " + count2);

    userDao.updateUserWithException(2, "update2");
  }
}
