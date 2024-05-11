package com.example.mobilejava;

public class Customer {
    private int id;
    private String firstName, lastName , state , delegation , companyName ,tradeMark , activity , adress  ;
    private boolean isCard , isAgree , isSubs1, isSubs2, isSubs3;
    private int phoneNum, cin, codePostal ;

    public Customer(int id , String firstName, String lastName, String state, String delegation, String companyName, String tradeMark, int cin, String activity, String adress, int codePostal, boolean isCard, boolean isAgree, int phoneNum, boolean isSubs1, boolean isSubs2,boolean isSubs3) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.delegation = delegation;
        this.companyName = companyName;
        this.tradeMark = tradeMark;
        this.cin = cin;
        this.activity = activity;
        this.adress = adress;
        this.codePostal = codePostal;
        this.isCard = isCard;
        this.isAgree = isAgree;
        this.phoneNum = phoneNum;
        this.isSubs1= isSubs1;
        this.isSubs2 = isSubs2;
        this.isSubs3 = isSubs3;
    }

    //getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public boolean isCard() {
        return isCard;
    }

    public void setCard(boolean card) {
        isCard = card;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isSubs1() {
        return isSubs1;
    }

    public void setSubs1(boolean subs1) {
        isSubs1 = subs1;
    }

    public boolean isSubs2() {
        return isSubs2;
    }

    public void setSubs2(boolean subs2) {
        isSubs2 = subs2;
    }

    public boolean isSubs3() {
        return isSubs3;
    }

    public void setSubs3(boolean subs3) {
        isSubs3 = subs3;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", delegation='" + delegation + '\'' +
                ", companyName='" + companyName + '\'' +
                ", tradeMark='" + tradeMark + '\'' +
                ", activity='" + activity + '\'' +
                ", adress='" + adress + '\'' +
                ", isCard=" + isCard +
                ", isAgree=" + isAgree +
                ", isSubs1=" + isSubs1 +
                ", isSubs2=" + isSubs2 +
                ", isSubs3=" + isSubs3 +
                ", phoneNum=" + phoneNum +
                ", cin=" + cin +
                ", codePostal=" + codePostal +
                '}';
    }


}