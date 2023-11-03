package util;

import server.CarRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ViewCarDTO implements Serializable {

    private boolean status; //viewer or manufacturer ??

    private List<CarRow> l;

    public ViewCarDTO()
    {
        l=new ArrayList();
    }

    public List<CarRow> getlist()
    {
        return l;
        //car er pura list ta !!!
    }

    public boolean isStatus() {

        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
