package com.lld.splitwise.models;

import com.lld.splitwise.dtos.UserDto;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    String name;
    String email;
    String password;

    public UserDto toDto()
    {
        UserDto userDto = new UserDto();
        userDto.setName(this.getName());
        userDto.setEmail(this.getEmail());

        return userDto;
    }
}
