// Name:				Ishva Patel
// NetId:				irp190001
// Date:				September 17, 2021
// Class: 				CS 2336.003
// Program Overview:	Is a calculator in which  it will do arithmetic operations to both real and complex numbers
// 						Main Class will do the operations and it was be using Real number objects and Complex number objects 
// 						that are created in the  Real.java and the Complex.Java

import java.io.*;
import java.util.Scanner;
import java.io.File;

public class Main {
	
	public static void main(String args[]) throws IOException
	{
		
		Scanner scnr = new Scanner(System.in); // scanner for user input
		
		// Variables
		String fileName; // Stores file name given by user
		Real num1; // stores complex number 1 
		Real num2; // Stores complex number 2
		double realPart;
		double imgPart;
		
		//Get File from user and open file
		System.out.println("Please enter a file name");
		fileName = scnr.next();
		
		File file = new File(fileName); // Creates file object
		Scanner input = new Scanner (new File (fileName)); // Opens scanner to do file reading
		
		// Check if the file can be opened
		if (file.canRead())
		{
			// go through the file
			while (input.hasNext())
			{
				try {
				String data = input.next();
				String val[] = data.split("[-+]"); // Splitting on the operation for the complex
				
				String operator = input.next(); // getting the operation
				if (!(operator.equals("+")) && !(operator.equals("-")) && !(operator.equals("*")) && !(operator.equals("/"))&& !(operator.equals("=")) && !(operator.equals("<")) && !(operator.equals(">")))
				{
					continue;
				}
				
				String data2 = input.next();
				String val2[] = data2.split("[-+]"); // Splitting on the operand for the complex

				//Parse for complex numbers for num1
				// a OR bi case
				if(val.length < 2) 
				{
					if(val[0].contains("i")) 
					{
						double value = Double.parseDouble(data.split("i")[0]); // remove the i
						// Checks if there is a negative value
						if (data.contains("-")){
							value = -value;
						}
						num1 = new Complex(0.0, value);
					} // end of if()
					else 
					{
						double value = Double.parseDouble(data);
						if (data.contains("-")){
							value = -value;
						}
						num1 = new Real(value);
						
					} // end of else()
				} // end of if()
				else 
				{ // a +/- bi case
					if (data.charAt(0) == '-') {
						realPart = Double.parseDouble(val[1]);
						realPart = -realPart;
					}
					else {
						realPart = Double.parseDouble(val[0]);
					}
					// Get sign of imaginary part
					int signIdx = val[1].length();
					int strLenIdx = data.length() - 1;
					if (data.charAt(strLenIdx - signIdx) == '-') {
						if(val.length == 3)
							imgPart = Double.parseDouble(val[2].split("i")[0]);
						else
							imgPart = Double.parseDouble(val[1].split("i")[0]);
						imgPart = -imgPart;
					}
					else {
						if(val.length == 3)
							imgPart = Double.parseDouble(val[2].split("i")[0]);
						else
							imgPart = Double.parseDouble(val[1].split("i")[0]);
					}
					num1 = new Complex(realPart, imgPart);
				} // end of else()
				
				// Parse for complex numbers for num2
				// a OR bi case
				if(val2.length < 2) 
				{
					if(val2[0].contains("i")) 
					{
						double value = Double.parseDouble(data2.split("i")[0]);
						if (data2.contains("-")){
							value = -value;
						}
						num2 = new Complex(0.0, value);
					} // end of if()
					else 
					{
						double value = Double.parseDouble(data2);
						if (data2.contains("-")){
							value = -value;
						}
						num2 = new Real(value);
					} // end of else()
				} // end of if()
				
				else // a +/- bi case
				{ 
					realPart = Double.parseDouble(val2[0]);
					imgPart = Double.parseDouble(val2[1].split("i")[0]);
					
					if (data2.charAt(0) == '-') {
						realPart = -realPart;
					}
					// Get sign of imaginary part
					int signIdx = val2[1].length();
					int strLenIdx = data2.length() - 1;
					if (data2.charAt(strLenIdx - signIdx) == '-') {
						if(val2.length == 3)
							imgPart = Double.parseDouble(val2[2].split("i")[0]);
						else
							imgPart = Double.parseDouble(val2[1].split("i")[0]);
						imgPart = -imgPart;
					}
					else {
						if(val2.length == 3)
							imgPart = Double.parseDouble(val2[2].split("i")[0]);
						else
							imgPart = Double.parseDouble(val2[1].split("i")[0]);
					}
					num2 = new Complex(realPart, imgPart);
					
				} // end of else()
				
				System.out.print(data + " " + operator + " " + data2 + '\t');
				
				//Check operator and call method
				
				// Checks if the operator is + if it is calls the addition method
				
				if (operator.equals("+"))
				{
					// Checks if both numbers are instanceof complex Class
					if ((num1 instanceof Complex) && (num2 instanceof Complex))
					{
						// Casts them as complex Number
						Complex num1Img = (Complex) num1; 
						Complex num2Img = (Complex) num2;
						Complex sum = Addition(num1Img, num2Img); // Does addition
						System.out.print(sum.toString()); // Prints the sum
						System.out.println();
						
					}
					// Check if num1 is a real and num2 is complex
					else if ((num1 instanceof Real) && (num2 instanceof Complex))
					{
						double num1Real = num1.getReal(); // Creates a complex num1 out of num1.get real
						Complex ComplexNum1Img = new Complex(num1Real, 0);
					
						Complex num2Img = (Complex)num2; // Casts num2 as complex
						Complex sum = Addition(ComplexNum1Img, num2Img); // Does addition
						System.out.print(sum.toString()); // Prints the sum
						System.out.println();
					}
					// Checks if num1 is complex and num2 is real
					else if ((num1 instanceof Complex) && (num2 instanceof Real))
					{
						double num2Real = num2.getReal(); // Creates a complex num2 out of the num2.getReal
						Complex ComplexNum2Img = new Complex(num2Real, 0); 
					
						Complex num1Img = (Complex)num1; // Casts num1 as Complex
						Complex sum = Addition(num1Img, ComplexNum2Img); // Does Addition
						System.out.print(sum.toString()); // Prints the sum
						System.out.println();
					}
					// Both are real numbers
					else
					{
						double num1Real = num1.getReal();
						Complex ComplexNum1Img = new Complex(num1Real, 0); // Creates num1 as complex number obj
						
						double num2Real = num2.getReal();
 						Complex ComplexNum2Img = new Complex(num2Real, 0); // Creates num2 as complex number obj
						Complex sum = Addition(ComplexNum1Img, ComplexNum2Img); // Does the Addition
						System.out.print(sum.toString()); // Prints the Sum
						System.out.println();
					}
					
				} // end of if
				//Check if the operator is - if it is calls the subtraction method
				else if (operator.equals("-"))
				{
					// Both are Complex
					if((num1 instanceof Complex) && (num2 instanceof Complex))
					{
						Complex num1Img = (Complex) num1;
						Complex num2Img = (Complex) num2;
						Complex difference = Subtraction(num1Img,num2Img);
						System.out.print(difference.toString());
						System.out.println();
					}
					// Num 1 is real num2 is complex
					else if ((num1 instanceof Real) && (num2 instanceof Complex))
					{
						double num1Real = num1.getReal(); // Creates a complex num1 out of num1.get real
						Complex ComplexNum1Img = new Complex(num1Real, 0);
					
						Complex num2Img = (Complex)num2; // Casts num2 as complex
						
						Complex difference = Subtraction(ComplexNum1Img,num2Img); // Calculates the difference
						System.out.print(difference.toString()); // prints the difference
						System.out.println();
					}
					// Num2 is real num1 is complex
					else if ((num2 instanceof Real) && (num1 instanceof Complex))
					{
						double num2Real = num2.getReal(); // Creates a complex num2 out of num2.get real
						Complex ComplexNum2Img = new Complex(num2Real, 0);
					
						Complex num1Img = (Complex)num1; // Casts num1 as complex
						
						Complex difference = Subtraction(num1Img, ComplexNum2Img); // Calculates the difference
						System.out.print(difference.toString()); // prints the difference
						System.out.println();
					}
					// Both are Reals
					else
					{
						double num1Real = num1.getReal(); // Creates a complex num1 out of num1.get real
						Complex ComplexNum1Img = new Complex(num1Real, 0);
						double num2Real = num2.getReal(); // Creates a complex num2 out of num2.get real
						Complex ComplexNum2Img = new Complex(num2Real, 0);
						
						Complex difference = Subtraction(ComplexNum1Img, ComplexNum2Img);
						System.out.print(difference.toString());
						System.out.println();
					}
				} // end of else if
				
				// Checks if the operator is the equal and calls the equals method
				else if (operator.equals("="))
				{
					if((num1 instanceof Complex) && (num2 instanceof Complex)) // num1 and num2 is complex
					{
						Complex num1Img = (Complex) num1; // Casts num 1 and num2 as Complex numbers
						Complex num2Img = (Complex) num2;
						boolean equals = CheckEqual(num1Img, num2Img); // Checks if they're equal
						System.out.println(equals); // prints it
					}
					else if((num1 instanceof Real) && (num2 instanceof Complex)) // Num1 is real and num2 is complex
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						Complex num2Img = (Complex) num2; // Casts num2 as complex number
						boolean equals = CheckEqual(num1Img, num2Img); // Checks if they're equal
						System.out.println(equals);  // prints it
					}
					else if((num1 instanceof Complex) && (num2 instanceof Real)) // num1 is complex and num2 is real
					{
						Complex num1Img = (Complex) num1; // Casts as complex
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						boolean equals = CheckEqual(num1Img, num2Img); // Checks if they're equal
						System.out.println(equals); // prints it
					}
					else // Num1 and num2 are both real
					{
							double realNum1 = num1.getReal(); // Creates num1 and num2 complex number obj
							double realNum2 = num2.getReal();
							Complex num1Img = new Complex(realNum1, 0);
							Complex num2Img = new Complex(realNum2, 0);
							boolean equals = CheckEqual(num1Img, num2Img); // Checks if they're equal
							System.out.println(equals); // prints it
					} 
				} // end of else if
				
				// Checks if the operator is the division and calls the division method
				else if (operator.equals("/"))
				{
					if((num1 instanceof Complex) && (num2 instanceof Complex))
					{
						Complex num1Img = (Complex) num1;
						Complex num2Img = (Complex) num2;
						Complex quotient = Division(num1Img, num2Img);
						System.out.println(quotient);
					}
					else if ((num1 instanceof Real) && (num2 instanceof Complex))
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						Complex num2Img = (Complex) num2; // Casts num2 as complex number
						Complex quotient = Division(num1Img, num2Img);
						System.out.println(quotient);
					}
					else if ((num1 instanceof Complex) && (num2 instanceof Real)) // num1 is complex and num2 is real
					{
						Complex num1Img = (Complex) num1; // Casts as complex
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						Complex quotient = Division(num1Img, num2Img);
						System.out.println(quotient);
					}
					else // num1 and num2 are real
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						Complex quotient = Division(num1Img, num2Img);
						System.out.println(quotient);
					}
				} // end of else if
				
				// Checks if the operator is the less than and calls the lessThan method
				else if (operator.equals("<"))
				{
					if ((num1 instanceof Complex) || (num2 instanceof Complex)) // both are complex
					{
						Complex num1Img = (Complex) num1;
						Complex num2Img = (Complex) num2;
						boolean lessThan = CheckLessThan(num1Img, num2Img);
						System.out.println(lessThan);
					}
					else if ((num1 instanceof Real) && (num2 instanceof Complex)) // num1 is real and num2 is complex
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						Complex num2Img = (Complex) num2; // Casts num2 as complex number
						boolean lessThan = CheckLessThan(num1Img, num2Img);
						System.out.println(lessThan);
					}
					else if ((num1 instanceof Complex) && (num2 instanceof Real)) // num1 is complex and num2 is real
					{
						Complex num1Img = (Complex) num1; // Casts as complex
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						boolean lessThan = CheckLessThan(num1Img, num2Img);
						System.out.println(lessThan);
					}
					else // num1 and num2 are real
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						boolean lessThan = CheckLessThan(num1Img, num2Img);
						System.out.println(lessThan);
					}
				} // end of else-if
				
				// Checks if the operator is the greater than and call the greaterThan Method
				else if(operator.equals(">"))
				{
					if((num1 instanceof Complex) && (num2 instanceof Complex)) // Both are complex
					{
						Complex num1Img = (Complex) num1;
						Complex num2Img = (Complex) num2;
						boolean greaterThan = CheckGreaterThan(num1Img, num2Img);
						System.out.println(greaterThan);
					}
					else  if((num1 instanceof Real) && (num2 instanceof Complex)) // num1 is real and num2 is complex
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						Complex num2Img = (Complex) num2; // Casts num2 as complex number)
						boolean greaterThan = CheckGreaterThan(num1Img, num2Img);
						System.out.println(greaterThan);
					}
					else if((num1 instanceof Complex) && (num2 instanceof Real)) // num1 is complex and num2 is real
					{
						Complex num1Img = (Complex) num1; // Casts as complex
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						boolean greaterThan = CheckGreaterThan(num1Img, num2Img);
						System.out.println(greaterThan);
					}
					else // both are real
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						boolean greaterThan = CheckGreaterThan(num1Img, num2Img);
						System.out.println(greaterThan);
					}
				} // end of else-if
				// Checks if the operator is * if it is calls the multiplication method
				else if (operator.equals("*"))
				{
					if ((num1 instanceof Complex) && (num2 instanceof Complex)) // Both Complex
					{
						Complex num1Img = (Complex) num1;
						Complex num2Img = (Complex) num2;
						Complex product = Multiplication(num1Img, num2Img);
						System.out.print(product.toString());
						System.out.println();
					}
					else  if((num1 instanceof Real) && (num2 instanceof Complex)) // num1 is real and num2 is complex
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						Complex num2Img = (Complex) num2; // Casts num2 as complex number)
						Complex product = Multiplication(num1Img, num2Img);
						System.out.print(product.toString());
						System.out.println();
					}
					else if((num1 instanceof Complex) && (num2 instanceof Real)) // num1 is complex and num2 is real
					{
						Complex num1Img = (Complex) num1; // Casts as complex
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						Complex product = Multiplication(num1Img, num2Img);
						System.out.print(product.toString());
						System.out.println();
					}
					else // Both are real
					{
						double num1Real = num1.getReal(); // Creates a num1 complex number
						Complex num1Img = new Complex(num1Real,0);
						double num2Real = num2.getReal(); // Create complex number object
						Complex num2Img = new Complex(num2Real, 0);
						Complex product = Multiplication(num1Img, num2Img);
						System.out.print(product.toString());
						System.out.println();
					}
				}
				
				
					
				}
				
				catch (Exception e) {
					if (input.hasNext()) {
						System.out.println();
						input.nextLine();
					}
					else
						return;
				}

			} // end of while
			
		} // end of if()
		// Close scanners
		input.close();
		scnr.close();
	} // end of main()
	
	// Does the addition between the two numbers
	public static Complex Addition(Complex num1, Complex num2)
	{
		double real = num1.getReal()+ num2.getReal(); // Add the real parts of the numbers
		double imag = num1.getImg() + num2.getImg(); // Add the imaginary parts of the numbers
		
		//System.out.println("real: "+ real + "img: "+ imag);
		Complex sum = new Complex(real, imag); // Creates a complex sum with the added terms
		
		return sum;
	} // end of Addition()
	
	//Does the subtraction between the two numbers
	public static Complex Subtraction(Complex num1, Complex num2)
	{
		double real = num1.getReal() - num2.getReal(); // Subs the real parts of the numbers
		double imag = num1.getImg() - num2.getImg(); // subs the imaginary parts of the numbers
		
		Complex difference = new Complex(real, imag); // creates a complex difference with the subtracted  terms
		
		return difference;
	} // end of Subtraction()
	
	// Does the multiplication between the two numbers
	public static Complex Multiplication(Complex num1, Complex num2)
	{
		
		double real = (num1.getReal() * num2.getReal()) - (num1.getImg() * num2.getImg()); // does FOIL for the real parts
		
		double imag =  (num1.getImg() * num2.getReal()) + (num1.getReal() * num2.getImg()); // does FOIL for the imaginary parts
		
		Complex product = new Complex(real, imag); // creates a complex product
		
		return product;
	} // end of Multiplication()

	// Compares to see if the numbers are equal to  each other and returns true or false
	public static Boolean CheckEqual(Complex num1, Complex num2)
	{
		if (num1.equals(num2)) //  checks if num 1 == num2 using the .equals method from Complex
			return true;
		
		return false; // Returns false else
	} // end of Division()
	
	public static Complex Division(Complex num1, Complex num2)
	{
		// Division for (a+bi)/(c + di)
		double a = num1.getReal();
		double b = num1.getImg();
		double c = num2.getReal();
		double d = num2.getImg();
		
		
		//Complex division formula: ((ac + bd) + (bc - ad)i)/(c^2 + d^2)
		double denom = (Math.pow(c,2) + Math.pow(d,2));
		double rNumerator = a*c + b*d;
		double iNumerator = b*c - a*d;
		
		rNumerator /= denom;
		iNumerator /= denom;

		return new Complex(rNumerator, iNumerator);
		
	} // end of Division()
	
	public static Boolean CheckLessThan(Complex num1, Complex num2)
	{
		if (num1.compareTo(num2) == -1)
			return true;
		return false;
	}
	
	public static Boolean CheckGreaterThan(Complex num1, Complex num2)
	{
		if (num1.compareTo(num2) == 1)
			return true;
		return false;
	}
} // end of Main()
