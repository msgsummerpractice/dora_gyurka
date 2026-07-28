package com.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        // ApplicationContext context = new
        // ClassPathXmlApplicationContext("applicationContext.xml");
        // Intern intern = context.getBean("intern", Intern.class);
        // intern.display();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Intern intern = context.getBean(Intern.class);
        intern.display();

        Greetings greetings = new Greetings(context.getBean(Menthor.class));
        greetings.display();
        ((AnnotationConfigApplicationContext) context).close();
    }
}
