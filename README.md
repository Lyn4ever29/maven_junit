有些人认为，写单元测试就是在浪费时间 ，写完代码，依然还是能够进行测试的。但是，还是建议写单元测试的，可以让你的条理更加清晰，而且当某个功能出现问题时，可能通过单元测试很容易的定位和解决问题。本文主要总结下在Spring及SpringBoot项目中，使用单元测试时的方法。将JUnit4和JUnit5对比着来写，因为我发现我身边的同事经常搞不明白要怎么用。

# Juint版本说明

> 这里主要说明下它们在Maven下的依赖包
## Junit4
```xml
<dependency>
	<groupId>junit</groupId>
	<artifactId>junit</artifactId>
	<version>4.13</version>
  <!--请注意这个scope的用法-->
  <scope>test</scope>
</dependency>
```

## Junit5

```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.6.2</version>
  <!--请注意这个scope的用法-->
  <scope>test</scope>
</dependency>
```

在上边的依赖中，两个依赖分别写了scope属性，这里做一个讲解：

一个标准的maven项目结构如下图：

![](https://gitee.com/lyn4ever/picgo-img/raw/master/img/20200423205406.png)

写Java代码的地方有两个src/main/java和src/test/java。如果我们不在上边依赖中添加scope为test属性，就可以在这两个地方任意地方写@Test测试方法，但是，如果添加了这个属性，就只能在src/test/java下写单元测试代码，这个就是maven所谓的test域。从上图也可以看出，test域可以有自己的配置文件，如果没有的话就会去加载main下的resources的配置文件，如果有，则以自己的为优先。



# Junit5常见注解及其用法

不管使用哪一种方法，一个标准的单元测试方法如下：

```java
public class TestDemo {
		
    @Test
    void fun1(){
        System.out.println("欢迎关注我的微信公众号——小鱼与Java");
    }
}
```

但是对于Junit4而言，所有的测试方法应当是public声明的，而Junit5不用。只不过不同的版本，这个@Test的类是不同的：

```java
Junit4: org.junit.Test
Junit5: org.junit.jupiter.api.Test
```

相比Junit4而言，5添加了新的一些注解，但是常用的注解还是相同的，主要有以下：

| 注解                     | Description                                                  |
| :----------------------- | :----------------------------------------------------------- |
| @Test                  | 写在一个测试类中的测试方法中的元注解，也就是说，在每一个单元测试方法上都应加上它才会生效 |
| @ParameterizedTest    | 参数化测试，就是在你的测试方法执行时，自动添加一些参数       |
| @RepeatedTest            | 重复此测试方法                                               |
| @TestFactory           | 动态测试的工厂方法 |
| @TestTemplate          | 测试模板                                                     |
| @TestMethodOrder       | 测试方法的执行顺序，默认是按照代码的前后顺序执行的 |
| @DisplayName           | 自定义测试方法的名称显示                                     |
| @DisplayNameGeneration | 自定义名称生成器 |
| @BeforeEach            | 在Junit4中，这个注解叫@Before。就是会在每一个测试方法执行前都会执行的方法，包括`@Test`, `@RepeatedTest`, `@ParameterizedTest`,或者 `@TestFactory`注解的方法 |
| @AfterEach             | 和上边很相似，在Junit4中，这个注解叫@After。就是会在每一个测试方法执行之后都会执行的方法，包括`@Test`, `@RepeatedTest`, `@ParameterizedTest`, 或者`@TestFactory`注解的方法. |
| @BeforeAll             | 在当前测试类中的方法执行前执行，只会执行一次，在Junit4中是@BeforeClass |
| @AfterAll              | 在当前测试类中的所有测试方法执行完之后执行，只会执行一次，在Junit4中是@AfterClass |
| @Nested                | 表示一个非静态的测试方法，也就是说@BeforeAll和@AfterAll对此方法无效，如果单纯地执行此方法，并不会触发这个类中的@BeforeAll和@AfterAll方法 |
| @Tag                   | 自定义tag，就是可以自定义一个属于自己的@Test一样功能的注解 |
| @Disabled              | 表明此方法不可用，并不会执行，在JUnit4中的@Ignore |
| @Timeout               | 设定方法执行的超时时间，如果超过，就会抛出异常 |

> 以上是在JUnit5中最常用的注解，可以自己挨个试下，一下子就会明白其用法。关注我，后续为您递上具体用法。

# 在普通Maven项目中使用Junit

> 引入相关依赖后，然后在对应的位置进行测试就可以了，这里不做演示，可以自行下载代码查看	

# 在Spring项目中使用Junit
这里的Spring和SpringBoot项目也是基于Maven构建的，和普通Maven项目的最大区别就是加载Sprign容器而已，一般来说，使用Spring提供的上下文ApplicationContext就可以从配置文件件或者配置类加载Spring容器。如下代码：
```java
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
```

但是，我们可以通过引入Spring相关的test依赖来让其自动加载Spring上下文，这样我们就能利用如@Autowired这样的自动注入方式来获取bean了

```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-test</artifactId>
  <version>5.2.5.RELEASE</version>
</dependency>
```

但是这里对于JUnit4和JUnit5写测试方法时有一点儿不同之处，如下：

## Junit4

```java
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
```

## Junit5

```java
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
```

它们都加了额外的注解来加载Spring上下文的

# 在SpringBoot项目中使用Junit

在SpringBoot中，为我们提供了一个SpringBootTest的注解来加载Spring容器。在SpringBoot2.2.0以前是JUnit4，在SpringBoot之后是JUnit5。但是我建议最应该使用JUnit5。

## Junit4

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.1.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>2.1.6.RELEASE</version>
    <!--表示只能在maven的测试域中使用-->
    <scope>test</scope>
  </dependency>
</dependencies>
```

```java
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
```

## Junit5

```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.2.6.RELEASE</version>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>2.2.6.RELEASE</version>
    <!--表示只能在maven的测试域中使用-->
    <scope>test</scope>
    <exclusions>
      <!--这个是JUnit5中为了支持使用JUint4所做的一个过度
       也就是说，你只需要在你的JUnit4旧项目中添加主个依赖，
       就能完美过渡，而不用修改之前代码
       这里用不到，自然也就排除了。当然，这里，它无关紧要
			-->
      <exclusion>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
</dependencies>
```

```java
@SpringBootTest //它默认会为我们加载Spring容器，
public class TestDemo {

    @Resource
    private Student student;

    @Test
    void fun1(){
        System.out.println(student.getName());
    }
}
```

> 为什么在SpringBoot中不用指定Spring容器的配置文件？

​	其实他是会自动加载类路径下的那个SpringBoot的启动类的，就算指定配置文件，也是指定那个启动类为配置类。如果你写的包结构不符合它的要求，就需要自己使用@ContextConfiguration注解来指定Spring的配置类了