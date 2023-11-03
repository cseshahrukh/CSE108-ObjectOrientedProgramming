package util;

import server.CarRow;

import java.io.Serializable;

public class AddCarDTO implements Serializable {

    private CarRow c;

    private int status;

    private String AddorEdit;

    public String getAddorEdit() {
        return AddorEdit;
    }

    public void setAddorEdit(String addorEdit) {
        AddorEdit = addorEdit;
    }

    public CarRow getC() {
        return c;
    }

    public void setC(CarRow c) {
        this.c = c;
    }

    public int isStatus() {
        return status; //warehouse e na thakle minus one hobe eitar value
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
