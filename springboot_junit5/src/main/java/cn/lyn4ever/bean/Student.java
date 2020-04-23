package cn.lyn4ever.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 007
 * 2020/4/23
 */
@Component
public class Student {
    private String name;
    private int age;

    @Autowired
    public Student(@Value("张三") String name, @Value("12") int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
