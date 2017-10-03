package akka;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CountActor extends AbstractActor {
	
	static public Props props() {
		return Props.create(CountActor.class, () -> new CountActor());
	}
	
	static public class Greeting {	
		public final String message;
		
		public Greeting(String message) {
			this.message = message;
		}
	}
	
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Greeting.class, greeting -> {log.info(greeting.message);}).build();
	}

}
