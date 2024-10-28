package com.example.exe201.utilities;

import com.example.exe201.dto.LoginDTO;
import com.example.exe201.model.User;
import com.example.exe201.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ValidateAccount {
    @Autowired
    private  UserServiceImpl userService;
    public HashMap<String, String> validateAccount(User user){
        HashMap<String, String> errors = new HashMap<>();
        if (user.getFirstName()== null || user.getFirstName().trim().isBlank()) {
            errors.put("firstNameErr", "Vui lòng nhập Họ");
        }
        if (user.getLastName()== null || user.getLastName().trim().isBlank()) {
            errors.put("lastNameErr", "Vui lòng nhập Tên");
        }
        if (user.getUsername() == null || user.getUsername().trim().isBlank()) {
            errors.put("usernameErr", "Vui lòng nhập Tên Đăng Nhập");
        } else {
            if (user.getUsername().matches(".*[A-Z].*")) {
                errors.put("usernameErr", "Tên đăng nhập không được chứa ký tự viết hoa");
            }
            if (user.getUsername().contains(" ")) {
                errors.put("UsernameErr", "Tên đăng nhập không được có khoảng trống");
            }
            User userAccount = userService.findUserByUsername(user.getUsername());
            if (userAccount != null) {
                errors.put("usernameErr", "Tên đăng nhập đã tồn tại");
                if (user.getEmail() != null && user.getEmail().equals(userAccount.getEmail())) {
                    errors.put("EmailErr", "Email đã tồn tại");
                }
            }
        }
        if (user.getEmail()== null || user.getEmail().trim().isBlank()) {
            errors.put("EmailErr", "Vui lòng nhập Email");
        }
        if (user.getPassword() == null || user.getPassword().trim().isBlank()) {
            errors.put("PasswordErr", "Vui lòng nhập Mật Khẩu");
        } else {
            if (!isValidPassword(user.getPassword())) {
                errors.put("PasswordErr", "Mật khẩu phải có ít nhất 6 ký tự, 1 chữ cái viết hoa, 1 số và 1 ký tự đặc biệt");
            }
        }
        return errors;
    }
    public HashMap<String, String> validateLogin(LoginDTO loginDTO){
        HashMap<String, String> errors = new HashMap<>();

        if(loginDTO.getUsername() == null || loginDTO.getUsername().trim().isBlank()){
            errors.put("usernameErr", "Vui lòng điền tên đăng nhập");
        }
        if(loginDTO.getPassword() == null || loginDTO.getPassword().trim().isBlank()){
            errors.put("passwordErr", "Vui lòng điền mật khẩu");
        }
        if(errors.isEmpty()){
            User userLoggedIn = userService.findUserByUsername(loginDTO.getUsername());
            if(userLoggedIn == null){
                errors.put("loginErr", "Tên đăng nhập hoặc mật khẩu sai");
            }else{
                if(!userLoggedIn.getPassword().equals(loginDTO.getPassword())){
                    errors.put("loginErr", "Tên đăng nhập hoặc mật khẩu sai");
                }
            }
        }
        return errors;
    }
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{6,}$";
        return password.matches(passwordRegex);
    }

}
