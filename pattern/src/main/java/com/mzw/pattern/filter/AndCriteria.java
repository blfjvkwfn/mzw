package com.mzw.pattern.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Data
@AllArgsConstructor
public class AndCriteria implements Criteria {
    private Criteria criteria;
    private Criteria otherCriteria;

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return otherCriteria.meetCriteria(criteria.meetCriteria(persons));
    }
}
