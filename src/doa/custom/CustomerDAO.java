package doa.custom;

import java.util.ArrayList;

import doa.CrudDAO;
import entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, Integer> {
    // custom data expect CRUD
    // ex:
    public ArrayList<Customer> getCustomerID() throws Exception;
}
