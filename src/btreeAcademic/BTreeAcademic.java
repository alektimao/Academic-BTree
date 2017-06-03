/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeAcademic;

import bplusTreeAcademic.*;

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
        
        System.out.println();
        System.out.println("B Tree");
        
        for (int i = 0; i < 100; i++) {
            btree.insere((int) (Math.random() * (400 - 0)) + 0);
            bplustree.insere((int) (Math.random() * (400 - 0)) + 0);
            
        }

        BTreePrinter.treePrinter(btree.getRaiz());
        System.out.println();
        System.out.println("BPlus Tree");

        
        BPLusTreePrinter.treePrinter(bplustree.getRaiz());
        System.out.println();
        //tree.exibe();
    }
    
}
