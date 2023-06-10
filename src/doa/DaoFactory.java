package doa;

import doa.custom.impl.CustomerDaoImpl;
import doa.custom.impl.ItemDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DaoFactory()) : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, ORDER
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            default:
                return null;
        }

    }

}
