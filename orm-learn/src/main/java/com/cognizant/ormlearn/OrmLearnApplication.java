package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.Exception.CountryNotFoundException;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication
public class OrmLearnApplication {

	private static CountryService countryService;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		LOGGER.info("Inside main");
		testGetAllCountries();
		getAllCountriesTest();
		addCountryTest();
		updateCountryTest();
	
	}
	
	private static void testGetAllCountries() 
	{

		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
	
		LOGGER.info("End");

	}
	
	private static void getAllCountriesTest() {

		LOGGER.info("Start");
		Country country;
		try 
		{
			country = countryService.findCountryByCode("IN");
			LOGGER.debug("Country:{}", country);
			
		} 
		catch (CountryNotFoundException e) 
		{
	
			System.out.println(e);
		}
		
		LOGGER.info("End");
		}
		
	private static void addCountryTest()
	{
			LOGGER.info("Start");
			Country c = new Country("EX","Example");
			countryService.addCountry(c);
			
			try 
			{
				Country country = countryService.findCountryByCode("EX");
				LOGGER.debug("Country:{}", country);
			} 
			catch (CountryNotFoundException e) 
			{
				
				System.out.println(e);
			}
			
			LOGGER.info("End");
		}
		
		private static void updateCountryTest()
		{
			LOGGER.info("Start");
			
			try 
			{
				countryService.updateCountry("EX", "New Example");
				Country country = countryService.findCountryByCode("EX");
				LOGGER.debug("Country:{}", country);
				countryService.deleteCountry("EX");
				
			} 
			
			catch (CountryNotFoundException e) 
			{
				
				System.out.println(e);
			}
			LOGGER.info("End");
			
		}
		
}
