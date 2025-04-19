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
	//channel.sendMessage("loading saved file...").submit().join();
	if(MatchThinker.loadFile(channel)) {
		
		//channel.sendMessage("file loaded!").submit().join();
		//channel.sendMessage(MatchThinker.Schedule.toString()).submit().join();
	} else {
		channel.sendMessage("file load error").submit().join();
		MatchThinker.Schedule.clear();
	}
	while(true) {
		long currentTime = new Date(System.currentTimeMillis()).getTime();
		//channel.sendMessage("checking for matches that time is up for...").submit().join();
		

	//	Calendar cal = new Calendar.Builder().
		for(int i = 0; i<MatchThinker.Schedule.size();i++) {
			if(MatchThinker.Schedule.get(i).date<currentTime) {
				//match time is earlier than current time
				//ping em boys
				
				String message = "Time for "+ MatchThinker.Schedule.get(i).getTitle()+"!!! \n"+ MatchThinker.Schedule.get(i).getRosterNotify();
				channel.sendMessage(message).submit().join();
				channel.sendMessageEmbeds(MatchThinker.Schedule.get(i).getEmbed(0)).submit().join();
				//why doesnt it send the embed...
				//is code stopping here?
				MatchThinker.Schedule.remove(i);
				i--;
				MatchThinker.saveScheduleToFile();
				channel.sendMessage("fine").submit().join();
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
