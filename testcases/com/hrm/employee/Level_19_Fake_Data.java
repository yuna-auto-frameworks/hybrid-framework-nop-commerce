package com.hrm.employee;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;
import utilities.DataUtil;

public class Level_19_Fake_Data extends BaseTest {
	String adminUserName, adminPassword, employeeFirstName, employeeLastName, employeeUserName, employeePassword;

	// Add Employee & Edit 'Personal Details'
	String employeeFullName, editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;

	// Edit 'Contact Details'
	String addressStreet1, addressStreet2, city, stateProvince, zipPostalCode, country, homeTelephone, mobile,
			workTelephone, workEmail, otherEmail;
	String employeeID, statusValue;

	// Emer. Contacts
	String emerName, emerRela, emerHomeTel, emerMobile, emerWorkTel;

	// Dependents
	String dependentName, dependentRela, specifyRela;
	Date dependentDOB;

	// Immigration
	String immigNumber;

	// Job
	String jobTitle, empStatus, jobCategory, jobSubUnit, jobLocation;
	Date joinedDate, startDate, endDate;

	// Salary

	// Tax Exemptions
	String federalStatus, federalExemptions, taxState, stateStatus, tateExemptions, unempState, workState;

	String avatarFilePath = GlobalConstants.UPLOAD_FILE + "Dedo.jpg";
	String attachmentFilePath = GlobalConstants.UPLOAD_FILE + "airbus320.jpg";
	String contractFilePath = GlobalConstants.UPLOAD_FILE + "Yunxi_Yi.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGenerator.getLoginPage(driver);
		fakeData = DataUtil.getData();

		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";

		// Add Employee
		employeeFirstName = fakeData.getFirstName();
		employeeLastName = fakeData.getLastName();
		employeeUserName = fakeData.getUsername();
		employeePassword = fakeData.getPassword();
		employeeFullName = employeeFirstName + " " + employeeLastName;

		// Edit 'Personal Details'
		editEmpFirstName = fakeData.getEditFirstName();
		editEmpLastName = fakeData.getEditLastName();
		editEmpGender = "Female";
		editEmpMaritalStatus = "Single";
		editEmpNationality = "Vietnamese";

		// Emer. Contacts
		emerName = fakeData.getName();
		emerRela = fakeData.getRelationship();
		emerHomeTel = fakeData.getCellPhone();
		emerMobile = fakeData.getCellPhone();
		emerWorkTel = fakeData.getCellPhone();

		// Dependents
		dependentName = fakeData.getName();
		dependentRela = "Other";
		specifyRela = fakeData.getRelationship();
		dependentDOB = fakeData.getYYYYMMDD();

		// Immigration
		immigNumber = fakeData.getPhoneNumber();

		// Edit 'Contact Details'
		addressStreet1 = fakeData.getStreetAddress();
		addressStreet2 = fakeData.getStreetAddress();
		city = fakeData.getCity();
		stateProvince = fakeData.getState();
		zipPostalCode = fakeData.getZipPostalCode();
		country = fakeData.getCountry();
		homeTelephone = fakeData.getCellPhone();
		mobile = fakeData.getCellPhone();
		workTelephone = fakeData.getCellPhone();
		workEmail = fakeData.getEmailAddress();
		otherEmail = fakeData.getEmailAddress();

		// Job
		jobTitle = "";
		empStatus = "";
		jobCategory = "";
		jobSubUnit = "";
		jobLocation = "";

		// Salary

		// Tax Exemptions
		federalStatus = "";
		federalExemptions = "";
		taxState = "";
		stateStatus = "";
		tateExemptions = "";
		unempState = "";
		workState = "";

		log.info("Pre-Condition - Step 02: Login with Admin Role '");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add_New_01 - Step 01: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 02: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Add_New_01 - Step 03: Enter valid info to 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", employeeFirstName);

		log.info("Add_New_01 - Step 04: Enter valid info to 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", employeeLastName);

		log.info("Add_New_01 - Step 05: Get value of 'Employee ID' ");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Add_New_01 - Step 06: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Add_New_01 - Step 07: Enter valid info to 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", employeeUserName);

		log.info("Add_New_01 - Step 08: Enter valid info to 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", employeePassword);

		log.info("Add_New_01 - Step 09: Enter valid info to 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", employeePassword);

