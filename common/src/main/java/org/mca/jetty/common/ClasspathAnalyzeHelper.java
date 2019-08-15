package org.mca.jetty.common;

import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ClasspathAnalyzeHelper {

    public static Map<String, String> analyze() {
        Map<String, String> retVal = new LinkedHashMap<>();
        retVal.computeIfAbsent("java.lang.Exception", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("javax.servlet.http.HttpServlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.common.Common", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleA.ModuleA_api", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleA.ModuleA_impl", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleA.ModuleA_servlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("module-a-api.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-a-impl.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-a-war.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("org.mca.jetty.moduleB.ModuleB_api", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleB.ModuleB_impl", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleB.ModuleB_servlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("module-b-api.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-b-impl.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-b-war.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("org.mca.jetty.moduleC.ModuleC_api", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleC.ModuleC_impl", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.moduleC.ModuleC_servlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("module-c-api.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-c-impl.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("module-c-war.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("org.mca.jetty.overlay.Overlay1_servlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("org.mca.jetty.overlay.Overlay2_servlet", ClasspathAnalyzeHelper::locateClass);
        retVal.computeIfAbsent("overlay-me.resource", ClasspathAnalyzeHelper::locateResource);
        retVal.computeIfAbsent("static-1.resource", ClasspathAnalyzeHelper::locateResource);
        return retVal;
    }

    public static void print(PrintWriter out) {
        out.println("<pre>");
        Collection<String> breakList = new HashSet<>();
        breakList.add("org.mca.jetty.common.Common");
        breakList.add("org.mca.jetty.moduleA.ModuleA_api");
        breakList.add("org.mca.jetty.moduleB.ModuleB_api");
        breakList.add("org.mca.jetty.moduleC.ModuleC_api");
        breakList.add("org.mca.jetty.overlay.Overlay1_servlet");
        for (Map.Entry<String, String> entry : analyze().entrySet()) {
            String resource = entry.getKey();
            String location = entry.getValue();
            if (breakList.contains(resource)) {
                out.println("</ br>");
            }
            String color = resource.endsWith(".resource") ? "darkred" : "black";
            String format = String.format("<span style=\"color:%s\"><b>%38s</b> | %s</span>",
                    color, resource, location);
            out.println(format);
        }
        out.println("</pre>");
    }

    private static String locateResource(String resource) {
        String filePath = "(not found)";
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (url != null) {
            filePath = url.toString();
        }
        return filePath;
    }

    private static String locateClass(String className) {
        String filePath = "(not found)";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(className.replaceAll("\\.", "\\/") + ".class");
        if (resource != null) {
            filePath = resource.toString();
        }
        return filePath;
    }

}
