package org.epam.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckUserId extends Transactions{

  public static void checkUserId() {

    CheckUserId m = new CheckUserId();
    try {
      Class.forName(Constant.JDBC_DRIVER);
      String url = Constant.DATABASE_URL;
      String login = "user";
      String password = "pass";
      Connection con = DriverManager.getConnection(url, login, password);
      try {
        m.selectUserId(con);
        m.selectUserId(con);
      } finally {
        con.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public ArrayList<String> selectUserId(Connection con) throws SQLException {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT userId FROM USERS");
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    ArrayList<String> userIdList = new ArrayList<>(columnCount);
    while (rs.next()) {
      int i = 1;
      while (i <= columnCount) {
        userIdList.add(rs.getString(i++));
      }
    }
    rs.close();
    stmt.close();
    return(userIdList);
  }

}
