package es.grise.upm.profundizacion.subscriptionService;

import java.util.Collection;

public class SubscriptionService {

	private Collection <User> subscribers;
	
	/*
	 * Constructor
	 */
	public SubscriptionService() {
		
	}

	/*
	 * Method to code
	 */
	public void addSubscriber(User user) throws NullUserException, ExistingUserException, LocalUserDoesNotHaveNullEMailException {
		if (user == null) throw new NullUserException();
		if (this.getSubscribers().contains(user)) throw new ExistingUserException();

		if(Delivery.LOCAL.equals(user.getDelivery()) && user.getEmail() != null) throw new LocalUserDoesNotHaveNullEMailException();

		this.getSubscribers().add(user);
	}
	
	/*
	 * Other setters & getters
	 */
	public Collection <User> getSubscribers() {
		
		return subscribers;
		
	}

}
