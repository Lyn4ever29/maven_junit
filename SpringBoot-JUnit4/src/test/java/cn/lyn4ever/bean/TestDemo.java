package cn.lyn4ever.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 微信公众号 小鱼与Java
 *
 * 2020/4/23
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDemo {

    @Resource
    private Student student;

    @Test
   public void fun1(){
        System.out.println(student.getName());
    }

}
