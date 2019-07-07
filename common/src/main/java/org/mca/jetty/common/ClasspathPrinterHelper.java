package org.mca.jetty.common;

import java.io.PrintWriter;
import java.net.URL;

public final class ClasspathPrinterHelper {

    public static void print(PrintWriter out) {
        out.println("<h5>JDK / maven repo files</h5>");
        out.println("<pre>");
        out.println(format("java.lang.Exception"));
        out.println(format("javax.servlet.http.HttpServlet"));
        out.println("</pre>");
        out.println("<h5>Overlay module</h5>");
        out.println("<pre>");
        out.println(format("org.mca.jetty.overlay.Overlay_servlet"));
        out.println(format("org.mca.jetty.overlay.OverlayNested_servlet"));
        out.println("</pre>");
        out.println("<h5>Common</h5>");
        out.println("<pre>");
        out.println(format("org.mca.jetty.common.Common"));
        out.println("</pre>");
        out.println("<h5>Module A</h5>");
        out.println("<pre>");
        out.println(format("org.mca.jetty.moduleA.ModuleA_api"));
        out.println(format("org.mca.jetty.moduleA.ModuleA_impl"));
        out.println(format("org.mca.jetty.moduleA.ModuleA_servlet"));
        out.println("</pre>");
        out.println("<h5>Module B</h5>");
        out.println("<pre>");
        out.println(format("org.mca.jetty.moduleB.ModuleB_api"));
        out.println(format("org.mca.jetty.moduleB.ModuleB_impl"));
        out.println(format("org.mca.jetty.moduleB.ModuleB_servlet"));
        out.println("</pre>");
        out.println("<h5>Module C</h5>");
        out.println("<pre>");
        out.println(format("org.mca.jetty.moduleC.ModuleC_api"));
        out.println(format("org.mca.jetty.moduleC.ModuleC_impl"));
        out.println(format("org.mca.jetty.moduleC.ModuleC_servlet"));
        out.println("</pre>");
    }

    private static String format(String className) {
        String filePath = "(none)";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(className.replaceAll("\\.", "\\/") + ".class");
        if (resource != null) {
            filePath = resource.getFile();
        }
        return className + " -> " + filePath;
    }

}
