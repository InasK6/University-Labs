package state;

public abstract class AbstractState implements State{
	private WatchWithState watch;
	
	public AbstractState(WatchWithState w) {
		watch=w;
	}
	
	public WatchWithState getWatch() {
		return watch;
	}
	
	
}
