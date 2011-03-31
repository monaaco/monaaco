import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.stream.*;

import com.sun.xml.internal.stream.buffer.XMLStreamBuffer;

 public class Escritura {

    public static void main(String[] args) throws IOException, XMLStreamException {
    	
       FileWriter filew = new FileWriter("xml/ejemplo23.xml");        
       XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
       XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(filew); 
       xmlw.writeStartDocument();
       xmlw.writeCharacters("\n");
	       xmlw.writeStartElement("Tracks");
	       	       xmlw.writeAttribute("N", "1000");
	       xmlw.writeCharacters("\n");

	       	for(int i=0; i<1000; i++){
	 	       xmlw.writeCharacters("\t"); xmlw.writeStartElement("Track");
	       	       xmlw.writeAttribute("ID", String.valueOf(i) );
	 	        xmlw.writeCharacters("\n");
	 	        	xmlw.writeCharacters("\t\t"); xmlw.writeStartElement("Title"); xmlw.writeCharacters("Boss"); xmlw.writeEndElement();
		 	        xmlw.writeCharacters("\n");
		 	        xmlw.writeCharacters("\t\t"); xmlw.writeStartElement("Artist"); xmlw.writeCharacters("Dj ManuAl"); xmlw.writeEndElement();
		 	        xmlw.writeCharacters("\n");
		 	        xmlw.writeCharacters("\t\t"); xmlw.writeStartElement("Album"); xmlw.writeCharacters("Samples"); xmlw.writeEndElement();
		 	        xmlw.writeCharacters("\n");
		 	        xmlw.writeCharacters("\t\t"); xmlw.writeStartElement("Time"); xmlw.writeCharacters("357"); xmlw.writeEndElement();
		 	        xmlw.writeCharacters("\n");
		 	        xmlw.writeCharacters("\t\t"); xmlw.writeStartElement("Type"); xmlw.writeCharacters("OGG"); xmlw.writeEndElement();
		 	        xmlw.writeCharacters("\n");
		 	       xmlw.writeCharacters("\t"); xmlw.writeEndElement();
	 	        xmlw.writeCharacters("\n");
	       	}
	       xmlw.writeEndElement();
	       xmlw.writeCharacters("\n");
       xmlw.writeEndDocument();
       xmlw.writeCharacters("\n");
       xmlw.close();
    }
 }
