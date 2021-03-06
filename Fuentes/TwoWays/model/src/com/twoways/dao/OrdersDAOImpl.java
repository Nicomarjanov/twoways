package com.twoways.dao;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.Reminder;
import com.google.gdata.util.ServiceException;

import com.twoways.service.CalendarGoogleService;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;

import com.twoways.to.DocTypes;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;

import com.twoways.to.RateTypesTO;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;

import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import org.springframework.dao.DataAccessException;

public class OrdersDAOImpl extends AbstractDAO implements OrdersDAO {
    public OrdersDAOImpl() {
    }
    
   
    private void insertFiles(OrdersTO order) throws IOException {

        List<FileItem> files = order.getFiles();

        try {

            for (Object fileObj: files.toArray()) {
                Blob doc = null;
                FileItem file = (FileItem)fileObj;
                //statement= con.createStatement(); 
                OrdersDocsTO ordersDocsTO = new OrdersDocsTO();

                Long odoId = 
                    (Long)getSqlMapClientTemplate().queryForObject("order.seq", 
                                                                   "");
                ordersDocsTO.setOrdersOrdId(order.getOrdId());
                ordersDocsTO.setOdoId(odoId);
                
                DocTypes auxDocType= new DocTypes();
                
                if( order.getDocTypesSelected().get(file.getName()) != null){ 
                    auxDocType.setDotId(order.getDocTypesSelected().get(file.getName()).toString());
                }else{
                    String id =   file.getFieldName().substring("file".length());
                    auxDocType.setDotId(order.getDocTypesSelected().get(id).toString());
                }
                ordersDocsTO.setDocType(auxDocType);
                if(ordersDocsTO.getDocType().getDotId().startsWith("FTP")){ 
                  ordersDocsTO.setOdoName(file.getName() + ".txt");
                }else{
                 ordersDocsTO.setOdoName(file.getName().substring(file.getName().lastIndexOf("\\") + 
                                                                   1));
                }

                try {
                    byte[] b = new byte[(int)file.getSize()];

                    file.getInputStream().read(b);
                    //out.write(b);
                    ordersDocsTO.setOdoDoc(b);

                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found.");
                    e.printStackTrace();
                }

                getSqlMapClientTemplate().insert("insertOrderDoc", 
                                                 ordersDocsTO);

            }

        } catch (IOException e1) {
            System.out.println("Error Upload The File.");
            e1.printStackTrace();
        }

        catch (Exception e) {

            System.out.println("Error The File.");
            e.printStackTrace();

        }


    }

    public OrdersTO getOrderById(Long ordId) throws Exception {
        OrdersTO ordersTO = (OrdersTO)getSqlMapClientTemplate().queryForObject("getOrderById", ordId);
        ordersTO.setOrderRatesTOList((List<OrdersRatesTO>)getSqlMapClientTemplate().queryForList("getOrderRatesByOrderId",ordersTO) );
        ordersTO.setServicesTOList((List<RateTypesTO>)getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO) );
        ordersTO.setOrdersDocsTOList((List<OrdersDocsTO>)getSqlMapClientTemplate().queryForList("getOrderDocByOrder", ordId));

