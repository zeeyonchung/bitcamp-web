package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;


@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends AbstractServlet {
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      if (!teacherDao.existName(request.getParameter("name"))) {
        out.println("입력하신 성함의 강사님 정보을 찾지 못했습니다.");
        return;
      }
  
      Teacher teacher = new Teacher();
      teacher.setName(request.getParameter("name"));
      teacher.setLectureName(request.getParameter("lectureName"));
      teacher.setJobCareer(request.getParameter("jobCareer"));
      teacher.setLectureCareer(request.getParameter("lectureCareer"));
      teacher.setBook(request.getParameter("book"));
      teacher.setSchool(request.getParameter("school"));
      teacher.setAppraisal(request.getParameter("appraisal"));
      teacher.setWebsite(request.getParameter("website"));
      teacher.setPrize(request.getParameter("prize"));
  
  
      teacherDao.update(teacher);
      out.println("변경하였습니다.");
      
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}