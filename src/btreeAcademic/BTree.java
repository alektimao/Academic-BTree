/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreeAcademic;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author titan
 */
public class BTree implements BTreeInterface {

    BTreeNode raiz;

    public BTree() {

    }

    BTreeNode localizaPai(BTreeNode node, int info) {

        BTreeNode aux = raiz;
        BTreeNode ancestral = aux;
        int i;

        while (aux != node) {
            i = 0;
            while (i < aux.getTl() && info > aux.getInfo(i)) {
                i++;
            }

            ancestral = aux;
            aux = aux.getLig(i);
        }
        return ancestral;
    }

    public BTreeNode navegaAteFolha(int info) {

        int i;
        BTreeNode aux = raiz; //BTreeNode aux = this();
        while (aux.getLig(0) != null) {
            for (i = 0; i < aux.getTl() && info > aux.getInfo(i); i++);
            aux = aux.getLig(i);
        }

        return aux;
    }

    public void split(BTreeNode folha, BTreeNode pai, int info) {

        BTreeNode aux1 = new BTreeNode();
        BTreeNode aux2 = new BTreeNode();

        int i, pos;

        for (i = 0; i < N; i++) {
            aux1.setInfo(folha.getInfo(i), i);
            aux1.setPos(folha.getPos(i), i);
            aux1.setLig(folha.getLig(i), i);
        }

        aux1.setLig(folha.getLig(N), N);
        aux1.setTl(N);

        for (i = N + 1; i <= 2 * N; i++) {
            aux2.setInfo(folha.getInfo(i), i - (N + 1));
            aux2.setPos(folha.getPos(i), i - (N + 1));
            aux2.setLig(folha.getLig(i), i - (N + 1));
        }

        aux2.setLig(folha.getLig(2 * N + 1),N);
        aux2.setTl(N);

        if (folha == pai) {
            folha.setInfo(folha.getInfo(N), 0);
            folha.setPos(folha.getPos(N), 0);
            folha.setLig(aux1, 0);
            folha.setLig(aux2, 1);
            folha.setTl(1);
        } else {
            info = folha.getInfo(N);
            pos = pai.procuraPosicao(info);
            pai.remaneja(pos);
            pai.setTl(pai.getTl() + 1);
            pai.setInfo(folha.getInfo(N), pos);
            pai.setPos(folha.getPos(N), pos);
            pai.setLig(aux1, pos);
            pai.setLig(aux2, pos + 1);

            if (pai.getTl() > 2 * N) {
                folha = pai;
                info = folha.getInfo(N);
                pai = localizaPai(folha, info);
                split(folha, pai, info);
            }
        }
    }

    public void insere(int info) {
        insere(info, 0);
    }

    public void insere(int info, int posAqr) {

        BTreeNode folha = null;
        BTreeNode pai;

        int pos;

        if (raiz == null) {
            raiz = new BTreeNode(info, posAqr);
        } else {
            folha = navegaAteFolha(info);
            pos = folha.procuraPosicao(info);
            folha.remaneja(pos);
            folha.setTl(folha.getTl() + 1);
            folha.setInfo(info, pos);
            folha.setPos(posAqr, pos);

            if (folha.getTl() > 2 * N) {
                pai = localizaPai(folha, info);
                split(folha, pai, info);
            }
        }
    }

    public void inOrden() {

        inOrden(raiz);
    }

    private void inOrden(BTreeNode root) {
        int i;
        if (root != null) {
            for (i = 0; i < root.getTl(); i++) {

                inOrden(root.getLig(i));
                System.out.print("" + root.getInfo(i) + ' ');
            }
            i++;
            inOrden(root.getLig(i));
        }
    }

    public void exibe() {
        Queue<BTreeNode> fila = new LinkedList<>();
        BTreeNode temp = raiz;
        while (temp != null) {
            System.out.print("|");
            for (int i = 0; i < temp.getTl(); i++) {
                System.out.print(temp.getInfo(i) + "|");
            }
            if (temp == raiz) {
                System.out.print("--(Raiz)");
            }
            for (int i = 0; i < temp.getTl() + 1; i++) {
                fila.add(temp.getLig(i));
            }
            System.out.println("");
            temp = fila.remove();

        }
    }
}
