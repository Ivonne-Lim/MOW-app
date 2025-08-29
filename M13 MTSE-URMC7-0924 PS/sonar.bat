@echo Running Maven passing Spring Boot coding to SonarQube for CI-based analysis
@echo ---------------------------------------------------------------------------
set SONAR_TOKEN=placeholder
./mvnw verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=Ivonne-Lim_MOW -Dmaven.test.skip=true
