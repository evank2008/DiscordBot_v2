package org.jointheleague.features.student.first_feature;

import java.util.HashMap;

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
    int ignoreCounter = 0;
    boolean deleteConfirmation=false;
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
        	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed(1));
        	}
        } else if(event.getEvent().getAuthor().getId().equals(userId)) {
        	if(deleteConfirmation) {
        		if(event.getMessageContent().equalsIgnoreCase("y")||event.getMessageContent().equalsIgnoreCase("yes")||event.getMessageContent().equalsIgnoreCase("confirm")) {
        			MatchThinker.Schedule.remove(matchIndex);
        			event.sendResponse("Match deleted.");
        			deleteConfirmation=false;
        			interactStatus=false;
            		userId=null;
            		MatchThinker.saveScheduleToFile();
        		} else {
        			deleteConfirmation=false;
        			event.sendResponse("Deletion aborted.");
        		}
        	}
        	else if(event.getMessageContent().equalsIgnoreCase("quit")||event.getMessageContent().equalsIgnoreCase("done")||event.getMessageContent().equalsIgnoreCase("close")||event.getMessageContent().equalsIgnoreCase("exit")) {
        		interactStatus=false;
        		userId=null;
        		event.sendResponse("Quit.");
        	} else if(event.getMessageContent().equalsIgnoreCase("next")||event.getMessageContent().equalsIgnoreCase("right")||event.getMessageContent().equalsIgnoreCase("forward")) {
        		if(MatchThinker.Schedule.size()==matchIndex+1) {
        			event.sendResponse("No matches past this one.");
        		} else {
        			matchIndex++;
                	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed(1));
        		}
        	} else if(event.getMessageContent().equalsIgnoreCase("last")||event.getMessageContent().equalsIgnoreCase("left")||event.getMessageContent().equalsIgnoreCase("back")||event.getMessageContent().equalsIgnoreCase("previous")) {
        		if(matchIndex==0) {
        			event.sendResponse("No matches before this one.");
        		} else {
        			matchIndex--;
                	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed(1));
        		}
        	} else if(event.getMessageContent().equalsIgnoreCase("ping")||event.getMessageContent().equalsIgnoreCase("notify")) {
        		event.sendResponse("Get ready for "+MatchThinker.Schedule.get(matchIndex).matchTitle+"!!! \n"+MatchThinker.Schedule.get(matchIndex).getRosterNotify());
        	} else if(event.getMessageContent().equalsIgnoreCase("remove")||event.getMessageContent().equalsIgnoreCase("delete")||event.getMessageContent().equalsIgnoreCase("expunge")) {
        		event.sendResponse("Are you _sure_ you want to remove the match "+MatchThinker.Schedule.get(matchIndex).matchTitle+"? y/n");
        		deleteConfirmation=true;
        	} else if(event.getMessageContent().startsWith("addline")||event.getMessageContent().startsWith("add")||event.getMessageContent().startsWith("newline")||event.getMessageContent().startsWith("line")) {
           	 
            	String extra = event.getMessageContent().substring(event.getMessageContent().indexOf(' ')+1);
            	MatchThinker.Schedule.get(matchIndex).addExtra(extra);
            	event.sendResponse("Added line: "+extra);
            	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed(1));
            	MatchThinker.saveScheduleToFile();
            	}
        	else if(event.getMessageContent().startsWith("clear")) {
           	 
            	MatchThinker.Schedule.get(matchIndex).clearExtra();
            	event.sendResponse("Cleared extra lines.");
            	event.sendResponse(MatchThinker.Schedule.get(matchIndex).getEmbed(1));
            	MatchThinker.saveScheduleToFile();
            	}
        	else {
        		ignoreCounter++;
        		if(ignoreCounter==3) {
        			ignoreCounter=0;
        			interactStatus=false;
        			userId=null;
        		}
        	}
        }
    }

}
