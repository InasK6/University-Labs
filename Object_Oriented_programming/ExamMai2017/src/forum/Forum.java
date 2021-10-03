package forum;

import java.util.LinkedList;
import java.util.List;

public class Forum implements IForum {

	private List<IChatter> participants=new LinkedList<>();
	//private List<String> messages=new LinkedList<>();
	private String name;
	public Forum( String n) {
		name=n;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean posterMessage(String auteur, String message) {
		for(IChatter c: participants) {
			c.messageRecu(name, auteur, message);
			
		}
		return true;
	}

	@Override
	public boolean rejoindre(IChatter chatter) {
		// TODO Auto-generated method stub
		if(participants.contains(chatter)) {
			return false;
		}
		participants.add(chatter);
		return true;
	}

	@Override
	public int nbParticipants() {
		// TODO Auto-generated method stub
		return participants.size();
	}

	@Override
	public boolean quitter(IChatter chatter) {
		// TODO Auto-generated method stub
		return participants.remove(chatter);
	}

}
