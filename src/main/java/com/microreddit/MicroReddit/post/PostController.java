package com.microreddit.MicroReddit.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/channels/{channelId}/post-create")
    public String submitNewPost(@ModelAttribute("post") Post post, @PathVariable("channelId") int channelId) {
        postService.addNewPost(post, channelId);
        return "redirect:/channels/{channelId}";
    }
}
