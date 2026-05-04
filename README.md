# Automation Testing Final Project (Web + API)

Project ini merupakan automation testing framework berbasis Java yang menggunakan Selenium, Rest Assured, Cucumber, dan Gradle untuk melakukan testing Web UI dan API dengan pendekatan BDD (Behavior Driven Development).

---

## Tech Stack

- Java 11
- Selenium WebDriver
- Rest Assured
- Cucumber (BDD)
- Gradle
- JUnit / Cucumber Runner
- ChromeDriver

---

# WEB AUTOMATION TEST

## Website Under Test
https://www.saucedemo.com/

## Test Scenarios

- Login with valid username and password
- Login with invalid password
- Login with empty username
- Login with empty password
- Successful checkout product

---

## API Automation Scenario

- Scenario: Get user list  
- Scenario: Get user by invalid ID (negative test)  
- Scenario: Create new user  
- Scenario: Update user  
- Scenario: Delete user  

## Base URL
https://dummyapi.io/data/v1  

## Endpoints

- GET Users → /user  
- CREATE User → /user/create  
- UPDATE User → /user/{id}  
- DELETE User → /user/{id}  

## App ID
63a804408eb0cb069b57e43a  

---

## How to Run the Test

### 1. Run Web Test
```bash
./gradlew webTest
### 2. Run API Test
./gradlew webTest

---

## Test Report
Web Report
- /reports/web-report.html
## API Report
- /reports/api-report.html

