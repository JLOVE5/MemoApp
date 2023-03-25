package com.example.memoapp;

import java.util.Calendar;

public class Memo {
    private int memoID;
    private String memoTitle;
    private String memoContent;
    private String memoDate;
    private String memoImportance;

    public Memo(){
        memoID = -1;

    }

    public int getMemoID() {
        return memoID;
    }

    public void setMemoID(int memoID) {
        this.memoID = memoID;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public String getMemoDate() {
        return memoDate;
    }

    public void setMemoDate(String memoDate) {
        this.memoDate = memoDate;
    }

    public String getMemoImportance() {
        return memoImportance;
    }

    public void setMemoImportance(String memoImportance) {
        this.memoImportance = memoImportance;
    }





}
