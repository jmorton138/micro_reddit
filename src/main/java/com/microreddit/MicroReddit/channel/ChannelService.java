package com.microreddit.MicroReddit.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepo channelRepo;

    public List<Channel> getAllChannels() {
        return channelRepo.findAll();
    }

    public void addNewChannel(Channel channel) {
        channelRepo.save(channel);
    }

    public Channel getChannelById(int channelId) {
        Optional<Channel> channelOptional =  channelRepo.findById(channelId);
        Channel channel = null;
        if (channelOptional.isPresent()) {
            channel = channelOptional.get();
        } else {
            throw new RuntimeException("Channel not found for id ::" + channelId);
        }
        return channel;
    }
}
