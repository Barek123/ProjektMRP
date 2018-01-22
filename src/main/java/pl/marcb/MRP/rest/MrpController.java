package pl.marcb.MRP.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.PurchaseHistory;
import pl.marcb.MRP.domain.dto.MrpDto;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.LogicException;
import pl.marcb.MRP.service.MrpService;
import pl.marcb.MRP.view.Views;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/mrp")
public class MrpController {

    @Autowired
    private MrpService mrpService;

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(Views.PurchaseHistory.class)
    public @ResponseBody List<PurchaseHistory> insertProduct(@RequestBody MrpDto mrpDto) throws BusinessEntityNotFoundException, LogicException {
        return mrpService.generateMRP(mrpDto.getDate(), mrpDto.getProductId(), mrpDto.getNumber());
    }
}
