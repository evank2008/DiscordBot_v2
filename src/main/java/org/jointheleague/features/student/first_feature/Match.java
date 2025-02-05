package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import net.dv8tion.jda.api.utils.TimeFormat;
import net.dv8tion.jda.api.utils.Timestamp;
public class Match{

String matchTitle;
String[] roster;
String date;
Calendar cal;

    public Match() {
        
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
    	
    	//todo: figure out how to make discord timestamp
return "<t:"+cal.getTimeInMillis()/1000+">";
    }
    void setRoster(String[] roster) {
    	this.roster=roster;
    }
    String[] getRoster() {
    	return roster;
    }

}
