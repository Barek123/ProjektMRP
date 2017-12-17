package pl.marcb.MRP.rest;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.marcb.MRP.service.StorageUpdateService;
import pl.marcb.MRP.view.Views;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/storageupdate")
public class StorageUpdateController {
    @Autowired
    private StorageUpdateService storageUpdateService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Views.StorageUpdate.class)
    public void updateStorage() {
        storageUpdateService.updateStorage();
    }
}
