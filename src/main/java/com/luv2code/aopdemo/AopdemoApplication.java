package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDao;
import com.luv2code.aopdemo.dao.MembershipDao;
import com.luv2code.aopdemo.dao.MembershipDaoImpl;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao theAccountDao ,
											   MembershipDao membershipDao,
											   TrafficFortuneService fortuneService){
		return runner->{

			//demoTheBeforeAdvice(theAccountDao , membershipDao);

			//demotheAfterReturningAdvice(theAccountDao);

			//demoTheAfterThrowingAdvice(theAccountDao);

			//demoTheAfterAdvice(theAccountDao);

			//demoTheAroundAdvice(fortuneService);
			//demoTheAroundAdviceHandleException(fortuneService);

			demoTheAroundAdviceReThrowException(fortuneService);

		};
	}

	private void demoTheAroundAdviceReThrowException(TrafficFortuneService fortuneService) throws InterruptedException {

		System.out.println("\nMain Program: demoTheAroundAdviceReThrowException");
		System.out.println("Calling getFortune");
		boolean tripWire=true;
		String data = fortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService fortuneService) throws InterruptedException {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune");
		boolean tripWire=true;
		String data = fortuneService.getFortune(tripWire);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService fortuneService) throws InterruptedException {

		System.out.println("\nMain Program: demoTheAroundAdvice");
		System.out.println("Calling getFortune");
		String data = fortuneService.getFortune();
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");

	}

	private void demoTheAfterAdvice(AccountDao theAccountDao) {
		List<Account> theaccounts = null;

		try {
			// add a boolean flag to simulate exceptions

			boolean tripwire = false;

			theaccounts = theAccountDao.findAccounts(tripwire);


			theaccounts = theAccountDao.findAccounts();


		}catch (Exception exc){

			System.out.println("\n\nMain program ... caught exception:" + exc);
		}
		System.out.println("\n\nMain program : demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(theaccounts);

		System.out.println("\n");

	}

	private void demoTheAfterThrowingAdvice(AccountDao theAccountDao) {

		List<Account> theaccounts = null;

		try {
			// add a boolean flag to simulate exceptions

			boolean tripwire = true;

			theaccounts = theAccountDao.findAccounts(tripwire);


		theaccounts = theAccountDao.findAccounts();


		}catch (Exception exc){

			System.out.println("\n\nMain program ... caught exception:" + exc);
		}
		System.out.println("\n\nMain program : demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(theaccounts);

		System.out.println("\n");

	}

	private void demotheAfterReturningAdvice(AccountDao theAccountDao) {
//		call method to find the account

		List<Account> theaccounts = theAccountDao.findAccounts();
		System.out.println("\n\nMain program : demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(theaccounts);

		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDao , MembershipDao membershipDao) {

		//call the business method

		//will only match on addAccount for this param type
		Account account = new Account();
		account.setName("Madhu");
		account.setLevel("platinum");
		theAccountDao.addAccount(account , true);
        theAccountDao.dowork();

//		call dao account getter and setter
		theAccountDao.setName("foobar");
		theAccountDao.setServiceCode("silver");

		String name = theAccountDao.getName();
		String code = theAccountDao.getServiceCode();

		membershipDao.addsillymember();
		membershipDao.goTosleep();

	}

}
