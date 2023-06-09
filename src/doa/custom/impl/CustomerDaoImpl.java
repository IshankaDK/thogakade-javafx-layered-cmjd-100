package doa.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import doa.CrudUtil;
import doa.custom.CustomerDAO;
import entity.Customer;

public class CustomerDaoImpl implements CustomerDAO {

    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",
                customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?",
                customer.getName(), customer.getAddress(), customer.getSalary(), customer.getId());
    }

    @Override
    public Customer get(Integer id) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", id);
        if (set.next()) {
            return new Customer(set.getInt(1), set.getString(2),
                    set.getString(3), set.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerList = new ArrayList<>();

        while (set.next()) {
            customerList.add(new Customer(
                    set.getInt(1), set.getString(2),
                    set.getString(3), set.getDouble(4)));
        }

        return customerList;
    }

}
