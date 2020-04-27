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
@Table(name = "DATAENTRY", schema = "dbo")
public class DataEntry extends AbstractActiveFlaggedEntity<Integer> {
    
    private Integer claimID;
    private String customerData;
    private String facilityData;
    private String brokerData;
    private String insuranceCompData;

    public Integer getClaimID() {
        return claimID;
    }

    public void setClaimID(Integer claimID) {
        this.claimID = claimID;
    }

    public String getCustomerData() {
        return customerData;
    }

    public void setCustomerData(String customerData) {
        this.customerData = customerData;
    }

    public String getFacilityData() {
        return facilityData;
    }

    public void setFacilityData(String facilityData) {
        this.facilityData = facilityData;
    }

    public String getBrokerData() {
        return brokerData;
    }

    public void setBrokerData(String brokerData) {
        this.brokerData = brokerData;
    }

    public String getInsuranceCompData() {
        return insuranceCompData;
    }

    public void setInsuranceCompData(String insuranceCompData) {
        this.insuranceCompData = insuranceCompData;
    }

 
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        DataEntry dataEntry = (DataEntry) o;

        return Objects.equals(claimID, dataEntry.claimID) &&
                Objects.equals(customerData, dataEntry.customerData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), claimID, customerData, facilityData, brokerData, insuranceCompData);
    }
}
