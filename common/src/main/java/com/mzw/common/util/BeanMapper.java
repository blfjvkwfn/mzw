package com.mzw.common.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
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
        List destinationList = new ArrayList(16);
        sourceList.forEach(sourceObject -> {
            destinationList.add(dozer.map(sourceObject, destinationClass));
        });

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject)
    {
        dozer.map(source, destinationObject);
    }
}
