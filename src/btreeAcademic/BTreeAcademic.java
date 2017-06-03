/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeAcademic;

import bplusTreeAcademic.*;
import java.util.Scanner;

/**
 *
 * @author titan
 */
public class BTreeAcademic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BTree btree = new BTree();
        BPlusTree bplustree = new BPlusTree();

        Scanner scan = new Scanner(System.in);
        String cmdline;
        String cmd_aux;
        String cmd_aux_ptr;
        boolean cmdOk = false;
        double radius = 0;
        int num = 0;

        cliTextHead();
        System.out.printf("\nCMD>");
        cmdline = scan.nextLine();
        cmdline = cmdline.toUpperCase();

        while (!cmdline.equals("EXIT")) {

            if (cmdline.contains("INSERT INTO BTREE ")) {

                cmdline = cmdline.replace("INSERT INTO BTREE ", "");
                try {
                    Integer.parseInt(cmdline);
                    btree.insere(Integer.parseInt(cmdline));
                    cmdOk = true;
                } catch (NumberFormatException e) {

                    System.out.println("--->Error in argument");
                }

            } else if (cmdline.equals("SHOW BTREE")) {
                BTreePrinter.treePrinter(btree.getRaiz());
                cmdOk = true;
            } else if (cmdline.equals("SHOW BTREE PRE ODER")) {

            } else if (cmdline.contains("INSERT AUTO BTREE BPLUSTREE ")) {
                cmdline = cmdline.replace("INSERT AUTO BTREE BPLUSTREE ", "");
                try {
                    num = Integer.parseInt(cmdline);
                    for (int i = 0; i < num; i++) {
                        btree.insere((int) (Math.random() * (400 - 0)) + 0);
                        bplustree.insere((int) (Math.random() * (400 - 0)) + 0);
                    }
                    cmdOk = true;
                } catch (NumberFormatException e) {

                    System.out.println("--->Error in argument");
                }

            }
            if (cmdline.contains("INSERT INTO BPLUSTREE ")) {

                cmdline = cmdline.replace("INSERT INTO BPLUSTREE ", "");
                try {
                    Integer.parseInt(cmdline);
                    bplustree.insere(Integer.parseInt(cmdline));
                    cmdOk = true;
                } catch (NumberFormatException e) {

                    System.out.println("--->Error in argument");
                }

            } else if (cmdline.equals("SHOW BPLUSTREE")) {
                BPLusTreePrinter.treePrinter(bplustree.getRaiz());
                cmdOk = true;
            } else if (cmdline.equals("SHOW BTREE PRE ODER")) {

            } else if (cmdline.contains("INSERT AUTO BTREE ")) {
                cmdline = cmdline.replace("INSERT AUTO BTREE ", "");
                try {
                    num = Integer.parseInt(cmdline);
                    for (int i = 0; i < num; i++) {
                        btree.insere((int) (Math.random() * (400 - 0)) + 0);
                    }
                    cmdOk = true;
                } catch (NumberFormatException e) {

                    System.out.println("--->Error in argument");
                }
            } else if (cmdline.contains("INSERT AUTO BPLUSTREE ")) {
                cmdline = cmdline.replace("INSERT AUTO BPLUSTREE ", "");
                try {
                    num = Integer.parseInt(cmdline);
                    for (int i = 0; i < num; i++) {
                        bplustree.insere((int) (Math.random() * (400 - 0)) + 0);
                    }
                    cmdOk = true;
                } catch (NumberFormatException e) {

                    System.out.println("--->Error in argument");
                }
            }
            if (cmdline.equals("HELP")) {
                cliTextHead();
            }

            if (!cmdOk) {
                System.out.println("\nInvalid Command\n");

            }

            cmdline = "";
            System.out.printf("\nCMD>");
            cmdline = scan.nextLine();
            cmdline = cmdline.toUpperCase();
            cmdOk = false;
        }

//        System.out.println();
//        System.out.println("B Tree");
//
//        BTreePrinter.treePrinter(btree.getRaiz());
//        System.out.println();
//        System.out.println("BPlus Tree");
//
//        BPLusTreePrinter.treePrinter(bplustree.getRaiz());
//        System.out.println();
        //tree.exibe();
    }

    private static void cliTextHead() {

        System.out.println("K-D Tree App, Gabriel L. P. Abreu & Aleksander Silva Gomes");
        System.out.println("*********");
        System.out.println("* Commands:");
        System.out.println("*  INSERT INTO BTREE [VALUE]");
        System.out.println("*  SHOW BTREE");
        System.out.println("*  SHOW BTREE PRE ODER");
        System.out.println("*  INSERT AUTO BTREE [number]");
        System.out.println("*  ");
        System.out.println("*  INSERT INTO BPLUSTREE [VALUE]");
        System.out.println("*  SHOW BPLUSTREE");
        System.out.println("*  SHOW BPLUSTREE PRE ODER");
        System.out.println("*  INSERT AUTO BPLUSTREE [number]");
        System.out.println("*  ");
        System.out.println("*  INSERT AUTO BTREE BPLUSTREE [number]");
        System.out.println("*  ");
        System.out.println("*  HELP (Print this text)");
        System.out.println("*  EXIT");
        System.out.println("*********");

    }

}
