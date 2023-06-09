package doa.custom;

import doa.CrudDAO;
import entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, Integer> {
    // custom data expect CRUD
    // ex:
    // public void getCustomerID();
}
