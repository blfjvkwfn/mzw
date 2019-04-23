package com.mzw.auth2.web;

import com.mzw.auth2.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jonathan Meng
 * @date 03/04/2019
 */
@RestController
@Slf4j
public class ProductApi {
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityUtil.authentication();
        log.info("getProduct authentication: " + authentication.isAuthenticated());
        return "product id is: " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return "order id is: " + id;
    }
}
