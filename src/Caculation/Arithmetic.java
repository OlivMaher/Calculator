package Caculation;

public class Arithmetic {
	private double num1;
	private double num2;
	private char arithmeticSymbols;
	
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public char getArithmeticSymbols() {
		return arithmeticSymbols;
	}
	public void setArithmeticSymbols(char arithmeticSymbols) {
		this.arithmeticSymbols = arithmeticSymbols;
	}
	
	public double add() {
		return this.num1 +this.num2;
	}
	public double subtract() {
		return this.num1 - this.num2;
	}
	public double multiply() {
		return this.num1 * this.num2;
	}
	public double divide() {
		return this.num1 / this.num2;
	}
}
