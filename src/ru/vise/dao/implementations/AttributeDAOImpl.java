package ru.vise.dao.implementations;


import org.hibernate.Session;
import ru.vise.dao.interfaces.AttributeDAO;
import ru.vise.entities.AttributeEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*;

public class AttributeDAOImpl implements AttributeDAO {
    public void deleteAttribute(int attrId) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(attrId);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateAttribute(AttributeEntity attributeEntity) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(attributeEntity);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void addAttribute(AttributeEntity attributeEntity) {

    }
}
