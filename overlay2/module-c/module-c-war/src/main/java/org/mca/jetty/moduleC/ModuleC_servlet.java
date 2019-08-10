package org.mca.jetty.moduleC;

import org.mca.jetty.common.ClasspathAnalyzeHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModuleC_servlet extends HttpServlet {

    private final ModuleC_api module = new ModuleC_impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<html style=\"background-color:darkgray\">");
        ClasspathAnalyzeHelper.print(resp.getWriter());
        resp.getWriter().println("</html>");
        module.callMe();
    }
}
