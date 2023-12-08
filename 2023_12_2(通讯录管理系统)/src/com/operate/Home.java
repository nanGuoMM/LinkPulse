package com.operate;

import javax.swing.*;
import javax.swing.table.*;

import com.dao.ContactDao;
import com.pojo.Contact;
import com.tools.DataTools;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Home extends JFrame implements ActionListener {


    private JPanel p1, p2, p3;
    private Font f1, f2, f3, f4;
    private JTable table;
    DefaultTableModel model;
    String name;
    ArrayList<Contact> contactList;
    JScrollPane scrollPane;
    JButton selectall = new JButton("显示所有联系人");
    JButton insertcontacts = new JButton("新建联系人");
    JButton order = new JButton("排序");
    JButton selectcontacts = new JButton("查找联系人");
    JButton logout = new JButton("退出");
    JButton selfinfo = new JButton("个人信息");
    JButton update = new JButton("更新");
    JButton draw = new JButton("开始");
    JButton delete = new JButton("删除");
    JButton sendemail = new JButton("导出数据");
    boolean drawFlag = false;

    static String[] columnNames = {"姓名", "手机号", "性别", "住址", "EMAIL", "备注"};   //列名


    public Home(String name1) {
        super("主界面");
        this.setLayout(new FlowLayout());
        name = name1;
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        f1 = new Font("宋体", Font.BOLD, 20);
        f2 = new Font("宋体", Font.BOLD, 20);
        selectall.setFont(f1);
        selectcontacts.setFont(f1);
        insertcontacts.setFont(f1);
        order.setFont(f1);
        logout.setFont(f1);
        selfinfo.setFont(f1);
        update.setFont(f1);
        draw.setFont(f1);
        delete.setFont(f1);
        sendemail.setFont(f1);

        p1.setLayout(new GridLayout(1, 4, 10, 10));

        p1.add(selectall);
        p1.add(selectcontacts);
        p1.add(order);
        p1.add(insertcontacts);
        p1.add(logout);
        p1.add(selfinfo);

        table = new JTable();
        tableValesRe();

        table.setFont(f2);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        TableColumn firsetColumn = table.getColumnModel().getColumn(0);
        firsetColumn.setPreferredWidth(150);
        TableColumn firsetColumn1 = table.getColumnModel().getColumn(2);
        firsetColumn1.setPreferredWidth(150);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            firsetColumn = table.getColumnModel().getColumn(i);
            firsetColumn.setPreferredWidth(150);
        }
        table.setRowHeight(25);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        scrollPane = new JScrollPane(table) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1100, 600);
            }
        };
        scrollPane.setViewportView(table);
        p2.setLayout(new FlowLayout());
        scrollPane.add(p2);

        selectcontacts.addActionListener(this);
        selectall.addActionListener(this);
        logout.addActionListener(this);
        insertcontacts.addActionListener(this);
        order.addActionListener(this);
        selfinfo.addActionListener(this);
        update.addActionListener(this);
        draw.addActionListener(this);
        sendemail.addActionListener(this);
        delete.addActionListener(this);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table.clearSelection();
            }
        });


        p3.setLayout(new FlowLayout());
        p3.add(delete);
        p3.add(new JLabel("    "));

        p3.add(update);
        p3.add(new JLabel("    "));
        p3.add(draw);
        p3.add(new JLabel("    "));
        p3.add(sendemail);

        this.add(p1);
        this.add(scrollPane);
        this.add(p3);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == logout) {//退出

            new Login();
            this.dispose();

        } else if (source == insertcontacts) {//新建联系人

            new AddContact(name);

        } else if (source == order) { //排序

            Object[] options = {"升序", "降序"};
            int userChoice = JOptionPane.showOptionDialog(null, "按联系人id排序", "提示",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            if (userChoice == 0) {

                sortContacts(contactList, true);
                tableValesRe();

            } else if (userChoice == 1) {

                sortContacts(contactList, false);
                tableValesRe();
            }

        } else if (source == selectall) {//查询所有联系人

            table.clearSelection();
            tableValesRe();

        } else if (source == selectcontacts) {//查找联系人

            String nameInput = (String) JOptionPane.showInputDialog(
                    null,
                    "要查找联系人的名字",
                    "输入名字",
                    JOptionPane.PLAIN_MESSAGE,
                    UIManager.getIcon("OptionPane.informationIcon"),
                    null,
                    "");

            if (nameInput != null) {
                for (int i = 0; i < table.getRowCount(); i++) {
                    Object[] rowData = getRowData(table, i);
                    String user = Arrays.toString(rowData);
                    String userName = user.substring(1, user.length() - 1).split(", ")[0];
                    if (userName.equals(nameInput)) {
                        table.setRowSelectionInterval(i,i);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "查无此人", "提示", JOptionPane.PLAIN_MESSAGE);
            }

        } else if (source == selfinfo) {//个人信息

            String[] temp = new String[0];
            try {
                temp = DataTools.readUserData(name);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            new DisplayInfor(temp);

        } else if (source == delete) {//删除联系人

            int[] selectedRows = table.getSelectedRows();
            if(selectedRows.length == 0) {
                return;
            }
            int userChoice = JOptionPane.showConfirmDialog(null, "你选中了" +  selectedRows.length
                    + "行," +"确定要删除?", "提示", JOptionPane.YES_NO_OPTION);

            if (userChoice == JOptionPane.YES_OPTION) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int selectedRow = selectedRows[i];
                    String selectedName = (String) table.getValueAt(selectedRow, 0);
                    model.removeRow(selectedRow);

                    try {
                        ContactDao.deleteContact(name, selectedName);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                table.clearSelection();
                tableValesRe();
            }

        } else if (source == update) {//更新

            table.clearSelection();
            tableValesRe();

        } else if (source == draw) {

            if(!drawFlag) { //开始

                table.clearSelection();
                tableValesRe();

                draw.setText("停止");
                drawFlag = true;

                Thread myThread = new Thread(()->{
                    while(true) {
                        if(contactList.size() == 0) {
                            break;
                        }
                        int selectRow = new Random().nextInt(contactList.size());
                        table.setRowSelectionInterval(selectRow,selectRow);

                        if(!drawFlag) {
                            break;
                        }

                        try {
                            Thread.sleep(100);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                myThread.start();

                selectall.setEnabled(false);
                insertcontacts.setEnabled(false);
                order.setEnabled(false);
                selectcontacts.setEnabled(false);
                logout.setEnabled(false);
                selfinfo.setEnabled(false);
                update.setEnabled(false);
                delete.setEnabled(false);
                sendemail.setEnabled(false);
            } else {
                draw.setText("开始");

                drawFlag = false;

                selectall.setEnabled(true);
                insertcontacts.setEnabled(true);
                order.setEnabled(true);
                selectcontacts.setEnabled(true);
                logout.setEnabled(true);
                selfinfo.setEnabled(true);
                update.setEnabled(true);
                delete.setEnabled(true);
                sendemail.setEnabled(true);
            }

        } else if (source == sendemail) {//导出数据
            if(ContactDao.contactExport(name,contactList)) {
                JOptionPane.showMessageDialog(null, "导出成功", "提示", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "失败", "提示", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void tableValesRe() {
        try {
            contactList = ContactDao.selectAllContact(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[][] tableVales = new String[contactList.size()][8];
        for (int i = 0; i < contactList.size(); i++) {
            tableVales[i][0] = contactList.get(i).getM_Name();
            tableVales[i][1] = contactList.get(i).getM_PhoneNum();
            tableVales[i][2] = contactList.get(i).getM_Sex();
            tableVales[i][3] = contactList.get(i).getM_Address();
            tableVales[i][4] = contactList.get(i).getM_Email();
            tableVales[i][5] = contactList.get(i).getM_BeiZhu();
        }
        model = new DefaultTableModel(tableVales, columnNames);
        table.setModel(model);
    }

    private void sortContacts(ArrayList<Contact> contacts, boolean ascending) {
        Comparator<Contact> comparator = Comparator.comparingInt(contact -> {
            try {
                return Integer.parseInt(contact.getM_Id());
            } catch (NumberFormatException e) {
                return 0;
            }
        });
        if (!ascending) {
            comparator = comparator.reversed();
        }
        contacts.sort(comparator);
    }

    private Object[] getRowData(JTable table, int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int columnCount = model.getColumnCount();
        Object[] rowData = new Object[columnCount];

        if (rowIndex >= 0 && rowIndex < model.getRowCount()) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                rowData[columnIndex] = table.getValueAt(rowIndex, columnIndex);
            }
            return rowData;
        }

        return null;
    }

}
