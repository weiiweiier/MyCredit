Feature: Testing MyCredit transaction process

  Scenario: Check the customer exist
    Given the base API URL is "http://127.0.0.1:8080/MyCredit/res"
    When I send a POST request to "/Query"
    Then the response code should be 200

  Scenario: Add a transaction
    Given the base API URL is "http://127.0.0.1:8080/MyCredit/res"
    When I send a POST request to "/Insert"
    Then the response code should be 200

#  Scenario: Update the transaction with Dispute transaction flag
#    Given the base API URL is "http://127.0.0.1:8080/MyCredit/res"
#    When I send a POST request to "/Modify"
#    Then the response code should be 200