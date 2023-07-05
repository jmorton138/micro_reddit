package com.microreddit.MicroReddit.comment;

import com.microreddit.MicroReddit.post.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;

    public void addNewCommentToPost(Comment comment, int postId) {
        Optional<Comment> newComment = postRepo.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepo.save(comment);
        });
    }

    public void addNewSubCommentToComment(int parentCommentId, Comment subComment) {
        Comment parentComment = commentRepo.getById(parentCommentId);
        List<Comment> subComments = parentComment.getSubComments();
        commentRepo.save(subComment);
        subComments.add(subComment);
        parentComment.setSubComments(subComments);
        commentRepo.save(parentComment);

    }

}
