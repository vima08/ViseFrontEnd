package ru.vise.dao.implementations;

import org.hibernate.Session;
import ru.vise.dao.interfaces.ListValueDAO;
import ru.vise.entities.ListValueEntity;
import ru.vise.utils.HibernateSessionFactory;


import javax.swing.*;

public class ListValueDAOImpl implements ListValueDAO {
    public void deleteListValue(int listValueId) {

    }

    public void updateListValue(ListValueEntity listValueEntity) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(listValueEntity);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void addListValue(ListValueEntity listValueEntity) {

    }
}
