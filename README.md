# reported issue

https://github.com/eclipse/jetty.project/issues/3332 (resolved)
https://github.com/eclipse/jetty.project/issues/3725

# project description

Bug spotted in jetty-maven-plugin (v.9.4.14.v20181114).

_jetty-maven-plugin_ is resolving dependencies without transitions (even if those are present). 

# project structure
project contains 5 major modules:

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
4. overlay1-war (jetty)
5. overlay2 (pom)
    5.1. module-c
        5.1.1 module-c-api
        5.1.2 module-c-impl
        5.1.3 module-c-war
    5.2 overlay2-war (jetty)
```

# maven configuration
For ease of testing following maven profiles are present:
````
- moduleA - deploys moduleA to jetty server (no overlay)
- moduleB - deploys moduleB to jetty server (no overlay)
- moduleC - deploys moduleC to jetty server (no overlay)
- overlay1 - deploys overlay to jetty server as overlay
- overlay2 - deploys overlay to jetty server as overlay
````

...and maven-deploy-plugin is disabled (skip=true)

# how to reproduce
1. checkout git project (master branch)
2. navigate to "root" of the project.
3. mvn deploy +MAVEN_PROFILE(s)
4. check result in localhost:8080