package org.jointheleague.features.student.first_feature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;


public class MatchThinker {
	static File scheduleFile = new File("src/main/java/org/jointheleague/features/student/first_feature/schedule.txt");
	static FileWriter fw;
	static FileReader fr;
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

static boolean saveScheduleToFile(){
	//System.getProperty("user.dir");
	
	try {
		fw = new FileWriter(scheduleFile,false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	try {
		String strong = "";
		for(int i = 0; i<Schedule.size();i++) {
			strong+=(Schedule.get(i).Serialize()+"\n");
			//fw.flush();
		}
		fw.append(strong);
		fw.flush();
		fw.close();
		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}

	}
static boolean loadFile(TextChannel mcu) {

	
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
					Match m = new Match(mcu).Deserialize(s);
					Schedule.add(m);
					mcu.sendMessage("added match "+m.getTitle()).submit().join();
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
}
