package org.jointheleague.features.student.first_feature;

import java.util.LinkedList;

public class MatchThinker {
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

void saveScheduleToFile() {
	//todo: this
}
}
