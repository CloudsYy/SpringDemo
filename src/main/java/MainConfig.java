import com.cloud.condition.linuxCondition;
import com.cloud.condition.windowsCondition;
import com.cloud.entity.Color;
import com.cloud.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

//配置类==配置文件
//注解类的使用！
//excludeFilters Filter[] 指定扫描的时候按照什么规则，排除哪些组件
//includeFilters Filter[] 指定扫描的时候按照哪些规则，包含哪些规则
/*excludeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = Controller.class)
        useDefaultFilters 默认值为true*/
@Configuration //告诉spring 这是一个配置类
@ComponentScan(value = "com.cloud",includeFilters = {
        @Filter(type = FilterType.CUSTOM,classes = MyDataType.class)   //可以用不同种方式来过滤，正则，注解，类，自定义等方式
},useDefaultFilters = false)
public class MainConfig {
    /*给容器中注册一个bean 类型为返回值类型，id默认为方法名*/
    /*
    request 同一次请求创建一个实例

    session 同一个session创建一个实例
    prototype 多实例的 ioc容器启动的时候不会自动调用方法创建对象，而是获取的时候，调用方法(new)创建对象，每个对象都不一样
    singleton 单实例的(默认值),ioc容器启动的时候自动调用方法，并把创建的对象放在ioc容器中，
              以后每次获取就是直接从容器中(map.get())中拿。
    scope 注解用来调整作用域
    @Lazy 懒加载
         单实例bean，默认在容器启动的时候创建对象
         懒加载，容器启动不创建对象，第一次使用(获取)bean对象，并初始化；
    * */
    @Scope("prototype")
    @Lazy
    @Bean("person") //这里设置别名
    public Person person01(){
         return new Person("张三", 22);
    }

    /*
    * @Conditional({condition})  按照一定的条件进行判断，满足条件给容器中注册bean
    * 这个注解也可以放在类中，这个类中配置的所有bean注册到容器中才有效，在类中统一设置
    * */

    @Conditional({windowsCondition.class})
    @Bean("bill")
    public Person person02(){
        return new Person("比尔", 62);
    }

    @Conditional(linuxCondition.class)
    @Bean("linus")
    public Person person03(){
        return new Person("林纳斯", 32);
    }
}
