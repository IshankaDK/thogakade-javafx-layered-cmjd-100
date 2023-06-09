package bo;

import bo.custom.impl.CustomerBoImpl;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory() {

    }

    public static BoFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BoFactory()) : boFactory;
    }

    public enum BoType {
        CUSTOMER, ITEM, ORDER
    }

    public <T> T getBo(BoType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            // case ITEM:
            // return null;
            default:
                return null;
        }

    }

}
