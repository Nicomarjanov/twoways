package com.twoways.dao;

import com.twoways.to.UsersTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

public class UserDAOImpl extends AbstractDAO  implements UserDAO{

    public List obtenerUsuarios() throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerUsuarios","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }

    public UsersTO getUserById(String usrId)  throws Exception{
       UsersTO usuario =  (UsersTO)getSqlMapClientTemplate().queryForObject("getUserById",usrId);
       return usuario;
    }

    public void insertarUsuario(UsersTO usersTO)  throws Exception {
            getSqlMapClientTemplate().insert("insertUser",usersTO);
    }

    public void updateUsuario(UsersTO usersTO) {
        getSqlMapClientTemplate().insert("updateUser",usersTO);
    }

    public List buscarUsuarios(String search) throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarUsuarios",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }

    public boolean deleteUser(UsersTO user)  throws Exception{
       int res =  getSqlMapClientTemplate().delete("deleteUsers",user);
       return (res > 0); 
    }
}
