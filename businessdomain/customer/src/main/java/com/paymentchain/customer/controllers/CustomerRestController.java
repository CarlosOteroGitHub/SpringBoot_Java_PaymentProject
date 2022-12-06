/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.paymentchain.customer.controllers;

import com.paymentchain.customer.models.CustomerModel;
import com.paymentchain.customer.services.CustomerService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public ArrayList<CustomerModel> get() {
        return customerService.get();
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public CustomerModel post(@RequestBody CustomerModel customerModel) {
        return this.customerService.post(customerModel);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public Optional<CustomerModel> getID(@PathVariable long id) {
        return customerService.getID(id);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.PUT)
    public Optional<CustomerModel> put(@PathVariable long id, @RequestBody CustomerModel customerModel) {
        return customerService.put(id, customerModel);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long id) {
        return customerService.delete(id);
    }
    
    @RequestMapping(value = "/api/customer/full", method = RequestMethod.GET)
    public CustomerModel getProducts(@PathVariable String code) {
        return customerService.getProducts(code);
    }
}