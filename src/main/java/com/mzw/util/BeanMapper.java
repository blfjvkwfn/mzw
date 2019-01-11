package com.mzw.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.assertj.core.util.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BeanMapper {
    private static Mapper dozer = DozerBeanMapperBuilder.buildDefault();

    private BeanMapper()
    {
    }

    public static <T> T map(Object source, Class<T> destinationClass)
    {
        return dozer.map(source, destinationClass);
    }

    public static List mapList(Collection sourceList, Class destinationClass)
    {
        List destinationList = Lists.newArrayList();
        Object destinationObject;
        for (Iterator iterator = sourceList.iterator(); iterator.hasNext(); destinationList.add(destinationObject))
        {
            Object sourceObject = iterator.next();
            destinationObject = dozer.map(sourceObject, destinationClass);
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject)
    {
        dozer.map(source, destinationObject);
    }
}
