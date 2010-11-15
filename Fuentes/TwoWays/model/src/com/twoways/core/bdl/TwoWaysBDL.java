package com.twoways.core.bdl;

import com.twoways.service.TW_SystemService;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

public class TwoWaysBDL {


    BeanFactoryLocator locator;
    BeanFactoryReference bfr;
    BeanFactory factory;
    public static final String SERVICIO = "ServiceTwoWays";
    private TW_SystemService serviceTwoWays;
    
    
    
    public TwoWaysBDL() throws Exception {
    
         this.locator = ContextSingletonBeanFactoryLocator.getInstance("classpath:spring/beanRefContext.xml");
         this.bfr = this.locator.useBeanFactory("servicio-twoways");
         this.factory = this.bfr.getFactory();
         this.serviceTwoWays = ((TW_SystemService)this.factory.getBean(SERVICIO));
        
             if (this.serviceTwoWays == null) {
             throw new Exception("No se pudo obtener la instancia del Servicio");
            }
    }

    public void setServiceTwoWays(TW_SystemService serviceTwoWays) {
        this.serviceTwoWays = serviceTwoWays;
    }

    public TW_SystemService getServiceTwoWays() {
        return serviceTwoWays;
    }
}
