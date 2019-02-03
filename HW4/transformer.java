import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import java.io.File;

public class transformer {
	public static void main(String[] args) {
		String xsl_file = args[0];
		String input_file =  args[1];
		String output_file = args[2];
		StreamSource code =	new StreamSource(new File(xsl_file));
		StreamSource in = new StreamSource(new File(input_file));
		StreamResult out = new StreamResult(new File(output_file));
		Transformer transfrmr;
		TransformerFactory transformerfac =	TransformerFactory.newInstance();
		try {
			transfrmr = transformerfac.newTransformer(code);
			transfrmr.transform(in, out);
			}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
			} 
		catch (TransformerException e) {
			e.printStackTrace();
			}
		}
	}
