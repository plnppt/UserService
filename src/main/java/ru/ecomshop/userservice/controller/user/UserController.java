package ru.ecomshop.userservice.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ecomshop.userservice.model.dto.user.request.CreateUserRequest;
import ru.ecomshop.userservice.model.dto.user.request.UpdateUserRequest;
import ru.ecomshop.userservice.model.dto.user.response.UserResponse;
import ru.ecomshop.userservice.service.user.UserService;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
//TODO: Подтверждение и блокировка
public class UserController implements UserApi {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Collection<UserResponse>> getAllUsers() {
        return ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        return ok(userService.getUserById(userId));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(createUserRequest));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable Long userId,
                                                       @RequestBody UpdateUserRequest updateUserRequest) {
        return ok(userService.updateUserById(userId, updateUserRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ok().build();
    }
}
