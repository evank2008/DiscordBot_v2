package org.jointheleague.features.abstract_classes;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

public abstract class Feature extends ListenerAdapter
{

    protected String channelName;

    public HelpEmbed helpEmbed;

    public Feature(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getName().equals(channelName)&&!event.getAuthor().getId().equals("1331447532798214204")) {
            handle(new ReceivedMessage(event));													//this is the id of the bot
    	}
    }

    public HelpEmbed getHelpEmbed() {
        return this.helpEmbed;
    }

    public abstract void handle(ReceivedMessage event);

}