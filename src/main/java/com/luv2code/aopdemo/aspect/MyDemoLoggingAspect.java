package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

//        print out method we are advising on

        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @After (finally) on method:" + method);
//        get begin timestamp

        long begin = System.currentTimeMillis();
//          now let s' execute the method

//        Handle the target method and Execute the target Method
        Object result = null;
        try {

           result= theProceedingJoinPoint.proceed();
        }catch (Exception exec){
            //log the exception
            System.out.println(exec.getMessage());

            //rethrow execption
            throw exec;

            //give user a custom message
            }
//        get end timestamp

        long end = System.currentTimeMillis();
//        compute duration and display it

        long duration = end - begin;

//        convert milliseconds to seconds 1000 milliseconds = 1s
        System.out.println("\n=======> Duration" + duration /1000.0 + "seconds");

        return result;
    }
    @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing @After (finally) on method:" + method);
    }



    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountdvice(JoinPoint joinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing AfterThrowing advice:" + method);

        // log the execption

        System.out.println("\n===>>> Executing AfterThrowing on method:" + theExc);
    }



    //    add a new advice for @AfterReturning on the findAccount Method
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
            returning = "result")
     public void afterReturningFindAccountAdvice(JoinPoint thejointpoint , List<Account> result){

//        print out which method we are advising on


        String method = thejointpoint.getSignature().toShortString();
        System.out.println("\n===>>> Executing AfterReturning on method:" + method);


//        print out the result of the method call

        System.out.println("\n===>>> result is:" + result);

        //let s' post-process the data ...let s' modify it
//        convert the account name to uppercase

        convertAccountNamesToUppercase(result);

        System.out.println("\n===>>> result is:" + result);

    }

    private void convertAccountNamesToUppercase(List<Account> result) {

        // loop through accounts

        for (Account tempaccount : result) {

            //get uppercase version of name

            String theUpperName = tempaccount.getName().toUpperCase();


            // update the name on the account

            tempaccount.setName(theUpperName);

        }

    }


    /*
* step 1: create a pointcut expression
*
* step 2 : Apply pointcut declaration to advice
*
*
* JointPoint has metadata about method call
* */
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpression.forDaoPackageNoGetterSetterMethod()")
    public void beforeAddAccountAdvice(JoinPoint thejointpoint){
    System.out.println("\n====>>>  Executing @Before  advice on method()");


    //display the method signature
        MethodSignature methodSignature = (MethodSignature) thejointpoint.getSignature();

        System.out.println("Method:" + methodSignature);

        //display method argument

        //get args
        Object[] args= thejointpoint.getArgs();

        //loop thru args

        for (Object tempargs : args){
            System.out.println(tempargs);

            if (tempargs instanceof Account){
                //downcast and print Account specific stuff

                Account theAccount = (Account) tempargs;

                System.out.println("account name:" + theAccount.getName());
                System.out.println("account level:" + theAccount.getLevel());
            }
        }
    }

}
