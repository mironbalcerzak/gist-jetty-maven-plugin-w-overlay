# project description

Possible bug spotted in jetty-maven-plugin (v.9.4.14.v20181114).

_jetty-maven-plugin_ is resolving dependencies without transitions (even if those are present). 

# project structure
project contains 4 major modules:

```
1. common
2. module-a
    2.1. module-a-api
    2.2. module-a-impl
    2.3. module-a-war (jetty)
3. module-b
    3.1. module-b-api 
    3.2. module-b-impl
    3.3. module-b-war (jetty)
4. overlay (jetty)
```

# maven configuration
For ease of testing following profiles are present:
````
- moduleA - deploys moduleA to jetty server (no overlay)
- moduleB - deploys moduleB to jetty server (no overlay)
- overlay - deploys overlay to jetty server as overlay

- allDeps - explicitly includes all dependencies (compile scope) used by module
          - profile defined in each of "war-modules" - which is, module-a-war, module-b-war and overlay
````
"allDeps" profile is more of a hack - and cannot be considered as a workaround (especially in war overlay scenario)

...and maven-deploy-plugin is disabled (skip=true)

# how to reproduce
1. checkout git project (master branch)
2. navigate to "root" of the project.
3. mvn deploy +MAVEN_PROFILE(s)
4. check result in localhost:8080

# results (what is displayed when accessing localhost:8000)


#### mvn deploy -PmoduleA
```
ModuleA: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> (none)
Module A
org.mca.jetty.moduleA.ModuleA_api -> file:/C:/jettyProject/module-a/module-a-api/target/module-a-api-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleA/ModuleA_api.class
org.mca.jetty.moduleA.ModuleA_impl -> /C:/jettyProject/module-a/module-a-impl/target/classes/org/mca/jetty/moduleA/ModuleA_impl.class
org.mca.jetty.moduleA.ModuleA_servlet -> /C:/jettyProject/module-a/module-a-war/target/classes/org/mca/jetty/moduleA/ModuleA_servlet.class
Module B
org.mca.jetty.moduleB.ModuleB_api -> (none)
org.mca.jetty.moduleB.ModuleB_impl -> (none)
org.mca.jetty.moduleB.ModuleB_servlet -> (none)
```
Fail - org.mca.jetty.moduleA.ModuleA_api is loaded from JAR file

#### mvn deploy -PmoduleA,allDeps
```
ModuleA: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> (none)
Module A
org.mca.jetty.moduleA.ModuleA_api -> /C:/jettyProject/module-a/module-a-api/target/classes/org/mca/jetty/moduleA/ModuleA_api.class
org.mca.jetty.moduleA.ModuleA_impl -> /C:/jettyProject/module-a/module-a-impl/target/classes/org/mca/jetty/moduleA/ModuleA_impl.class
org.mca.jetty.moduleA.ModuleA_servlet -> /C:/jettyProject/module-a/module-a-war/target/classes/org/mca/jetty/moduleA/ModuleA_servlet.class
Module B
org.mca.jetty.moduleB.ModuleB_api -> (none)
org.mca.jetty.moduleB.ModuleB_impl -> (none)
org.mca.jetty.moduleB.ModuleB_servlet -> (none)
```
All is good (but only if all dependencies are explicitly set)

#### mvn deploy -PmoduleB
```
ModuleB: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> (none)
Module A
org.mca.jetty.moduleA.ModuleA_api -> (none)
org.mca.jetty.moduleA.ModuleA_impl -> (none)
org.mca.jetty.moduleA.ModuleA_servlet -> (none)
Module B
org.mca.jetty.moduleB.ModuleB_api -> file:/C:/jettyProject/module-b/module-b-api/target/module-b-api-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleB/ModuleB_api.class
org.mca.jetty.moduleB.ModuleB_impl -> /C:/jettyProject/module-b/module-b-impl/target/classes/org/mca/jetty/moduleB/ModuleB_impl.class
org.mca.jetty.moduleB.ModuleB_servlet -> /C:/jettyProject/module-b/module-b-war/target/classes/org/mca/jetty/moduleB/ModuleB_servlet.class
```
Fail - org.mca.jetty.moduleB.ModuleB_api is loaded from JAR file 

