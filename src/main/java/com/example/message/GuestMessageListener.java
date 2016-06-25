package com.example.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class GuestMessageListener implements MessageListener {

	@Autowired
	private GuestMessageSender sender;
	private static final Logger log = Logger.getLogger(GuestMessageListener.class);

	public void onMessage(Message message) {
		log.debug("Received message from queue [" + message + "]");

		if (message instanceof TextMessage) {
			try {
				String msgText = ((TextMessage) message).getText();
				sender.send(msgText);
				log.debug("Processed message");

			} catch (JMSException ex) {
				log.error("Error while processing message",ex);
			}
		} else {
			throw new RuntimeException("Invalid Message");
		}
	}

	public void setTestMessageSender(GuestMessageSender sender) {
		this.sender = sender;
	}

}
