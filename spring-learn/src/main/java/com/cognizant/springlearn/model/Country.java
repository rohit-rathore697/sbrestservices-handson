package com.cognizant.springlearn.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.slf4j.LoggerFactory;




public class Country {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Country.class);

	@NotNull
	@Size(min=2, max=2, message="Country code should be 2 characters")
	private String code;
	@NotNull
	@Size(min=1 , max=30 , message="Country name invalid")
	private String name;

	public String getCode() {
		LOGGER.debug("inside code getter");
		return code;
	}

	public void setCode(String code) {
		LOGGER.debug("Inside code setter");
		this.code = code;
	}

	public String getName() {
		LOGGER.debug("Inside name getter");
		return name;
	}

	public void setName(String name) {
		LOGGER.debug("Inside name setter");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}

	public Country() {
		LOGGER.debug("Inside Country Constructor");
	}

	public Country(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

}
