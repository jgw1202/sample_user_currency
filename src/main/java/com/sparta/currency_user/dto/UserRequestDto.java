package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = "Name은 필수 입력 값입니다.")
    @Size(min = 2, max = 30, message = "Name은 2자 이상 30자 이하로 입력해주세요.")
    private String name;

    @NotBlank(message = "Email은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "올바른 이메일 형식을 입력해 주세요")
    private String email;

    // toEntity 메서드 추가
    public User toEntity() {
        return new User(name, email);
    }
}
