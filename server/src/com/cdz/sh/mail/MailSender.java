package com.cdz.sh.mail;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.mail.exception.EMailException;
import com.cdz.sh.model.request.EmailRequest;
import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;



public class MailSender {

	private static final String HOST_GMAIL = "smtp.gmail.com";
	private static final String HOST_YAHOO = "smtp.mail.yahoo.co.in";
	private static final String HOST_PORT = "587";
	private static final String SMTP = "smtp";
	
	public void sendMail(EmailRequest emailRequest) throws EMailException {
		try {
			
			validateParameters(emailRequest);
			String host = "";
			// Get system properties
			Properties props = System.getProperties();
				
			if(emailRequest.getFrom().endsWith("@gmail.com")){
				host = HOST_GMAIL;
			}
			else if(emailRequest.getFrom().contains("@yahoo.com")){
				host = HOST_YAHOO;
			}
			
			props.setProperty("mail.smtp.ssl.trust", "*");
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", HOST_PORT);
			props.setProperty("mail.smtp.user", emailRequest.getFrom());
			
			// Get session
			Session session = Session.getDefaultInstance(props);
			
			// Define message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailRequest.getFrom()));

			for (String to : emailRequest.getToList()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
						
			message.setSubject(emailRequest.getSubject());
			
			message.setContent(createMessageContent(emailRequest.getBody()));

			
			Transport transport = session.getTransport(SMTP);
			transport.connect(emailRequest.getFrom(), emailRequest.getPassword());
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			if(e.getNextException() instanceof ConnectException){
				throw new EMailException(ExceptionErrorCodes.INVALID_FROM_EMAIL, e.getMessage());
			}
			if(e instanceof SMTPSendFailedException || e instanceof AuthenticationFailedException){
				throw new EMailException(ExceptionErrorCodes.INVALID_SENDER, e.getMessage());
			}
			if(e.getNextException() instanceof SMTPAddressFailedException){
				throw new EMailException(ExceptionErrorCodes.INVALID_TO_EMAIL, e.getMessage());
			}
			
			throw new EMailException(ExceptionErrorCodes.EMAIL_NOT_SENT, e.getMessage());
		}
		
		
		
	}

	
	private Multipart createMessageContent(String body) throws EMailException {
		
		Multipart multipart = new MimeMultipart("related");
		try 
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(body));
			Document doc = dBuilder.parse(is);
			
			List<BodyPart> imgBodyParts = new ArrayList<BodyPart>();
			NodeList imgNodeList = doc.getElementsByTagName("IMG");
			
			for (int i = 0; i < imgNodeList.getLength(); i++) {
				BodyPart imgBodyPart = createImageBodyPart(imgNodeList.item(i));
				imgBodyParts.add(imgBodyPart);
			}
						
			doc = convertFontTagToSpanTag(doc);
			
			String docString = toString(doc);
			
			// replaces '\t' with &emsp; that is HTML compliance
			docString = docString.replace("\t", "&emsp;&emsp;&emsp;");

			System.out.println(docString);
			
			BodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(docString, "text/html");
			
			multipart.addBodyPart(htmlPart);
			
			for (BodyPart imgBodyPart : imgBodyParts) {
				multipart.addBodyPart(imgBodyPart);
			}

		}
		catch (MessagingException e) 
		{
			throw new EMailException("", e.getMessage());
			
		}
		catch (ParserConfigurationException e) 
		{
			throw new EMailException("", e.getMessage());
		}
		catch (SAXException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multipart;
	}
	

