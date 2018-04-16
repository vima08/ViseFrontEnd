package ru.vise.dao;

import ru.vise.dao.implementations.AttributeDAOImpl;
import ru.vise.dao.implementations.ListValueDAOImpl;
import ru.vise.dao.implementations.ObjectDAOImpl;
import ru.vise.dao.implementations.ParamDAOImpl;
import ru.vise.dao.interfaces.AttributeDAO;
import ru.vise.dao.interfaces.ListValueDAO;
import ru.vise.dao.interfaces.ObjectDAO;
import ru.vise.dao.interfaces.ParamDAO;

public class FactoryDAO {
    private static AttributeDAO attributeDAO = null;
    private static ListValueDAO listValueDAO = null;
    private static ObjectDAO objectDAO = null;
    private static ParamDAO paramDAO = null;
    private static FactoryDAO instance = null;

    public static synchronized FactoryDAO getInstance(){
        if (instance == null){
            instance = new FactoryDAO();
        }
        return instance;
    }

    public static AttributeDAO getAttributeDAO() {
        if (attributeDAO == null){
            attributeDAO = new AttributeDAOImpl();
        }
        return attributeDAO;
    }

    public static ListValueDAO listValueDAO() {
        if (listValueDAO == null){
            listValueDAO = new ListValueDAOImpl();
        }
        return listValueDAO;
    }
    public static ObjectDAO getObjectDAO() {
        if (objectDAO == null) {
            objectDAO = new ObjectDAOImpl();
        }
        return objectDAO;
    }

    public static ParamDAO getParamDAO() {
        if (paramDAO == null){
            paramDAO = new ParamDAOImpl();
        }
        return paramDAO;
    }
}
