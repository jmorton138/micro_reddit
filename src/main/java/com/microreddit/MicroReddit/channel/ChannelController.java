package com.microreddit.MicroReddit.channel;

import com.microreddit.MicroReddit.post.Post;
import com.microreddit.MicroReddit.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private PostService postService;
    @GetMapping("/channels")
    public String showChannelsList(Model model) {
        model.addAttribute("channels", channelService.getAllChannels());
        return "channels-list";
    }

    @GetMapping("/channels/{channelId}")
    public String showChannel(@PathVariable("channelId") int channelId, Model model) {
        Channel channel = channelService.getChannelById(channelId);
        model.addAttribute("channel", channel);
        model.addAttribute("newPost", new Post());
        model.addAttribute("posts", postService.getAllPostsByChannel(channel));
        return "channel";
    }

    @GetMapping("/channel-create")
    public String showChannelCreateForm(Model model) {
        model.addAttribute("channel", new Channel());
        return "channel-create";
    }

    @PostMapping("channel-create")
    public String submitChannelCreateForm(@ModelAttribute("channel") Channel channel) {
        channelService.addNewChannel(channel);
        return "redirect:/channels";
    }




}
