package baseClass.Implementation.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOppPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OganizationPage;
import com.comcast.crm.objectrepositoryutility.OppLookUpPage;
import com.comcast.crm.objectrepositoryutility.OpportunityPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;

import baseUtility.Base1Class;

public class OwnTest extends Base1Class{
	@Test
	public void createOppotunityTest() throws Exception {
		
				//read test script data from excel file
				String orgName = elib.getDataFromExcel("org", 10, 2) + jlib.getRandomNumber();
				String OppName = elib.getDataFromExcel("org", 10, 3) + jlib.getRandomNumber();
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
				
				// Navigate to Opportunity module
				hp.getOpportunityLink().click();

				// click on create Opportunity button
				OpportunityPage Op=new OpportunityPage(driver);
				Op.getCreateNewOppBtn().click();

				// Enter all the details and create new Opportunity
				CreateNewOppPage CnOp=new CreateNewOppPage(driver);
				CnOp.getOppNameEdt().sendKeys(OppName);
				CnOp.getOppLookUpBtn().click();

				// switch to child window
				wlib.switchToWin();	
				OppLookUpPage oplUp=new OppLookUpPage(driver);
				oplUp.getSearchtextlookUp().sendKeys(orgName);
				oplUp.getSearchBtnlookUp().click();
				driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
				wlib.switchToParentWindow();
				CnOp.getOppSaveBtn().click();
				
				// verify header msg expected result
				String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (headerinfo.contains(OppName)) {
					System.out.println(OppName + " Opportunity header is verrified==PASS");
				} else {
					System.out.println(OppName + " Opportunity header is notverrified==FAIL");
				}

				// verify orgname info expected resulted
				String actOrgName = driver.findElement(By.xpath("//td[@class='dvtCellInfo']/a[@title='Organizations']"))
						.getText();
				if (actOrgName.contains(orgName)) {
					System.out.println(orgName + " Opportunity with org is verrified==PASS");
				} else {
					System.out.println(orgName + " Opportunity with org is not notverrified==FAIL");
				}

				// logout from application
				driver.quit();

	}

}
