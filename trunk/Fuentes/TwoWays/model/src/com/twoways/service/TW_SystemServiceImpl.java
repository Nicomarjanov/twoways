package com.twoways.service;

import com.twoways.dao.AccountDAO;
import com.twoways.dao.ClientDAO;
import com.twoways.dao.CotizationDAO;
import com.twoways.dao.CurrencyDAO;
import com.twoways.dao.DocTypesDAO;
import com.twoways.dao.EmployeeDAO;
import com.twoways.dao.ExpenseDAO;
import com.twoways.dao.ExpensesDAOImpl;
import com.twoways.dao.InvoiceDAO;
import com.twoways.dao.OrdersDAO;
import com.twoways.dao.ProjectDAO;
import com.twoways.dao.RateDAO;
import com.twoways.dao.RateTypesDAO;
import com.twoways.dao.ServiceDAO;
import com.twoways.dao.StatesDAO;
import com.twoways.dao.UserDAO;
import com.twoways.dao.RolesDAO;
import com.twoways.dao.ItemDAO;
import com.twoways.dao.LanguagueDAO;
import com.twoways.dao.PaymentDAO;
import com.twoways.dao.TranslatorDAO;

import com.twoways.to.AccountsTO;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CotizationsTO;
import com.twoways.to.CurrencyCotizationsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.DocTypes;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.InvoicesTO;
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
import com.twoways.to.StatesTO;
import com.twoways.to.TranslatorsTO;
import com.twoways.to.UsersTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


public class TW_SystemServiceImpl implements TW_SystemService {

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
    private ExpenseDAO expensesDao;
    private PaymentDAO paymentDao;
    private DocTypesDAO docTypesDao;
    private CotizationDAO cotizationsDao;
    private InvoiceDAO invoiceDao;
    private StatesDAO statesDao;

    public TW_SystemServiceImpl() {
    }

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDAO getClientDao() {
        return clientDao;
    }

    public List obtenerClientes() throws Exception {
        return this.clientDao.obtenerClientes();
    }

