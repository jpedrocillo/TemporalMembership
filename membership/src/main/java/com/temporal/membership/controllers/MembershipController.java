package com.temporal.membership.controllers;

import com.temporal.membership.model.MembershipDto;
import com.temporal.membership.services.AccountService;
import com.temporal.membership.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private AccountService accountService;

    private final HashMap<String, Object> response = new HashMap<>();

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody MembershipDto inputTransaction){

        //Service call
        try{
            membershipService.register(inputTransaction);

            response.put("status", "success");
            response.put("message", "Successfully place your membership with registration no: " + inputTransaction.getRegistrationNo());
            response.put("data", inputTransaction.getRegistrationNo());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Workflow already created: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("verify")
    public ResponseEntity<?> verify(@RequestParam String registrationNo){

        //Service call
        try{
            membershipService.verify(registrationNo);

            response.put("status", "success");
            response.put("message", "Successfully verified your membership with registration no: " + registrationNo);
            response.put("data", registrationNo);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("createAccount")
    public ResponseEntity<?> createAccount(@RequestParam String registrationNo){

        //Service call
        try {
            membershipService.createAccount(registrationNo);

            response.put("status", "success");
            response.put("message", "Successfully created the account with registration no: " + registrationNo);
            response.put("data", registrationNo);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("complete")
    public ResponseEntity<?> complete(@RequestParam String registrationNo){
        //Service call
        try {
            membershipService.complete(registrationNo);

            response.put("status", "success");
            response.put("message", "Membership registration complete. Email sent to user for activation ");
            response.put("data", registrationNo);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "activate/{registrationNo}")
    public ResponseEntity<?> activateAccount(@PathVariable String registrationNo){

        accountService.activate(registrationNo);

        response.put("status", "success");
        response.put("message", "Successfully activated your membership account.");
        response.put("data", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
