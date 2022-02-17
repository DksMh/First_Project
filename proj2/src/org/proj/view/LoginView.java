package org.proj.view;

import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.NowView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.model.UserDto;

public class LoginView extends GameView{
	boolean flag = false;
	ImageIcon backgroundImg;
	JLabel background;

	JPanel loginbg;
	JPanel login;
	JPanel newUserPane;

	JButton LoginBtn = new RoundJButton("로그인");
	JButton signupBtn = new RoundJButton("회원가입");
	ImageIcon passIcon = new ImageIcon("images/login/eye.png");
	JToggleButton passLookBtn = new JToggleButton(passIcon);
	
	JTextField inputID = new JTextField(15);
//	JTextField inputPW = new JTextField(15);
	// 02.10 변경
	JPasswordField inputPW = new JPasswordField(15);
	
	JTextField textname = new JTextField(20);
	JTextField textid = new JTextField(15);
	JTextField textpass = new JTextField(15);
	JTextField textage = new JTextField(15);
	JButton idcheck = new JButton("check");
	JButton signup = new RoundJButton("확인");
	JButton cancel = new RoundJButton("취소");
	boolean signupState = false;

	public LoginView() {
//		display();
		LoginBtn.addActionListener(this);
		signupBtn.addActionListener(this);
		passLookBtn.addActionListener(this);
		signup.addActionListener(this);
		cancel.addActionListener(this);
		idcheck.addActionListener(this);
	}

	@Override
	public void display() {
		init();

		backgroundImg = new ImageIcon("images/login/mainBack.png");
		background = new JLabel(backgroundImg);

		background.add(loginbg);
		this.setLayout(null);
		this.add(background);

		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		loginbg.setBounds(FRAME_WIDTH / 2 - 320 / 2, FRAME_HEIGHT / 2 - 370 / 2, 320, 370);
//		login.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);

//		LoginBtn.addActionListener(this);
//		signupBtn.addActionListener(this);
//		signup.addActionListener(this);
//		cancel.addActionListener(this);
//		idcheck.addActionListener(this);
	}

	public void init() {
		setLoginBox();
		setCreateBox();
	}

	public void setLoginBox() {

		loginbg = new JPanel();
		loginbg.setBackground(new Color(255, 255, 255, 150));
		loginbg.setLayout(null);

		login = new JPanel();
		login.setBackground(new Color(255, 255, 255, 255));
		login.setLayout(null);

		JLabel loginLabel = new JLabel("로그인");
		JLabel loginID = new JLabel("I D");
		JLabel loginPW = new JLabel("PW");

		loginLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		loginID.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		loginPW.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		login.add(loginLabel);
		login.add(loginID);
		login.add(loginPW);

		loginLabel.setBounds(300 / 2 - 50, 20, 100, 40);
		loginID.setBounds(300 / 2 - 120, 350 / 2 - 80, 40, 40);
		loginPW.setBounds(300 / 2 - 120, 350 / 2 - 20, 40, 40);
		
		inputPW.setEchoChar('*');
		
		inputID.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		inputPW.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		login.add(passLookBtn);
		login.add(inputID);
		login.add(inputPW);
		login.add(LoginBtn);
		login.add(signupBtn);
		loginbg.add(login);

		login.setBounds(10, 10, 300, 350);
		
		
		LoginBtn.setBackground(new Color(82, 206, 105));
		LoginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		LoginBtn.setForeground(Color.white);
		LoginBtn.setBorderPainted(false);
		LoginBtn.setContentAreaFilled(false);

		signupBtn.setBackground(new Color(82, 206, 105));
		signupBtn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		signupBtn.setForeground(Color.white);
		signupBtn.setBorderPainted(false);
		signupBtn.setContentAreaFilled(false);
		
		// 02.10 변경
		passLookBtn.setSelected(false);
		passLookBtn.setBorderPainted(false);
		passLookBtn.setContentAreaFilled(false);
		passLookBtn.setBounds(260,160,30,30);

		LoginBtn.setBounds(300 / 2 - 140, 350 - 60, 120, 50);
		signupBtn.setBounds(300 / 2 + 10, 350 - 60, 120, 50);
		inputID.setBounds(300 / 2 - 70, 350 / 2 - 80, 180, 40);
		inputPW.setBounds(300 / 2 - 70, 350 / 2 - 20, 180, 40);
	}

