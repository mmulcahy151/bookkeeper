package com.example.mmulcahy.mybooks;

/**
 * Created by mmulcahy on 8/8/2016.
 */
public class User {
    public User(){

    }

    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public int getUserId(){
        return this.userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
}
