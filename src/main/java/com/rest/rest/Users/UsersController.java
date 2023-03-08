package com.rest.rest.Users;

import com.rest.rest.Users.Domain.User;
import com.rest.rest.Users.Domain.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController()
public class UsersController {
    List<User> users = new ArrayList<User>();

    public UsersController(){
        users.add(0, new User(1, "João", "joao@email.com"));
        users.add(1, new User(2, "Maria", "marial@email.com"));
    }

    @GetMapping("v1/users")
    private ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("v1/users")
    private ResponseEntity<User> postUser(@RequestBody @Valid UserDto dto){
        User user = new User(users.size() + 1, dto.getName(), dto.getEmail());
        users.add(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @GetMapping("v1/users/{id}")
    private ResponseEntity getUser(@PathVariable("id") int id){
        User userResponse = null;
        for (User user:
             users) {
            if (user.getId() == id){
                userResponse = user;
            }
        }

        if (userResponse != null){
            return new ResponseEntity<User>(userResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("v1/users/{id}")
    private ResponseEntity attUser(@PathVariable("id") int id, @RequestBody @Valid UserDto dto){
        User userResponse = null;
        int index;
        for (User user:
                users) {
            if (user.getId() == id){
                user.setEmail(dto.getEmail());
                user.setName(dto.getName());
                userResponse = user;
            }
        }

        if (userResponse != null){
            return new ResponseEntity<User>(userResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("v1/users/{id}")
    private ResponseEntity delete(@PathVariable("id") int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                break;
            }
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
