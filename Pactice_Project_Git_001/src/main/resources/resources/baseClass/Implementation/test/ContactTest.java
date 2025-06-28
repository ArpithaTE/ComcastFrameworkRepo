package baseClass.Implementation.test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CrNwContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OganizationPage;
import com.comcast.crm.objectrepositoryutility.OrgLookUpPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;

public class ContactTest extends BaseClass {
	@Test
	public void createContactTest() throws Exception {
		// read test script data from excel file
		String lastname = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();
		// Navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// click on create organization button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Enter all the details and create new organization
		CrNwContactPage ncp = new CrNwContactPage(driver);
		ncp.getLastNameEdt().sendKeys(lastname);
		ncp.getSaveContactBtn().click();

		// verify org
		ContactInfoPage conInfo = new ContactInfoPage(driver);
		String ActlastName = conInfo.getLastNameText().getText();
		if (ActlastName.contains(lastname)) {
			System.out.println(lastname + " Info is Veified==PASS");
		} else {
			System.out.println(lastname + " Info is NotVeified==FAIL");
		}
	}

	@Test
	public void createContactWithSupportDate() throws Exception {

		String lastname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// Synchronize using Implicitly wait
		wlib.waitForPageToLoad();

		// Navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// click on create new contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		// Enter all the details and create new organization
		CrNwContactPage ncp = new CrNwContactPage(driver);
		ncp.getLastNameEdt().sendKeys(lastname);
		ncp.getStaretDate().clear();
		ncp.getStaretDate().sendKeys(jlib.getCurrentDateYYYYDDMM());
		ncp.getEndDate().clear();
		ncp.getEndDate().sendKeys(jlib.getRequiredDateYYYYDDMM(30));
		ncp.getSaveContactBtn().click();

		String curDate = jlib.getCurrentDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);

		// verify orgname in
		ContactInfoPage conInfo = new ContactInfoPage(driver);
		String ActStartDate = conInfo.getStartDateText().getText();
		if (ActStartDate.contains(curDate)) {
			System.out.println(curDate + " Info is Veified==PASS");
		} else {
			System.out.println(curDate + " Info is NotVeified==FAIL");
		}

		String ActEndDate = conInfo.getEndDateText().getText();
		if (ActEndDate.contains(endDate)) {
			System.out.println(endDate + " Info is Veified==PASS");
		} else {
			System.out.println(endDate + " Info is NotVeified==FAIL");
		}
	}


	@Test
	public void createContactwtOrgTest() throws Exception {
		//read test script data from excel file
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastname = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		//Synchronize using Implicitly wait
		wlib.waitForPageToLoad();

		//Navigate to Organization module
		HomePage hp= new HomePage(driver);
		hp.getOrgLink().click();

		//click on create organization button
		OganizationPage orgP= new OganizationPage(driver);
		orgP.getCreateNewOrgButton().click();

		//Enter all the details and create new organization
		CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
		cnop.getOrgnameEdt().sendKeys(orgName);
		cnop.getSaveBtn().click();

		//verify header msg expected result
		OrganizationInfoPage orgInfoPage=new OrganizationInfoPage(driver);
		String actHeadeInfo = orgInfoPage.getHeaderText().getText();
		if(actHeadeInfo.contains(orgName)) {
			System.out.println(orgName+" header is Verified==PASS");			
		}else {
			System.out.println(orgName+" header is not Verified==FAIL");
		}

		//Navigate to Contacts module
		hp.getContactLink().click();

		//click on create organization button
		ContactPage cp= new ContactPage(driver);
		cp.getCreateNewContactBtn().click();

		//Enter all the details and create new organization
		CrNwContactPage cncp=new CrNwContactPage(driver);
		cncp.getLastNameEdt().sendKeys(lastname);
		cncp.getOrgLookUpBtn().click();

		//switch to child window
		wlib.switchToWin();	
		OrgLookUpPage OLU=new OrgLookUpPage(driver);
		OLU.getSearchtextlookUp().sendKeys(orgName);
		OLU.getSearchBtnlookUp().click();
						 
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		//switch to parent window
		wlib.switchToParentWindow();
		//wlib.switchToTabOnURL(driver, "Contacts&parenttab");


		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();


		//verify header msg expected result
		ContactInfoPage cip=new ContactInfoPage(driver);
		String headerinfo = cip.getContactHeaderText().getText();
		if(headerinfo.contains(lastname)) {
			System.out.println(lastname+" contact is Created==PASS");			
		}else {
			System.out.println(lastname+" contact is not created==FAIL");
		}

		//verify hader orgname info expected resulted
		String actOrgName = cip.getOrgText().getText();	
		if(actOrgName.contains(orgName)) {
			System.out.println(orgName+" contact with org is Created==PASS");			
		}else {
			System.out.println(orgName+" contact with org is not created==FAIL");
		}
	}}
		
