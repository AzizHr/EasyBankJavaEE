package controllers;

import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            // Configure Hibernate properties
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/easy_bank");
            configuration.setProperty("hibernate.connection.username", "postgres");
            configuration.setProperty("hibernate.connection.password", "aziz");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hbm2ddl.auto", "update");


            // Add annotated classes
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Demand.class);
            configuration.addAnnotatedClass(CurrentAccount.class);
            configuration.addAnnotatedClass(Agency.class);
            configuration.addAnnotatedClass(Mission.class);
            configuration.addAnnotatedClass(MissionHistory.class);
            configuration.addAnnotatedClass(Operation.class);
            configuration.addAnnotatedClass(Payment.class);
            configuration.addAnnotatedClass(RecruitmentHistory.class);
            configuration.addAnnotatedClass(SavingAccount.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

}
