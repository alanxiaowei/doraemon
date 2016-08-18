package com.alanma.doraemon.utils.list;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListUtil {

    public static String printList(List list, String logInfo, int layer) {
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(strBuf);
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object element = it.next();
            strBuf.append(printLayer(layer, strBuf.toString()));
            strBuf.append("[" + i++ + "] = "); 
            if (element == null) {
                strBuf.append("\n");
                continue;
            }
            if (element instanceof Map) {
                strBuf.append("[Map]");
                strBuf.append("\n");
                strBuf.append(printMap((Map) element, strBuf.toString(), layer + 1));
            }
            else if (element instanceof List) {
                strBuf.append("[List]");
                strBuf.append("\n");
                strBuf.append(printList((List) element, strBuf.toString(), layer + 1));
            }
            else if (element instanceof byte[]) {
                strBuf.append(new String(bytesToHexString((byte[]) element)));
                strBuf.append("\n");
            }
            else {
                strBuf.append(element.toString());
                strBuf.append("\n");
            }

        }
        
        return strBuf.toString();
    }

    public static String printMap(Map map, String logInfo, int layer) {
        StringBuffer strBuf = new StringBuffer();

        Iterator it = map.entrySet().iterator();
        Map.Entry entry = null;
        Object key = null;
        Object value = null;
        while (it.hasNext()) {
            entry = (Map.Entry) it.next();
            key = entry.getKey();
            value = entry.getValue();
            strBuf.append(printLayer(layer, logInfo));
            strBuf.append("\"" + key + "\" = ");
            if (value == null) {
                strBuf.append("\n");
                continue;
            }
            if (value instanceof Map) {
                strBuf.append("[Map]");
                strBuf.append("\n");
                strBuf.append(printMap((Map) value, strBuf.toString(), layer + 1));
            }
            else if (value instanceof List) {
                strBuf.append("[List]");
                strBuf.append(printList((List) value, strBuf.toString(), layer + 1));
            }
            else if (value instanceof byte[]) {
                strBuf.append(new String(bytesToHexString((byte[]) value)));
                strBuf.append("\n");
            }
            else {
                strBuf.append(value.toString());
                strBuf.append("\n");
            }
        }

        return strBuf.toString();
    }

    private static String printLayer(int layer, String logInfo) {
        StringBuffer strBuf = new StringBuffer();
        if (0 == layer) {
            return "";
        }
        for (int i = 0; i < layer; i++) {
            strBuf.append("    ");
        }
        strBuf.append("|" + "\n");
        for (int i = 0; i < layer; i++) {
            strBuf.append("    ");
        }
        strBuf.append("+----");
        return strBuf.toString();
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
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        List list2 = new ArrayList();
        list2.add("a2");
        list2.add("b2");
        list2.add("c2");
        list.add(list2);
        Map map = new HashMap();
        map.put("Ka3", "Va3");
        map.put("Kb3", "Vb3");
        list2.add(map);
       System.out.print(printList(list, null, 0)) ;

    }
}
