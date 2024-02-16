package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import TestBase.baseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BeCognizantPage;
import pageObjects.OneCognizantPage;
import pageObjects.TimeSheetPage;

public class Timesheet {

	baseClass bc=new baseClass();
	BeCognizantPage bec;OneCognizantPage one;TimeSheetPage timesheet;
	public static List <String> windowHandles = new ArrayList<String>();
	Logger logger=baseClass.getLogger();
	@Given("User opens BeCognizant website")
	public void user_opens_be_cognizant_website() throws IOException {
		logger.info("***** starting TC001_beCognizant *****");	
		bec = new BeCognizantPage(baseClass.getDriver());
		logger.info("Opening BeCognizant in Browser");
	}

	@When("User checks the user profile")
	public void user_checks_the_user_profile() throws InterruptedException, IOException {
		Thread.sleep(10000);
		bec.clickProfile();
		logger.info("clicked on profile");
		Thread.sleep(1000);
		System.out.println("Account verified");
		logger.info("verified the account");
		bec.getProfile();
		bc.captureScreen("img_userprofile");
		bec.closeProfile();
		Thread.sleep(10000);
		windowHandles.add(bc.getHandle());
		System.out.println(windowHandles);
	}

	@Then("User verifies that the onecognizant button is present and clicks it")
	public void user_verifies_that_the_onecognizant_button_is_present_and_clicks_it() throws Exception {
		bec.openOneCognizant(baseClass.getDriver());
		bc.getHandle();
		logger.info("opening the OneCognizant");
		Thread.sleep(10000);
		bc.captureScreen("img_onecognizant");
		bc.setHandle(baseClass.getDriver(),windowHandles);
		logger.info("handled the OneCognizant window");
	}
	
	@Given("User is on the OneCog page")
	public void user_is_on_the_one_cog_page() throws Exception {
		logger.info("Navigated to OneCognizant");
		one = new OneCognizantPage(baseClass.getDriver()); 
		Thread.sleep(1000);
		windowHandles.add(bc.getHandle());
		System.out.println(windowHandles);
	}

	@When("User searches for {string} and clicks it")
	public void user_searches_for_and_clicks_on_submit_timesheet_button(String string) throws InterruptedException {
		logger.info("clicked on search Icon");
		one.inputSearchBar("Timesheet");
		one.search();
		one.clickSubmitTimesheet();
		System.out.println(windowHandles);
	}

	@Given("User is on the timesheet page")
	public void user_is_on_the_timesheet_page() throws Exception {
		Thread.sleep(10000);
		System.out.println(windowHandles);
		timesheet=new TimeSheetPage(baseClass.getDriver());
		logger.info("Navigated to timesheet");
		System.out.println(baseClass.getDriver().getWindowHandles());
		bc.setHandle(baseClass.getDriver(),windowHandles);
		windowHandles.add(bc.getHandle());
	}
	
	@Then("User verifies the timesheet page")
	public void user_navigates_and_verifies_the_timesheet_page() throws Exception {
		timesheet.headerValidation();
		bc.captureScreen("img_timesheet");
		logger.info("validating the header");
	}

	@When("user checks three Weeks Timesheet")
	public void user_checks_three_weeks_timesheet() throws Exception {
		Thread.sleep(1000);
		//Last Three week Timesheets
		timesheet.threeWeeksTimesheet();
		logger.info("three weeks of timesheet");
		bc.captureScreen("img_firstThreeWeeks");
	}

	@Then("User clicks on the searchBy dropdown and selects {string} .")
	public void user_clicks_on_the_search_by_dropdown_and_selects(String datestring) {
		//search By -Date
		timesheet.setSearchByDate(datestring);
	}
	
	@Then("User verifies that the date dropdown appears")
	public void user_verifies_that_the_date_dropdown_appears() {
		String DateDropDown=timesheet.verifyDateDropDown();
		logger.info(DateDropDown);
		System.out.println(DateDropDown);	
	}

	@When("User clicks todays date and searches it")
	public void user_clicks_todays_date_and_searches_it() throws InterruptedException {
		// set Today Date
		timesheet.setCurrentDate();
		timesheet.timesheetSearch();
	}

	@Then("User verifies that only the current week timesheet is displayed")
	public void user_verifies_that_only_the_current_week_timesheet_is_displayed() throws Exception {
		Thread.sleep(1000);
		timesheet.currentWeek();
		logger.info("Displayed current week");
		bc.captureScreen("img_currentWeek");
		timesheet.dateValidationTimesheet();
	}

	@When("User changes searchBy dropdown option to {string}")
	public void user_changes_search_by_dropdown_option_to(String stat) {
		//search By - Status
		timesheet.setSearchByStatus(stat);
	}

	@Then("User verifies that the status dropdown appears")
	public void user_verifies_that_the_status_dropdown_appears() {
		String StatusDropDown=timesheet.verifyStatusDropDown();
		logger.info(StatusDropDown);
		System.out.println(StatusDropDown);
	}

	@Then("User clicks and verifies all the elements from the dropdown separately")
	public void user_clicks_and_verifies_all_the_elements_from_the_dropdown_separately() throws Exception {
		//status - Approved 
		Thread.sleep(2000);
		timesheet.tsStatusApproved();
		logger.info("displayed  Status Approved");
		bc.captureScreen("img_StatusApproved");
		
		//status - OverDue
		Thread.sleep(5000);
		timesheet.tsStatusOverdue();
		logger.info("displayed tsStatusOverdue");
		bc.captureScreen("img_StatusOverdue");
		
		//status - Partially Approved
		Thread.sleep(5000);
		timesheet.tsStatusPartiallyApproved();
		logger.info("displayed tsStatusPartiallyApproved");
		bc.captureScreen("img_StatusPartiallyApproved");
		
		//status - Pending
		Thread.sleep(5000);
		timesheet.tsStatusPending();
		logger.info("displayed tsStatusPending ");
		bc.captureScreen("img_StatusPending");
	
		//status - Saved
		Thread.sleep(5000);
		timesheet.tsStatusSaved();
		logger.info("displayed tsStatusSaved");
		bc.captureScreen("img_StatusSaved");
		
		//status - Sent Back for Revision
		Thread.sleep(5000);
		timesheet.tsStatusSentBackforRevision();
		logger.info("displayed tsStatusSentBackforRevision");
		bc.captureScreen("img_StatusSentBackforRevision");
		
		//status - Submitted for Approval
		Thread.sleep(5000);
		timesheet.tsStatusSubmittedforApproval();
		logger.info("displayed tsStatusSubmittedforApproval");
		bc.captureScreen("img_StatusSubmittedforApproval");	
	}

}
