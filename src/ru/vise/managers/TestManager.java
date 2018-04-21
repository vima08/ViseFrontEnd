//TODO Max Этот класс надо выпилить, но здесь должны появиться твои классы по работе с базой, которые ты будешь
//TODO добавлять (inject) в рестовые сервисы, не надо прямо на месте использовать hibernate

package ru.vise.managers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import testPack.TestTableEntity;

public class TestManager {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

//    public static TestTableEntity get() {
//        TestTableEntity object = null;
//        final Session session = getSession();
//        try {
//            object  = session.find(TestTableEntity.class, 1);
//        } finally {
//            session.close();
//        }
//        return object;
//    }
}
