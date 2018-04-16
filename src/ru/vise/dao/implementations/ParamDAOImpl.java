package ru.vise.dao.implementations;


import org.hibernate.Session;
import ru.vise.dao.interfaces.ParamDAO;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*;

public class ParamDAOImpl implements ParamDAO {
    public void deleteParam(int paramId) {

    }

    public void updateParam(ParamEntity paramEntity) {

    }

    public void addParam(ParamEntity paramEntity) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(paramEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateParam(ObjectEntity objectEntity) {

    }

    public void addObject(ObjectEntity objectEntity) {

    }
}
