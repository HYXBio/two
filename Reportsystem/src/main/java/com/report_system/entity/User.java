package com.report_system.entity;

public class User {

    //定义用户权限0管理员，1普通用户
    private static final int ADMIN = 0;
    private static final int PEOPLE = 1;

    private Integer    id;          //用户id
    private String user_name;   //用户名
    private String password;    //密码
    private String department;  //所属村
    private String contact;     //联系人
    private String phone;       //练习电话
    private Integer    type;         //用户权限


    //构造函数
    public User() {
    }

    public User(String user_name, String password, String department, String contact, String phone, int type) {

        this.user_name = user_name;
        this.password = password;
        this.department = department;
        this.contact = contact;
        this.phone = phone;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }

}
