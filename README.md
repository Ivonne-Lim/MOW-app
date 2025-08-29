# MOW-app
"Meals on Wheels" project for Spring Boot

## Spring Boot Project

The Spring Boot project has to be imported into Spring Tool Suite (STS) to install the Meals on Wheels (MOW) application.

The web.config file and its webapps subfolder can be found in the wwwroot folder added. However, the WAR file and the Azure App Service resource (if applicable) have to be created first to deploy the MOW application elsewhere with the WAR file (renamed as "ROOT.war") put in the webapps subfolder.

## MySQL Database

The following SQL scripts, to create the database, can be found in the mydb(mow-app) folder added. However, a connection from MySQL Workbench has to be created first to do so.

* Script excluding creating the administrator profile (filename prefixed with “import_”).
* Script including creating the administrator profile (filename prefixed with “export_”).

## Node JS Projects

The Express JS scripts and their public subfolder can be found in the file-upload-app folder added. However, the Node JS project has to be created first to install or deploy the file server elsewhere.

The React Native related files, their assets subfolder and their screens subfolder can be found in the mow-app folder added. However, the Node JS project has to be created first to install or deploy the mobile application elsewhere.

## JMeter Tests

The JMeter test scripts and their results files can be found in the jmeter-scripts folder added. However, the paths to the results files have to be set correctly first to run the tests elsewhere.

## SonarQube Tests

The batch file, for triggering SonarQube CI-based analysis, can be found in the project main folder. It uses Maven which wrapper (mvnw.cmd) had a defect being fixed.

### Notes

The following files have each of their confidential data replaced with the word “placeholder” therefore the Spring Boot project is not fully complete. The data can be obtained from the student separately for restoration.

1. application.properties (MySQL datasource password) in src\main\resources
2. application.properties.bak (MySQL datasource password) in src\main\resources
3. application.yml (Meta app / client secret) in src\main\resources
4. application.yml.bak (Meta app / client secret) in src\main\resources
5. api_key.txt (Google Geocoding API key) in src\main\resources\config
6. password.txt (Gmail app password) in src\main\resources\config
7. MealService.java (Google Geocoding API key) in src\main\java\org\merrymeal\mow\service
8. ProfileService.java (JWT secret key and Gmail app password) in src\main\java\org\merrymeal\mow\service
9. sonar.bat (SonarQube token) in project main folder

The entire target folder, including class and WAR files, is also removed since it also contains the confidential data which can be extracted. Therefore, if needed, the Spring Boot source code has to be re-compiled for running then the Spring Boot project re-packaged for deployment.

The constants for pagination size are currently set to 2 for testing and demonstration hence may be corrected (e.g. set to 15) in the following java files followed by the source code re-compiled then re-packaged for production deployment. There is no reading of text files in the config folders for pagination size because the size is hardly to be changed.

1. RegistrationController.java in src\main\java\org\merrymeal\mow\controller
2. EvaluationController.java in src\main\java\org\merrymeal\mow\controller
3. MenusController.java in src\main\java\org\merrymeal\mow\controller
4. MealsController.java in src\main\java\org\merrymeal\mow\controller
5. PickupsController.java in src\main\java\org\merrymeal\mow\controller
6. FeedbackController.java in src\main\java\org\merrymeal\mow\controller
