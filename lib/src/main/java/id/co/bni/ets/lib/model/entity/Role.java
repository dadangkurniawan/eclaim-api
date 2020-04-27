package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_role", schema = "dbo")
public class Role extends AbstractActiveFlaggedEntity<Integer> {

    private String name;

    private String description;

    @OneToMany(mappedBy = "secondEntity")
    @JsonIgnore
    private Collection<UserRole> userRoles;

    @OneToMany(mappedBy = "firstEntity", cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<RolePrivilege> rolePrivileges;

    @OneToMany(mappedBy = "firstEntity", cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<RoleMenu> roleMenus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Collection<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Collection<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    public Collection<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Collection<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Role role = (Role) o;

        return Objects.equals(name, role.name) &&
                Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, userRoles, rolePrivileges, roleMenus);
    }
}
