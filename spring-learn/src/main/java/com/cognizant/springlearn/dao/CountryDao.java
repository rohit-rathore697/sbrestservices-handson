package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

public class CountryDao {

	private static ArrayList<Country> countryList;
	private final static Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);

	public CountryDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		countryList = (ArrayList) context.getBean("countryList");
	}

	public void deleteCountry(Country country) throws CountryNotFoundException {
		LOGGER.info("START");
		LOGGER.info("END");
	}

	public void updateCountry(Country country) throws CountryNotFoundException {
		LOGGER.info("START");
		LOGGER.info("END");
	}

	public static ArrayList<Country> getAllCountries() {
		return countryList;
	}

}
