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
        tree.insere(1);
        tree.insere(4);
        tree.insere(7);
        tree.insere(10);
        tree.insere(17);
        tree.insere(21);
        tree.insere(31);
        tree.insere(25);
        tree.insere(19);
        tree.insere(20);
        tree.inOrden();
        tree.exibe();
    }
    
}
