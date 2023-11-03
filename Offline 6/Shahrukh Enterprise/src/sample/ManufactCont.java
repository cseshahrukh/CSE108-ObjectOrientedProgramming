package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.CarRow;
import util.AddCarDTO;
import util.OneThingDTO;
import util.ViewCarDTO;

import java.io.IOException;


public class ManufactCont {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private TextField userText1;

    @FXML
    private TextField userText2;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    public void logoutAction(ActionEvent actionEvent) {

        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addCar(ActionEvent event) {
        System.out.println("inside manufactcont add car func");
        String userName = userText.getText();
        AddCarDTO addCarDTO = new AddCarDTO();

        addCarDTO.setAddorEdit("add");

        System.out.println("username is "+userName);
        if (userName.compareTo("")==0)
        {
            System.out.println("inside manufact when username is null");
            main.showAlertForAdd(-2);
            return ;
        }
        String[] s=userName.split(",");
        System.out.println("s is "+s);
        System.out.println("inside manufactocont ");
        if (s.length!=9)
        {
            main.showAlertForAdd(-2);
            return;
        }
        if (  (!s[7].matches(".*\\d.*")) || (!s[8].matches(".*\\d.*")) || (!s[1].matches(".*\\d.*"))){
            System.out.println("in manufact minus 2");
            addCarDTO.setStatus(-2);
            main.showAlertForAdd(-2);
            return;
        }
        CarRow cr=new CarRow(userName);
        addCarDTO.setC(cr);
        System.out.println(cr);
        try {

            main.getNetworkUtil().write(addCarDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @FXML
    void deleteCar(ActionEvent event) {
        System.out.println("inside manufactcont delte");
        String userName = userText2.getText();

        OneThingDTO oneThingDTO = new  OneThingDTO();
        oneThingDTO.setReg(userName);
        oneThingDTO.setType("DLT");
        //loginDTO.setPassword(password);
        try {
            System.out.println("inside the try"); //pass na milleo eita hobe
            main.getNetworkUtil().write(oneThingDTO);
        } catch (Exception e) {
            System.out.println("mone hocche car pass hocche na");
            e.printStackTrace();
        }
    }

    @FXML
    void editCar(ActionEvent event) {
        System.out.println("inside manufactcont add car func");
        String userName = userText1.getText();
        AddCarDTO addCarDTO = new AddCarDTO();


        System.out.println("username is "+userName);
        if (userName.compareTo("")==0)
        {
            System.out.println("inside manufact when username is null");
            main.showAlertForEdit(-2);
            return ;
        }
        String[] s=userName.split(",");
        System.out.println("s is "+s);

        if (s.length!=9)
        {
            main.showAlertForEdit(-2);
            return;
        }
        if (  (!s[7].matches(".*\\d.*")) || (!s[8].matches(".*\\d.*")) || (!s[1].matches(".*\\d.*"))){
            System.out.println("in manufact minus 2");
            addCarDTO.setStatus(-2);
            main.showAlertForEdit(-2);
            return;
        }

        CarRow cr=new CarRow(userName);
        addCarDTO.setAddorEdit("edit");
        addCarDTO.setC(cr);
        System.out.println(cr);
        try {
            main.getNetworkUtil().write(addCarDTO);
        } catch (IOException e) {
            System.out.println("ManuFAct er Addcar e Error");
            e.printStackTrace();
        }

    }


    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    void setMain(Main main) {
        this.main = main;
    }


    public void viewCar(ActionEvent actionEvent) {
        ViewCarDTO viewCarDTO=new ViewCarDTO();
        viewCarDTO.setStatus(true); //as this is manu

        try{
            System.out.println("nside controllers  view car ");
            main.getNetworkUtil().write(viewCarDTO); //sedning request
            //main.showCar();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
