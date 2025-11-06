package es.grise.upm.profundizacion.subscriptionService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriptionServiceTest {

	SubscriptionService subscriptionService;
	List<User> users;

	@BeforeEach
	void setUp() {
		users = new ArrayList<>();
		subscriptionService = spy(new SubscriptionService());
		when(subscriptionService.getSubscribers()).thenReturn(users);
	}

	@Test
	void addNullUserTest() {
		assertThrows(NullUserException.class, () -> subscriptionService.addSubscriber(null));
	}

	@Test
	void addValidUserWithEmailTest() {
		User user = mock(User.class);
		when(user.getDelivery()).thenReturn(Delivery.DO_NOT_DELIVER);
		when(user.getEmail()).thenReturn("example@example.com");
		assertDoesNotThrow(() -> {subscriptionService.addSubscriber(user);});
		assertEquals(1, users.size());
		assertTrue(users.contains(user));
	}

	@Test
	void addValidUserWithoutEmailTest() {
		User user = mock(User.class);
		when(user.getDelivery()).thenReturn(Delivery.LOCAL);
		when(user.getEmail()).thenReturn(null);
		assertDoesNotThrow(() -> subscriptionService.addSubscriber(user));
		assertEquals(1, users.size());
		assertTrue(users.contains(user));
	}

	
	@Test
	void addDuplicateUserTest() {
		User user = mock(User.class);
		when(user.getDelivery()).thenReturn(Delivery.LOCAL);
		when(user.getEmail()).thenReturn(null);
		assertDoesNotThrow(() -> subscriptionService.addSubscriber(user));
		assertThrows(ExistingUserException.class, () -> subscriptionService.addSubscriber(user));
	}

	
	@Test
	void addLocalUserWithEmailTest() {
		User user = mock(User.class);
		when(user.getDelivery()).thenReturn(Delivery.LOCAL);
		when(user.getEmail()).thenReturn("example@example.com");
		assertThrows(LocalUserDoesNotHaveNullEMailException.class, () -> subscriptionService.addSubscriber(user));
	}
}
