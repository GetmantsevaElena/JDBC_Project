package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class CheckCurrency {

  public static ArrayList<String> checkCurrency() {
    Users users = new Users();
    ArrayList<String> userCurrencyList = new ArrayList<>();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT currency FROM Accounts WHERE userId = ?");
        statement.setInt(1, users.getUserId());
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        userCurrencyList = new ArrayList<>(columnCount);
        while (resultSet.next()) {
          int i = 1;
          while (i <= columnCount) {
            userCurrencyList.add(resultSet.getString(i++));
          }
        }
        resultSet.close();
        statement.close();
      } finally {
        connection.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (userCurrencyList);
  }
}





