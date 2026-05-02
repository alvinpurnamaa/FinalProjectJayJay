@api
#Feature: User API Test
#
#  Scenario: CRUD User
#
#    Given I set API base URL
#
#    When I send POST request to "/user" with body:
#      """
#      {
#        "firstName": "Alvin",
#        "lastName": "QA",
#        "email": "alvinqa@test.com",
#        "title": "mr"
#      }
#      """
#    Then response status should be 200
#    And response should contain "id"
#
#    When I send PUT request to "/user/{id}" with body:
#      """
#      {
#        "firstName": "UpdatedName"
#      }
#      """
#    Then response status should be 200
#
#    When I send DELETE request to "/user/{id}"
#    Then response status should be 200


Feature: User API CRUD Test

  Scenario: Get user list
    Given user set GET API endpoint
    When user send GET request
    Then user get response status code 200

  Scenario: Get user by invalid ID (negative)
    Given user set GET API endpoint with invalid user id
    When user send GET request by invalid id
    Then user get response status code 400

  Scenario: Create new user
    Given user set POST API endpoint
    When user send POST request with valid body
    Then user get response status code 200

  Scenario: Update user
    Given user set PUT API endpoint
    When user send PUT request with valid body
    Then user get response status code 200

  Scenario: Delete user
    Given user set DELETE API endpoint
    When user send DELETE request
    Then user get response status code 200