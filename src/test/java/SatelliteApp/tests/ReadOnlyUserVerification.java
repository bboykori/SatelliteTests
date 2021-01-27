package SatelliteApp.tests;

import Base.BaseSeleniumTest;
import Base.helpers.MyExcelReader;
import Base.helpers.TestListener;
import SatelliteApp.pages.BasePage;
import SatelliteApp.pages.LogonPage;
import SatelliteApp.pages.tracking.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(TestListener.class)
public class ReadOnlyUserVerification extends BaseSeleniumTest {

    @Test(dataProvider="getData")
    public void readOnlyUserVerification(String username, String password)  {

        String nameSurename = "Jan Kowalski";

        //Arrenge
        testReporter.set(reports.createTest("Satellite - Read only user verification, user: "+username));
        driver.get("https://track.satellite.net.pl");
        BasePage basePage = new BasePage(driver);
        LogonPage logonPage = new LogonPage(driver);
        TopPanel topPanel = new TopPanel(driver);
        SettingsPanel_TabUser settingsPanel_tabUser = new SettingsPanel_TabUser(driver);

        //Act
        logonPage.submitCredentials(username,password);
        topPanel.goToUserSettingsPanel();
        settingsPanel_tabUser.typeNameSurename(nameSurename);
        testReporter.get().info("Typing data into field 'Imie i nazwisko' and trying to save.");
        settingsPanel_tabUser.saveSettings();

        //Assert
        testReporter.get().info("Assertion: expected alert message: 'To konto nie ma do tego uprawnień.'");
        Assert.assertEquals(basePage.getAlertMessage(),"To konto nie ma do tego uprawnień.");

    }

    @DataProvider
    public Object[][] getData() {
        Object[][] data = null;
        try {
            data = MyExcelReader.myExcelReaderForDataProvider("src\\test\\resources\\SatelliteUserTest.xlsx",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }

}
