@FeatureAutomationTest
Feature: Hotel booking reservation
  As a customer
  I want to use the book hotel reservation system
  So that I can book my hotel reservations

  Scenario Outline: User should be able to save the hotel booking with or without deposit amount.
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>, <checkin_date>, <checkout_date>
    When I click on the save button
    Then I should be able to save the booking : <firstname>, <surename>, <price>, <deposit>, <checkin_date>, <checkout_date>
    Examples:
      | firstname | surename | price | deposit | checkin_date | checkout_date |
      | Jack      | Bob      | 380   | true    | 20           | 25            |
      | Jim       | Jole     | 650   | false   | 16           | 22            |

  Scenario Outline: User should be able to delete the saved bookings successfully.
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>, <checkin_date>, <checkout_date>
    And I click on the save button
    When I click on the delete button : <firstname>
    Then the saved bookings should be deleted : <firstname>, <surename>
    Examples:
      | firstname | surename | price | deposit | checkin_date | checkout_date |
      | Jack      | Bob      | 380   | true    | 20           | 25            |
      | Jim       | Jole     | 650   | false   | 16           | 22            |