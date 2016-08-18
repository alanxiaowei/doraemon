package com.alanma.doraemon.utils.list;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ListPrintUtil {
    
    
    private static void printList(List list, PrintStream out, int layer) {
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object element = it.next();
            printLayer(layer, out);
            out.print("[" + i++ + "] = ");
            if (element == null) {
                out.println();
                continue;
            }
            if (element instanceof Map) {
                out.println("[Map]");
                printMap((Map) element, out, layer + 1);
            } else if (element instanceof List) {
                out.println("[List]");
                printList((List) element, out, layer + 1);
            } else if (element instanceof byte[]) {
                out.println(new String(bytesToHexString((byte[]) element)));
            } else {
                out.println(element.toString());
            }
            
        }
    }
    
    private static void printMap(Map map, PrintStream out, int layer) {
        Iterator it = map.entrySet().iterator();
        Map.Entry entry = null;
        Object key = null;
        Object value = null;
        while (it.hasNext()) {
            entry = (Map.Entry) it.next();
            key = entry.getKey();
            value = entry.getValue();
            printLayer(layer, out);
            out.print("\"" + key + "\" = ");
            if (value == null) {
                out.println();
                continue;
            }
            if (value instanceof Map) {
                out.println("[Map]");
                printMap((Map) value, out, layer + 1);
            } else if (value instanceof List) {
                out.println("[List]");
                printList((List) value, out, layer + 1);
            } else if (value instanceof byte[]) {
                out.println(new String(bytesToHexString((byte[]) value)));
            } else {
                out.println(value.toString());
            }
        }
    }
    
    private static void printLayer(int layer, PrintStream out) {
        if (0 == layer) {
            return;
        }
        for (int i = 0; i < layer; i++) {
            out.print("    ");
        }
        out.println("|");
        for (int i = 0; i < layer; i++) {
            out.print("    ");
        }
        out.print("+----");
    }
    
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        List list =new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        List list2=new  ArrayList();
        list2.add("a2");
        list2.add("b2");
        list2.add("c2");
        list.add(list2);
        Map map =new HashMap();
        map.put("Ka3", "Va3");
        map.put("Kb3", "Vb3");
        list2.add(map);
        printList(list, System.out, 0);
        
    }
}
