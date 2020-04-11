/**************************************************************************************
* Project1GUI.java
* ** NOTE ** This class file was adapted from the original file written by Charles Kann 
* 
* Purpose: This class is the main entry point into the Project1 application. It provides 
* 				graphical user interface used to accept data entry, display calcualtion results, 
* 				perform actions as mandated by user button operation. The GUI contains two entry 
* 				fields: name and grade. The name entry can be any text string while the grade 
* 				entry must be a number between 0 and 100 inclusively. Both fields are mandatory
* 				and processing of the Enter button action will not be processed until a valid
* 			  	entry is made. The GUI also has four output fields used to display a list of 
*				entered data, a running average of grade inputs, the highest grade, 
*				and the lowest grade. Calulations are performed after the entry of
* 				each student's data. The GUI action is controlled through three user buttons:
*				Enter, List, and Exit. The action events for each of the buttons is handled by
*				respective inner classes. The Enter button is used to store the user data and
* 				perform the required calculations. The List button is used to display a list 
* 				of the name and grade for each student entered. The Exit button is used to 
*				terminate the program.
* Author:	Patrick Nutt
* 				University of Maryland University Colleges
*				CMSC 335
* Date:		3 June 2007
**************************************************************************************/

packageStudentProject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.util.ArrayList;
import java.text.DecimalFormat;


public class Project1GUI {
	
	private static int index = 0; // Used to keep track of the current position in studentList
	private static ArrayList<Student> studentList = new ArrayList();
	private static Double avg = new Double(0.0);
	private static Double highG = new Double(0.0);
	private static Double lowG = new Double(100.0);
	
	public static void main(String[] args) {
		JFrame gui = new JFrame("Project 1 GUI");
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2));
		inputPanel.add(new Label("Student Name"));
		final JTextField nameField = new JTextField(15);
		inputPanel.add(nameField);
		inputPanel.add(new Label("Student Grade"));
		final JTextField gradeField = new JTextField(15);
		inputPanel.add(gradeField);
		inputPanel.add(new Label("Average"));
		final JLabel averageLabel = new JLabel("---");
		inputPanel.add(averageLabel);
		inputPanel.add(new Label("High Score"));
		final JLabel highLabel = new JLabel("---");
		inputPanel.add(highLabel);
		inputPanel.add(new Label("Low Score"));
		final JLabel lowLabel = new JLabel("---");		
		inputPanel.add(lowLabel);
		
		gui.add(inputPanel, BorderLayout.NORTH);
		
		final DefaultListModel dfl = new DefaultListModel();
		JList jl = new JList(dfl);
		JScrollPane jsp = new JScrollPane(jl);

		// Enter Button
		JPanel buttonPanel = new JPanel();
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				
				String name;
				String gText;
				Double grade;	
				Double total = 0.0;
				
				name = nameField.getText();
				gText = gradeField.getText();		
				
				// Detect null entries
				if (name.equals("") || name.equals("Invalid Entry")) {
					nameField.setText("Invalid Entry");
					return;
				}
				if (gText.equals("") || gText.equals("Invalid Entry")) {
					gradeField.setText("Invalid Entry");
					return;
				}
				
				try{
					grade = new Double(gText);
				}
				catch (NumberFormatException e) {
					gradeField.setText("Invalid Entry");
					return;
				}				
			 		
				// Validate input
				if (grade < 0.0) {
					grade = 0.0;
				}
				else if (grade > 100.0) { 
					grade = 100.0;
				}
				
				// Determine if grade is highest or lowest
				if (grade > highG) {
					highG = grade;
				} 
				else if (grade < lowG) {
					lowG = grade;
				}
								
				// Store student information
				Student student = new Student (name, grade);
				studentList.add(student);
				
				// Determine the current average
				for (int count = 0; count < studentList.size(); count++){					
					
					total = total + studentList.get(count).getGrade();
				}
				
					avg = total / studentList.size();				
				
				// Update output labels
				averageLabel.setText(avg.toString());
				highLabel.setText(highG.toString());
				lowLabel.setText(lowG.toString());	 			
				
			}
		});				
		buttonPanel.add(enterButton);
		
		// List Button
		JButton listButton = new JButton("List");
		listButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				for (int count = index; count < studentList.size(); count++){					
					
					dfl.addElement(studentList.get(count));
					index++;
				}
			}
		});		
		buttonPanel.add(listButton);
		
		// Exit Button
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(1);
			}
		});
		buttonPanel.add(exitButton);
		
		// Add components to GUI
		gui.add(jsp);
		gui.add(buttonPanel, BorderLayout.SOUTH);
		
		gui.pack();
		gui.setVisible(true);
	}
}
