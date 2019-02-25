package net.masterthought.cucumber.xml.deserializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;

import net.masterthought.cucumber.xml.BugInstance;
import net.masterthought.cucumber.xml.FileStats;

public class SpotBugsDeserializer {

	public static List<BugInstance> deserializeBugsReport(String xmlFile) {
		try {
			File inputFile = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("file");

			List<BugInstance> bugInstances = new ArrayList<>();

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				NodeList elements = ((DeferredElementImpl) nNode).getElementsByTagName("BugInstance");

				for (int j = 0; j < elements.getLength(); j++) {
					NamedNodeMap attributes = elements.item(j).getAttributes();

					bugInstances.add(new BugInstance(
							((DeferredElementImpl) nNode).getAttribute("classname"),
							attributes.getNamedItem("type").getTextContent(),
							attributes.getNamedItem("category").getTextContent(),
							attributes.getNamedItem("message").getTextContent(),
							attributes.getNamedItem("lineNumber").getTextContent()
					));
				}
			}

			return bugInstances;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<FileStats> deserializeFullReport(String xmlFile) {
		try {
			File inputFile = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("FileStats");

			List<FileStats> fileStats = new ArrayList<>();

			for (int i = 0; i < nList.getLength(); i++) {
				NamedNodeMap attributes = nList.item(i).getAttributes();
				fileStats.add(new FileStats(
						attributes.getNamedItem("path").getTextContent(),
						attributes.getNamedItem("size").getTextContent(),
						attributes.getNamedItem("bugCount").getTextContent()
				));
			}

			return fileStats;
		} catch (Exception e) {
			return null;
		}
	}
}
