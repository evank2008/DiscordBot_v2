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

public class SerializationTest {
	
    private final String testChannelName = "test";
    Match m;

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
    void SerializedMatchShouldEqualMatch() {
        m=new Match(mcu);
        m.inputDate("2/2/2028 6:35");
        String[] st = {"504080869384912906"};
        m.setRoster(st);
        m.setTitle("testTitle");
        
        Match mTest = m.Deserialize(m.Serialize());
        assertEquals(m,mTest);
        }
    
}