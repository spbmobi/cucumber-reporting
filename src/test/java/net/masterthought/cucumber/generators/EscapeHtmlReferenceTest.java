package net.masterthought.cucumber.generators;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.junit.Test;

/**
 * @author M.P. Korstanje (mpkorstanje@github)
 */
public class EscapeHtmlReferenceTest {

    private static final String SOME_REFERENCE = "someReference";
    private final ReferenceInsertionEventHandler insertionEventHandler = new EscapeHtmlReference();

    @Test
    public void referenceInsert_returnNormalText(){
        String normalText = "a plain statement";
        assertThat(insertionEventHandler.referenceInsert(SOME_REFERENCE, normalText)).isEqualTo(normalText);
    }

    @Test
    public void referenceInsert_shouldEscapeHtmlForAnyLabel(){
        String html = "<b>a bold statement</b>";
        assertThat(insertionEventHandler.referenceInsert(SOME_REFERENCE, html)).isEqualTo(escapeHtml(html));
    }

    @Test
    public void referenceInsert_shouldReturnNullForNull(){
        assertThat(insertionEventHandler.referenceInsert(SOME_REFERENCE, null)).isNull();
    }

    @Test
    public void referenceInsert_shouldSanitize(){
        String html = "<a href=\"www.example.com\" rel=\"nofollow noopener noreferrer\">a hyper web reference</a>";
        String img = "<img src=\"screenshot.png\" />";
        String span = "<span style=\"background-color: #66CCEE;\">some text</span>";

        assertThat(insertionEventHandler.referenceInsert("$_sanitize_" + SOME_REFERENCE, html))
                .isEqualTo(html);
        assertThat(insertionEventHandler.referenceInsert("$_sanitize_" + SOME_REFERENCE, img))
                .isEqualTo(img);
        assertThat(insertionEventHandler.referenceInsert("$_sanitize_" + SOME_REFERENCE, span))
                .isEqualTo(span);
    }
}
