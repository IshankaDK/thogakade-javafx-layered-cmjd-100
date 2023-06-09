package bo.custom.impl;

import java.util.ArrayList;

import bo.custom.CustomerBo;
import doa.DaoFactory;
import doa.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
        return dao.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return dao.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }

    @Override
    public boolean deleteCustomer(Integer id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(Integer id) throws Exception {
        Customer customer = dao.get(id);
        if (customer != null) {
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        }
        return null;

    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        ArrayList<Customer> list = dao.getAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();

        for (Customer c : list) {
            dtoList.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress(), c.getSalary()));
        }
        return dtoList;
    }

}
