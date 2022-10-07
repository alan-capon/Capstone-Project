package reviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.domain.AppUserService;
import reviews.domain.Result;
import reviews.models.AppUser;

@RestController
@RequestMapping("/api/user")
public class AppUserController {

    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public AppUser findByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AppUser appUser) {
        Result<AppUser> result = service.create(appUser);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

}
