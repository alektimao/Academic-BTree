package bplusTreeAcademic;

public class BPlusTreeNode implements BPlusTreeInterface
{
    private int[] vInfo;
    private BPlusTreeNode[] vLig;
    private int tl;
    private BPlusTreeNode ant, prox;

    public BPlusTreeNode()
    {
        vInfo = new int[NINFO + 1];
        vLig = new BPlusTreeNode[N + 1];
        
        tl = 0;
        for(int i = 0; i < N; i++)
        {
            vLig[i] = null;//inicializa o vetor de lig
        }
        ant = prox = null;
    }
    
    public BPlusTreeNode(int info)
    {
        this.vInfo = new int[NINFO + 1];
        vLig = new BPlusTreeNode[N + 1];
        
        tl = 1;
        for(int i = 0; i < N; i++)
        {
            vLig[i] = null;//inicializa o vetor de lig
        }
        ant = prox = null;
        this.vInfo[0] = info;
    }

    public int getInfo(int pos)
    {
        return vInfo[pos];
    }

    public void setInfo(int pos, int info)
    {
        this.vInfo[pos] = info;
    }

    public BPlusTreeNode getLig(int pos)
    {
        if(this.vLig.length > pos)
        {
            return this.vLig[pos];
        }
        
        return null;
    }

    public void setLig(int pos, BPlusTreeNode no)
    {
        this.vLig[pos] = no;
    }

    public int getTl()
    {
        return tl;
    }

    public void setTl(int tl)
    {
        this.tl = tl;
    }

    public BPlusTreeNode getAnt()
    {
        return ant;
    }

    public void setAnt(BPlusTreeNode ant)
    {
        this.ant = ant;
    }

    public BPlusTreeNode getProx()
    {
        return prox;
    }

    public void setProx(BPlusTreeNode prox)
    {
        this.prox = prox;
    }
    
}
