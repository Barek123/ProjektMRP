package pl.marcb.MRP.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.marcb.MRP.domain.dto.MrpDto;
import pl.marcb.MRP.exceptions.BusinessEntityNotFoundException;
import pl.marcb.MRP.exceptions.LogicException;
import pl.marcb.MRP.service.MrpService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/mrp")
public class MrpController {

    @Autowired
    private MrpService mrpService;

    @RequestMapping(method = RequestMethod.POST, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public void insertProduct(@RequestBody MrpDto mrpDto) throws BusinessEntityNotFoundException, LogicException {
        mrpService.generateMRP(mrpDto.getDate(), mrpDto.getProduct(), mrpDto.getNumber());
    }
}
