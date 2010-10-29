package com.twoways.test;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;

import com.twoways.to.EmployeesTO;

import java.util.List;

public class AppTest {
    public AppTest() {
    }
    
    
    public static void main(String[] args) {
        TwoWaysBDL twoWaysBDL;

        try {
            twoWaysBDL = new TwoWaysBDL();
            
            twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            List<EmployeesTO> empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            for(EmployeesTO employee : empleados){
                System.out.println(employee.getEmpMail());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
