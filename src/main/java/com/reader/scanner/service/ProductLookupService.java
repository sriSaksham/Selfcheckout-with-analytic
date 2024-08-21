//package com.reader.scanner.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//@Service
//public class ProductLookupService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Map<String, String> getProductDetails(String barcode) {
//        String url = "http://localhost:8080/rfconsole/product/" + barcode;
//        return restTemplate.getForObject(url, Map.class);
//    }
//}

//package com.reader.scanner.service;
//
//import com.reader.scanner.util.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//@Service
//public class ProductLookupService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//
//    public Map<String, String> getProductDetails(String barcode) {
//        String url = "http://localhost:8080/rfconsole/product/" + barcode;
//        HttpHeaders headers = new HttpHeaders();
//        String token = jwtTokenUtil.getToken();
//        if (token != null) {
//            headers.set("Authorization", "Bearer " + token);
//        }
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();
//    }
//}

package com.reader.scanner.service;

import com.reader.scanner.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProductLookupService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    public Map<String, String> getProductDetails(String barcode) {
//        String url = "http://localhost:8080/rfconsole/product/" + barcode;
//        HttpHeaders headers = new HttpHeaders();
//        String token = jwtTokenUtil.getToken();
//        if (token != null) {
//            headers.set("Authorization", "Bearer " + token);
//        } else {
//            throw new RuntimeException("JWT Token not found in Security Context");
//        }
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();
//    }
//}

    public Map<String, String> getProductDetails(String barcode) {
        String url = "http://localhost:8080/rfconsole/product/" + barcode;
        HttpHeaders headers = new HttpHeaders();
        String token = jwtTokenUtil.getToken();

        // Ensure the token is prefixed with "Bearer "
        if (token != null) {
            if (!token.startsWith("Bearer ")) {
                token = "Bearer " + token;
            }
            headers.set("Authorization", token);
        } else {
            throw new RuntimeException("JWT Token not found in Security Context");
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();
    }
}

