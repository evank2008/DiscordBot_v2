package org.jointheleague.features.student.first_feature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;


public class MatchThinker {
	File scheduleFile = new File("src/main/java/org/jointheleague/features/student/first_feature/schedule.json");
	FileWriter fw;
	FileReader fr;
static LinkedList<Match> Schedule = new LinkedList<Match>();
//todo: save to file
public MatchThinker() {
	
}
static void saveMatch(Match match) {
	//sort it based on match.cal.getTimeInMillis(), earliest to latest
	if(Schedule.isEmpty()) {
		Schedule.add(match);
	} else {
		boolean added = false;
		for(int i = 0; i<Schedule.size()&&!added;i++) {
			//assume schedule is already sorted
			if(Schedule.get(i).cal.getTimeInMillis()>match.cal.getTimeInMillis()) {
				Schedule.add(i, match);
				added=true;
			}
		}
		if(!added) {
			Schedule.add(match);
		}
	}
}

boolean saveScheduleToFile(){
	//System.getProperty("user.dir");
	//todo: this
	/* 1: find directory the project is in
	 * 2: save the schedule to file
	 * 3: confirmation message maybe
	 * 
	 */
	if(fw==null) {
	try {
		fw = new FileWriter(scheduleFile,true);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
	try {
		fw.write("\n");
		
		for(int i = 0; i>Schedule.size();i++) {
			fw.append(Schedule.get(i).Serialize()+"\n");
		}
		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
boolean loadFile(MessageChannelUnion mcu) {
	if(fr==null) {
		try {
			fr=new FileReader(scheduleFile);
			BufferedReader br = new BufferedReader(fr);
			Schedule = new LinkedList<Match>();
			while(true) {
				String s = br.readLine();
				if(s==null) {
					return true;
				}
				else {
					Schedule.add(new Match(mcu).Deserialize(s));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	return false;
}
}
