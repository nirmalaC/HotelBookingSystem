Feature: As a customer I want to be able to book hotel reservation

  Scenario Outline: As a customer when we provide valid details we should be able to save our hotel booking
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>, <checkin_date>, <checkout_date>
    When I click on the save button
    Then I should be able to save the booking : <firstname>, <surename>
    Examples:
    | firstname  | surename   | price   | deposit   | checkin_date | checkout_date |
    | Jack       | Bob        | 380     | true      | 20           |  25           |
    | Jim        | Jole       | 650     | false     | 16           |  22           |


  Scenario Outline: As a customer I should be able to delete the saved booking
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>, <checkin_date>, <checkout_date>
    And vaild booking exists <firstname>, <surename>
    When I click on the delete button
    Then the saved bookings should be deleted : <firstname>, <surename>

    Examples:
      | firstname  | surename   |
      | Jack       | Bob        |
      | Jim        | Jole       |