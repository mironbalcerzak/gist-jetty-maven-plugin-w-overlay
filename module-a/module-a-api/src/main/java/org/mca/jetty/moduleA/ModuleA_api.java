package org.mca.jetty.moduleA;

public interface ModuleA_api {

    default void callMe() {
        System.out.println("ModuleA_api - called");
    }
}
