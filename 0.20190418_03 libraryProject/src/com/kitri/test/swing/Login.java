package com.kitri.test.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.Cursor;

public class Login extends JPanel implements ActionListener{

	public JPanel contentPane;
	public JTextField idTF;
	public JTextField pwTF;
	public JButton loginBt;

	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});	
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		
		
		setBounds(100, 100, 800, 800);
		
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel labelid = new JLabel("\uC544\uC774\uB514 ");
		labelid.setFont(new Font("굴림", Font.PLAIN, 12));
		labelid.setForeground(SystemColor.inactiveCaptionText);
		labelid.setBounds(276, 470, 54, 36);
		add(labelid);
		
		JLabel labelpw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		labelpw.setFont(new Font("굴림", Font.PLAIN, 12));
		labelpw.setForeground(SystemColor.inactiveCaptionText);
		labelpw.setBounds(264, 529, 54, 30);
		add(labelpw);
		
		idTF = new JTextField();
		idTF.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		idTF.setText("\uB0B4\uC6A9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		idTF.setBounds(342, 474, 131, 30);
		add(idTF);
		idTF.setColumns(10);
		
		pwTF = new JTextField();
		pwTF.setText("\uB0B4\uC6A9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		pwTF.setBounds(342, 530, 131, 30);
		add(pwTF);
		pwTF.setColumns(10);
		
		loginBt = new JButton("\uB85C\uADF8\uC778");
		loginBt.setBounds(485, 478, 54, 79);
		add(loginBt);
		
		JLabel labelLb = new JLabel("\uD0A4\uD2B8\uB9AC \uB3C4\uC11C\uAD00");
		labelLb.setFont(new Font("굴림", Font.BOLD, 54));
		labelLb.setForeground(UIManager.getColor("Button.disabledForeground"));
		labelLb.setHorizontalAlignment(SwingConstants.CENTER);
		labelLb.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		labelLb.setBounds(123, 116, 552, 209);
		add(labelLb);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.infoText);
		panel.setBounds(0, 0, 784, 18);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.infoText);
		panel_1.setBounds(0, 744, 784, 18);
		add(panel_1);
		
		
		
		loginBt.addActionListener(this);
		
		idTF.setEditable(false); //초기 커서설정을 제거함 (마우스 클릭을 유도하여 초기 내용을 제거하기 위함)
		idTF.setBackground(Color.white);
		idTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idTF.setEditable(true);
				idTF.setText("");
				idTF.getCaret().setVisible(true); //커서가 보이게함
			}
		});
		pwTF.setEditable(false);
		pwTF.setBackground(Color.white);
		pwTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwTF.setEditable(true);
				pwTF.setText("");
				pwTF.getCaret().setVisible(true);
			}
		});
		
		setVisible(true);
	}//end Login


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}//end Login Class
