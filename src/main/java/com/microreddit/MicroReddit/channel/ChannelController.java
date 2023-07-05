package com.microreddit.MicroReddit.channel;

import com.microreddit.MicroReddit.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping("/channels")
    public String showChannelsList(Model model) {
        model.addAttribute("channels", channelService.getAllChannels());
        return "channels-list";
    }

    @GetMapping("/channels/{channelId}")
    public String showChannel(@PathVariable("channelId") int channelId, Model model) {
        model.addAttribute("channel", channelService.getChannelById(channelId));
        model.addAttribute("newPost", new Post());
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
