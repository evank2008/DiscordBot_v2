package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.TimeFormat;
import net.dv8tion.jda.api.utils.Timestamp;

public class Match{

String matchTitle;
User[] roster;
String date;
Calendar cal;
JDA jda;
Random ran;

    public Match() {
    	jda = JDABuilder.createDefault("MTMzMTQ0NzUzMjc5ODIxNDIwNA.G2"+"vfUr.qJjWqpuFj0qHoiTUb5-V1PAj7GQpnh9UUnR0_U",GatewayIntent.GUILD_MEMBERS)    			
    			.setMemberCachePolicy(MemberCachePolicy.ALL)
    			.build();
    	ran = new Random();
    }
    void setTitle(String title) {
    	this.matchTitle=title;
    }
    String getTitle() {
    	return matchTitle;
    }
    void setDate(String date) {
    	this.date=date;
    }
    String inputDate(String dateInput) {
    	try {
    	//dateInput should be in the format: "m/d/yyyy h:m" in EST
    	String[] splitDate = dateInput.split("/|\\ |\\:");
    	if(splitDate.length!=5) {
    		String s = "Full date not entered, or too much date entered We got: \n";
    		for(String se:splitDate) {
    			s+=se+"\n";
    		}
    		return s;
    	}
    	int[] dateInts = new int[5];
    	for(int i=0; i<5;i++) {
    		dateInts[i]=Integer.parseInt(splitDate[i]);
    	}
    	//set the date to whatev
    	 cal = new Calendar.Builder()
    			 .setTimeZone(TimeZone.getTimeZone("EST"))
    			 .build();
     	//cal.set(2025, 12, 31, 15, 30);

     	cal.set(dateInts[2], dateInts[0]-1, dateInts[1], dateInts[3], dateInts[4]);
    	return "1";
    	} catch(Exception e) {
    		return e.getMessage();
    	}
    }
    String getDate() {
    	return "<t:"+cal.getTimeInMillis()/1000+">";
    }
    String setRoster(String[] roster) {
    	//string is of user ids
    	
    	 this.roster = new User[roster.length];
    	for(int i = 0;i<roster.length;i++)  {
    		try {
    			this.roster[i]=jda.retrieveUserById(roster[i]).submit().get();
    			
    		//this.roster[i]=jda.getUserById(roster[i]);
    		} catch(Exception e) {
    			this.roster=null;
    			return e.getMessage();
    		}
    	}
    	return "1";
    	
    }
    User[] getRoster() {
    	return roster;
    }
    String getPrint() {
    	String statement = "**"+matchTitle+"** \n \n**Date:** "+this.getDate()+" \n \n**Players:** \n";
		String se = "";
		for(User u: roster) {
			se+=(u.getName()+" \n");
		}
		statement+=se;
		return statement;
    }

    MessageEmbed getEmbed() {
    	String statement = "**Date:** "+this.getDate()+" \n \n**Players:** \n";
		String se = "";
		for(User u: roster) {
			se+=(u.getName()+" \n");
		}
		statement+=se;
    	return new EmbedBuilder().setColor(new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256)))
    	.setTitle(matchTitle)
    	.setDescription(statement)
    	.build();
    }
    void save() {
    	MatchThinker.saveMatch(this);
    }
}
