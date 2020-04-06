# testBrdo
Testing the registration form on the site

# Libraries, Frameworks
Selenide, Allure, TestNg, WebDriverManager, log4j

## Usage
To launch this example install Gradle and run the following command from the root directory:
```bash
$ gradle
```
You should see failed tests and generated Allure XML files in **build/allure-results** directory.

## Report

Report generation example:
```bash
# Run from the root directory
$ allure generate build
```
Open report example:
```bash
# Run from the root directory
$ allure report open
```

Clean the report :
```bash
# Run from the root directory
$ allure report clean
```

To Run a Single Test
```bash
gradle test --tests *sigNupTests.SiNupTest
```
