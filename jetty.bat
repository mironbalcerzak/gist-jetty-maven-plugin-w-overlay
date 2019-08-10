@cls
@echo ------------------------------------------------------------------------------
@echo Available maven profiles:
@echo runOverlay1, overlay1Defaults, overlay1WithExcludes, overlay1WithTargetPath
@echo runOverlay2, overlay2Defaults, overlay2WithExcludes, overlay2WithTargetPath
@echo ------------------------------------------------------------------------------
mvn clean deploy -DskipTests %*