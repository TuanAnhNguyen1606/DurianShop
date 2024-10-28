package com.example.exe201.controller;

import com.example.exe201.model.*;
import com.example.exe201.service.BlogCommentService;
import com.example.exe201.service.impl.BlogServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Blog")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private BlogCommentService blogCommentService;
    @GetMapping()
    public String blog(Model model){
        List<Blog> blogList = blogService.getAllBlogs();
        HashMap<Integer, Integer> numberOfComments = new HashMap<>();
        for(Blog blog : blogList){
            List<BlogComment> comments = blogCommentService.getAllBlogCommentsByBlogID(blog.getBlogID());
            numberOfComments.put(blog.getBlogID(), comments.size());
        }
        model.addAttribute("blogs", blogList);
        model.addAttribute("numberOfComments", numberOfComments);
        return "blog";
    }
    @GetMapping("/{blogID}")
    public String getBlogDetail(@PathVariable("blogID") int blogID, Model model){
        Optional<Blog> blog = blogService.getBlogDetail(blogID);
        List<BlogDetail> blogDetails = blogService.getBlogDetails(blogID);
        List<Blog> blogList = blogService.getAllBlogs();
        List<BlogComment> blogComments = blogCommentService.getAllBlogCommentsByBlogID(blogID);

        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            model.addAttribute("blogDetails", blogDetails);
            model.addAttribute("blogs", blogList);
            model.addAttribute("blogComments", blogComments);
            return "blog-single"; // View to show blog details
        } else {
            return "404";
        }
    }
    @PostMapping("/add-comment")
    public String addBlogComment(@RequestParam(name = "blogID") Integer blogID,
                                 @RequestParam(name = "content") String content,
                                 RedirectAttributes redirectAttributes,
                                 HttpSession session){
        User user = (User) session.getAttribute("user");
        Blog blog = blogService.getBlogDetail(blogID).orElse(null);
        if(user != null){
            BlogComment blogComment = BlogComment.builder()
                    .blog(blog)
                    .commentContent(content)
                    .createdAt(LocalDate.now())
                    .parentID(null)
                    .user(user).build();
            blogCommentService.addBlogComment(blogComment);
            redirectAttributes.addFlashAttribute("commentBlogSuccess", "Bạn đã bình luận thành công");
            return "redirect:/Blog/" + blogID;
        }
        return "redirect:/Account/Login";
    }
    @PostMapping("/reply-comment")
    public String replyBlogComment(@RequestParam(name = "blogID") Integer blogID,
                                   @RequestParam(name = "commentID") Integer commentID,
                                   @RequestParam(name = "content") String content,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession session){
        User user = (User) session.getAttribute("user");
        Blog blog = blogService.getBlogDetail(blogID).orElse(null);
        if(user != null){
            BlogComment blogComment = BlogComment.builder()
                    .blog(blog)
                    .commentContent(content)
                    .createdAt(LocalDate.now())
                    .parentID(commentID)
                    .user(user)
                    .build();
            blogCommentService.addBlogComment(blogComment);
            redirectAttributes.addFlashAttribute("replyCommentBlog", "Bạn đã trả lời bình luận thành công");
            return "redirect:/Blog/" + blogID;
        }
        return "redirect:/Account/Login";
    }
}
