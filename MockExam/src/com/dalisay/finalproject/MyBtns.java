package com.dalisay.finalproject;

import java.awt.Dimension;
import javax.swing.*;


public class MyBtns extends JButton{
	private static final long serialVersionUID = 1L;

		MyBtns (String text){
			
			this.setText(text);
			this.setForeground(MyColors.TEXT_COLOR);
			this.setPreferredSize(new Dimension(150,50));
			this.setBackground(MyColors.SECONDARY_COLOR);
		}
}