package id.co.bni.ets.lib.model;

import id.co.bni.ets.lib.model.entity.Organization;

import java.io.Serializable;

@SuppressWarnings("unused")
public class SimpleOrganization implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String codeName;

    private String abbreviation;

    public SimpleOrganization() {
        // Empty constructor.
    }

    public SimpleOrganization(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.description = organization.getDescription();
        this.codeName = organization.getCodeName();
        this.abbreviation = organization.getAbbreviation();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
