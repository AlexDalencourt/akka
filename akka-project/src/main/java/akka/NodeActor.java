package akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

/**
 * Divise le text en deux et l'envoie à ses sous entité attend enfin le retour des sous entités.
 * 
 * @author dalencourt
 *
 */
public class NodeActor extends AbstractActor {

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
							right.tell(new TextToParseMessage(message.getRightPart(), message.getPatternToMatch()), getSelf());
							rightReturn = true;
						})
				.match(CountReturnMessage.class, 
						message -> {
							if(getSender() == left) {
								leftReturn = true;
							}else {
								rightReturn = true;
							}
							count += message.getCount();
							if(!leftReturn && !rightReturn) {
								sender.tell(new CountReturnMessage(count), getSelf());
							}
				}).build();
	}

}
