package doa;

import doa.custom.impl.CustomerDaoImpl;
import doa.custom.impl.ItemDaoImpl;
import doa.custom.impl.OrderDaoImpl;
import doa.custom.impl.OrderDetailDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DaoFactory()) : daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDaoImpl();
            case ORDER:
                return (T) new OrderDaoImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDaoImpl();
            default:
                return null;
        }

    }

}
