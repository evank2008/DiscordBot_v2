package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Match{

String matchTitle;
String[] roster;
String date;

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
    void inputDate(String dateInput) {
    	//set the date to whatev
    }
    String getDate() {
    	return date;
    }
    void setRoster(String[] roster) {
    	this.roster=roster;
    }
    String[] getRoster() {
    	return roster;
    }

}
