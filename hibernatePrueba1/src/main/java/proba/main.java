package proba;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import java.util.List;
import java.util.Scanner;

import utils.Pantalla;
import model.*;

public class main {

    static Session session;
    static SessionFactory sessionFactory;
    static ServiceRegistry serviceRegistry;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {

        Pantalla p = new Pantalla();

        p.comprovarOpcio();

    }

}
