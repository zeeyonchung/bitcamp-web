package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;


@WebServlet("/teacher/view")
public class TeacherViewServlet extends AbstractServlet {
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      ArrayList<Teacher> list = teacherDao.getList();

      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      for (Teacher teacher : list) {
        if (teacher.getName().equals(request.getParameter("name"))) {
          out.printf("이름: %s\n", teacher.getName());
          out.printf("담당강의: %s\n", teacher.getLectureName());
          out.printf("회사경력: %s\n", teacher.getJobCareer());
          out.printf("강의경력: %s\n", teacher.getLectureCareer());
          out.printf("저서: %s\n", teacher.getBook());
          out.printf("학력: %s\n", teacher.getSchool());
          out.printf("강의평가: %s\n", teacher.getAppraisal());
          out.printf("웹사이트: %s\n", teacher.getWebsite());
          out.printf("수상내역: %s\n", teacher.getPrize());
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
