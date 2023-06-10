package entity;

public class OrderDetail implements SuperEntity {
    private String orderId;
    private String code;
    private double qty;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String code, double qty, double unitPrice) {
        this.orderId = orderId;
        this.code = code;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
