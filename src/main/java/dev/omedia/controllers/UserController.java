package dev.omedia.controllers;


import dev.omedia.domains.User;
import dev.omedia.domains.UserStatus;
import dev.omedia.helpers.Pager;
import dev.omedia.services.UserService;
import dev.omedia.services.UserStatusLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("users")
public class UserController {
    private final UserService service;
    private final UserStatusLibraryService statusService;

    @Autowired
    public UserController(UserService service, UserStatusLibraryService statusService) {

        this.service = service;
        this.statusService=statusService;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getUsers(
            @RequestParam(required = false,defaultValue = "${page}", name = "page") final int page
            , @RequestParam(required = false,defaultValue = "${pageSize}", name = "pageSize") final int pageSize) {

        log.debug(" GetMapping (getUsers) page: {} pageSize: {} ",page,pageSize);

        return new ResponseEntity<>(Pager.getPageContent(page,pageSize,service.getUsers())
                , HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<User>  getUser( @PathVariable final  long id) {
        log.debug(" GetMapping (getUser) id: {}",id);
        return new ResponseEntity<>(service.getUser(id),HttpStatus.OK);
    }
    @GetMapping("{id}/userStatus")
    public ResponseEntity<UserStatus> getUserStatus(@PathVariable final long id) {
        log.debug(" GetMapping (getUserStatus) userId: {}", id);
        return new ResponseEntity<>(service.getUser(id).getStatus(), HttpStatus.OK);
    }

    @PutMapping("{id}/userStatus")
    public ResponseEntity<Boolean> updateUserStatus(@RequestBody final long statusId
            , @PathVariable final long id) {
        UserStatus status =statusService.getUserStatus(statusId);
        service.getUser(id).setStatus(status);
        log.debug(" PutMapping (updateUserStatus) id: {}", statusId);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> removeUser( @PathVariable final long id) {
        log.debug(" DeleteMapping (removeUser) id: {} ",id);
        return new ResponseEntity<>(service.removeUser(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> addUser(@RequestBody final User User) {
        log.debug(" PostMapping (addUser) ");
        return new ResponseEntity<>(service.addUser(User),HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable final long id,@RequestBody final User User) {
        log.debug(" PutMapping (updateUser) id: {}",id);
        return new ResponseEntity<>(service.updateUser(id, User),HttpStatus.CREATED);
    }
}
