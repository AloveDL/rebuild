package task2.GUI;

import task2.db.DB;
import task2.entity.Person;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author 27635
 */
public class Window {
	private JFrame newframe ;
	protected DefaultListModel listModel;
	protected JList jList;
	protected DB db;

	public Window() throws Exception {
		db = new DB();
		newframe = new JFrame("个人通讯录管理系统");
		jList = new JList();
	}

	public void show() throws SQLException {

		newframe.setSize(1000,800);
		newframe.setResizable(false);
		int windowWidth = newframe.getWidth();
		int windowHeight = newframe.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		newframe.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		newframe.add(panel);
		placeComponents(panel);
		newframe.setVisible(true);
	}

	private void placeComponents(JPanel panel) throws SQLException {

		panel.setLayout(null);

		JButton addButton = new JButton("+");
		addButton.setFont(new Font("宋体",Font.PLAIN, 30));
		addButton.setBounds(260,30,60,35);
		panel.add(addButton);

		JLabel imgLabel = new JLabel();
		ImageIcon img = new ImageIcon("src\\task2\\images\\image.jpg");
		imgLabel.setIcon(img);
		imgLabel.setBounds(535,100,230,230);
		imgLabel.setVisible(false);
		panel.add(imgLabel);

		Font labelfont = new Font("宋体",Font.PLAIN, 20);

		JLabel name = new JLabel("联系人姓名:");
		name.setBounds(420,350,130,40);
		name.setFont(labelfont);
		panel.add(name);

		JLabel gender = new JLabel("联系人性别:");
		gender.setBounds(420,400,130,40);
		gender.setFont(labelfont);
		panel.add(gender);

		JLabel phonenumber = new JLabel("联系人号码:");
		phonenumber.setBounds(420,450,130,40);
		phonenumber.setFont(labelfont);
		panel.add(phonenumber);

		name.setVisible(false);
		gender.setVisible(false);
		phonenumber.setVisible(false);

		JTextField jTextFieldName = new JTextField();
		jTextFieldName.setBounds(550,350,300,40);
		jTextFieldName.setFocusable(false);
		jTextFieldName.setFont(labelfont);
		panel.add(jTextFieldName);

		JTextField jTextFieldGender = new JTextField();
		jTextFieldGender.setBounds(550,400,300,40);
		jTextFieldGender.setFocusable(false);
		jTextFieldGender.setFont(labelfont);
		panel.add(jTextFieldGender);

		JTextField jTextFieldNumber = new JTextField();
		jTextFieldNumber.setBounds(550,450,300,40);
		jTextFieldNumber.setFocusable(false);
		jTextFieldNumber.setFont(labelfont);
		panel.add(jTextFieldNumber);

		jTextFieldName.setVisible(false);
		jTextFieldGender.setVisible(false);
		jTextFieldNumber.setVisible(false);

		JButton finishButton = new JButton("完成");
		finishButton.setBounds(750,500,100,30);
		panel.add(finishButton);
		finishButton.setVisible(false);

		JTextArea jTextArea = new JTextArea();
		jTextArea.setBounds(400,70,500,500);
		jTextArea.setEnabled(false);
		panel.add(jTextArea);

		listModel = new DefaultListModel();
		Vector<Person> personlist = db.QueryAll();
		for (int i = 0; i < personlist.size(); i++) {
			Person tempperson= personlist.get(i);
			listModel.add(i,tempperson.getName());

		}

		jList.setModel(listModel);
		jList.setBounds(30,70,300,800);
		jList.setFont(labelfont);
		panel.add(jList);

		JLabel personLabel = new JLabel("通讯录：");
		Font font = new Font("宋体",Font.PLAIN, 20);
		personLabel.setFont(font);
		personLabel.setBounds(30,20,300,40);
		panel.add(personLabel);

		JButton updateButton = new JButton("修改");
		updateButton.setBounds(400,580,100,30);
		panel.add(updateButton);

		JButton deleteButton = new JButton("删除");
		deleteButton.setBounds(800,580,100,30);
		panel.add(deleteButton);

		deleteButton.setVisible(false);
		updateButton.setVisible(false);
		finishButton.setVisible(false);

		jList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				String sname = (String) jList.getSelectedValue();
				Person person = null;
				try {
					person = db.Query(sname);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				jTextFieldName.setVisible(true);
				jTextFieldGender.setVisible(true);
				jTextFieldNumber.setVisible(true);
				name.setVisible(true);
				gender.setVisible(true);
				phonenumber.setVisible(true);
				imgLabel.setVisible(true);
				deleteButton.setVisible(true);
				updateButton.setVisible(true);
				jTextFieldName.setText(person.getName());
				jTextFieldGender.setText(person.getGender());
				jTextFieldNumber.setText(person.getPhoneNumber());
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});


		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Person tempPerson = new Person();
					AddWindow addWindow = new AddWindow();
					addWindow.createPerson(listModel);
					listModel.addElement(tempPerson.getName());
					jList.setModel(listModel);

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				finishButton.setVisible(true);
				jTextFieldName.setFocusable(true);
				jTextFieldGender.setFocusable(true);
				jTextFieldNumber.setFocusable(true);

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String deleteName = (String) jList.getSelectedValue();
				try {
					db.Delete(deleteName);
					imgLabel.setVisible(false);
					jTextFieldName.setVisible(false);
					jTextFieldGender.setVisible(false);
					jTextFieldNumber.setVisible(false);

					name.setVisible(false);
					gender.setVisible(false);
					phonenumber.setVisible(false);

					deleteButton.setVisible(false);
					updateButton.setVisible(false);
					listModel.remove(jList.getSelectedIndex());


				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		});

		finishButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					db.Update((String) jList.getSelectedValue(),jTextFieldName.getText(),jTextFieldGender.getText(),jTextFieldNumber.getText());
					listModel.setElementAt(jTextFieldName.getText(),jList.getSelectedIndex());
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				jTextFieldName.setFocusable(false);
				jTextFieldGender.setFocusable(false);
				jTextFieldNumber.setFocusable(false);
				finishButton.setVisible(false);
			}
		});
	}

	public class AddWindow {
		private JFrame frame;

		public AddWindow() throws Exception {
			frame = new JFrame("添加联系人");
			db = new DB();
		}

		public void createPerson(DefaultListModel listmodel) {

			frame.setSize(400, 300);
			frame.setResizable(false);
			int windowWidth = frame.getWidth();
			int windowHeight = frame.getHeight();
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
			JPanel panel = new JPanel();
			frame.add(panel);
			placeComponents(panel, listmodel);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);

		}

		public void placeComponents(JPanel panel, DefaultListModel listModel) {

			panel.setLayout(null);

			Font labelfont = new Font("宋体", Font.PLAIN, 20);
			JLabel name = new JLabel("姓名:");
			name.setBounds(20, 30, 100, 30);
			name.setFont(labelfont);
			panel.add(name);

			JLabel gender = new JLabel("性别:");
			gender.setBounds(20, 80, 100, 30);
			gender.setFont(labelfont);
			panel.add(gender);

			JLabel number = new JLabel("号码:");
			number.setBounds(20, 130, 100, 30);
			number.setFont(labelfont);
			panel.add(number);

			JTextField nameField = new JTextField();
			nameField.setBounds(120, 30, 200, 30);
			nameField.setFont(labelfont);
			panel.add(nameField);

			JTextField genderField = new JTextField();
			genderField.setBounds(120, 80, 200, 30);
			genderField.setFont(labelfont);
			panel.add(genderField);

			JTextField numberField = new JTextField();
			numberField.setBounds(120, 130, 200, 30);
			numberField.setFont(labelfont);
			panel.add(numberField);

			JButton button = new JButton("添加");
			button.setBounds(230, 200, 90, 30);
			panel.add(button);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = nameField.getText();
					try {
						if(db.isexist(name)){
							JOptionPane.showMessageDialog(null, "已存在该联系人", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}

					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
					String gender = genderField.getText();
					String number = numberField.getText();
					try {
						db.Insert(name, gender, number);
						listModel.addElement(name);
						frame.dispose();
					} catch (SQLException throwables) {
						throwables.printStackTrace();
					}
				}
			});

		}

	}

	public static void main(String[] args) throws Exception {
		Window window =  new Window();
		window.show();
	}

}


