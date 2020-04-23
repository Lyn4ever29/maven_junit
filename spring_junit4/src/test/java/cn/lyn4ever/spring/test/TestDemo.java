package cn.lyn4ever.spring.test;

import cn.lyn4ever.spring.bean.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author 微信公众号: 小鱼与Java
 * 2020/4/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class TestDemo {
    @Resource
    private Teacher teacher;

    @Test
    public void fun(){
        System.out.println(teacher.getName());
    }
}
