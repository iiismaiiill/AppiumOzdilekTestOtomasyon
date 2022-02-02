import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StepImplementation extends BaseTest{



    Logger logger = LogManager.getLogger(StepImplementation.class);

    @Step("<time> saniye kadar bekle")
    public void waitForseconds(int time) throws InterruptedException {
        Thread.sleep(time * 1000);

    }

    @Step("id <id> li emente tıkla")
    public void clickByid(String id) {
        appiumDriver.findElement(By.id(id)).click();
        logger.info("Elemana tiklandi.");

    }
    @Step("xpath <xpath> li emente tıkla")
    public void clickByXpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
        logger.info("Elemana tiklandi.");

    }

    @Step("id <id> li ementi bul ve <text> değerini yaz")
    public void sendkeysByid(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info("Ement bulundu  ve -> "+text+" değeri yazildi.");
    }

    @Step("Android klavyeyi kapat")
    public void closeKeyboardonAndroid() {
        appiumDriver.hideKeyboard();


    }

    @Step("Sayfayı sola kaydır")
    public void swipeLeft() {
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
        logger.info("sayfa sola kaydirildi. ");


    }
    @Step("Sayfayı aşağı kaydır")
    public void swipeUp() {
        final int ANIMATION_TIME = 2000; // ms

        final int PRESS_TIME = 2000; // ms

        int edgeBorder = 2; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2,edgeBorder);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
        logger.info("Sayfa asagi kaydirildi.");

    }

    @Step("id <id> li elementi bul ve <text> alanını kontrol et")
    public void textIdAreacontrol(String id, String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));
        logger.info("uygulama kontrolu  -> " + text+ " yapildi  ve dogrulandi.");
    }
    @Step("xpath <xpath> li elementi bul ve <text> alanını kontrol et")
    public void textXpathAreacontrol(String xpath, String text) {
        Assert.assertTrue("Element text değerini içermiyor", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
        logger.info("Element bulundu -> "+text +" kontrol dogrulandi.");
    }

    @Step("Android klavyeden arama tuşuna bas")
    public void clickSearchbuttonOnadnroidKeyboard() {
        ((JavascriptExecutor) appiumDriver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        logger.info("Klavyeden arama butonuna basildi.");
    }

    @Step("Elementler <id> arasından rasgele bir tanesini seç ve tıkla")
    public void clickRandomelement(String id) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.id(id));
        int index = random.nextInt(products.size());
        products.get(index).click();
        logger.info("Elementler arasindsan rastgele biri secildi tiklandi.");
    }

    @Step("Elementleri <xpath> arasından rasgele bir tanesini seç ve tıkla")
    public void clickRandomXpathelement(String xpath) {
        Random random = new Random();
        List<MobileElement> products = appiumDriver.findElements(By.xpath(xpath));
        int index = random.nextInt(products.size());
        products.get(index).click();
        logger.info("Elementler arasindsan rastgele biri secildi tiklandi.");

    }




}
