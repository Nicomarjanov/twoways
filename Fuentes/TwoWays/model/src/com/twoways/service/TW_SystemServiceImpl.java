package com.twoways.service;

import com.twoways.dao.ClientDAO;
import com.twoways.dao.CurrencyDAO;
import com.twoways.dao.EmployeeDAO;
import com.twoways.dao.RateDAO;
import com.twoways.dao.RateTypesDAO;
import com.twoways.dao.UserDAO;
import com.twoways.dao.RolesDAO;

import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.UsersTO;

import java.util.Collections;
import java.util.List;

public class TW_SystemServiceImpl implements TW_SystemService{
    
    private ClientDAO clientDao;
    private EmployeeDAO employeeDao;
    private CurrencyDAO currencyDao;
    private RateDAO rateDao;
    private RateTypesDAO rateTypesDao;
    private UserDAO userDao;
    private RolesDAO rolesDao;
     
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

    public void updateCliente(ClientsTO clientsTO) throws Exception {
        this.clientDao.updateCliente(clientsTO);
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
    
    
  
    
    
  }
