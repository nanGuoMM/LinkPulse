package com.pojo;

public class Contact {
    private String m_Name;
    private String m_Address;
    private String m_Email;
    private String m_Id;
    private String m_PhoneNum;
    private String m_Sex;
    private String m_BeiZhu;


    public Contact() {
    }

    public Contact(String m_Name, String m_Address, String m_Email, String m_Id, String m_PhoneNum, String m_Sex, String m_BeiZhu) {
        this.m_Name = m_Name;
        this.m_Address = m_Address;
        this.m_Email = m_Email;
        this.m_Id = m_Id;
        this.m_PhoneNum = m_PhoneNum;
        this.m_Sex = m_Sex;
        this.m_BeiZhu = m_BeiZhu;
    }

    /**
     * 获取
     *
     * @return m_Name
     */
    public String getM_Name() {
        return m_Name;
    }

    /**
     * 设置
     *
     * @param m_Name
     */
    public void setM_Name(String m_Name) {
        this.m_Name = m_Name;
    }

    /**
     * 获取
     *
     * @return m_Address
     */
    public String getM_Address() {
        return m_Address;
    }

    /**
     * 设置
     *
     * @param m_Address
     */
    public void setM_Address(String m_Address) {
        this.m_Address = m_Address;
    }

    /**
     * 获取
     *
     * @return m_Email
     */
    public String getM_Email() {
        return m_Email;
    }

    /**
     * 设置
     *
     * @param m_Email
     */
    public void setM_Email(String m_Email) {
        this.m_Email = m_Email;
    }

    /**
     * 获取
     *
     * @return m_Id
     */
    public String getM_Id() {
        return m_Id;
    }

    /**
     * 设置
     *
     * @param m_Id
     */
    public void setM_Id(String m_Id) {
        this.m_Id = m_Id;
    }

    /**
     * 获取
     *
     * @return m_PhoneNum
     */
    public String getM_PhoneNum() {
        return m_PhoneNum;
    }

    /**
     * 设置
     *
     * @param m_PhoneNum
     */
    public void setM_PhoneNum(String m_PhoneNum) {
        this.m_PhoneNum = m_PhoneNum;
    }

    /**
     * 获取
     *
     * @return m_Sex
     */
    public String getM_Sex() {
        return m_Sex;
    }

    /**
     * 设置
     *
     * @param m_Sex
     */
    public void setM_Sex(String m_Sex) {
        this.m_Sex = m_Sex;
    }

    /**
     * 获取
     *
     * @return m_BeiZhu
     */
    public String getM_BeiZhu() {
        return m_BeiZhu;
    }

    /**
     * 设置
     *
     * @param m_BeiZhu
     */
    public void setM_BeiZhu(String m_BeiZhu) {
        this.m_BeiZhu = m_BeiZhu;
    }

    public String toString() {
        return "Contact{m_Name = " + m_Name + ", m_Address = " + m_Address + ", m_Email = " + m_Email + ", m_Id = " + m_Id + ", m_PhoneNum = " + m_PhoneNum + ", m_Sex = " + m_Sex + ", m_BeiZhu = " + m_BeiZhu + "}";
    }
}
