package com.example.exe201.controller;


import com.example.exe201.repository.UserRepository;
import com.example.exe201.service.VisitSiteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomepageController {

    @Autowired
    private VisitSiteService visitSiteService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = {"/", "/Home"})
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        // Kiểm tra cookie
        Cookie[] cookies = request.getCookies();
        boolean hasVisited = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("hasVisited".equals(cookie.getName())) {
                    hasVisited = true;
                    break;
                }
            }
        }

        if (!hasVisited) {
            visitSiteService.incrementVisitCount();
            Cookie visitCookie = new Cookie("hasVisited", "true");
            visitCookie.setMaxAge(30 * 24 * 60 * 60); // Cookie tồn tại trong 30 ngày
            response.addCookie(visitCookie); // Thêm cookie vào phản hồi
        }
        long totalVisits = visitSiteService.getTotalVisits() + 150;
        int numberOfCustomers = userRepository.countUsersByRole("User") + 70;
        model.addAttribute("totalVisits", totalVisits);
        model.addAttribute("numberOfCustomers", numberOfCustomers);
        return "index";
    }
}
