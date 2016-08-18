package com.alanma.doraemon.utils.reflect.domain;

/**
 * t_arsm_dataprocsql对应对象
 * 
 * @author AlanMa
 * 
 */
public class EntityTAD {

    // 数据操作关键字
    private String busidatakey;
    // 创建方式 0-工具创建 1-手工创建
    private String crttype;
    // 操作类型
    private String opertype;
    // sql语句
    private String sqlstr;
    // 平台字段列表
    private String infocols;
    // 数据库字段列表
    private String dbinfocols;
    // 平台条件列表
    private String condcols;
    // 数据库条件列表
    private String dbcondcols;
    // 操作表名
    private String proctablename;
    // 操作说明
    private String busidatadesc;

    public String getBusidatakey() {
        return busidatakey;
    }

    public void setBusidatakey(String busidatakey) {
        this.busidatakey = busidatakey;
    }

    public String getCrttype() {
        return crttype;
    }

    public void setCrttype(String crttype) {
        this.crttype = crttype;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getSqlstr() {
        return sqlstr;
    }

    public void setSqlstr(String sqlstr) {
        this.sqlstr = sqlstr;
    }

    public String getInfocols() {
        return infocols;
    }

    public void setInfocols(String infocols) {
        this.infocols = infocols;
    }

    public String getDbinfocols() {
        return dbinfocols;
    }

    public void setDbinfocols(String dbinfocols) {
        this.dbinfocols = dbinfocols;
    }

    public String getCondcols() {
        return condcols;
    }

    public void setCondcols(String condcols) {
        this.condcols = condcols;
    }

    public String getDbcondcols() {
        return dbcondcols;
    }

    public void setDbcondcols(String dbcondcols) {
        this.dbcondcols = dbcondcols;
    }

    public String getProctablename() {
        return proctablename;
    }

    public void setProctablename(String proctablename) {
        this.proctablename = proctablename;
    }

    public String getBusidatadesc() {
        return busidatadesc;
    }

    public void setBusidatadesc(String busidatadesc) {
        this.busidatadesc = busidatadesc;
    }

}
