package controller;

import java.util.ArrayList;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import bo.custom.OrderBo;
import dto.CustomerDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import view.tm.OrderTM;

public class OrderController {

    @FXML
    private ComboBox<Integer> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<OrderTM, String> colCode;

    @FXML
    private TableColumn<OrderTM, String> colDescription;

    @FXML
    private TableColumn<OrderTM, Double> colQty;

    @FXML
    private TableColumn<OrderTM, Double> colQtyOnHand;

    @FXML
    private TableColumn<OrderTM, Button> colRemove;

    @FXML
    private TableColumn<OrderTM, Double> colTotal;

    @FXML
    private TableColumn<OrderTM, Double> colUnitPrice;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<OrderTM> tblOrder;

    @FXML
    private TextField txtBuyingQty;

    @FXML
    private TextField txtOrderId;

    CustomerBo customerBo;
    ItemBo itemBo;
    OrderBo bo;

    public void initialize() {
        customerBo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
        itemBo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);
        bo = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);

        loadCustomerID();
        loadItemCode();
    }

    ObservableList<OrderTM> tmList = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        try {
            String code = cmbItemCode.getValue();
            String description = lblDescription.getText();
            Double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            Double qtyOnHand = Double.parseDouble(lblQtyOnHand.getText());
            Double qty = Double.parseDouble(txtBuyingQty.getText());
            Double total = qty * unitPrice;
            Button btnRemove = new Button("Remove");
            btnRemove.setMaxSize(100, 50);
            btnRemove.setCursor(Cursor.HAND);
            btnRemove.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
            btnRemove.setTextFill(Color.web("#ecf0f1"));

            tmList.add(new OrderTM(code, description, unitPrice, qtyOnHand, qty, total, btnRemove));
            tblOrder.setItems(tmList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            boolean isSaved = bo.saveOrder(getOrder(), getOrderDetail());
            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Order is saved.!").show();

            } else {
                new Alert(AlertType.ERROR, "Order is not saved..!").show();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public OrderDTO getOrder() {
        String orderId = txtOrderId.getText();
        String orderDate = "2023-03-03";
        Integer customerID = cmbCustomerID.getValue();

        return new OrderDTO(orderId, orderDate, customerID);
    }

    public ArrayList<OrderDetailDTO> getOrderDetail() {
        String orderId = txtOrderId.getText();

        ArrayList<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            OrderTM orderTM = tmList.get(i);
            orderDetailDTOs
                    .add(new OrderDetailDTO(orderId, orderTM.getCode(), orderTM.getQty(), orderTM.getUnitPrice()));

        }
        return orderDetailDTOs;

    }

    public void loadCustomerID() {
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDTO> allCustomerID = customerBo.getAllCustomerID();
            for (CustomerDTO dto : allCustomerID) {
                customerIDList.add(dto.getId());
            }
            cmbCustomerID.setItems(customerIDList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadItemCode() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> allItemCode = itemBo.getAllItemCode();
            for (ItemDTO dto : allItemCode) {
                itemCodeList.add(dto.getCode());
            }
            cmbItemCode.setItems(itemCodeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {

        Integer id = cmbCustomerID.getValue();

        try {
            CustomerDTO dto = customerBo.getCustomer(id);
            if (dto != null) {
                lblCustomerName.setText(dto.getName());
                lblAddress.setText(dto.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void cmdItemCodeOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        try {
            ItemDTO dto = itemBo.getItem(code);
            if (dto != null) {
                lblDescription.setText(dto.getDescription());
                lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
