import com.cloud.condition.ColorFactory;
import com.cloud.condition.MyBeanDefinitionRegistrar;
import com.cloud.condition.MyImportSelector;
import com.cloud.entity.Black;
import com.cloud.entity.Color;
import com.cloud.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

//配置类==配置文件
/*excludeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = Controller.class)}
        useDefaultFilters 默认值为true*/
@Configuration //告诉spring 这是一个配置类
@ComponentScan(value = "com.cloud",includeFilters = {
        @Filter(type = FilterType.CUSTOM,classes = MyDataType.class)   //可以用不同种方式来过滤，正则，注解，类，自定义等方式
},useDefaultFilters = false)
@Import({Color.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class})//该组件，id默认的是全类名，导入多个可以{color.class,person.class}
public class MainConfig2 {
    /*给容器中注册一个bean 类型为返回值类型，id默认为方法名*/
    //默认为单实例
    @Scope("prototype")
    @Bean("person")
    public Person person01(){
         return new Person("张三三", 23);
    }


    /*
     * 给容器中添加组件
     * 1) 包扫描+组件标注注解(如:@Controller、@Repository、@Service、@Component)
     * 2） @Bean（导入的第三方包）
     * 3) @Import 快速给容器导入一个组件
     * 4) @ImportSelector 导入的选择器：导入全类名的数组
     * 5) ImportBeanDefinitionRegistrar 手工注册bean
     * 6) 使用spring提供的FactoryBean (工厂bean)
     * 7) 默认获取到的是工厂bean调用的getObject创建的对象
     * 8) 要获取工厂Bean的本身，我们需要在id前面加一个&，如下:
     *    &colorFactoryBean
     * */
    @Bean
    public ColorFactory colorFactory(){
        return new ColorFactory();
    }
}
