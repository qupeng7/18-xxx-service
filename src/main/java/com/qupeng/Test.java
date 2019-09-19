package com.qupeng;

import com.qupeng.config.DubboServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(DubboServiceConfig.class);

        //启动了spring ioc容器后，不要退出，以便于其他服务可以调用
        System.in.read();

        //也ok
        //Thread.sleep(1000000000);
    }
}
