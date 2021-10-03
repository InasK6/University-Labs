package forum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ForumProtege extends ForumDecorator {

	private Collection<String> liste=new ArrayList<String>();
	public ForumProtege(IForum f, Collection<String> interdits) {
		super(f);
		liste=interdits;
	}
	
	@Override
	public boolean posterMessage(String auteur, String message) {
		for(String s: liste) {
			if(message.contains(s)) {
				return false;
			}
		}
		return super.posterMessage(auteur, message);
	}

}
