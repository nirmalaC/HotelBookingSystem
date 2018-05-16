Feature: As a customer I want to be able to book hotel reservation

  Scenario Outline: As a customer when we provide valid details we should be able to save our hotel booking
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>
    When I click on the save button
    Then I should be able to make

    Examples:
    | firstname  | surename   | price   | deposit   |
    | Jack       | Bob        | 380     | true      |
    | Jim        | Jole       | 650     | false     |


  Scenario Outline: As a customer I should be able to delete the saved booking
    Given I enter vaild details : <firstname>, <surename>, <price>, <deposit>
    When I click on the save button
    Then I should be able to make

    Examples:
      | firstname  | surename   | price   | deposit   |
      | Jack       | Bob        | 380     | true      |
      | Jim        | Jole       | 650     | false     |