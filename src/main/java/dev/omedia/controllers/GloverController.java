package dev.omedia.controllers;

import dev.omedia.domains.Glover;
import dev.omedia.domains.TransportType;
import dev.omedia.helpers.Pager;
import dev.omedia.services.GloverService;
import dev.omedia.services.TransportTypeLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("Glovers")
public class GloverController {
    private final GloverService service;
    private final TransportTypeLibraryService transportTypeLibraryService;

    @Autowired
    public GloverController(GloverService service,TransportTypeLibraryService transportTypeLibraryService) {
        this.service = service;
        this.transportTypeLibraryService=transportTypeLibraryService;
    }

    @GetMapping
    public ResponseEntity<Collection<Glover>> getGlovers(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getGlovers) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getGlovers())
                , HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Glover>  getGlover( @PathVariable final  long id) {
        log.debug(" GetMapping (getGlover) id: {}",id);
        return new ResponseEntity<>(service.getGlover(id),HttpStatus.OK);
    }
    @GetMapping("{id}/transportType")
    public ResponseEntity<TransportType> getTransportType(@PathVariable final long id) {
        log.debug(" GetMapping (getTransportType) gloverId: {}", id);
        return new ResponseEntity<>(service.getGlover(id).getTransportType(), HttpStatus.OK);
    }

    @PutMapping("{id}/transportType")
    public ResponseEntity<Boolean> updateTransportType(@RequestBody final long typeId
            , @PathVariable final long id) {
        TransportType tt =transportTypeLibraryService.getTransportType(typeId);
        service.getGlover(id).setTransportType(tt);
        log.debug(" PutMapping (updateTransportType) id: {}", typeId);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeGlover( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeGlover) id: {} ",id);
        return new ResponseEntity<>(service.removeGlover(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addGlover(@RequestBody final Glover Glover) {
        log.debug(" PostMapping (addGlover) ");
        return new ResponseEntity<>(service.addGlover(Glover),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Glover> updateGlover(@PathVariable final long id,@RequestBody final Glover Glover) {
        log.debug(" PutMapping (updateGlover) id: {}",id);
        return new ResponseEntity<>(service.updateGlover(id, Glover),HttpStatus.CREATED);
    }
}
