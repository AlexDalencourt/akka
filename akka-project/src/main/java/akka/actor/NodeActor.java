package akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.message.CountReturnMessage;
import akka.message.TextToParseMessage;

/**
 * Divise le text en deux et l'envoie Ã  ses sous entitÃ© attend enfin le retour des sous entitÃ©s.
 * 
 * @author dalencourt
 *
 */
public class NodeActor extends AbstractActor {

	public static Props props(ActorRef left, ActorRef right) {
		return Props.create(AbstractActor.class, () -> new NodeActor(left, right));
	}
	
	private final ActorRef left;
	private boolean leftReturn;
	private final ActorRef right;
	private boolean rightReturn;
	private ActorRef sender;
	private int count;
	
	public NodeActor(ActorRef left, ActorRef right) {
		this.left = left;
		this.leftReturn = false;
		this.right = right;
		this.rightReturn = false;
		this.sender = null;
		this.count = 0;
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(TextToParseMessage.class, 
						message -> {
							count = 0;
							sender = getSender();
							left.tell(new TextToParseMessage(message.getLeftPart(), message.getPatternToMatch()), getSelf());
							leftReturn = true;
							String textToSend = message.getRightPart();
							if(textToSend != null){
								right.tell(new TextToParseMessage(textToSend, message.getPatternToMatch()), getSelf());
								rightReturn = true;
							}
						})
				.match(CountReturnMessage.class, 
						message -> {
							if(getSender() == left) {
								leftReturn = false;
							}else {
								rightReturn = false;
							}
							count += message.getCount();
							if(!leftReturn && !rightReturn) {
								sender.tell(new CountReturnMessage(count), getSelf());
							}
				}).build();
	}

}
