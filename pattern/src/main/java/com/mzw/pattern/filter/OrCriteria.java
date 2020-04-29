package com.mzw.pattern.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
@Data
@AllArgsConstructor
public class OrCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> people = criteria.meetCriteria(persons);
        people.addAll(otherCriteria.meetCriteria(persons).stream().filter(item->!people.contains(item)).collect(Collectors.toList()));
        return people;
    }
}
