# Siaam Web App
Single Identity and Access Management

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
emS,s%yhf3pa
## Prerequisites
-   Knowledge of Git/Java/J2EE/Maven/Spring/oAuth required.

## Installation
-   Install Git Bash
-   Install JDK 1.8
-   Install maven 3.3+ 
-   Ensure jdk and maven are added to the bash path (i.e. in .bashrc or .bash_profile), confirm by running javac and mvn on bash
-   Install mysql 5.7+ 
a) Create a user siaamadmin user in mysql with your choice of password (update the password in src/main/resources/config/siaam.properties)
b) Run script1_siaamdb, script2_oAuthDb, script3_oAuth_data in mysql to create db, tables, insert config rows etc

## Build
-   start Git bash
-   goto siaamweb foler
-   make sure the folder /tmp/logs exist and have write perms for log files. Alternatively update the log file path in sAuth/src/main/resources/logback.xml
-   Rename siaam.properties to siaam.properties.local and replace <Add> with the actual value based on your local env 
-   Prepare build using command 
    ```
    $ ./build.sh
    ```  
Few important things to note:- 
- build.sh will compile all siaam projects. If you want to build a specific project, goto that subdir and run 
    ```
    $ ./mvn clean install -DskipTests
    ```
- If you want to setup project for eclipse IDE, run following after step a)
    ```
    $ ./mvn clean eclipse:clean
    ```
    ```
    $ ./mvn clean eclipse:eclipse
    ``` 
## Deployment
-   start Git bash
-   goto siaamweb foler
-   Run the command below to start tomcat as a process, deploy sauth and start) 
    ```
    $ ./run.sh
    ``` 
- Open browser and access the UI client http://localhost:8080/sAuth/resources/index.html#/login

## REST REST Auth Services Testing
-	For REST Auth Services testing, you can trace request and response by testing using Angular JS UI at 	 
        http://localhost:8080/sAuth/resources/index.html#/login

## OAUTH testing
-	Redirect to client with AuthCode (note that you need to login first before hitting this url in browser)  
	http://localhost:8080/sAuth/oauth/authorize?client_id=my-trusted-client-with-secret&redirect_uri=https://www.google.com&response_type=code
	
-	Fetch Access token with authorization grant (Hint: Fetch AuthCode first from URL pasted above for /oauth/authorize)
	http://localhost:8080/sAuth/oauth/token?grant_type=authorization_code&redirect_uri=https://www.google.com&code=KxI4mI
	Authorization: Basic bXktdHJ1c3RlZC1jbGllbnQtd2l0aC1zZWNyZXQ6c29tZXNlY3JldA==
	
	Sample Response 
	```
	{
		"access_token": "d38802e7-ddb6-45c0-adaf-4c51a7f1222b"
	 	"token_type": "bearer"
		"refresh_token": "934c6117-2c0e-4543-9b00-6f0ab595f917"
		"expires_in": 59
		"scope": "read trust write"
		"client_id": "my-trusted-client-with-secret"
	}
	```
-	Fetch Access token with password grant (you dont need to login or authenticate before this step) 
	http://localhost:8080/sAuth/oauth/token?grant_type=password&username=pruthik_21&password=passc0de
	Authorization: Basic bXktdHJ1c3RlZC1jbGllbnQtd2l0aC1zZWNyZXQ6c29tZXNlY3JldA==
	
	Sample Response
	``` 
	{
		"access_token": "d38802e7-ddb6-45c0-adaf-4c51a7f1222b"
		"token_type": "bearer"
		"refresh_token": "934c6117-2c0e-4543-9b00-6f0ab595f917"
		"expires_in": 59
		"scope": "read trust write"
		"client_id": "my-trusted-client-with-secret"
	}
	```
-	Use access token fetched previously to hit a protected url 
	http://localhost:8080/sAuth/protected/greeting?name=guest
	Authorization: Bearer d38802e7-ddb6-45c0-adaf-4c51a7f1222b
	
	Sample Response
	```
	{
		"id": 1 
		"content": 
		"Hello, guest!"
	}
	```
## Versioning

## Authors

## License

## Acknowledgments
