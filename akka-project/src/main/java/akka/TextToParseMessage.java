package akka;

public class TextToParseMessage {
	private String textToParse;
	
	private String patternToMatch;
	
	public TextToParseMessage(String textToParse, String patternToMatch) {
		this.textToParse = textToParse;
		this.patternToMatch = patternToMatch;
	}

	public String getPatternToMatch() {
		return patternToMatch;
	}
	
	public String getTextToParse() {
		return textToParse;
	}
}
