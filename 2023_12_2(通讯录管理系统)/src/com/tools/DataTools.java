package com.tools;

import com.pojo.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class DataTools {
    public static Document documentForxml(String userName) throws IOException, SAXException, ParserConfigurationException {
        File file = new File("userFiles" + File.separator + "contactData" + File.separator + userName + ".xml");
        Document document;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        if(file.exists()) {
            document = builder.parse(file);
        } else {
            document = builder.newDocument();

            document = builder.newDocument();
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            DataTools.saveDocument(document,userName);
            JOptionPane.showMessageDialog(null, userName + "是个未登录过的新用户，退出后程序将在磁盘创建该用户的存储文件", "提示",JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        return document;
    }

    public static void saveDocument(Document document,String userName) {
        File file = new File("userFiles" + File.separator + "contactData" + File.separator + userName + ".xml");
        try (OutputStream os = new FileOutputStream(file)) {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");

            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(os);

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] readUserData(String userName) throws IOException {
        String[] result = null;
        BufferedReader reader = new BufferedReader(new FileReader("userFiles" + File.separator + "Users.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] temp = line.split(" ");
                if(temp[0].equals(userName)) {
                    result = temp;
                    break;
                }
            }
        return result;
    }

 }
