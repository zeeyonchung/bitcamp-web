package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;


@WebServlet("/contact/update")
public class ContactUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    
    Contact contact = new Contact();
    contact.setEmail(request.getParameter("email"));
    contact.setName(request.getParameter("name"));
    contact.setPosition(request.getParameter("position"));
    contact.setTel(request.getParameter("tel"));
    
    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>연락처관리-등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>등록 결과</h1>");
    
    
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      
      if (!contactDao.existEmail(contact.getEmail())) {
        throw new Exception ("해당 이메일의 연락처가 없습니다.");
      }

      contactDao.update(contact);
      out.println("<p>변경 하였습니다.</p>");
      
      
    } catch (Exception e) {
      out.println(e.getMessage());
    }
    
    
    out.println("</body>");
    out.println("</html>");
  }

}
