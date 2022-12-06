/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class CustomerProduct {
 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    
    @Column(unique = false, nullable = false)
    private long productId;
    
    @Transient
    @Column(length = 50, unique = false, nullable = false)
    private String productName;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CustomerModel.class)
    @JoinColumn(name = "customerId", nullable = true)   
    private CustomerModel customer;     
}