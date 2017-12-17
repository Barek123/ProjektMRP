package pl.marcb.MRP.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.PurchaseHistory;
import pl.marcb.MRP.service.PurchaseHistoryService;
import pl.marcb.MRP.view.Views;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/purchasehistory")
public class PurchaseHistoryController {
    @Autowired
    private PurchaseHistoryService purchaseHistoryService;

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.PurchaseHistory.class)
    public void removeAllPurchases(){
        this.purchaseHistoryService.removeAllPurchases();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(Views.PurchaseHistory.class)
    public @ResponseBody PurchaseHistory insertNewPurchase(@RequestBody PurchaseHistory purchaseHistory){
        return this.purchaseHistoryService.insertNewPurchase(purchaseHistory);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @JsonView(Views.PurchaseHistory.class)
    public @ResponseBody PurchaseHistory findPurchaseById(@PathVariable("id") Long id){
        return this.purchaseHistoryService.findPurchaseById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.PurchaseHistory.class)
    public @ResponseBody List<PurchaseHistory> getAll(){
        return this.purchaseHistoryService.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/from/{from}/to/{to}")
    @JsonView(Views.PurchaseHistory.class)
    public @ResponseBody List<PurchaseHistory> getAll(@PathVariable("from") Date from, @PathVariable("to") Date to){
        return this.purchaseHistoryService.findAllByRange(from, to);
    }
}

