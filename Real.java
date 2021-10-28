// Name:				Ishva Patel
// NetID:				irp190001
// Program Overview:	Process the real numbers from the input file

import java.io.*;
import java.text.DecimalFormat;

public class Real{
	// Variables
	protected double real = 0.0;
	
	// Default Constructor
	public Real() {}
	
	public Real(double r) {real = r;} // Overloaded Constructor
	
	// accessor
	public double getReal() { return real;}
	
	// mutator
	public void setReal(double r) {real = r;}

	// toString method for Real numbers
	@Override
	public String toString()
	{
		DecimalFormat format = new DecimalFormat("#0.00");
		format.format(real);
		String str = "";
		if (real == 0)
			return str;
		else
			return format.format(real);
	} // end of toString()
	
	// equals method for Real numbers
	@Override
	public boolean equals (Object o)
	{
		if (o instanceof Real)
		{
			Real R = (Real) o;
			if (real == R.real)
				return true;
		}
		return false;
	} // end of equals
	
	
	public int compareTo(Object o)
	{
		if (o instanceof Real)
		{
			if(this.getReal() == ((Real)o).getReal())
			{
				return 0;
			}
			else if (this.getReal() > ((Real)o).getReal())
			{
				return 1;
			}
			else if (this.getReal() < ((Real)o).getReal())
				return -1;
		}
		
		return -2;
	}
	
	@Override
	public int hashCode() {
		return 1;
	}
}
