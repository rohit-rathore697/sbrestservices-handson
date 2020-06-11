package com.cognizant.springlearn.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.dao.CountryDao;
import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private static CountryDao cdao;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	
	
	public ArrayList<Country> getAllCountries(){
		LOGGER.info("START");
		LOGGER.info("END");
		return CountryDao.getAllCountries();
	}
	
	public void updateCountry(Country country) throws CountryNotFoundException {
		cdao.updateCountry(country);
	}
	
	public void deleteCountry(Country country) throws CountryNotFoundException{
		cdao.deleteCountry(country);
	}
	
	public Country getCountry(String code) throws CountryNotFoundException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		ArrayList<Country> list = (ArrayList)context.getBean("countryList");
		for(Country c : list) {
			if(c.getCode().equalsIgnoreCase(code)) {
				return c;
			}
		}
		LOGGER.info("END");
	    throw new CountryNotFoundException();
	}

}
