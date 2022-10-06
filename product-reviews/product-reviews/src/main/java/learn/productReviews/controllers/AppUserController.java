package learn.productReviews.controllers;

import learn.productReviews.App;
import learn.productReviews.domain.AppUserService;
import learn.productReviews.domain.Result;
import learn.productReviews.models.AppUser;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/friends")
public class AppUserController {

    // add friends (need to implement in data and domain)
    // find friends (already implemented in data and domain)

    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public List<AppUser> getFriends(@PathVariable int id){

        return service.getFriends(id);
    }

    @PostMapping("/user/{currentUserId}")
    public ResponseEntity<?> addFriend(@PathVariable int currentUserId, @RequestBody AppUser friend){
        
        AppUser currentUser = service.findById(currentUserId);
        Result<AppUser> result = service.addFriend(currentUser, friend);

        if (!result.isSuccess()){
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
}
