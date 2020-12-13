package com.controller;

import com.commons.ExtentRepo;
import com.constants.LoginPage;

import com.relevantcodes.extentreports.ExtentTest;

import com.utils.SeleniumUtils;
import io.appium.java_client.MobileBy;

import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.net.MalformedURLException;


public class BaseTest {

    BrowserFactory instance  = BrowserFactory.getInstance();
    ExtentRepo repoInstance = ExtentRepo.getInstance();
    SeleniumUtils util = new SeleniumUtils();
    ExtentTest test=null;

    @BeforeTest
    @Parameters
    public void initiate() throws MalformedURLException {
        instance.setAppiumThreads("");

    }

    @BeforeClass
    @Parameters({"TestRunning","SheetRunning"})
    public void setReporter(@Optional("Test") String testRunnning,@Optional("Test") String sheetRunning) throws MalformedURLException {

         repoInstance.setReportLocalTest(testRunnning);
         test = repoInstance.getReportTest();

    }

    @Test
    @Parameters({"TestRunning","SheetRunning"})
    public void test1(@Optional("Test") String testRunnning,@Optional("Test") String sheetRunning){
        instance.getAppiumThreads().get("https://www.amazon.com");

        util.findElementAndClick(By.xpath("//*[contains(@class,'nav-show-sign-in')]"),"Sign In Link");
        util.findElementAndClick(By.xpath("//*[contains(@id,'continue-announce')]"),"Click Continue Announce");
        repoInstance.getReporter().endTest(test);

    }

    @AfterTest
    public void killProcesses(){
        instance.getAppiumThreads().close();
        repoInstance.killThreadLocals();
    }
}
