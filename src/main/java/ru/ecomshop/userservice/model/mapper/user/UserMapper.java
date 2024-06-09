package ru.ecomshop.userservice.model.mapper.user;

import org.mapstruct.Mapper;
import ru.ecomshop.userservice.model.dto.user.request.CreateUserRequest;
import ru.ecomshop.userservice.model.dto.user.response.UserResponse;
import ru.ecomshop.userservice.model.entity.User;

@Mapper
public interface UserMapper {

    UserResponse map(User user);
    User map(UserResponse dto);
    User map(CreateUserRequest dto);
}
