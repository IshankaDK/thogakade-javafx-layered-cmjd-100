package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import bo.BoFactory;
import bo.custom.CustomerBo;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import view.tm.CustomerTM;

public class CustomerController {

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerSalary;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, Integer> colID;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private TableColumn<CustomerTM, Double> colSalary;

    @FXML
    private TableColumn<CustomerTM, Button> colDeleteButton;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    CustomerBo bo;

    public void initialize() throws ClassNotFoundException, SQLException {

        bo = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDeleteButton.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        getCustomers();
    }

    public void getCustomers() {

        try {
            ArrayList<CustomerDTO> allCustomer = bo.getAllCustomer();
            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            for (CustomerDTO customer : allCustomer) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
                btnDelete.setTextFill(Color.web("#ecf0f1"));
                CustomerTM customerTM = new CustomerTM(customer.getId(), customer.getName(),
                        customer.getAddress(),
                        customer.getSalary(), btnDelete);
                tmList.add(customerTM);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();
                    try {
                        if (result.orElse(no) == ok) {
                            if (bo.deleteCustomer(customerTM.getId())) {
                                new Alert(AlertType.CONFIRMATION, "Customer is deleted.!").show();
                                getCustomers();
                                return;
                            }
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                });
            }

            tblCustomer.setItems(tmList);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        try {
            // get inserted data from the text fields
            int id = Integer.parseInt(txtCustomerId.getText());
            String name = txtCustomerName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtCustomerSalary.getText());

            CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);

            boolean isSaved = bo.saveCustomer(customerDTO);

            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Customer is saved.!").show();
                // load all customers after save new one
                getCustomers();
            } else {
                new Alert(AlertType.ERROR, "Customer is not saved..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void txtCustomerIDOnAction(ActionEvent event) {

        try {
            int id = Integer.parseInt(txtCustomerId.getText());
            CustomerDTO dto = bo.getCustomer(id);

            if (dto != null) {
                txtCustomerName.setText(dto.getName());
                txtAddress.setText(dto.getAddress());
                txtCustomerSalary.setText(String.valueOf(dto.getSalary()));
            } else {
                new Alert(AlertType.ERROR, "Customer Not Found. Please check the customer id and try again.!").show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            // get inserted data from the text fields
            int id = Integer.parseInt(txtCustomerId.getText());
            String name = txtCustomerName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtCustomerSalary.getText());

            CustomerDTO customerDTO = new CustomerDTO(id, name, address, salary);

            boolean isUpdated = bo.updateCustomer(customerDTO);

            if (isUpdated) {
                new Alert(AlertType.CONFIRMATION, "Customer is updated.!").show();
                // load all customers after save new one
                getCustomers();
            } else {
                new Alert(AlertType.ERROR, "Customer is not updated..!").show();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            new Alert(AlertType.ERROR, "class is not Found " + classNotFoundException).show();
        } catch (SQLException sqlException) {
            new Alert(AlertType.ERROR, "SQl Exception " + sqlException).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
