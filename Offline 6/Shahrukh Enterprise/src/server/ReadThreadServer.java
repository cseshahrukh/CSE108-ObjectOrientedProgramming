package server;

import util.*;

import java.io.IOException;
import java.util.HashMap;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;

    public HashMap<String, String> userMap;


    public ReadThreadServer(HashMap<String, String> userMap,  NetworkUtil networkUtil) {
        this.userMap=userMap;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                //System.out.println("Inside ReadThreadServer");
                //System.out.println("Before networkUtil.read");
                Object o = networkUtil.read();
                //System.out.println("After networkUtil.read");
                if (o != null) {
                    //String s = (String) o;
                    if (o instanceof LoginDTO) {
                        //System.out.println("insdie read thread sever logindto");
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }
                    else if (o instanceof AddCarDTO){
                        //System.out.println("insdie read thread sever addcardto");
                        //System.out.println("inside rts addcardto");
                        AddCarDTO cr=(AddCarDTO) o;
                        CarWareHouse cwh=new CarWareHouse();
                        cwh.load();
                        cr.setStatus(cwh.searchReg(cr.getC().getReg()));//na thakle minus one
                        //CarRow c=cr.getC();
                        //String[] s =c.toString().split(",");

                         if (cr.getAddorEdit().compareTo("edit")==0)
                        {
                            if (cr.isStatus()!=-1 &&cr.isStatus()!=-2){
                                cwh.l.remove(cr.isStatus());
                                cwh.add(cr.getC());
                                cwh.save();
                            }
                            //else do nothing ei case e minus one thakbe
                        }
                        else if (cr.getAddorEdit().compareTo("add")==0){
                           if (cr.isStatus()==-1){
                               cwh.add(cr.getC());
                               cwh.save();
                           }
                           //else do nothing
                        }
                        //System.out.println(cr.isStatus());
                        //System.out.println("UPore status print holo");
                        //System.out.println(cr);
                        networkUtil.write(cr);
                    }
                    else if (o instanceof OneThingDTO) {
                        //System.out.println("print er ager line");
                        //System.out.println("insdie read thread sever onethingdto");
                        //System.out.println("eikhane ashche");
                        OneThingDTO oneThineDTO = (OneThingDTO) o;
                        //System.out.println("eikhnae ashtese na ");
                        //System.out.println("inside read thread server one thing dto is "+oneThineDTO.getType());
                        CarWareHouse cwh=new CarWareHouse();
                        cwh.load();
                        if (oneThineDTO.getType().compareTo("REG")==0 ||oneThineDTO.getType().compareTo("DLT")==0||oneThineDTO.getType().compareTo("BUY")==0 )
                            oneThineDTO.setStatus(cwh.searchReg(oneThineDTO.getReg()));//na thakle minus one

                        if (oneThineDTO.isStatus()!=-1 && oneThineDTO.getType().compareTo("DLT")==0){
                            //System.out.println("This readthread sever delete");
                            cwh.l.remove(oneThineDTO.isStatus());
                            cwh.save();
                        }
                        else if (oneThineDTO.isStatus()!=-1 && oneThineDTO.getType().compareTo("BUY")==0 &&cwh.getlist().get(oneThineDTO.isStatus()).getQuantity()!=0 ){
                            cwh.getlist().get(oneThineDTO.isStatus()).setQuantity(cwh.getlist().get(oneThineDTO.isStatus()).getQuantity()-1);
                            //cwh.l.remove(oneThineDTO.isStatus());
                            cwh.save();
                            //System.out.println("hello from read thread server ");
                        }
                        else if (oneThineDTO.isStatus()!=-1 && oneThineDTO.getType().compareTo("BUY")==0 &&cwh.getlist().get(oneThineDTO.isStatus()).getQuantity()==0 ){
                            oneThineDTO.setStatus(-1);
                            //System.out.println("hello boss !!");
                        }
                        else if (oneThineDTO.getType().compareTo("MODEL")==0){
                            cwh.flag=false;
                            cwh=cwh.showModel(oneThineDTO.getReg());
                        }
                        if (oneThineDTO.getType().compareTo("MODEL")==0 || oneThineDTO.getType().compareTo("view")==0) {
                            //System.out.println("model e ache");
                            networkUtil.write(cwh);
                        }

                        else {
                            //System.out.println("onethinedto wire hocche");
                            networkUtil.write(oneThineDTO);
                        }
                    }
                    else if (o instanceof ViewCarDTO) {
                        ViewCarDTO viewCarDTO=(ViewCarDTO)o;
                        //System.out.println("insdie read thread sever viewcardto");

                        CarWareHouse cwh=new CarWareHouse();
                        cwh.load();
                        cwh.flag= viewCarDTO.isStatus();
                        networkUtil.write(cwh);
                    }
                }
            }
        }
                catch (Exception e) {
                    System.out.println("Client has left");
                    //e.printStackTrace();
                }
         finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




