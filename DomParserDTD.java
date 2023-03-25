package Dtdvalidation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParserDTD {

	public static void main(String[] args){
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			validate("channel.xml");
		} catch (ParserConfigurationException e) {
			flag=false;
		} catch (FileNotFoundException e) {
			flag=false;

		} catch (SAXException e) {
			flag=false;

		} catch (IOException e) {
			flag=false;
		}
		//validated the xml against DTD
		System.out.println("xml file is valid: "+ flag);
		

	}

	public static void validate(String string) throws ParserConfigurationException,FileNotFoundException,SAXException,IOException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
		documentBuilder.setErrorHandler(new org.xml.sax.ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {
				// TODO Auto-generated method stub
				throw exception;
				
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				// TODO Auto-generated method stub
				throw exception;
				
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				// TODO Auto-generated method stub
				throw exception;
				
			}
				
		});	
		//Parsing the DOM 
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

}
