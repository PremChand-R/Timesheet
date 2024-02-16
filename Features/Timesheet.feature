Feature: Timesheet Testing

  Scenario: User Profile Verification and OneCog Navigation
    Given User opens BeCognizant website
    When User checks the user profile
    Then User verifies that the onecognizant button is present and clicks it

  Scenario: Timesheet Navigation and Verification
    Given User is on the OneCog page
    When User searches for "timesheet" and clicks it
    
  Scenario: Timesheet Search Result Verification
  	Given User is on the timesheet page 
    Then User verifies the timesheet page
		When user checks three Weeks Timesheet
    Then User clicks on the searchBy dropdown and selects "Date" .
    Then User verifies that the date dropdown appears
    When User clicks todays date and searches it
    Then User verifies that only the current week timesheet is displayed
    When User changes searchBy dropdown option to "Status"
    Then User verifies that the status dropdown appears
    Then User clicks and verifies all the elements from the dropdown separately