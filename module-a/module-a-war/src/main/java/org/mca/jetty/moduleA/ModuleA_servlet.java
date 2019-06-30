package org.mca.jetty.moduleA;

import org.mca.jetty.common.ClasspathPrinterHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModuleA_servlet extends HttpServlet {

    private final ModuleA_api moduleA = new ModuleA_impl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("ModuleA_servlet - called");
        moduleA.callMe();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h3>ModuleA: Hello World</h3>");
        ClasspathPrinterHelper.print(out);
    }
}
