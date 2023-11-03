package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import server.CarRow;
import server.CarWareHouse;
import util.NetworkUtil;

import java.io.IOException;

public class Main extends Application {

    public static final String TAG = "Main->";
    public Stage stage;
    private NetworkUtil networkUtil;
    public int option;

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showHomePageForManufacturer(String userName) throws Exception {

        //System.out.println("Insdie the showHomePge for manu");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manufactuter.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ManufactCont controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("HomeForManu");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
    }
    public void showHomePageForViewer(String userName) throws Exception {
        //System.out.println("Insdie the showHomePge for viewer");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewers.fxml"));
        //System.out.println("inside showhomfor viewer 1");
        Parent root = loader.load();

        System.out.println("inside showhomfor viewer 1");

        // Loading the controller
        ViewersController controller = loader.getController();
        //controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("HomeForViewer");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();
        System.out.println("Finishing showHomePageForViewer From main");
    }

    public void showCar(CarWareHouse cww)  {
        try {
            System.out.println(TAG + "Inside the showCar");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("showCars.fxml"));
            System.out.println(TAG + "inside showCar viewer 1");
            Parent root = loader.load();

            System.out.println(TAG + "inside showcarViewer viewer 2");

            // Loading the controller
            ShowCarsController controller = loader.getController();
            controller.setMain(this);
            controller.init(cww);

            // Set the primary stage
            stage.setTitle("Information of All Cars");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println("Under showCar exception");
            e.printStackTrace();
        }
    }



    public void showAlertForEdit(int index) {
        if (index!=-1 && index!=-2) {

            //System.out.println(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Credentials");
            alert.setHeaderText("Car Edited Successfully");
            alert.setContentText("Car Edited Successfully!!");
            alert.showAndWait();
        }
        else if (index==-1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("No CAR with the REG NUM or invalid input");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Give Correct Format");

            alert.showAndWait();
        }

    }

    public void showAlertForRegSearch(int index) {
        //System.out.println("inside main showaleart");
        CarWareHouse cwh=new CarWareHouse();
        cwh.load();
        System.out.println("index is " + index);
        if (index!=-1) {
            CarRow c = cwh.getlist().get(index);
            System.out.println("hello er ager boss");
            String s = c.toString();

            //System.out.println(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Credentials");
            alert.setHeaderText("Car Found");
            alert.setContentText(c.toString());
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("No CAR with the REG NUM");
            alert.showAndWait();
        }
    }
    public void showAlertForDelete(int index) {
        //System.out.println("inside main showaleart");
        //System.out.println("index is " + index);
        if (index!=-1) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Credentials");
            alert.setHeaderText("Car Found");
            alert.setContentText("Deleted Succesfully");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("No CAR with the REG NUM");
            alert.showAndWait();
        }
    }
    public void showModelSearch(CarWareHouse cww) {

            try {
                System.out.println(TAG + "Inside the showCar");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("showmodel.fxml"));
                Parent root = loader.load();

                // Loading the controller
                ShowModelsController controller = loader.getController();
                controller.setMain(this);
                controller.init(cww);

                // Set the primary stage
                stage.setTitle("Information of All Cars");
                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e){
                System.out.println("Under showCar exception");
                e.printStackTrace();
            }
        }

    public void showAlertForBuy(int index) {
        System.out.println("inside main showaleart");
        //CarWareHouse cwh=new CarWareHouse();
        //cwh.load();
        System.out.println("index is " + index);
        if (index!=-1) {
            //CarRow c = cwh.getlist().get(index);
            //System.out.println("hello er ager boss");
            //String s = c.toString();

            //System.out.println(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Credentials");
            alert.setHeaderText("Car Found");
            alert.setContentText("successfully Bought!!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Unsuccessful BUY!. Please try another car");
            alert.showAndWait();
        }
    }

    public void showAlertForAdd(int index) {
        System.out.println("index is "+index);
        if (index==-2){
            System.out.println("inside showalertfor add for -2");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("Give Correct Format");
        alert.showAndWait();
        }
        else if (index==-1){
            System.out.println("inside showalertfor add for --1");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correct Credentials");
            alert.setHeaderText("Correct Credentials");
            alert.setContentText("Car Added");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Car Already Exist");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
