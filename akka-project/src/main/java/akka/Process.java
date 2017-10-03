package akka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class Process {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("AKKA");
		final Scanner scan = new Scanner(System.in);
		
		int nb_proccess = 2;

		List<ActorRef> actors = new ArrayList<>();
		for(int i = 0; i < nb_proccess; i++) {
			actors.add(system.actorOf(CountActor.props(),"CountActor" + i));
		}
		
		while(actors.size() > 1) {
			ActorRef left = actors.remove(0);
			ActorRef right = actors.remove(0);
			actors.add(system.actorOf(NodeActor.props(left, right)));
		}
		ActorRef root = system.actorOf(RootActor.props(actors.get(0)));
		
		String message = scan.nextLine();
		String pattern = scan.nextLine();
		
		root.tell(new TextToParseMessage(message, pattern), ActorRef.noSender());
		
		system.terminate();
	}
}
