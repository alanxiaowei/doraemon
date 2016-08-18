package com.alanma.doraemon.utils.split;

public class TestSplit {

    private static String T_ARSM_DATAPROCSQL_C = "busidatakey,crttype,opertype,sqlstr,infocols,dbinfocols,condcols,dbcondcols,proctablename,busidatadesc";
    private static String str = ":";
    private static String dataDic = "channelcode,channelname,channelsname,virtualflag,virtualbranch,virtualteller,channelstatus,chleffectdate,chlinvaliddate";
    private static String dbCols = "channelcode,channelname,channelsname,virtualflag,virtualbranch,virtualteller,channelstatus,chleffectdate,chlinvaliddate";

    public static void main(String[] args) {
        // split1();
        // System.out.println("dsf" + null);
        // splict2();

        getColNames(dataDic, dbCols);
    }

    private static void splict2() {
        String[] cols = str.split(":");
        System.out.print(cols.length);
        for (int i = 0; i < cols.length; i++) {
            System.out.println(cols[i]);
        }

    }

    private static void split1() {
        String[] cols = T_ARSM_DATAPROCSQL_C.split(",");
        for (int i = 0; i < cols.length; i++) {
            System.out.println(cols[i]);
        }
    }

    public static String getColNames(String dataDic, String dbCols) {
        if (dataDic == null || dbCols == null) {
            System.out.print("exception");
        }
        String[] tabColNamesDD = dataDic.split(",");
        String[] tabColNamesDB = dbCols.split(",");
        StringBuffer colName = new StringBuffer();
        for (int i = 0; i < tabColNamesDD.length; i++) {
            colName.append(tabColNamesDB[i]);
            colName.append(" ");
            colName.append(tabColNamesDD[i]);
            if (++i != tabColNamesDD.length) {
                colName.append(", ");
            }
            --i;
        }
        System.out.print("[column name is:]" + colName.toString());
        return colName.toString();
    }
}
