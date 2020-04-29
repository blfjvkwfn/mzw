package com.mzw.pattern.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    private GenderType gender;
    private MaritalStatusType maritalStatus;
}
