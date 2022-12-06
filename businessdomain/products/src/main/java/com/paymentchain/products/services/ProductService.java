/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.products.services;

import com.paymentchain.products.models.ProductModel;
import com.paymentchain.products.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    //Estructura de la función GET para retornar todos los registros de la tabla "product".
    public ArrayList<ProductModel> get() {
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    //Estructura de la función POST para ingresar un registro a la tabla "product".
    public ProductModel post(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    //Estructura de la función GET para retornar un registro de la tabla "product" mediante un ID.
    public Optional<ProductModel> getID(long id) {
        return productRepository.findById(id);
    }

    //Estructura de la función PUT para actualizar un registro de la tabla "product" mediante un ID.
    public Optional<ProductModel> put(long id, ProductModel productModel) {
        return productRepository.findById(id).map(value -> {
            value.setName(productModel.getName());
            return productRepository.save(value);
        });
    }

    //Estructura de la función DELETE para eliminar un registro de la tabla "product" mediante un ID.
    public boolean delete(long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
