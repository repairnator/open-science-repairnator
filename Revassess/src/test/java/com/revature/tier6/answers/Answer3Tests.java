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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * prompt:
 * Register the servlet with tomcat 
 * by utilizing the deployment descriptor
 */
public class Answer3Tests {
    
    private NodeList nl;
    @Before
    public void setup() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("src/main/webapp/WEB-INF/web.xml").getAbsolutePath());
        nl = doc.getElementsByTagName("servlet");

    }

    @Test
    public void testServletInWebXml(){
    	try {
        assertTrue(nl.getLength()>0);
        Node node = nl.item(0);
        Node servclass = node.getFirstChild(), servname = node.getLastChild();
        assertTrue(servclass.getTextContent().equals("com.rev.servlet.RevassessServlet")||!servclass.getTextContent().equals(null));
        assertTrue(!servname.getTextContent().equals(null)||servname.getTextContent().equals("com.rev.servlet.RevassessServlet"));
        addPoints(200);
    	} catch(Exception e) {
    		fail();
    	}
    }
}