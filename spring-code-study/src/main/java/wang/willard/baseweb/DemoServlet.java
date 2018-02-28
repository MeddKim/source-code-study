package wang.willard.baseweb;

/**
 *  使用注解代替web.xml
 *  在web.xml中，我们经常需要配置servlet、filter、initParam参数（包括servlet和filter的参数）、listener
 *  这些我们可以通过以下注解来配置
 *  @WebServlet
 *  @WebFilter
 *  @WebInitParam
 *  @WebListener
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = {"/base/demo","/base/test"},
        initParams = {
                @WebInitParam(name="site",value = "wang.willard"),
                @WebInitParam(name="role",value = "admin")
        },
        loadOnStartup = 1
)
public class DemoServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Init Param Servlet Example</h2>");
        ServletConfig config= getServletConfig();
        String pValue= config.getInitParameter("Site :");
        out.println("Param Value : "+pValue);
        String pValue1= config.getInitParameter("Rose");
        out.println("<br>Param Value : "+pValue1);
        out.close();
    }
}
