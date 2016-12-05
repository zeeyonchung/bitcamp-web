package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;


@WebServlet("/teacher/delete")
public class TeacherDeleteServlet extends AbstractServlet {
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();

      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existName(request.getParameter("name"))) {
        out.println("입력하신 성함의 강사님 정보를 찾지 못했습니다.");
        return;
      }
      
      
      teacherDao.delete(request.getParameter("name"));
      out.println("해당 데이터를 삭제했습니다.");
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}