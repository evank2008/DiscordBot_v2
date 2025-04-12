package org.jointheleague.features.student.first_feature;

import java.util.Calendar;
import java.util.Date;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

public class PingChecker extends Thread{
	TextChannel channel;
	//every [time] check the time/date
	//iterate through each match, if it's time, ping everyone and delete the match or move it to past matches
public PingChecker(TextChannel channel) {
	this.channel=channel;
}
@Override
public void run() {
	channel.sendMessage("loading saved file...").submit().join();
	//if(MatchThinker.loadFile(channel)) {
		if(false) {
		channel.sendMessage("file loaded!").submit().join();
	} else {
		channel.sendMessage("file load error").submit().join();
		MatchThinker.Schedule.clear();
	}
	while(true) {
		long currentTime = new Date(System.currentTimeMillis()).getTime();
		channel.sendMessage("checking for matches that time is up for...").submit().join();
		

	//	Calendar cal = new Calendar.Builder().
		for(Match m: MatchThinker.Schedule) {
			if(m.date<currentTime) {
				//match time is earlier than current time
				//ping em boys
				String message = "Time for "+ m.getTitle()+"!!! \n"+ m.getRosterNotify();
				channel.sendMessage(message).submit().join();
				channel.sendMessageEmbeds(m.getEmbed(2)).submit().join();
				//should probably remove the match from schedule?
				//shoudl there be logs/records?
				//add that later idk
				MatchThinker.Schedule.remove(m);
				MatchThinker.saveScheduleToFile();
			}
			
		}

	try {
		sleep(20000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
}
