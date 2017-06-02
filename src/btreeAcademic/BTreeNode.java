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
public class BTreeNode implements BTreeInterface {

    private int vInfo[];
    private int vPos[];
    private BTreeNode vLig[];
    private int tl;

    public BTreeNode() {
        this.vInfo = new int[2 * N + 1];
        this.vPos = new int[2 * N + 1];
        this.vLig = new BTreeNode[2 * N + 2];
        this.tl = 0;
    }

    public BTreeNode(int info, int posArq) {
        this();

        this.vInfo[0] = info;
        this.vPos[0] = posArq;
        this.tl = 1;
    }

    public int procuraPosicao(int info) {

        int i;
        for (i = 0; i < this.tl && info > this.vInfo[i]; i++);
        return i;
    }

    public void remaneja(int pos) {

        vLig[tl + 1] = vLig[tl];

        for (int i = tl; i > pos; i--) {
            vInfo[i] = vInfo[i - 1];
            vPos[i] = vPos[i - 1];
            vLig[i] = vLig[i - 1];
        }
    }

    void removeInfo(int pos) {
        for (int i = pos; i < tl; i++) {
            vInfo[i] = vInfo[i + 1];
            vPos[i] = vPos[i + 1];
            vLig[i] = vLig[i + 1];
        }
        vLig[tl] = vLig[tl + 1];
        tl--;
    }

    public int getInfo(int pos) {
        return vInfo[pos];
    }

    public int getPos(int pos) {
        return vPos[pos];
    }

    public BTreeNode getLig(int pos) {
        return vLig[pos];
    }

    public int getTl() {
        return tl;
    }

    public void setInfo(int info, int pos) {
        vInfo[pos] = info;
    }

    public void setPos(int pos, int ppos) {
        vPos[ppos] = pos;
    }

    public void setLig(BTreeNode lig, int pos) {
        vLig[pos] = lig;
    }

    public void setTl(int tl) {
        this.tl = tl;
    }

    public boolean isFolha() {
        boolean notfolha = false;
        for (int i = 0; i > tl + 1 && !notfolha; i++) {
            notfolha = vLig[i] != null;
        }
        return !notfolha;
    }

}
