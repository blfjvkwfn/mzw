package com.mzw.pattern.filter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream().filter(item-> item.getMaritalStatus().equals(MaritalStatusType.SINGLE)).collect(Collectors.toList());
    }
}
