/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class CustomerModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    
    @Column(length = 50, unique = false, nullable = false)
    private String names;
    
    @Column(length = 50, unique = false, nullable = false)
    private String surnames;
    
    @Column(length = 10, unique = false, nullable = false)
    private String code;
    
    @Column(length = 10, unique = false, nullable = false)
    private String phone;
    
    @Column(length = 50, unique = false, nullable = false)
    private String lban;
    
    @Column(length = 100, unique = false, nullable = false)
    private String address;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<CustomerProduct> products;
    
    @Transient
    private List<?> transactions;
}