package com.alanma.doraemon.utils.string;

public class TestString {
private static String comment = "列信息JavaList，如：[[\"COL1\",VAL1],[\"COL2\",VAL2]]";
private static String comment2 = "条件信息JavaList，如：[\"列\",\"=\",值]或者[[\"列1\",\"=\",值1,\"and\"],[\"列2\",\"<\",值2]]";
private static String comment3 = "非查询类业务数据操作的入口函数,所有对数据库的非查询类操纵均需通过本接口进行操作ext_context为扩展参数字典,里面现在有如下KEY dyncondlist格式为条件的嵌套list,如[[[\"a\", \"in\", \"'3','2','1'\", \"and\"],[\"b\", \"is\", \"Null\"], \"or\"], [[\"c\", \"=\",  None, \"and\"], [\"b\", \"!=\", None]]],标识 (a in('3','2','1') and b is null) or (c=cond_data_context['c'] and b != cond_data_context['b'])";   
public static void main(String[] args) {
//       String str="SELECT busidatakey,crttype from t_arsm_dataprocsql order by busidatakey desc";
//       StringBuffer sql=new StringBuffer();
//       sql.append("select  count(*)");
//       sql.append(str.subSequence(str.indexOf(" from "), str.length()));
//       System.out.println(sql);
        System.out.println(comment);
        System.out.println(comment2);
        System.out.println(comment3);
    }
    
//    public static void main(String[] args)
//    {
//    String s="csdn.net";
//    String sChange=s.replaceAll("csdn","classjava");
//    System.out.println(sChange);
//    }
//    String sql = "select sum(reserv_max_num) from bt_reserv_time where reserv_begin_time>='%s' and reserv_end_time<='%s' and branch='%s'";
//    String sql2=sql.replaceAll("'%s'", "?");
//    System.out.println(sql2);
    
    
//    String sql="select branch from bt_sys_branch_info where branch = '%s'";
//    if(sql.indexOf(" where ")!=-1){
//        sql.indexOf(" where ");
//    }
//    System.out.println(comment);
    
    
//    StringBuffer strBuf=new StringBuffer();
//    strBuf.append("hahaha");
//    strBuf.insert( 0,"(");
//    System.out.println(strBuf.toString());
}
