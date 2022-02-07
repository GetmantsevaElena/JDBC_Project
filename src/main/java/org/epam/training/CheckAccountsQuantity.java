package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckAccountsQuantity {

  public static int checkAccountsQuantity() {
    Users users = new Users();
    int accountsQuantity = 0;

    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(accountId)"
            + " FROM ACCOUNTS WHERE userId =" + "'" + users.getUserId() + "'");
        while (resultSet.next()) {
          accountsQuantity = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (accountsQuantity);
  }
}




