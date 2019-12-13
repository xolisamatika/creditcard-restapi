package com.xolisa.creditcardrestapi.repository;

import com.xolisa.creditcardrestapi.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends MongoRepository<Country, String> {

    public List<Country> findAll();

    public Optional<Country> findByName(String name);
}
