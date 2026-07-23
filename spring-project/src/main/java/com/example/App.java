package com.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Hello world!
 *
 */

@Configuration
public class App 
{
    
    @Bean
    public Intern intern() {            
        return new Intern();
    }

    public static void main( String[] args )
    {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //Intern intern = context.getBean("intern", Intern.class);
        //intern.display();
     
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        Intern intern = context.getBean(Intern.class);
        intern.display();
    }
}
