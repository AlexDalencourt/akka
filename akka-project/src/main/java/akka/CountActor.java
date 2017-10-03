package akka;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CountActor extends AbstractActor {
	
	static public Props props() {
		return Props.create(CountActor.class, () -> new CountActor());
	}
	
	private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(TextToParseMessage.class, message -> {log.info(message.getTextToParse());}).build();
	}

}
