//목적: javax.servlet.Servlet 인터페이스의 4 개 메서드를 미리 구현하여 서브 클래스에게 상속해주는 용도
package bitcamp.java89.ems.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AbstractServlet implements Servlet {
  ServletConfig config;
  

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.config;
  }

  @Override
  public String getServletInfo() {
    return this.getClass().getName();
  }

  @Override
  public void destroy() {
    
  }

}
