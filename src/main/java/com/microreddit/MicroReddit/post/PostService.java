package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import com.microreddit.MicroReddit.channel.ChannelRepo;
import com.microreddit.MicroReddit.comment.Comment;
import com.microreddit.MicroReddit.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ChannelRepo channelRepo;
    public void addNewPost(Post post, int channelId) {

        Optional<Post> newPost = channelRepo.findById(channelId).map(channel -> {
            int upVotes = 0;
            int downVotes = 0;
            post.setChannel(channel);
            post.setUpVotes(upVotes);
            post.setDownVotes(downVotes);
            return postRepo.save(post);
        });

    }

    public List<Post> getAllPostsByChannel(Channel channel) {
        return postRepo.findByChannel(channel);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getPostById(int postId) {
        Optional<Post> postOptional =  postRepo.findById(postId);
        Post post = null;
        if (postOptional.isPresent()) {
            post = postOptional.get();
        } else {
            throw new RuntimeException("Post not found for id ::" + postId);
        }
        return post;
    }

    public boolean userHasUpVoted(int postId) {
        Post post = getPostById(postId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getUpVoterIds().contains(currentUser.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userHasDownVoted(int postId) {
        Post post = getPostById(postId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getDownVoterIds().contains(currentUser.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePost(int postId, String voteType) {
        Post post = getPostById(postId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (voteType.equals("upvote")) {
            if (userHasUpVoted(postId)) {
                post.getUpVoterIds().removeIf( id -> id == currentUser.getId());
                post.setUpVotes(post.getUpVotes() - 1);
            } else {
                post.getUpVoterIds().add(currentUser.getId());
                post.setUpVotes(post.getUpVotes() + 1);
            }
        } else if (voteType.equals("downvote")) {
            if (userHasDownVoted(postId)) {
                post.getDownVoterIds().removeIf( id -> id == currentUser.getId());
                post.setDownVotes(post.getDownVotes() - 1);
            } else {
                post.getDownVoterIds().add(currentUser.getId());
                post.setDownVotes(post.getDownVotes() + 1);
            }
        }
        postRepo.save(post);
    }

    public void addNewSubPost(int parentPostId, Post subPost) {
        Post parentPost = postRepo.getById(parentPostId);
        List<Post> subPosts = parentPost.getSubPosts();
        postRepo.save(subPost);
        subPosts.add(subPost);
        parentPost.setSubPosts(subPosts);
        postRepo.save(parentPost);
    }
}
