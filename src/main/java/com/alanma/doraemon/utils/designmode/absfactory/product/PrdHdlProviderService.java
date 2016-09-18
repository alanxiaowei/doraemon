package com.alanma.doraemon.utils.designmode.absfactory.product;

/**
 * 抽象工厂提供者
 * 
 * @author AlanMa
 *
 */
public interface PrdHdlProviderService {

    /**
     * 生产处理器
     * 
     * @return
     */
    public PrdHandlerService produceHandler();
}
