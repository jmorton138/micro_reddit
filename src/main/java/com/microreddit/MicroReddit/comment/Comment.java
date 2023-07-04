package com.microreddit.MicroReddit.comment;

import com.microreddit.MicroReddit.post.Post;
import com.microreddit.MicroReddit.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;

    private User author;
    private String text;

    private Long upVotes;
    private Long downVotes;
    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @OneToMany
    private List<Comment> subComments;

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

    public List<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Comment> subComments) {
        this.subComments = subComments;
    }
}
