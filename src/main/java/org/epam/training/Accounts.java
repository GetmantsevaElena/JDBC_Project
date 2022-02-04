package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts {

  private String accountId;
  private String accountCurrency;
  Users users = new Users();

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public static String getBalance() {
    System.out.println("Enter the amount you want to deposit");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static String getCurrency() {
    System.out.println("Enter currency of your deposit: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getAccountCurrency() {
    System.out.println("Choose your account currency: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    accountCurrency = scanner.next();
    return accountCurrency;
  }

  public void createAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO ACCOUNTS (userId,balance,currency) VALUES (?,?,?)");
        statement.setInt(1, users.getUserId());
        statement.setString(2, getBalance());
        statement.setString(3, getCurrency());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void showAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM Accounts WHERE userId = " + "'" + users.getUserId() + "'");
        System.out.println("Your accounts:");
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getString("userId")
              + "\nBalance " + resultSet.getString("balance")
              + "\nCurrency " + resultSet.getString("currency");
          System.out.println(str);
          System.out.println("--------------------");
        }
        resultSet.close();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void showChoosenAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM Accounts WHERE currency = " + "'" + accountCurrency + "'" +
                "AND userId = " + "'" + users.getUserId() + "'");
        System.out.println("Your account:");
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getString("userId")
              + "\nBalance " + resultSet.getString("balance")
              + "\nCurrency " + resultSet.getString("currency");
          System.out.println(str);
          System.out.println("--------------------");
        }
        resultSet.close();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

