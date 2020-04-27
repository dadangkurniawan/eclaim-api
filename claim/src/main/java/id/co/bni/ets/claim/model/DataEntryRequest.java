package id.co.bni.ets.claim.model;

import id.co.bni.ets.lib.base.model.request.AbstractRelationActiveFLaggedRequest;
import id.co.bni.ets.lib.base.model.request.RetrievableEntity;
import id.co.bni.ets.lib.model.entity.DataEntry;

import javax.validation.constraints.Positive;

@SuppressWarnings("unused")
public class DataEntryRequest extends AbstractRelationActiveFLaggedRequest<DataEntry> {

    @Positive(message = "Wrong role id.")
    private Integer claimId;

    private String customerData;
    private String facilityData;
    private String brokerData;
    private String insuranceCompData;

    public Integer getClaimId() {
        return claimId;
    }

    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
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
    public DataEntry getEntity() {
        DataEntry dataEntry = new DataEntry();

        dataEntry.setClaimID(claimId);
        dataEntry.setFacilityData(facilityData);
        dataEntry.setCustomerData(customerData);
        dataEntry.setBrokerData(brokerData);
        dataEntry.setInsuranceCompData(insuranceCompData);

        return dataEntry;
    }
}
