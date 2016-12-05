package bitcamp.java89.ems.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems.dao.TeacherDao;
import bitcamp.java89.ems.util.DataSource;
import bitcamp.java89.ems.vo.Teacher;

public class TeacherMysqlDao implements TeacherDao {
  DataSource ds;
  

  private TeacherMysqlDao() {
    ds = DataSource.getInstance();
  }
  
  static TeacherMysqlDao instance;
  public static TeacherMysqlDao getInstance() {
    if (instance == null) {
      instance = new TeacherMysqlDao();
    }
    return instance;
  }


  public ArrayList<Teacher> getList() throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement("select * from ex_teachers");
        ResultSet rs = stmt.executeQuery(); ){

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher();
        teacher.setName(rs.getString("name"));
        teacher.setLectureName(rs.getString("lec"));
        teacher.setJobCareer(rs.getString("jobcr"));
        teacher.setLectureCareer(rs.getString("leccr"));
        teacher.setBook(rs.getString("book"));
        teacher.setSchool(rs.getString("schl"));
        teacher.setAppraisal(rs.getString("appr"));
        teacher.setWebsite(rs.getString("wbs"));
        teacher.setPrize(rs.getString("prz"));
        list.add(teacher);
      }
    } finally {
      ds.returnConnetion(con);
    }
    return list;
  }


  public ArrayList<Teacher> getListByName(String name) throws Exception {
    ArrayList<Teacher> list = new ArrayList<>();
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "select * from ex_teachers where name=?"); ){

      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Teacher teacher = new Teacher();
        teacher.setName(rs.getString("name"));
        teacher.setLectureName(rs.getString("lec"));
        teacher.setJobCareer(rs.getString("jobcr"));
        teacher.setLectureCareer(rs.getString("leccr"));
        teacher.setBook(rs.getString("book"));
        teacher.setSchool(rs.getString("schl"));
        teacher.setAppraisal(rs.getString("appr"));
        teacher.setWebsite(rs.getString("wbs"));
        teacher.setPrize(rs.getString("prz"));
        list.add(teacher);
      }
      rs.close();
    } finally {
      ds.returnConnetion(con);
    }
    return list;
  }


  public void insert(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into ex_teachers(name,lec,jobcr,leccr,book,schl,appr,wbs,prz) values(?,?,?,?,?,?,?,?,?)"); ){

      stmt.setString(1, teacher.getName());
      stmt.setString(2, teacher.getLectureName());
      stmt.setString(3, teacher.getJobCareer());
      stmt.setString(4, teacher.getLectureCareer());
      stmt.setString(5, teacher.getBook());
      stmt.setString(6, teacher.getSchool());
      stmt.setString(7, teacher.getAppraisal());
      stmt.setString(8, teacher.getWebsite());
      stmt.setString(9, teacher.getPrize());

      stmt.executeUpdate();

    } finally {
      ds.returnConnetion(con);
    }
  }


  public void delete(String name) throws Exception {
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "delete from ex_teachers where name=?"); ){

      stmt.setString(1, name);

      stmt.executeUpdate();

    } finally {
      ds.returnConnetion(con);
    }
  }



  public void update(Teacher teacher) throws Exception {
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "update ex_teachers set lec=?, jobcr=?, leccr=?, book=?, schl=?, appr=?, wbs=?, prz=? where name=?"); ){

      stmt.setString(1, teacher.getLectureName());
      stmt.setString(2, teacher.getJobCareer());
      stmt.setString(3, teacher.getLectureCareer());
      stmt.setString(4, teacher.getBook());
      stmt.setString(5, teacher.getSchool());
      stmt.setString(6, teacher.getAppraisal());
      stmt.setString(7, teacher.getWebsite());
      stmt.setString(8, teacher.getPrize());
      stmt.setString(9, teacher.getName());

      stmt.executeUpdate();

    } finally {
      ds.returnConnetion(con);
    }
  }


  public boolean existName(String name) throws Exception {
    Connection con = ds.getConnection();

    try (
        PreparedStatement stmt = con.prepareStatement(
            "select * from ex_teachers where name=?"); ){

      stmt.setString(1, name);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        rs.close();
        return true;
      } else {
        rs.close();
        return false;
      }

    } finally {
      ds.returnConnetion(con);
    }
  }

}