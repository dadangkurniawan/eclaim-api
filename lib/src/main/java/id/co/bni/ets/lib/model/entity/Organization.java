package id.co.bni.ets.lib.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.co.bni.ets.lib.base.model.entity.AbstractActiveFlaggedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "sys_organization", schema = "dbo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization extends AbstractActiveFlaggedEntity<Long> {

    private String name;

    private String description;

    private String codeName;

    private String abbreviation;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private Integer parentId;

    @OneToMany(mappedBy = "secondEntity")
    @JsonIgnore
    private Collection<UserOrganization> userOrganizations;

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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        Organization that = (Organization) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(codeName, that.codeName) &&
                Objects.equals(abbreviation, that.abbreviation) &&
                Objects.equals(attribute1, that.attribute1) &&
                Objects.equals(attribute2, that.attribute2) &&
                Objects.equals(attribute3, that.attribute3) &&
                Objects.equals(attribute4, that.attribute4) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, codeName, abbreviation, attribute1, attribute2,
                attribute3, attribute4, parentId);
    }
}
