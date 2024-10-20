package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;

import java.util.List;

public interface AccountDao {

    //void addAccount(Account theaccount);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripwire);


    void addAccount(Account theaccount , boolean vipflag);

    boolean dowork();

     String getName();

     void setName(String name);

     String getServiceCode();

   void setServiceCode(String serviceCode);


}
