package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarWareHouse implements Serializable
{
    public List<CarRow> l;
    private static final String INPUT_FILE_NAME = "in.txt";
    public boolean flag;

    public CarWareHouse()
    {
        l=new ArrayList();
        //Eita na korle null pointer exception !!!
    }

    public List<CarRow> getlist()
    {
        return l;
        //car er pura list ta !!!
    }
    public void load() //File theke list e add kora
    {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            //input file er jinis read korar jonno
            //pura input file er jinis ete store hoye thakbe
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String p[]=line.split(",");
                l.add(new CarRow(p[0],Integer.parseInt(p[1]),p[2],p[3],p[4],p[5],p[6],Integer.parseInt(p[7]), Integer.parseInt(p[8])));

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int add(CarRow c)
    {
        int flag=1;
        for(int i=0;i<l.size();i++)
        {
            if(l.get(i).getReg().equalsIgnoreCase(c.getReg()))
            {
                System.out.println("cannot be added");
                flag=0;
                break; //karon already add hoye ache !!!
            }
        }
        if(flag==1) //add hoy nai. so add korte hobe
        {
            l.add(c);
        }
        return flag; //indicator of success
    }


    public int searchReg(String str)
    {
        System.out.println("inside search reg");
        int save=-1;
        for(int i=0;i<l.size();i++)
        {
            //System.out.println("inside for loop ");
            if(l.get(i).getReg().equalsIgnoreCase(str))
            {
                //System.out.println("inside if case ");
                save=i;

            }
        }

        System.out.println("save is "+ save);
        return save;
    }


    public CarWareHouse showModel(String s)
    {
        CarWareHouse c1=new CarWareHouse();
        c1.load();
        CarWareHouse c2=new CarWareHouse();

        for(int i=0;i<c1.l.size();i++)
        {
            CarRow p=c1.l.get(i);
                if(s.equalsIgnoreCase("any") || s.equalsIgnoreCase(p.getCarModel()))
                {
                    c2.add(p);
                }
        }
        return c2;
    }
    public void save()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
            for(int i=0;i<l.size();i++)
            {
                CarRow p=l.get(i);
                bw.write(p.getReg()+","+p.getYear()+","+p.getColor1()+","+p.getColor2()+","+p.getColor3()+","+     p.getCarModel()+","+  p.getCarMake()+","+  p.getPrice()+","+p.getQuantity()+"\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Modelsave()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("modelSave.txt"));
            for(int i=0;i<l.size();i++)
            {
                CarRow p=l.get(i);
                bw.write(p.getReg()+","+p.getYear()+","+p.getColor1()+","+p.getColor2()+","+p.getColor3()+","+p.getCarMake()+","+p.getCarModel()+","+p.getPrice()+","+p.getQuantity()+"\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

