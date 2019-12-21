package XMLUtils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XMLUtil 
 {
	public String convertToXml(Object source, Class... type) {
        String result;
        StringWriter sw = new StringWriter();
        try {
            JAXBContext carContext = JAXBContext.newInstance(type);
            Marshaller carMarshaller = carContext.createMarshaller();
            carMarshaller.marshal(source, sw);
            result = sw.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
   /* public static void main(String[] args) {
    	BTA obj = new BTA();
    	Consents ob1 =  new Consents();
    	ob1.setConsentType("TVTA");
    	ob1.setConsentValue("OPTIN");
    	ob1.setLastUpdatedBy("MOHAN");
    	ob1.setConsentMessage("REST ASSURED");
    	obj.setConentDetails(ob1);
    	
        XMLUtil xmlUtil= new  XMLUtil();
        String xml = xmlUtil.convertToXml(obj, obj.getClass());
        System.out.println(xml);
        }*/

 }

