package com.mzw.pattern.filter;

import java.util.List;

/**
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public interface Criteria {
    List<Person> meetCriteria(List<Person> persons);
}
