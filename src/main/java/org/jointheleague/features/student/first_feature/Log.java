package org.jointheleague.features.student.first_feature;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.jointheleague.api_wrapper.ReceivedMessage;

public class Log {
FileWriter fw;
	public Log(String message, ReceivedMessage event){
		String date = new Date(System.currentTimeMillis()).toString();
	
		try {
			fw = new FileWriter("src/main/java/org/jointheleague/features/student/first_feature/logs.txt", true);
		
		fw.write(date+": "+event.getEvent().getAuthor().getName()+" "+message+"\n");
		fw.flush();
	} catch (IOException e) {
		System.exit(0);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
