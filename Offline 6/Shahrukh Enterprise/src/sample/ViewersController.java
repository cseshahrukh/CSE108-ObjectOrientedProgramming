package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.CarWareHouse;
import util.OneThingDTO;
import util.ViewCarDTO;

public class ViewersController {

    private Main main;

    @FXML
    private Label message;

    @FXML
    private TextField regSearch;
    @FXML
    private TextField modelSearch;
    @FXML
    private TextField buy;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    public void init(String msg) {
        message.setText(msg);
        Image img = new Image(Main.class.getResourceAsStream("1.png"));
        //image.setImage(img);
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public void viewCar(ActionEvent actionEvent) {
        ViewCarDTO viewCarDTO=new ViewCarDTO();
        viewCarDTO.setStatus(false);
        //oneThingDTO.setReg(userName);
        //System.out.println("pressed button is "+userName);
        //oneThingDTO.setType("MODEL");

        try {
            main.getNetworkUtil().write(viewCarDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try{
            System.out.println("inside controllers  view car ");
            //main.showCarForViewers();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
    }

    @FXML
    public void REGSearch(ActionEvent event) {
        String userName = regSearch.getText();

        OneThingDTO oneThingDTO = new  OneThingDTO();
        oneThingDTO.setReg(userName);
        oneThingDTO.setType("REG");
        try {
            main.getNetworkUtil().write(oneThingDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void MODELSearch(ActionEvent event) {
        String userName = modelSearch.getText();
        OneThingDTO oneThingDTO = new  OneThingDTO();
        oneThingDTO.setReg(userName);
        System.out.println("pressed button is "+userName);
        oneThingDTO.setType("MODEL");

        try {
            main.getNetworkUtil().write(oneThingDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }




        CarWareHouse cwh=new CarWareHouse();
        cwh=cwh.showModel(userName);
        cwh.Modelsave();
        main.showModelSearch(cwh);

    }
    @FXML
    public void BUY(ActionEvent event) {
        String userName = buy.getText();
        OneThingDTO oneThingDTO = new  OneThingDTO();
        oneThingDTO.setReg(userName);
        System.out.println("pressed button is "+userName);
        oneThingDTO.setType("BUY");

        try {
            main.getNetworkUtil().write(oneThingDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
