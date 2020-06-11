package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@RestController
public class CountryController {

	@Autowired
	private static CountryService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	public CountryController() {
		LOGGER.info("START");
		LOGGER.debug("Inside CountryController Constructor");
		LOGGER.info("END");
	}

	@PostMapping()
	public void addCountry() {
		LOGGER.info("START");
		LOGGER.info("END");
	}
	
	@PostMapping()
	public void updateCountry(@RequestBody @Valid Country country) throws CountryNotFoundException {
		LOGGER.info("START");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Country>> violations = validator.validate(country);
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<Country> violation : violations) {
			errors.add(violation.getMessage());
		}
		if (violations.size() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		service.updateCountry(country);
		LOGGER.info("END");
	}
	
	@PostMapping()
	public void deleteCountry(@RequestBody @Valid Country country) throws CountryNotFoundException {
		LOGGER.info("START");
		service.deleteCountry(country);
		LOGGER.info("END");
	}

	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("START");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Country>> violations = validator.validate(country);
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<Country> violation : violations) {
			errors.add(violation.getMessage());
		}
		if (violations.size() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.toString());
		}
		LOGGER.debug("Country : {}", country.toString());
		LOGGER.info("END");
		return country;
	}

	@GetMapping("/countries/{code}")
	public static Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		LOGGER.info("START");
		Country c = service.getCountry(code);
		LOGGER.info("END");
		return c;
	}

	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public static Country getCountryIndia() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country c = (Country) context.getBean("in");
		LOGGER.info("END");
		return c;
	}

	@GetMapping("/countylist")
	public static ArrayList<Country> getAllCountries() {
		LOGGER.info("START");
		LOGGER.info("END");
		return service.getAllCountries();
	}

}
