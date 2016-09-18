package com.alanma.doraemon.utils.designmode.absfactory.product;

/**
 * 产品处理器
 * 
 * @author AlanMa
 *
 */
public interface PrdHandlerService {

    /**
     * 校验产品信息
     */
    public void checkPrdInfo(String prductName) throws Exception;

    /**
     * 锁定产品份额
     */
    public void lockPrdQuota(String productId, Long purchasePortion) throws Exception;

    /**
     * 解锁产品份额
     */
    public void unlockPrdQuota(String productId, Long purchasePortion) throws Exception;
}