	private Document convertFontTagToSpanTag(Document doc) {
		
		NodeList fontNodeList = doc.getElementsByTagName("FONT");
		List<Node> fontNodes = new ArrayList<Node>();
		
		for (int i = 0; i < fontNodeList.getLength(); i++) {
			
			Node fontNode = fontNodeList.item(i);
			String styleValue = "";
			
			Node fontSizeAttr = fontNode.getAttributes().getNamedItem("SIZE");
			if(fontSizeAttr != null){
				styleValue += "font-size:" + fontSizeAttr.getTextContent() + "px; ";
				fontNode.getAttributes().removeNamedItem("SIZE");
			}
			
			Node fontFamilyAttr = fontNode.getAttributes().getNamedItem("FACE");
			if(fontFamilyAttr != null){
				styleValue += "font-family:" + fontFamilyAttr.getTextContent() + "; ";
				fontNode.getAttributes().removeNamedItem("FACE");
			}
			
			Node fontColorAttr = fontNode.getAttributes().getNamedItem("COLOR");
			if(fontColorAttr != null){
				styleValue += "color:" + fontColorAttr.getTextContent() + ";";
				fontNode.getAttributes().removeNamedItem("COLOR");
			}
			
			Node namedItem = fontNode.getAttributes().getNamedItem("KERNING");
			if(namedItem != null){
				fontNode.getAttributes().removeNamedItem("KERNING");
			}
			namedItem = fontNode.getAttributes().getNamedItem("LETTERSPACING");
			if(namedItem != null){
				fontNode.getAttributes().removeNamedItem("LETTERSPACING");
			}
			
			
			Attr styleAttr = doc.createAttribute("style");
			styleAttr.setNodeValue(styleValue);
			
			fontNode.getAttributes().setNamedItem(styleAttr);
			
			fontNodes.add(fontNode);
		}
		
		for (Node fontNode : fontNodes) {
			doc.renameNode(fontNode, null, "span");
		}
				
		return doc;
	}


	private BodyPart createImageBodyPart(Node imgNode) throws MessagingException {
		
		BodyPart imgBodyPart = new MimeBodyPart();
		
		//get the img id
		Node imgIdAttr = imgNode.getAttributes().getNamedItem("id");
		String imgId = imgIdAttr.getTextContent();
		
		// get the image path 
		Node imgSrcAttr = imgNode.getAttributes().getNamedItem("SRC");
		String imagePath = imgSrcAttr.getTextContent();

		//replace the src for a cid value
		imgSrcAttr.setTextContent("cid:" + imgId);
		
		// Loading the image
		DataSource ds = new FileDataSource(imagePath);
        imgBodyPart.setDataHandler(new DataHandler(ds));

        //imgNode.getAttributes().removeNamedItem("id");
        
        //Setting the header
        imgBodyPart.setHeader("Content-ID","<" + imgId + ">");
        imgBodyPart.setDisposition(MimeBodyPart.INLINE);
        return imgBodyPart;
    }


	private void validateParameters(EmailRequest emailRequest) throws EMailException {
		
		if(emailRequest.getFrom() == null){
			throw new EMailException(ExceptionErrorCodes.INVALID_FROM_EMAIL, "'from' email is empty");
		}
						
		if(emailRequest.getToList() == null || emailRequest.getToList().isEmpty()){
			throw new EMailException(ExceptionErrorCodes.TO_LIST_EMPTY, "The 'To' list is empty.");
		}
		
		if(emailRequest.getBody() == null){
			throw new EMailException(ExceptionErrorCodes.INVALID_BODY, "email body is empty");
		}
	}
	
	
	private String toString(Document doc) {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	        transformer.setOutputProperty(OutputKeys.METHOD, "html");
	        transformer.setOutputProperty(OutputKeys.INDENT, "no");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(sw));
	        return sw.toString();
	    }
	    catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}
	
	
	// adapter for testing purposes
	public void sendMail(String from, String password, List<String> toList, String subject, String body, boolean isHtml) throws EMailException {
		
		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setFrom(from);
		emailRequest.setPassword(password);
		emailRequest.setToList(toList);
		emailRequest.setSubject(subject);
		emailRequest.setBody(body);
		emailRequest.setIsHtml(isHtml);
		
		this.sendMail(emailRequest);
	}
}
