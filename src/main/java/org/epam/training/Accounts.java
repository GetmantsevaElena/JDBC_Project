package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Accounts {



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
    System.out.println("Enter currency: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getAccountCurrency() {
    System.out.println("Choose account currency: EUR, USD, BYN");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static void createAccount() {
    Users users = new Users();
    Accounts accounts = new Accounts();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO ACCOUNTS (userId,balance,currency)"
                + " VALUES (?,?,?)");
        //In the next line I have an exception, because of NULL...I have no idea why
        statement.setInt(1, users.getUserId());
        statement.setString(2, accounts.getBalance());
        statement.setString(3, accounts.getCurrency());
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
    Users users = new Users();

    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM Accounts WHERE userId = ? AND currency = ?");

        statement.setString(1, "%" + users.getUserId() + "%");
        statement.setString(2, "%" + getAccountCurrency() + "%");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
          String str = "UserID: " + resultSet.getString("userId")
              + "\nBalance " + resultSet.getString("balance")
              + "\nCurrency " + resultSet.getString("currency");
          System.out.println(str + " " + users.getUserId() +""+getAccountCurrency());
          resultSet.close();
          statement.close();
        }
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
