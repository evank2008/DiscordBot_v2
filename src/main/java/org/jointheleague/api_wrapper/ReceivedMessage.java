package org.jointheleague.api_wrapper;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ReceivedMessage {

    private MessageReceivedEvent event;
    private Guild guild;

    public ReceivedMessage(MessageReceivedEvent event) {
        this.event = event;
        this.guild=event.getGuild();
    }

    public String getMessageContent() {
        return this.event.getMessage().getContentStripped();
    }

    public Guild getGuild() {
    	return this.guild;
    }
    public void sendResponse(String message) {
        this.event.getChannel().sendMessage(message).submit().join();
    }
    public MessageReceivedEvent getEvent() {
        return this.event;
    }

    public Message sendResponse(MessageEmbed embed) {
        return this.event.getChannel().sendMessageEmbeds(embed).submit().join();
    }

}