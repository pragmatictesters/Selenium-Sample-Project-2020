package com.pragmatic.workshop;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ReadXMLExample {


    @Test (dataProvider = "user_credentials")
    public void testReadXMLDDT(JSONObject jsonObject){
        System.out.println("jsonObject = " + jsonObject);
    }




    @Test
    public void testReadXML(){
        readXML();
    }



    @DataProvider(name = "user_credentials")
    public Object[][] user_credentials(){
        return readXML();
    }



    public JSONObject[][] readXML() {
        JSONObject[][] data = null;

        try {
            String fileName = "src/test/resources/user_credentials.xml";
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(fileName);


            NodeList nodeList = document.getElementsByTagName("row");
            data = new JSONObject[nodeList.getLength()][1];
            Node node = nodeList.item(0);
            Element element = (Element) node;

            for (int i = 0; i < nodeList.getLength(); i++) {

                 JSONObject jsonObject = new JSONObject();
                 node = nodeList.item(i);
                 element = (Element) node;

                NodeList  nodeList1 = element.getChildNodes();
                for (int k=0; k < element.getChildNodes().getLength(); k ++){

                    if (nodeList1.item(k).getNodeName()!="#text") {
                        String nodeName = nodeList1.item(k).getNodeName();
                        jsonObject.put(nodeName, element.getElementsByTagName(nodeName).item(0).getTextContent() );

                    }
                }
                data[i][0] = jsonObject;
            }
            return data;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
