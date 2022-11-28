package UserP;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import LoginP.*;

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
	String name;
	int ID;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoard frame = new DashBoard();
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
	public DashBoard() {
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
			new Object[][] {},
			new String[] {
				"Item", "Description", "Price", "Image", "Availability"
			}
		));
		scrollPane.setViewportView(table_1);
		
		btnNewButton = new JButton("Add Item To Cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				qty = (int) spinner.getValue();
				int row = table_1.getSelectedRow();
				Object x = table_1.getValueAt(row, 0);
				name = ((JLabel) x).getText();
				loadItemID(name, dbconn);
				
				try {
					
					Statement st = dbconn.createStatement();
					String query = "INSERT INTO orderItems (itemID, itemQuantity) VALUES ("+ID+", "+qty+")";
					
					st.executeUpdate(query);
					
					
					System.out.println(name + " added to database");
					
					spinner.setValue(0);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 229, 173, 23);
		contentPane.add(btnNewButton);
		
		btnViewCart = new JButton("View Cart");
		btnViewCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Cart cartPaneInstance = new Cart();
				cartPaneInstance.setVisible(true);
			}
		});
		btnViewCart.setBounds(228, 229, 173, 23);
		contentPane.add(btnViewCart);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Login loginPaneInstance = new Login();
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
		
		loadData(dbconn);
		
		table_1.getColumn("Item").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Description").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Price").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Image").setCellRenderer(new myTableCellRenderer());
		table_1.getColumn("Availability").setCellRenderer(new myTableCellRenderer());
	}
	
	class myTableCellRenderer implements TableCellRenderer {
		
		private Object value;
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			table_1.setRowHeight(90);
			return (Component) value;
		}
		
		public Object getValue() {
			return value;
			
		}
	}
	
	public void loadData(Connection dbconn) {
	
		try {
			
			
			String query = "SELECT * FROM menuitems";
			PreparedStatement st = dbconn.prepareStatement(query);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next())
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
				Object[] data = {itemName, itemDescription, price, imageLabel, availability};
				((DefaultTableModel) table_1.getModel()).addRow(data);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadItemID(String name, Connection dbconn) {
		
		try {
			
			
			String query = "SELECT * FROM menuitems WHERE itemName = ?";
			PreparedStatement st = dbconn.prepareStatement(query);
			
			st.setString(1, name);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next())
			{
				
				ID = rs.getInt("itemID");
				
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
