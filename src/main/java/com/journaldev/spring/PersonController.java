package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String welcome() {
		readXml();
		return "Welcome to Spring Boot REST...";
	}
	
	public void readXml() {

	    try {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		DefaultHandler handler = new DefaultHandler() {

		boolean bfname = false;
		boolean blname = false;
		boolean bnname = false;
		boolean bsalary = false;

		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {

			if (qName.equalsIgnoreCase("FIRSTNAME")) {
				bfname = true;
				System.out.println("Start Element qName :" + qName);
				System.out.println("Start Element attributes length :" + attributes.getLength());
			}

			if (qName.equalsIgnoreCase("LASTNAME")) {
				blname = true;
				System.out.println("Start Element qName :" + qName);
				System.out.println("Start Element attributes length :" + attributes.getLength());
			}

			if (qName.equalsIgnoreCase("NICKNAME")) {
				bnname = true;
				System.out.println("Start Element qName :" + qName);
				System.out.println("Start Element attributes length :" + attributes.getLength());
			}

			if (qName.equalsIgnoreCase("SALARY")) {
				bsalary = true;
				System.out.println("Start Element qName :" + qName);
				System.out.println("Start Element attributes length :" + attributes.getLength());
			}

		}

		public void endElement(String uri, String localName,
			String qName) throws SAXException {

			System.out.println("End Element :" + qName);

		}

		public void characters(char ch[], int start, int length) throws SAXException {

			if (bfname) {
				System.out.println("First Name : " + new String(ch, start, length));
				bfname = false;
			}

			if (blname) {
				System.out.println("Last Name : " + new String(ch, start, length));
				blname = false;
			}

			if (bnname) {
				System.out.println("Nick Name : " + new String(ch, start, length));
				bnname = false;
			}

			if (bsalary) {
				System.out.println("Salary : " + new String(ch, start, length));
				bsalary = false;
			}

		}

	   };
           
	       ClassLoader classLoader = this.getClass().getClassLoader();
	       saxParser.parse(new File(classLoader.getResource("company.xml").getFile()), handler);
	 
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	  
	   }

}
