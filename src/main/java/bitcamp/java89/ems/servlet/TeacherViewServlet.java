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


@WebServlet("/teacher/view")
public class TeacherViewServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    String name = request.getParameter("name");
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>연락처관리-상세정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>연락처 정보</h1>");
    out.println("<form action='update' method='post'>");
    
    
    
    try {
      TeacherMysqlDao teacherDao = TeacherMysqlDao.getInstance();
      
      Teacher teacher = teacherDao.getDetail(name);
      if (teacher == null) {
        throw new Exception("해당 이름의 강사 데이터가 없습니다.");
      }
      
      else {
        out.println("<table border='1'>");
        
        out.printf("<tr><th>이름</th><td>" 
            + "<input name='name' type='text' value='%s' readonly></td></tr>\n", teacher.getName());
        out.printf("<tr><th>강의명</th><td>"
            + "<input name='lectureName' type='text' value='%s'></td></tr>\n", teacher.getLectureName());
        out.printf("<tr><th>회사경력</th><td>"
            + "<input name='jobCareer' type='text' value='%s'></td></tr>\n", teacher.getJobCareer());
        out.printf("<tr><th>강의경력</th><td>"
            + "<input name='lectureCareer' type='text' value='%s'></td></tr>\n", teacher.getLectureCareer());
        out.printf("<tr><th>저서</th><td>" 
            + "<input name='book' type='text' value='%s'></td></tr>\n", teacher.getBook());
        out.printf("<tr><th>학교</th><td>"
            + "<input name='school' type='text' value='%s'></td></tr>\n", teacher.getSchool());
        out.printf("<tr><th>평가</th><td>"
            + "<input name='appraisal' type='text' value='%s'></td></tr>\n", teacher.getAppraisal());
        out.printf("<tr><th>홈페이지</th><td>"
            + "<input name='website' type='text' value='%s'></td></tr>\n", teacher.getWebsite());
        out.printf("<tr><th>수상내역</th><td>"
            + "<input name='prize' type='text' value='%s'></td></tr>\n", teacher.getPrize());
        
        out.println("</table>");
        out.println("<button type='submit'>변경</button>");
        out.printf(" <a href='delete?name=%s'>삭제</a>", teacher.getName());
        out.println(" <a href='list'>목록</a>"); 
        
      }
      
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
