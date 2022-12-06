/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.products.controllers;

import com.paymentchain.products.models.ProductModel;
import com.paymentchain.products.services.ProductService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public ArrayList<ProductModel> get() {
        return productService.get();
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public ProductModel post(@RequestBody ProductModel productModel) {
        return this.productService.post(productModel);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public Optional<ProductModel> getID(@PathVariable long id) {
        return productService.getID(id);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.PUT)
    public Optional<ProductModel> put(@PathVariable long id, @RequestBody ProductModel productModel) {
        return productService.put(id, productModel);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable long id) {
        return productService.delete(id);
    }
}