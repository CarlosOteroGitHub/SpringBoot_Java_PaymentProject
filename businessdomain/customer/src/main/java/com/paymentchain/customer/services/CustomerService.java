/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.customer.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.paymentchain.customer.models.CustomerModel;
import com.paymentchain.customer.models.CustomerProduct;
import com.paymentchain.customer.repositories.CustomerRepository;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private final WebClient.Builder webClientBuilder;

    // Constructor de Clase.
    public CustomerService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    // define timeout
    HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(EpollChannelOption.TCP_KEEPIDLE, 300)
            .option(EpollChannelOption.TCP_KEEPINTVL, 60)
            .responseTimeout(Duration.ofSeconds(1))
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    // Estructura de la función GET para retornar todos los registros de la tabla "customer".
    public ArrayList<CustomerModel> get() {
        return (ArrayList<CustomerModel>) customerRepository.findAll();
    }

    // Estructura de la función POST para ingresar un registro a la tabla "customer".
    public CustomerModel post(CustomerModel customerModel) {
        customerModel.getProducts().forEach(x -> x.setCustomer(customerModel));
        return customerRepository.save(customerModel);
    }

    // Estructura de la función GET para retornar un registro de la tabla "customer" mediante un ID.
    public Optional<CustomerModel> getID(long id) {
        return customerRepository.findById(id);
    }

    // Estructura de la función PUT para actualizar un registro de la tabla "customer" mediante un ID.
    public Optional<CustomerModel> put(long id, CustomerModel customerModel) {
        return customerRepository.findById(id).map(value -> {
            value.setNames(customerModel.getNames());
            return customerRepository.save(value);
        });
    }

    // Estructura de la función DELETE para eliminar un registro de la tabla "customer" mediante un ID.
    public boolean delete(long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
    
    private String getProductName(long id) {
        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://businessdomain-product/product")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-product/product"))
                .build();
        JsonNode block = client.method(HttpMethod.GET).uri("/" + id)
                .retrieve().bodyToMono(JsonNode.class).block();
        String name = block.get("name").asText();
        return name;
    }
    
    // Estructura de la función GET para retornar un registro de la tabla "customer" mediante un Code, adicionando las transacciones y productos asociados a dicho cliente.
    public CustomerModel getProducts(String code) {
        CustomerModel customerModel = customerRepository.findByCode(code);
        List<CustomerProduct> products = customerModel.getProducts();
        products.forEach(x -> {
            String productName = getProductName(x.getProductId());
            x.setProductName(productName);
        });
        List<?> transactions = getTransacctions(customerModel.getLban());
        customerModel.setTransactions(transactions);
        return customerModel;
    }

    private List<?> getTransacctions(String accountIban) {
        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://businessdomain-transactions/transaction")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-transactions/transaction"))
                .build();
        List<?> transactions = client.method(HttpMethod.GET).uri(uriBuilder -> uriBuilder
                .path("/transactions")
                .queryParam("ibanAccount", accountIban)
                .build())
                .retrieve().bodyToFlux(Object.class).collectList().block();
        return transactions;
    }
}