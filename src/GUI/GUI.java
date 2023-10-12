package GUI;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import Caculation.Arithmetic;
import constants.CommonConstants;

public class GUI extends JFrame implements ActionListener{
	private final SpringLayout springLayout = new SpringLayout();
	private Arithmetic arithmetic;
	private JTextField jtextfield;
	private JButton[] buttons;
	
	//Flags
	private boolean pressedOperator = false;
	private boolean pressedEquals = false;
	
	
	
	public GUI() {
		super(CommonConstants.APP_NAME);
		setSize(CommonConstants.APP_SIZE[0],CommonConstants.APP_SIZE[1]);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(springLayout);
		
		arithmetic = new Arithmetic();
		guiComponents();
	}
	
	public void guiComponents() {
		//Display Components
		addDisplayGui();
		//Button Components
		addButtonGui();
	}
	public void addDisplayGui() {
		JPanel jpanel = new JPanel();
		jtextfield = new JTextField(CommonConstants.TEXTFIELD_LENGTH);
		jtextfield.setFont(new Font("Mutable", Font.PLAIN, 36));
		jtextfield.setEditable(false);
		jtextfield.setText("0");
		jtextfield.setHorizontalAlignment(SwingConstants.RIGHT);
		jpanel.add(jtextfield);
		this.getContentPane().add(jpanel);
		springLayout.putConstraint(SpringLayout.NORTH, jpanel, 36, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, jpanel, 33, SpringLayout.WEST, this);
		
	}
	public void addButtonGui() {
		GridLayout gridLayout = new GridLayout(4,4);
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(gridLayout);
		buttons = new JButton[16];
		for (int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(getButtonLabel(i));
			button.setFont(new Font("Mutable", Font.PLAIN, 46));
			button.addActionListener(this);
			
			buttonpanel.add(button);
		}
		//Sets gap between buttons
		gridLayout.setHgap(25);
		gridLayout.setVgap(25);
		
		this.getContentPane().add(buttonpanel);
		
		springLayout.putConstraint(SpringLayout.NORTH, buttonpanel, 150, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, buttonpanel, 33, SpringLayout.WEST, this);
		
	}

	public String getButtonLabel(int index) {
		switch(index) {
			case 0: 
				return "7";
			case 1:
				return "8";
			case 2: 
				return "9";
			case 3: 
				return "/";
			case 4:
				return "4";
			case 5:
				return "5";
			case 6:
				return "6";
			case 7:
				return "x";
			case 8:
				return "1";
			case 9:
				return "2";
			case 10:
				return "3";
			case 11:
				return "-";
			case 12:
				return "0";
			case 13:
				return ".";
			case 14:
				return "+";
			case 15:
				return "=";
		}
		return "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	String command = e.getActionCommand();
	if(command.matches("[0-9]")) {
		 if(pressedEquals || pressedOperator || jtextfield.getText().equals("0")) {
			 jtextfield.setText(command);
		 }
		 else {
			 jtextfield.setText(jtextfield.getText() + command );
		 }
		 pressedOperator = false;
		 pressedEquals = false;
	}else if(command.equals("=")) {
		//Calculate
		arithmetic.setNum2(Double.parseDouble(jtextfield.getText()));
		double result =0;
		switch(arithmetic.getArithmeticSymbols()) {
			case '+':
				result = arithmetic.add();
				break;
			case '-':
				result = arithmetic.subtract();
				break;
			case 'x':
				result = arithmetic.multiply();
				break;
			case '/':
				result = arithmetic.divide();
				break;
		}
		//update textfield
		jtextfield.setText(Double.toString(result));
		
		
		//update flags
		pressedEquals = true;
		pressedOperator = false;
	}else if (command.equals(".")) {
		if(!jtextfield.getText().contains(".")) {
			jtextfield.setText(jtextfield.getText()+ command );
		}
	}else {
		if(!pressedOperator) {
			arithmetic.setNum1(Double.parseDouble(jtextfield.getText()));
			arithmetic.setArithmeticSymbols(command.charAt(0)); 
			
			//update flags
			pressedOperator = true;
			pressedEquals = false;
		}
	}
	
	}
}
