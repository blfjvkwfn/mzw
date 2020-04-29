package com.mzw.pattern.filter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream().filter(item->item.getGender().equals(GenderType.FEMALE)).collect(Collectors.toList());
    }
}
