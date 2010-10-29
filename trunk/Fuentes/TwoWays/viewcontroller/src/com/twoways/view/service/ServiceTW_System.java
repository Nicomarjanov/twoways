package com.twoways.view.service;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;

import java.util.List;

public class ServiceTW_System {
    
    private TwoWaysBDL twoWaysBDL;
    
    
    public ServiceTW_System() {
    
       
        try {
            twoWaysBDL = new TwoWaysBDL();
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

   public List  obtenerClientes(){
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void setTwoWaysBDL(TwoWaysBDL twoWaysBDL) {
        this.twoWaysBDL = twoWaysBDL;
    }

    public TwoWaysBDL getTwoWaysBDL() {
        return twoWaysBDL;
    }
}
