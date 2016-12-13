package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.TeacherMysqlDao;
import bitcamp.java89.ems.vo.Teacher;


@WebServlet("/teacher/update")
public class TeacherUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
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


    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강사관리-수정</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>등록 결과</h1>");

    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      
      if (!teacherDao.existName(teacher.getName())) {
        throw new Exception("해당 이름의 강사 데이터가 없습니다.");
      }
  
      teacherDao.update(teacher);
      out.println("<p>변경하였습니다.</p>");
      
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    

    out.println("</body>");
    out.println("</html>");
  }

}
