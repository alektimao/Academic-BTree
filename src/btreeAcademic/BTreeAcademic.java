/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeAcademic;

/**
 *
 * @author titan
 */
public class BTreeAcademic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.insere(10);
        tree.insere(20);
        tree.insere(30);
        tree.insere(40);
        tree.insere(50);
        tree.insere(60);
        tree.insere(70);
        tree.insere(80);
        tree.insere(90);
        tree.insere(100);
        tree.insere(110);
        tree.insere(120);
        //tree.remover(20);
        tree.inOrden();
        System.out.println();
        tree.exibe();
    }
    
}
