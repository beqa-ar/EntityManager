package dev.omedia.controllers;


import dev.omedia.domains.UserStatus;
import dev.omedia.helpers.Pager;
import dev.omedia.services.UserStatusLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("statuses")
public class UserStatusLibraryController {
    private final UserStatusLibraryService service;

    @Autowired
    public UserStatusLibraryController(UserStatusLibraryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Collection<UserStatus>> getUserStatuses(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getUserStatuses) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getUserStatuses())
                , HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeUserStatus( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeUserStatus) id: {} ",id);
        return new ResponseEntity<>(service.removeUserStatus(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addUserStatus(@RequestBody final UserStatus UserStatus) {
        log.debug(" PostMapping (addUserStatus) ");
        return new ResponseEntity<>(service.addUserStatus(UserStatus),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserStatus> updateUserStatus(@PathVariable final long id,@RequestBody final UserStatus UserStatus) {
        log.debug(" PutMapping (updateUserStatus) id: {}",id);
        return new ResponseEntity<>(service.updateUserStatus(id, UserStatus),HttpStatus.CREATED);
    }
}
