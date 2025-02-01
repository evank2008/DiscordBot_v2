package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

public class Kys extends Feature {

    public final String COMMAND = "onion kys";

    public Kys(String channelName) {
        super(channelName);

        //Create a help embed to describe feature when !help command is sent
        //no
    }

    @Override
    public void handle(ReceivedMessage event) {
        String messageContent = event.getMessageContent().toLowerCase();
        
        if (messageContent.contains("onion")) {
        	if(messageContent.contains("kys")||messageContent.contains("kill yourself")) {
            //respond to message here
            event.sendResponse("killing self...");
            System.exit(0);
        	}
        }
    }

}
