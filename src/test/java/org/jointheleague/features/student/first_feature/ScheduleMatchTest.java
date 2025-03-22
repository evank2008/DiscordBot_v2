package org.jointheleague.features.student.first_feature;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ScheduleMatchTest {
	String testChannel = "test";
	TextChannel tc;
ScheduleMatch sm = new ScheduleMatch(testChannel,tc);

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    sm.checker=new PingChecker(null);
}
@Mock
private ReceivedMessage receivedMessage;
@Mock
private User user;
@Mock
private MessageReceivedEvent event;

@Test
void RespondWithCorrectPrompt() {
	String command = "onion schedulematch";

	when(receivedMessage.getMessageContent()).thenReturn(command);
	when(receivedMessage.getEvent()).thenReturn(event);
	when(event.getAuthor()).thenReturn(user);
	when(user.getName()).thenReturn("onionsondis_cord");
	when(user.getId()).thenReturn("504080869384912906");

	
	sm.handle(receivedMessage);
	
	verify(receivedMessage, times(1)).sendResponse("Alright onionsondis_cord, let's schedule a match. Enter the date and time(m/d/yyyy h:mm) in EST Military Format.");

	when(receivedMessage.getMessageContent()).thenReturn("12/14/2022 5:30");
	sm.handle(receivedMessage);
	verify(receivedMessage, times(1)).sendResponse("Enter the list of people playing by listing each person's user ID, separated by spaces");

}
@Test
void dontRespondToWrongId() {
	when(receivedMessage.getMessageContent()).thenReturn("onion schedulematch");
	when(receivedMessage.getEvent()).thenReturn(event);
	when(event.getAuthor()).thenReturn(user);
	when(user.getName()).thenReturn("onionsondis_cord");
	when(user.getId()).thenReturn("504080869384912906");
	
	sm.handle(receivedMessage);
	when(user.getId()).thenReturn("ploob");
	when(receivedMessage.getMessageContent()).thenReturn("12/14/2022 5:30");
	sm.handle(receivedMessage);
	verify(receivedMessage, times(0)).sendResponse("Enter the list of people playing by listing each person's user ID, separated by spaces");
//since id is different it shouldnt respond

	
}
@Test
void QuickScheduleCorrectly() {
	

	when(receivedMessage.getMessageContent()).thenReturn("onion schedulematch; 3/18/2050 5:00; 504080869384912906; grosh");
	when(receivedMessage.getEvent()).thenReturn(event);
	when(event.getAuthor()).thenReturn(user);
	when(user.getName()).thenReturn("onionsondis_cord");
	when(user.getId()).thenReturn("504080869384912906");

	
	sm.handle(receivedMessage);
	
	verify(receivedMessage, times(1)).sendResponse("Match scheduled.");
}
@Test
void LongScheduleCorrectly() {
	

	when(receivedMessage.getMessageContent()).thenReturn("onion schedulematch");
	when(receivedMessage.getEvent()).thenReturn(event);
	when(event.getAuthor()).thenReturn(user);
	when(user.getName()).thenReturn("onionsondis_cord");
	when(user.getId()).thenReturn("504080869384912906");

	
	sm.handle(receivedMessage);
	when(receivedMessage.getMessageContent()).thenReturn("3/18/2050 5:00");
	sm.handle(receivedMessage);
	when(receivedMessage.getMessageContent()).thenReturn("504080869384912906");
	sm.handle(receivedMessage);
	when(receivedMessage.getMessageContent()).thenReturn("grosh");
	sm.handle(receivedMessage);
//onion schedulematch; ; 504080869384912906; grosh
	
	verify(receivedMessage, times(1)).sendResponse("Match scheduled.");
}

}
