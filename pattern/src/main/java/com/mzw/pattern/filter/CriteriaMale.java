package com.mzw.pattern.filter;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream().filter(item-> item.getGender().equals(GenderType.MALE)).collect(Collectors.toList());
    }
}
