/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebanking;
import java.awt.Label;
import java.util.concurrent.Semaphore;
/**
 *
 * @author Max Holmes
 */

class Withdraw extends Thread{
    Label box;
    String src = "";
    String dest = "";
    long tmp;
    public Withdraw(){

	}

	public void costly(){
		for(int i =0;i<256;i++){
			src+="aa"+i;
			dest+="aa"+i;
		}
        int compareTo = src.compareTo(dest);
        //System.out.println(src+"\n"+dest);

	}

    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
		int j=0;
		long tmp2;
		while (true) {
            while (!SemaphoresBanking.stop) {
                j++;
                try {
                    SemaphoresBanking.sema.acquire();

                    tmp2 = SemaphoresBanking.pubCounter;
                    costly();
                    tmp = tmp2 - 2;
                    costly();
                    SemaphoresBanking.pubCounter = tmp;

                    System.out.println(j + " Withdraw " + tmp2 + " -> " + tmp + " -> " + SemaphoresBanking.pubCounter);
                } catch (InterruptedException e) {
                } finally {
                    SemaphoresBanking.sema.release(); 
                }
            }
        }
  	}
}

class Deposit extends Thread{
    Label box;
    String src = "";
    String dest = "";
    long tmp;
    public Deposit(){

	}

	public void costly(){
		for(int i =0;i<256;i++){
			src+="aa"+i;
			dest+="aa"+i;
		}
		src.compareTo(dest);
		//System.out.println(src+"\n"+dest);
	}

    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
		int j=0;
		long tmp2=0;
		while (true) {
            while (!SemaphoresBanking.stop) {
                j++;
                try {
                    SemaphoresBanking.sema.acquire();

                    tmp2 = SemaphoresBanking.pubCounter;
                    costly();
                    tmp = tmp2 + 2;
                    costly();
                    SemaphoresBanking.pubCounter = tmp;

                    System.out.println(j + " Deposit " + tmp2 + " -> " + tmp + " -> " + SemaphoresBanking.pubCounter);
                } catch (InterruptedException e) {

                } finally {
                    SemaphoresBanking.sema.release();
                }
            }
        }
  	}
}

public class SemaphoresBanking {
    public static Semaphore sema = new Semaphore(1);
    public static boolean stop = false;
    public static boolean count = true;
    public static int sleepTime = 100;
    public static  long pubCounter = 100;

    public static void main(String[] args) {
    	(new Deposit ()).start();
    	(new Withdraw ()).start();
    }
}

