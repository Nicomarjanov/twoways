package com.twoways.test;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;

import java.util.List;

public class AppTest {
    public AppTest() {
    }
    
    
    public static void main(String[] args) {
        TwoWaysBDL twoWaysBDL;

        try {
            twoWaysBDL = new TwoWaysBDL();
            
            twoWaysBDL.getServiceTwoWays().obtenerClientes();
            List<ClientsTO> clientes =  twoWaysBDL.getServiceTwoWays().obtenerClientes();
            for(ClientsTO client : clientes){
                System.out.println(client.getCliName());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
