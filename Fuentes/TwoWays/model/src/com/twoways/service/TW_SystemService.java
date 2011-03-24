package com.twoways.service;

import com.twoways.dao.TranslatorDAO;
import com.twoways.to.AccountsTO;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.DocTypes;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import com.twoways.to.TranslatorsTO;
import com.twoways.to.UsersTO;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;

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
    
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception;
    
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
    
    public List obtenerItem(String itmType) throws Exception;
    
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
    
    public OrdersTO updateOrder(OrdersTO ordersTO) throws Exception;
    
    public OrdersTO getOrderById(Long ordId) throws Exception; 
    
    public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception;

    public List<ClientsRatesTO> getTarifaClienteById(Long cliId)  throws Exception;
    
    public List obtenerIdioma()throws Exception;
    
    public AccountsTO insertarAccount(AccountsTO accountsTO) throws Exception;
    
    public boolean deleteAccount(AccountsTO cuenta) throws Exception;
    
    public AccountsTO actualizarAccount(AccountsTO accountsTO) throws Exception;    

    public List obtenerAccount() throws Exception;    

    public AccountsTO getAccountById(String accId) throws Exception; 
    
    public List buscarAccounts(String search) throws Exception;    
    
    public List <OrdersTO> findOrders(Map orderParameters)  throws Exception;

    public ProjectsTO getProjectById(Long proId) throws Exception;
    
    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception;
    
    public ProjectsTO updateProject(ProjectsTO projectsTO) throws Exception;
    
    public ProjectsTO insertProject(ProjectsTO projectsTO) throws Exception;
    
    public List getLangByEmpId(Long empId) throws Exception; 

    public List obtenerEspecializaciones() throws Exception;

    public List obtenerAcronimos()throws Exception;
    
    public List getEmpByRatesName(String rateName) throws Exception;

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO project)throws Exception;

    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO project)throws Exception;
    
    public Long obtenerTraductorByEmpId(String empId)throws Exception;
    
    public List obtenerEspecializacionesByTraId(Long traId)throws Exception;
    
    public List <ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId) throws Exception;

    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO)throws Exception;

    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId)throws Exception;

    public ProjectAssignmentsTO getProjectAssignmentsById(Long praId)throws Exception;

    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail)throws Exception;

    public Long buscarAssignacion(String praDate, Long emp, String serv, 
                                  String proId)throws Exception;
                                  
    public Long buscarAssignacion(String praDate, Long emp)throws Exception;
                                  
   
    public List<EmployeesRatesTO> getEmpRatesByEmpId(Long empId) throws Exception;
    
    public void deleteProjectAssigmentDetailsByPraId(Map params)throws Exception ;
    
    public ItemsExpensesTO getItemsExpenseByExpId(Long expId)throws Exception ;
    
    public void deleteProjectAssigment(Map params) throws Exception ;
    
    public void  updateProjectAssigmentFromDetails(ProjectAssignmentsTO projectAssignmentsTO) throws Exception ;
    
    public void updateProjectAssigmentDetailsByPadId(ProAssigmentsDetailsTO proAssigmentsDetailsTO) throws Exception ;
    
    public boolean enviarMailAsignacion(Long praId, String message, UsersTO user) throws Exception;

    public void insertarGasto(ExpensesTO expensesTO) throws Exception;
    
    public void updateGasto(ExpensesTO expensesTO) throws Exception;    
    
    public boolean deleteGasto(String expId) throws Exception;
    
    public ExpensesTO getExpenseById(Long expId) throws Exception;
   
    public Map getCostPA(ProjectAssignmentsTO projectAssignmentsTO)throws Exception;
    
    public List getItemsExpenseByDate(String itmFecha) throws Exception;

    public List buscarListaEmpleados(String search) throws Exception;

    public List <EmployeesTO> findEmployees(Map employParameters)  throws Exception;

    public List getProjectAssignmentsByEmpId(Long empId, String mesId) throws Exception;
    
    public void insertarPago(PaymentsTO paymentsTO) throws Exception;
    
    public PaymentsTO getPaymentById(Long payId) throws Exception;
    

    public List<EmployeesRatesTO> getEmpRatesByEmpId(Long empId, String serv) throws Exception;
    
    public List<DocTypes> obtenerTipoDocumentos() throws Exception;

    
}
