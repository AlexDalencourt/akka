package akka;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.CountActor;
import akka.actor.NodeActor;
import akka.actor.RootActor;
import akka.message.TextToParseMessage;

public class Process {
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("AKKA");
		final Scanner scan = new Scanner(System.in);
		
		System.out.print("Choisir le nombre de processus de comptage à utiliser : ");
		int nb_proccess = new Integer(scan.nextLine());

		List<ActorRef> actors = new ArrayList<>();
		for(int i = 0; i < nb_proccess; i++) {
			actors.add(system.actorOf(CountActor.props(),"CountActor" + i));
		}
		
		while(actors.size() > 1) {
			ActorRef left = actors.remove(0);
			ActorRef right = actors.remove(0);
			System.out.println("Fusion : " + left.path().name() + " - " + right.path().name());
			actors.add(system.actorOf(NodeActor.props(left, right)));
			System.out.println("Node créée " + actors.get(actors.size() - 1).path().name());
		}
		ActorRef root = system.actorOf(RootActor.props(actors.get(0)), "root");
		
		System.out.println("Insérer le message à parser : ");
		String message = scan.nextLine();
		System.out.println("Insérer le motif à rechercher (peut être une regex) : ");
		String pattern = scan.nextLine();
		
		root.tell(new TextToParseMessage(message, pattern), ActorRef.noSender());
		
		System.out.println("Appuyer sur ENTRER pour terminer le programme");
		scan.nextLine();
		
		scan.close();
		system.terminate();
	}
}
