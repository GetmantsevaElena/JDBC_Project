package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckBalance {

  private static int balance = 0;

  public static int checkBalance() {
    Users users = new Users();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT balance"
            + " FROM ACCOUNTS WHERE userId =" + "'" + users.getUserId() + "'" + " AND"
            + " currency = " + "'" + Accounts.getAccountCurrency() + "'");
        while (resultSet.next()) {
          balance = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (balance);
  }
}