		log.info("Add_New_01 - Step 10: Select ' " + statusValue + " ' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);

		log.info("Add_New_01 - Step 11: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Add_New_01 - Step 12: Open 'Employee List' Page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Add_New_01 - Step 13: Enter valid info to 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", employeeFullName);
		verifyTrue(employeeListPage.areJQueryAndJSLoadedSuccess(driver));

		log.info("Add_New_01 - Step 14: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		log.info("Add_New_01 - Step 15: Verify Employee Infomation displayed at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"),
				employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable",
				"First (& Middle) Name", "1"), employeeFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"),
				employeeLastName);

	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload_Avatar_02 - Step 01: Login with Employee Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, employeeUserName, employeePassword);

		log.info("Upload_Avatar_02 - Step 02: Open Personal Detail Page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		log.info("Upload_Avatar_02 - Step 03: Click to Change Photo Image");
		myInfoPage.clickToChangePhotoImage();

		log.info("Upload_Avatar_02 - Step 04: Upload New Avatar Image");
		myInfoPage.uploadImage(driver, avatarFilePath);

		log.info("Upload_Avatar_02 - Step 05: Click on 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		myInfoPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("Upload_Avatar_02 - Step 06: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Upload_Avatar_02 - Step 07: Verify New Avatar Image is Displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Perosnal_Details() {
		log.info("Personal Details_03 - Step 01: Open 'Personal Details' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");

		log.info("Personal Details_03 - Step 02: Verify all fields at 'Personal Details' forms are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtLicExpDate"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmpNickName"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_chkSmokeFlag"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtMilitarySer"));

		log.info("Personal Details_03 - Step 03: Click to 'Edit' Button at 'Personal Details' ");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal Details_03 - Step 04: Verify 'Employee ID' textbox is disabled"); // 1
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtEmployeeId"));

		log.info("Personal Details_03 - Step 05: Verify 'Driver's License Number textbox is disabled"); // 2
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtLicenNo"));

		log.info("Personal Details_03 - Step 06: Verify 'SSN Number' textbox is disabled"); // 3
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtNICNo"));

		log.info("Personal Details_03 - Step 07: Verify 'SIN Number' textbox is disabled"); // 4
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_txtSINNo"));

		log.info("Personal Details_03 - Step 08: Verify 'Date of Birth' textbox is disabled"); // 5
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "personal_DOB"));

		log.info("Personal Details_03 - Step 09: Enter new value to 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);

		log.info("Personal Details_03 - Step 10: Enter new value to 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);

		log.info("Personal Details_03 - Step 11: Select new value to 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);

		log.info("Personal Details_03 - Step 12: Select new value to 'Marital Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);

		log.info("Personal Details_03 - Step 13: Select new value to 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);

		log.info("Personal Details_03 - Step 14: Click on 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal Details_03 - Step 15: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal Details_03 - Step 16: Verify 'First Name' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		log.info("Personal Details_03 - Step 17: Verify 'Last Name' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		log.info("Personal Details_03 - Step 18: Verify 'Gender' radio button is successfully updated");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLable(driver, editEmpGender));

		log.info("Personal Details_03 - Step 19: Verify 'Marital Status' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);

		log.info("Personal Details_03 - Step 20: Verify 'Nationality' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);

		log.info("Personal Details_03 - Step 21: Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);

		log.info("Personal Details_03 - Step 22: Click on 'Edit' button at 'Custom Fields' ");
		myInfoPage.clickToButtonByID(driver, "btnEditCustom");

		log.info("Personal Details_03 - Step 23: Print out all values in 'Blood Type' dropdown list");
		myInfoPage.getAllValuesInDropdownList(driver);

		log.info("Personal Details_03 - Step 24: Click on 'Add' button at 'Attachments' field");
		myInfoPage.clickToButtonByID(driver, "btnAddAttachment");

		log.info("Personal Details_03 - Step 25: Upload Attachment");
		myInfoPage.uploadImage(driver, attachmentFilePath);

		log.info("Personal Details_03 - Step 26: Click on 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSaveAttachment");
		myInfoPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("Personal Details_03 - Step 27: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		log.info("Personal Details_03 - Step 28: Verify new attachment is uploaded");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblAttachments", "File Name", "1"),
				"airbus320.jpg");

	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact_Details_04 - Step 01: Open 'Contact Details' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		log.info("Contact_Details_04 - Step 02: Verify all fields at 'Contact Details' forms are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_province"));

		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "contact_emp_oth_email"));

		log.info("Contact_Details_04 - Step 03: Click to 'Edit' Button at 'Contact Details' ");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 04: Enter new value to 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", addressStreet1);

		log.info("Contact_Details_04 - Step 05: Enter new value to 'Address Street 2' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street2", addressStreet2);

		log.info("Contact_Details_04 - Step 06: Enter new value to 'City' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_city", city);

		log.info("Contact_Details_04 - Step 07: Enter new value to 'State/Province' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_province", stateProvince);

		log.info("Contact_Details_04 - Step 08: Enter new value to 'Zip/Postal Code' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_zipcode", zipPostalCode);

		log.info("Contact_Details_04 - Step 09: Select new value to 'Country' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", country);

		log.info("Contact_Details_04 - Step 10: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_hm_telephone", homeTelephone);

		log.info("Contact_Details_04 - Step 11: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_mobile", mobile);

		log.info("Contact_Details_04 - Step 12: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_telephone", workTelephone);

		log.info("Contact_Details_04 - Step 13: Enter new value to 'Work Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_email", workEmail);

		log.info("Contact_Details_04 - Step 14: Enter new value to 'Other Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_oth_email", otherEmail);

		log.info("Contact_Details_04 - Step 15: Click on 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact_Details_04 - Step 16: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact_Details_04 - Step 17: Verify 'Address Street 1' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), addressStreet1);

		log.info("Contact_Details_04 - Step 18: Verify 'Address Street 2' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), addressStreet2);

		log.info("Contact_Details_04 - Step 19: Verify 'City' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), city);

		log.info("Contact_Details_04 - Step 20: Verify 'State/Province' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), stateProvince);

		log.info("Contact_Details_04 - Step 21: Verify 'Zip/Postal Code' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), zipPostalCode);

		log.info("Contact_Details_04 - Step 22: Verify 'Country' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByID(driver, "contact_country"), country);

		log.info("Contact_Details_04 - Step 23: Verify 'Home Telephone' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), homeTelephone);

		log.info("Contact_Details_04 - Step 24: Verify 'Mobile' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), mobile);

		log.info("Contact_Details_04 - Step 25: Verify 'Work Telephone' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), workTelephone);

		log.info("Contact_Details_04 - Step 26: Verify 'Work Email' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), workEmail);

		log.info("Contact_Details_04 - Step 27: Verify 'Other Email' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"), otherEmail);

	}

	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency_Contacts_05 - Step 01: Open 'Emergency Contacts' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");

		log.info("Emergency_Contacts_05 - Step 02: Click on 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		log.info("Emergency_Contacts_05 - Step 03: Verify all fields at 'Add Emergency Contact' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "emgcontacts_name"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "emgcontacts_relationship"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "emgcontacts_homePhone"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "emgcontacts_mobilePhone"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "emgcontacts_workPhone"));

		log.info("Emergency_Contacts_05 - Step 04: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", emerName);

		log.info("Emergency_Contacts_05 - Step 05: Enter new value to 'Relationship' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", emerRela);

		log.info("Emergency_Contacts_05 - Step 06: Enter new value to 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", emerHomeTel);

		log.info("Emergency_Contacts_05 - Step 07: Enter new value to 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_mobilePhone", emerMobile);

		log.info("Emergency_Contacts_05 - Step 08: Enter new value to 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_workPhone", emerWorkTel);

		log.info("Emergency_Contacts_05 - Step 09: Click on 'Save' button at 'Add Emergency Contact' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		log.info("Emergency_Contacts_05 - Step 10: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Emergency_Contacts_05 - Step 11: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		log.info("Emergency_Contacts_05 - Step 12: Verify added 'Name' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"),
				emerName);

		log.info("Emergency_Contacts_05 - Step 13: Verify added 'Relationship' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"),
				emerRela);

		log.info("Emergency_Contacts_05 - Step 14: Verify added 'Home Telephone' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"),
				emerHomeTel);

		log.info("Emergency_Contacts_05 - Step 15: Verify added 'Mobile' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Mobile", "1"),
				emerMobile);

		log.info("Emergency_Contacts_05 - Step 16: Verify added 'Work Telephone' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Work Telephone", "1"),
				emerWorkTel);

	}

	@Test
	public void Employee_06_Assigned_Dependents() {
		log.info("Assigned_Dependents_06 - Step 01: Open 'Dependents' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Dependents");

		log.info("Assigned_Dependents_06 - Step 02: Click on 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		log.info("Assigned_Dependents_06 - Step 03: Verify all fields at 'Add Dependent' are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "dependent_name"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "dependent_relationshipType"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "dependent_dateOfBirth"));

		log.info("Assigned_Dependents_06 - Step 04: Enter new value to 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_name", dependentName);

		log.info("Assigned_Dependents_06 - Step 05-a: Select ' " + dependentRela
				+ " ' value in 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", dependentRela);

		log.info("Assigned_Dependents_06 - Step 05-b: Enter new value to 'Please Specify' textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_relationship", specifyRela);

		log.info("Assigned_Dependents_06 - Step 06: Pick new value from 'Date of Birth' datepicker");
		myInfoPage.enterADateToTextboxByID(driver, "dependent_dateOfBirth", dependentDOB);

		log.info("Assigned_Dependents_06 - Step 07: Click on 'Save' button at 'Add Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		log.info("Assigned_Dependents_06 - Step 08: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigned_Dependents_06 - Step 09: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		log.info("Assigned_Dependents_06 - Step 10: Verify added 'Name' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"),
				dependentName);

		log.info("Assigned_Dependents_06 - Step 11: Verify added 'Relationship' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"),
				specifyRela);

		log.info("Assigned_Dependents_06 - Step 12: Verify added 'Date of Birth' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"),
				dependentDOB);
	}

	@Test
	public void Employee_07_Assigned_Immigration_Records() {
		log.info("Assigned_Immigration_07 - Step 01: Open 'Immigration' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Immigration");

		log.info("Assigned_Immigration_07 - Step 02: Click on 'Add' button at 'Assigned Immigration Records' ");
		myInfoPage.clickToButtonByID(driver, "btnAdd");

		log.info("Assigned_Immigration_07 - Step 03: Verify that 'Passport' checkbox default selected");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLable(driver, "Passport"));

		log.info("Assigned_Immigration_07 - Step 04: Veriy all fields at 'Add Immigration' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_number"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_passport_issue_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_passport_expire_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_i9_status"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_country"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_i9_review_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "immigration_comments"));

		log.info("Assigned_Immigration_07 - Step 05: Enter new value to 'Number' textbox");
		myInfoPage.enterToTextboxByID(driver, "immigration_number", immigNumber);

		log.info("Assigned_Immigration_07 - Step 06: Click on 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Assigned_Immigration_07_ Step 08: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Assigned_Immigration_07_ Step 09: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		log.info("Assigned_Immigration_07_ Step 10: Verify added 'Passport' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "", "Document", "1"), "Passport");

		log.info("Assigned_Immigration_07_ Step 11: Verify added 'Number' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "", "Number", "1"), immigNumber);
	}

	@Test
	public void Employee_08_Edit_View_Job() {
		log.info("Job_08 - Step 01: Open 'Job' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Job");

		log.info("Job_08 - Step 02: Verify some fields at 'Job' form are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_job_title"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_location"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "job_contract_end_date"));

		log.info("Job_08 - Step 03: Click on 'Edit' button at 'Job' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Job_08 - Step 04: Print out all values in 'Job Title' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "job_job_title");

		log.info("Job_08 - Step 05: Select new value to 'Job Title' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "job_job_title", jobTitle);

		log.info("Job_08 - Step 06: Print out all values in 'Employment Status' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "job_emp_status");

		log.info("Job_08 - Step 07: Select new value to 'Employment Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "job_emp_status", empStatus);

		log.info("Job_08 - Step 08: Print out all values in 'Job Category' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "job_eeo_category");

		log.info("Job_08 - Step 09: Select new value to 'Job Category' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "job_eeo_category", jobCategory);

		log.info("Job_08 - Step 10: Pick new value from 'Joined Date' datepicker");
		myInfoPage.enterADateToTextboxByID(driver, "job_joined_date", joinedDate);

		log.info("Job_08 - Step 11: Print out all values in 'Sub Unit' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "job_sub_unit");

		log.info("Job_08 - Step 12: Select new value to 'Sub Unit' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "job_sub_unit", jobSubUnit);

		log.info("Job_08 - Step 13: Print out all values in 'Location' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "job_location");

		log.info("Job_08 - Step 14: Select new value to 'Location' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "job_location", jobLocation);

		log.info("Job_08 - Step 15: Pick new value from 'Start Date' datepicker at 'Employment Contract' ");
		myInfoPage.enterADateToTextboxByID(driver, "job_contract_start_date", startDate);

		log.info("Job_08 - Step 16: Pick new value from 'End Date' datepicker at 'Employment Contract' ");
		myInfoPage.enterADateToTextboxByID(driver, "job_contract_end_date", endDate);

		log.info("Job_08 - Step 17: Upload new file at 'Contract Details' ");
		myInfoPage.uploadImage(driver, contractFilePath);

		log.info("Job_08 - Step 18: Click on 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		myInfoPage.isJQueryAjaxLoadedSuccess(driver);

		log.info("Job_08 - Step 19: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		log.info("Job_08 - Step 20: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_job_title"), jobTitle);

		log.info("Job_08 - Step 21: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_emp_status"), empStatus);

		log.info("Job_08 - Step 22: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_eeo_category"), jobCategory);

		log.info("Job_08 - Step 23: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_joined_date"), joinedDate);

		log.info("Job_08 - Step 24: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_sub_unit"), jobSubUnit);

		log.info("Job_08 - Step 25: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_location"), jobLocation);

		log.info("Job_08 - Step 26: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_start_date"), startDate);

		log.info("Job_08 - Step 27: Verify ");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "job_contract_end_date"), endDate);

		log.info("Job_08 - Step 28: Verify ");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "", "File Name", "1"), "Yunxi_Yi.jpg");

	}

	@Test
	public void Employee_09_Edit_View_Salary() {
		log.info("Salary_09 - Step 01: Open 'Salary' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Salary");

	}

	@Test
	public void Employee_10_Edit_View_Tax() {
		log.info("Tax_Exemptions_10 - Step 01: Open 'Tax Exemptions' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Tax Exemptions");

		log.info("Tax_Exemptions_10 - Step 02: Verify all fiels at 'Tax Exemptions' form are Disabled");
		// Federal Income Tax
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_federalStatus"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_federalExemptions"));
		// State Income Tax
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_state"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_stateStatus"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_stateExemptions"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_unempState"));
		verifyFalse(myInfoPage.isFieldEnabledByName(driver, "tax_workState"));

		log.info("Tax_Exemptions_10 - Step 03: Click on 'Edit' button at 'Tax Exemptions' ");
		myInfoPage.clickToButtonByID(driver, "");

		log.info(
				"Tax_Exemptions_10 - Step 04: Print out all values in 'Status' dropdown list at 'Federal Income Tax' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "tax_federalStatus");

		log.info("Tax_Exemptions_10 - Step 05: Select new value to 'Status' dropdown at 'Federal Income Tax' ");
		myInfoPage.selectItemInDropdownByID(driver, "tax_federalStatus", federalStatus);

		log.info("Tax_Exemptions_10 - Step 06: Enter new value to 'Exemptions' textbox at 'Federal Income Tax' ");
		myInfoPage.enterToTextboxByID(driver, "tax_federalExemptions", federalExemptions);

		log.info("Tax_Exemptions_10 - Step 07: Print out all values in 'State' dropdown list at 'State Income Tax' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "tax_state");

		log.info("Tax_Exemptions_10 - Step 08: Select new value to 'State' dropdown at 'State Income Tax' ");
		myInfoPage.selectItemInDropdownByID(driver, "tax_state", taxState);

		log.info("Tax_Exemptions_10 - Step 09: Print out all values in 'Status' dropdown list at 'State Income Tax' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "tax_stateStatus");

		log.info("Tax_Exemptions_10 - Step 10: Select new value to 'Status' dropdown at 'State Income Tax' ");
		myInfoPage.selectItemInDropdownByID(driver, "tax_stateStatus", stateStatus);

		log.info("Tax_Exemptions_10 - Step 11: Enter new value to 'Exemptions' textbox at 'State Income Tax' ");
		myInfoPage.enterToTextboxByID(driver, "tax_stateExemptions", tateExemptions);

		log.info(
				"Tax_Exemptions_10 - Step 12: Print out all values in 'Unemployment State' dropdown list at 'State Income Tax' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "tax_unempState");

		log.info(
				"Tax_Exemptions_10 - Step 13: Select new value to 'Unemployment State' dropdown at 'State Income Tax' ");
		myInfoPage.selectItemInDropdownByID(driver, "tax_unempState", unempState);

		log.info(
				"Tax_Exemptions_10 - Step 14: Print out all values in 'Work State' dropdown list at 'State Income Tax' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "tax_workState");

		log.info("Tax_Exemptions_10 - Step 15: Select new value to 'Work State' dropdown at 'State Income Tax' ");
		myInfoPage.selectItemInDropdownByID(driver, "tax_workState", workState);

	}

	@Test
	public void Employee_11_Qualifications() {
		log.info("Qualifications_11 - Step 01: Open 'Qualifications' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Qualifications");

		// Work Experience
		log.info("Qualifications_11 - Step 02: Click on 'Add' button at 'Work Experience' form");
		myInfoPage.clickToButtonByID(driver, "addWorkExperience");

		log.info("Qualifications_11 - Step 03: Verify all fields at 'Add Work Experience' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "experience_employer"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "experience_jobtitle"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "experience_from_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "experience_to_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "experience_comments"));

		log.info("Qualifications_11 - Step 04 : Enter new value to 'Company' textbox at 'Add Work Experience' form");
		log.info("Qualifications_11 - Step 05 : Enter new value to 'Job Title' textbox at 'Add Work Experience' form");
		log.info("Qualifications_11 - Step 06 : Pick new value at 'From' ");
		log.info("Qualifications_11 - Step 07 : Pick new value at 'To' ");
		log.info("Qualifications_11 - Step 08 : Enter new value to 'Comment' textbox at 'Add Work Experience' form");
		log.info("Qualifications_11 - Step 09 : Click on 'Save' button at 'Add Work Experience' form");

		log.info("Qualifications_11 - Step 10 : Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Qualifications_11 - Step 11: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		log.info("Qualifications_11 - Step 12: Verify added 'Company' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "table hover", "Company", "1"), "");

		log.info("Qualifications_11 - Step 13: Verify added 'Job Title' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "table hover", "Job Title", "1"), "");

		log.info("Qualifications_11 - Step 14: Verify added 'From' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "table hover", "From", "1"), "");

		log.info("Qualifications_11 - Step 15: Verify added 'To' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "table hover", "To", "1"), "");

		log.info("Qualifications_11 - Step 16: Verify added 'Comment' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "table hover", "Comment", "1"), "");

		// Education
		log.info("Qualifications_11 - Step 16: Click on 'Add' button at 'Education' form");
		myInfoPage.clickToButtonByID(driver, "addEducation");

		log.info("Qualifications_11 - Step 17: Verify all fields at 'Education' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_code"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_institute"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_major"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_year"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_gpa"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_start_date"));
		verifyTrue(myInfoPage.isFieldEnabledByName(driver, "education_end_date"));

		log.info("Qualifications_11 - Step 18: Print out all values in 'Level' dropdown list at 'Add Education' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "education_code");

		log.info("Qualifications_11 - Step 19: Select new value 'Level' dropdown list at 'Add Education' ");
		myInfoPage.selectItemInDropdownByID(driver, "education_code", "");

		log.info("Qualifications_11 - Step 20: Enter new value to 'Institute' textbox at 'Add Education' form");
		log.info(
				"Qualifications_11 - Step 21: Enter new value to 'Major/Specialization' textbox at 'Add Education' form");
		log.info("Qualifications_11 - Step 22: Enter new value to 'Year' textbox at 'Add Education' form");
		log.info("Qualifications_11 - Step 23: Enter new value to 'GPA/Score' textbox at 'Add Education' form");
		log.info("Qualifications_11 - Step 24: Pick new value from 'Start Date' datepicker at 'Add Education' form");
		log.info("Qualifications_11 - Step 25: Pick new value from 'End Date' datepicker at 'Add Education' form");

		log.info("Qualifications_11 - Step 26: Click on 'Save' button at 'Add Education' form");

		log.info("Qualifications_11 - Step 27: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Qualifications_11 - Step 28: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		log.info("Qualifications_11 - Step 29: Verify added 'Level' is updated");

	}

	@Test
	public void Employee_12_Search_Employee() {

	}

	@Parameters({ "browser" })
	@AfterClass()
	public void cleanBrowser() {
		log.info("►►►►►►►►►► Close Browsers and Drivers ►►►►►►►►►►");
		cleanDriverInstance();
	}

	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	MyInfoPO myInfoPage;
	EmployeeListPO employeeListPage;
	DataUtil fakeData;

}
