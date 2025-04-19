package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;
import org.jointheleague.features.templates.FeatureTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ViewMatchTest {
	
    private final String testChannelName = "test";
    ViewMatch viewMatch = new ViewMatch(testChannelName);

    @Mock
    private ReceivedMessage receivedMessage;
    @Mock
    private User user;
    @Mock
    private MessageReceivedEvent event;

    @Mock
    private TextChannel mcu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void thisTestShouldPass() {
    	assertEquals(true,true);
    }

    @Test
    void itShouldRespondToCommand() {
        //Given
         when(receivedMessage.getEvent()).thenReturn(event);
         when(event.getAuthor()).thenReturn(user);
         when(user.getId()).thenReturn("504080869384912906");
        //When
        MatchThinker.Schedule.clear();
        when(receivedMessage.getMessageContent()).thenReturn("onion view");
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage).sendResponse("No matches scheduled.");
       
        when(receivedMessage.getMessageContent()).thenReturn("quit");

        viewMatch.handle(receivedMessage);

    }
    @Test
    void itShouldNotRespondToNoCommand() {
        //Given

        //When
        String command = "onion view"; 
        when(receivedMessage.getMessageContent()).thenReturn("ploob");
        when(receivedMessage.getEvent()).thenReturn(event);
        when(event.getAuthor()).thenReturn(user);
        when(user.getId()).thenReturn("504080869384912906");
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(0)).sendResponse("No matches scheduled.");
    }
    @Test
    void itShouldShowMatch() {
        //Given
Match m = new Match(mcu);
String[] roster = {"504080869384912906"};
m.setRoster(roster);
m.setTitle("ploob");
m.inputDate("1/1/1970 5:00");
MatchThinker.saveMatch(m);

//When
        String command = "onion view";
        when(receivedMessage.getMessageContent()).thenReturn(command);
        when(receivedMessage.getEvent()).thenReturn(event);
        when(event.getAuthor()).thenReturn(user);
        when(user.getId()).thenReturn("504080869384912906");
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(1)).sendResponse(m.getEmbed(1));
    }
    @Test
    void itShouldAddLine() {
    	Match m = new Match(mcu);
    	String[] roster = {"504080869384912906"};
    	m.setRoster(roster);
    	m.setTitle("ploob");
    	m.inputDate("1/1/1970 5:00");
    	MatchThinker.Schedule.addFirst(m);
    	when(receivedMessage.getMessageContent()).thenReturn("onion view");
        when(receivedMessage.getEvent()).thenReturn(event);
        when(event.getAuthor()).thenReturn(user);
        when(user.getId()).thenReturn("504080869384912906");
        viewMatch.handle(receivedMessage);
    	when(receivedMessage.getMessageContent()).thenReturn("add watching morbius afterwards");
        viewMatch.handle(receivedMessage);
        m.addExtra("watching morbius afterwards");
        when(receivedMessage.getMessageContent()).thenReturn("quit");
        viewMatch.handle(receivedMessage);
        when(receivedMessage.getMessageContent()).thenReturn("onion view");
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(1)).sendResponse(m.getEmbed(1));
        

    }
}
