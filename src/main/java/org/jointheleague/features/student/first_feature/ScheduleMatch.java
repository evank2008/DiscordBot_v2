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
    MatchThinker bigThinker = new MatchThinker();
    PingChecker checker;
    public ScheduleMatch(String channelName) {
        super(channelName);

        //Create a help embed to describe feature when !help command is sent
        helpEmbed = new HelpEmbed(COMMAND, "Add a match to the schedule. Input date/time, players, and match title. \n for quick input, use the following sytax: \n onion schedulematch; date time; players; matchTitle");
    }

    @Override
    public void handle(ReceivedMessage event) {
    	if(checker==null) {
    		checker=new PingChecker(event.getEvent().getChannel());
    		checker.start();
    	}
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
        		match = new Match(event.getEvent().getChannel());
        		String inp = match.inputDate(messageContent);
        		if(!inp.equals("1")) {
        			event.sendResponse("Invalid date: "+inp);
        			scheduleInProgress=false;
        			scheduleProgress=0;
        		} else {
        		//date
        		event.sendResponse("Enter the list of people playing by listing each person's user ID, separated by spaces");
        		scheduleProgress++;
        		}
        		break;
        	case 1:
        		//add the player list something
        		String[] idSplit = messageContent.split(" ");
        		//event.sendResponse("setRoster loading...");
        		String set = match.setRoster(idSplit);
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
        		MatchThinker.saveMatch(match);
        		scheduleProgress=0;
        		userId=null;
        		event.sendResponse("Match scheduled.");
        		event.sendResponse(match.getEmbed());
        		break;
        	
        	}
        }else if(event.getMessageContent().split("; ").length>=4&&event.getMessageContent().substring(0, 20).equals("onion schedulematch;")) {
        	event.sendResponse("quick shedule detected.");
        	String[] splitMessage = event.getMessageContent().split("; ");
        	//example command: onion schedulematch; date; roster; matchName
        	String[] idSplit = splitMessage[2].split(" ");
        	match=new Match(event.getEvent().getChannel());
        	
        	String rost = match.setRoster(idSplit);
        	String date = match.inputDate(splitMessage[1]);
        	if(date.equals("1")&&rost.equals("1")) {
        	
        		match.setTitle(splitMessage[3]);
            	MatchThinker.saveMatch(match);
            	event.sendResponse("Match scheduled.");
        		event.sendResponse(match.getEmbed());
        	} else {
        		event.sendResponse("error: \ndate: "+date+" \nroster: "+rost);
        	}
        	
        } else if (event.getMessageContent().substring(0, 20).equals("onion schedulematch;")) {
        	event.sendResponse("Incorrect format. \nWomp womp.");
        }
        
    }

}
