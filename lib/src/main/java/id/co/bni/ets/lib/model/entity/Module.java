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
@Table(name = "sys_module", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Module extends AbstractTrackedEntity<Integer> {

    private String name;

    private String description;

    @OneToMany(mappedBy = "secondEntity")
    @JsonIgnore
    private Collection<RoleModule> roleModules;

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

    public Collection<RoleModule> getRoleModules() {
        return roleModules;
    }

    public void setRoleModules(Collection<RoleModule> rolePrivileges) {
        this.roleModules = rolePrivileges;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Module that = (Module) o;

        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }
}
