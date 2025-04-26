/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplebanking;

/**
 *
 * @author Max Holmes
 */
import java.awt.Label;

class Withdraw extends Thread {
    Label box;
    String src = "";
    String dest = "";
    long tmp;
    int id = 0;

    public Withdraw() {}

    public void costly() {
        for (int i = 0; i < 256; i++) {
            src += "aa" + i;
            dest += "aa" + i;
        }
        int compare = src.compareTo(dest);
    }

    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
        int j = 0;
        long tmp2;
        while (true) {
            while (!PetersonBanking.stop) {
                j++;

                PetersonBanking.flag[id] = true;
                PetersonBanking.turn = 1;

                while (PetersonBanking.flag[1] && PetersonBanking.turn == 1) {
                }

                tmp2 = PetersonBanking.pubCounter;
                costly();
                tmp = tmp2 - 2;
                costly();
                PetersonBanking.pubCounter = tmp;

                System.out.println(j + " Withdraw " + tmp2 + " -> " + tmp + " -> " + PetersonBanking.pubCounter);

                PetersonBanking.flag[id] = false;

                try {
                    Thread.sleep(PetersonBanking.sleepTime);
                } catch (InterruptedException e) {}
            }
        }
    }
}

class Deposit extends Thread {
    Label box;
    String src = "";
    String dest = "";
    long tmp;
    int id = 1;

    public Deposit() {}

    public void costly() {
        for (int i = 0; i < 256; i++) {
            src += "aa" + i;
            dest += "aa" + i;
        }
        int test = src.compareTo(dest);
    }

    @Override
    public void run() {
        // DO SOME COMPUTATION
	// now wait for others to finish
        int j = 0;
        long tmp2;
        while (true) {
            while (!PetersonBanking.stop) {
                j++;

                PetersonBanking.flag[id] = true;
                PetersonBanking.turn = 0;

                while (PetersonBanking.flag[0] && PetersonBanking.turn == 0) {
                }

                
                tmp2 = PetersonBanking.pubCounter;
                costly();
                tmp = tmp2 + 2;
                costly();
                PetersonBanking.pubCounter = tmp;

                System.out.println(j + " Deposit " + tmp2 + " -> " + tmp + " -> " + PetersonBanking.pubCounter);

                PetersonBanking.flag[id] = false;

                try {
                    Thread.sleep(PetersonBanking.sleepTime);
                } catch (InterruptedException e) {}
            }
        }
    }
}

public class PetersonBanking {
    public static volatile boolean[] flag = new boolean[2];
    public static volatile int turn = 0;
    public static volatile boolean stop = false;
    public static int sleepTime = 100;
    public static volatile long pubCounter = 100;

    public static void main(String[] args) {
        new Deposit().start();
        new Withdraw().start();
    }
}