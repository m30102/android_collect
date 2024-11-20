package com.fan.interview;

public class User {

    private static String msg = "old user";

    public static String getMsg() {
        return msg;
    }


    private long userId;
    private String userName;
    private int userAge;

    public User(){
        this(-1,"NONE",-1);
    }
    public User(long userId,String userName,int userAge){
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }
}
