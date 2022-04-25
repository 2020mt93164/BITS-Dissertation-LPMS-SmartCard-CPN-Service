package com.cpn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Service
public class ProcessingService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${smartbank.basepath}")
    private String smartbankPath;

    @Value("${idealbank.basepath}")
    private String idealbankPath;

    public ResponseEntity createNewCard(Card card){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Card> entity = new HttpEntity<Card>(card,headers);

        String baseUri;
        int random = new Random().nextInt(2);
        if (random==1){
            baseUri = smartbankPath;
        }else {
            baseUri = smartbankPath;
        }
        ResponseEntity<String> result = restTemplate.exchange(baseUri, HttpMethod.POST, entity, String.class);
        return new ResponseEntity<>(result.getBody(),result.getStatusCode());
    }

    public ResponseEntity validateCard(Card card){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity <Card> entity = new HttpEntity<>(card,headers);

        String baseUri;
        if (card.getCardNo().startsWith("5")){
            baseUri = smartbankPath;
        }else {
            baseUri = smartbankPath;
        }
        ResponseEntity<String> result = restTemplate.exchange(baseUri+"/validations", HttpMethod.POST, entity, String.class);
        return new ResponseEntity<>(result.getBody(),result.getStatusCode());
    }

    public ResponseEntity redeemCard(HttpServletRequest request, Card card){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("pointsToRedeem",request.getHeader("pointsToRedeem"));
        HttpEntity entity = new HttpEntity<>(card,headers);

        String baseUri;

        if (card.getCardNo().startsWith("5")){
            baseUri = smartbankPath;
        }else {
            baseUri = smartbankPath;
        }
        ResponseEntity<String> result = restTemplate.exchange(baseUri+"/redemptions", HttpMethod.PUT, entity, String.class);
        return new ResponseEntity<>(result.getBody(),result.getStatusCode());
    }
}
