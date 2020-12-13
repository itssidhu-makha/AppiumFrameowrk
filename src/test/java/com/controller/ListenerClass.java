package com.controller;


import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Method failed"+ result.getName());

    }
}
