package org.mca.jetty.common;

import java.io.PrintWriter;
import java.net.URL;

public final class ClasspathPrinterHelper {

    public static void print(PrintWriter out) {
        out.println("<h5>JDK / maven repo files</h5>");
        out.println("<pre>");
        displayClass("java.lang.Exception", out);
        displayClass("javax.servlet.http.HttpServlet", out);
        out.println("</pre>");

        out.println("<h5>Overlay module</h5>");
        out.println("<pre>");
        displayClass("org.mca.jetty.overlay.Overlay_servlet", out);
        displayClass("org.mca.jetty.overlay.OverlayNested_servlet", out);
        out.println("</pre>");

        out.println("<h5>Common</h5>");
        out.println("<pre>");
        displayClass("org.mca.jetty.common.Common", out);
        out.println("</pre>");

        out.println("<h5>Module A</h5>");
        out.println("<pre>");
        displayClass("org.mca.jetty.moduleA.ModuleA_api", out);
        displayClass("org.mca.jetty.moduleA.ModuleA_impl", out);
        displayClass("org.mca.jetty.moduleA.ModuleA_servlet", out);
        displayResource("module-a-api.txt", out);
        displayResource("module-a-impl.txt", out);
        displayResource("module-a-war.txt", out);
        displayResource("module-a-root.txt", out);

        out.println("</pre>");
        out.println("<h5>Module B</h5>");
        out.println("<pre>");
        displayClass("org.mca.jetty.moduleB.ModuleB_api", out);
        displayClass("org.mca.jetty.moduleB.ModuleB_impl", out);
        displayClass("org.mca.jetty.moduleB.ModuleB_servlet", out);
        displayResource("module-b-api.txt", out);
        displayResource("module-b-impl.txt", out);
        displayResource("module-b-war.txt", out);
        displayResource("module-b-root.txt", out);
        out.println("</pre>");

        out.println("<h5>Module C</h5>");
        out.println("<pre>");
        displayClass("org.mca.jetty.moduleC.ModuleC_api", out);
        displayClass("org.mca.jetty.moduleC.ModuleC_impl", out);
        displayClass("org.mca.jetty.moduleC.ModuleC_servlet", out);
        displayResource("module-c-api.txt", out);
        displayResource("module-c-impl.txt", out);
        displayResource("module-c-war.txt", out);
        displayResource("module-c-root.txt", out);
        out.println("</pre>");
    }

    private static void displayResource(String resource, PrintWriter out) {
        String filePath = "(not available)";
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (url != null) {
            filePath = url.getFile();
        }
        out.println(String.format("<span style=\"color:grey\">[resource]</span> ${webRoot}\\%s\t->\t%s", resource, filePath));
    }

    private static void displayClass(String className, PrintWriter writer) {
        String filePath = "(not available)";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(className.replaceAll("\\.", "\\/") + ".class");
        if (resource != null) {
            filePath = resource.getFile();
        }
        StringBuilder sb = new StringBuilder("<span style=\"color:grey\">[class]</span> ");
        String[] split = className.split("\\.");
        for (int i = 0; i < split.length; i++) {
            if (i < split.length - 2) {
                sb.append(split[i].charAt(0)).append(".");
            }
        }
        sb.append(split[split.length - 2]).append(".").append(split[split.length - 1]);
        writer.println(sb.toString() + "\t->\t" + filePath);
    }

}
