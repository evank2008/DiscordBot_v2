package org.jointheleague.features.student.first_feature;

import java.io.File;
import java.util.Random;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.abstract_classes.Feature;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;

import net.dv8tion.jda.api.utils.FileUpload;

public class Kys extends Feature {

    public final String COMMAND = "onion kys";
    File kysVid = new File("src/main/java/org/jointheleague/onionkys.mp4");
    FileUpload fu = FileUpload.fromData(kysVid);
    public Kys(String channelName) {
        super(channelName);

        //Create a help embed to describe feature when !help command is sent
        //no
    }

    @Override
    public void handle(ReceivedMessage event) {
        String messageContent = event.getMessageContent().toLowerCase();
        
        if (messageContent.contains("onion")) {
        	if(messageContent.contains("kys")||messageContent.contains("kill yourself")) {
            //respond to message here
        		if(new Random().nextInt(4)==2) {
        	event.getEvent().getChannel().sendFiles(fu).submit().join();
        		}
            event.sendResponse("killing self...");
            MatchThinker.saveScheduleToFile();
            System.exit(0);
        	}
        }
    }

}
