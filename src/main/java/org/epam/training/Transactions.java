package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Transactions extends Accounts{

  public String getTransaction() {
    System.out.println("Enter: 1 to pop up your balance"
        + "2 to withdrawal cash");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public String getAmount() {
    System.out.println("Enter your amount");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }

  public static void showAccount() {

    Transactions s = new Transactions();

    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection con = DriverManager.getConnection(url, login, password);
      try {
        //Actually that`s not working)
//        return switch (s.getTransaction()) {
//          case "1" -> s.popUpBalance(con);
//          case "2" -> s.withdrawalCash(con);
//        };
      } finally {
        con.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createTransaction(Connection con) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("INSERT INTO TRANSACTIONS"
        + " (accountId, amount) VALUES (?,?)");
    stmt.setString(1, getAccountId());
    stmt.setString(2, getAmount());
    stmt.executeUpdate();
    stmt.close();
  }

  private void popUpBalance(Connection con) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("UPDATE ACCOUNTS SET balance = ?");
    stmt.setString(1, getAccountId());

    stmt.executeUpdate();
    stmt.close();
  }

  private void withdrawalCash(Connection con) throws SQLException {
    PreparedStatement stmt = con.prepareStatement("INSERT INTO TRANSACTIONS (accountId,amount)"
        + "(?,?)");
    stmt.setString(1, getAccountId());
    stmt.setString(2, getBalance());
    stmt.executeUpdate();
    stmt.close();
  }

  private void showBalance(Connection con) throws SQLException {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS");
    while (rs.next()) {
      String str =
          rs.getString(1) +
    ":" + rs.getString(2) +
    ":" + rs.getString(3);
      System.out.println(str);
    }
    rs.close();
    stmt.close();
  }
}
