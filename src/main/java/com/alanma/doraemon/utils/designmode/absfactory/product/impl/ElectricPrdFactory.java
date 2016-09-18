package com.alanma.doraemon.utils.designmode.absfactory.product.impl;

import com.alanma.doraemon.utils.designmode.absfactory.product.PrdHandlerService;
import com.alanma.doraemon.utils.designmode.absfactory.product.PrdHdlProviderService;

/**
 * 电动车产品
 * 
 * @author AlanMa
 *
 */
public class ElectricPrdFactory implements PrdHdlProviderService {

    @Override
    public PrdHandlerService produceHandler() {
        return new ElectricPrdHandler();
    }

}
