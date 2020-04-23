package cn.lyn4ever.test;

import cn.lyn4ever.spring.bean.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 微信公众号 “小鱼与Java”
 *
 * @date 2020/4/23
 * @auther Lyn4ever
 */
@SpringJUnitConfig
//指定配置文件路径，会先从test域中找
@ContextConfiguration("classpath:application.xml")
public class SpringTest {

    @Resource
    private Teacher teacher;

    @Test
    void fun(){
        System.out.println(teacher.getName());
    }

}
