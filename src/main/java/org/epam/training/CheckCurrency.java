package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckCurrency extends Transactions {

  public static ArrayList<String> checkCurrency() {

    Users users = new Users();

    int columnCount = 0;
    ArrayList<String> userCurrencyList = new ArrayList<>(columnCount);
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection connection = DriverManager.getConnection(url, login, password);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT currency FROM Accounts WHERE"
            + " userId =" + users.getUserId() + ";");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        columnCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
          int i = 1;
          while (i <= columnCount) {
            userCurrencyList.add(resultSet.getString(i++));
          }
          resultSet.close();
          statement.close();
        }
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (userCurrencyList);
  }
}




