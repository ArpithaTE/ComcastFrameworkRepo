package baseClass.Implementation.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OganizationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
@Listeners(com.comcast.crm.listenerutility.ListernerImplimentationClass.class)
public class OrganizationTest extends BaseClass {
		@Test
		public void createOrgTest() throws Exception {

			// read test script data from excel file
			String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();
			wlib.waitForPageToLoad();
			// Navigate to Organization module
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();

			// click on create organization button
			OganizationPage op = new OganizationPage(driver);
			op.getCreateNewOrgButton().click();

			// Enter all the details and create new organization
			CreatingNewOrganizationPage newOrg = new CreatingNewOrganizationPage(driver);
			newOrg.createOrgn(orgName);

			// verify header msg expected result
			OrganizationInfoPage orgpage = new OrganizationInfoPage(driver);
			String actOgName = orgpage.getHeaderText().getText();
			if (actOgName.contains(orgName)) {
				System.out.println(orgName + "header info is verified==PASS");
			} else {
				System.out.println(orgName + "header info is notverified==FAIL");
			}
		}

		@Test
		public void CreateOrgwithIndustry() throws Exception {

			// read test script data from excel file
			String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
			String industry = elib.getDataFromExcel("org", 4, 3);
			String type = elib.getDataFromExcel("org", 4, 4);
			wlib.waitForPageToLoad();

			// Navigate to Organization module
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();

			// click on create organization button
			OganizationPage op = new OganizationPage(driver);
			op.getCreateNewOrgButton().click();
			// Enter all the details and create new organization
			CreatingNewOrganizationPage newOrg = new CreatingNewOrganizationPage(driver);
			newOrg.createOrgn(orgName, industry, type);
			// verify header msg expected result
			OrganizationInfoPage orgpage = new OrganizationInfoPage(driver);
			String actOgName = orgpage.getHeaderText().getText();
			if (actOgName.contains(orgName)) {
				System.out.println(orgName + "header info is verified==PASS");
			} else {
				System.out.println(orgName + "header info is notverified==FAIL");
			}

			// verify Industry
			String actIndus = orgpage.getIndustryText().getText();
			if (actIndus.contains(industry)) {
				System.out.println(industry + " info is verified==PASS");
			} else {
				System.out.println(industry + " info is notverified==FAIL");
			}
			// verify type
			String actType = orgpage.getTypeText().getText();
			if (actType.contains(type)) {
				System.out.println(type + "header info is verified==PASS");
			} else {
				System.out.println(type + "header info is notverified==FAIL");
			}
		}

		@Test
		public void CreateOrgwithPhNum() throws Exception {
			// read test script data from excel file
			String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
			String phonenum = elib.getDataFromExcel("org", 7, 3);
			wlib.waitForPageToLoad();

			// Navigate to Organization module
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();
			// click on create organization button
			OganizationPage op = new OganizationPage(driver);
			op.getCreateNewOrgButton().click();

			// Enter all the details and create new organization
			CreatingNewOrganizationPage newOrg = new CreatingNewOrganizationPage(driver);
			newOrg.createOrgn(orgName, phonenum);

			// verify phoneNumber
			OrganizationInfoPage orgpage = new OrganizationInfoPage(driver);
			String actPhNum = orgpage.getPhoneText().getText();
			if (actPhNum.contains(phonenum)) {
				System.out.println(phonenum + "header info is verified==PASS");
			} else {
				System.out.println(phonenum + "header info is notverified==FAIL");
			}
		}
	}
