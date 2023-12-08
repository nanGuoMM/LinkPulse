package com.dao;

import com.pojo.Contact;
import com.tools.DataTools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class ContactDao {
    public static ArrayList<Contact> selectAllContact(String userName) throws IOException, ParserConfigurationException, SAXException {
        Document document = DataTools.documentForxml(userName);
        NodeList personList = document.getElementsByTagName("person");

        ArrayList<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < personList.getLength(); i++) {
            Element presonNode = (Element) personList.item(i);

            String name = presonNode.getElementsByTagName("name").item(0).getTextContent();
            String phoneNum = presonNode.getElementsByTagName("phoneNum").item(0).getTextContent();
            String id = presonNode.getElementsByTagName("id").item(0).getTextContent();
            String sex = presonNode.getElementsByTagName("sex").item(0).getTextContent();
            String address = presonNode.getElementsByTagName("address").item(0).getTextContent();
            String email = presonNode.getElementsByTagName("email").item(0).getTextContent();
            String beiZhu = presonNode.getElementsByTagName("beiZhu").item(0).getTextContent();


            contacts.add(new Contact(name, address, email, id, phoneNum, sex, beiZhu));
        }
        return contacts;
    }

    public static void addContact(String userName, Contact contact) throws IOException, ParserConfigurationException, SAXException {
        Document document = DataTools.documentForxml(userName);

        Element person = document.createElement("person");

        Element name = document.createElement("name");
        name.setTextContent(contact.getM_Name());
        person.appendChild(name);

        Element phoneNum = document.createElement("phoneNum");
        phoneNum.setTextContent(contact.getM_PhoneNum());
        person.appendChild(phoneNum);

        Element id = document.createElement("id");
        id.setTextContent(contact.getM_Id());
        person.appendChild(id);

        Element sex = document.createElement("sex");
        sex.setTextContent(contact.getM_Sex());
        person.appendChild(sex);

        Element address = document.createElement("address");
        address.setTextContent(contact.getM_Address());
        person.appendChild(address);

        Element email = document.createElement("email");
        email.setTextContent(contact.getM_Email());
        person.appendChild(email);

        Element beiZhu = document.createElement("beiZhu");
        beiZhu.setTextContent(contact.getM_BeiZhu());
        person.appendChild(beiZhu);

        Element rootElement = document.getDocumentElement();
        rootElement.appendChild(person);

        DataTools.saveDocument(document, userName);
    }

    public static void deleteContact(String userName, String selectedName) throws IOException, ParserConfigurationException, SAXException {
        Document document = DataTools.documentForxml(userName);

        Element rootElement = document.getDocumentElement();

        NodeList childNodes = rootElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {

            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) childNodes.item(i);
                NodeList temp = childElement.getElementsByTagName("name");

                if (temp.item(0).getTextContent().equals(selectedName)) {
                    rootElement.removeChild(childNodes.item(i));
                    break;
                }
            }
        }
        DataTools.saveDocument(document, userName);
    }

    public static boolean  contactExport(String userName,ArrayList<Contact> contacts) {

        String exportPath = "outputFiles" + File.separator +userName + ".csv";

        File directory = new File("outputFiles");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(exportPath))) {

            StringBuilder builder = new StringBuilder();

            String columnNames = "姓名,手机号,性别,住址,EMAIL,备注";
            builder.append(columnNames);
            builder.append("\n");

            for (Contact row : contacts) {
                builder.append(row.getM_Name());
                builder.append(",");
                builder.append(row.getM_PhoneNum());
                builder.append(",");
                builder.append(row.getM_Sex());
                builder.append(",");
                builder.append(row.getM_Address());
                builder.append(",");
                builder.append(row.getM_Email());
                builder.append(",");
                builder.append(row.getM_BeiZhu());
                builder.append("\n");
            }

            bw.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
