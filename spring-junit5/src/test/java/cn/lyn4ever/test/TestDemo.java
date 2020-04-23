package cn.lyn4ever.test;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 微信公众号 “小鱼与Java”
 *
 * @date 2020/4/23
 * @auther Lyn4ever
 */
public class TestDemo {

    @Test
    void fun1() {
        System.out.println("欢迎关注我的微信公众号——小鱼与Java");
        String[] properties = {"a","b"};
        List list = CollectionUtils.arrayToList(properties);
        System.out.println(list.contains("b"));
    }


}
