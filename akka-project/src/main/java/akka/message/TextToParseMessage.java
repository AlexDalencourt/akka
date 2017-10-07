package akka.message;

public class TextToParseMessage {
	private String textToParse;
	
	private String patternToMatch;
	
	private String leftPart;
	
	private String rightPart;
	
	public TextToParseMessage(String textToParse, String patternToMatch) {
		this.textToParse = textToParse;
		this.patternToMatch = patternToMatch;
		if(this.textToParse.length() > 2 * this.patternToMatch.length()){
			int idx = findIndexToCut();
			leftPart = this.textToParse.substring(0, idx);
			rightPart = this.textToParse.substring(idx);
		}
		else{
			leftPart = this.textToParse;
			rightPart = null;
		}
	}

	public String getPatternToMatch() {
		return patternToMatch;
	}
	
	public String getTextToParse() {
		return textToParse;
	}
	
	public String getLeftPart() {
		return leftPart;
	}
	
	public String getRightPart() {
		return rightPart;
	}
	
	private int findIndexToCut() {
		final int textSize = textToParse.length();
		final int patternSize = patternToMatch.length();
		
		int idx = textSize/2;
		if(textToParse.substring(idx - patternSize + 1, idx + patternSize - 2).matches(patternToMatch)){
			idx = idx + patternSize - 1;
		}
		return idx;
	}
}
