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
public class BTreePrinter implements BTreeInterface{

    static String depth;
    static int di;

//    static {
//        tprinter_depth = new char[2056];
//
//    }
    private static void treePrinterPush(char c) {
        depth += " ";
        depth += c;
        depth += " ";
        depth += " ";
    }

    private static void TreePrinterPop() {
        if (depth != null && depth.length() > 0) {
            depth = depth.substring(0, depth.length() - 4);
        }
    }

    private static void treePrinterRec(BTreeNode subroot) {
        String str = "(|";
        for (int i = 0; i < 2*N; i++) {
            str += String.format("%3s|", i < subroot.getTl()?""+subroot.getInfo(i):"   ");  
        }
        System.out.println(str+")");
        

        for (int i = 0; i < subroot.getTl()-1 && subroot.getLig(subroot.getTl()) != null; i++) {
            System.out.print(depth+" `-(lig-"+i+")--");
            treePrinterPush('|');
            treePrinterRec(subroot.getLig(i));
            TreePrinterPop();
        }
        if (subroot.getLig(subroot.getTl()) != null ){
            System.out.print(depth+" `-(lig-"+subroot.getTl()+")--");
            treePrinterPush(' ');
            treePrinterRec(subroot.getLig(subroot.getTl()));
            TreePrinterPop();
        }

    }

    static void treePrinter(BTreeNode root) {

        depth = "";
        System.out.println();
        if (root != null) {
            treePrinterRec(root);
        }
    }
}
