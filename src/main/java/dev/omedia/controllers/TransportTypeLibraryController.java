package dev.omedia.controllers;

import dev.omedia.domains.TransportType;
import dev.omedia.helpers.Pager;
import dev.omedia.services.TransportTypeLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("transportTypes")
public class TransportTypeLibraryController {
    private final TransportTypeLibraryService service;

    @Autowired
    public TransportTypeLibraryController(TransportTypeLibraryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<TransportType>> getTransportTypes(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getTransportType) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getTransportTypes())
                , HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeTransportType( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeTransportType) id: {} ",id);
        return new ResponseEntity<>(service.removeTransportType(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addTransportType(@RequestBody final TransportType transportType) {
        log.debug(" PostMapping (addTransportType) ");
        return new ResponseEntity<>(service.addTransportType(transportType),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TransportType> updateTransportType(@PathVariable final long id,@RequestBody final TransportType transportType) {
        log.debug(" PutMapping (updateTransportType) id: {}",id);
        return new ResponseEntity<>(service.updateTransportType(id, transportType),HttpStatus.CREATED);
    }
    
}
