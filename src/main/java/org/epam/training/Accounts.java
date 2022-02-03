package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts extends Users {

  private String accountId;

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getBalance() {
    System.out.println("Enter the amount you want to deposit");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getCurrency() {
    System.out.println("Enter currency");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static void createAccount() {

    Accounts s = new Accounts();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        s.insert(connection);
        s.select(connection);
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void insert(Connection connection) throws SQLException {
    PreparedStatement statement = connection.prepareStatement(
        "INSERT INTO ACCOUNTS (userId,balance,currency)"
            + " VALUES (?,?,?)");
    //In the next line I have an exception, because of NULL...I have no idea why
    statement.setString(1, getUserId());
    statement.setString(2, getBalance());
    statement.setString(3, getCurrency());
    statement.executeUpdate();
    statement.close();
  }

  private void select(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNTS");
    while (resultSet.next()) {
      setAccountId(resultSet.getString("accountId"));
      //This strings are just for sure that I put data and get userId
//      String str = resultSet.getString(1) +
//          ":" + resultSet.getString(2) +
//          ":" + resultSet.getString(3) +
//          ":" + resultSet.getString(4);
//      System.out.println(str);
    }
    resultSet.close();
    statement.close();
  }
}