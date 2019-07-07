package org.mca.jetty.moduleC;

import org.mca.jetty.common.ClasspathPrinterHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModuleC_servlet extends HttpServlet {

    private final ModuleC_api moduleB = new ModuleC_impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("ModuleC_servlet - called");
        moduleB.callMe();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>ModuleC: Hello World</h3>");
        ClasspathPrinterHelper.print(out);
    }
}
