package com.dalisay.finalproject;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

/*-MAIN FRAME SUB COMPONENTS
		 Main Frame 
		   		Header
		   			Header Wrapper
		   				Title
		   				Topic Panel
		   					Topic Field
		   					Generate Button
		   					View Answers Button   
		   		Body
		   			Exam Panel 
		   				Item Panel
		   					Answer Field
		   					Question
		   			
		   		 
*/

public class AppGUI {
	
	private static AppGUI instance;
	
	//GUI Sub components
    private JFrame mainFrame;
    private JPanel headerPanel;
    private JLabel title;
    private JPanel topicPanel;
    private JTextField topicField;
    private JButton generateBtn;
    private JPanel bodyPanel;
    private JPanel examPanel;
    private JPanel itemPanel;
    private JButton viewBtn;
    private JFrame frame2;
    private JLabel answerKey;
    private JLabel scoreLabel;
    

    private AppGUI() {
            initialize();
    }
    
    public static AppGUI getGuiInstance() {
        if (instance == null) {
            instance = new AppGUI();
        }
        return instance;
    }

    
    private void initialize() {

        setMainFrame();
        setHeaderPanel();
        setTitle();
        setTopicPanel();
        setTopicField();
        setGenerateBtn();
        setBodyPanel();
        setViewBtn();
        setFrame2();
        setIcon();
    
    }
    
    //SETTERS
    
    private void setMainFrame() {
        JFrame mainFrame = new JFrame();
        this.mainFrame =mainFrame;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(screenSize);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.setTitle("Mock Exam Generator");
        mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setVisible(true);
    }
    
    private void setHeaderPanel() {
        JPanel headerPanel = new JPanel();
        this.headerPanel =headerPanel;
        headerPanel.setBackground(MyColors.PRIMARY_COLOR);

        mainFrame.add(headerPanel);
    }
    
    private void setTitle() {
   
    	JLabel title = new JLabel("MOCK EXAM GENERATOR");
    	this.title = title;
		Font titleFont = new Font("serif", Font.BOLD, 60);
		title.setFont(titleFont);
		title.setForeground(MyColors.TEXT_COLOR);
		headerPanel.add(title);

    }
    
    private void setTopicPanel() {
    	
    	JPanel topicPanel = new JPanel();
    	this.topicPanel = topicPanel;
    	headerPanel.add(topicPanel);

    }
    
	private void setTopicField() {
	    	
		JTextField topicField = new JTextField(50);
		this.topicField = topicField;
  		String placeholder ="ENTER ANY TOPIC";
  		Font font = new Font("Arial", Font.PLAIN, 16);
        topicField.setFont(font);
  		topicField.setPreferredSize(new Dimension(100,40));
  		topicField.setText(placeholder);
  		topicField.setForeground(MyColors.PLACEHOLDER_COLOR);
  		
  		topicField.addFocusListener(new FocusListener() {
              @Override
              public void focusGained(FocusEvent e) {
                  if (topicField.getText().equals(placeholder)) {
                      topicField.setText("");
                      topicField.setForeground(Color.BLACK); // 
                  }
              }

              @Override
              public void focusLost(FocusEvent e) {
                  if (topicField.getText().isEmpty()) {
                      topicField.setText(placeholder);
                      topicField.setForeground(Color.GRAY);
                  }
              }
          });
  		
  		topicPanel.add(topicField);
	}
	
	private void setGenerateBtn() {

		MyBtns generateBtn = new MyBtns("GENERATE");
		this.generateBtn = generateBtn;
		topicPanel.add(generateBtn);
	    	
	}
	
	private void setBodyPanel() {

		JPanel bodyPanel = new JPanel();
		this.bodyPanel =bodyPanel;
		bodyPanel.setBackground(MyColors.PRIMARY_COLOR);
		
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		mainFrame.add(bodyPanel);
	}
	

	public void setExamPanel(String[] questionsArr) {
	    JPanel examPanel = new JPanel(new GridLayout(5, 2));
	    this.examPanel = examPanel;
	    examPanel.setBackground(MyColors.PRIMARY_COLOR);
	    examPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
	    for (int i = 0; i < 10; i++) {
	        Component itemPanel = this.setItemPanel(questionsArr[i]);
	        examPanel.add(itemPanel);
	    }
	
	    bodyPanel.add(examPanel);
	  
	}
	
	
	private Component setItemPanel(String question) {
			
			JPanel itemPanel = new JPanel();
			
			Font font = new Font("Arial", Font.PLAIN, 16);
			JLabel questionLabel = new JLabel();
			questionLabel.setText(question);
			questionLabel.setForeground(MyColors.TEXT_COLOR);
			questionLabel.setFont(font);
			
			JTextField answerField = new JTextField();
			answerField.setPreferredSize(new Dimension(30, 20));
			
			itemPanel.setBackground(MyColors.PRIMARY_COLOR);
			itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			itemPanel.add(answerField);
			itemPanel.add(questionLabel);
	    	return itemPanel;
		};
		
		
	public void setViewBtn() {
		    this.viewBtn = new MyBtns("VIEW ANSWERS");
		    topicPanel.add(viewBtn);
		    viewBtn.setVisible(false);
		}
		
	private void setFrame2() {
		
			frame2 = new JFrame("Answers");
	        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame2.setLocationRelativeTo(mainFrame);
	        frame2.setResizable(false);
	        frame2.getContentPane().setBackground(MyColors.SECONDARY_COLOR);
	        frame2.setLayout(new FlowLayout(FlowLayout.CENTER));
	        frame2.setSize(500,300);
	        frame2.setVisible(false);
	        
	        setAnswerKey();
	  
			
		};
		
	private void setAnswerKey() {
			
	        JLabel answerKey = new JLabel();
	        this.answerKey =answerKey;
	       
	        answerKey.setForeground(MyColors.TEXT_COLOR);

	        frame2.add(answerKey);
	        
			
		};

	private void setIcon() {
	        ImageIcon icon = new ImageIcon("src/com/dalisay/finalproject/icon.png");
	        mainFrame.setIconImage(icon.getImage());
			frame2.setIconImage(icon.getImage());
			
		};
		
		//GETTERS
    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JLabel getTitle() {
        return title;
    }

    public JPanel getTopicPanel() {
        return topicPanel;
    }

    public JTextField getTopicField() {
        return topicField;
    }


    public JButton getGenerateBtn() {
        return generateBtn;
    }

    public JPanel getBodyPanel() {
        return bodyPanel;
    }

    public JPanel getExamPanel() {
        return examPanel;
    }
    
    public JPanel getItemPanel() {
        return itemPanel;
    }
    
    public JButton getViewBtn() {
        return viewBtn;
    }

    public JFrame getFrame2() {
        return frame2;
    }
    
    public JLabel getAnswerKey() {
        return answerKey;
    }
    
    public JLabel getScoreLabel() {
        return scoreLabel;
    }
	
    //CLEAR exam panel for new set of questions
    public void clearExamPanel() {
    	Component[] components = examPanel.getComponents();
    	for (int i = 0; i<10; i++) {

            examPanel.remove(components[i]);
                 
        }
    	mainFrame.revalidate();
        mainFrame.repaint();
	}
}