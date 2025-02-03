<img src="https://www.readingroom.com/media/je2bjrgz/microsoftteams-image-78-1.png?width=1312&height=736&v=1d984adbf92fc30" width="300" height=auto />

<div style="width: auto; height: auto; margin: 15px auto; padding: 2px; background-color: #072169 !important; text-align: center;">
    <h1 style="font-size: 22px; color: #fefefe;">How To Run Using MVN Commands</h1>
</div>
<div style="background-color: #f0f8ff; padding: 10px; border-radius: 5px; margin: 20px 0;">
    <p><strong>Info:</strong> Follow the instructions below to run the tests using Maven commands for different configurations and scenarios.</p>
</div>


# Maven Test Execution Guide

This repository contains automation tests for Web and API Testing using Maven, Selenium, Cucumber, and Rest-Assured. Below are various ways to execute the test suite directly from the command line using Maven.


## **Table of Contents**
- [Prerequisites](#prerequisites)
- [Basic Execution](#basic-execution)
- [Running Tests in Different Browsers](#running-tests-in-different-browsers)
- [Running Tests by Tags](#running-tests-by-tags)
- [Running Tests in Parallel](#running-tests-in-parallel)
- [Debugging Tests](#debugging-tests)
- [Generating Reports](#generating-reports)
- [Executing Tests with Exec Plugin](#executing-tests-with-exec-plugin)
- [Combining Multiple Options](#combining-multiple-options)

## **Prerequisites**
1. **JDK 17+** installed and configured in the `PATH`
2. **Apache Maven** installed (`mvn -version` to verify)
3. **WebDriver for the browser** (handled by WebDriverManager)
4. **IDE** (e.g., IntelliJ IDEA, Eclipse) (handled by WebDriverManager)
5. **Jenkins (Optional)** for job triggering


## **Basic Execution**
Run all test cases in the default browser (**Chrome**):

```
mvn clean test
```

## Running Tests in Different Browsers
You can specify the browser for test execution using the -Dbrowser argument. This dynamically sets the browser to be used during the test execution.

 - **Run Tests on Chrome**:

```
mvn clean test -Dbrowser=chrome
```
 - **Run Tests on Edge**:
 
```
mvn clean test -Dbrowser=edge

```

 - **Run Tests on Firefox**:
 
```
mvn clean test -Dbrowser=firefox

```

 - **Run Tests on Safari**(Only for MacOS):
 
```
mvn clean test -Dbrowser=safari

```

## Running Tests by Tags
Cucumber tests can be filtered by tags. Use the -Dcucumber.filter.tags argument to run tests with a specific tag.
- **Run Tests with a Specific Tag:**:

```
mvn clean test -Dcucumber.filter.tags="@Smoke"

```
- **Run Tests with Multiple Tags:**:

```
mvn clean test -Dcucumber.filter.tags="@Smoke and @Regression"

```
- **Override Tags and Run with a Specific Test**

```
mvn clean test -Dcucumber.filter.tags="@Smoke or @AmazonSearch"
```

## Run Tests for a Specific Environment
Override the environment to dynamically pick the appropriate URL from your environment-config.yaml file:

```
mvn clean test -Denv=IN
```
## Running Tests in Parallel
To enable parallel execution of tests, you can configure the parallel.thread.count property.

- **Run Tests in Parallel**:

```
mvn clean test -Dcucumber.parallel.enabled=true

```
- **Run Tests with specific thread count**:

```
mvn clean test -Dcucumber.parallel.enabled=true -Dparallel.thread.count=4

```
## Run Tests for a Specific Feature File
- **Specify the feature file to run the tests for**:

```
mvn clean test -Dcucumber.features="src/test/resources/features/amazon_search.feature"
```

## Run Tests with Specific Cucumber Plugin Options
Specify additional reporting plugins (e.g., HTML, JSON, JUnit reports):

```
mvn clean test -Dcucumber.plugin="pretty,html:${project.build.directory}/cucumber-reports/cucumber-html-report.html,json:${project.build.directory}/cucumber-reports/cucumber.json,junit:${project.build.directory}/cucumber-reports/Cucumber.xml"
```

## Debugging Tests
If you need to debug your test execution, you can enable debugging by setting the -DdebugForkedProcess to true. This is especially useful when running tests in parallel.

- **Run Tests with Debugging Enabled**:

```
mvn clean test -DdebugForkedProcess=true

```

## Generating Reports
Reports can be generated in different formats (HTML, JSON, JUnit). The following options will store the reports in the target/cucumber-reports directory.

- **Generate HTML, JSON, and JUnit Reports**:

```
mvn clean test -DgenerateReports=true

```

## Executing Tests with Exec Plugin
If you need to trigger additional commands during your test execution, such as invoking a Jenkins job, use the exec-maven-plugin.

- **Trigger Jenkins Job**:

```
mvn exec:exec -Dexec.executable="curl" -Dexec.args="-X POST http://localhost:8080/job/circlehealth_WebAPI_Automation/build --user 'user:token'"
```

## Combining Multiple Options
You can combine multiple options to fine-tune your test execution. For example, to run tests on Chrome in parallel with a specific tag and generate HTML reports, use:

- **Combine Parallel Execution, Browser, and Reports Generation**:

```
mvn clean test -Dbrowser=chrome -Dcucumber.filter.tags="@Smoke" -Dparallel.thread.count=4 -DgenerateReports=true

```
