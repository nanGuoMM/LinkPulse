package com.dao;


import com.pojo.User;
import com.tools.DataTools;

import java.io.*;

public class UserDao {
    public static void addUser(User user) {
        try (FileWriter writer = new FileWriter("userFiles" + File.separator + "Users.txt",true)) {
                writer.write(user.getM_Name() + " " + user.getM_Password() + " "
                        + user.getM_Address() + " " + user.getM_PhoneNum() + " "+user.getM_Email()  + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkUser(String userName,String userPsw) {
        String[] user = null;
        try {
            user = DataTools.readUserData(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user != null && user.length != 0) {
            return user[1].equals(userPsw);
        }
        return false;
    }
}
