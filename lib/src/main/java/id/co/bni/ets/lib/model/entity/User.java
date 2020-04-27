package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_user", schema = "dbo")
public class User extends AbstractActiveFlaggedEntity<Long> {

    @NotBlank(message = "Username must not blank.")
    private String username;

    private String description;

    @NotBlank(message = "Authentication provider must not blank.")
    private String authProvider;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Character locked;

    @OneToMany(mappedBy = "firstEntity", cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<UserRole> userRoles;

    @OneToMany(mappedBy = "firstEntity", cascade = CascadeType.PERSIST)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<UserOrganization> userOrganizations;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Collection<UserOrganization> getUserOrganizations() {
        return userOrganizations;
    }

    public void setUserOrganizations(Collection<UserOrganization> userOrganizations) {
        this.userOrganizations = userOrganizations;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        User user = (User) o;

        return Objects.equals(username, user.username) &&
                Objects.equals(description, user.description) &&
                Objects.equals(authProvider, user.authProvider) &&
                Objects.equals(password, user.password) &&
                Objects.equals(locked, user.locked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, description, authProvider, password, locked);
    }
}
