@ApiTests
Feature: User should be able to submit POST and DELETE requests to the web services

  Scenario: User should be able to save the hotel booking
    Given I have POST service api endpoint
    When I post my booking details
    Then I should be able to create my bookings
      | firstname | surename | price  | deposit | checkindate | checkoutdate |
      | Laura     | Sim      | 594.38 | false   | sysdate+10  | sysdate+15   |

  Scenario: User should be able to delete the saved bookings successfully
    Given I have DELETE service api endpoint
    When I delete my booking details
    Then the saved bookings should be deleted

