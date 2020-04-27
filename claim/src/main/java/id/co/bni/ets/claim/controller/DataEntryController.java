package id.co.bni.ets.claim.controller;

import id.co.bni.ets.claim.base.controller.AbstractAdminLookupController;
import id.co.bni.ets.claim.service.DataEntryService;
import id.co.bni.ets.lib.model.entity.DataEntry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataentry")
public class DataEntryController extends AbstractAdminLookupController<DataEntry, Integer, DataEntry> {

    public DataEntryController(DataEntryService dataEntryService) {
        super(dataEntryService);
    }

    @Override
    protected String getEntityName() {
        return "DATAENTRY";
    }
}