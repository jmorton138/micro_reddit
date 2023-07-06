package com.microreddit.MicroReddit.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/channels/{channelId}/posts/{postId}/comment-create")
    public String submitNewCommentToPost(@PathVariable("postId") int postId, @ModelAttribute("newComment") Comment comment) {
        commentService.addNewCommentToPost(comment, postId);
        return "redirect:/channels/{channelId}/posts/{postId}";
    }

    @PostMapping("/channels/{channelId}/posts/{postId}/{commentId}/subcomment-create")
    public String submitNewSubCommentToComment(@PathVariable("commentId") int parentCommentId, @ModelAttribute("newComment") Comment subComment) {
        commentService.addNewSubCommentToComment(parentCommentId, subComment);
        return "redirect:/channels/{channelId}/posts/{postId}";
    }
}
