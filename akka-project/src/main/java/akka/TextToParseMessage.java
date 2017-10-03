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
	
	public String getLeftPart() {
		return textToParse.substring(0, textToParse.length()/2);
	}
	
	public String getRightPart() {
		return textToParse.substring(textToParse.length()/2);
	}
}
