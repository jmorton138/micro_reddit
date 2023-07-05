package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByChannel(Channel channel);
}
