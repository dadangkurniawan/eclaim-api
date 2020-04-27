package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.co.bni.ets.lib.base.model.entity.AbstractTrackedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_privilege", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Privilege extends AbstractTrackedEntity<Integer> {

    private String name;

    private String description;

    @OneToMany(mappedBy = "secondEntity")
    @JsonIgnore
    private Collection<RolePrivilege> rolePrivileges;

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

    public Collection<RolePrivilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(Collection<RolePrivilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Privilege privilege = (Privilege) o;
        return Objects.equals(name, privilege.name) &&
                Objects.equals(description, privilege.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }
}
