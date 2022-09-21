package bat.ke.qq.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NacosConfigApplication {
    
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        
        while (true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔3秒中从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("common.name");
            String userAge = applicationContext.getEnvironment().getProperty("common.age");
            System.err.println("common name :" + userName + "; age: " + userAge);
            TimeUnit.SECONDS.sleep(3);
        }
        
    }
    
}