package com.dalisay.finalproject;

import java.io.*;
import java.net.*;

public class ChatGPT {
	static AppGUI appGUI = AppGUI.getGuiInstance();
	static String chatGptResponse;
	private static ChatGPT instance = new ChatGPT();
	

	private ChatGPT() {
		
    }
	
	public static ChatGPT getGPT_Instance() {
        if (instance == null) {
            instance = new ChatGPT();
        }
        return instance;
    }
	
	public void setResponse() {
		chatGptResponse = getResponse(getPrompt());
		chatGptResponse = chatGptResponse.replace("\\n", "<br>");
	}
	
	private static String getPrompt() {//concatenate initial prompt and the user's chosen topic 

    	String userInput = appGUI.getTopicField().getText();
        String incPrompt = String.format("Make 10 multiple-choice questions with list of"
        		+ " answers at the end about");
        String completePrompt = incPrompt + userInput;
		return completePrompt;

		}
	
	
	
	public static String getResponse(String message) {
	    String apiURL = "https://api.openai.com/v1/chat/completions";
	    String apiKey = "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"; // My API (limited tokens & will expire soon)
	    

	    try {
	        // Create the HTTP POST request
	        @SuppressWarnings("deprecation")
			URL obj = new URL(apiURL);
	        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
	        connection.setRequestProperty("Content-Type", "application/json");
	
	        // Request body
	        String body = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
	        connection.setDoOutput(true);
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	        writer.write(body);
	        writer.flush();
	        writer.close();
	
	        // Response
	        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	
	        
	        return getContent(response.toString());
	
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private static String getContent(String response) {
	    int start = response.indexOf("content")+11; 
	    int end = response.indexOf("\"", start); 
	    return response.substring(start, end); 
	}
	
}



