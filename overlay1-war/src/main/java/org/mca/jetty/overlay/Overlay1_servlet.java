package org.mca.jetty.overlay;

import org.mca.jetty.common.ClasspathAnalyzeHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Overlay1_servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<html style=\"background-color:darkgray\">");
        ClasspathAnalyzeHelper.print(resp.getWriter());
        resp.getWriter().println("</html>");
    }
}
