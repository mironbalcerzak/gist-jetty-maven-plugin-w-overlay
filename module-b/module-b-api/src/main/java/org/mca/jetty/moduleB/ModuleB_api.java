package org.mca.jetty.moduleB;

public interface ModuleB_api {

    default void callMe() {
        System.out.println("ModuleB_api - called");
    }
}
