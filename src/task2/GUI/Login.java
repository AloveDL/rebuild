package task2.GUI;

import task2.db.DB;
import task2.entity.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Login {

	private DB db;
	private JFrame frame;
	public Login() throws Exception {
		db = new DB();
		frame = new JFrame("个人通讯录管理系统");
	}
	public void show() {


		frame.setSize(350, 200);
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);

		placeComponents(panel);

		frame.setVisible(true);
	}

	private  void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel userLabel = new JLabel("User:");

		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);

		/*
		 * 创建文本域用于用户输入
		 */
		JTextField userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);

		// 输入密码的文本域
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);

		/*
		 *这个类似用于输入的文本域
		 * 但是输入的信息会以点号代替，用于包含密码的安全性
		 */
		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);

		// 创建登录按钮
		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					User user = new User(userText.getText(),passwordText.getText());
					if(db.login(user.getUsername(),user.getPassword())){
						Window window = new Window();
						window.show();
						frame.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			});
		panel.add(loginButton);

	}

	public static void main(String[] args) throws Exception {
		Login login = new Login();
		login.show();
	}
}