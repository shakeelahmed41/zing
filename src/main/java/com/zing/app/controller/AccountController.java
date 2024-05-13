package com.zing.app.controller;

import com.zing.app.model.AccountDetails;
import com.zing.app.model.AccountResponseDto;
import com.zing.app.repository.AccountDetailsRepository;
import com.zing.app.repository.CustomerRepository;
import com.zing.app.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountDetailsRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public AccountController() {
    }

    @GetMapping("/account")
    public AccountDetails getAccountDetails(@RequestParam("customerId") Long customerId) {
        Optional<AccountDetails> byId = accountRepository.findById(customerId);
        return byId.orElse(null);
    }

    @GetMapping("/transaction")
    public AccountResponseDto getAccountSummary(@RequestParam("customerId") Long customerId)
    {
     Optional<AccountDetails> accountDetails=accountRepository.findById(customerId);
     if(accountDetails.isPresent())
     {
         AccountResponseDto accountResponseDto=new AccountResponseDto();
         accountResponseDto.setAccountNumber(accountDetails.get().getAccountNumber());
         accountResponseDto.setAmount(accountDetails.get().getBalance());
         accountResponseDto.setCreationDate(LocalDate.now());
         accountResponseDto.setTransactionDetails(accountDetails.get().getTransaction());
         return accountResponseDto;
     }
     return null;
    }
}
