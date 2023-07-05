package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import com.microreddit.MicroReddit.comment.Comment;
import com.microreddit.MicroReddit.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private User author;

    @ManyToOne
    @JoinColumn(name="channel_id")
    private Channel channel;
    private String text;

    private Long upVotes;
    private Long downVotes;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Long upVotes) {
        this.upVotes = upVotes;
    }

    public Long getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(Long downVotes) {
        this.downVotes = downVotes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
