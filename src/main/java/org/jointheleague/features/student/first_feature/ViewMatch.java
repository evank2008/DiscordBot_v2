package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.FileUpload;

public class ViewMatch extends Feature {

    public final String COMMAND = "onion view";
    boolean interactStatus=false;
    String userId;
    int matchIndex;
    public ViewMatch(String channelName) {
        super(channelName);

        helpEmbed = new HelpEmbed(COMMAND, 
        "View the list of upcoming matches.");

    }

    @Override
    public void handle(ReceivedMessage event) {
        String messageContent = event.getMessageContent().toLowerCase();
        
        if (messageContent.contains(COMMAND)&&!interactStatus) {
        	if(MatchThinker.Schedule.isEmpty()) {
        		event.sendResponse("No matches scheduled.");
        	} else {
            //respond to message here
        	interactStatus=true;
        	userId=event.getEvent().getAuthor().getId();
        	matchIndex=0;
        	//show earliest match in list, add some way to go forward or back
        	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed());
        	}
        } else if(event.getEvent().getAuthor().getId().equals(userId)) {
        	if(event.getMessageContent().equalsIgnoreCase("quit")||event.getMessageContent().equalsIgnoreCase("done")||event.getMessageContent().equalsIgnoreCase("close")||event.getMessageContent().equalsIgnoreCase("exit")) {
        		interactStatus=false;
        		userId=null;
        		event.sendResponse("Quit.");
        	} else if(event.getMessageContent().equalsIgnoreCase("next")||event.getMessageContent().equalsIgnoreCase("right")||event.getMessageContent().equalsIgnoreCase("forward")) {
        		if(MatchThinker.Schedule.size()==matchIndex+1) {
        			event.sendResponse("No matches past this one.");
        		} else {
        			matchIndex++;
                	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed());
        		}
        	} else if(event.getMessageContent().equalsIgnoreCase("last")||event.getMessageContent().equalsIgnoreCase("left")||event.getMessageContent().equalsIgnoreCase("back")||event.getMessageContent().equalsIgnoreCase("previous")) {
        		if(matchIndex==0) {
        			event.sendResponse("No matches before this one.");
        		} else {
        			matchIndex--;
                	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed());
        		}
        	}
        }
    }

}
