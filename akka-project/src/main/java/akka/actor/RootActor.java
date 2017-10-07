package akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.message.CountReturnMessage;
import akka.message.TextToParseMessage;

public class RootActor extends AbstractActor {

	public static Props props(ActorRef child) {
		return Props.create(AbstractActor.class, () -> new RootActor(child));
	}
	
	private final ActorRef child;
	
	public RootActor(ActorRef child) {
		this.child = child;
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TextToParseMessage.class, 
						message -> {
							child.tell(message, getSelf());
						})
				.match(CountReturnMessage.class, 
						message -> {
							System.out.println("Nombre d'occurences : " + message.getCount());
				}).build();
	}

}
