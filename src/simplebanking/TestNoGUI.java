/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebanking;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;

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
		int compare = src.compareTo(dest);
		//System.out.println(src+"\n"+dest);

	}

      
    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
		int j=0;
		long tmp2;
		while(true){
			while(!TestNoGUI.stop) {

				j++;
				tmp2 = TestNoGUI.pubCounter;
				costly();
				tmp = tmp2 - 2;
				costly();
				TestNoGUI.pubCounter = tmp;
				System.out.println(j + "Withdraw " + tmp2 + "-" + tmp + "-" + TestNoGUI.pubCounter);
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
		var compare = src.compareTo(dest);
		//System.out.println(src+"\n"+dest);
	}
        
    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
		int j=0;
		long tmp2=0;
		while(true){
			while(!TestNoGUI.stop) {

				j++;
				tmp2 = TestNoGUI.pubCounter;
				costly();
				tmp = tmp2 + 2;
				costly();
				TestNoGUI.pubCounter = tmp;
				System.out.println(j + "Deposit " + tmp2 + "-" + tmp + "-" + TestNoGUI.pubCounter);
			}
		}
  	}
}


public class TestNoGUI{
	 public static boolean stop = false;
	 public static boolean count = true;
	 public static int sleepTime = 100;
	 public static  long pubCounter = 100;


	 public static void main(String[] args) {
    	(new Deposit ()).start();
    	(new Withdraw ()).start();
    }
}
