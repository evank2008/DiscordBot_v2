package org.jointheleague.features.student.first_feature;

import org.jointheleague.api_wrapper.ReceivedMessage;
import org.jointheleague.features.help_embed.plain_old_java_objects.help_embed.HelpEmbed;
import org.jointheleague.features.templates.FeatureTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ViewMatchTest {
	/*
    private final String testChannelName = "test";
    private final ViewMatch viewMatch = new ViewMatch(testChannelName);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private ReceivedMessage receivedMessage;

    @Mock
    private MessageChannelUnion mcu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void itShouldNotPrintToSystemOut() {
        String expected = "";
        String actual = outContent.toString();

        assertEquals(expected, actual);
        System.setOut(originalOut);
    }

    @Test
    void itShouldRespondToCommand() {
        //Given

        //When
        String command = "onion view";
        when(receivedMessage.getMessageContent()).thenReturn(command);
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(1)).sendResponse("No matches scheduled.");
    }
    @Test
    void itShouldNotRespondToNoCommand() {
        //Given

        //When
        String command = "onion view";
        when(receivedMessage.getMessageContent()).thenReturn("ploob");
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(0)).sendResponse("No matches scheduled.");
    }
    @Test
    void itShouldShowMatch() {
        //Given
Match m = new Match(mcu);

        //When
        String command = "onion view";
        when(receivedMessage.getMessageContent()).thenReturn(command);
        //Then
        viewMatch.handle(receivedMessage);
        verify(receivedMessage, times(1)).sendResponse("No matches scheduled.");
    }
    */
}
