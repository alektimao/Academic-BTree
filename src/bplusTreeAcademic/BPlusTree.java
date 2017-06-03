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
        int calculoDistribuicao, pospai = 0, pos;
        BPlusTreeNode vizinhoEsq, vizinhoDir;
        BPlusTreeNode cx1 = new BPlusTreeNode();
        BPlusTreeNode cx2 = new BPlusTreeNode();

        if (folha.getLig(0) == null) // quando é folha
        {
            calculoDistribuicao = Math.round((float) (N - 1) / 2); //calculo

            for (int i = 0; i < calculoDistribuicao; i++) //preenche a cx1
            {
                cx1.setInfo(i, folha.getInfo(i));
                cx1.setLig(i, folha.getLig(i));
            }
            cx1.setLig(calculoDistribuicao, folha.getLig(calculoDistribuicao));
            cx1.setTl(calculoDistribuicao);

            for (int i = calculoDistribuicao; i < folha.getTl(); i++) //preenche a cx2
            {
                cx2.setInfo(i - calculoDistribuicao, folha.getInfo(i));
                cx2.setLig(i - calculoDistribuicao, folha.getLig(i));
            }
            cx2.setLig(folha.getTl() - calculoDistribuicao, folha.getLig(folha.getTl()));
            cx2.setTl(folha.getTl() - calculoDistribuicao);

            //junta as duas caixas pelo ant e prox
            cx1.setProx(cx2);
            cx2.setAnt(cx1);

            if (pai == folha) //ligando as folhas nas caixas
            {
                folha.setInfo(0, cx2.getInfo(0));
                folha.setTl(1);
                folha.setLig(0, cx1);
                folha.setLig(1, cx2);
            } else //pai não é folha
            {
                pos = buscaPosicao(pai, cx2.getInfo(0));
                remanejar(pai, pos);
                pai.setInfo(pos, cx2.getInfo(0));
                pai.setTl(pai.getTl() + 1);
                pai.setLig(pos, cx1);
                pai.setLig(pos + 1, cx2);

                if (pos > 0) {
                    vizinhoEsq = pai.getLig(pos - 1);
                    vizinhoEsq.setProx(cx1);
                    cx1.setAnt(vizinhoEsq);
                }
                if (pos < pai.getTl() - 1) {
                    vizinhoDir = pai.getLig(pos + 1);
                    vizinhoDir.setAnt(cx2);
                    cx2.setProx(vizinhoDir);
                }
            }
        } else // não é folha
        {
            calculoDistribuicao = Math.round((float) N / 2) - 1;

            for (int i = 0; i < calculoDistribuicao; i++) {
                cx1.setInfo(i, folha.getInfo(i));
                cx1.setLig(i, folha.getLig(i));
            }
            cx1.setLig(calculoDistribuicao, folha.getLig(calculoDistribuicao));
            cx1.setTl(calculoDistribuicao);

            for (int i = calculoDistribuicao + 1; i < folha.getTl(); i++) {
                cx2.setInfo(i - (calculoDistribuicao + 1), folha.getInfo(i));
                cx2.setLig(i - (calculoDistribuicao + 1), folha.getLig(i));
            }
            cx2.setLig(folha.getTl() - (calculoDistribuicao + 1), folha.getLig(folha.getTl()));
            cx2.setTl(folha.getTl() - (calculoDistribuicao + 1));

            folha.setInfo(0, folha.getInfo(calculoDistribuicao));
            folha.setTl(1);
            folha.setLig(0, cx1);
            folha.setLig(1, cx2);
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
