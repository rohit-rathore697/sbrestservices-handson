package com.cognizant.springlearn;

import java.text.ParseException ;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.springlearn.model.Country;

@SpringBootApplication
@ComponentScan("com.*")
public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringApplication.class);
	
	public static void displayCountries() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		ArrayList<Country> list = context.getBean("countryList" , java.util.ArrayList.class);
		for(Country c : list) {
			LOGGER.debug(c.getCode());
			LOGGER.debug(c.getName());
		}
		LOGGER.info("END");
	}

	public static void displayCountry() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = (Country) context.getBean("country", Country.class);
		LOGGER.debug("Country : {}", country.toString());
		Country anotherCountry = context.getBean("country", Country.class);
		LOGGER.debug("Country : {}", anotherCountry.toString());
		LOGGER.info("END");

	}

	public static void displayDate() throws ParseException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		Date date = format.parse("31/12/2018");
		String d = format.format(date);
		LOGGER.debug(d);
		LOGGER.info("END");

	}

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SpringLearnApplication.class, args);
		displayDate();
		displayCountry();
		displayCountries();
	}

}
