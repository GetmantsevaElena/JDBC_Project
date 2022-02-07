package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts {

  private static String accountCurrency;
  private static String currency;
  private static Double balance;
  Users users = new Users();

  public static Double getBalance() {
    return balance;
  }

  public static void setBalance() {
    System.out.println("Enter the amount you want to deposit");
    Scanner scanner = new Scanner(System.in);
    balance = Math.round((scanner.nextDouble()) * (int) Math.pow(10.0, 3)) / Math.pow(10.0, 3);
  }

  public static String getDepositCurrency() {
    return currency;
  }

  public static void setDepositCurrency() {
    System.out.println("Enter currency of your deposit: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    currency = scanner.next();
  }

  public static String getAccountCurrency() {
    return accountCurrency;
  }

  public static void setAccountCurrency() {
    System.out.println("Choose your account currency: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    accountCurrency = scanner.next();
  }

  public void createAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO ACCOUNTS (userId,balance,currency) VALUES (?,?,?)");
        statement.setInt(1, users.getUserId());
        statement.setDouble(2, getBalance());
        statement.setString(3, getDepositCurrency());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void showUserAccounts() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM ACCOUNTS WHERE userId = " + "'" + users.getUserId() + "'");
        System.out.println("Your accounts:");
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getString("userId")
              + "\nBalance: " + resultSet.getDouble("balance")
              + "\nCurrency: " + resultSet.getString("currency");
          System.out.println(str);
          System.out.println("----------------");
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

  public void showAccountAfterTransaction() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM ACCOUNTS WHERE currency = " + "'" + getAccountCurrency()
                + "'" + "AND userId = " + "'" + users.getUserId() + "'");
        System.out.println("Your account:");
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getInt("userId")
              + "\nBalance: " + resultSet.getDouble("balance")
              + "\nCurrency: " + resultSet.getString("currency");
          System.out.println(str);
          System.out.println("-------------");
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
  public void showCurrentAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM ACCOUNTS WHERE currency = " + "'" + getDepositCurrency()
                + "'" + "AND userId = " + "'" + users.getUserId() + "'");
        System.out.println("Your account:");
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getInt("userId")
              + "\nBalance: " + resultSet.getDouble("balance")
              + "\nCurrency: " + resultSet.getString("currency");
          System.out.println(str);
          System.out.println("-------------");
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

