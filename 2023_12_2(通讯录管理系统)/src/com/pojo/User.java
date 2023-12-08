package com.pojo;

public class User {
    private String m_Name;
    private String m_Password;
    private String m_Address;
    private String m_PhoneNum;
    private String m_Email;


    public User() {
    }

    public User(String m_Name, String m_Password, String m_Address, String m_PhoneNum, String m_Email) {
        this.m_Name = m_Name;
        this.m_Password = m_Password;
        this.m_Address = m_Address;
        this.m_PhoneNum = m_PhoneNum;
        this.m_Email = m_Email;
    }

    /**
     * 获取
     * @return m_Name
     */
    public String getM_Name() {
        return m_Name;
    }

    /**
     * 设置
     * @param m_Name
     */
    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    /**
     * 获取
     * @return m_Password
     */
    public String getM_Password() {
        return m_Password;
    }

    /**
     * 设置
     * @param m_Password
     */
    public void setM_Password(String m_Password) {
        this.m_Password = m_Password;
    }

    /**
     * 获取
     * @return m_Address
     */
    public String getM_Address() {
        return m_Address;
    }

    /**
     * 设置
     * @param m_Address
     */
    public void setM_Address(String m_Address) {
        this.m_Address = m_Address;
    }

    /**
     * 获取
     * @return m_PhoneNum
     */
    public String getM_PhoneNum() {
        return m_PhoneNum;
    }

    /**
     * 设置
     * @param m_PhoneNum
     */
    public void setM_PhoneNum(String m_PhoneNum) {
        this.m_PhoneNum = m_PhoneNum;
    }

    /**
     * 获取
     * @return m_Email
     */
    public String getM_Email() {
        return m_Email;
    }

    /**
     * 设置
     * @param m_Email
     */
    public void setM_Email(String m_Email) {
        this.m_Email = m_Email;
    }

    public String toString() {
        return "UserDao{m_Name = " + m_Name + ", m_Password = " + m_Password + ", m_Address = " + m_Address + ", m_PhoneNum = " + m_PhoneNum + ", m_Email = " + m_Email + "}";
    }
}
