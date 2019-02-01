package com.mzw.common;

import com.mzw.common.model.A;
import com.mzw.common.model.B;
import com.mzw.common.util.BeanMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengzhaowei@boce.cn
 * @date 2019/2/1 17:08
 */
public class BeanMapperTest {
    @Test
    public void mapList() {
        String name = "zhang";
        int age = 10;
        List<A> list = new ArrayList<>(1);
        list.add(new A(name,age));
        List<B> list1 = BeanMapper.mapList(list, B.class);
        Assert.assertEquals(name, list1.get(0).getName());
        Assert.assertEquals(age, list1.get(0).getAge());
    }
}
