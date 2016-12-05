package bitcamp.java89.ems.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java89.ems.dao.impl.ContactMysqlDao;
import bitcamp.java89.ems.vo.Contact;


//원래는 톰캣 서버가 실행할 수 있는 클래스가 되기 위해 반드시 Servlet 규격에 맞춰 제작해야 한다.
//그러나 Servlet 인터페이스의 메서드가 많아서 구현하기 번거로움 
//--> Servlet을 구현하는 AbstractServlet 추상클래스 생성. 이 추상클래스를 상속받음. --> service 메서드만 구현하면 됨.

@WebServlet("/contact/view")
public class ContactViewServlet extends AbstractServlet {
  ServletConfig config;

  @Override
  public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    try {
      ContactMysqlDao contactDao = ContactMysqlDao.getInstance();
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      String name = request.getParameter("name");
      ArrayList<Contact> list = contactDao.getListByName(name);
      for (Contact contact : list) {
        out.println("----------------");
        out.printf("이름: %s\n", contact.getName());
        out.printf("직위: %s\n", contact.getPosition());
        out.printf("전화: %s\n", contact.getTel());
        out.printf("이메일: %s\n", contact.getEmail());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}
