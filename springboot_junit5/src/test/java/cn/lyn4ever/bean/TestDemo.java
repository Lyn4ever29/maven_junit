package cn.lyn4ever.bean;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 微信公众号 小鱼与Java
 *
 * 2020/4/23
 */
@SpringBootTest //它默认会为我们加载Spring容器，
public class TestDemo {

    @Resource
    private Student student;

    @Test
    void fun1(){
        System.out.println(student.getName());
    }

}
