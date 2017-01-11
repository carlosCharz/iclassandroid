package com.wedevol.smartclass.models;

/** Created by paolo on 12/20/16.*/

public class BankAccount {
    private String bankName = "";
    private String bankAccountType = "";
    private String bankAccountId = "";
    private String bankAccountProvinceType = "";
    private String bankAccountProvinceId = "";

    public BankAccount(String bankName, String bankAccountType, String bankAccountId, String bankAccountProvinceType, String bankAccountProvinceId) {
        this.bankName = bankName;
        this.bankAccountType = bankAccountType;
        this.bankAccountId = bankAccountId;
        this.bankAccountProvinceType = bankAccountProvinceType;
        this.bankAccountProvinceId = bankAccountProvinceId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountProvinceType() {
        return bankAccountProvinceType;
    }

    public void setBankAccountProvinceType(String bankAccountProvinceType) {
        this.bankAccountProvinceType = bankAccountProvinceType;
    }

    public String getBankAccountProvinceId() {
        return bankAccountProvinceId;
    }

    public void setBankAccountProvinceId(String bankAccountProvinceId) {
        this.bankAccountProvinceId = bankAccountProvinceId;
    }
}
