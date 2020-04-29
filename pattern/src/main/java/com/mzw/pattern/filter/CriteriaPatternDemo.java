package com.mzw.pattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤者模式
 *
 * @author Jonathan Meng
 * @date 08/05/2019
 */
public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Robert", GenderType.MALE, MaritalStatusType.SINGLE));
        persons.add(new Person("John", GenderType.MALE, MaritalStatusType.MARRIED));
        persons.add(new Person("Laura", GenderType.FEMALE, MaritalStatusType.MARRIED));
        persons.add(new Person("Diana", GenderType.FEMALE, MaritalStatusType.SINGLE));
        persons.add(new Person("Mike", GenderType.MALE, MaritalStatusType.SINGLE));
        persons.add(new Person("Bobby", GenderType.MALE, MaritalStatusType.SINGLE));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(single, male);
        Criteria singleOrFemale = new OrCriteria(single, female);

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle: ");
        printPersons(single.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        persons.forEach(item -> System.out.println(item.toString()));
    }
}
