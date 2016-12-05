package bitcamp.java89.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DataSource {
  ArrayList<Connection> conPool = new ArrayList<>();
  
  
  private DataSource() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  static DataSource instance;
  
  public static DataSource getInstance() {
    if (instance == null) {
      instance = new DataSource();
    }
    return instance;
  }
  
  
  public Connection getConnection() throws Exception {
      if (conPool.size() == 0) {
        System.out.println("DB 커넥션 생성");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", "java89", "1111");
      } else {
        return conPool.remove(0);
      }
    }
  
  public void returnConnetion(Connection con) {
    try {
      if (!con.isClosed() && con.isValid(5)) {
        conPool.add(con);
      }
    } catch (Exception e) {}
  }
}
