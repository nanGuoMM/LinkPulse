package com.operate;

import com.dao.ContactDao;
import com.pojo.Contact;
import com.tools.DataTools;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddContact extends JFrame implements ActionListener {
    private final String username;
    JPanel p1, p2;
    Font f1;
    JLabel contactname = new JLabel("姓名");
    JLabel contactphone = new JLabel("手机号");
    JLabel contactsex = new JLabel("性别");
    JLabel contactaddress = new JLabel("住址");
    JLabel contactemail = new JLabel("email");
    JLabel contactbeizhu = new JLabel("备注");

    //输入框
    JTextField contactnameinput = new JTextField(10);
    JTextField contactphoneinput = new JTextField(10);
    JTextField contactsexinput = new JTextField(10);
    JTextField contactaddressinput = new JTextField(10);
    JTextField contactemailinput = new JTextField(10);
    JTextField contactbeizhuinput = new JTextField(10);


    JButton submit = new JButton("添加");
    JButton reset = new JButton("重置");

    public AddContact(String userName) {
        super("添加联系人");
        this.username = userName;
        setLayout(new FlowLayout());
        f1 = new Font("宋体", Font.BOLD, 20);
        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new GridLayout(8, 2, 10, 10));

        JLabel[] jLabels = {contactname, contactphone, contactsex,
                contactaddress, contactemail, contactbeizhu};
        for (JLabel jLabel : jLabels) {
            jLabel.setFont(f1);
        }

        p1.add(contactname);
        p1.add(contactnameinput);
        p1.add(contactphone);
        p1.add(contactphoneinput);
        p1.add(contactsex);
        p1.add(contactsexinput);
        p1.add(contactaddress);
        p1.add(contactaddressinput);
        p1.add(contactemail);
        p1.add(contactemailinput);
        p1.add(contactbeizhu);
        p1.add(contactbeizhuinput);


        submit.addActionListener(this);
        reset.addActionListener(this);

        p2.add(submit);
        p2.add(new JLabel("    "));
        p2.add(reset);

        this.add(p1);
        this.add(p2);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submit) {//点击添加
            String name = contactnameinput.getText();
            String phone = contactphoneinput.getText();
            String sex = contactsexinput.getText();
            String id = null;
            try {
                id = DataTools.documentForxml(username).getElementsByTagName("person").getLength()+1 + "";
            } catch (IOException | SAXException | ParserConfigurationException ex) {
                throw new RuntimeException(ex);
            }
            String address = contactaddressinput.getText();
            String email = contactemailinput.getText();
            String beizhu = contactbeizhuinput.getText();
            if(!name.matches("^[a-zA-Z一-龥]+$")) {
                JOptionPane.showMessageDialog(null, "姓名不正确！",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!phone.matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(null, "电话号码不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!sex.equals("男") && !sex.equals("女")) {
                JOptionPane.showMessageDialog(null, "性别不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (address.isEmpty()) {
                JOptionPane.showMessageDialog(null, "地址不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!email.matches("^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "邮箱不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ContactDao.addContact(username,new Contact(name,address,email,id,phone,sex,beizhu));
            } catch (IOException | ParserConfigurationException | SAXException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null, "联系人添加成功", "提示",JOptionPane.PLAIN_MESSAGE);
            this.dispose();
        } else {
            contactnameinput.setText("");
            contactphoneinput.setText("");
            contactsexinput.setText("");
            contactaddressinput.setText("");
            contactemailinput.setText("");
            contactbeizhuinput.setText("");
        }

    }
}