	public void setCreateBox() {
		newUserPane = new JPanel();

		JLabel newUserTitle = new JLabel("회원가입");
		JLabel lblName = new JLabel("이름 : ");
		JLabel lblId = new JLabel("아이디 : ");
		JLabel lblPass = new JLabel("비밀번호 : ");
		JLabel lblAge = new JLabel("나이 : ");

		newUserPane.setLayout(null);
		newUserPane.setBackground(new Color(255, 255, 255, 255));

		newUserPane.add(newUserTitle);
		newUserPane.add(lblName);
		newUserPane.add(lblId);
		newUserPane.add(lblPass);
		newUserPane.add(lblAge);

		newUserTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPass.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblAge.setFont(new Font("맑은 고딕", Font.BOLD, 18));

		newUserTitle.setBounds(90, 20, 150, 40);
		lblName.setBounds(48, 100, 60, 20);
		lblId.setBounds(28, 140, 80, 20);
		lblPass.setBounds(10, 180, 100, 20);
		lblAge.setBounds(48, 220, 60, 20);

		newUserPane.add(textname);
		newUserPane.add(textid);
		newUserPane.add(idcheck);
		newUserPane.add(textpass);
		newUserPane.add(textage);
		newUserPane.add(signup);
		newUserPane.add(cancel);
//		loginbg.add(newUserPane);
		newUserPane.setBounds(10, 10, 300, 350);

		textname.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textid.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textpass.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textage.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		textname.setBounds(100, 100, 120, 25);
		textid.setBounds(100, 140, 120, 25);
		idcheck.setBounds(225, 140, 70, 25);
		textpass.setBounds(100, 180, 120, 25);
		textage.setBounds(100, 220, 120, 25);

		signup.setBounds(10, 290, 120, 50);
		cancel.setBounds(160, 290, 120, 50);

		idcheck.setBackground(new Color(184, 218, 235));
		idcheck.setFont(new Font("맑은 고딕", Font.BOLD, 13));
//		idcheck.setForeground(Color.white);
		idcheck.setBorderPainted(false);
//		idcheck.setContentAreaFilled(false);

		signup.setBackground(new Color(82, 206, 105));
		signup.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		signup.setForeground(Color.white);
		signup.setBorderPainted(false);
		signup.setContentAreaFilled(false);

		cancel.setBackground(new Color(82, 206, 105));
		cancel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		cancel.setForeground(Color.white);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == LoginBtn) {
			String id = inputID.getText();
			// 02.10 변경
//			String pw = inputPW.getText();
			String pw = new String(inputPW.getPassword());

			if (id.length() != 0 && pw.length() != 0) {
				inputID.setText("");
				inputPW.setText("");
				UserDto user = new UserDto(0, null, id, pw, 0);

				Controller c = Controller.getController();
				c.login(user);

			} else {
				JOptionPane.showMessageDialog(this, new JLabel("ID와 PW를 모두 입력하세요!", javax.swing.SwingConstants.CENTER), "로그인",JOptionPane.PLAIN_MESSAGE);
			}
		}

		if (e.getSource() == idcheck) {
			String checkid = textid.getText();
			if (checkid.length() != 0) {
				Controller c = Controller.getController();
				c.idcheck(checkid);

			}else {
				JOptionPane.showMessageDialog(this, new JLabel("아이디를 입력해주세요.", 
						javax.swing.SwingConstants.CENTER), "중복확인",JOptionPane.PLAIN_MESSAGE);
			}
		}

		if (e.getSource() == signup) {

			if (signupState) {
				String newName = textname.getText();
				String newId = textid.getText();
				String newPw = textpass.getText();
				int newAge = 0;
				try {
					newAge = Integer.parseInt(textage.getText());
				} catch (NumberFormatException e0) {
					JOptionPane.showMessageDialog(this,  new JLabel("나이는 숫자만 입력하세요!", 
							javax.swing.SwingConstants.CENTER),"회원가입",JOptionPane.PLAIN_MESSAGE);
				}

				if (newName.length() != 0 && newId.length() != 0 && newPw.length() != 0
						&& textage.getText().length() != 0) {

					UserDto user = new UserDto(0, newName, newId, newPw, newAge);
					Controller c = Controller.getController();
					boolean result = c.signup(user);
					if (result) {
						textname.setText("");
						textid.setText("");
						textpass.setText("");
						textage.setText("");
					}
					signupState = false;
				} else {
					JOptionPane.showMessageDialog(this,  new JLabel("모든 정보를 입력해 주세요", 
							javax.swing.SwingConstants.CENTER),"회원가입",JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,  new JLabel("ID 중복체크를 해주세요!", 
						javax.swing.SwingConstants.CENTER),"회원가입",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(e.getSource() == passLookBtn) {
			if(passLookBtn.isSelected() == true) {
				inputPW.setEchoChar((char)0);
			}else {
				inputPW.setEchoChar('*');
			}
		}
		
		if (e.getSource() == signupBtn) {
			loginbg.remove(login);
			loginbg.add(newUserPane);
			revalidate();
			repaint();
		}

		if (e.getSource() == cancel) {
			textname.setText("");
			textid.setText("");
			textpass.setText("");
			textage.setText("");
			loginbg.remove(newUserPane);
			loginbg.add(login);
			revalidate();
			repaint();
		}

	}
	
	public void respidcheck(boolean approval) {
		if(approval) {
			signupState = true;
			JOptionPane.showMessageDialog(this, new JLabel("사용할 수 있는 아이디입니다!", javax.swing.SwingConstants.CENTER), "중복확인",JOptionPane.PLAIN_MESSAGE);
		}else {
			signupState = false;
			JOptionPane.showMessageDialog(this, new JLabel("사용할 수 없는 아이디입니다!", javax.swing.SwingConstants.CENTER), "중복확인",JOptionPane.PLAIN_MESSAGE);
		}
	}
	public String toBGM() {
		return "main.wav";
	}

}