        return ordersTO;
    }


    public OrdersDocsTO getOrdersDocById(Long docId) throws Exception {
        OrdersDocsTO ordersDocsTO = 
            (OrdersDocsTO)getSqlMapClientTemplate().queryForObject("getOrderDocById", 
                                                                   docId);

        return ordersDocsTO;
    }


    public OrdersTO updateOrder(OrdersTO ordersTO) throws Exception {

        


        getSqlMapClientTemplate().insert("updateOrders", ordersTO);
        List <OrdersRatesTO> ordRates = ordersTO.getOrderRatesTOList();
        
        // insertar las nuevas 
        
        List<OrdersRatesTO> oldOrdRates = (List<OrdersRatesTO>) getSqlMapClientTemplate().queryForList("getOrderRatesByOrderId",ordersTO); 
         
        if(ordRates ==null){ 
            ordRates= new ArrayList();
        }
        for(OrdersRatesTO ordersRatesTO: ordRates ){
             boolean insertar= true;   
            
            for(OrdersRatesTO oldOrdRate: oldOrdRates ){
               
                 
                if( oldOrdRate.getOrdersTO().getOrdId().equals(ordersRatesTO.getOrdersTO().getOrdId()) && oldOrdRate.getRatesTO().getRatId().equals(ordersRatesTO.getRatesTO().getRatId())  ){
                    insertar=false;
                    break;
                }
                
            }     
             
            if(insertar)
            {
                ordersRatesTO.setOrdersTO(ordersTO);
                getSqlMapClientTemplate().insert("insertOrdersRates",ordersRatesTO);
            }else{
                getSqlMapClientTemplate().insert("updateOrdersRates",ordersRatesTO);
            }  
        }
        
        //borrar las viejas
        for(OrdersRatesTO oldOrdRate: oldOrdRates ){
               boolean borrar= true;   
               for(OrdersRatesTO ordersRatesTO: ordRates ){
               
                   
                   if( oldOrdRate.getOrdersTO().getOrdId().equals(ordersRatesTO.getOrdersTO().getOrdId()) && oldOrdRate.getRatesTO().getRatId().equals(ordersRatesTO.getRatesTO().getRatId())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   getSqlMapClientTemplate().delete("deleteOrdersRates",oldOrdRate);
               }  
           }
                        
        List <RateTypesTO> services = ordersTO.getServicesTOList();
        
        // insertar las nuevas 
        
        List<RateTypesTO> oldOrdServices = (List<RateTypesTO>) getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO); 
         
        
        for(RateTypesTO servicesTO: services ){
             boolean insertar= true;   
            
            for(RateTypesTO oldServices: oldOrdServices ){
                if( oldServices.getRtyName().equals(servicesTO.getRtyName())){
                    insertar=false;
                    break;
                }
                
            }     
             
            if(insertar)
            {
                Map insert= new HashMap();
                insert.put("ordId",ordersTO.getOrdId());
                insert.put("serId",servicesTO.getRtyName());
                getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            } 
        }
        
        //borrar las viejas
        for(RateTypesTO oldServices: oldOrdServices ){
               boolean borrar= true;   
               for(RateTypesTO servicesTO: services ){
                   if( oldServices.getRtyName().equals(servicesTO.getRtyName())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   Map insert= new HashMap();
                   insert.put("ordId",ordersTO.getOrdId());
                   insert.put("serId",oldServices.getRtyName());
                   getSqlMapClientTemplate().delete("deleteOrdersServices",insert);               
               }  
           }
                       
        List <OrdersDocsTO> docsList = ordersTO.getOrdersDocsTOList();
       
       if (docsList==null){
           docsList = new ArrayList(); 
       }
        List<OrdersDocsTO> documentosOld = (List<OrdersDocsTO>)getSqlMapClientTemplate().queryForList("getOrderDocByOrder", 
                                                                                                ordersTO.getOrdId());
        
        for(OrdersDocsTO oldDocs: documentosOld ){
               boolean borrar= true;   
               for(OrdersDocsTO ordersDocsTO: docsList ){
               
                   
                   if( oldDocs.getOdoName().equals(ordersDocsTO.getOdoName())  ){
                       borrar=false;
                       break;
                   }                   
               }     
                
               if(borrar)
               {
                   Map insert= new HashMap();
                   insert.put("ordId",ordersTO.getOrdId());
                   insert.put("odoId",oldDocs.getOdoId());
                   getSqlMapClientTemplate().delete("deleteOrdersDocs",insert);
                                     
               }  
           }
        
        insertFiles(ordersTO);

        return getOrderById(ordersTO.getOrdId());
    }
    
    
    public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception {
        
        Logger log = Logger.getRootLogger(); 
        log.debug("OrdersTO insertarOrder(OrdersTO ordersTO)");
        Long ordId = 
            (Long)getSqlMapClientTemplate().queryForObject("ordersdoc.seq", 
                                                           "");
        ordersTO.setOrdId(ordId);
        
        getSqlMapClientTemplate().insert("insertOrders", ordersTO);
          
        if (ordersTO.getOrderRatesTOList() != null) {


            for (Object ordRate: ordersTO.getOrderRatesTOList().toArray()) {

                ((OrdersRatesTO)ordRate).setOrdersTO(ordersTO);
                getSqlMapClientTemplate().insert("insertOrdersRates", 
                                                 (OrdersRatesTO)ordRate);
            }

        }
        
        if (ordersTO.getServicesTOList() != null) {


            for (Object service: ordersTO.getServicesTOList().toArray()) {

               Map insert= new HashMap();
               insert.put("ordId",ordersTO.getOrdId());
               insert.put("serId",((RateTypesTO)service).getRtyName());
               getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            }

        }
        
        insertFiles(ordersTO);

        return getOrderById(ordersTO.getOrdId());

    }
    
    public List <OrdersTO> findOrders(Map orderParameters) {
        
       List <OrdersTO> results = new ArrayList<OrdersTO>();
       DataSource ds = this.getDataSource(); 
       Connection con = null;
       Statement stm = null;
       ResultSet rs= null ;
       String query =  " select * " + 
                       " from orders o, clients c " + 
                       " where upper(decode(o.ord_Proj_Id, null , ' ',o.ord_Proj_Id)) like  upper('%#ordProjId#%')"+
                       " and o.clients_cli_id = c.cli_Id " + 
                       " and c.cli_id = decode(#cliId#,0,c.cli_id,#cliId#) " ;
       
       if(orderParameters.get("ordenes") != null){
            query += " and upper(o.ord_name) = upper('#ordName#') ";
       }
       else {
            query += " and upper(o.ord_name) like upper('%#ordName#%') ";
       }
       if(orderParameters.get("ordStartDate") != null && orderParameters.get("ordStartDate").toString().length() > 0){
       
          String formato = "dd/MM/yyyy hh24:mi";
          if (orderParameters.get("ordStartDate").toString().length() == 10){
              if(!orderParameters.get("ordDateOpt").toString().equals("=")){ 
                  formato = "dd/MM/yyyy";
                  query += " and o.ord_start_date "+ orderParameters.get("ordDateOpt").toString()+"  to_date ('#ordStartDate#','"+formato+"')";
              }else{
                  query += " and o.ord_start_date >= to_date ('#ordStartDate# 00:00','"+formato+"') and  o.ord_start_date <= to_date ('#ordStartDate# 23:59','"+formato+"') ";
              }
          }else{        
            query += " and o.ord_start_date "+ orderParameters.get("ordDateOpt").toString()+"  to_date ('#ordStartDate#','"+formato+"')";
          }  
       }
       
        if(orderParameters.get("ordFinishDate") != null && orderParameters.get("ordFinishDate").toString().length() > 0){
        
           String formato = "dd/MM/yyyy hh24:mi";
           if (orderParameters.get("ordFinishDate").toString().length() == 10){
               if(!orderParameters.get("ordFinishDateOpt").toString().equals("=")){ 
                   formato = "dd/MM/yyyy";
                   query += " and o.ord_finish_date "+ orderParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
               }else{
                   query += " and o.ord_finish_date >= to_date ('#ordFinishDate# 00:00','"+formato+"') and  o.ord_finish_date <= to_date ('#ordFinishDate# 23:59','"+formato+"') ";
               }
           }else{        
             query += " and o.ord_finish_date "+ orderParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
           }  
        }
                   
       for (Iterator i = orderParameters.keySet().iterator();i.hasNext();){
           String param = (String)i.next();
           query = query.replaceAll("#"+param+"#",orderParameters.get(param).toString());
       }
       
       query+= " order by o.ord_start_date desc " ;
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            //System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                OrdersTO order= new OrdersTO();
                order.setOrdId(rs.getLong("ord_id"));
                order.setOrdName(rs.getString("ord_name"));
                order.setOrdProjId(rs.getString("ord_Proj_Id"));
                ClientsTO client = new ClientsTO();
                client.setCliId(rs.getLong("cli_id"));
                client.setCliName(rs.getString("cli_name"));
                order.setClientsTO(client);
                if(rs.getTime("ord_finish_date") !=null ){ 
                   
                        
                       java.sql.Timestamp timest = rs.getTimestamp("ord_finish_date"); 
                        order.setOrdFinishDate(timest);
                                            
                }
                if(rs.getTime("ord_start_date") !=null ){ 
                   
                       java.sql.Timestamp timest = rs.getTimestamp("ord_start_date"); 
                        order.setOrdStartDate(timest);
                                           
                }
                
                results.add(order);
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally{
            try {
            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }                        
        }              
        return results;
    }
    
    
    public List getOrdersByCliId(Long cliId,String mesId,String anioId)throws Exception{
        List ret = null;
        try {
            Map params = new HashMap();
            params.put("cliId",cliId);
            params.put("mesId",mesId+anioId); 
            //params.put("mesId",25+mesId+anioId);

            ret = getSqlMapClientTemplate().queryForList("getOrdersByCliId",params);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    public List getOrdersByCliIdInvId(Long cliId,Long invoiceId)throws Exception{
        List ret = null;
        try {
            Map params= new HashMap();
            params.put("cliId",cliId);
            params.put("invoiceId",invoiceId);

            ret = getSqlMapClientTemplate().queryForList("getOrdersByCliIdInvId",params);
        } catch (DataAccessException dae) {
    
           dae.printStackTrace();
        }
        return ret;
    }
    
    public void deleteOrderByOrdId(Long ordId)throws Exception{
        getSqlMapClientTemplate().delete("deleteOrdersDocByOrdId",ordId);
        getSqlMapClientTemplate().delete("deleteOrdersRatesByOrdId",ordId);
        getSqlMapClientTemplate().delete("deleteOrdersServicesByOrdId",ordId);
        getSqlMapClientTemplate().delete("deleteOrderByOrdId",ordId);
    }
    
    public List findFutureIncomes(Map params) throws Exception{
        List salida = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select t.currency_cur_id as curId,\n" + 
        "       to_date(o.ord_finish_date,'dd/mm/rrrr') as finishDate ,\n" + 
        "       sum(r.orr_value*r.orr_wcount) as ingresoTotal\n" + 
        "  from orders o, orders_rates r,rates t\n" + 
        " where o.ord_id =r.orders_ord_id\n" + 
        "  and r.rates_rat_id=t.rat_id";
        
        if (params.get("mesId") != null && params.get("mesId").toString().length() > 0){
            query +=" and to_char(o.ord_finish_date,'MMyyyy')=#mesId##anioId#";
        }      
        else if (params.get("anioId") != null && params.get("anioId").toString().length() > 0){
            query +=" and to_char(o.ord_finish_date,'yyyy')=#anioId#";
        }              
        query +=" group by t.currency_cur_id,  to_date(o.ord_finish_date,'dd/mm/rrrr')\n" + 
                " order by 2";
                
        for (Iterator i = params.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",params.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            rs = stm.executeQuery(query);
            while(rs.next()){
                List results = new ArrayList();         
                results.add(rs.getString("curId"));                         
                results.add(rs.getString("finishDate"));            
                results.add(rs.getString("ingresoTotal"));                    
                salida.add(results);
            }
            
            } catch (SQLException e) {
            e.printStackTrace();
            }finally{
            try {
            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            }
            return salida;
        
    }

    public List obtenerPalabrasxCliente(String anio) throws Exception{
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        List salida = new ArrayList();
        String query =  "select cliente,\n" + 
        "       Enero,\n" + 
        "       Febrero,\n" + 
        "       Marzo,\n" + 
        "       Abril,\n" + 
        "       Mayo,\n" + 
        "       Junio,\n" + 
        "       Julio,\n" + 
        "       Agosto,\n" + 
        "       Septiembre,\n" + 
        "       Octubre,\n" + 
        "       Noviembre,\n" + 
        "       Diciembre\n" + 
        "from (\n" + 
        "select to_date(to_CHAR(o.ord_finish_date,'MON-RRRR'),'MON-RRRR') as mm, c.cli_name as cliente, sum(r.orr_wcount) as total  \n" +
        "from orders o, clients c, orders_rates r  \n" +
        "where c.cli_id = o.clients_cli_id   \n" +
        "and r.orders_ord_id = o.ord_id \n" +
        "and to_char(o.ord_finish_date,'rrrr') = #anio# \n" + 
        "group by to_date(to_CHAR(o.ord_finish_date,'MON-RRRR'),'MON-RRRR'),c.cli_name \n" + 
        ")\n" + 
        "PIVOT (sum(total) FOR mm IN ('01/01/#anio#' Enero,'01/02/#anio#' Febrero,'01/03/#anio#' Marzo,'01/04/#anio#' Abril,'01/05/#anio#' Mayo,'01/06/#anio#' Junio,'01/07/#anio#' Julio,'01/08/#anio#' Agosto,'01/09/#anio#' Septiembre,'01/10/#anio#' Octubre,'01/11/#anio#' Noviembre,'01/12/#anio#' Diciembre)\n" + 
        ")\n" + 
        "order by cliente";

        try {
                con = ds.getConnection();
                stm = con.createStatement();
                String aux=null;
                //for (int i=0; i<anios.size();i++){                   
                    aux = query.replace("#anio#",anio);            
                    rs = stm.executeQuery(aux);
                    while(rs.next()){
                        List results = new ArrayList();
                      //  results.add(anios.get(i).toString());
                        results.add(rs.getString("cliente"));
                        results.add(rs.getLong("Enero"));
                        results.add(rs.getLong("Febrero"));          
                        results.add(rs.getLong("Marzo"));
                        results.add(rs.getLong("Abril"));                       
                        results.add(rs.getLong("Mayo"));
                        results.add(rs.getLong("Junio"));          
                        results.add(rs.getLong("Julio"));
                        results.add(rs.getLong("Agosto"));   
                        results.add(rs.getLong("Septiembre"));
                        results.add(rs.getLong("Octubre"));          
                        results.add(rs.getLong("Noviembre"));
                        results.add(rs.getLong("Diciembre"));   
                        
                        salida.add(results);
                    }
               // }
            
            } catch (SQLException e) {
            e.printStackTrace();
            }finally{
            try {
            rs.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
            return salida;
            }

    public List obtenerPalabrasxMes(List anios) throws Exception{
        List salida = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;

        String query =  "select MonthName as mes, total \n" + 
        "from (\n" + 
        "        select to_date(to_CHAR(o.ord_finish_date,'MON-RRRR'),'MON-RRRR') as mm, sum(r.orr_wcount) total  \n" + 
        "        from orders o, clients c, orders_rates r   \n" + 
        "        where o.ord_id=r.orders_ord_id  \n" + 
        "        and c.cli_id = o.clients_cli_id " + 
        "and to_char(o.ord_finish_date,'rrrr') = #anio# \n" + 
        "group by to_date(to_CHAR(o.ord_finish_date,'MON-RRRR'),'MON-RRRR') \n" + 
        ")palabras,\n" + 
        "(select add_months(to_date('01-Ene-#anio#','dd-MM-RRRR'),level -1) MonthName \n" + 
        "from dual \n" + 
        "connect by level <= 12\n" + 
        ")ALLMONTHS \n" + 
        "where mm (+) =MonthName \n" + 
        "order by mes";
        

    try {
        con = ds.getConnection();
        stm = con.createStatement();
        String aux=null;
        for (int i=0; i<anios.size();i++){   
        
            aux = query.replace("#anio#",anios.get(i).toString());            
            rs = stm.executeQuery(aux);
            while(rs.next()){
                List results = new ArrayList();
                results.add(rs.getString("mes"));
                results.add(rs.getLong("total"));
          
                salida.add(results);
            }
        }
        
    } catch (SQLException e) {
         e.printStackTrace();
    }finally{
        try {
        rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
        stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
        con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }                        
    }              
    return salida;
    }


        public List findOrdenesyPalabras(Map projParameters){
            List salida = new ArrayList();
            DataSource ds = this.getDataSource(); 
            Connection con = null;
            Statement stm = null;
            ResultSet rs= null ;
            String query =  "select o.ord_name, o.ord_finish_date, sum(r.orr_wcount) total, o.ord_start_date \n" + 
            "from orders o, clients c, orders_rates r, projects p \n" + 
            "where o.ord_id=r.orders_ord_id   \n" + 
            "and c.cli_id = o.clients_cli_id \n" +
            "and p.orders_ord_id =o.ord_id \n";

            if (projParameters.get("cliId") != null && projParameters.get("cliId").toString().length() > 0){
                query += " and c.cli_id =#cliId# \n";  
            }

            if (projParameters.get("projName") != null && projParameters.get("projName").toString().length() > 0){
                query += " and p.pro_name  like '%'||'#projName#'||'%' \n";  
            }

            if (projParameters.get("ordName") != null && projParameters.get("ordName").toString().length() > 0){
                query += " and o.ord_name  like '%'||'#ordName#'||'%' \n";  
            }
            
            if(projParameters.get("ordStartDate") != null && projParameters.get("ordStartDate").toString().length() > 0){
            
               String formato = "dd/MM/yyyy hh24:mi";
               if (projParameters.get("ordStartDate").toString().length() == 10){
                   if(!projParameters.get("ordDateOpt").toString().equals("=")){ 
                       formato = "dd/MM/yyyy";
                       query += " and o.ord_start_date "+ projParameters.get("ordDateOpt").toString()+"  to_date ('#ordStartDate#','"+formato+"')";
                   }else{
                       query += " and o.ord_start_date >= to_date ('#projDate# 00:00','"+formato+"') and o.ord_start_date <= to_date ('#ordStartDate# 23:59','"+formato+"') ";
                   }
               }else{        
                 query += " and t.pro_start_date "+ projParameters.get("ordDateOpt").toString()+"  to_date ('#ordStartDate#','"+formato+"')";
               }  
            }
            
             if(projParameters.get("ordFinishDate") != null && projParameters.get("ordFinishDate").toString().length() > 0){
             
                String formato = "dd/MM/yyyy hh24:mi";
                if (projParameters.get("ordFinishDate").toString().length() == 10){
                    if(!projParameters.get("ordFinishDateOpt").toString().equals("=")){ 
                        formato = "dd/MM/yyyy";
                        query += " and o.ord_finish_date"+ projParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
                    }else{
                        query += " and o.ord_finish_date >= to_date ('#ordFinishDate# 00:00','"+formato+"') and o.ord_finish_date <= to_date ('#ordFinishDate# 23:59','"+formato+"') ";
                    }
                }else{        
                  query += " and o.ord_finish_date "+ projParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
                }  
             }
            
            
                        
            for (Iterator i = projParameters.keySet().iterator();i.hasNext();){
                String param = (String)i.next();
                query = query.replaceAll("#"+param+"#",projParameters.get(param).toString());
            }
            
            query+= "group by ord_name, ord_finish_date,o.ord_start_date order by o.ord_start_date " ;
             try {
                 con = ds.getConnection();
                 stm = con.createStatement();
                 //System.out.println(query);
                 rs = stm.executeQuery(query);
                 
                 while(rs.next()){
                     List results = new ArrayList();
                     results.add(rs.getString("ord_name"));
                     results.add(rs.getLong("total"));
                     results.add(rs.getString("ord_finish_date"));
                
                     salida.add(results);
                 }
                 
             } catch (SQLException e) {
                  e.printStackTrace();
             }finally{
                 try {
                 rs.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
                 try{
                 stm.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
                 try{
                 con.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }                        
             }              
             return salida;        
        }


}
