package com.xolisa.creditcardrestapi.controller;

import com.xolisa.creditcardrestapi.entity.CreditCard;
import com.xolisa.creditcardrestapi.repository.CreditCardRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cards")
public class CreditCardController {

    private static final Logger log = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardRepository creditCardRepository;

    @RequestMapping(method= RequestMethod.GET, value = "/{cardNumber}")
    public CreditCard lookup(@PathVariable String cardNumber) {
        CreditCard creditCard = null;
        if (creditCardRepository.findById(cardNumber) == null) {
            return null;
        }
        try {
            String data = lookupCreditCard(cardNumber);
            JSONObject obj = new JSONObject(data);
            String countryName = obj.getJSONObject("country").getString("name");
            String countryalpha2 = obj.getJSONObject("country").getString("alpha2");
            String countryemoji = obj.getJSONObject("country").getString("emoji");

            creditCard = new CreditCard(cardNumber, countryName, countryalpha2, countryemoji);

            creditCardRepository.save(creditCard);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return creditCard;
    }

    @RequestMapping(method= RequestMethod.GET)
    public Iterable<CreditCard> creditCards() {
        return creditCardRepository.findAll();
    }

    public static String lookupCreditCard(String cardNumber) throws IOException {
        URL urlForGetRequest = new URL("https://lookup.binlist.net/"+cardNumber);
        String readLine = null;

        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Something went wrong. HttpResponseCode : "+connection.getResponseCode());
        } else {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();

            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            }
            in .close();
            return response.toString();
        }
    }
}