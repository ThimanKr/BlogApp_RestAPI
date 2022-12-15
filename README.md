# Sample REST API for simple Blog Application
This is a sample Java / Maven / Spring Boot (version 3.0.0) application with REST APIs for simple Blog Application. This is implemented for Thortful API Challenge.

## How To Run
* Clone the repository using `git clone`
* Make sure you are using Java 11 and Maven 3.x
* Checkout to dev branch using `git checkout dev`
* Make sure you pull all the updates in dev branch using `git pull origin dev`
* You can build the project and run it using IDE or command line
* For IDE, execute the `main` method in the `SpringBootBlogRestApiApplication` class
* For command line run `mvn clean package` then `mvn spring-boot:run`
* To run test cases, you can simply go to test classes and run test methods using IDE or run `mvn clean test` command in command line

## About the Blog Rest API
The Blog REST API is a RESTFul web services for simple Blog Application. It uses an in-memory database (H2) to store the data. Initial data is inserted to this in-memory database at the application startup.

This API uses a 3rd party API called `TextCortex` for generate post content based on user given title and keywords. And  also to generate post title based on the user given intro and keyword. This API has many text-generation APIs and you can check more from here [TextCortex](https://textcortex.com/text-generation-api) and here [TextCortex-AI](https://documenter.getpostman.com/view/936254/2s83tCLYi9#text-generation-examples)

All APIs are documented using OpenAPI (Formerly Swagger).
Swagger-UI can be access via `http://localhost:8080/swagger-ui/index.html#/`

![plot](https://github.com/ThimanKr/BlogApp_RestAPI/blob/main/src/main/resources/images/Swagger-UI.png)
