package akka.actor;

import org.apache.commons.lang3.StringUtils;

import akka.message.CountReturnMessage;
import akka.message.TextToParseMessage;

public class CountActor extends AbstractActor {

	private CountReturnMessage count;

	static public Props props() {
		return Props.create(CountActor.class, () -> new CountActor());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(TextToParseMessage.class, message -> {
			String reception = message.getTextToParse();
			String parse = message.getPatternToMatch();
			count = new CountReturnMessage(StringUtils.countMatches(reception, parse));
			getSender().tell(new CountReturnMessage(count.getCount()), getSelf());
		}).build();
	}

}
