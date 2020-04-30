package com.example.pooja.homymarket.Models;

public class MyOrdersFetch {

    private String ORDER_NO,TITLE,PRICE,STATUS,ADDRESS,PHONE,DELIVARY_SLOT,ORDER_TIME;

    public MyOrdersFetch(String ORDER_NO, String TITLE, String PRICE, String STATUS, String ADDRESS, String PHONE, String DELIVARY_SLOT,String ORDER_TIME) {
        this.ORDER_NO = ORDER_NO;
        this.TITLE = TITLE;
        this.PRICE = PRICE;
        this.STATUS = STATUS;
        this.ADDRESS = ADDRESS;
        this.PHONE = PHONE;
        this.DELIVARY_SLOT = DELIVARY_SLOT;
        this.ORDER_TIME = ORDER_TIME;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public String getTITLE() {
        return TITLE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public String getORDER_TIME() { return ORDER_TIME; }

    public String getSTATUS() {
        return STATUS;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public String getDELIVARY_SLOT() {
        return DELIVARY_SLOT;
    }


    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public void setDELIVARY_SLOT(String DELIVARY_SLOT) {
        this.DELIVARY_SLOT = DELIVARY_SLOT;
    }
}
