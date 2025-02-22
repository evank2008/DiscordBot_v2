package org.jointheleague.features.student.first_feature;

import java.util.Calendar;

public class PingChecker extends Thread{
	
	//every [time] check the time/date
	//iterate through each match, if it's time, ping everyone and delete the match or move it to past matches
public PingChecker() {
	
}
@Override
public void run() {
	
	while(true) {
		
	//	Calendar cal = new Calendar.Builder().
		for(Match m: MatchThinker.Schedule) {
			
			
		}

	try {
		sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
}
