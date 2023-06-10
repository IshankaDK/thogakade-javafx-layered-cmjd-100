package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import bo.BoFactory;
import bo.custom.ItemBo;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import view.tm.ItemTM;

public class ItemController {
    @FXML
    private TableColumn<ItemTM, String> colCode;

    @FXML
    private TableColumn<ItemTM, Button> colDeleteButton;

    @FXML
    private TableColumn<ItemTM, String> colDescription;

    @FXML
    private TableColumn<ItemTM, Double> colQty;

    @FXML
    private TableColumn<ItemTM, Double> colUnitPrice;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    ItemBo bo;

    public void initialize() {
        bo = BoFactory.getInstance().getBo(BoFactory.BoType.ITEM);
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDeleteButton.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        getItems();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        try {
            // get inserted data from the text fields
            String code = txtItemCode.getText();
            String description = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double qtyOnHand = Double.parseDouble(txtQtyOnHand.getText());

            ItemDTO itemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);

            boolean isSaved = bo.saveItem(itemDTO);

            if (isSaved) {
                new Alert(AlertType.CONFIRMATION, "Item is saved.!").show();
                // load all Items after save new one
                getItems();
            } else {
                new Alert(AlertType.ERROR, "Item is not saved..!").show();
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
    void btnUpdateOnAction(ActionEvent event) {
        try {
            String code = txtItemCode.getText();
            String description = txtDescription.getText();
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double qtyOnHand = Double.parseDouble(txtQtyOnHand.getText());

            ItemDTO itemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);
            boolean isUpdated = bo.updateItem(itemDTO);

            if (isUpdated) {
                new Alert(AlertType.CONFIRMATION, "Item is updated.!").show();
                // load all items after update new one
                getItems();
            } else {
                new Alert(AlertType.ERROR, "Item is not updated..!").show();
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
    void txtItemCodeOnAction(ActionEvent event) {
        try {
            String code = txtItemCode.getText();
            ItemDTO dto = bo.getItem(code);

            if (dto != null) {
                txtDescription.setText(dto.getDescription());
                txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            } else {
                new Alert(AlertType.ERROR, "Item Not Found. Please check the item code  and try again.!").show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getItems() {

        try {
            ArrayList<ItemDTO> allItems = bo.getAllItem();
            ObservableList<ItemTM> tmList = FXCollections.observableArrayList();
            for (ItemDTO item : allItems) {
                Button btnDelete = new Button("Delete");
                btnDelete.setMaxSize(100, 50);
                btnDelete.setCursor(Cursor.HAND);
                btnDelete.setStyle("-fx-background-color:#e74c3c; -fx-font-weight:bold");
                btnDelete.setTextFill(Color.web("#ecf0f1"));
                ItemTM itemTM = new ItemTM(item.getCode(), item.getDescription(), item.getUnitPrice(),
                        item.getQtyOnHand(), btnDelete);
                tmList.add(itemTM);

                btnDelete.setOnAction((e) -> {
                    ButtonType ok = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?", ok, no);

                    Optional<ButtonType> result = alert.showAndWait();
                    try {
                        if (result.orElse(no) == ok) {
                            if (bo.deleteItem(itemTM.getCode())) {
                                new Alert(AlertType.CONFIRMATION, "Item is deleted.!").show();
                                getItems();
                                return;
                            }
                        }
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                });
            }

            tblItem.setItems(tmList);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
