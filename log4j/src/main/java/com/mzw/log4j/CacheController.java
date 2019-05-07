package com.mzw.log4j;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@RestController
public class CacheController {
    @GetMapping("/hello/{name}")
    @Cacheable(value = "test", unless = "#name.equals('null')")
    public String hello(@PathVariable String name) {
        return "hello " + name + ", now:" + LocalDateTime.now();
    }
}
