package com.base;

import io.opentelemetry.sdk.common.CompletableResultCode;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public abstract class Base_Class {

    public static WebDriver driver;

    protected static WebDriver launchBrowser(String browserName ) {
        try {
            if(browserName.equalsIgnoreCase("chrome")) {
                driver =new ChromeDriver();
            }else if(browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }else if(browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
        }catch(Exception e) {
            Assert.fail("ERROR : OCCUR DURING BROWSER LAUNCH");
        }
        driver.manage().window().maximize();
        return driver;

    }
    protected static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING URL LAUNCH");
        }
    }
    protected static void clickElement(WebElement element) {
        try {
            element.click();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CLICK ELEMENT");
        }
    }
    protected static void navigateTo(String url) {
        try {
            driver.navigate().to(url);
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING NAVIGATE TO URL");
        }
    }
    protected static void navigateMethods(String type) {
        try {
            if (type.equalsIgnoreCase("back")) {
                driver.navigate().back();
            }else if(type.equalsIgnoreCase("forward")) {
                driver.navigate().forward();
            }else if(type.equalsIgnoreCase("refresh")) {
                driver.navigate().refresh();
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING NAVIGATE METHODS");
        }
    }
    protected static void passInput(WebElement element, String value) {
        try {
            element.sendKeys(value);
        }catch (Exception e) {
            throw new AssertionError("ERROR : OCCURS DURING PASS INPUT:"+e.getMessage(),e);
        }
    }
    protected static void elementClick(WebElement continueButton) {
        try {
            continueButton.click();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CLICK ELEMENT");
        }
    }
    protected static void close() {
        try {
            driver.close();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CLOSE CURRENT TAB");
        }
    }
    protected static void quit() {
        try {
            driver.quit();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING TERMINATE BROWSER");
        }
    }
    protected static void webElementIsEnabled(WebElement element) {
        try {
            System.out.println(element.isEnabled());
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CHECK WEBELEMENT ISENABLED");
        }
    }
    protected static void webElementIsDisplayed(WebElement element) {
        try {
            System.out.println(element.isDisplayed());
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CHECK WEBELEMENT ISDISPLAYED");
        }
    }
    protected static void webElementIsSelected(WebElement element) {
        try {
            System.out.println(element.isSelected());
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CHECK WEBELEMENT ISSELECTED");
        }
    }
    protected static void getText(WebElement element) {
        try {
            element.getText();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING GETTEXT FOR WEBELEMENT");
        }
    }
    protected static void getTitle() {
        try {
            System.out.println(driver.getTitle());
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING GETTITLE FOR WEBPAGE");
        }
    }
    protected static void getCurrentUrl() {
        try {
            System.out.println(driver.getCurrentUrl());
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING GETCURRENTURL FOR WEBPAGE");
        }
    }
    protected static void alertMethods(String type,String value) {
        Alert alert =driver.switchTo().alert();
        try {
            if(type.equalsIgnoreCase("accept")) {
                alert.accept();
            }else if(type.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
            }else if(type.equalsIgnoreCase("gettext")) {
                alert.getText();
            }else if(type.equalsIgnoreCase("sendkeys")) {
                alert.sendKeys(value);
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING ALERT FUNCTIONS");
        }
    }
    protected static void dropDownMethods(WebElement element,String type,String value,String text) {
        Select select = new Select(element);
        try {
            if(type.equalsIgnoreCase("selectbyvalue")) {
                select.selectByValue(value);
            }else if(type.equalsIgnoreCase("selectbyindex")) {
                select.selectByIndex(Integer.parseInt(value));
            }else if(type.equalsIgnoreCase("selectbyvisibletext")) {
                select.selectByVisibleText(text);
            }else if(type.equalsIgnoreCase("deselectbyindex")) {
                select.deselectByIndex(Integer.parseInt(value));
            }else if(type.equalsIgnoreCase("deselectbyvisibletext")) {
                select.deselectByVisibleText(text);
            }else if(type.equalsIgnoreCase("deselectbyvalue")) {
                select.deselectByValue(value);
            }else if(type.equalsIgnoreCase("ismultiple")) {
                System.out.println(select.isMultiple());
            }else if(type.equalsIgnoreCase("getoptions")) {
                select.getOptions();
            }else if(type.equalsIgnoreCase("getallselectedoptions")) {
                select.getAllSelectedOptions();
            }else if(type.equalsIgnoreCase("getfirstselectedoption")) {
                select.getFirstSelectedOption();
            }else if(type.equalsIgnoreCase("deselectall")) {
                select.deselectAll();
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING DROPDOWN FUNCTIONS");
        }
    }
    protected static void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
    protected static void explicitWaitVisibilityOf(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
        try {
               wait.until(ExpectedConditions.visibilityOf(element));
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING VISIBILITYOF EXPLICIT WAIT METHODS");
        }
    }
    protected static void explicitWaitElementToBeClickable(WebElement element,int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING ELEMENTTOBECLICKABLE EXPLICIT WAIT");
        }
    }

    protected static void actionsMethod(WebElement element, WebElement source, String type) {
        Actions actions = new Actions(driver);
        try {
            if(type.equalsIgnoreCase("click")) {
                actions.click().perform();
            }else if(type.equalsIgnoreCase("contextclick")) {
                actions.contextClick(element).perform();
            }else if(type.equalsIgnoreCase("doubleclick")) {
                actions.doubleClick(element).perform();
            }else if(type.equalsIgnoreCase("contextclick")) {
                actions.contextClick(element).perform();
            }else if(type.equalsIgnoreCase("movetoelement")) {
                actions.moveToElement(element).perform();
            }else if(type.equalsIgnoreCase("draganddrop")) {
                actions.dragAndDrop(element, source).perform();
            }else if(type.equalsIgnoreCase("clickandhold")) {
                actions.clickAndHold(element).perform();
            }else if(type.equalsIgnoreCase("release")) {
                actions.release(element).perform();
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING ACTIONS METHOD");
        }
    }
    protected static void robotMethods(String type,int key) throws AWTException {
        Robot robot = new Robot();
        try {
            if(type.equalsIgnoreCase("keypress")) {
                robot.keyPress(KeyEvent.VK_ENTER);
            }else if(type.equalsIgnoreCase("keyrelease")) {
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING ROBOT METHODS");
        }
    }
    protected static void checkBox(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CLICK CHECKBOX");
        }
    }
    protected static void radioButton(WebElement element) {
        try {
            element.click();
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING CLICK RADIO BUTTON");
        }
    }
    protected static void windowHandling(String type, int num) {
        try {
            if(type.equalsIgnoreCase("getwindowhandle")) {
                driver.getWindowHandle();
            }else if (type.equalsIgnoreCase("getwindowhandles")) {
                Set<String> windows = driver.getWindowHandles();
                List<String> windowList = new ArrayList<>(windows);
                driver.switchTo().window(windowList.get(num));
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING WINDOW HANDLING");
        }
    }
    protected static void takesScreenshot(String filePath) throws IOException {
        TakesScreenshot screenShot = (TakesScreenshot) driver;
        try {
            File source = screenShot.getScreenshotAs(OutputType.FILE);
            File destination = new File(filePath);
            FileHandler.copy(source,destination);
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING TAKES SCREENSHOT");
        }
    }
    protected static void frameMethods(String type, WebElement element, int index, String nameOrId) {
        try {
            if(type.equalsIgnoreCase("switchtoframebyelement")) {
                driver.switchTo().frame(element);
            }else if(type.equalsIgnoreCase("switchtoframebyindex")) {
                driver.switchTo().frame(index);
            }else if(type.equalsIgnoreCase("switchtoframebynameorid")) {
                driver.switchTo().frame(nameOrId);
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING FRAME METHODS");
        }
    }
    protected static void scroll(String type) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            if(type.equalsIgnoreCase("scrolldown")) {
                js.executeScript("window.scrollBy(0,1000);");
            }else if(type.equalsIgnoreCase("scrollup")) {
                js.executeScript("window.scrollby(0,-3000);");
            }
        }catch (Exception e) {
            Assert.fail("ERROR : OCCURS DURING SCROLL");
        }
    }


}
