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
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      
      if (!contactDao.existEmail(request.getParameter("email"))) {
        out.println("이메일을 찾지 못했습니다.");
        return;
      }

      Contact contact = new Contact();
      contact.setEmail(request.getParameter("email"));
      contact.setName(request.getParameter("name"));
      contact.setPosition(request.getParameter("position"));
      contact.setTel(request.getParameter("tel"));


      contactDao.update(contact);
      out.println("변경 하였습니다.");
      
      
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
