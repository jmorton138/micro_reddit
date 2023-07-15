package com.microreddit.MicroReddit.post;

import com.microreddit.MicroReddit.channel.Channel;
import com.microreddit.MicroReddit.comment.Comment;
import com.microreddit.MicroReddit.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
public class Post {
    @Id
    @GeneratedValue
    private int id;

    private User author;

    @ManyToOne
    @JoinColumn(name="channel_id")
    private Channel channel;
    private String text;

    private int upVotes;
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Integer> upVoterIds = new ArrayList<Integer>();
    private int downVotes;

    private List<Integer> downVoterIds = new ArrayList<Integer>();

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

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
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


    public List<Integer> getUpVoterIds() {
        return upVoterIds;
    }

    public void setUpVoterIds(List<Integer> upVoterIds) {
        this.upVoterIds = upVoterIds;
    }

    public List<Integer> getDownVoterIds() {
        return downVoterIds;
    }

    public void setDownVoterIds(List<Integer> downVoterIds) {
        this.downVoterIds = downVoterIds;
    }
}
