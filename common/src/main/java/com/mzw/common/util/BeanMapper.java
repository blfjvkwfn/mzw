package com.mzw.common.util;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BeanMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <T> T map(Object source, Class<T> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    public static <T, E> List<E> mapList(Collection<T> sourceList, Class<E> destinationClass) {
        return (List) sourceList.stream().map(item->map(item, destinationClass)).collect(Collectors.toList());
    }
}
