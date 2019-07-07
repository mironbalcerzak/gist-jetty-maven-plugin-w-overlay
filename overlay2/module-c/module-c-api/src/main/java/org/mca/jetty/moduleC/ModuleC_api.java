package org.mca.jetty.moduleC;

public interface ModuleC_api {

    default void callMe() {
        System.out.println("ModuleC_api - called");
    }
}
