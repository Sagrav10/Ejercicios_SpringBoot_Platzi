package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    // =====================================
    // Attributes
    // =====================================

    private Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private ComponentDependency componentDependency;

    private MyBean myBean;

    private MyBeanWithDependency myBeanWithDependency;

    private MyBeanWithProperties myBeanWithProperties;

    private UserPojo userPojo;

    private UserRepository userRepository;

    // =====================================
    // Constructor
    // =====================================

    /*
     * ComponentDependency - Parametro para la inyeccion de componentes
     * Qualifer - Nombre del componente a utilizar
     */
    public FundamentosApplication(
            @Qualifier("componentTwoImplement") ComponentDependency componentDependency,
            MyBean myBean,
            MyBeanWithDependency myBeanWithDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo,
            UserRepository userRepository
    ) {

        // Inyeccion de componentes
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
    }

    // =====================================
    // Flujo de procesos
    // =====================================

    @Override
    public void run(String... args) {
        // Call previous test in the classes
        this._previousTest();

        // Save users data in data base
        this._saveUsersInDataBase();
    }


    // =====================================
    // Private methods
    // =====================================

    private void _saveUsersInDataBase() {
        User user1 = new User("Sebastian", "jsvargasq@hotmail.com", LocalDate.of(2001, 3, 10));
        User user2 = new User("Juan", "jsvargasq@unbosque.edu.co", LocalDate.of(2021, 6, 22));
        User user3 = new User("User3", "correo3@hotmail.com", LocalDate.of(2021, 3, 23));
        User user4 = new User("User4", "correo4@hotmail.com", LocalDate.of(2021, 4, 24));
        User user5 = new User("User5", "correo5@hotmail.com", LocalDate.of(2021, 5, 25));
        User user6 = new User("User6", "correo6@hotmail.com", LocalDate.of(2021, 6, 26));
        User user7 = new User("User7", "correo7@hotmail.com", LocalDate.of(2021, 7, 27));
        User user8 = new User("User8", "correo8@hotmail.com", LocalDate.of(2021, 8, 28));
        User user9 = new User("User9", "correo9@hotmail.com", LocalDate.of(2021, 9, 29));
        User user10 = new User("User10", "correo10@hotmail.com", LocalDate.of(2021, 10, 10));
        User user11 = new User("User11", "correo11@hotmail.com", LocalDate.of(2021, 11, 11));
        User user12 = new User("User12", "correo12@hotmail.com", LocalDate.of(2021, 12, 12));

        // Create array with all users
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);

        // Save each user of list
        users.stream().forEach( userRepository::save );
    }

    private void _previousTest() {
        // Call of the 'saludar' method
        componentDependency.saludar();

        // Call 'print' of Bean
        myBean.print();

        // Call 'print with dependency' on bean with dependency
        myBeanWithDependency.printWithDependency();

        // Call my bean with properties
        System.out.println(myBeanWithProperties.fuction());

        System.out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " _ Age: " + userPojo.getAge());

        // Example of Error Log
        try {
            int value = 10 / 0;
            LOGGER.debug("Response of operation in Try - Catch is: " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error en Try - Catch: " + e.getMessage());
        }
    }

    // =====================================
    // Main
    // =====================================

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }
}
