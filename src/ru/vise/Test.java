package ru.vise;

import altr.BackEnd;
import com.google.gson.Gson;
import org.hibernate.Session;
import ru.vise.dao.FactoryDAO;
import ru.vise.entities.ListValueEntity;
import ru.vise.entities.ObjectEntity;
import ru.vise.entities.ParamEntity;
import ru.vise.utils.HibernateSessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        ObjectEntity objectEntity = new ObjectEntity();
//        objectEntity.setName("test main");
//        objectEntity.setDescription("Exp1");
////        FactoryDAO.getInstance().getObjectDAO().addObject(objectEntity);
//        ParamEntity paramEntity = new ParamEntity();
//        paramEntity.setValue("159");
////        FactoryDAO.getInstance().getParamDAO().addParam(paramEntity);
//        Session session = null;
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            session.beginTransaction();
////            ObjectEntity objEnt = session.get(ObjectEntity.class, objectEntity);
////            ParamEntity parEnt = session.get(ParamEntity.class, paramEntity);
////            parEnt.setObjectsByObjectId(objEnt);
////            Integer integer = new Integer(159);
////            System.out.println(integer);
////            objectEntity.setObjectId(111);
////            Integer idParam = (Integer) session.save(paramEntity);
////            System.out.println("In trans idObj " + objectEntity.getObjectId());
//
//            paramEntity.setObjectsByObjectId(objectEntity);
//            objectEntity.getParamsOfObject().add(paramEntity);
////            session.update(paramEntity);
////            session.update(objectEntity);
////            System.out.println("ObjId before save = " + objectEntity.getObjectId());
//            Integer idObj = (Integer) session.save(objectEntity);
////            System.out.println("ObjId after save = " + objectEntity.getObjectId());
//            Integer idParam = (Integer) session.save(paramEntity);
//            session.getTransaction().commit();
////            System.out.println("Out trans idObj " + objectEntity.getObjectId());
//            int size = objectEntity.getParamsOfObject().size();
//            System.out.println("Size Set of Obj = " + size);
//            Iterator<ParamEntity> iterator = objectEntity.getParamsOfObject().iterator();
//            while (iterator.hasNext()){
//                System.out.println("Obj's Param: " + iterator.next().toString());
//            }
//            System.out.println("ObjId = " + objectEntity.getObjectId());
//            System.out.println("ParamId = " + paramEntity.getParamId());
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }

//        List<double[]> result = BackEnd.runSimpleEgo(10, "ParetoDistribution", 0.1, 1,
//                20, 100, 10, 101,
//                0.5, -250, 100, 10, "mu");
//        System.out.println(Arrays.toString(result.get(0)));
//        System.out.println(Arrays.toString(result.get(1)));
//        System.out.println(Arrays.toString(result.get(2)));
//        System.out.println(result.get(0).length);
//        System.out.println(result.get(1).length);
//        System.out.println("Size!!! = " + result.size());

//        System.out.println(result);
//        ListValueEntity listValueEntity = new ListValueEntity();
//        listValueEntity.setNumber(20);
//        listValueEntity.setValue("FUCk");
//        listValueEntity.setAttributesByAttrId(null);
//        Session session = null;
//
//        try {
//            session = HibernateSessionFactory.getSessionFactory().openSession();
//            Integer id = (Integer) session.save(listValueEntity);
//            System.out.println(id);
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }

        String paretoDistribution = new Gson().toJson(BackEnd.runSimpleEgo(10, "ParetoDistribution", 0.1, 1,
                20, 100, 10, 101,
                0.5, -250, 100, 10));
        System.out.println(paretoDistribution);

    }
}
