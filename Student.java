/**************************************************************************************
* Student.java
* Purpose: This class represents a student object. 
* Attributes: String name -- contains the name of this student.
*				  Double grade -- contains the grade of this student.
* Methods: Double getGrade -- returns the grade for this student.
* 			  String toString -- returns a string consisting of the name and grade for 
										this student.
* Author:	Patrick Nutt
* 				University of Maryland University Colleges
*				CMSC 335
* Date:		3 June 2007
**************************************************************************************/

package project1; 

public class Student {
	
	private String name;
	private Double grade;
	
	public Student (String n, Double g) {
		this.name = n;
		this.grade = g;
	}
	
	public Double getGrade() {
		return grade;
	}
	
	public String toString() {
		
		String string;
		
		string = this.name + " " + this.grade;
		
		return string;
	}
	
}