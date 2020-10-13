package com.revature.tier6.answers;

import static com.revature.tier6.answers.PointsTests.addPoints;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * prompt: Add the index.html inside 
 * the html folder to the web.xml welcome file
 * list.
 */
public class Answer1Tests {
    private NodeList nl;
    @Before
    public void setup() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/main/webapp/WEB-INF/web.xml").getAbsolutePath());
        nl = doc.getElementsByTagName("welcome-file");

    }

    @Test
    public void test1() {
        assertTrue(nl.getLength()>0);
        String s;
        for (int i = 0; i<nl.getLength();i++){
            s=nl.item(i).getTextContent();
            System.out.println(s);
            if(s.equals("html/index.html")){
                addPoints(100);
                return;             
            }
        }
        fail();
    }
}
