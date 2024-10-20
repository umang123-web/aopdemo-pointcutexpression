package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao{


    @Override
    public boolean addsillymember() {
        System.out.println(  getClass() + "  doing my membership work");

        return true;

    }

    @Override
    public void goTosleep() {
        System.out.println(  getClass() + "I am going to sleep now ...");

    }
}
