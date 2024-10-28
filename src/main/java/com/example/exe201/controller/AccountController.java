package com.example.exe201.controller;

import com.example.exe201.config.SendEmail;
import com.example.exe201.dto.LoginDTO;
import com.example.exe201.dto.RegisterDTO;
import com.example.exe201.enums.Role;
import com.example.exe201.model.User;
import com.example.exe201.service.impl.UserServiceImpl;
import com.example.exe201.utilities.ValidateAccount;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/Account")
public class AccountController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ValidateAccount validateAccount;
    @Autowired
    private SendEmail sendEmail;

    @GetMapping("/Login")
    public String login(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            return "index";
        }
        return "login";
    }
    @PostMapping("/Login")
    public String userLogin(@RequestParam(name="username") String username,
                            @RequestParam(name="password") String password,
                            Model model, HttpSession session){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        HashMap<String, String> errors = validateAccount.validateLogin(loginDTO);
        if(errors != null && !errors.isEmpty()){
            model.addAttribute("errors", errors);
            model.addAttribute("loggedIn", loginDTO);
            return "login";
        }else{
            User user = userService.findUserByUsername(loginDTO.getUsername());
            if(user == null){
                model.addAttribute("errors", "Tên đăng nhập hoặc mật khẩu sai");
                model.addAttribute("loggedIn", loginDTO);
                return "login";
            }else{
                session.setAttribute("user", user);
                return "redirect:/Home";
            }
        }
    }
    @GetMapping("/Register")
    public String register(){
        return "register";
    }
    @PostMapping("/Register")
    public String addNewUser(Model model, RegisterDTO registerDTO
            , @RequestParam(name="confirm-password") String repassword,
              HttpSession session){
        User createdUser = User.builder()
                .firstName(registerDTO.getFirstName())
                .middleName(registerDTO.getMiddleName())
                .lastName(registerDTO.getLastName())
                .email(registerDTO.getEmail())
                .role("User")
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                .build();
        HashMap<String, String> errors = validateAccount.validateAccount(createdUser);
        Random randomOTP = new Random();
        int otp = 100000 + randomOTP.nextInt(900000);
        if(!registerDTO.getPassword().equals(repassword)){
            errors.put("passwordErr", "Mật khẩu xác thực không trùng với mật khẩu");
        }
        if(errors != null && !errors.isEmpty()){
            model.addAttribute("errors", errors);
            model.addAttribute("createdUser", createdUser);
            return "register";
        }else{
            session.setAttribute("createdUser", createdUser);
            session.setAttribute("otp", otp);
            sendEmail.sendEmail(createdUser.getEmail(), "Hãy xác thực mã OTP", "Mã OTP của bạn là " + otp);
            return "EnterOTP";
        }
    }
    @PostMapping("/VerifyOTP")
    public String verifyOTP(@RequestParam(name="otp", required = true) int OTP,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes){
        Integer sessionOtp = (Integer) session.getAttribute("otp");
        User createdUser = (User) session.getAttribute("createdUser");
        if (sessionOtp.equals(OTP)) {
            userService.createUser(createdUser);
            session.removeAttribute("otp");
            session.removeAttribute("createdUser");
            redirectAttributes.addFlashAttribute("registerSuccess", "Bạn đã đăng ký thành công");
            return "redirect:/Account/Login";
        } else {
            model.addAttribute("errorOTP", "Mã OTP không chính xác. Vui lòng thử lại.");
            return "EnterOTP";
        }
    }
    @GetMapping("/Logout")
    public String logout(HttpSession session, Model model){
        session.invalidate();
        model.addAttribute("logoutSuccess", "Bạn đã đăng xuất thành công");
        return "login";
    }
    @GetMapping("/Profile")
    public String userProfile(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/Account/Login";
        }
        return "profile";
    }

    @PostMapping("/UpdateProfile")
    public String updateProfile(@RequestParam(name = "firstName", required = false) String firstName,
                                @RequestParam(name = "middleName", required = false) String middleName,
                                @RequestParam(name = "lastName", required = false) String lastName,
                                @RequestParam(name = "password", required = false) String password,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/Account/Login";
        }
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setPassword(password);
        User updatedUser = userService.updateUser(user.getUserId(), user);
        if (updatedUser != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật thông tin thất bại!");
        }
        session.setAttribute("user", updatedUser);
        return "redirect:/Account/Profile";
    }
    @GetMapping("/MyOrder")
    public String getProfileOrder(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/Account/Login";
        }
        return "myorder";
    }
    @GetMapping("/MyOrder/Details")
    public String showMyOrderDetail(){
        return "myorderdetail";
    }

}
