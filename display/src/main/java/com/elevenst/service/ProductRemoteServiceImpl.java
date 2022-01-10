package com.elevenst.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ProductRemoteServiceImpl
 */
@Service
public class ProductRemoteServiceImpl implements ProductRemoteService {
    private static final String url = "http://product/products/";

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "fallbackProductInfo")
    public String getProductInfo(String productId) {
        return restTemplate.getForObject(url + productId, String.class);
    }

    public String fallbackProductInfo(String productId, Throwable t) {
        System.out.println(t);
        return "SOLDOUT";
    }
}
