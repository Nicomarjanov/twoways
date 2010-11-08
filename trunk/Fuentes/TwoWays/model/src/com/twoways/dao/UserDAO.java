package com.twoways.dao;

import com.twoways.to.UsersTO;

import java.util.List;

public interface UserDAO {
    public List obtenerUsuarios() throws Exception;
    public UsersTO getUserById(String usrId)  throws Exception;
    public void insertarUsuario(UsersTO usersTO) throws Exception;
    public void updateUsuario(UsersTO usersTO) throws Exception;
    public List buscarUsuarios(String search) throws Exception;
    public List buscarUsuariosId(String search) throws Exception;    
    public boolean  deleteUser(UsersTO user)  throws Exception;
    public String getPass(String userId)throws Exception;
    
}
