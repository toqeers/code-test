HOW TO RUN THE APPLICATION
Unzip the source code, it should create code-test folder
move to new folder i.e. cd code-test
code is already built, but if you want you can build it with command "./gradlew clean build" otherwise skip this step
move to build/libs folder
give following command "java -jar code-test-1.0-SNAPSHOT.jar"
now application should start, it will create "logs" folder in the current location

How to make request
we need to POST json request to this service, you can either use UI tool e.g. Postman or use curl command. Assuming that you have request json in a file "risk-clalc-request.json" in current location
curl -i -X POST http://localhost:3000/api/v1.0/risk -H "Content-Type: application/json" -H "Accept: application/json"  --data "@risk-clalc-request.json"

What is Done
An authenticated REST service that calls authenticated service to get risk measures. The access token and risk measures are cached to avoid unnecessary network calls.
Unit tests for happy paths
Functional Test (or Integration Test) for happy path using WireMock

What is not Done
Support for multiple formats
Custom error codes and exception handling
any throttling to avoid overloading

