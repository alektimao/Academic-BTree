/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bplusTreeAcademic;


/**
 *
 * @author titan
 */
public class BPLusTreePrinter implements BPlusTreeInterface{

    private static String depth;

//    static {
//        tprinter_depth = new char[2056];
//
//    }
    private static void treePrinterPush(char c) {
        depth += " ";
        depth += c;
        depth += " ";
        depth += " ";
        depth += " ";
        depth += " ";
        depth += " ";
    }

    private static void TreePrinterPop() {
        if (depth != null && depth.length() > 0) {
            depth = depth.substring(0, depth.length() - 7);
        }
    }

    private static void treePrinterRec(BPlusTreeNode subroot) {
        String str = "(|";
        for (int i = 0; i < NINFO; i++) {
            str += String.format("%3s|", i < subroot.getTl()?""+subroot.getInfo(i):"   ");  
        }
        System.out.println(str+")");
        

        for (int i = 0; i < subroot.getTl() && subroot.getLig(i) != null; i++) {
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

    public static void treePrinter(BPlusTreeNode root) {

        depth = "";
        System.out.println();
        if (root != null) {
            treePrinterRec(root);
        }
    }
}
