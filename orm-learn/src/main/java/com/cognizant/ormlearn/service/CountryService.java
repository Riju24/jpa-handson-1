package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.Exception.CountryNotFoundException;
import com.cognizant.ormlearn.CountryRepository;
import com.cognizant.ormlearn.model.Country;

@Service
public class CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Transactional
	public List<Country> getAllCountries()
	{
		
		List<Country> li = countryRepository.findAll();
		
		return li;
		
	}
	
	@Transactional 
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException
	{
		Optional<Country> result = countryRepository.findById(countryCode);
		
		if(!result.isPresent())
		{
			throw new CountryNotFoundException("Coutry not found");
		}
		
		Country country = result.get();
		return country;
	
	
	}
	
	@Transactional
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code,String name) throws CountryNotFoundException
	{
		Country country = findCountryByCode(code);
		
		country.setName(name);
		countryRepository.save(country);
	}
	@Transactional
	public void deleteCountry(String id)
	{
		countryRepository.deleteById(id);
	}

}
