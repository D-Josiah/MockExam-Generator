package com.dalisay.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AuthorDetails(name = "JOSIAH DALISAY", date = "2024-01-23", studentNumber = 202004864)

public class Main {
	
	static AppGUI appGUI = AppGUI.getGuiInstance();
	static ChatGPT chatGPT = ChatGPT.getGPT_Instance();
	static String chatGptResponse;
	
	public static void main(String[] args) {
       
        //if GENERATE/RETRY BUTTON IS CLICKED (it may take a while to generate: blue = loading)
        appGUI.getGenerateBtn().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		    	if (appGUI.getGenerateBtn().getText() == "GENERATE") {
		    		
		    		//UNCOMMENT to see results if API doesn't work
			        //chatGptResponse ="1. What is a common breed for guide dogs?\\\\na) Golden Retriever\\\\nb) German Shepherd\\\\nc) Poodle\\\\n\\\\n2. Which breed has a short, wrinkled face?\\\\na) Bulldog\\\\nb) Dalmatian\\\\nc) Doberman\\\\n\\\\n3. What is the smallest dog breed?\\\\na) Chihuahua\\\\nb) Pomeranian\\\\nc) Yorkshire Terrier\\\\n\\\\n4. What is the largest dog breed?\\\\na) Great Dane\\\\nb) Saint Bernard\\\\nc) Mastiff\\\\n\\\\n5. Which breed is known for their webbed feet?\\\\na) Labrador Retriever\\\\nb) Beagle\\\\nc) Boxer\\\\n\\\\n6. What breed is often associated with fire departments?\\\\na) Dalmatian\\\\nb) Rottweiler\\\\nc) Border Collie\\\\n\\\\n7. What is the most popular dog breed in the US?\\\\na) Labrador Retriever\\\\nb) Beagle\\\\nc) Bulldog\\\\n\\\\n8. Which breed is known for its blue tongue?\\\\na) Chow Chow\\\\nb) Siberian Husky\\\\nc) Shih Tzu\\\\n\\\\n9. What breed has a characteristic curly tail?\\\\na) Akita\\\\nb) Shiba Inu\\\\nc) Pug\\\\n\\\\n10. What breed has long, droopy ears?\\\\na) Basset Hound\\\\nb) Greyhound\\\\nc) Australian Shepherd\\\\n\\\\nAnswers: \\\\n1. a) Golden Retriever\\\\n2. a) Bulldog\\\\n3. a) Chihuahua\\\\n4. a) Great Dane\\\\n5. a) Labrador Retriever\\\\n6. a) Dalmatian\\\\n7. a) Labrador Retriever\\\\n8. a) Chow Chow\\\\n9. c) Pug\\\\n10. a) Basset Hound\";";
					chatGPT.setResponse();
					chatGptResponse = ChatGPT.chatGptResponse;
			     		
			        appGUI.setExamPanel(getQuestions(chatGptResponse));
			        appGUI.getViewBtn().setVisible(true);
			        appGUI.getTitle().setVisible(false);
			        appGUI.getGenerateBtn().setText("RETRY");
        		}else {//If button changes to retry
        			
        			appGUI.clearExamPanel();
        			appGUI.getGenerateBtn().setText("GENERATE");
        			appGUI.getViewBtn().setVisible(false);
        			appGUI.getTitle().setVisible(true);
        		}
	
		    }
        });
        
        //if VIEW ANSWERS BUTTON IS CLICKED
        appGUI.getViewBtn().addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        appGUI.getFrame2().setVisible(true);
		       
		        String answerKey;
		        
		        try {
		            int startIndex = chatGptResponse.indexOf("Answers:");
		            int endIndex = chatGptResponse.length();

		            if (startIndex != -1) {
		                // GET the answers from the response (starts with the word "Answers:"
		                String answersPart = chatGptResponse.substring(startIndex, endIndex).trim().replace("\\n", "<br>");
		                answerKey ="<html>" + answersPart + "</html>";
		                appGUI.getAnswerKey().setText(answerKey);
				       
		      
		            } else {
		            	appGUI.getAnswerKey().setText("--NOT AVAILABLE--");
		                System.out.println("Error: the response from chat GPT is incomplete");
		            }   
		        } catch (Exception e1) {
		            System.out.println("UNEXPECTED ERROR - " + e1.getMessage());
		         
		        }

		    }
		});

	}
	
	
	private static String[] getQuestions(String response) {
	    String[] questionsArr = new String[10];
	    String[] responseArr = response.split("\\d+\\."); // split every question per number

	    for (int i = 1; i <= 10; i++) {
	        try {
	            if (!responseArr[i].trim().isEmpty()) { 
	                questionsArr[i - 1] = "<html>" + i + ". " + responseArr[i] + "</html>"; // add HTML tags for GUI format

	            }
	        } catch (Exception e1) {
	            System.out.println("Error: " + e1.getMessage());
	            break;
	        }
	    }
	    return questionsArr;
	}
 
}
