package com.zhzteam.zhz233.test;

import com.zhzteam.zhz233.common.utils.*;
import com.zhzteam.zhz233.model.JWTModel;
import com.zhzteam.zhz233.model.zlb.JWTResult;
import com.zhzteam.zhz233.model.zlb.SMSResult;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UtilsTest {

    @Test
    public void Test13(){
        WebDriver webDriver = Schedul4399Utils.Schedul4399("binbin201806","QQ109ZL2");
        Schedul4399Utils.closeWebDriver(webDriver);
    }

    @Test
    public void Test12(){
        System.setProperty("webdriver.gecko.driver", "G:\\Zhz233File\\Driver\\32\\geckodriver.exe");
        System.setProperty("webdriver.firefox.bin","D:\\Program Files\\Firefox\\firefox.exe");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            webDriver.get("http://www.4399.com/");
            WebElement btnLoginBox = webDriver.findElement(By.id("login_tologin"));
            btnLoginBox.click();
            webDriver.switchTo().frame(0);
            WebElement usernameBox = webDriver.findElement(By.id("username"));
            WebElement passwordBox = webDriver.findElement(By.id("password"));
            usernameBox.sendKeys("binbin201806");
            passwordBox.sendKeys("QQ109ZL2");
            WebElement submitBtn = webDriver.findElement(By.className("ptlogin_btn"));
            submitBtn.click();
        }catch (NoSuchElementException e){
            webDriver.close();
        }catch (Exception e){
            webDriver.close();
        }
    }




    @Test
    public void Test11(){
        SMSResult smsResult= new SMSResult();
        String MSG = SMSUtils.createCodeMsg("123123");
        smsResult = SMSUtils.execute("15123328416",MSG);
        System.err.println(smsResult.getRespCode());
        System.err.println(smsResult.getFailCount());
        System.err.println(smsResult.getRespDesc());
        System.err.println(smsResult.getSmsId());
    }
    @Test
    public void Test10(){
        Date b = new Date(0);
        Date c = new Date(1);
        System.err.println(c.after(b));
        System.err.println(DateTimeUtils.After(b));
    }
    @Test
    public void Test9(){
        //System.err.println(AutoIncUtils.getFileURLNo(""));
        System.err.println(REVUtils.isChinese("张三李四我aiab"));
    }
    @Test
    public void Test8(){
        System.err.println(DateTimeUtils.getPatternYMDHMS());
        System.err.println(DateTimeUtils.getCurrentTimeMillis());
        System.err.println(DateTimeUtils.getCurrentTimeMillis());
    }

    @Test
    public void Test7(){
        System.err.println(AutoIncUtils.getInvlaOrderNo("2018052500000000000001"));
    }
    @Test
    public void Test6(){
        String A = AutoIncUtils.getAccountNo("");
        System.err.println(A);
        String a = AutoIncUtils.getLeaseOrderNo("");
        System.err.println(a);
    }
    @Test
    public void Test5(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        //calendar.add(Calendar.DAY_OF_MONTH,1);
        //calendar.add(Calendar.DAY_OF_YEAR,1);
        System.err.println(df.format(calendar.getTime()));
    }
    @Test
    public void Test4(){
        Date t= DateTimeUtils.getDateTime(22,0,0);
        System.err.println(t);
        Date tt= DateTimeUtils.getDateTime(22+10,0,0);
        System.err.println(tt);
    }
    @Test
    public void Test3(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Long t = System.currentTimeMillis();
        Date tt = new Date(t);
        Date ttt = new Date(t+ DateUtils.getLongHour(2));

        System.err.println(df.format(tt));
        System.err.println(ttt);
        System.err.println(DateTimeUtils.getDatePATTERN_YMDHMS(df.format(tt)));
    }
    @Test
    public void Test2(){
        String fileName = "12312.jpg";
        String prefixName = fileName.substring(fileName.lastIndexOf("."));
        prefixName = fileName.substring(0,fileName.lastIndexOf("."));
        System.out.println(fileName.lastIndexOf("."));
        System.out.println(prefixName);
    }
    @Test
    public void Test1(){
        String autoNo = AutoIncUtils.getAccountNo("");
    }
    @Test
    public void Test0() {
        //String jwtStr = JWTUtils.createJWT(JWTConfig.JWT_ISS, "", "1", "", JWTConfig.JWT_TIME_30MINUTE);
        String jwtStr = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJaSFpUZWFtIiwiYXVkIjoienVtZW5nMjAxOCIsImp0aSI6IjIwMTgwMDAwMDAwMyIsInN1YiI6IlpIWjIzM19VU0VSX0FVVEgwIiwiaWF0IjoxNTI2MzEyMDMwLCJleHAiOjE1MjYzMTM4MzB9.hPGoOmzTXGdg8aYV42k-GhixEb0VbTorWhul7yNEvP8";
        JWTResult jwtResult = JWTUtils.validateJWT(jwtStr);
        System.err.println(jwtResult.getStatus());
        String claims = GsonUtils.objectToJsonStr(jwtResult.getClaims());
        JWTModel jwtModel = GsonUtils.jsonStrToObject(claims, JWTModel.class);
        System.err.println(jwtStr);
        System.err.println(claims);
        System.err.println(jwtModel.getJti());
    }
}
