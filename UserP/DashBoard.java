package UserP;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import loginP.DBConnection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.List;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;


import javax.swing.JScrollPane;
import javax.swing.JSpinner;

public class DashBoard extends JFrame {

	
	private JPanel contentPane;
	
	DBConnection conn;
	
	JLabel imageLabel;
	JLabel itemName;
	JLabel itemDescription;
	JLabel price;
	JLabel availability;
	private JButton btnNewButton;
	private JTable table_1;
	private JButton btnViewCart;
	private JButton btnLogout;
	private JLabel lblNewLabel;
	private JSpinner spinner;
	int qty;
	int orderID;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerFrame frame = new customerFrame();
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
	public customerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		DBConnection conn = new DBConnection();
		Connection dbconn = conn.connectDB();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 11, 630, 168);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setForeground(Color.BLACK);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Item", "Description", "Price", "Image", "Availability"
			}
		));
		scrollPane.setViewportView(table_1);
		
		btnNewButton = new JButton("Add Item To Cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(10, 229, 173, 23);
		contentPane.add(btnNewButton);
		
		btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				cartP.cartFrame cartPaneInstance = new cartP.cartFrame();
				cartPaneInstance.setVisible(true);
			}
		});
		btnViewCart.setBounds(228, 229, 173, 23);
		contentPane.add(btnViewCart);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				loginP.Login loginPaneInstance = new loginP.Login();
				loginPaneInstance.setVisible(true);
			}
		});
		btnLogout.setBounds(446, 229, 173, 23);
		contentPane.add(btnLogout);
		
		lblNewLabel = new JLabel("QTY:");
		lblNewLabel.setBounds(19, 190, 46, 14);
		contentPane.add(lblNewLabel);
		
		spinner = new JSpinner();
		spinner.setBounds(46, 190, 30, 20);
		contentPane.add(spinner);
		
		loadData(001, dbconn);
		table_1.setValueAt(itemName, 0, 0);
		table_1.setValueAt(itemDescription, 0, 1);
		table_1.setValueAt(price, 0, 2);
		table_1.setValueAt(imageLabel, 0, 3);
		table_1.setValueAt(availability, 0, 4);
		
		loadData(002, dbconn);
		table_1.setValueAt(itemName, 1, 0);
		table_1.setValueAt(itemDescription, 1, 1);
		table_1.setValueAt(price, 1, 2);
		table_1.setValueAt(imageLabel, 1, 3);
		table_1.setValueAt(availability, 1, 4);
		
		loadData(003, dbconn);
		table_1.setValueAt(itemName, 2, 0);
		table_1.setValueAt(itemDescription, 2, 1);
		table_1.setValueAt(price, 2, 2);
		table_1.setValueAt(imageLabel, 2, 3);
		table_1.setValueAt(availability, 2, 4);
		
		loadData(004, dbconn);
		table_1.setValueAt(itemName, 3, 0);
		table_1.setValueAt(itemDescription, 3, 1);
		table_1.setValueAt(price, 3, 2);
		table_1.setValueAt(imageLabel, 3, 3);
		table_1.setValueAt(availability, 3, 4);
		
		table_1.getColumn("Item").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Description").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Price").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Image").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Availability").setCellRenderer(new myTableCellRenderer());
		
		
	}
	
	class myTableCellRenderer implements TableCellRenderer {
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			table_1.setRowHeight(90);
			return (Component) value;
		}
	}
	
	public void loadData(int itemID, Connection dbconn) {
		
		try {
			
			
			String query = "SELECT * FROM menuitems WHERE itemID = ?";
			PreparedStatement st = dbconn.prepareStatement(query);
			st.setInt(1, itemID);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
			{
				imageLabel = new JLabel();
				itemName = new JLabel(rs.getNString("itemName"));
				itemDescription = new JLabel(rs.getNString("itemDescription"));
				price = new JLabel(rs.getString("itemPrice"));
				availability = new JLabel(rs.getNString("itemAvailability"));
				byte[] img = rs.getBytes("itemImage");
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				Image myimg = im.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon newImage = new ImageIcon(myimg);
				imageLabel.setIcon(newImage);
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "No Item Found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadOrderID(String itemID, Connection dbconn) {
		
		try {
			
			
			String query = "SELECT LAST(order_ID) FROM orderItems";
			PreparedStatement st = dbconn.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
			{
				
				orderID = rs.getInt("order_ID");
				
			}
			else
			{
				JOptionPane.showMessageDialog(this, "No Item Found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
