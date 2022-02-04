package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckUserId {

  public static ArrayList<String> checkUserId() {

    ArrayList<String> userIdList = new ArrayList<>();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      Connection connection = DriverManager.getConnection(Constant.DATABASE_URL);
      try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT userId FROM USERS");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        userIdList = new ArrayList<>(columnCount);
        while (resultSet.next()) {
          int i = 1;
          while (i <= columnCount) {
            userIdList.add(resultSet.getString(i++));
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
    return (userIdList);
  }
}