    public void setEmployeeDao(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeDAO getEmployeeDao() {
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

    public EmployeesTO getEmpById(String empId) throws Exception {
        return this.employeeDao.getEmpById(empId);
    }

    public List buscarEmpleados(String search) throws Exception {
        return this.employeeDao.buscarEmpleados(search);
    }
    
    public boolean buscarAsignacionesEmpleado(String empId) throws Exception {
        return this.employeeDao.buscarAsignacionesEmpleado(empId);
    }

    public boolean deleteEmployees(EmployeesTO employeesTO) throws Exception {
        return this.employeeDao.deleteEmployee(employeesTO);
    }

    public boolean updateDeleteEmployee(EmployeesTO employeesTO) throws Exception{
        return this.employeeDao.updateDeleteEmployee(employeesTO);
    }
    
    public List obtenerTipoEmpleado() throws Exception {
        return this.employeeDao.obtenerTipoEmpleado();
    }

    public List obtenerTipoEmpleadoById(String empId) throws Exception {
        return this.employeeDao.obtenerTipoEmpleadoById(empId);
    }

    public void setCurrencyDao(CurrencyDAO currencyDao) {
        this.currencyDao = currencyDao;
    }

    public CurrencyDAO getCurrencyDao() {
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

    public ClientsTO getClientById(String cliId) throws Exception {
        return this.clientDao.getClientById(cliId);
    }


    public List buscarClientes(String search) throws Exception {
        return this.clientDao.buscarClientes(search);
    }

    public boolean deleteClients(ClientsTO client) throws Exception {
        return this.clientDao.deleteClients(client);
    }

    public List obtenerTarifas() throws Exception {
        return this.rateDao.obtenerTarifas();
    }

    public void insertarTarifa(RatesTO ratesTO) throws Exception {
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

    public RatesTO getRateById(String ratId) throws Exception {
        return this.rateDao.getRateById(ratId);
    }

    public List getRateByType() throws Exception {
        return this.rateDao.getRatesByType();
    }

    public boolean deleteRate(RatesTO rate) throws Exception {
        return this.rateDao.deleteRate(rate);
    }

    public List buscarTarifas(String search) throws Exception {
        return this.rateDao.buscarTarifas(search);
    }

    public CurrencyTO getCurrencyById(String curId) throws Exception {
        return this.currencyDao.getCurrencyById(curId);
    }

    public List obtenerTipoTarifas() throws Exception {
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

    public List obtenerUsuarios() throws Exception {
        return this.userDao.obtenerUsuarios();
    }

    public void insertarUsuario(UsersTO usersTO) throws Exception {
        this.userDao.insertarUsuario(usersTO);
    }

    public void updateUsuario(UsersTO usersTO) throws Exception {
        this.userDao.updateUsuario(usersTO);
    }

    public UsersTO getUserById(String usrId) throws Exception {
        return this.userDao.getUserById(usrId);
    }

    public List buscarUsuario(String search) throws Exception {
        return this.userDao.buscarUsuarios(search);
    }

    public List buscarUsuarioId(String search) throws Exception {
        return this.userDao.buscarUsuariosId(search);
    }

    public boolean deleteUsers(UsersTO user) throws Exception {
        return this.userDao.deleteUser(user);
    }

    public List obtenerRoles() throws Exception {
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
        if (passIn != null && encript(passIn).equals(pass)) {
            return this.userDao.getUserById(userId);
        } else {
            return null;
        }
    }

    private String encript(String pass) {
        return pass;
    }

    public void setItemDao(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }

    public ItemDAO getItemDao() {
        return itemDao;
    }

    public void insertarItem(ItemsTO itemsTO) throws Exception {
        this.itemDao.insertarItem(itemsTO);
    }

    public void actualizarItem(ItemsTO itemsTO) throws Exception {
        this.itemDao.actualizarItem(itemsTO);
    }

    public boolean deleteItem(ItemsTO item) throws Exception {
        return this.itemDao.deleteItem(item);
    }

    public List obtenerItem(String itmType) throws Exception {
        return this.itemDao.obtenerItem(itmType);
    }

    public ItemsTO getItemById(String itmId) throws Exception {
        return this.itemDao.getItemById(itmId);
    }

    public List buscarItems(String search) throws Exception {
        return this.itemDao.buscarItems(search);
    }

    public void setServiceDao(ServiceDAO serviceDao) {
        this.serviceDao = serviceDao;
    }

    public ServiceDAO getServiceDao() {
        return serviceDao;
    }

    public List obtenerServicios() throws Exception {
        
       
        return this.rateTypesDao.obtenerTipoServicios();
    }

    public void setTranslatorDao(TranslatorDAO translatorDao) {
        this.translatorDao = translatorDao;
    }

    public TranslatorDAO getTranslatorDao() {
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

    public TranslatorsTO getTraByEmpId(String empId) throws Exception {
        return this.translatorDao.getTraByEmpId(empId);
    }

    public TranslatorsTO getTraById(Long traId) throws Exception {
        return this.translatorDao.getTraById(traId);
    }

    public List buscarTraductores(String search) throws Exception {
        return this.translatorDao.buscarTraductores(search);
    }

    public boolean deleteTraductor(TranslatorsTO translatorsTO) throws Exception {
        return this.translatorDao.deleteTraductor(translatorsTO);
    }


    public ClientsTO getClientByName(String nombre) throws Exception {
        return clientDao.getClientByName(nombre);
    }

    public List obtenerIdioma() throws Exception {
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

    public OrdersTO getOrderById(Long ordId) throws Exception {
        return this.ordersDao.getOrderById(ordId);
    }

    public OrdersDocsTO getOrdersDocById(Long docId) throws Exception {
        return this.ordersDao.getOrdersDocById(docId);
    }

    public List<ClientsRatesTO> getTarifaClienteById(Long cliId) throws Exception {
        return clientDao.getTarifaClienteById(cliId);
    }

    public void setAccountDao(AccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    public AccountDAO getAccountDao() {
        return accountDao;
    }

    public AccountsTO insertarAccount(AccountsTO accountsTO) throws Exception {
        return this.accountDao.insertarAccount(accountsTO);
    }

    public AccountsTO actualizarAccount(AccountsTO accountsTO) throws Exception {
        return this.accountDao.actualizarAccount(accountsTO);
    }

    public boolean deleteAccount(AccountsTO cuenta) throws Exception {
        return this.accountDao.deleteAccount(cuenta);
    }

    public List obtenerAccount() throws Exception {
        return this.accountDao.obtenerAccount();
    }

    public AccountsTO getAccountById(String accId) throws Exception {
        return this.accountDao.getAccountById(accId);
    }

    public List buscarAccounts(String search) throws Exception {
        return this.accountDao.buscarAccounts(search);
    }

    public List obtenerAcronimos() throws Exception {
        return this.languagueDao.obtenerAcronimos();
    }

    public List obtenerEspecializaciones() throws Exception {
        return this.translatorDao.obtenerEspecializaciones();
    }

    public List<OrdersTO> findOrders(Map orderParameters) throws Exception {
        return this.ordersDao.findOrders(orderParameters);
    }

    public ProjectsTO getProjectById(Long proId) throws Exception {

        ProjectsTO proyecto = this.projectDao.getProjectById(proId);
        proyecto.setOrdersTO(ordersDao.getOrderById(proyecto.getOrdersTO().getOrdId()));
        proyecto.setProjectAssignmentsTOList(this.getProjectAssignmentsByProId(proId));

        return proyecto;
    }

    public List<ProjectAssignmentsTO> getProjectAssignmentsByProId(Long proId) throws Exception {

        return projectDao.getProjectAssignmentsByProId(proId);
    }

    public ProjectsTO getProjectByOrdId(Long ordId) throws Exception {

        ProjectsTO proyecto = this.projectDao.getProjectByOrdId(ordId);
        if (proyecto != null) {
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

    public List getLangByEmpId(Long empId) throws Exception{
        return this.employeeDao.getLangByEmpId(empId);
    }

    public List getEmpByRatesName(String rateName) throws Exception {
        return this.employeeDao.getEmpByRatesName(rateName);
    }

    public ProjectAssignmentsTO updateProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO) throws Exception {
        return this.projectDao.updateProjectAssignament(projectAssignmentsTO);
    }

    public Long obtenerTraductorByEmpId(String empId) throws Exception {
        return this.translatorDao.obtenerTraductorByEmpId(empId);
    }

    public List obtenerEspecializacionesByTraId(Long traId) throws Exception {
        return this.translatorDao.obtenerEspecializacionesByTraId(traId);
    }

    public ProjectAssignmentsTO insertProjectAssignament(ProjectAssignmentsTO projectAssignmentsTO) throws Exception {
        return this.projectDao.insertProjectAssignament(projectAssignmentsTO);
    }

    public void insertProjectAssignamentDetails(ProAssigmentsDetailsTO proAssigmentsDetailsTO) throws Exception {

        List<EmployeesRatesTO> employeesRatesTOList = employeeDao.getEmpRatesByEmpIdRate(proAssigmentsDetailsTO.getProjectAssignmentsTO().getEmployeesTO(),proAssigmentsDetailsTO.getProjectAssignmentsTO().getServiceTO());
        this.projectDao.insertProjectAssignamentDetails(proAssigmentsDetailsTO, 
                                                        employeesRatesTOList);
    }


    public List<EmployeesRatesTO> getEmpRatesByEmpId(Long empId) throws Exception {
        EmployeesTO employeesTO = new EmployeesTO();
        employeesTO.setEmpId(empId);
        return employeeDao.getEmpRatesByEmpId(employeesTO);
    }
    
    public List<EmployeesRatesTO> getEmpRatesByEmpIdRateName(Long empId, String rateName ) throws Exception {
        EmployeesTO employeesTO = new EmployeesTO();
        employeesTO.setEmpId(empId);
        RateTypesTO rt =  new RateTypesTO(); 
        rt.setRtyName(rateName);
        return employeeDao.getEmpRatesByEmpIdRate(employeesTO,rt);
    }

    public List<ProAssigmentsDetailsTO> getProjectAssignmentsDetailsById(Long praId) throws Exception {
        return this.projectDao.getProjectAssignmentsDetailsById(praId);
    }

    public ProjectAssignmentsTO getProjectAssignmentsById(Long praId) throws Exception {
        return this.projectDao.getProjectAssignmentsById(praId);
    }

    public void deleteProjectAssignamentDetails(ProAssigmentsDetailsTO detail) throws Exception {

        this.projectDao.deleteProjectAssignamentDetails(detail);
    }

    public Long buscarAssignacion(String praDate, Long emp, String serv, 
                                  String proId) throws Exception {
        return this.projectDao.findProjectAssignament(praDate, emp, serv, 
                                                      proId);
    }

    public void deleteProjectAssigmentDetailsByPraId(Map params) throws Exception {
        
        this.projectDao.deleteProjectAssigmentDetailsByPraId(params);

    }

    public ItemsExpensesTO getItemsExpenseByExpId(ExpensesTO expensesTO) throws Exception {
        return this.expensesDao.getItemsExpenseByExpId(expensesTO);
    }

    public void deleteProjectAssigment(Map params) throws Exception {

        this.projectDao.deleteProjectAssigment(params);
    }

    public Long buscarAssignacion(String praDate, Long emp) throws Exception {
        return this.projectDao.findProjectAssignament(praDate, emp);
    }

    public void updateProjectAssigmentFromDetails(ProjectAssignmentsTO projectAssignmentsTO) throws Exception {
        this.projectDao.updateProjectAssigmentFromDetails(projectAssignmentsTO);
    }

    public void updateProjectAssigmentDetailsByPadId(ProAssigmentsDetailsTO proAssigmentsDetailsTO) throws Exception {
        this.projectDao.updateProjectAssigmentDetailsByPadId(proAssigmentsDetailsTO);
    }


    public boolean enviarMailAsignacion(Long praId, String message, UsersTO user, String otrosDestinatarios) throws Exception {

        //MessageFormat msf = new MessageFormat("");
        ProjectAssignmentsTO projectAssignmentsTO = 
            this.getProjectAssignmentsById(praId);
        ProjectsTO project = 
            this.getProjectById(projectAssignmentsTO.getProjectsTO().getProId());
        List<ProAssigmentsDetailsTO> proAssigmentsDetailsTOList = 
            this.projectDao.getProjectAssignmentsDetailsById(projectAssignmentsTO.getPraId());
        EmployeesTO employee = 
            this.getEmpById(projectAssignmentsTO.getEmployeesTO().getEmpId().toString());
        List<OrdersDocsTO> ordDocList = new ArrayList<OrdersDocsTO>();
        List<EmployeesTO> empList = new ArrayList<EmployeesTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String mensajeSalida = "";
        String texto = "" ;
        String textoAdicional = "";
        String subject = 
            "next assignment: " + project.getProName() +". Deadline: " + sdf.format(projectAssignmentsTO.getPraFinishDate());
        texto += 
                //"\nFecha de asignación: #fechaAsignacion#\n" +
                "<P>Hola "+projectAssignmentsTO.getEmployeesTO().getEmpFirstName()+" "+projectAssignmentsTO.getEmployeesTO().getEmpLastName()
                +",</P><BR><TABLE><TR><TD><B>Servicio: </B>"+projectAssignmentsTO.getServiceTO().getRtyAlternativeName()+"</TD></TR>";


        //texto = texto.replaceAll("#fechaAsignacion#", sdf.format(projectAssignmentsTO.getPraAssignDate()));
        //texto = texto.replaceAll("#servicio#", projectAssignmentsTO.getServiceTO().getRtyAlternativeName());
        //Map enviados = new HashMap(); 
        String prevFileName="";
        DecimalFormat df = new DecimalFormat("#");
        for (ProAssigmentsDetailsTO proAssigmentsDetailsTO: proAssigmentsDetailsTOList) {
            if (!proAssigmentsDetailsTO.getOrdersDocsTO().getDocType().getDotId().contains("Source") && !proAssigmentsDetailsTO.getOrdersDocsTO().getDocType().getDotId().contains("FTP") ){
                textoAdicional += "<TR><TD></TD></TR><TR><TD><B>Additional File Name:</B> "+ proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName()+"</TD></TR>";
                OrdersDocsTO odo = this.getOrdersDocById(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoId());
                ordDocList.add(odo);
            }else{
                 if (prevFileName != null && proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString().equalsIgnoreCase(prevFileName))
                    texto += df.format(Double.parseDouble((proAssigmentsDetailsTO.getPadWCount()!=null)?proAssigmentsDetailsTO.getPadWCount().toString():"0"))+"/"+proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getRatName()+" ";
                 else {
                    
                        texto += "<TR><TD><B>File Name:</B> " + proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName()+"</TD></TR><TR><TD><B>Word count: </B>";
                        texto += df.format(Double.parseDouble((proAssigmentsDetailsTO.getPadWCount()!=null)?proAssigmentsDetailsTO.getPadWCount().toString():"0"))+"/"+proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getRatName()+" ";
                        
                        prevFileName= proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName();    
                         if (!projectAssignmentsTO.getServiceTO().getRtyName().equalsIgnoreCase("Maquetador") ) {
                             
                            // if(enviados.get(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString())!=null){ 
                             
                             subject += 
                                     " ["+proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLanguaguesTO().getLanShortName() + "-"+
                                        proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLaaAcronym() +
                                     "] > [" + proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLanguaguesTO().getLanShortName() + "-"+ 
                                                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLaaAcronym() +
                                     "] ";
                            /* }else{
                                 enviados.put(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString(),"");
                             }*/
                         }
                     
                    
                    OrdersDocsTO odo = this.getOrdersDocById(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoId());
                    ordDocList.add(odo);
                    }
            }
        }
        if (textoAdicional.length() > 0)
            texto += "</TD></TR>"+textoAdicional+"</TABLE>";
        else
            texto += "</TD></TR></TABLE>";
            
        if (message != null && message.length() > 0)
            texto+= "<BR><TABLE><TR><TD align=left><B>Additional information: </B></TD></TR><TR><TD align=left>"+message.replaceAll("\n","<BR>")+"</TD></TR></TABLE>";
            
        texto+= "<BR><P>Please confirm receipt and acceptance of this job.</P>"+
                "<BR><P>Thanks!</P>+" +
                "<BR><BR><P><B>IMPORTANTE</B> para los trabajos en <B>WORDFAST</B>: Los segmentos en verde (100% matches) que ya vienen del cliente <B>DEBEN</B> quedar en verde. " +
                "Los segmentos verdes propios deben quedar confirmados en azul, por lo que hay que hacerles una pequeña modificación y luego deshacerla para que el segmento quede primero en rosa y luego en azul. " +
                "Los autopropagados deben quedar en naranja.</P>";
        String firma = "<BR><TABLE style='color:#808080;'><TR><TD><B>"+((user.getUsrFirstName() != null )? user.getUsrFirstName():"") +" "+ ((user.getUsrLastName() != null )?user.getUsrLastName():"")+ "</B></TD></TR>" +
        ((user.getRolesTO() != null )? "<TR><TD>"+user.getRolesTO().getRolName() +"</TD></TR>":"")+
        "<TR><TD>Two Ways Translation Services</TD></TR>" +
        ((user.getUsrMail() != null ) ?"<TR><TD>E-mail: "+user.getUsrMail() +"</TD></TR>":"")+
        ((user.getUsrOfficeNumber() != null ) ?"<TR><TD>Tel: "+user.getUsrOfficeNumber() +"</TD></TR>":"")+        
        ((user.getUsrMobileNumber() != null ) ?"<TR><TD>Mobile: "+user.getUsrMobileNumber() +"</TD></TR>":"")+
        "<TR><TD><A HREF='http://www.twoways.net/'>www.twoways.net</A></TD></TR></TABLE>"+
        "<BR><P STYLE='font-family:verdana;font-size:12px;color:green;font-weight:bold;'><I>Our business hours are Monday through Friday, from 08:00 AM to 09:00 PM local time (-03:00 GMT) / 7:00 AM to 8:00 PM EST</I></P>"; 
        texto+=firma;    
        //Si es traductor busco si tiene un editor para enviar copia del mail
        if (projectAssignmentsTO.getServiceTO().getRtyName().equalsIgnoreCase("Traductor") ) {
            for(OrdersDocsTO ordDoc : ordDocList){
                empList = this.getEditorByDocId(projectAssignmentsTO.getPraId(),ordDoc.getOdoId());
            }
        }
        if (empList.size() > 0){
            for(EmployeesTO empTO : empList){
                if (otrosDestinatarios != null && !otrosDestinatarios.equalsIgnoreCase(""))
                    otrosDestinatarios += ","+((empTO.getEmpMail() != null )?empTO.getEmpMail():"");
                else otrosDestinatarios = ((empTO.getEmpMail() != null )?empTO.getEmpMail():"");
            }
        }
        ServiceMail sm = new ServiceMail();

        String mailEmp = ((employee.getEmpMail() != null ) ?employee.getEmpMail():"Vacio");
        
        if (mailEmp.equalsIgnoreCase("Vacio"))throw new NullPointerException();
        else sm.sendAttach(mailEmp, ordDocList, subject,otrosDestinatarios,texto.replaceAll("null", " "));
        
        return true;
    }

    public ExpenseDAO getExpenseDao() {
        return expensesDao;
    }

    public void setExpensesDao(ExpenseDAO expensesDao) {
        this.expensesDao = expensesDao;
    }

    public Long insertarGasto(ExpensesTO expensesTO) throws Exception {
        return this.expensesDao.insertarExpense(expensesTO);
    }

    public Long updateGasto(ExpensesTO expensesTO) throws Exception {
        return this.expensesDao.actualizarExpense(expensesTO);
    }

    public void insertarExpenseExtra(ExpensesTO expensesTO) throws Exception{
        this.expensesDao.insertarExpenseExtra(expensesTO);
    }
    
    public boolean deleteGasto(Long expId) throws Exception {
        return this.expensesDao.deleteExpense(expId);
    }

    public ExpensesTO getExpenseById(Long expId) throws Exception {
        return this.expensesDao.getExpenseById(expId);
    }


    public Map getCostPA(ProjectAssignmentsTO projectAssignmentsTO)  throws Exception {
        Double costo= 0.0;
        Long curId =  projectAssignmentsTO.getProjectsTO().getCurrencyTO().getCurId();
        Timestamp date = projectAssignmentsTO.getPraAssignDate();
        Double cotiA = this.getCurrencyDao().getCurrencyValue(date,curId);
        Map<Long,Double> cotiz  = new  HashMap <Long,Double>();
        cotiz.put(curId,cotiA); 
        
        for(ProAssigmentsDetailsTO pa :projectAssignmentsTO.getProAssigmentsDetailsTO() ){
           
            Double cotiB = 0.0; 
            if(pa.getEmployeesRatesTO().getRatesTO().getCurrencyTO()!=null){ 
                if(cotiz.get(pa.getEmployeesRatesTO().getRatesTO().getCurrencyTO().getCurId())!=null){
                  cotiB= cotiz.get(pa.getEmployeesRatesTO().getRatesTO().getCurrencyTO().getCurId());
                }else{
                  cotiB = this.getCurrencyDao().getCurrencyValue(date,pa.getEmployeesRatesTO().getRatesTO().getCurrencyTO().getCurId() );    
                  cotiz.put(pa.getEmployeesRatesTO().getRatesTO().getCurrencyTO().getCurId(),cotiB);
                } 
                
               Double costoUnitOriginal = ((pa.getPadRate()!=null)?pa.getPadRate():0.0) * ((pa.getPadWCount()!=null)?pa.getPadWCount():0.0) ;
               if(!costoUnitOriginal.isNaN() && costoUnitOriginal != 0.0) {
                    costo+= convert(cotiA,cotiB,costoUnitOriginal);
               }else {
                    costo += 0.0;
               }
            }
        }
        
        Map result = new HashMap(); 
        result.put("costo",costo); 
        result.put("cotizaciones",cotiz);  
        return result ;
    }

    public Double convert( Double cotizPeso, Double cotizOtraMoneda, Double valores){
        
        return (cotizOtraMoneda * valores) /cotizPeso; 
    }
    
    public List getItemsExpenseByDate(String mesId, String anioId) throws Exception{
        return this.expensesDao.getItemsExpenseByDate(mesId, anioId);
    }

    public List getItemsExpenseList(Long expId) throws Exception{
        return this.expensesDao.getItemsExpenseList(expId);
    }
    
    public List<EmployeesRatesTO> getEmpRatesByEmpId(Long empId, String serv) throws Exception {
        return this.getEmpRatesByEmpIdRateName(empId,serv);
    }
    
    public List buscarListaEmpleados(String empName) throws Exception{
        return this.employeeDao.buscarListaEmpleados(empName);
    }

    public List <EmployeesTO> findEmployees(Map employParameters) throws Exception {
        return this.employeeDao.findEmployees(employParameters);
    }
    
    public List<DocTypes> obtenerTipoDocumentos() throws Exception{
         return this.docTypesDao.obtenerTipoDocumentos(); 
    }

    public void setDocTypesDao(DocTypesDAO docTypesDao) {
        this.docTypesDao = docTypesDao;
    }

    public DocTypesDAO getDocTypesDao() {
        return docTypesDao;
    }
    public List getProjectAssignmentsByEmpId(Long empId, String mesId,String anioId) throws Exception {
        return this.projectDao.getProjectAssignmentsByEmpId(empId,mesId,anioId);
    }

    public Long insertarPago(PaymentsTO paymentsTO) throws Exception {
        return this.paymentDao.insertarPago(paymentsTO);
    }

    public ExpenseDAO getExpensesDao() {
        return expensesDao;
    }

    public void setPaymentDao(PaymentDAO paymentDao) {
        this.paymentDao = paymentDao;
    }

    public PaymentDAO getPaymentDao() {
        return paymentDao;
    }
    
    public PaymentsTO getPaymentById(Long payId) throws Exception {
        return this.paymentDao.getPaymentById(payId);
    }
    
    public Double getCurrencyCotizationValue(Timestamp date, Long curIdDesde, Long curIdHasta, Double value)throws Exception {
        
        if (curIdHasta == 4L){
        Double cotDesde = this.getCurrencyDao().getCurrencyValue(date,curIdDesde);
        return value * cotDesde;
    }
    else if (curIdDesde == 4L){
        Double cotHasta = this.getCurrencyDao().getCurrencyValue(date,curIdHasta);
        return value / cotHasta;
    }
    else if (curIdDesde == curIdHasta){
        return value;
    }
    else{
        Double cotHasta = this.getCurrencyDao().getCurrencyValue(date,curIdHasta);
        Double aux = value / cotHasta;

        Double cotDesde = this.getCurrencyDao().getCurrencyValue(date,curIdDesde);
        Double aux1 = aux * cotDesde;        
        
        return aux1;                                   
    }
}
    public List buscarCotizaciones(String search) throws Exception {
        return this.cotizationsDao.buscarCotizaciones(search);
    }
    
    public CotizationsTO insertarCotizacion(CotizationsTO cotizationsTO) throws Exception {
        return this.cotizationsDao.insertarCotizacion(cotizationsTO);
    }
    
    public boolean eliminarCotizacion(CurrencyCotizationsTO currencyCotizationsTO) throws Exception {
        return this.cotizationsDao.eliminarCotizacion(currencyCotizationsTO);
    }

    public void setCotizationsDao(CotizationDAO cotizationsDao) {
        this.cotizationsDao = cotizationsDao;
    }

    public CotizationDAO getCotizationsDao() {
        return cotizationsDao;
    }
    
    public List getClientResponsableByCliId(ClientsTO clientsTO){
        return this.clientDao.getClientResponsableByCliId(clientsTO);
    }
    
    public List getOrdersByCliId(Long search,String mesId,String anioId)throws Exception{
        return this.ordersDao.getOrdersByCliId(search,mesId,anioId);
    }

    public void setInvoiceDao(InvoiceDAO invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public InvoiceDAO getInvoiceDao() {
        return invoiceDao;
    }
    
    public Long insertarFactura(InvoicesTO factura) throws Exception{
        return this.invoiceDao.insertarFactura(factura);
    }

    public void setStatesDao(StatesDAO statesDao) {
        this.statesDao = statesDao;
    }

    public StatesDAO getStatesDao() {
        return statesDao;
    }

    public List<StatesTO> getStatesListByType(String type) {
        return statesDao.getStatesByType(type);
    }
    
    public List <InvoicesTO> findInvoices(Map invoiceParameters) throws Exception {
        return this.invoiceDao.findInvoices(invoiceParameters);
    }
   
    public List getOrdersByCliIdInvId(Long cliId,Long invoiceId)throws Exception{
        return this.ordersDao.getOrdersByCliIdInvId(cliId,invoiceId);
    }
    
    public void actualizarFactura(InvoicesTO factura) throws Exception{
        this.invoiceDao.actualizarFactura(factura);
    }
    
    public List obtenerItemsFactura(Long invId) throws Exception{
        return this.invoiceDao.obtenerItemsFactura(invId);
    }
    
    public List obtenerResponsables() throws Exception{
        return this.clientDao.obtenerResponsables();
    }
    
    public List <ProjectsTO> findProjects(Map projParameters){
        return this.projectDao.findProjects(projParameters);
    }

    public List findProjectsyPalabras(Map projParameters){
        return this.projectDao.findProjectsyPalabras(projParameters);
    }
    
    public List <InvoicesTO> findIncomesByClient(Map invoiceParameters) throws Exception{
        return this.invoiceDao.findIncomesByClient(invoiceParameters);
    }

   /* public List findOpeningBalance(Map invoiceParameters) throws Exception{
        return this.expensesDao.findOpeningBalance(invoiceParameters);
    }  */  
    
    public List findIncomes(Map invoiceParameters, String itemType, Map itemsParameters) throws Exception{
        return this.expensesDao.findIncomes(invoiceParameters, itemType, itemsParameters);
    }    
    
    public List <ItemsExpensesTO>findExpenses(Map expensesParameters, Map itemsParameters) throws Exception{
        return this.expensesDao.findExpenses(expensesParameters,itemsParameters);
    }
    
    public List findFutureExpenses(Map expensesParameters) throws Exception{
        return this.expensesDao.findFutureExpenses(expensesParameters);
    }
    
    public List<EmployeesTO> getEditorByDocId(Long praId,Long docId) throws Exception{
        return this.employeeDao.getEditorByDocId(praId,docId);
    }
    
    public void eliminarOrden(OrdersTO ordersTO) throws Exception{   
        this.ordersDao.deleteOrderByOrdId(ordersTO.getOrdId());       
    }
    
    public void eliminarProyecto(Long ordId) throws Exception{
        this.projectDao.deleteProjectByOrdId(ordId);
    }
    
    public boolean enviarMailDesasignacion(Long praId, UsersTO user) throws Exception {

        ProjectAssignmentsTO projectAssignmentsTO = 
            this.getProjectAssignmentsById(praId);
        ProjectsTO project = 
            this.getProjectById(projectAssignmentsTO.getProjectsTO().getProId());
        List<ProAssigmentsDetailsTO> proAssigmentsDetailsTOList = 
            this.projectDao.getProjectAssignmentsDetailsById(projectAssignmentsTO.getPraId());
        EmployeesTO employee = 
            this.getEmpById(projectAssignmentsTO.getEmployeesTO().getEmpId().toString());
        List<OrdersDocsTO> ordDocList = new ArrayList<OrdersDocsTO>();
        List<EmployeesTO> empList = new ArrayList<EmployeesTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String otrosDestinatarios = "";
        String texto = "" ;
        String textoAdicional = "";
        String subject = 
            "assignment: " + project.getProName() +" canceled.";
        texto += 
                //"\nFecha de asignación: #fechaAsignacion#\n" +
                "<P>Hola "+projectAssignmentsTO.getEmployeesTO().getEmpFirstName()+" "+projectAssignmentsTO.getEmployeesTO().getEmpLastName()
                +", la asignación ha sido <B>CANCELADA</B> para el siguiente servicio: </P><BR><TABLE><TR><TD><B>Servicio: </B>"+projectAssignmentsTO.getServiceTO().getRtyAlternativeName()+"</TD></TR>";


        //texto = texto.replaceAll("#fechaAsignacion#", sdf.format(projectAssignmentsTO.getPraAssignDate()));
        //texto = texto.replaceAll("#servicio#", projectAssignmentsTO.getServiceTO().getRtyAlternativeName());
        //Map enviados = new HashMap(); 
        String prevFileName="";
        DecimalFormat df = new DecimalFormat("#");
        for (ProAssigmentsDetailsTO proAssigmentsDetailsTO: proAssigmentsDetailsTOList) {
            if (!proAssigmentsDetailsTO.getOrdersDocsTO().getDocType().getDotId().contains("Source") && !proAssigmentsDetailsTO.getOrdersDocsTO().getDocType().getDotId().contains("FTP") ){
                textoAdicional += "<TR><TD></TD></TR><TR><TD><B>Additional File Name:</B> "+ proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName()+"</TD></TR>";
                OrdersDocsTO odo = this.getOrdersDocById(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoId());
                ordDocList.add(odo);
            }else{
                 if (prevFileName != null && proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString().equalsIgnoreCase(prevFileName))
                    texto += df.format(Double.parseDouble((proAssigmentsDetailsTO.getPadWCount()!=null)?proAssigmentsDetailsTO.getPadWCount().toString():"0"))+"/"+proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getRatName()+" ";
                 else {
                    
                        texto += "<TR><TD><B>File Name:</B> " + proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName()+"</TD></TR><TR><TD><B>Word count: </B>";
                        texto += df.format(Double.parseDouble((proAssigmentsDetailsTO.getPadWCount()!=null)?proAssigmentsDetailsTO.getPadWCount().toString():"0"))+"/"+proAssigmentsDetailsTO.getEmployeesRatesTO().getRatesTO().getRatName()+" ";
                        
                        prevFileName= proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName();    
                         if (!projectAssignmentsTO.getServiceTO().getRtyName().equalsIgnoreCase("Maquetador") ) {
                             
                            // if(enviados.get(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString())!=null){ 
                             
                             subject += 
                                     " ["+proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLanguaguesTO().getLanShortName() + "-"+
                                        proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO().getLaaAcronym() +
                                     "] > [" + proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLanguaguesTO().getLanShortName() + "-"+ 
                                                proAssigmentsDetailsTO.getPranslatorsLanguaguesTO().getLangAcronymsTO1().getLaaAcronym() +
                                     "] ";
                            /* }else{
                                 enviados.put(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoName().toString(),"");
                             }*/
                         }
                     
                    
                   /* OrdersDocsTO odo = this.getOrdersDocById(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoId());
                    ordDocList.add(odo);*/
                    }
            }
        }
        if (textoAdicional.length() > 0)
            texto += "</TD></TR>"+textoAdicional+"</TABLE>";
        else
            texto += "</TD></TR></TABLE>";
                        

        String firma = "<BR><TABLE style='color:#808080;'><TR><TD><B>"+((user.getUsrFirstName() != null )? user.getUsrFirstName():"") +" "+ ((user.getUsrLastName() != null )?user.getUsrLastName():"")+ "</B></TD></TR>" +
        ((user.getRolesTO() != null )? "<TR><TD>"+user.getRolesTO().getRolName() +"</TD></TR>":"")+
        "<TR><TD>Two Ways Translation Services</TD></TR>" +
        ((user.getUsrMail() != null ) ?"<TR><TD>E-mail: "+user.getUsrMail() +"</TD></TR>":"")+
        ((user.getUsrOfficeNumber() != null ) ?"<TR><TD>Tel: "+user.getUsrOfficeNumber() +"</TD></TR>":"")+        
        ((user.getUsrMobileNumber() != null ) ?"<TR><TD>Mobile: "+user.getUsrMobileNumber() +"</TD></TR>":"")+
        "<TR><TD><A HREF='http://www.twoways.net/'>www.twoways.net</A></TD></TR></TABLE>"+
        "<BR><P STYLE='font-family:verdana;font-size:12px;color:green;font-weight:bold;'><I>Our business hours are Monday through Friday, from 08:00 AM to 09:00 PM local time (-03:00 GMT) / 7:00 AM to 8:00 PM EST</I></P>"; 
        texto+=firma;    
        //Si es traductor busco si tiene un editor para enviar copia del mail
        if (projectAssignmentsTO.getServiceTO().getRtyName().equalsIgnoreCase("Traductor") ) {
            for(OrdersDocsTO ordDoc : ordDocList){
                empList = this.getEditorByDocId(projectAssignmentsTO.getPraId(),ordDoc.getOdoId());
            }
        }
        if (empList.size() > 0){
            for(EmployeesTO empTO : empList){
                if (otrosDestinatarios != null && !otrosDestinatarios.equalsIgnoreCase(""))
                    otrosDestinatarios += ","+((empTO.getEmpMail() != null )?empTO.getEmpMail():"");
                else otrosDestinatarios = ((empTO.getEmpMail() != null )?empTO.getEmpMail():"");
            }
        }
        ServiceMail sm = new ServiceMail();

        String mailEmp = ((employee.getEmpMail() != null ) ?employee.getEmpMail():"Vacio");
        
        if (mailEmp.equalsIgnoreCase("Vacio"))throw new NullPointerException();
        else sm.sendAttach(mailEmp, ordDocList, subject,otrosDestinatarios,texto.replaceAll("null", " "));
        
        return true;
    }

    public void anularFactura(Long invId) throws Exception{

        this.expensesDao.eraseInvoiceExpense(invId);        
        this.invoiceDao.eraseInvoice(invId);

    }

    public List <PaymentsTO> findPayments(Map paymentParameters) throws Exception{
        return this.paymentDao.findPayments(paymentParameters);        
    }

    public List obtenerItemsPago(Long payId) throws Exception{
        return this.paymentDao.obtenerItemsPago(payId);
    }
    
    public void anularPago(Long payId) throws Exception{

        this.expensesDao.erasePaymentExpense(payId);
        this.paymentDao.erasePayment(payId);        
    }
    
    public List findFutureIncomesByClient(Map params) throws Exception{
        return this.paymentDao.findFutureIncomesByClient(params);
    }
    
    public List findFutureIncomes(Map params) throws Exception{
        return this.ordersDao.findFutureIncomes(params);
    }
    
    public List findFuturePayments(Map params) throws Exception{
        return this.paymentDao.findFuturePayments(params);
    }

    public Long getTotalPalabrasxProyecto(Long proId) throws Exception{
        return this.projectDao.getTotalPalabrasxProyecto(proId);
    }

    public List obtenerPalabrasxMes(List anios) throws Exception{
        return this.projectDao.obtenerPalabrasxMes(anios);
    }

    public List obtenerPalabrasxCliente(String anio) throws Exception{
        return this.projectDao.obtenerPalabrasxCliente(anio);
    }
}


