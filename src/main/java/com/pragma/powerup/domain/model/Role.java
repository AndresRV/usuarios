package com.pragma.powerup.domain.model;

public class Role {
    private Long id;
    private String roleName;
    private String roleDescription;

    public Role(Long id, String roleName, String roleDescription) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
