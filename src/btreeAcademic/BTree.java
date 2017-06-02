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
//    public BTreeNode RecNavegaAteNo(BTreeNode folha,int info)
//    {
//        BTreeNode aux=folha;
//        
//    }
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

        aux2.setLig(folha.getLig(2 * N + 1), N);
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

    // Remover
    public BTreeNode FusaoFilhoPai(BTreeNode pai)//fundi filhos com seu pai
    {
        BTreeNode aux = pai.getLig(0);
        BTreeNode aux1 = pai.getLig(1);
        int i;

        aux.setInfo(aux.getTl(), pai.getInfo(0));
        aux.setPos(aux.getTl(), pai.getPos(0));  // foi mudado
        aux.setTl(aux.getTl() + 1);

        for (i = 0; i < aux1.getTl(); i++) {
            aux.setLig(aux1.getLig(i), aux.getTl());
            aux.setInfo(aux.getTl(), aux1.getInfo(i));
            aux.setPos(aux.getTl(), aux1.getPos(i));
            aux.setTl(aux.getTl() + 1);
        }
        aux.setLig(aux1.getLig(i), aux.getTl());
        return aux;
    }

    public void fusaoRaiz(BTreeNode folha)//fusao da raiz quando fica sozinha
    {
        int i = 0, j = 0, pos = 0;
        BTreeNode i1 = null, aux, pai, avo;
        pai = localizaPai(folha, folha.getInfo(0));
        i = i1.procuraPosicao(folha.getInfo(0));
        if (pai == raiz) {
            if (pai.getTl() > 1) {
                if (i == 0) {
                    fusao(i, folha, pai);
                } else {
                    fusao(i - 1, folha, pai);
                }
            } else {
                aux = FusaoFilhoPai(pai);
                raiz = aux;
            }
        }
    }

    public void fusao(int i, BTreeNode folha, BTreeNode pai)//segunda possibilidade do power point
    {
        BTreeNode aux = pai.getLig(i + 1);
        int auxinfo, auxpos;

        auxinfo = pai.getInfo(i);
        auxpos = pai.getPos(i);
        remover(auxinfo);

        for (int p = i + 1; p <= pai.getTl(); p++) {
            pai.setLig(pai.getLig(p + 1), p);
        }
        insere(auxinfo, auxpos);

        for (int p = 0; p < aux.getTl(); p++) {
            insere(aux.getInfo(p), aux.getPos(p));
        }

        if (pai.getTl() < N && pai != raiz) // fusao de vizinho com vizinho
        {
            fusaoRaiz(pai);
        }

    }

    //Pegar da direita
    public boolean PegarDireita(int info, int i, BTreeNode no, BTreeNode raiz) {
        BTreeNode aux = raiz.getLig(i + 1);
        int auxinfo, auxpos, infopai, pospai;
        //verificaçao de mais de N registros na direita
        if (raiz.getLig(i + 1) != null && raiz.getLig(i + 1).getTl() > N) {
            auxinfo = aux.getInfo(0);//pega menor valor eprestado
            auxpos = aux.getPos(0);
            infopai = raiz.getInfo(i);
            pospai = raiz.getPos(i);

            remover(aux.getInfo(0)); // utiliza Remove
            raiz.setInfo(i, auxinfo);//coloca os valores no resgistro
            raiz.setPos(i, auxpos);
            insere(infopai, pospai);
            return true;
        }
        return false;
    }

    //Pegar esquerda
    public boolean pegaresquerda(int info, int i, BTreeNode no, BTreeNode raiz) {
        BTreeNode aux = raiz.getLig(i);
        int auxinfopai, auxpospai, auxinfo, auxpos;

        if (raiz.getLig(i) != null && raiz.getLig(i).getTl() > N) {
            auxinfo = aux.getInfo(aux.getTl() - 1);
            auxpos = aux.getPos(aux.getTl() - 1);
            auxinfopai = raiz.getInfo(i);
            auxpospai = raiz.getPos(i);

            remover(aux.getInfo(aux.getTl() - 1));
            raiz.setInfo(i, auxinfo);
            raiz.setPos(i, auxpos);
            insere(auxinfopai, auxpospai);
            return true;
        } else {
            return false;
        }
    }

    public boolean PegarFolha(BTreeNode folha, int info) {
        BTreeNode aux = folha, aux1 = null;
        int auxinfo = 0, auxpos = 0;
        int pos = aux1.procuraPosicao(info);

        aux = aux.getLig(pos);
        while (aux.getLig(aux.getTl()) != null) // Anda até a folha
        {
            aux = aux.getLig(aux.getTl());
        }
        if (aux.getTl() > N) // Valida para saber se pode emprestar ! caso sim 
        {                  // Apaga da folha e joga para a raiz  
            auxinfo = aux.getInfo(aux.getTl() - 1);
            auxpos = aux.getPos(aux.getTl() - 1);
            aux.removeInfo(aux.procuraPosicao(aux.getInfo(aux.getTl() - 1)));
            folha.setInfo(pos, auxinfo);
            folha.setPos(pos, auxpos);
            return true;
        }
        aux = folha;
        aux = aux.getLig(pos + 1);
        while (aux.getLig(0) != null) // Anda até a folha novamente
        {
            aux = aux.getLig(0);
        }
        if (aux.getTl() > N) // Valida para saber se pode emprestar ! caso sim Apaga a folha e joga para a raiz
        {
            auxinfo = aux.getInfo(0);
            auxpos = aux.getPos(0);
            aux.removeInfo(aux.procuraPosicao(aux.getInfo(0)));
            folha.setInfo(pos, auxinfo);
            folha.setPos(pos, auxpos);
            return true;
        }
        return false;
    }

    public BTreeNode fusaofilhos(BTreeNode folha)//fundir dois irmaos e transforma em pai
    {
        BTreeNode aux = folha.getLig(0); // pega filho da lig 0
        BTreeNode aux1 = folha.getLig(1); // pega filho da da lig 1
        int i = 0;

        for (i = 0; i < aux1.getTl(); i++) {
            aux.setLig(aux1.getLig(i), aux.getTl());
            aux.setInfo(aux.getTl(), aux1.getInfo(i));
            aux.setPos(aux.getTl(), aux1.getPos(i));
            aux.setTl(aux.getTl() + 1);
        }
        aux.setLig(aux1.getLig(i), aux.getTl());
        return aux;
    }

    public BTreeNode fusaoPai(BTreeNode folha)//Junta dois filhos e elimina o Pai
    { // so executa quando a raiz possui um elemento
        BTreeNode aux = folha.getLig(0); // recebe filho da esquerda da (FOLHA)
        BTreeNode aux1 = retiraFilho(folha.getLig(1), aux);
        int i = 0;

        for (i = 0; i < aux1.getTl(); i++) {
            aux.setLig(aux1.getLig(i), aux.getTl());
            aux.setInfo(aux.getTl(), aux1.getInfo(i));
            aux.setPos(aux.getTl(), aux1.getPos(i));
            aux.setTl(aux.getTl() + 1);
        }
        aux.setLig(aux1.getLig(i), aux.getTl());
        return aux;
    }

    public BTreeNode retiraFilho(BTreeNode folha, BTreeNode folha2) {
        BTreeNode aux = folha.getLig(0); // filho da esquerda
        BTreeNode aux1 = folha.getLig(1); // filho da direita
        int i = 0, auxinfo = aux.getInfo(0), auxpos = aux.getPos(0); // guarda o primeiro elemento do NO e a posicao

        for (i = 0; i < aux.getTl() - 1; i++) {

            aux.setInfo(i, aux.getInfo(i + 1)); // remaneja as informações
            aux.setPos(i, aux.getPos(i + 1));   // remenaje as posições
        }

        aux.setInfo(i, folha.getInfo(0));
        aux.setPos(i, folha.getPos(0));

        for (i = 0; i < aux1.getTl(); i++) {
            aux.setInfo(aux.getTl(), aux1.getInfo(i));
            aux.setPos(aux.getTl(), aux1.getPos(i));
            aux.setTl(aux.getTl() + 1);
        }

        folha.setLig(aux, 1);
        folha.setLig(folha2.getLig(folha2.getTl()), 0);
        folha.setInfo(0, auxinfo);
        folha.setPos(0, auxpos);

        return folha;
    }

    public boolean ExcluirNo(BTreeNode folha, int info) {
        BTreeNode aux;
        BTreeNode aux1, aux2 = null;
        int i = 0, j = 0;

        i = aux2.procuraPosicao(info); // posicao do elemento a ser excluido
        aux = folha.getLig(i); // filho da esquerda
        aux1 = folha.getLig(i + 1); // filho da direita

        for (j = 0; j < aux1.getTl(); j++) // junta os filhos do NO
        {
            aux.setLig(aux1.getLig(j), aux.getTl());    // insere a ligação do filho da direita(j)  
            aux.setInfo(aux.getTl(), aux1.getInfo(j));
            aux.setPos(aux.getTl(), aux1.getPos(j));
            aux.setTl(aux.getTl() + 1);
        }
        aux.setLig(aux1.getLig(j), aux.getTl()); // aux recebe a ligação filho do seu irmao 
        folha.setLig(aux, i);

        // exclui um elemento da folha
        for (j = i; j < folha.getTl(); j++) // faz o remenejamento
        {
            folha.setInfo(j, folha.getInfo(j + 1)); // remanejamento da informação
            folha.setPos(j, folha.getPos(j + 1));   // remanejamento da posicao

            if (j != i) {
                folha.setLig(folha.getLig(j + 1), j); // remanejamento da ligação
            }
        }
        folha.setTl(folha.getTl() - 1); // decrementa o TL
        return true;
    }

    public void remover(int info) {
        BTreeNode folha = null, pai = null, aux1 = null;
        int i = 0, pos = 0, aux = 0;
        boolean ok = false;
        if (raiz == null) // raiz igual a null 
        {
            System.out.println("A árvore não possui elementos");
        } else {
            folha = navegaAteFolha(info);
            if (folha != null)// Valida para saber se o nó informado existe!
            {
                if (folha.isFolha())//Valida para saber se é uma folha, pois caso não terá procedimento diferentes
                {
                    if (folha == raiz) // A folha é tbm a raiz?
                    {
                        folha.removeInfo(folha.procuraPosicao(info)); // Então passa por parametro a folha e o elemento a excluir. fim!
                        ok = true; // Fim
                    } else // Caso a folha não for igual a Raiz
                    {
                        pai = localizaPai(folha, info); // Encontra o Pai da Folha
                        folha.removeInfo(folha.procuraPosicao(info)); // E Apaga a Info

                        // (!) 3.Se a folha continuar com o número mínimo de chaves, fim.
                        if (folha.getTl() < N)// Caso o TL fique menor que o N tem que emprestar.
                        {   aux1=folha;
                            i = aux1.procuraPosicao(info); // Procura posição da folha para emprestar !
                            if (i == 0)// quer dizer que ele so tem vizinho a direita
                            {
                                ok = PegarDireita(info, i, folha, pai);
                            } else// É a folha mais a esquerda
                             if (i > 0 && i < pai.getTl())// É uma folha do meio
                                {
                                    ok = pegaresquerda(info, i - 1, folha, pai);//Tenta da esquerda
                                    if (!ok)//Não conseguiu
                                    {
                                        ok = PegarDireita(info, i, folha, pai);//Tenta da direita
                                    }
                                } else//É a folha mais a direita
                                {
                                    ok = pegaresquerda(info, i - 1, folha, pai);//Tenta da esquerda
                                }
                            if (!ok)//Caso não tenha conseguido emprestar das folhas vizinhas!
                            {
                                System.out.println("fazer fusao nao da pra empresta");
                                if (i == 0) {
                                    fusao(i, folha, pai); // Ocorre a Fusão !
                                    ok = true;
                                } else {
                                    fusao(i - 1, folha, pai);
                                    ok = true;
                                }
                            }
                        } else {
                            ok = true;
                        }

                    }
                    if (ok) {
                        System.out.println("removido.");
                    } else {
                        System.out.println("nao remoção.");
                    }

                } else//Caso for um nó
                {
                    //1. -Se a chave não estiver numa folha, troque-a com seu sucessor imediato.
                    ok = PegarFolha(folha, info);//Tenta pegar de uma folha
                    if (!ok)//não conseguiu pegar da folha
                    {
                        if (folha != raiz)//A Info nao esta na raiz
                        {
                            pai = localizaPai(folha, info);
                            folha.removeInfo(folha.procuraPosicao(info)); // 2.Elimine a chave da folha.
                            i = aux1.procuraPosicao(info);
                            fusaoRaiz(pai);
                            ok = true;
                        } else//a info esta na raiz
                        // 4. A folha tem uma chave a menos que o mínimo. Verifique as páginas irmãs da esquerda e direita
                         if (folha.getTl() == N - 1) {
                                folha.removeInfo(folha.procuraPosicao(info)); // 2. Elimine a chave da folha.
                                BTreeNode prov = fusaofilhos(folha); // quando o NÓ está sendo excluido está na raiz
                                raiz = prov; // raiz é atualizada
                                ok = true;

                            } else {
                                ok = ExcluirNo(folha, info);
                            }

                    }

                    if (ok) {
                        System.out.println("Elemento removido.");
                    } else {
                        System.out.println("Falha na remoção.");
                    }

                }
            } else//O elemento não Existe
            {
                System.out.println("O elemento não existe.");
            }
        }
    }
}
