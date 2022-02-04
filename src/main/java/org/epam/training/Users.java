package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Users {

  private Integer userId;
  private String name;
  private String address;

  public Users() {

  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public Integer UserId() {
    System.out.println("Enter your phone number");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public String Name() {
    System.out.println("Enter user name");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String Address() {
    System.out.println("Enter address");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static void addUser() {
    Users users = new Users();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        PreparedStatement statement = connection.prepareStatement
            ("INSERT INTO USERS (userId, name, address) VALUES (?,?,?)");
        statement.setInt(1, users.getUserId());
        statement.setString(2, users.getName());
        statement.setString(3, users.getAddress());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}