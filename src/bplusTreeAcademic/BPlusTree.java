package bplusTreeAcademic;

import java.util.LinkedList;
import java.util.Queue;

public class BPlusTree implements BPlusTreeInterface {

    public BPlusTreeNode raiz;

    public BPlusTreeNode getRaiz() {
        return raiz;
    }

    public void setRaiz(BPlusTreeNode raiz) {
        this.raiz = raiz;
    }

    public int buscaPosicao(BPlusTreeNode nodo, int info) {
        int i;
        for (i = 0; i < nodo.getTl() && info > nodo.getInfo(i); i++) {
        }

        return i;
    }

    public BPlusTreeNode navegaFolha(int info) {//vai até a folha 
        BPlusTreeNode no = getRaiz();
        int i;
        while (no.getLig(0) != null) {
            for (i = 0; i < no.getTl() && info > no.getInfo(i); i++) {
            }

            no = no.getLig(i);
        }
        return no;
    }

    public void remanejar(BPlusTreeNode nodo, int pos) {
        nodo.setLig(nodo.getTl() + 1, nodo.getLig(nodo.getTl()));
        for (int i = nodo.getTl(); i > pos; i--) {
            nodo.setInfo(i, nodo.getInfo(i - 1));
            nodo.setLig(i, nodo.getLig(i - 1));
        }
    }

    public BPlusTreeNode localizaPai(BPlusTreeNode folha, int info) {
        int i;
        BPlusTreeNode no = getRaiz();
        BPlusTreeNode pai = no;

        while (no != folha) {
            for (i = 0; i < no.getTl() && info > no.getInfo(i); i++) {
            }

            pai = no;
            no = no.getLig(i);
        }
        return pai;
    }

    public void insere(int info) {
        BPlusTreeNode folha;
        BPlusTreeNode pai;
        int pos;//posição de onde sera inserido

        if (raiz == null) {
            raiz = new BPlusTreeNode(info);
        } else {
            folha = navegaFolha(info); // retorna a folha
            pos = buscaPosicao(folha, info); // retorna a posiçao

            remanejar(folha, pos);
            folha.setInfo(pos, info);
            folha.setTl(folha.getTl() + 1);
            if (folha.getTl() > NINFO) {
                pai = localizaPai(folha, info);
                split(folha, pai, info);
            }
        }

    }

    private void split(BPlusTreeNode folha, BPlusTreeNode pai, int info) {
        int poscentro, pospai = 0, pos;
        BPlusTreeNode leftNode, rightNode;
        BPlusTreeNode node1 = new BPlusTreeNode();
        BPlusTreeNode node2 = new BPlusTreeNode();

        if (folha.getLig(0) == null) 
        {
            poscentro = Math.round((float) (N - 1) / 2); 

            for (int i = 0; i < poscentro; i++) //enche no1 de informaçao
            {
                node1.setInfo(i, folha.getInfo(i));
                node1.setLig(i, folha.getLig(i));
            }
            node1.setLig(poscentro, folha.getLig(poscentro));
            node1.setTl(poscentro);

            for (int i = poscentro; i < folha.getTl(); i++) //enche no2 de informaçao
            {
                node2.setInfo(i - poscentro, folha.getInfo(i));
                node2.setLig(i - poscentro, folha.getLig(i));
            }
            node2.setLig(folha.getTl() - poscentro, folha.getLig(folha.getTl()));
            node2.setTl(folha.getTl() - poscentro);

            //junta a ligaçao dos dois nos pelo ant e prox
            node1.setProx(node2);
            node2.setAnt(node1);

            if (pai == folha) //ligando as folhas nas caixas
            {
                folha.setInfo(0, node2.getInfo(0));
                folha.setTl(1);
                folha.setLig(0, node1);
                folha.setLig(1, node2);
            } else //pai não é folha
            {
                pos = buscaPosicao(pai, node2.getInfo(0));
                remanejar(pai, pos);
                pai.setInfo(pos, node2.getInfo(0));
                pai.setTl(pai.getTl() + 1);
                pai.setLig(pos, node1);
                pai.setLig(pos + 1, node2);

                if (pos > 0) {
                    leftNode = pai.getLig(pos - 1);
                    leftNode.setProx(node1);
                    node1.setAnt(leftNode);
                }
                if (pos < pai.getTl() - 1) {
                    rightNode = pai.getLig(pos + 1);
                    rightNode.setAnt(node2);
                    node2.setProx(rightNode);
                }
            }
        } else // não é folha
        {
            poscentro = Math.round((float) N / 2) - 1;

            for (int i = 0; i < poscentro; i++) {
                node1.setInfo(i, folha.getInfo(i));
                node1.setLig(i, folha.getLig(i));
            }
            node1.setLig(poscentro, folha.getLig(poscentro));
            node1.setTl(poscentro);

            for (int i = poscentro + 1; i < folha.getTl(); i++) {
                node2.setInfo(i - (poscentro + 1), folha.getInfo(i));
                node2.setLig(i - (poscentro + 1), folha.getLig(i));
            }
            node2.setLig(folha.getTl() - (poscentro + 1), folha.getLig(folha.getTl()));
            node2.setTl(folha.getTl() - (poscentro + 1));

            folha.setInfo(0, folha.getInfo(poscentro));
            folha.setTl(1);
            folha.setLig(0, node1);
            folha.setLig(1, node2);
        }
        if (pai.getTl() > NINFO) {
            folha = pai;
            pai = localizaPai(folha, info);
            split(folha, pai, info);
        }

    }

    public void inOrdem(BPlusTreeNode raiz) {
        int i;
        if (raiz != null) {
            for (i = 0; i < raiz.getTl(); i++) {
                inOrdem(raiz.getLig(i));
                System.out.println(raiz.getInfo(i));
            }
            inOrdem(raiz.getLig(i));
        }
    }

    public void exibeMagico() {
        Queue<BPlusTreeNode> fila = new LinkedList<>();
        BPlusTreeNode no = getRaiz();

        while (no != null) {
            System.out.print("|");
            for (int i = 0; i < no.getTl(); i++) {
                System.out.print(no.getInfo(i) + "|");
            }
            if (no == getRaiz()) {
                System.out.print("--(Raiz)");
            }
            for (int i = 0; i < no.getTl() + 1; i++) {
                fila.add(no.getLig(i));
            }
            System.out.println("");
            no = fila.remove();
        }
    }

}
