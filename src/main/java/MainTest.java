
import com.cloud.condition.BeanLifeCycle;
import com.cloud.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class MainTest {
    public static void main(String arg[]){
        //传入配置文件的位置
        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mapper/Person.xml");
        //获取一个bean对象
        Person bean = (Person) applicationContext.getBean("Person");
        System.out.println(bean);*/
        //获取配置类文件 ioc容器
        //AnnotationConfigApplicationContext() 这里加的哪个配置类（注解）
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        /* 获取bean类，
        Person bean = (Person) applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] per = applicationContext.getBeanNamesForType(Person.class);
        for (String person: per) {
            System.out.println(person);
        }*/
        /*String[] names = applicationContext.getBeanDefinitionNames();
        for (String beanNames: names) {
            System.out.println(beanNames);
        }*/
        /*Person bean = (Person) applicationContext.getBean(Person.class);
        System.out.println(bean.getName()+ ' ' + bean.getAge());
        Person bean2 = (Person) applicationContext.getBean(Person.class);
        System.out.println(bean2.getName()+ ' ' + bean2.getAge());
        System.out.println(bean==bean2);
        -Dos.name=linux
        */
        /*Environment environment = applicationContext.getEnvironment();
        String sysName = environment.getProperty("os.name");//获取当前使用的是什么系统
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String s : beanNamesForType) {
            System.out.println("s = " + s);
        }

        System.out.println(sysName);*/

        /*String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        Object colorFactory = applicationContext.getBean("colorFactory");

        System.out.println(colorFactory.getClass());*/

        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(BeanLifeCycle.class);

        System.out.println("容器已经创建！");

        //容器已经关闭(多实例的时候，容器不会管理多实例bean，不会调用销毁的方法)
        applicationContext1.close();
        System.out.println("容器已经关闭！");

    }
}
