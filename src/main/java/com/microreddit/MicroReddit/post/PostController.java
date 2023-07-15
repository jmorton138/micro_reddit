package com.microreddit.MicroReddit.post;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.microreddit.MicroReddit.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        model.addAttribute("hasUpVoted", postService.userHasUpVoted(postId));
        model.addAttribute("hasDownVoted", postService.userHasDownVoted(postId));
        return "post";
    }

    @PostMapping("/channels/{channelId}/post-create")
    public String submitNewPost(@ModelAttribute("post") Post post, @PathVariable("channelId") int channelId) {
        postService.addNewPost(post, channelId);
        return "redirect:/channels/{channelId}";
    }

    @PutMapping("/posts/{postId}/vote")
    public String updatePostVotes(@PathVariable("postId") int postId, @RequestBody Map<String, String> requestData) {
       // @RequestBody JsonNode voteType
        // System.out.println(requestData.get("voteType"));
        postService.updatePost(postId, String.valueOf(requestData.get("voteType")));
        return "index";
    }

    @GetMapping("/posts/{postId}/votes/vote-type/{nonTargetedVoteType}")
    public ResponseEntity<Boolean> userVotedNonTargeted(@PathVariable("postId") int postId, @PathVariable("nonTargetedVoteType") String nonTargetedVoteType) {
        System.out.println(nonTargetedVoteType);
        if (nonTargetedVoteType.equals("upvote")) {
            ResponseEntity<Boolean> hasVoted = new ResponseEntity<Boolean>(postService.userHasUpVoted(postId), HttpStatus.OK);
            return hasVoted;
        } else {
            ResponseEntity<Boolean> hasVoted = new ResponseEntity<Boolean>(postService.userHasDownVoted(postId), HttpStatus.OK);
            return hasVoted;
        }

    }

}
