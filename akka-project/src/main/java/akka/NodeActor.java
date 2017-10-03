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
	private final ActorRef right;
	
	public NodeActor(ActorRef left, ActorRef right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder().match(arg0, arg1);
	}

}
