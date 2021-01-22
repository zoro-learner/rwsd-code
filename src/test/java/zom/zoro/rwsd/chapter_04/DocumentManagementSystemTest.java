package zom.zoro.rwsd.chapter_04;

import org.junit.Test;

import javax.print.Doc;

import static org.hamcrest.Matchers.hasSize;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;
import static zom.zoro.rwsd.chapter_04.Attributes.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @author lenovo
 * @create 2021-01-19 14:27
 */
public class DocumentManagementSystemTest {
    private static final String RESOURCES =
        "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private static final String LETTER = RESOURCES + "patient.letter";
    private static final String REPORT = RESOURCES + "patient.report";
    private static final String XRAY = RESOURCES + "xray.jpg";
    private static final String INVOICE = RESOURCES + "patient.invoice";
    private static final String JOE_BLOGGS = "Joe Bloggs";

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, Attributes.PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttributes() throws Exception {
        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, ADDRESS,
            "123 Fake Street\n" +
                "Westminster\n" +
                "London\n" +
                "United Kingdom");

        assertAttributeEquals(document, BODY,
            "We are writing to you to confirm the re-scheduling of your appointment\n" +
            "with Dr. Avaj from 29th December 2016 to 5th January 2017.");

        assertTypeIs("LETTER", document);
    }

    @Test
    public void shouldImportReportAttributes() throws Exception {
        system.importFile(REPORT);

        assertIsReport(onlyDocument());
    }

    @Test
    public void shouldImportImageAttributes() throws Exception {
        system.importFile(XRAY);

        final Document document = onlyDocument();

        assertAttributeEquals(document, WIDTH, "320");
        assertAttributeEquals(document, HEIGHT, "179");
        assertTypeIs("IMAGE", document);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception {
        system.importFile("gobbledygook.txt");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFile() throws Exception {
        system.importFile(RESOURCES + "unknown.txt");
    }

    private void assertIsReport(final Document document) {
        assertAttributeEquals(document, PATIENT, JOE_BLOGGS);
        assertAttributeEquals(document, BODY,
            "On 5th January 2017 I examined Joe's teeth.\n" +
                "We discussed his switch from drinking Coke to Diet Coke.\n" +
                "No new problems were noted with his teeth.");
        assertTypeIs("REPORT", document);
    }

    private void assertTypeIs(final String type, final Document document) {
        assertAttributeEquals(document, TYPE, type);
    }

    private void assertAttributeEquals(Document document, String attributeName, String expectedValue) {
        assertEquals(
            "Document has the wrong value for " + attributeName,
            expectedValue,
            document.getAttribute(attributeName)
        );
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }



}