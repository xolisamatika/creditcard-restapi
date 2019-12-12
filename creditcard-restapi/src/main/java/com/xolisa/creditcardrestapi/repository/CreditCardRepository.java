package com.xolisa.creditcardrestapi.repository;

import com.xolisa.creditcardrestapi.entity.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

    public Optional<CreditCard> findById(String id);
}
