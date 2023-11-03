package com.company;
import java.util.Random;
class ParallelMax implements Runnable {
    // your code here
    private int [] array;
    private int indexStart;
    private int max;
    Thread t;

    public int getMax() {
        return max;
    }

    public ParallelMax(int [] arr, int start) {
        array=arr;
        indexStart=start;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        max=array[indexStart];
        for(int i=indexStart+1;i<indexStart+200;i++){
            if(max<array[i]){
                max=array[i];
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Random random = new Random(100);
        int [] numbers = new int[1000];
        int maximum=0; //to check from main thread

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt();
            if(maximum<numbers[i]){
                maximum=numbers[i];
            }
        }
        //System.out.println("Maximum without thread is "+maximum);

        ParallelMax [] parallelMax = new ParallelMax[5];
        parallelMax[0]=new ParallelMax(numbers, 0);
        parallelMax[1]=new ParallelMax(numbers, 200);
        parallelMax[2]=new ParallelMax(numbers, 400);
        parallelMax[3]=new ParallelMax(numbers, 600);
        parallelMax[4]=new ParallelMax(numbers, 800);
        try{
            for (int i=0; i<5; i++)
                parallelMax[i].t.join();
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        int threadMaximum=0;
        for(int i=0;i<5;i++){
            if(threadMaximum<parallelMax[i].getMax()){
                threadMaximum=parallelMax[i].getMax();
            }
        }
        System.out.print("Maximum with thread is ");
        System.out.println(threadMaximum);
        }
    }