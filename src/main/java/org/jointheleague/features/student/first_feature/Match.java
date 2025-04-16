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
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.TimeFormat;
import net.dv8tion.jda.api.utils.Timestamp;

public class Match{
//make an array of ids and an array of names?
String[] idRoster;
String[] nameRoster;
//MessageChannelUnion channel;
TextChannel channel;
String matchTitle;
long date;
Calendar cal;
JDA jda;
String extra=" ";
Color color;

    public Match(TextChannel chan) {
    	jda = JDABuilder.createDefault("MTMzMTQ0NzUzMjc5ODIxNDIwNA.G2"+"vfUr.qJjWqpuFj0qHoiTUb5-V1PAj7GQpnh9UUnR0_U",GatewayIntent.GUILD_MEMBERS)    			
    			.setMemberCachePolicy(MemberCachePolicy.ALL)
    			.build();
    	Random ran = new Random();
    	color = new Color(ran.nextInt(256),ran.nextInt(256),ran.nextInt(256));
    	channel=chan;
    }
    void alert() {
    	String message="Get ready for "+matchTitle+"!!! \n"+getRosterNotify();
    	
    	channel.sendMessage(message).submit().join();
    }
    void setTitle(String title) {
    	this.matchTitle=title;
    }
    String getTitle() {
    	return matchTitle;
    }
    void setDate(long date) {
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
     	date=cal.getTimeInMillis();
    	return "1";
    	} catch(Exception e) {
    		return e.getMessage();
    	}
    }
    String getDate() {
    	return "<t:"+date/1000+">";
    }
    String setRoster(String[] roster) {
    	//string is of user ids
    	idRoster = roster;
    	nameRoster = new String[roster.length];
    	for(int i = 0;i<roster.length;i++)  {
    		try {
    			this.nameRoster[i]=jda.retrieveUserById(roster[i]).submit().get().getEffectiveName();
    		//this.roster[i]=jda.getUserById(roster[i]);
    		} catch(Exception e) {
    			nameRoster=null;
    			idRoster=null;
    			return e.getMessage();
    		}
    	}
    	return "1";
    	
    }
    String[] getNameRoster() {
    	return nameRoster;
    }
    String[] getIdRoster() {
    	return idRoster;
    }
    void addExtra(String ne) {
    	extra+="\n"+ne+"\n";
    }
    void clearExtra() {
    	extra="";
    }
    String getRosterNotify() {
    	String s="";
    	for(String e: idRoster) {
    		s+="<@"+e+">\n";
    	}
    	return s;
    }
    String getPrint() {
    	String statement = "**"+matchTitle+"** \n \n**Date:** "+this.getDate()+" \n \n**Players:** \n";
		String se = "";
		for(String e: nameRoster) {
			se+=(e+" \n");
		}
		statement+=se+extra;
		return statement;
    }

    MessageEmbed getEmbed(int type) {
    	//0 for regular, 1 for nav
    	String statement = "**Date:** "+this.getDate()+" \n \n**Players:** `\n";
		String se = "";
		for(String e: nameRoster) {
			se+=(e+" \n");
		}
		statement+=se+"`"+extra;
		MessageEmbed embed=null;
		switch (type) {
		case 0:
			embed = new EmbedBuilder().setColor(color)
	    	.setTitle(matchTitle)
	    	.setDescription(statement)
	    	//.setFooter("Type ''left'' or ''right'' to navigate. Then ''quit'' once finished.")   	
	    	.build();
			break;
		case 1:
			embed = new EmbedBuilder().setColor(color)
	    	.setTitle(matchTitle)
	    	.setDescription(statement)
	    	.setFooter("Type ''left'' or ''right'' to navigate. Then ''quit'' once finished.")   	
	    	.build();
			break;
		}
    	return embed;
    }
    void save() {
    	MatchThinker.saveMatch(this);
    }
    String Serialize() {
    	String div = " aaaa ";
    	String div2 = " bbbb ";
    	/*
    	 * title
    	 * date
    	 * extra
    	 * color
    	 * idroster
    	 * nameroster
    	 */
    	
    	//extra div color div idroster1 dvi2 idroster2 div2 idroster3 div nameroster1 div2 nameroster2 div2 nameroster3 div
    	String fin = "";
    	fin+=matchTitle+div;
    	fin+=date+div;
    	fin+=extra+div;
    	fin+=color.getRGB()+div;
    	
    	String idr = "";
    	for(int i = 0; i<idRoster.length;i++) {
    		idr+=idRoster[i];
    		idr+=div2;
    	}
    	String nmr = "";
    	for(int i = 0; i<nameRoster.length;i++) {
    		nmr+=nameRoster[i];
    		nmr+=div2;
    	}
    	
    	fin+=idr+div;
    	fin+=nmr;
    	return fin;
    }
    static Match Deserialize(String fin, TextChannel tcn) {
    	String div = " aaaa ";
    	String div2 = " bbbb ";
    	Match m = new Match(tcn);
    	/*
    	 * title
    	 * date
    	 * extra
    	 * color
    	 * idroster
    	 * nameroster
    	 */
    	String[] datas = fin.split(div);
    	m.matchTitle=datas[0];
    	
    	m.date=Long.parseLong(datas[1]);
    	m.extra=datas[2];
    	m.color = new Color(Integer.parseInt(datas[3]));
    	String[] idr = datas[4].split(div2);
    	String[] nmr = datas[5].split(div2);
    	m.idRoster=idr;
    	m.nameRoster=nmr;
    	return m;
    }
}
