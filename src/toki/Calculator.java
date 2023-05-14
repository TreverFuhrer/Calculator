package toki;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener{
	
	public static void main(String[] args) {
		new Calculator();
	}

	private JFrame frame;
	private JTextField textField;
	private JButton[] buttons;
	private JButton[] bottomButtons;
	private JPanel panel;
	private JPanel zeroPanel;
	private JPanel finalButtonsPanel;
	private String operator="";
	private Double num1= 1.0, num2 = 1.0, solution = 1.0;

	public Calculator() {
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		this.frame.setSize(290, 400);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setBackground(Color.BLACK);
		this.frame.setResizable(false);
		this.frame.setLayout(null);
		
		// Set Icon
		ImageIcon image = new ImageIcon("src\\logo.png");
		this.frame.setIconImage(image.getImage());
		
		// Create Text Field For Operations
		textField = new JTextField();
		this.textField.setBounds(11, 15, 250, 50);
		this.textField.setBorder(BorderFactory.createLoweredBevelBorder());
		this.textField.setFont(new Font("SF Digital Readout", 1, 30));
		this.textField.setHorizontalAlignment(JTextField.RIGHT);
		this.textField.setEditable(false);
		
		
		// Panel
		panel = new JPanel();
		this.panel.setBounds(10, 80, 255, 205);
		this.panel.setLayout(new GridLayout(4,4,10,10));
		this.panel.setBackground(Color.BLACK);
		zeroPanel = new JPanel();
		this.zeroPanel.setBounds(10, 296, 122, 50);
		this.zeroPanel.setLayout(new GridLayout(1,1,10,10));
		this.zeroPanel.setBackground(Color.BLACK);
		finalButtonsPanel = new JPanel();
		this.finalButtonsPanel.setBounds(142, 296, 122, 50);
		this.finalButtonsPanel.setLayout(new GridLayout(1,1,10,10));
		this.finalButtonsPanel.setBackground(Color.BLACK);
		
		// Buttons
		JButton clear = new JButton("AC");
		JButton negative = new JButton("+/-");
		JButton percentage = new JButton("%");
		JButton divide = new JButton("/");
		JButton seven = new JButton("7");
		JButton eight = new JButton("8");
		JButton nine = new JButton("9");
		JButton multi = new JButton("*");
		JButton four = new JButton("4");
		JButton five = new JButton("5");
		JButton six = new JButton("6");
		JButton minus = new JButton("-");
		JButton one = new JButton("1");
		JButton two = new JButton("2");
		JButton three = new JButton("3");
		JButton plus = new JButton("+");
		JButton zero = new JButton("0");
		JButton dot = new JButton(".");
		JButton equals = new JButton("=");
		buttons = new JButton[] {clear, negative, percentage, divide, seven,
								 eight, nine, multi, four, five, six, minus,
								 one, two, three, plus};
		bottomButtons = new JButton[] {zero, dot, equals};
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setBounds(11, 35, 50, 50);
			buttons[i].setBackground(Color.GRAY);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
			if(buttons[i].getText().equals("AC") ||
					buttons[i].getText().equals("+/-") ||
					buttons[i].getText().equals("%")) {
				buttons[i].setBackground(Color.LIGHT_GRAY);
			}
			else if(buttons[i].getText().equals("/") ||
					buttons[i].getText().equals("*") ||
					buttons[i].getText().equals("-") ||
					buttons[i].getText().equals("+") ||
					buttons[i].getText().equals("=")) {
				buttons[i].setBackground(Color.ORANGE);
			}
			else if(buttons[i].getText().equals("0")) {
				buttons[i].setBounds(11, 35, 110, 50);
			}
			this.panel.add(buttons[i]);
		}
		// Zero Button
		bottomButtons[0].setBounds(11, 35, 50, 50);
		bottomButtons[0].setBackground(Color.GRAY);
		bottomButtons[0].setFocusable(false);
		bottomButtons[0].addActionListener(this);
		bottomButtons[0].setBounds(11, 35, 110, 50);
		this.zeroPanel.add(bottomButtons[0]);
		
		// Dot Button
		bottomButtons[1].setBounds(11, 35, 50, 50);
		bottomButtons[1].setBackground(Color.GRAY);
		bottomButtons[1].setFocusable(false);
		bottomButtons[1].addActionListener(this);
		bottomButtons[1].setBounds(11, 35, 110, 50);
		this.finalButtonsPanel.add(bottomButtons[1]);
		
		// Equals Button
		bottomButtons[2].setBounds(11, 35, 50, 50);
		bottomButtons[2].setBackground(Color.ORANGE);
		bottomButtons[2].setFocusable(false);
		bottomButtons[2].addActionListener(this);
		bottomButtons[2].setBounds(11, 35, 110, 50);
		this.finalButtonsPanel.add(bottomButtons[2]);
		
		
		

		this.frame.add(this.textField);
		this.frame.add(this.panel);
		this.frame.add(this.zeroPanel);
		this.frame.add(this.finalButtonsPanel);
		this.frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < this.buttons.length; i++) {
			if(e.getSource() == buttons[i]) {
				String text = this.textField.getText();
				switch(buttons[i].getText()) {
				case "AC": 
					this.textField.setText("");
					this.operator = "";
					break;
				case "+/-":
					text = this.textField.getText();
					if(!text.contains(" -")) {
						this.textField.setText("");
						this.textField.setText(" -");
						this.textField.setText(textField.getText().concat(text));
					} break;
				case "%":
					this.textField.setText(textField.getText().concat("%")); break;
				case "/":
					if(this.operator != "") {
						calculate();
						this.operator = "/";
						break;
					}
					if(text.contains("%")) {
						text = text.substring(0, text.indexOf("%")) + text.substring(text.indexOf("%") + 1);
						this.num1 = (Double.parseDouble(text) / 100);
					}
					else 
						this.num1 = Double.parseDouble(text);
					this.operator = "/";
					this.textField.setText(""); break;
				case "7":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("7")); break;
				case "8":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("8")); break;
				case "9":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("9")); break;
				case "*":
					if(this.operator != "") {
						calculate();
						this.operator = "*";
						break;
					}
					if(text.contains("%")) {
						text = text.substring(0, text.indexOf("%")) + text.substring(text.indexOf("%") + 1);
						this.num1 = (Double.parseDouble(text) / 100);
					}
					else 
						this.num1 = Double.parseDouble(text);
					this.operator = "*";
					this.textField.setText(""); break;
				case "4":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("4")); break;
				case "5":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("5")); break;
				case "6":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("6")); break;
				case "-":
					if(this.operator != "") {
						calculate();
						this.operator = "-";
						break;
					}
					if(text.contains("%")) {
						text = text.substring(0, text.indexOf("%")) + text.substring(text.indexOf("%") + 1);
						this.num1 = (Double.parseDouble(text) / 100);
					}
					else 
						this.num1 = Double.parseDouble(text);
					this.operator = "-";
					this.textField.setText(""); break;
				case "1":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("1")); break;
				case "2":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("2")); break;
				case "3":
					if(this.operator != "") 
						this.textField.setText("");
					this.textField.setText(textField.getText().concat("3")); break;
				case "+":
					if(this.operator != "") {
						calculate();
						this.operator = "+";
						break;
					}
					if(text.contains("%")) {
						text = text.substring(0, text.indexOf("%")) + text.substring(text.indexOf("%") + 1);
						this.num1 = (Double.parseDouble(text) / 100);
					}
					else 
						this.num1 = Double.parseDouble(text);
					this.operator = "+";
					this.textField.setText("");
					break;
				}
			}
		}
		for(JButton button : this.bottomButtons) {
			if(e.getSource() == button) {
				if(button.getText().equals("0")) 
					this.textField.setText(textField.getText().concat("0"));
				else if(button.getText().equals(".")) 
					this.textField.setText(textField.getText().concat("."));
				else if(button.getText().equals("=")) {
					calculate();
					this.operator = "";
				}
			}
		}
	}
	
	
	private void calculate() {
		String text = this.textField.getText();
		if(text.contains("%")) {
			text = text.substring(0, text.indexOf("%")) + text.substring(text.indexOf("%") + 1);
			this.num2 = (Double.parseDouble(text) / 100);
		}
		else 
			this.num2 = Double.parseDouble(text);
		switch(this.operator) {
		case "/":
			this.solution = (this.num1/this.num2);
			this.num1 = this.solution;
			this.textField.setText(solution.toString());
			break;
		case "*":
			this.solution = (this.num1*this.num2);
			this.num1 = this.solution;
			this.textField.setText(solution.toString());
			break;
		case "+":
			this.solution = (this.num1+this.num2);
			this.num1 = this.solution;
			this.textField.setText(solution.toString());
			break;
		case "-":
			this.solution = (this.num1-this.num2);
			this.num1 = this.solution;
			this.textField.setText(solution.toString());
			break;
		default:
			this.textField.setText(num2.toString());
		}
	}

}
