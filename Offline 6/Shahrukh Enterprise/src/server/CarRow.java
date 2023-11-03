package server;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

import java.io.Serializable;

public class CarRow implements Serializable
{
    //Main main;
    private String reg,CarMake,CarModel,color1,color2,color3;
    private int year,price,quantity;
    public CarRow(String reg, int year, String color1, String color2, String color3, String CarModel, String CarMake, int price,int quantity)
    {
        this.reg=reg;
        //Registration_Number=a;
        this.year=year;
        this.color1=color1;
        this.color2=color2;
        this.color3=color3;
        this.CarMake=CarMake;
        this.CarModel=CarModel;
        this.price=price;
        this.quantity=quantity;

    }
    public CarRow(String str){
        String p[]=str.split(",");
        /*if (p.length!=9) {
            System.out.println("Invalid Input");
            showAlertFormat();
            return ;
        }*/
        this.reg=p[0];
        p[1]=p[1].trim();
        this.year=Integer.parseInt(p[1]);
        this.color1=p[2];
        this.color2=p[3];
        this.color3=p[4];
        this.CarMake=p[6];
        this.CarModel=p[5];
        p[7]=p[7].trim();
        this.price=Integer.parseInt(p[7]);
        p[8]=p[8].trim();
        this.quantity=Integer.parseInt(p[8]);
    }

    @Override
    public String toString()
    {
        return reg+","+year+","+color1+","+color2+","+color3+","+   CarModel+","+   CarMake+","+      price+","+quantity;
    }

    public String getReg() {
        return reg;
    }


    public String getCarMake() {
        return CarMake;
    }

    public String getCarModel() {
        return CarModel;
    }

    public String getColor1() {
        return color1;
    }

    public String getColor2() {
        return color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() { return quantity; }

}
