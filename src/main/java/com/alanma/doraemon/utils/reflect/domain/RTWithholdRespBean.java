package com.alanma.doraemon.utils.reflect.domain;

/**
 * 实时代扣应答
 * 
 * @author AlanMa
 *
 */
public class RTWithholdRespBean {

    /**
     * 应答码
     */
    private String responseCode;
    /**
     * 商户号
     */
    private String merId;
    /**
     * 商户日期
     */
    private String transDate;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 金额
     */
    private String transAmt;
    /**
     * 币种
     */
    private String curyId;
    /**
     * 交易类型
     */
    private String transType;
    /**
     * 私有域
     */
    private String priv1;
    /**
     * 代扣状态
     */
    private String transStat;
    /**
     * 网关号
     */
    private String gateId;
    /**
     * 卡折标志
     */
    private String cardType;
    /**
     * 卡号/折号
     */
    private String cardNo;
    /**
     * 持卡人姓名
     */
    private String userNme;
    /**
     * 证件类型
     */
    private String certType;
    /**
     * 证件号
     */
    private String certId;
    /**
     * 描述
     */
    private String message;
    /**
     * 渠道返回日期
     */
    private String chnlRetDate;
    /**
     * 签名值
     */
    private String chkValue;
    /**
     * 内部交易类型
     */
    private String innerTransType;
    
    public String getResponseCode() {
        return responseCode;
    }
    
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    
    public String getMerId() {
        return merId;
    }
    
    public void setMerId(String merId) {
        this.merId = merId;
    }
    
    public String getTransDate() {
        return transDate;
    }
    
    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getTransAmt() {
        return transAmt;
    }
    
    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }
    
    public String getCuryId() {
        return curyId;
    }
    
    public void setCuryId(String curyId) {
        this.curyId = curyId;
    }
    
    public String getTransType() {
        return transType;
    }
    
    public void setTransType(String transType) {
        this.transType = transType;
    }
    
    public String getPriv1() {
        return priv1;
    }
    
    public void setPriv1(String priv1) {
        this.priv1 = priv1;
    }
    
    public String getTransStat() {
        return transStat;
    }
    
    public void setTransStat(String transStat) {
        this.transStat = transStat;
    }
    
    public String getGateId() {
        return gateId;
    }
    
    public void setGateId(String gateId) {
        this.gateId = gateId;
    }
    
    public String getCardType() {
        return cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    public String getCardNo() {
        return cardNo;
    }
    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    
    public String getUserNme() {
        return userNme;
    }
    
    public void setUserNme(String userNme) {
        this.userNme = userNme;
    }
    
    public String getCertType() {
        return certType;
    }
    
    public void setCertType(String certType) {
        this.certType = certType;
    }
    
    public String getCertId() {
        return certId;
    }
    
    public void setCertId(String certId) {
        this.certId = certId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getChnlRetDate() {
        return chnlRetDate;
    }
    
    public void setChnlRetDate(String chnlRetDate) {
        this.chnlRetDate = chnlRetDate;
    }
    
    public String getChkValue() {
        return chkValue;
    }
    
    public void setChkValue(String chkValue) {
        this.chkValue = chkValue;
    }
    
    public String getInnerTransType() {
        return innerTransType;
    }
    
    public void setInnerTransType(String innerTransType) {
        this.innerTransType = innerTransType;
    }

    @Override
    public String toString() {
        return "RTWithholdRespBean [responseCode=" + responseCode + ", merId=" + merId + ", transDate=" + transDate + ", orderNo=" + orderNo + ", transAmt=" + transAmt + ", curyId=" + curyId
                + ", transType=" + transType + ", priv1=" + priv1 + ", transStat=" + transStat + ", gateId=" + gateId + ", cardType=" + cardType + ", cardNo=" + cardNo + ", userNme=" + userNme
                + ", certType=" + certType + ", certId=" + certId + ", message=" + message + ", chnlRetDate=" + chnlRetDate + ", chkValue=" + chkValue + ", innerTransType=" + innerTransType + "]";
    }

}
