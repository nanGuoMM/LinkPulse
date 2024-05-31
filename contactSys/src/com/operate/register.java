package com.operate;


import javax.swing.*;

import com.dao.UserDao;
import com.pojo.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class register extends JFrame implements ActionListener {
    JPanel p1, p2;
    Font f1;
    JLabel username = new JLabel("用户名");
    JLabel userpsd = new JLabel("密码");
    JLabel userpsdconfirm = new JLabel("确认密码");
    JLabel useraddress = new JLabel("地址");
    JLabel userphone = new JLabel("手机号");
    JLabel useremail = new JLabel("电子邮箱");

    JTextField usernameinput = new JTextField(10);
    JPasswordField userpsdinput = new JPasswordField(10);
    JPasswordField userpsdconfirminput = new JPasswordField(10);
    JTextField useraddressinput = new JTextField(10);
    JTextField userphoneinput = new JTextField(10);
    JTextField useremailinput = new JTextField(10);

    JButton submit = new JButton("提交");
    JButton reset = new JButton("重置");

    JLabel[] jLabels = {username, userpsd, userpsdconfirm, 
    		useraddress, userphone, useremail};

//    public register(int userid) {
    public register() {
        super("注册页面");
        setLayout(new FlowLayout());
        f1 = new Font("宋体", Font.BOLD, 20);
        p1 = new JPanel();
        p2 = new JPanel();
        p1.setLayout(new GridLayout(7, 2, 10, 10));

        for (int i = 0; i < jLabels.length; i++) {
            jLabels[i].setFont(f1);
        }
        p1.add(username);
        p1.add(usernameinput);
        p1.add(userpsd);
        p1.add(userpsdinput);
        p1.add(userpsdconfirm);
        p1.add(userpsdconfirminput);
        p1.add(useraddress);
        p1.add(useraddressinput);
        p1.add(userphone);
        p1.add(userphoneinput);
        p1.add(useremail);
        p1.add(useremailinput);

        p2.add(submit);
        p2.add(new JLabel("    "));
        p2.add(reset);

        p2.setLayout(new FlowLayout());

        submit.addActionListener(this);
        reset.addActionListener(this);

        this.add(p1);
        this.add(p2);

        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            if(!usernameinput.getText().matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "用户名不正确！",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if(!String.valueOf(userpsdinput.getPassword()).matches("^[a-zA-Z0-9]+$")) {
                System.out.println(userpsdinput.getPassword());
                JOptionPane.showMessageDialog(null, "密码不正确！",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!Arrays.equals(userpsdconfirminput.getPassword(),userpsdinput.getPassword())) {
                JOptionPane.showMessageDialog(null, "两次密码不一样",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;             
            } else if (useraddressinput.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "地址不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!userphoneinput.getText().matches("^[0-9]+$")) {
                JOptionPane.showMessageDialog(null, "电话号码不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            } else if (!useremailinput.getText().matches("^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "邮箱不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
                return;
            }
            UserDao.addUser(new User(usernameinput.getText(),String.valueOf(userpsdinput.getPassword()),
                    useraddressinput.getText(),userphoneinput.getText(),useremailinput.getText()));
            this.dispose();
        }
        this.usernameinput.setText("");
        this.userpsdinput.setText("");
        this.userpsdconfirminput.setText("");
        this.useraddressinput.setText("");
        this.userphoneinput.setText("");
        this.useremailinput.setText("");
    }
}
