package akka;

public class CountReturnMessage {
	private int count;
	
	public CountReturnMessage(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
