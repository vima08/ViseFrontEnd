package ru.vise.dao.implementations;

import org.hibernate.Session;
import ru.vise.dao.interfaces.ObjectDAO;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class ObjectDAOImpl implements ObjectDAO {

    public void deleteObject(int objectId) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(objectId);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public void updateObject(ObjectEntity objectEntity) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(objectEntity);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public int addObject(ObjectEntity objectEntity) {
        Session session = null;
        int objectId = -1;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(objectEntity);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        objectId = objectEntity.getObjectId();
        return objectId;
    }

    public Set<ParamEntity> getAllParam(int objectId) {
        Session session = null;
        Set<ParamEntity> params = new HashSet<ParamEntity>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            ObjectEntity objectEntity = session.get(ObjectEntity.class, objectId);
            session.beginTransaction();
            params = objectEntity.getParamsOfObject();
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return params;
    }

    public ObjectEntity getObject(int objectId) {
        Session session = null;
        ObjectEntity objectEntity = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            objectEntity = session.get(ObjectEntity.class, objectId);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return objectEntity;
    }

    @Override
    public ObjectEntity getObject(ObjectEntity objectEntity) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
//            objectEntity = session.get();
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
        return objectEntity;
    }

    @Override
    public void addParams(Set<ParamEntity> paramEntities, int objectId) {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            ObjectEntity objectEntity = session.get(ObjectEntity.class, objectId);
            objectEntity.setParamsOfObject(paramEntities);
            session.getTransaction().commit();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
