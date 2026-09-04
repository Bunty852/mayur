Feature: Practice Test Automation Login

  Scenario: Successful login with valid credentials
    Given User navigates to the login page
    When User enters username "student" and password "Password123"
    And User clicks the submit button
    Then User should be redirected to the success page containing "logged-in-successfully"
    And The logout button should be displayed