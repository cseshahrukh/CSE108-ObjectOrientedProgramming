package sample;

import javafx.application.Platform;
import server.CarWareHouse;
import util.AddCarDTO;
import util.LoginDTO;
import util.OneThingDTO;

import java.io.IOException;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    //public int option;
    //public CarWareHouse cwh;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        //System.out.println("before the user name");
                        //System.out.println(loginDTO.getUserName());
                        //System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {

                                        main.showHomePageForManufacturer(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        System.out.println(loginDTO.getUserName());
                                        main.showHomePageForViewer(loginDTO.getUserName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        });
                    }
                    else  if (o instanceof CarWareHouse) {
                        CarWareHouse loginDTO = (CarWareHouse) o;
                        //System.out.println("before the user name");
                        //System.out.println(loginDTO.getUserName());
                        //System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                        if (loginDTO.flag==false)
                                            main.showModelSearch(loginDTO);
                                        else
                                            main.showCar(loginDTO);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                            }
                        });
                    }
                    else if (o instanceof AddCarDTO) {
                        System.out.println("here is instance of CarRow");
                        AddCarDTO addCarDTO = (AddCarDTO) o;
                        System.out.println("before the user name");
                        if (addCarDTO.getAddorEdit().compareTo("add")==0) {
                            Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      try {
                                                          System.out.println("inside readthread of addcar");
                                                          main.showAlertForAdd(addCarDTO.isStatus());
                                                          //System.out.println("After saving into the file");
                                                      } catch (Exception e) {
                                                          //System.out.println("Exception in readthread ");
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                            );
                        }
                        else if (addCarDTO.getAddorEdit().compareTo("edit")==0) {
                            Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      try {
                                                          main.showAlertForEdit(addCarDTO.isStatus());
                                                      } catch (Exception e) {
                                                          System.out.println("Exception in readthread ");
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                            );
                        }
                    }
                    else if (o instanceof OneThingDTO) {
                        System.out.println("here is instance of OneThingDTO");
                        OneThingDTO oneThingDTO = (OneThingDTO) o;
                        System.out.println("before the user name");
                        if (oneThingDTO.getType().compareTo("REG")==0) {
                            Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      try {
                                                          main.showAlertForRegSearch(oneThingDTO.isStatus());
                                                      } catch (Exception e) {
                                                          System.out.println("Exception in readthread while reging");
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                            );
                        }
                        else if (oneThingDTO.getType().compareTo("DLT")==0) {
                            Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      try {
                                                          System.out.println("Delete korte chacchi");
                                                          System.out.println("index is "+oneThingDTO.isStatus());
                                                              main.showAlertForDelete(oneThingDTO.isStatus());
                                                      } catch (Exception e) {
                                                          System.out.println("Exception in readthread while delete");
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                            );
                        }
                        else if (oneThingDTO.getType().compareTo("BUY")==0) {
                            Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      try {
                                                          System.out.println("index is "+oneThingDTO.isStatus());
                                                          main.showAlertForBuy(oneThingDTO.isStatus());
                                                      } catch (Exception e) {
                                                          System.out.println("Exception in readthread whilebuying");
                                                          e.printStackTrace();
                                                      }
                                                  }
                                              }
                            );
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Server got disconnected!");
            e.printStackTrace();
        } finally {
            System.out.println("In finally blcok of readthred");
            try {
                System.out.println("In finally's try");
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                System.out.println("in finally's catch ");
                e.printStackTrace();
            }
        }
    }
}



