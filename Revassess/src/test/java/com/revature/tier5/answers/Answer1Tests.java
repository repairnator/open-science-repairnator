package com.revature.tier5.answers;

import static com.revature.tier5.answers.PointsTests.addPoints;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

/**
 * prompt:
 * Use the index HTML file 
 * and include the Bootstrap 
 * CDN link and script tags, 
 * as well as a script tag 
 * that imports the index.js file.
 */
public class Answer1Tests {

    String sources;
    @Before
    public void setup(){
            sources="https://code.jquery.com/jquery-[0-9].[0-9].[0-9].slim.min.js|"+
                "https://cdn.jsdelivr.net/npm/popper.js@[0-9].[0-9]+.[0-9]/dist/umd/popper.min.js|"+
                "https://stackpath.bootstrapcdn.com/bootstrap/[0-9].[0-9].[0-9]/js/bootstrap.min.js|"+
                "js/index.js|"+
                "../js/index.js";
    }
    @Test
    public void testScriptTags() {
        try {
            Document html = Jsoup.parse(new File("src/main/webapp/html/index.html"), "UTF-8");
            Elements scripts = html.getElementsByTag("script");
            assertEquals(3, scripts.size());
            scripts.forEach(e->assertTrue(e.attr("src").matches(sources)));
        } catch (Exception e) {
            fail();
        }
        addPoints(10);
    }

}