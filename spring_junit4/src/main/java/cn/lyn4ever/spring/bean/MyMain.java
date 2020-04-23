package cn.lyn4ever.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用普通的Spring上下文来加载Spring容器
 *
 * @auther 微信公众号：小鱼与Java
 * 2020/4/23
 */
public class MyMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        Teacher teacher = (Teacher) ctx.getBean("teacher");
        System.out.println(teacher.getName());
    }
}
