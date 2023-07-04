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

    public Optional<Channel> getChannelById(int channelId) {
        return channelRepo.findById(channelId);
    }
}
