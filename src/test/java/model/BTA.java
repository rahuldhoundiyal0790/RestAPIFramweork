package model;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BTA")
public class BTA 
{
	@XmlAttribute(name = "xmlns:xsi")
	private String xmlnsxsi = "http://www.w3.org/2001/XMLSchema-instance";
	private String xsinoNamespaceSchemaLocation;
	private Consents consents ;   
	private AddReminder addReminder;

	@XmlElement(name="AddReminderDoc")
	public AddReminder getAddReminder() {
		return addReminder;
	}

	public void setAddReminder(AddReminder addReminder) {
		this.addReminder = addReminder;
	}



	@XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
	public String getXsinoNamespaceSchemaLocation() {
		return xsinoNamespaceSchemaLocation;
	}

	public void setXsinoNamespaceSchemaLocation(String xsinoNamespaceSchemaLocation) {
		this.xsinoNamespaceSchemaLocation = xsinoNamespaceSchemaLocation;
	}

	@XmlElement(name="Consent")
	public Consents getConentDetails() {
		return consents;
	}

	public void setConentDetails(Consents consents) {
		this.consents = consents;
	}






}
