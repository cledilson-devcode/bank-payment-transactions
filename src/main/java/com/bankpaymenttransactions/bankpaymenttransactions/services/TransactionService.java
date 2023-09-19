package com.bankpaymenttransactions.bankpaymenttransactions.services;

import com.bankpaymenttransactions.bankpaymenttransactions.domain.tansaction.Transaction;
import com.bankpaymenttransactions.bankpaymenttransactions.domain.user.User;
import com.bankpaymenttransactions.bankpaymenttransactions.dtos.TransactionDTO;
import com.bankpaymenttransactions.bankpaymenttransactions.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

//        boolean isAuthorizad = this.authorizeTransaction(sender, transactionDTO.value());
//        if (!isAuthorizad){
//            throw new Exception("Transação não autorizada");
//        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação realizada com sucesso!!!");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso!!!");

        return newTransaction;

    }

    public boolean authorizeTransaction(User sender, BigDecimal value){

        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else {
            return false;
        }

    }

}
