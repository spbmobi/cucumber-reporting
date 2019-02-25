package net.masterthought.cucumber.generators;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportResult;

public class SpotBugsOverviewPage extends AbstractPage{
	public static final String WEB_PAGE = "overview-spotbugs.html";

	public SpotBugsOverviewPage(ReportResult reportResult, Configuration configuration) {
		super(reportResult, "overviewSpotBugs.vm", configuration);
	}

	@Override
	public String getWebPage() {
		return WEB_PAGE;
	}

	@Override
	public void prepareReport() {
		context.put("all_bugs", reportResult.getAllBugInstances());
		context.put("file_stats", reportResult.getFileStats());
	}
}
