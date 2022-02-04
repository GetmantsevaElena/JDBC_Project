package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Transactions {

  Users users = new Users();
  private static int transactionOption;

  public static int getTransaction() {
    return transactionOption;
  }

  public void setTransaction() {
    System.out.println("1 - to pop up your balance\n2 - to withdrawal cash");
    Scanner scanner = new Scanner(System.in);
    transactionOption = scanner.nextInt();
  }

  public Integer getAmount() {
    System.out.println("Enter your amount");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextInt();
  }

  public void createTransaction() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO TRANSACTIONS (accountId,amount) VALUES (?,?)");
        statement.setInt(1, Accounts.getAccountId());
        statement.setInt(2, getAmount());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void popUpBalance() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE ACCOUNTS SET balance = balance + ? WHERE userId = ?"
                + " AND currency = ?");
        statement.setInt(1, getAmount());
        statement.setInt(2, users.getUserId());
        statement.setString(3, Accounts.getAccountCurrency());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void withdrawalCash() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE ACCOUNTS SET balance = balance - ? WHERE userId = ?"
                + " AND currency = ?");
        statement.setInt(1, getAmount());
        statement.setInt(2, users.getUserId());
        statement.setString(3, Accounts.getAccountCurrency());
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

