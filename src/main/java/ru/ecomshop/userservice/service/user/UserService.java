package ru.ecomshop.userservice.service.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ecomshop.userservice.model.dto.user.request.CreateUserRequest;
import ru.ecomshop.userservice.model.dto.user.request.UpdateUserRequest;
import ru.ecomshop.userservice.model.dto.user.response.UserResponse;
import ru.ecomshop.userservice.model.entity.User;
import ru.ecomshop.userservice.model.mapper.user.UserMapper;
import ru.ecomshop.userservice.repository.user.UserRepository;

import java.util.Collection;
import java.util.Date;

import static ru.ecomshop.userservice.model.entity.enums.AccountStatus.CREATED;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Collection<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .toList();
    }

    public UserResponse getUserById(Long id) {
        var user = processFindUserById(id);
        return userMapper.map(user);
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.map(createUserRequest);
        user.setRegistrationDate(new Date());
        user.setAccountStatus(CREATED);

        return userMapper.map(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateUserById(Long userId, UpdateUserRequest updateUserRequest) {
        var user = processFindUserById(userId);

        user.updateFromRequest(updateUserRequest);

        return userMapper.map(user);
    }

    public void deleteUserById(Long userId) {
        var user = processFindUserById(userId);

        userRepository.delete(user);
    }

    private User processFindUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Пользователя по такому идентификатору не существует")
        );
    }

}
