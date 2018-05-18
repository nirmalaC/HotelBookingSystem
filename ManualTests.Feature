All the acceptance tests are written based on the assumptions :
1. Error messages are displayed when invalid details are provided (e.g : firstname, surename, price and deposit, checkin date and checkout date)
2. Error messages are displayed when checkout date is lesser than the checkin date or this scenario should not be allowed.
3. Success message is displayed when a booking is saved successfully.
4. Success message is displayed when a booking is deleted successfully.
5. Bookings canot be saved for past dates and only saved for future dates.
6. Firstname and Surename fields only allow Alphabets(A-Z or a-z). Numeric or special characters are allowed.
7. Deposit field only allows numeric characters(0-9). Alphabets are nort allowed.
8. Checkin and checkout dates only allows vaild date format.


Feature : Hotel Booking System

  1. Scenario Outline:  User successfully saves booking reference
  Given I entered the valid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should be saved
  And success message is displayed
  And saved booking should be displayed : <first_name>, <sure_name>, <price>, <deposit>
  Examples:
  | first_name | sure_name | price |deposit | checkin_date | checkout_date  |
  | Jack       | Jones     | 500   | true   |  20          |  25            |
  | JImmy      | Bill      | 460   | false  |  25          |  28            |


  2. Scenario Outline:  User successfully deletes a saved booking reference
  Given I entered the valid details : <first_name>, <sure_name>, <price>, <deposit>
  And I saved the booking
  When I clciked delete button
  Then the saved booking should be deleted
  And success message is displayed
  Examples:
  | first_name | sure_name | price |deposit |
  | Jack       | Jones     | 500   | true   |
  | Jack       | Jones     | 500   | false  |


  3. Scenario Outline:  User should be displayed with error message if invalid first name are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name      | sure_name      | price |deposit | error_message                    |
  | 12345867!"£$%   | Jones          | 500   | true   | Please enter a vaild First Name  |
  | Ji543^&         | Jones          | 460   | false  | Please enter a vaild First Name  |
  | null            | Jones          | 460   | false  | Please enter a vaild First Name  |
  |                 | Jones          | 460   | false  | Please enter a vaild First Name  |



  4. Scenario Outline:  User should be displayed with error message if invalid Sure name are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name    | sure_name        | price |deposit | error_message                    |
  | Jack          | 12345867!"£$%    | 500   | true   | Please enter a vaild Sure Name   |
  | Jack          | 1234567          | 460   | false  | Please enter a vaild Sure Name   |
  | Jack          | 1234^&*          | 460   | false  | Please enter a vaild Sure Name   |
  | Jack          | null             | 460   | false  | Please enter a vaild Sure Name   |


  5. Scenario Outline:  User should be displayed with error message if invalid price are provided and the booking should not be saved
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>
  When I clciked Save button
  Then the booking should not be saved
  And should be display <error_message>
  Examples:
  | first_name | sure_name  | price |deposit | error_message                    |
  | Jack       | Jones      | 500   | true   | Please enter a vaild First Name  |
  | Jack       | Jones      | 460   | false  | Please enter a vaild First Name  |


  7. Scenario: User should not be able to save booking on past dates
  Given I entered the invalid details : <first_name>, <sure_name>, <price>, <deposit>, <checkin_dtae>, <checkout_dtae>
  When I clciked Save button
  Then the booking should not be saved
  And checkin daet <checkin_error_message> should be display
  And checkin daet <error_message> should be display
