package com.twoways.service;

import com.twoways.dao.AccountDAO;
import com.twoways.dao.ClientDAO;
import com.twoways.dao.CurrencyDAO;
import com.twoways.dao.EmployeeDAO;
import com.twoways.dao.OrdersDAO;
import com.twoways.dao.ProjectDAO;
import com.twoways.dao.RateDAO;
import com.twoways.dao.RateTypesDAO;
import com.twoways.dao.ServiceDAO;
import com.twoways.dao.UserDAO;
import com.twoways.dao.RolesDAO;
import com.twoways.dao.ItemDAO;
import com.twoways.dao.LanguagueDAO;
import com.twoways.dao.TranslatorDAO;

import com.twoways.to.AccountsTO;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.TranslatorsTO;
import com.twoways.to.UsersTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TW_SystemServiceImpl implements TW_SystemService{
    
    private ClientDAO clientDao;
    private EmployeeDAO employeeDao;
    private CurrencyDAO currencyDao;
    private RateDAO rateDao;
    private RateTypesDAO rateTypesDao;
    private UserDAO userDao;
    private RolesDAO rolesDao;
    private ItemDAO itemDao;
    private ServiceDAO serviceDao;
    private TranslatorDAO translatorDao;
    private OrdersDAO ordersDao;
    private ProjectDAO projectDao;
    private LanguagueDAO languagueDao;
    private AccountDAO accountDao;

     
    public TW_SystemServiceImpl() {
    }

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDAO getClientDao() {
        return clientDao;
    }
    
    public List obtenerClientes() throws Exception
    {
    return this.clientDao.obtenerClientes();
    }

    public void setEmployeeDao(EmployeeDAO employeeDao){
        this.employeeDao = employeeDao;
    }
    
    public EmployeeDAO getEmployeeDao(){
        return employeeDao;
    }

    public List obtenerEmpleados() throws Exception {
        return this.employeeDao.obtenerEmpleados();
    }
    
    public EmployeesTO insertarEmpleado(EmployeesTO employeesTO) throws Exception {
      return this.employeeDao.insertarEmployee(employeesTO);
    }
    
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception {
      return this.employeeDao.updateEmpleado(employeesTO);
    }
    
    public EmployeesTO getEmpById(String empId)  throws Exception{
      return  this.employeeDao.getEmpById(empId);
    }
    
    
    public List buscarEmpleados(String search) throws Exception {
      return this.employeeDao.buscarEmpleados(search);
    }
    
    public boolean  deleteEmployees(EmployeesTO employeesTO)  throws Exception{
      return this.employeeDao.deleteEmployee(employeesTO);
    }

    public List obtenerTipoEmpleado() throws Exception{
          return this.employeeDao.obtenerTipoEmpleado();
      }
  
    public List obtenerTipoEmpleadoById(String empId) throws Exception{
          return this.employeeDao.obtenerTipoEmpleadoById(empId);
      }    
      
    public void setCurrencyDao(CurrencyDAO currencyDao){
        this.currencyDao = currencyDao;
    }
    
    public CurrencyDAO getCurrencyDao(){
        return currencyDao;
    }

    public List obtenerMonedas() throws Exception {
        return this.currencyDao.obtenerMonedas();
    }
    
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception {
        return this.clientDao.insertarCliente(clientsTO);
    }
    

    public ClientsTO updateCliente(ClientsTO clientsTO) throws Exception {
        return this.clientDao.updateCliente(clientsTO);
    }
    
    public ClientsTO getClientById(String cliId)  throws Exception{
        return  this.clientDao.getClientById(cliId);
    }


    public List buscarClientes(String search) throws Exception {
        return this.clientDao.buscarClientes(search);
    }
    
    public boolean  deleteClients(ClientsTO client)  throws Exception{
        return this.clientDao.deleteClients(client);
    }

    public List obtenerTarifas() throws Exception{
        return this.rateDao.obtenerTarifas();
    }

    public void insertarTarifa(RatesTO ratesTO) throws Exception{
        this.rateDao.insertarTarifa(ratesTO);
    }
    
    public void actualizarTarifa(RatesTO ratesTO) throws Exception {
        this.rateDao.actualizarTarifa(ratesTO);
    }

    public void setRateDao(RateDAO rateDao) {
        this.rateDao = rateDao;
    }

    public RateDAO getRateDao() {
        return rateDao;
    }
    
    public  RatesTO getRateById(String ratId) throws Exception{
        return  this.rateDao.getRateById(ratId);
    }
    
    public  List getRateByType(RateTypesTO rateTypesTO) throws Exception{
        return  this.rateDao.getRatesByType(rateTypesTO);
    }
    
    public boolean  deleteRate(RatesTO rate)  throws Exception{
        return this.rateDao.deleteRate(rate);
    }
    
    public List buscarTarifas(String search) throws Exception {
        return this.rateDao.buscarTarifas(search);
    }
    
    public CurrencyTO getCurrencyById(String curId)  throws Exception{        
        return this.currencyDao.getCurrencyById(curId);        
    }

    public List obtenerTipoTarifas() throws Exception{
        return this.rateTypesDao.obtenerTipoTarifas();
    }

    public void setRateTypesDao(RateTypesDAO rateTypesDao) {
        this.rateTypesDao = rateTypesDao;
    }

    public RateTypesDAO getRateTypesDao() {
        return rateTypesDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public List obtenerUsuarios() throws Exception{
        return this.userDao.obtenerUsuarios();
    }

    public void insertarUsuario(UsersTO usersTO) throws Exception{
        this.userDao.insertarUsuario(usersTO);
    }

    public void updateUsuario(UsersTO usersTO) throws Exception{
        this.userDao.updateUsuario(usersTO);
    }

    public UsersTO getUserById(String usrId) throws Exception{
        return this.userDao.getUserById(usrId);  
    }

    public List buscarUsuario(String search) throws Exception{
        return this.userDao.buscarUsuarios(search);
    }
    
    public List buscarUsuarioId(String search) throws Exception{
          return this.userDao.buscarUsuariosId(search);
      }

    public boolean deleteUsers(UsersTO user) throws Exception{
        return this.userDao.deleteUser(user);
    }
   
    public List obtenerRoles() throws Exception{
        return this.rolesDao.obtenerRoles();
    }

    public void setRolesDao(RolesDAO rolesDao) {
        this.rolesDao = rolesDao;
    }

    public RolesDAO getRolesDao() {
        return rolesDao;
    }
    
    public UsersTO getLogin(String userId, String pass) throws Exception {
    
          String passIn = this.userDao.getPass(userId);
          if(passIn != null && encript(passIn).equals(pass)){
              return this.userDao.getUserById(userId);
          }else{
              return null; 
          }
    }
    
    private String encript(String pass){
        return pass;
    }    
    
    public void setItemDao(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }
    
    public ItemDAO getItemDao() {
        return itemDao;
    }  
    public void insertarItem(ItemsTO itemsTO) throws Exception{
            this.itemDao.insertarItem(itemsTO);
        }
        
    public void actualizarItem(ItemsTO itemsTO) throws Exception {
            this.itemDao.actualizarItem(itemsTO);
        }

    public boolean deleteItem(ItemsTO item) throws Exception{
          return this.itemDao.deleteItem(item);
      }
      
    public List obtenerItem() throws Exception{
          return this.itemDao.obtenerItem();
      }      
      
    public ItemsTO getItemById(String itmId) throws Exception{
          return this.itemDao.getItemById(itmId);  
      }      
      
    public List buscarItems(String search) throws Exception{
      return this.itemDao.buscarItems(search);
    }      

    public void setServiceDao(ServiceDAO serviceDao) {
        this.serviceDao = serviceDao;
    }

    public ServiceDAO getServiceDao() {
        return serviceDao;
    }

    public List obtenerServicios() throws Exception {
        return serviceDao.obtenerServices();
    }

    public void setTranslatorDao(TranslatorDAO translatorDao){
        this.translatorDao = translatorDao;
    }
    
    public TranslatorDAO getTranslatorDao(){
        return translatorDao;
    }

    public List obtenerTraductores() throws Exception {
        return this.translatorDao.obtenerTraductores();
    }
    
    public TranslatorsTO insertarTraductor(TranslatorsTO translatorsTO) throws Exception {
      return this.translatorDao.insertarTraductor(translatorsTO);
    }
    
    public void updateTraductor(TranslatorsTO translatorsTO) throws Exception {
      this.translatorDao.updateTraductor(translatorsTO);
    }
    
    public TranslatorsTO getTraByEmpId(String empId)  throws Exception{
      return  this.translatorDao.getTraByEmpId(empId);
    }    
    
    public TranslatorsTO getTraById(Long traId)  throws Exception{
      return  this.translatorDao.getTraById(traId);
    }  
    
    public List buscarTraductores(String search) throws Exception {
      return this.translatorDao.buscarTraductores(search);
    }
    
    public boolean deleteTraductor(TranslatorsTO translatorsTO) throws Exception{
          return this.translatorDao.deleteTraductor(translatorsTO);
      }    

  
    public ClientsTO getClientByName(String nombre) throws Exception {
        return clientDao.getClientByName(nombre) ; 
    }

    public List obtenerIdioma() throws Exception{
        return this.languagueDao.obtenerIdioma();
    }

    public void setLanguagueDao(LanguagueDAO languagueDao) {
        this.languagueDao = languagueDao;
    }

    public LanguagueDAO getLanguagueDao() {
        return languagueDao;
    }

    public void setOrdersDao(OrdersDAO ordersDao) {
        this.ordersDao = ordersDao;
    }

    public OrdersDAO getOrdersDao() {
        return ordersDao;
    }
    
    public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception {        
         ordersTO = this.ordersDao.insertarOrder(ordersTO);
        
        return ordersTO;                
    }
    
    public OrdersTO updateOrder(OrdersTO ordersTO) throws Exception {
        
        ordersTO = this.ordersDao.updateOrder(ordersTO);
        return ordersTO;
        
        
    }
    
    public OrdersTO getOrderById(Long ordId) throws Exception{
        return this.ordersDao.getOrderById(ordId);
    }
    
    public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception{
        return this.ordersDao.getOrdersDocById(docId);
    }
    
    public List<ClientsRatesTO> getTarifaClienteById(Long cliId)  throws Exception{
        return clientDao.getTarifaClienteById(cliId);
    }

    public void setAccountDao(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }
    
    public AccountDAO getAccountDao() {
        return accountDao;
    }  
    public AccountsTO insertarAccount(AccountsTO accountsTO) throws Exception{
           return this.accountDao.insertarAccount(accountsTO);
        }
        
    public AccountsTO actualizarAccount(AccountsTO accountsTO) throws Exception {
           return this.accountDao.actualizarAccount(accountsTO);
        }

    public boolean deleteAccount(AccountsTO cuenta) throws Exception{
          return this.accountDao.deleteAccount(cuenta);
      }
      
    public List obtenerAccount() throws Exception{
          return this.accountDao.obtenerAccount();
      }      
      
    public AccountsTO getAccountById(String accId) throws Exception{
          return this.accountDao.getAccountById(accId);  
      }      
      
    public List buscarAccounts(String search) throws Exception{
      return this.accountDao.buscarAccounts(search);
    }      
    
    public List obtenerAcronimos() throws Exception{
        return this.languagueDao.obtenerAcronimos();
    }

    public List obtenerEspecializaciones() throws Exception{
        return this.translatorDao.obtenerEspecializaciones();
    }

    public List <OrdersTO> findOrders(Map orderParameters)  throws Exception{
        return this.ordersDao.findOrders(orderParameters);
    }
   
    public ProjectsTO getProjectById(Long proId) throws Exception{
        
        ProjectsTO proyecto =  this.projectDao.getProjectById(proId);
        proyecto.setOrdersTO(ordersDao.getOrderById(proyecto.getOrdersTO().getOrdId()));
        proyecto.setProjectAssignmentsTOList(this.getProjectAssignmentsByProId(proId));
        
        return proyecto;
    }
    
    public List <ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId)throws Exception{
        
        return projectDao.getProjectAssignmentsByProId(proId);
    }
    
    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception{
        
        ProjectsTO proyecto =  this.projectDao.getProjectByOrdId(ordId);
        if(proyecto!= null){ 
            proyecto.setOrdersTO(ordersDao.getOrderById(proyecto.getOrdersTO().getOrdId()));
        }
        return proyecto;
    }
    

    public void setProjectDao(ProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectDAO getProjectDao() {
        return projectDao;
    }

    public ProjectsTO updateProject(ProjectsTO projectsTO) throws Exception {
        return projectDao.updateProject(projectsTO);
    }

    public ProjectsTO insertProject(ProjectsTO projectsTO) throws Exception {
        return projectDao.insertProject(projectsTO);
    }

    public List getLangByTradId(Long tradId) throws Exception{
        return this.translatorDao.getLangByTradId(tradId);
    }
    
    public List getEmpByRatesName(String rateName) throws Exception {
        return this.employeeDao.getEmpByRatesName(rateName);
    }

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO)throws Exception {
        return this.projectDao.updateProjectAssignament(projectAssignmentsTO);
    }

    public Long obtenerTraductorByEmpId(String empId)throws Exception{
        return this.translatorDao.obtenerTraductorByEmpId(empId);
    }
    
    public List obtenerEspecializacionesByTraId(Long traId)throws Exception{
        return this.translatorDao.obtenerEspecializacionesByTraId(traId);
    }
    
    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO)throws Exception {
        return this.projectDao.insertProjectAssignament(projectAssignmentsTO);
    }

    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO) throws Exception {
       
       List<EmployeesRatesTO> employeesRatesTOList = employeeDao.getEmpRatesByEmpId(proAssigmentsDetailsTO.getProjectAssignmentsTO().getEmployeesTO());
       this.projectDao.insertProjectAssignamentDetails( proAssigmentsDetailsTO,employeesRatesTOList);
    }
    
    
    public List<EmployeesRatesTO> getEmpRatesByEmpId(Long empId) throws Exception {
        EmployeesTO employeesTO= new  EmployeesTO();
        employeesTO.setEmpId(empId);
        return  employeeDao.getEmpRatesByEmpId(employeesTO);
    }

    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId)throws Exception {
        return this.projectDao.getProjectAssignmentsDetailsById( praId);
    }

    public ProjectAssignmentsTO getProjectAssignmentsById(Long praId)throws Exception {
        return this.projectDao.getProjectAssignmentsById(praId);
    }

    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail) throws Exception {
    
       this.projectDao.deleteProjectAssignamentDetails(detail);
    }

    public Long buscarAssignacion(String praDate, Long emp, String serv, 
                                     String proId) throws Exception {
        return this.projectDao.findProjectAssignament(praDate,emp,serv,proId);
    }
    
    public void deleteProjectAssigmentDetailsByPraId(Map params)throws Exception {
        this.projectDao.deleteProjectAssigmentDetailsByPraId(params); 
    }
}


