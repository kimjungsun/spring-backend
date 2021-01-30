package com.ecommerce.j3.controller.api;

import lombok.Builder;
import org.hibernate.mapping.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api")
@Builder
public class HelloController {

    @GetMapping("/success")
    public ResponseEntity<String> successMessage(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping()
    public String home(
            @RequestHeader(value="Accept") String accept,
            @RequestHeader(value="Accept-Language", defaultValue = "korean") String acceptLanguage,
            @RequestHeader(value="User-Agent", defaultValue="myBrowser") String userAgent,
            @RequestHeader(value="Host") String host
    ) {

        System.out.println("Accept: " + accept);
        System.out.println("Accept-Language: " + acceptLanguage);
        System.out.println("User-Agent: " + userAgent);
        System.out.println("Host: " + host);

        return "home";
    }



}