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


@WebServlet("/contact/delete")
public class ContactDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  ServletConfig config;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();

      if (!contactDao.existEmail(request.getParameter("email"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      contactDao.delete(request.getParameter("email"));
      out.println("해당 데이터 삭제 완료하였습니다.");
      
    
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
