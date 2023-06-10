package controller;

import java.util.ArrayList;

import bo.BoFactory;
import bo.custom.CustomerBo;
import bo.custom.ItemBo;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<OrderTM> tblItem;

    @FXML
    private TextField txtBuyingQty;

    CustomerBo customerBo;
    ItemBo itemBo;

    public void initialize() {
        customerBo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
        itemBo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);

        loadCustomerID();
        loadItemCode();
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

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
