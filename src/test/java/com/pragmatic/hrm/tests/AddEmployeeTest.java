package com.pragmatic.hrm.tests;


import com.pragmatic.hrm.HRMTestBase;
import com.pragmatic.hrm.pages.AddNewEmployeePage;
import com.pragmatic.hrm.pages.MenuPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class AddEmployeeTest extends HRMTestBase {


    @Test
    public void addNewEmployeeWithMandatoryInformation() {
        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("Janesh").typeLastname("Kodikara").clickSaveWithSuccess();
    }


    @Test
    public void addNewEmployeeWithUserCredentialsEnabled() {
        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("Janesh").typeLastname("Kodikara").checkCreateLoginDetails().selectStatus("Enabled");
        addNewEmployeePage.clickSaveWithSuccess();

    }


    @Test
    public void addNewEmployeeWithUserCredentialsDisabled() {
        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("Janesh").typeLastname("Kodikara").checkCreateLoginDetails().selectStatus("Disabled");
        addNewEmployeePage.clickSaveWithSuccess();

    }


    @Test
    public void addNewEmployeeWithoutFirstName() {
        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("").typeLastname("Kodikara").clickSaveWithError();
        Assert.assertEquals(addNewEmployeePage.getFirstNameError(), "Required");
    }


    @Test
    public void addNewEmployeeWithoutLastName() {
        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("").typeLastname("").clickSaveWithError();
        Assert.assertEquals(addNewEmployeePage.getFirstNameError(), "Required");
        Assert.assertEquals(addNewEmployeePage.getLastNameError(), "Required");
    }


    @Test
    public void addNewEmployeeWithProfilePicture() {

        login("Admin", "Ptl@#321");
        MenuPage menuPage = PageFactory.initElements(webDriver, MenuPage.class);
        AddNewEmployeePage addNewEmployeePage = menuPage.navigateToAddEmployeePage(webDriver);
        addNewEmployeePage.typeFirstname("janesh").typeLastname("kodikara")
                .uplaodProfile("src/test/resources/good-pic.png")
                .clickSaveWithSuccess();
        System.out.println("pause");
    }


}
