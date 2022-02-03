package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Users {

  private String userId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getNumber() {
    System.out.println("Enter your phone number");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getName() {
    System.out.println("Enter user name");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getAddress() {
    System.out.println("Enter address");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static void addUser() {
    Users m = new Users();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        m.insert(connection);
        m.select(connection);
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Add new user to the table
  private void insert(Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement
        ("INSERT INTO USERS (userId, name, address) VALUES (?,?,?)");
    statement.setString(1, getNumber());
    statement.setString(2, getName());
    statement.setString(3, getAddress());
    statement.executeUpdate();
    statement.close();
  }

  //Get a userId from table
  private void select(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
    while (resultSet.next()) {
      setUserId(resultSet.getString("userId"));
  //This strings are just for sure that I put data and get userId
//      String id = getUserId();
//      String str = rs.getString("userId")
//           + ":" + rs.getString("name")
//           + ":" + rs.getString("address");
//      System.out.println(str + " " + id);
    }
    resultSet.close();
    statement.close();
  }
}