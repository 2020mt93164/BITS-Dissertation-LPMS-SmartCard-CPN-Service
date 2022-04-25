package com.cpn.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class ProcessingController {

    @Autowired
    ProcessingService processingService;

    @PostMapping(value = "/cards",consumes = "application/json", produces = "application/json")
    public ResponseEntity createNewCard(@RequestBody Card card){
        return  processingService.createNewCard(card);
    }

    @PostMapping(value = "/cards/validations",consumes = "application/json", produces = "application/json")
    public ResponseEntity validateCard(@RequestBody Card card){
        return processingService.validateCard(card);
    }

    @PutMapping(value = "/cards/redemptions", consumes = "application/json", produces = "application/json")
    public ResponseEntity redeemCard(HttpServletRequest request,@RequestBody Card card){
           return processingService.redeemCard(request,card);
    }
}