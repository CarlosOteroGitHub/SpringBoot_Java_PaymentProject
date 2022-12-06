/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.transactions.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    
    @Column(length = 50, unique = false, nullable = false)
    private String reference;
    
    @Column(length = 50, unique = false, nullable = false)
    private String ibanAccount;
    
    @Column(unique = false, nullable = false)
    private LocalDateTime date;
    
    @Column(unique = false, nullable = false)
    private double amount;
    
    @Column(unique = false, nullable = false)
    private double fee;
    
    @Column(length = 50, unique = false, nullable = false)
    private String description;
    
    @Column(length = 50, unique = false, nullable = false)
    private String status;
    
    @Column(length = 50, unique = false, nullable = false)
    private String channel;
}