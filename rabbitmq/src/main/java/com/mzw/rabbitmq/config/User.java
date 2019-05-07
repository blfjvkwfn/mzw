package com.mzw.rabbitmq.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jonathan Meng
 * @date 07/05/2019
 */
@Data
@AllArgsConstructor
public class User {
    private String id;
    private Integer age;
    private String name;
}
