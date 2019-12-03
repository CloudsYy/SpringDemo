import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
//自定义的扫描过滤规则
public class MyDataType implements TypeFilter {
    /*
    * metadataReader 读取到的当前正在扫描的类的信息
    * metadataReaderFactory 可以获取到其他任何类信息的
    * */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前注解类的信息
        AnnotatedTypeMetadata annotatedTypeMetadata = metadataReader.getAnnotationMetadata();
        //获取当前的类正在扫描的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前的类资源
        Resource resource = metadataReader.getResource();
        //获取类名信息
        String className = classMetadata.getClassName();


        if (className.contains("er")){
            return true;
        }
        return false;
    }
}
