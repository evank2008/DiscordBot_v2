package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;
import org.jointheleague.features.student.first_feature.Match;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class ScheduleMatch extends Feature {

    public final String COMMAND = "onion schedulematch";
    boolean scheduleInProgress=false;
    int scheduleProgress=0;
    String userId;
    String userName;
    Match match;
    public ScheduleMatch(String channelName) {
        super(channelName);

        //Create a help embed to describe feature when !help command is sent
        helpEmbed = new HelpEmbed(COMMAND, "Add a match to the schedule. Input date/time, players, and match title.");
    }

    @Override
    public void handle(ReceivedMessage event) {
    	
        String messageContent = event.getMessageContent();
        
        if (messageContent.equalsIgnoreCase(COMMAND)&&!scheduleInProgress) {
        	scheduleInProgress=true;
        	scheduleProgress=0;
            //respond to message here
        	 userId = event.getEvent().getAuthor().getId();
        	 userName = event.getEvent().getAuthor().getName();
            event.sendResponse("Alright "+userName+", let's schedule a match. Enter the date and time(m/d/yyyy h:mm) in EST Military Format.");
        } else if(scheduleInProgress&&event.getEvent().getAuthor().getId().equals(userId)) {
        	//correct person
        	switch (scheduleProgress) {
        	case 0:
        		match = new Match();
        		String inp = match.inputDate(messageContent);
        		if(!inp.equals("1")) {
        			event.sendResponse(inp);
        			scheduleInProgress=false;
        			scheduleProgress=0;
        		} else {
        		//date
        		event.sendResponse("Enter the list of people playing by listing each person's user ID, separated by spaces - IN PROGRESS");
        		scheduleProgress++;
        		}
        		break;
        	case 1:
        		//add the player list something
        		String[] idSplit = messageContent.split(" ");
        		event.sendResponse("setRoster loading...");
        		String set = match.setRoster(idSplit);
        		//event.sendResponse("setRoster went through");
        		if(!set.equals("1")) {
        			event.sendResponse("Error: "+set);
        			scheduleInProgress=false;
        			scheduleProgress=0;
        		} else {
        		event.sendResponse("Enter match title");
        		scheduleProgress++;
        		}
        		break;
        	case 2:
        		match.setTitle(messageContent);
        		//add it to the list/database whatever
        		event.sendResponse("Match scheduled.");
        		scheduleInProgress=false;
        		String se = "";
        		for(User u: match.getRoster()) {
        			//se+=("<@!"+u.getId()+"> \n");
        			event.sendResponse("Adding name...");
        			se+=(u.getName()+" \n");
        		}
        		event.sendResponse("Match data: \nDate: "+match.getDate()+"\nTitle: "+match.getTitle()+"\n Players: \n"+se);
        		break;
        	
        	}
        }
        
    }

}
