package com.mzw.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@RestController
public class CacheController {

    /**
     * This interface will show hello name and time with cache except name is skip
     * @param name
     * @return
     */
    @GetMapping("/hello/{name}")
    @Cacheable(value = "cache1", unless = "#name.equals('skip')")
    public String hello(@PathVariable String name) {
        return "Hello " + name + ",now time:" + LocalDateTime.now();
    }
}
