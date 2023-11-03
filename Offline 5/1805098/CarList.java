import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String regNum, colour1,colour2,colour3, carMake, carModel;
    private int yearMade;
    private int price;

    public Car(String regNum, int yearMade,String colour1, String colour2,String colour3,String carMake, String carModel,  int price) {
        this.regNum = regNum;
        this.colour1 = colour1;
        this.colour2 = colour2;
        this.colour3 = colour3;
        this.carMake = carMake;
        this.carModel = carModel;
        this.yearMade = yearMade;
        this.price = price;
    }

    public Car(String str){
        String[] coll=str.split(",");

        for (int i=0; i<8;i++)
        {
            coll[i]=coll[i].trim();
        }
        this.regNum = coll[0];
        this.yearMade = Integer.parseInt(coll[1]);
        this.colour1 = coll[2];
        this.colour2 = coll[3];
        this.colour3 = coll[4];
        this.carMake = coll[5];
        this.carModel = coll[6];
        this.price = Integer.parseInt(coll[7]);
    }

    public String getRegNum() {
        return regNum;
    }

    public String getCarMake() {
        return carMake;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNum='" + regNum + '\'' +
                ", colour1='" + colour1 + '\'' +
                ", colour2='" + colour2 + '\'' +
                ", colour3='" + colour3 + '\'' +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                ", yearMade=" + yearMade +
                ", price=" + price +
                '}';
    }

    public String toStringSave(){
        return
                regNum + ',' +
                        yearMade +','+
                colour1 + ',' +
                 colour2 + ',' +
                 colour3 + ',' +
                 carMake + ','+
                carModel + ',' +
                 price
                ;
    }

    public String getCarModel() {
        return carModel;
    }
}

public class CarList {
    private List<Car> carList = new ArrayList();
    private void add(){
        System.out.println("Adding a new car");
        System.out.println("Give a car with description");
        Scanner scn=new Scanner(System.in);
        String regg=scn.nextLine();
        String[] coll=regg.split(",");
        if (coll.length!=8){
            System.out.println("Give input in correct format");
            add();
        }
        for (Car a:carList) {
            if (coll[0].equals(a.getRegNum())) {
                System.out.println("Same Reg is already here");
                System.out.println(a);
                add();
            }
        }
        Car a=new Car(regg);
        carList.add(a);
        showMainMenu();
    }
    private void delete(){
        System.out.println("Give reg num of that car you want to delete");
        Scanner scn=new Scanner(System.in);
        String reg=scn.nextLine();
        reg=reg.toLowerCase();
        int searchIndex = -1;
        for (int i = 0; i < carList.size(); i++) {
            Car t = carList.get(i);
            if (t.getRegNum().toLowerCase().equals(reg)) {
                searchIndex = i;
            }
        }
        if (searchIndex != -1) {
            carList.remove(searchIndex);
        }
        else {
            System.out.println("No car found with given reg num");
        }
        showMainMenu();
    }

    private  void searchCar() {
        Scanner scn=new Scanner(System.in);
        System.out.println("Car Searching Options");
        System.out.println("1. By Registration Number");
        System.out.println("2. By Car Make and Car Model");
        System.out.println("3. Back to Main Menu");
        String oneTwoThree=scn.nextLine();
        if (oneTwoThree.equals("1")){
            System.out.println("Give registration number that you want to search");
            String reg=scn.nextLine();
            reg=reg.toLowerCase();
            for (Car a:carList) {
                if (reg.equals(a.getRegNum().toLowerCase())) {
                    System.out.println("Found it");
                    System.out.println(a);
                    searchCar();
                }
            }
            System.out.println("No such car with this Registration Number");
            searchCar();
        }
        if (oneTwoThree.equals("2")){
            int flag=1;
            System.out.println("Give Car Make");
            String carMake=scn.nextLine();
            carMake=carMake.toLowerCase();
            System.out.println("Give Car Model");
            String model=scn.nextLine();
            model=model.toLowerCase();
            if (model.equals("any")){
                for (Car a:carList) {
                    if (carMake.equals(a.getCarMake().toLowerCase())) {
                        System.out.println("Found it");
                        System.out.println(a);
                        flag=0;
                    }
                }
                if (flag==1)System.out.println("No such car with this Registration Number");
                searchCar();
            }
            else {
                for (Car a:carList) {
                    if (carMake.equals(a.getCarMake().toLowerCase()) &&model.equals(a.getCarModel().toLowerCase())) {
                        System.out.println("Found it");
                        System.out.println(a);
                        flag=0;
                    }
                }
                if (flag==1)System.out.println("No such car with this");
                searchCar();
            }
        }
        else if (oneTwoThree.equals("3")){
            showMainMenu();
        }
        else {
            System.out.println("Give Integer from 1-3");
        }

    }
    private void printCar(){
        for (Car c:carList){
            System.out.println(c);
        }
    }
    private void showMainMenu(){
        System.out.println("Main Menu");
        System.out.println("1. Search Cars");
        System.out.println("2. Add Cars");
        System.out.println("3. Delete Cars");
        System.out.println("4. Exit System");
        Scanner scn=new Scanner(System.in);
        String exam=scn.next();
        if (exam.equals("1")){
            searchCar();
        }
        else if (exam.equals("2")){
            add();
        }
        else if (exam.equals("3")){
            delete();
        }
        else if (exam.equals("4"))
        {
            //save kore exit korte hobe
            String text = "";
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("cars.txt"));
                for (Car a: carList){
                    text=a.toStringSave();
                    bw.write(text);
                    bw.write("\n");
                }
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("hey! Stop! You are giving the wrong input");
            showMainMenu();
        }
    }
    public static void main(String[] args) {
        CarList k=new CarList();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                if (line=="\n") break;
                k.carList.add(new Car(line));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //k.printCar();
        k.showMainMenu();
    }
}
