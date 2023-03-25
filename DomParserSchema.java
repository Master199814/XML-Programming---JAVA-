package XMl_Parser;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParserSchema {

	public static void main(String[] args) throws Exception {
		boolean flag=true;
		try {
			validate("channel.xml","channel.xsd");
		} catch (SAXException e) {
			flag=false;
		} catch (IOException e) {
			flag=false;
		}
		//validated the xml file against Schema
		System.out.println("xml file is valid: "+flag);
		DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
		//parsing the xml document using dom parser
		Document doc=documentBuilder.parse("channel.xml");
		String authorname=doc.getElementsByTagName("name").item(0).getTextContent();
		System.out.println("Name:"+authorname);
		NodeList topicList=doc.getElementsByTagName("topic");
		for(int i=0;i<topicList.getLength();i++)
		{
			Node p=topicList.item(i);
			if(p.getNodeType()==Node.ELEMENT_NODE)
			{
				Element topic=(Element)p;
				NodeList childList=topic.getChildNodes();
				for(int j=0;j<childList.getLength();j++)
				{
					Node node=childList.item(j);
					if(node.getNodeType()==node.ELEMENT_NODE)
					{
						Element name=(Element) node;
						System.out.println("Topic"+":"+name.getTagName()+"="+name.getTextContent());
					}
					
				}
			}
		}
	}
	

	public static void validate(String xmlFile, String validationFile) throws SAXException, IOException {
		// TODO Auto-generated method stub
		SchemaFactory schemaFactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		((schemaFactory.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource(new File(xmlFile)));
		
	}
	

	

}

