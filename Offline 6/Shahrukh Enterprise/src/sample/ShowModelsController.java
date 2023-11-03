package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.CarRow;
import server.CarWareHouse;


public class ShowModelsController  {

    public static final String TAG = "ShowCarsController->";
    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    private TableView<CarRow> tableView;
    @FXML
    private Button Back_button;

    private ObservableList<CarRow> allCars;
    private CarWareHouse cwh;



    void setMain(Main main) {
        this.main = main;
    }


    public void logoutAction(ActionEvent actionEvent) {

        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(CarWareHouse cwh)
    {


        allCars= FXCollections.observableArrayList();
        allCars.addAll(cwh.getlist());

        tableView.setItems(allCars);

        TableColumn<CarRow, String> Registration_Number = new TableColumn<>("Car Reg.");
        Registration_Number.setMinWidth(50);
        Registration_Number.setCellValueFactory(new PropertyValueFactory<>("Reg"));

        TableColumn<CarRow, Integer> YearMadeCol = new TableColumn<>("Year Made");
        YearMadeCol.setMinWidth(50);
        YearMadeCol.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<CarRow, String> ColorCol = new TableColumn<>("Colors1");
        ColorCol.setMinWidth(50);
        ColorCol.setCellValueFactory(new PropertyValueFactory<>("color1"));

        TableColumn<CarRow, String> ColorCol2 = new TableColumn<>("Colors2");
        ColorCol2.setMinWidth(50);
        ColorCol2.setCellValueFactory(new PropertyValueFactory<>("color2"));

        TableColumn<CarRow, String> ColorCol3 = new TableColumn<>("Colors3");
        ColorCol3.setMinWidth(50);
        ColorCol3.setCellValueFactory(new PropertyValueFactory<>("color3"));

        TableColumn<CarRow, String> CarModelCol = new TableColumn<>("Car Model");
        CarModelCol.setMinWidth(50);
        CarModelCol.setCellValueFactory(new PropertyValueFactory<>("CarModel"));

        TableColumn<CarRow, String> CarMakeCol = new TableColumn<>("Car Make");
        CarMakeCol.setMinWidth(50);
        CarMakeCol.setCellValueFactory(new PropertyValueFactory<>("CarMake"));



        TableColumn<CarRow, Integer> PriceCol = new TableColumn<>("Price");
        PriceCol.setMinWidth(50);
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<CarRow, Integer> QuantityCol = new TableColumn<>("Quantity");
        QuantityCol.setMinWidth(50);
        QuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(Registration_Number, YearMadeCol, ColorCol, ColorCol2, ColorCol3,CarModelCol,CarMakeCol,PriceCol,QuantityCol);

    }

    public void Back_button_pressed(ActionEvent actionEvent) {
        try {
            main.showHomePageForViewer("Hello Boss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
