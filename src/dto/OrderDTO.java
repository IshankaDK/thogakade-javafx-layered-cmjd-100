package dto;

public class OrderDTO {
    private String orderID;
    private String orderDate;
    private int customerID;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderDate, int customerID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

}
