package com.utils;

import com.commons.ExtentRepo;
import com.controller.BrowserFactory;

import com.controller.Runner;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;


public class SeleniumUtils {
    BrowserFactory driverInstance = BrowserFactory.getInstance();
    ExtentRepo repoInstance = ExtentRepo.getInstance();
    public void findElementAndClick(By by, String eleName){
        try {

            MobileElement ele = findAnyWebElement(by, eleName);

            clickElement(ele);
        }catch(Exception e){
            e.printStackTrace();
            repoInstance.reportFail("Could not click the element::"+eleName);
        }
    }

    private void clickElement(MobileElement ele) {
        explicitWaitForElementToBeClickable(ele);
        ele.click();
    }

    private MobileElement findAnyWebElement(By by, String eleName) {
        MobileElement ele =null;
        try {
            ele =driverInstance.getAppiumThreads().findElement(by);
            repoInstance.reportPass("Element found ::"+eleName);
            repoInstance.getReporter().flush();
        }catch (Exception e){
            repoInstance.reportFail("Could not find the element::"+eleName+"::"+e.getMessage());
            repoInstance.getReporter().flush();
        }
        return ele;

    }

    public void explicitWaitForElementVisibility(MobileElement ele){
        new AppiumFluentWait<AppiumDriver<MobileElement>>(driverInstance.getAppiumThreads()).withTimeout(Duration.ofSeconds(Runner.IMPLICITWAITTIME))
                .pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.visibilityOf(ele));
    }

    public void explicitWaitForElementToBeClickable(MobileElement ele){
        new AppiumFluentWait<AppiumDriver<MobileElement>>(driverInstance.getAppiumThreads()).withTimeout(Duration.ofSeconds(Runner.IMPLICITWAITTIME))
                .pollingEvery(Duration.ofMillis(500)).until(ExpectedConditions.elementToBeClickable(ele));
    }
}
