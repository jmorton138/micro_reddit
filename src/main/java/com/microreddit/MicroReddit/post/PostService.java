package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import com.microreddit.MicroReddit.channel.ChannelRepo;
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
    public void addVote(int postId) {
        addVoter();
    }
    public void removeVote(int postId) {
        removeVoter();
    }
    
    public void addVoter() {

    }
    public void removeVoter() {

    }
    public void updatePost(int postId, String voteType) {
        Post post = getPostById(postId);
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (post.getUpVoterIds().contains(currentUser.getId())) {
            post.getUpVoterIds().removeIf( id -> id == currentUser.getId());
            System.out.println(post.getUpVoterIds());
            post.setUpVotes(post.getUpVotes() - 1);
        } else {
            post.getUpVoterIds().add(currentUser.getId());
            post.setUpVotes(post.getUpVotes() + 1);
        }
        postRepo.save(post);
    }
}
