package bo.custom;

import java.util.ArrayList;

import dto.CustomerDTO;

public interface CustomerBo {

    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception;

    public boolean deleteCustomer(Integer id) throws Exception;

    public CustomerDTO getCustomer(Integer id) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    // other logics

}
