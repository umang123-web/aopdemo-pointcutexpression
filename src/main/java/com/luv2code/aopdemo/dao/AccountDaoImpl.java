package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl  implements AccountDao{

    private String name;

    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);

    }

    @Override
    public List<Account> findAccounts(boolean tripwire) {

        // for academic purposes ... simulate an exception

        if (tripwire) {
            throw new RuntimeException("no soup for you");
        }

        List<Account> myAccount = new ArrayList<>();

        //create a sample account

        Account temp1 = new Account("John" , "Platinum");
        Account temp2 = new Account("Madhu" , "Silver");
        Account temp3 = new Account("Luca" , "Gold");
//        add them to our account list

        myAccount.add(temp1);
        myAccount.add(temp2);
        myAccount.add(temp3);
        return myAccount;
    }

    @Override
    public void addAccount(Account theaccount, boolean vipflag) {
        System.out.println(getClass() + " doing my db account work");
    }

    @Override
    public boolean dowork() {
        System.out.println(getClass() + " : dowork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + " : in getName()");

        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " :in setName()");

        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " : in getServiceCode()");

        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " : in setServiceCode()");

        this.serviceCode = serviceCode;
    }
}
