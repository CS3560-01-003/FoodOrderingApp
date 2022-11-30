package UserP;

import LoginP.DBConnection;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checkout extends JFrame {

	private JPanel contentPane;
	private JTextField cardNumberTxt;
	private String[] cardTypeStrings = { "Debit", "Credit"};
	private JTextField expDateTxt;
	private JTextField cvvTxt;
	private JTextField addressTxtField;
	private JTextField nameTxt;
	int ID;
	String name, cardNumber, cardType, address, orderType;
	Integer expDate, cvv;
	int numOfProducts;
	DBConnection conn = new DBConnection();
	Connection dbconn = conn.connectDB();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private LocalDateTime dateTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout frame = new Checkout();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Checkout() {
		this.setTitle("Checkout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));



		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JToggleButton pickupToggle = new JToggleButton("Pickup");
		pickupToggle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED)
				{
					addressTxtField.setEnabled(false);
					orderType = "Pick up";
				}
				else
				{
					addressTxtField.setEnabled(true);
					orderType = "Delivery";
				}
			}
		});

		GridBagConstraints gbc_pickupToggle = new GridBagConstraints();
		gbc_pickupToggle.gridwidth = 2;
		gbc_pickupToggle.insets = new Insets(0, 0, 5, 0);
		gbc_pickupToggle.gridx = 0;
		gbc_pickupToggle.gridy = 0;
		contentPane.add(pickupToggle, gbc_pickupToggle);

		JButton btnNewButton_1 = new JButton("Back To Cart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Cart cartPaneInstance = new Cart();
				cartPaneInstance.setVisible(true);
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Name");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);

		nameTxt = new JTextField();
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_nameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTxt.gridx = 1;
		gbc_nameTxt.gridy = 2;
		contentPane.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Card Number");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.LINE_START;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		cardNumberTxt = new JTextField();
		GridBagConstraints gbc_cardNumberTxt = new GridBagConstraints();
		gbc_cardNumberTxt.insets = new Insets(0, 0, 5, 0);
		gbc_cardNumberTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_cardNumberTxt.gridx = 1;
		gbc_cardNumberTxt.gridy = 3;
		contentPane.add(cardNumberTxt, gbc_cardNumberTxt);
		cardNumberTxt.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Expiration Date");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		expDateTxt = new JTextField();
		GridBagConstraints gbc_expDateTxt = new GridBagConstraints();
		gbc_expDateTxt.insets = new Insets(0, 0, 5, 0);
		gbc_expDateTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_expDateTxt.gridx = 1;
		gbc_expDateTxt.gridy = 4;
		contentPane.add(expDateTxt, gbc_expDateTxt);
		expDateTxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("CVV");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 6;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		cvvTxt = new JTextField();
		GridBagConstraints gbc_cvvTxt = new GridBagConstraints();
		gbc_cvvTxt.insets = new Insets(0, 0, 5, 0);
		gbc_cvvTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_cvvTxt.gridx = 1;
		gbc_cvvTxt.gridy = 6;
		contentPane.add(cvvTxt, gbc_cvvTxt);
		cvvTxt.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Card Type");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 7;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);

		JComboBox<Object> comboBox = new JComboBox<Object>(cardTypeStrings);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 7;
		contentPane.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_4 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 8;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);

		addressTxtField = new JTextField();
		GridBagConstraints gbc_addressTxtField = new GridBagConstraints();
		gbc_addressTxtField.insets = new Insets(0, 0, 5, 0);
		gbc_addressTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTxtField.gridx = 1;
		gbc_addressTxtField.gridy = 8;
		contentPane.add(addressTxtField, gbc_addressTxtField);
		addressTxtField.setColumns(10);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 10;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton = new JButton("Place Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{


				dateTime = LocalDateTime.now();
				expDate = Integer.parseInt(expDateTxt.getText());
				cvv = Integer.parseInt(cvvTxt.getText());
				name = nameTxt.getText();
				cardNumber = cardNumberTxt.getText();
				cardType = comboBox.getSelectedItem().toString();
				address = addressTxtField.getText();
				loadUserID(name, dbconn);
				loadQty(dbconn);

				if (name.isEmpty()||  //condition for if user doesn't fill out all the fields
						cardNumber.isEmpty() ||
						cardType.isEmpty() ||
						cvvTxt.getText().isEmpty()||
						expDateTxt.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please fill all information", "Error", JOptionPane.ERROR_MESSAGE);
				} else
				{
					try
					{

						PreparedStatement st = dbconn.prepareStatement("SELECT * FROM payment WHERE cardNumber = ?");

						st.setString(1, cardNumber);

						ResultSet res = st.executeQuery(); //Making sure user is a customer in the database

						if (res.next())
						{
							String query = "INSERT INTO customerOrder (userID, orderTime, numOfProducts, cardNumber, orderType) VALUES (?,?, ?, ?, ?)";
							PreparedStatement st2 = dbconn.prepareStatement(query);
							st2.setInt(1, ID);
							st2.setString(2, dateTime.toString());
							st2.setInt(3, numOfProducts);
							st2.setString(4, cardNumber);
							st2.setString(5, orderType);
							st2.executeUpdate();
							JOptionPane.showMessageDialog(null, "Order placed");
						} else
						{
							String query = "INSERT INTO payment (userID, customerName, cardType, cardNumber, cardExp, cardSecure) VALUES (?,?, ?, ?, ?, ?)";
							PreparedStatement st2 = dbconn.prepareStatement(query);
							st2.setInt(1, ID);
							st2.setString(2, name);
							st2.setString(3, cardType);
							st2.setString(4, cardNumber);
							st2.setInt(5, expDate);
							st2.setInt(6, cvv);
							st2.executeUpdate();
							JOptionPane.showMessageDialog(null, "Payment Success");

							query = "INSERT INTO customerOrder (userID, orderTime, numOfProducts, cardNumber, orderType) VALUES (?,?, ?, ?, ?)";
							st2 = dbconn.prepareStatement(query);
							st2.setInt(1, ID);
							st2.setString(2, dateTime.toString());
							st2.setInt(3, numOfProducts);
							st2.setString(4, cardNumber);
							st2.setString(5, orderType);
							st2.executeUpdate();
							st2.close();
							res.close();
						}

						Statement statement = dbconn.createStatement();
						statement.executeUpdate("Truncate orderItems");
						dispose();
						JOptionPane.showMessageDialog(null, "Order placed");
						Options optionFrame = new Options();
						optionFrame.setVisible(true);
					}
					catch (SQLException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 10;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

	public void loadUserID(String name, Connection dbconn) {

		try {


			String query = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement st = dbconn.prepareStatement(query);

			st.setString(1, name);

			ResultSet rs = st.executeQuery();

			if (rs.next())
			{

				ID = rs.getInt("userID");

			}
			else
			{
				JOptionPane.showMessageDialog(this, "No Item Found");
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadQty(Connection dbconn) {

		numOfProducts = 0;

		try
		{
			String query = "SELECT * FROM orderItems";
			PreparedStatement st = dbconn.prepareStatement(query);

			ResultSet rs = st.executeQuery();

			while (rs.next())
			{
				int qty = rs.getInt("itemQuantity");
				numOfProducts = numOfProducts + qty;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
