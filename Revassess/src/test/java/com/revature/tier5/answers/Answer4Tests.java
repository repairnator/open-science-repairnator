package com.revature.tier5.answers;

import static com.revature.tier5.answers.PointsTests.addPoints;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.AssertionFailedError;

/**
 * prompt: Create a Bootstrap carousel that will allow users to click through
 * different flash cards one at a time
 */
public class Answer4Tests {

    private WebDriver wd;
    Map<String, List<String>> jsonMap;

    @Before
    public void pageSetup() {
        System.setProperty("webdriver.gecko.driver", "src/assets/geckodriver.exe");
        wd = new FirefoxDriver();
        File html = Paths.get("src/main/webapp/html/index.html").toFile();
        wd.navigate().to("file://" + html.getAbsolutePath());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Before
    public void answerSetup() {
        List<String> jsonQues = new ArrayList<>();
        jsonQues.add("core java question");
        jsonQues.add("java reflection question");
        jsonQues.add("java collections question");
        List<String> jsonAns = new ArrayList<>();
        jsonAns.add("dummy answer");
        List<String> jsonCat = new ArrayList<>();
        jsonCat.add("core java");
        jsonCat.add("java reflection");
        jsonCat.add("java collections");
        List<String> jsonIds = new ArrayList<>();
        jsonIds.add("1");
        jsonIds.add("2");
        jsonIds.add("3");
        jsonMap = new HashMap<>();
        jsonMap.put("cardId", jsonIds);
        jsonMap.put("cardQstn", jsonQues);
        jsonMap.put("cardAns", jsonAns);
        jsonMap.put("cardCat", jsonCat);
    }

    @Test
    public void testCarousel() throws InterruptedException {
    	try {
        for(int i = 0; i<=2; i++){
            next();
            Thread.sleep(3000);
            assertTrue(checkElements());
        }
        wd.close();
        addPoints(40);
    	} catch(Exception e) {
    		fail();
    	}
    }

    private Map<String, String> refreshElements(){
        WebElement we = wd.findElement(By.xpath("//*[@class='carousel-item active']"));
        List<WebElement> list = we.findElements(By.xpath("./*[@id='cardId']|./*[@id='cardQstn']|./*[@id='cardAns']|./*[@id='cardCat']"));
        Map<String, String> elements = new HashMap<>();
        list.stream().forEach(e->elements.put(e.getAttribute("id"), e.getText()));
        return elements;
    }

    private boolean checkElements(){
        Map<String, String> map = refreshElements();
        map.keySet().stream().forEach(e->{
            if(jsonMap.get(e).contains(map.get(e))){

            } else {
                throw new AssertionFailedError("the fields inside the carousel are incorrect");
            }
        });
        return true;
    }

    private void next(){
        wd.findElement(By.xpath("//*[@class='carousel-control-next']")).click();
    }
}