#### mvn deploy -PmoduleB,allDeps
```
ModuleB: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> (none)
Module A
org.mca.jetty.moduleA.ModuleA_api -> (none)
org.mca.jetty.moduleA.ModuleA_impl -> (none)
org.mca.jetty.moduleA.ModuleA_servlet -> (none)
Module B
org.mca.jetty.moduleB.ModuleB_api -> /C:/jettyProject/module-b/module-b-api/target/classes/org/mca/jetty/moduleB/ModuleB_api.class
org.mca.jetty.moduleB.ModuleB_impl -> /C:/jettyProject/module-b/module-b-impl/target/classes/org/mca/jetty/moduleB/ModuleB_impl.class
org.mca.jetty.moduleB.ModuleB_servlet -> /C:/jettyProject/module-b/module-b-war/target/classes/org/mca/jetty/moduleB/ModuleB_servlet.class
```
All is good (but only if all dependencies are explicitly set)

#### mvn deploy -Poverlay

```
Overlay: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> /C:/jettyProject/overlay/target/classes/org/mca/jetty/overlay/Overlay_servlet.class
Module A
org.mca.jetty.moduleA.ModuleA_api -> file:/C:/jettyProject/overlay/target/jetty_overlays/module-a-war-1_0-SNAPSHOT_war/WEB-INF/lib/module-a-api-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleA/ModuleA_api.class
org.mca.jetty.moduleA.ModuleA_impl -> file:/C:/jettyProject/overlay/target/jetty_overlays/module-a-war-1_0-SNAPSHOT_war/WEB-INF/lib/module-a-impl-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleA/ModuleA_impl.class
org.mca.jetty.moduleA.ModuleA_servlet -> /C:/jettyProject/overlay/target/jetty_overlays/module-a-war-1_0-SNAPSHOT_war/WEB-INF/classes/org/mca/jetty/moduleA/ModuleA_servlet.class
Module B
org.mca.jetty.moduleB.ModuleB_api -> file:/C:/jettyProject/overlay/target/jetty_overlays/module-b-war-1_0-SNAPSHOT_war/WEB-INF/lib/module-b-api-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleB/ModuleB_api.class
org.mca.jetty.moduleB.ModuleB_impl -> file:/C:/jettyProject/overlay/target/jetty_overlays/module-b-war-1_0-SNAPSHOT_war/WEB-INF/lib/module-b-impl-1.0-SNAPSHOT.jar!/org/mca/jetty/moduleB/ModuleB_impl.class
org.mca.jetty.moduleB.ModuleB_servlet -> /C:/jettyProject/overlay/target/jetty_overlays/module-b-war-1_0-SNAPSHOT_war/WEB-INF/classes/org/mca/jetty/moduleB/ModuleB_servlet.class
```
Fail - only org.mca.jetty.overlay.Overlay_servlet is loaded correctly, rest is loaded from bad locations.

#### mvn deploy -Poverlay,allDeps
```
Overlay: Hello World
JDK / maven repo files
java.lang.Exception -> file:/C:/Program Files/Java/jdk1.8.0_XXX/jre/lib/rt.jar!/java/lang/Exception.class
javax.servlet.http.HttpServlet -> file:/C:/Users/user/.m2/repository/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar!/javax/servlet/http/HttpServlet.class
Overlaid war file
org.mca.jetty.overlay.Overlay_servlet -> /C:/jettyProject/overlay/target/classes/org/mca/jetty/overlay/Overlay_servlet.class
Module A
org.mca.jetty.moduleA.ModuleA_api -> /C:/jettyProject/module-a/module-a-api/target/classes/org/mca/jetty/moduleA/ModuleA_api.class
org.mca.jetty.moduleA.ModuleA_impl -> /C:/jettyProject/module-a/module-a-impl/target/classes/org/mca/jetty/moduleA/ModuleA_impl.class
org.mca.jetty.moduleA.ModuleA_servlet -> /C:/jettyProject/overlay/target/jetty_overlays/module-a-war-1_0-SNAPSHOT_war/WEB-INF/classes/org/mca/jetty/moduleA/ModuleA_servlet.class
Module B
org.mca.jetty.moduleB.ModuleB_api -> /C:/jettyProject/module-b/module-b-api/target/classes/org/mca/jetty/moduleB/ModuleB_api.class
org.mca.jetty.moduleB.ModuleB_impl -> /C:/jettyProject/module-b/module-b-impl/target/classes/org/mca/jetty/moduleB/ModuleB_impl.class
org.mca.jetty.moduleB.ModuleB_servlet -> /C:/jettyProject/overlay/target/jetty_overlays/module-b-war-1_0-SNAPSHOT_war/WEB-INF/classes/org/mca/jetty/moduleB/ModuleB_servlet.class
```
Fail - Api / Impl modules are resolved correctly, however content of WAR (ModuleA and ModuleB) are loaded from jetty_overlays.
