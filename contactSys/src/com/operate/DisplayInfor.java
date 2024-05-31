package com.operate;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayInfor extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    static String[] columnNames = {"姓名", "地址", "手机号", "EMAIL"};

    public DisplayInfor(String[] text) {
        super("个人信息");
        model = new DefaultTableModel(null, columnNames);

        table = new JTable(model);
        addDataToModel(text);
        table.setModel(model);
        add(new JScrollPane(table));

        setSize(651, 115);
        setLocationRelativeTo(null);
        table.setRowSelectionAllowed(false);
        setVisible(true);
    }

    private void addDataToModel(String[] text) {
        if (text.length != columnNames.length + 1) {
            throw new IllegalArgumentException("长度不匹配");
        }
        String[] temp = new String[4];

        temp[0] = text[0];
        temp[1] = text[2];
        temp[2] = text[3];
        temp[3] = text[4];

        model.addRow(temp);
    }
}
