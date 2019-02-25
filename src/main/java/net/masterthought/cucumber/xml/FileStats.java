package net.masterthought.cucumber.xml;

public class FileStats {
	private String path;
	private String size;
	private String bugCount;

	public FileStats(String path, String size, String bugCount) {
		this.path = path;
		this.size = size;
		this.bugCount = bugCount;
	}

	public String getPath() {
		return path;
	}

	public String getSize() {
		return size;
	}

	public String getBugCount() {
		return bugCount;
	}
}
