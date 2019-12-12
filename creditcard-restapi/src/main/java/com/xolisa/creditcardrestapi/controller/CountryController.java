package com.xolisa.creditcardrestapi.controller;

import com.xolisa.creditcardrestapi.entity.Country;
import com.xolisa.creditcardrestapi.entity.CreditCard;
import com.xolisa.creditcardrestapi.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/countries")
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(method= RequestMethod.GET)
    public Iterable<Country> getAll() {
        return countryRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.GET, value = "/{name}")
    public Optional<Country> getByName(@PathVariable String name) {
       return countryRepository.findByName(name);
    }
}