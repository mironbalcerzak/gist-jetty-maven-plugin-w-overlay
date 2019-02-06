package org.mca.jetty.moduleB;

import org.mca.jetty.common.ClasspathPrinterHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModuleB_servlet extends HttpServlet {

    private final ModuleB_api moduleB = new ModuleB_impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>ModuleB: Hello World</h3>");
        ClasspathPrinterHelper.print(out);
    }
}
