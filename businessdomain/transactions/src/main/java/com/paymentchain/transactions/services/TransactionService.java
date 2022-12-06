/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.services;

import com.paymentchain.transactions.models.TransactionModel;
import com.paymentchain.transactions.repositories.TransactionRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;

    //Estructura de la función GET para retornar todos los registros de la tabla "transaction".
    public ArrayList<TransactionModel> get() {
        return (ArrayList<TransactionModel>) transactionRepository.findAll();
    }

    //Estructura de la función POST para ingresar un registro a la tabla "transaction".
    public TransactionModel post(TransactionModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

    //Estructura de la función GET para retornar un registro de la tabla "transaction" mediante un ID.
    public Optional<TransactionModel> getID(long id) {
        return transactionRepository.findById(id);
    }

    //Estructura de la función PUT para actualizar un registro de la tabla "transaction" mediante un ID.
    public Optional<TransactionModel> put(long id, TransactionModel transactionModel) {
        return transactionRepository.findById(id).map(value -> {
            value.setReference(transactionModel.getReference());
            return transactionRepository.save(value);
        });
    }

    //Estructura de la función DELETE para eliminar un registro de la tabla "transaction" mediante un ID.
    public boolean delete(long id) {
        try {
            transactionRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
