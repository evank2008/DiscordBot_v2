package org.jointheleague.features.student.first_feature;

import java.io.File;
import java.util.Random;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

import net.dv8tion.jda.api.utils.FileUpload;

public class FileManipulator extends Feature {

    public final String COMMAND = "onion clear";
  boolean confirm = false;
  String id;
    public FileManipulator(String channelName) {
        super(channelName);
        helpEmbed = new HelpEmbed(COMMAND, 
                "Remove all matches from the schedule.");
 
    }

    @Override
    public void handle(ReceivedMessage event) {
        String messageContent = event.getMessageContent().toLowerCase();
        
      
        if(confirm) {
        	confirm = false;
            //respond to message here
        	if(id.equals(event.getEvent().getAuthor().getId())) {
        		if(messageContent.equals("y")||messageContent.equals("yes")) {
        			event.sendResponse("Schedule cleared.");
        		MatchThinker.Schedule.clear();
        		MatchThinker.saveScheduleToFile();
        		id=null;
        		}
        	else {
        		event.sendResponse("Deletion aborted.");
        	}
        }
        		}
        else if(messageContent.contains(COMMAND)) {
            //respond to message here
        		confirm = true;
        		event.sendResponse("Are you _sure_ you want to remove all matches from the schedule?");
        		id=event.getEvent().getAuthor().getId();
        		}
        	}
        
    }


