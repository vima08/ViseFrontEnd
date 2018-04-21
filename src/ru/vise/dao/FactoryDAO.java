package ru.vise.dao;


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
}
