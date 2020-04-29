package com.mzw.pattern.composite;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@ToString(exclude = "subordinates")
@Getter
@Setter
@RequiredArgsConstructor
public class Employee {
    @NonNull
    private String name;
    @NonNull
    private String dept;
    @NonNull
    private double salary;
    private List<Employee> subordinates = new ArrayList<>();

    public void add(Employee employee) {
        subordinates.add(employee);
    }
}
