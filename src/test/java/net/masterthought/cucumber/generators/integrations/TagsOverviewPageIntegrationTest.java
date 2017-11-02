package net.masterthought.cucumber.generators.integrations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.masterthought.cucumber.generators.TagsOverviewPage;
import net.masterthought.cucumber.generators.integrations.helpers.DocumentAssertion;
import net.masterthought.cucumber.generators.integrations.helpers.LeadAssertion;
import net.masterthought.cucumber.generators.integrations.helpers.SummaryAssertion;
import net.masterthought.cucumber.generators.integrations.helpers.WebAssertion;

/**
 * @author Damian Szczepanik (damianszczepanik@github)
 */
public class TagsOverviewPageIntegrationTest extends PageTest {

    @Test
    public void generatePage_generatesTitle() {

        // given
        setUpWithJson(SAMPLE_JSON);
        page = new TagsOverviewPage(reportResult, configuration);
        final String titleValue = String.format("Cucumber Reports  - Tags Overview",
                configuration.getBuildNumber());

        // when
        page.generatePage();

        // then
        DocumentAssertion document = documentFrom(page.getWebPage());
        String title = document.getHead().getTitle();

        assertThat(title).isEqualTo(titleValue);
    }

    @Test
    public void generatePage_generatesLead() {

        // given
        setUpWithJson(SAMPLE_JSON);
        page = new TagsOverviewPage(reportResult, configuration);

        // when
        page.generatePage();

        // then
        DocumentAssertion document = documentFrom(page.getWebPage());
        LeadAssertion lead = document.getLead();

        assertThat(lead.getHeader()).isEqualTo("Tags Statistics");
        assertThat(lead.getDescription()).isEqualTo("The following graph shows passing and failing statistics for tags");
    }

    @Test
    public void generatePage_generatesCharts() {

        // given
        setUpWithJson(SAMPLE_JSON);
        page = new TagsOverviewPage(reportResult, configuration);

        // when
        page.generatePage();

        // then
        DocumentAssertion document = documentFrom(page.getWebPage());

        assertThat(document.byId("charts", WebAssertion.class)).isNotNull();
    }

    @Test
    public void generatePage_insertsChartData() {

        // given
        setUpWithJson(SAMPLE_JSON);
        page = new TagsOverviewPage(reportResult, configuration);

        // when
        page.generatePage();

        // then
        DocumentAssertion document = documentFrom(page.getWebPage());

        // check that data used by the charts is correctly inserted into the script section

        assertThat(document.html()).contains("labels:  [ \"@checkout\",  \"@fast\",  \"@featureTag\", ]");
        assertThat(document.html()).contains("data:  [ 62.50,  100.00,  100.00, ]");
        assertThat(document.html()).contains("data:  [ 6.25,  0.00,  0.00, ]");
        assertThat(document.html()).contains("data:  [ 12.50,  0.00,  0.00, ]");
        assertThat(document.html()).contains("data:  [ 6.25,  0.00,  0.00, ]");
        assertThat(document.html()).contains("data:  [ 12.50,  0.00,  0.00, ]");

    }

    @Test
    public void generatePage_onJsonWithoutTags_generatesProperMessage() {

        // given
        setUpWithJson(SIMPLE_JSON);
        page = new TagsOverviewPage(reportResult, configuration);

        // when
        page.generatePage();

        // then
        DocumentAssertion document = documentFrom(page.getWebPage());
        SummaryAssertion summary = document.getReport();
        assertThat(summary.getEmptyReportMessage()).isEqualTo("You have no tags in your cucumber report");
    }
}
