package net.masterthought.cucumber.xml;

public class BugInstance {
	private String className;
	private String type;
	private String category;
	private String message;
	private String lineNumber;

	public BugInstance(String className, String type, String category, String message, String lineNumber) {
		this.className = className;
		this.type = type.toLowerCase();
		this.category = category.toLowerCase();
		this.message = message;
		this.lineNumber = lineNumber;
	}

	public String getClassName() {
		return className;
	}

	public String getType() {
		return type;
	}

	public String getCategory() {
		return category;
	}

	public String getMessage() {
		return message;
	}

	public String getLineNumber() {
		return lineNumber;
	}
}
