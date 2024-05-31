package com.operate;


import javax.swing.*;

import com.dao.UserDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame implements ActionListener {

    int userid;
    JPanel p1, p2, p3;
    Font f1, f2, f3, f4;

    JLabel head1 = new JLabel("欢迎使用通讯录管理系统");

    JLabel username = new JLabel("用户账号");
    JLabel userpsd = new JLabel("用户密码");

    JTextField username_input = new JTextField(10);
    JPasswordField userpsd_input = new JPasswordField(10);

    JButton login = new JButton("登录");
    JButton rest = new JButton("取消");
    JButton register = new JButton("注册");

    public Login() {
        super("通讯录管理系统");
        setLayout(new FlowLayout());
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        f1 = new Font("宋体", Font.BOLD, 20);
        f2 = new Font("幼圆", Font.ITALIC, 30);
        f3 = new Font("楷书", Font.BOLD, 18);
        f4 = new Font("隶书", Font.PLAIN, 40);

        head1.setForeground(Color.blue);
        head1.setFont(f2);
        p1.add(head1);

        p2.setLayout(new GridLayout(2, 2, 5, 5));
        username.setFont(f1);
        p2.add(username);
        p2.add(username_input);
        userpsd.setFont(f1);
        p2.add(userpsd);
        p2.add(userpsd_input);

        login.addActionListener(this);
        register.addActionListener(this);
        rest.addActionListener(this);

        p3.add(login);
        p3.add(new JLabel("    "));
        p3.add(rest);
        p3.add(new JLabel("    "));
        p3.add(register);


        this.add(p1);
        this.add(p2);
        this.add(p3);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login) {
            boolean b = UserDao.checkUser(username_input.getText(), String.valueOf(userpsd_input.getPassword()));
            if(!b) {
                JOptionPane.showMessageDialog(null, "账号或密码不正确",
                        "友情提醒",JOptionPane.WARNING_MESSAGE);
            } else {
                new Home(username_input.getText());
                this.dispose();
            }
        } else if (e.getSource() == rest){
            username_input.setText("");
            userpsd_input.setText("");
        } else {
            new register();
        }
    }

}
