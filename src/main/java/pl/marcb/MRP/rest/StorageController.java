package pl.marcb.MRP.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.Storage;
import pl.marcb.MRP.service.StorageService;
import pl.marcb.MRP.view.Views;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(Views.Storage.class)
    public @ResponseBody
    Storage insertStorage(@RequestBody Storage storage) {
        return storageService.insertStorage(storage);
    }
}