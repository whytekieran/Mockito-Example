package com.whyte.powermock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitPlatform.class)
@PrepareForTest({ CollaboratorWithFinalMethods.class })
class CollaboratorWithFinalMethodsPowerMock {

	@Test
	public void test() throws Exception {
		CollaboratorWithFinalMethods mock = PowerMockito.mock(CollaboratorWithFinalMethods.class);
		PowerMockito.whenNew(CollaboratorWithFinalMethods.class).withNoArguments().thenReturn(mock);
		
		CollaboratorWithFinalMethods collaborator = new CollaboratorWithFinalMethods();
		PowerMockito.verifyNew(CollaboratorWithFinalMethods.class).withNoArguments();
		
		PowerMockito.when(collaborator.helloMethod()).thenReturn("Hello Baeldung!");
		String welcome = collaborator.helloMethod();
		
		verify(collaborator).helloMethod();
		assertEquals("Hello Baeldung!", welcome);

	}

}
