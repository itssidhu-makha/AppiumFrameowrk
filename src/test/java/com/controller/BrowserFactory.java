package com.controller;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static BrowserFactory instance =null;
    public static BrowserFactory getInstance(){
        if(instance==null){
            instance= new BrowserFactory();
        }
        return instance;
    }

    public AppiumDriver<MobileElement> getAppiumThreads() {

        return appiumThreads.get();
    }

    public void setAppiumThreads(String deviceName) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName","SM-G950FD");
        caps.setCapability("udid","ce10182a21e48b0c03");
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","9");
        caps.setCapability("appackage","com.android.chrome");
        caps.setCapability("appActivity","com.google.android.apps.chrome.Main");
        caps.setCapability("browserName","chrome");
        caps.setCapability("skipServerInstallation",true);
        appiumThreads.set(new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps));
        appiumThreads.get().manage().timeouts().implicitlyWait(Runner.IMPLICITWAITTIME, TimeUnit.SECONDS);

    }

    ThreadLocal<AppiumDriver<MobileElement>> appiumThreads = new ThreadLocal<>();
    private BrowserFactory(){

    }

}
