package com.commons;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Calendar;
import java.util.Date;

public class ExtentRepo {

    public ExtentTest getReportTest() {
        return repoTest.get();
    }

    public void setTest(String testName){
        repoTest.set(reportLocal.get().startTest(testName));
    }


    public  void setReportLocalTest(String testName) {

        reportLocal.set(new ExtentReports(System.getProperty("user.dir")+"\\repo\\"+testName+".html",true));
        setTest(testName);
    }
    public ExtentReports getReporter() {
        return reportLocal.get();
    }

     ThreadLocal<ExtentReports> reportLocal = new ThreadLocal<ExtentReports>();
     ThreadLocal<ExtentTest> repoTest = new ThreadLocal<ExtentTest>();

    public static ExtentRepo instance=null;

    public static void killThreadLocals(){

       getInstance().getReporter().close();



    }

    public static void main(String[] args) {
        Date currentDate = Calendar.getInstance().getTime();
        System.out.println(currentDate);
    }

    private ExtentRepo(){

    }
    public static ExtentRepo getInstance(){
        if(instance==null){
            instance=new ExtentRepo();
        }
        return instance;
    }

    public void reportPass(String message){
        getReportTest().log(LogStatus.PASS,message);
    }
    public void reportFail(String message){
        getReportTest().log(LogStatus.FAIL,message);
    }
    public void reportWarning(String message){
        getReportTest().log(LogStatus.WARNING,message);
    }
    public void reportFatal(String message){
        getReportTest().log(LogStatus.FATAL,message);
    }



}
