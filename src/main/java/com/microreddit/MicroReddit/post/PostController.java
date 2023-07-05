package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/channels/{channelId}/posts/{postId}")
    public String showPost(@PathVariable("postId") int postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("channel", post.getChannel());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("comments", post.getComments());
        return "post";
    }

    @PostMapping("/channels/{channelId}/post-create")
    public String submitNewPost(@ModelAttribute("post") Post post, @PathVariable("channelId") int channelId) {
        postService.addNewPost(post, channelId);
        return "redirect:/channels/{channelId}";
    }
}
