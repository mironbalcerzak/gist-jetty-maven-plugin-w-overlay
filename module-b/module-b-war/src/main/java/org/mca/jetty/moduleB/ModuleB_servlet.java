package org.mca.jetty.moduleB;

import org.mca.jetty.common.ClasspathAnalyzeHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModuleB_servlet extends HttpServlet {

    private final ModuleB_api module = new ModuleB_impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<html style=\"background-color:darkgray\">");
        ClasspathAnalyzeHelper.print(resp.getWriter());
        resp.getWriter().println("</html>");
        module.callMe();
    }
}
