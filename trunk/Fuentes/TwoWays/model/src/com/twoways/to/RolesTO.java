package com.twoways.to;

import java.util.List;


//(name = "ROLES")
public class RolesTO {
    //(name="ROL_DESCRIPTION")
    private String rolDescription;
    //
    //(name="ROL_ID", nullable = false)
    private Long rolId;
    //(name="ROL_NAME", nullable = false)
    private String rolName;
     private List<UsersTO> usersTOList;

    public RolesTO() {
    }

    public String getRolDescription() {
        return rolDescription;
    }

    public void setRolDescription(String rolDescription) {
        this.rolDescription = rolDescription;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public List<UsersTO> getUsersTOList() {
        return usersTOList;
    }

    public void setUsersTOList(List<UsersTO> usersTOList) {
        this.usersTOList = usersTOList;
    }

    public UsersTO addUsersTO(UsersTO usersTO) {
        getUsersTOList().add(usersTO);
        usersTO.setRolesTO(this);
        return usersTO;
    }

    public UsersTO removeUsersTO(UsersTO usersTO) {
        getUsersTOList().remove(usersTO);
        usersTO.setRolesTO(null);
        return usersTO;
    }
}
