package com.alanma.doraemon.utils.designmode.absfactory.product.impl;

import com.alanma.doraemon.utils.designmode.absfactory.product.PrdHandlerService;
import com.alanma.doraemon.utils.designmode.absfactory.product.PrdHdlProviderService;

/**
 * 绿色能源产品
 * 
 * @author AlanMa
 *
 */
public class SolarPrdFactory implements PrdHdlProviderService {

    @Override
    public PrdHandlerService produceHandler() {
        return new SolarPrdHandler();
    }

}
