package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Transactions {

  Users users = new Users();
  private static int transactionOption;
  private static int amount;

  public static int getTransaction() {
    return transactionOption;
  }

  public void setTransaction() {
    System.out.println("""
        Please choose operation:
        1 - pop up your balance
        2 - withdrawal cash
        3 - create new account
        4 - exit""");
    Scanner scanner = new Scanner(System.in);
    transactionOption = scanner.nextInt();
  }

  public static int getAmount() {
    return amount;
  }

  public void setAmount() {
    System.out.println("Enter your amount:");
    Scanner scanner = new Scanner(System.in);
    amount = scanner.nextInt();
  }

  public void createTransactionExistedAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO TRANSACTIONS (accountId,amount) VALUES"
                + " ((SELECT accountId FROM ACCOUNTS WHERE currency = ? AND userId = ?),?)");
        statement.setString(1, Accounts.getAccountCurrency());
        statement.setInt(2, users.getUserId());
        statement.setInt(3, getAmount());
        statement.executeUpdate();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void createTransactionNewAccount() {
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO TRANSACTIONS (accountId,amount) VALUES"
                + " ((SELECT accountId FROM ACCOUNTS WHERE currency = ? AND userId = ?),?)");
        statement.setString(1, Accounts.getDepositCurrency());
        statement.setInt(2, users.getUserId());
        statement.setInt(3, Accounts.getBalance());
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

