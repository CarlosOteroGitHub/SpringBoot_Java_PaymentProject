/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.paymentchain.customer.repositories;

import com.paymentchain.customer.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    @Query(value = "SELECT c FROM CustomerModel c WHERE c.code = ?1", nativeQuery = true)
    public CustomerModel findByCode(String code);
}