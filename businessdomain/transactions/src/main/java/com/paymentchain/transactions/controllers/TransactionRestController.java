/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.controllers;

import com.paymentchain.transactions.models.TransactionModel;
import com.paymentchain.transactions.services.TransactionService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class TransactionRestController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/api/transaction", method = RequestMethod.GET)
    public ArrayList<TransactionModel> get() {
        return transactionService.get();
    }

    @RequestMapping(value = "/api/transaction", method = RequestMethod.POST)
    public TransactionModel post(@RequestBody TransactionModel transactionModel) {
        return this.transactionService.post(transactionModel);
    }

    @RequestMapping(value = "/api/transaction/{id}", method = RequestMethod.GET)
    public Optional<TransactionModel> getID(@PathVariable long id) {
        return transactionService.getID(id);
    }

    @RequestMapping(value = "/api/transaction/{id}", method = RequestMethod.PUT)
    public Optional<TransactionModel> put(@PathVariable long id, @RequestBody TransactionModel transactionModel) {
        return transactionService.put(id, transactionModel);
    }

    @RequestMapping(value = "/api/transaction/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long id) {
        return transactionService.delete(id);
    }
}