package com.nees.senativos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EmotionServerHandler {
	
	private static String baseURL;
	
	private static EmotionServerHandler E_S_H = null;
	
	/**
	 * This method implements Singleton designing pattern
	 * to create a new instance of EmotionServerHandler.
	 */
	public static EmotionServerHandler getInstance() {
		if(E_S_H == null)
			E_S_H = new EmotionServerHandler();
		return E_S_H;
	}
	
	/**
	 * Private constructor to create a base URL
	 * to connect with server. 
	 * The default URL base is for local use.
	 */
	private EmotionServerHandler() {
		baseURL = "http://localhost:8080/UnityBridge/OntologyServlet?emotion=";
	}
	
	/**
	 * Once ontology has found some emotion, 
	 * this method can be used to send it to server.
	 * 
	 * @param found emotion
	 */
	public void sendEmotionToServer(String foundEmotion, int transitionTime) {		
		try {
			URL ontologyBridge = new URL(buildURL(foundEmotion, transitionTime));
			URLConnection connection = ontologyBridge.openConnection();
			BufferedReader serverFeedbackStream = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));
			String serverFeedback = serverFeedbackStream.readLine();
			System.err.println("===Emotion Server Handler LOG: " + serverFeedback);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * If there's a need to setup an URL to a non local
	 * server, setup the base URL here with this format:
	 *     .../UnityBridge/OntologyServlet?emotion=
	 * 
	 * @param baseURL
	 */
	public void setBaseURL(String baseURL) {
		EmotionServerHandler.baseURL = baseURL;
	}
	
	private String buildURL(String emotion, int transitionTime) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(baseURL);
		urlBuilder.append(emotion);
		urlBuilder.append("&tt=");
		urlBuilder.append(transitionTime);
		return urlBuilder.toString();
	}
}
