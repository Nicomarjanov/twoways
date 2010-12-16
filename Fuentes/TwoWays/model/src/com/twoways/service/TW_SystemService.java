package com.twoways.service;

import com.twoways.dao.TranslatorDAO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import com.twoways.to.TranslatorsTO;
import com.twoways.to.UsersTO;

import java.util.List;

public interface TW_SystemService {

    public List obtenerClientes() throws Exception;
    
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception ;
    
    public ClientsTO updateCliente(ClientsTO clientsTO) throws Exception ;
    
    public ClientsTO getClientById(String cliId)  throws Exception;
    
    //public String getClientByName(String empName)  throws Exception;
    
    public List buscarClientes(String search) throws Exception;
    
    public boolean  deleteClients(ClientsTO client)  throws Exception;
    
    public List obtenerEmpleados() throws Exception;
    
    public EmployeesTO insertarEmpleado(EmployeesTO employeesTO) throws Exception;
    
    public void updateEmpleado(EmployeesTO employeesTO) throws Exception;
    
    public EmployeesTO getEmpById(String empId)  throws Exception;
    
    public List buscarEmpleados(String search) throws Exception;
    
    public boolean  deleteEmployees(EmployeesTO employeesTO)  throws Exception;
    
    public List obtenerTipoEmpleado() throws Exception;
    
    public List obtenerTipoEmpleadoById(String empId) throws Exception;

    public List obtenerMonedas() throws Exception;
    
    public List obtenerTarifas() throws Exception;
    
    public List obtenerTipoTarifas() throws Exception;
    
    public void insertarTarifa(RatesTO ratesTO) throws Exception ;

    public void actualizarTarifa(RatesTO ratesTO) throws Exception ;    

    public RatesTO getRateById(String ratId)  throws Exception;
    
    public boolean deleteRate(RatesTO rate)  throws Exception;

    public List buscarTarifas(String search) throws Exception;
    
    public  List getRateByType(RateTypesTO rateTypesTO) throws Exception;

    public List obtenerUsuarios() throws Exception;
    
    public void insertarUsuario(UsersTO usersTO) throws Exception ;
    
    public void updateUsuario(UsersTO usersTO) throws Exception ;
    
    public UsersTO getUserById(String usrId)  throws Exception;
    
    public List buscarUsuario(String search) throws Exception;

    public List buscarUsuarioId(String search) throws Exception;    
    
    public boolean  deleteUsers(UsersTO user)  throws Exception;
    
    public List obtenerRoles() throws Exception;
    
    public UsersTO getLogin(String userId, String pass) throws Exception ;
      
    public void insertarItem(ItemsTO itemsTO) throws Exception;
    
    public void actualizarItem(ItemsTO itemsTO) throws Exception;
    
    public boolean deleteItem(ItemsTO item) throws Exception;
    
    public List obtenerItem() throws Exception;
    
    public ItemsTO getItemById(String itmId)  throws Exception;

    public List buscarItems(String search) throws Exception;    
    
    public List obtenerServicios() throws Exception;        

    public List obtenerTraductores() throws Exception;
    
    public TranslatorsTO insertarTraductor(TranslatorsTO translatorsTO) throws Exception;
    
    public void updateTraductor(TranslatorsTO translatorsTO) throws Exception ;
    
    public TranslatorsTO getTraByEmpId(String empId)  throws Exception;    
    
    public List buscarTraductores(String search) throws Exception;
    
    public boolean  deleteTraductor(TranslatorsTO translatorsTO) throws Exception;    

    public ClientsTO getClientByName(String name) throws Exception;
    
    public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception;
    
    public OrdersTO getOrderById(Long ordId) throws Exception; 
    
    public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception;
    
    public List<ClientsRatesTO> getTarifaClienteById(Long cliId)  throws Exception;
    
    public List obtenerIdioma()throws Exception;
   
}
