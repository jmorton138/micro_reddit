package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import com.microreddit.MicroReddit.channel.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
            post.setChannel(channel);
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
}
