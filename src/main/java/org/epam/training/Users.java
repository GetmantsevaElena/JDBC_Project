package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Users {

  private static Integer userId;
  private static String name;
  private static String address;

  public Integer getUserId(){
    return userId;
  }

  public String getName(){
    return name;
  }

  public String getAddress(){
    return address;
  }
  public static void setUserId() {
    System.out.println("Enter your phone number");
    Scanner scanner = new Scanner(System.in);
    userId = scanner.nextInt();
  }

  public static void setName() {
    System.out.println("Enter user name");
    Scanner scanner = new Scanner(System.in);
    name = scanner.next();
  }

  public static void setAddress() {
    System.out.println("Enter address");
    Scanner scanner = new Scanner(System.in);
    address = scanner.next();
  }

  public void addUser() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        PreparedStatement statement = connection.prepareStatement
            ("INSERT INTO USERS (userId, name, address) VALUES ( ?, ?, ?)");
        statement.setInt(1, userId);
        statement.setString(2, name);
        statement.setString(3, address);
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