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

public class KysTest {
	String testChannel = "test";
Kys kys = new Kys(testChannel);

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}
@Mock
private ReceivedMessage receivedMessage;

@Test
void DontCloseWithoutCommand() {
	String command = "onion live";
	
	when(receivedMessage.getMessageContent()).thenReturn(command);
	
	kys.handle(receivedMessage);
	
	verify(receivedMessage, times(0)).sendResponse("killing self...");
}

}
