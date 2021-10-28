// Name:				Ishva Patel
// NetID:				irp190001
// Program Overview:	Processes the complex numbers from the input file

import java.io.*;
import java.text.DecimalFormat;

public class Complex extends Real {
	private double img = 0.0;
	
	
	public Complex() {} // Default constructor
	
	// Overloaded constructor
	public Complex( double r,double i) 
	{
		super(r); // get the values from Real
		img = i;
	}
	
	// accessor
	public double getImg() {return img;}
	//public double getReal() {return real;}
	
	// Mutators
	public void setImg(double i) {img = i;}
	//public void setReal(double r) {real = r;}
	
	// toString function for the Complex numbers
	@Override
	public String toString()
	{
		DecimalFormat format = new DecimalFormat("#0.00");
		if ((img == 0) && (real != 0))
			return super.toString();
		else if ((real == 0) && (img != 0))
		{
			if (img < 0) {
				return super.toString() + format.format(img)+"i";
			}
			return super.toString() + format.format(img) +"i";
		}
			
			
		else
		{
			if (img < 0) {
				return super.toString() + format.format(img)+"i";
			}
			return super.toString() +  "+" + format.format(img) + "i";
		}
		
	} // end of toString()
	
	// equals to method for Complex numbers
	@Override
	public boolean equals(Object o)
	{
		if(super.equals(o)){
			if (o instanceof Complex)
			{
				if (((Double)(this.getImg())).equals(((Complex)o).img))
					return true;
			}
		}
		
		return false;
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		if (o instanceof Complex) 
		{
			double thisMagnitude = Math.sqrt(Math.pow(this.getReal(), 2) + Math.pow(this.getImg(), 2));
			double thatMagnitude = Math.sqrt(Math.pow((((Complex)o).getReal()), 2) + Math.pow((((Complex)o).getImg()), 2));
			
			if (thisMagnitude == thatMagnitude) 
			{
				return 0;
			}
			else if (thisMagnitude > thatMagnitude) {
				return 1;
			}
			else if (thisMagnitude < thatMagnitude) {
				return -1;
			}
		}
		return -2;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
} // end of Complex()
