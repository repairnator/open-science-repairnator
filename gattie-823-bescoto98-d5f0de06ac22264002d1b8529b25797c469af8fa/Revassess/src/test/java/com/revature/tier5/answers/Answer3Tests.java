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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * prompt: Dynamically render a flashcard's information obtained from the
 * provided endpoint to the screen
 */
public class Answer3Tests {
    private WebDriver wd;
    Map<String, List<String>> jsonMap;

    @Before
    public void pageSetup() {
        System.setProperty("webdriver.gecko.driver", "src/assets/geckodriver.exe");
        wd = new FirefoxDriver();
        File html = Paths.get("src/main/webapp/html/index.html").toFile();
        wd.navigate().to("file://" + html.getAbsolutePath());
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
    public void testContent() {
    	try {
        Map<String, String> elMap= new HashMap<>();
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardId').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardQstn').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardAns').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardCat').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("manipDom()");
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cardQstn']")));
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='cardId']|//*[@id='cardQstn']|//*[@id='cardAns']|//*[@id='cardCat']"));
        elements.stream().forEach(e-> elMap.put(e.getAttribute("id"), e.getText()));
        wd.close();
        elMap.keySet().stream().forEach(e->assertTrue(jsonMap.get(e).contains(elMap.get(e))));
        addPoints(30);
    	} catch(Exception e) {
    		fail();
    	}
    }

}