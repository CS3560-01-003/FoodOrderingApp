package UserP;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import LoginP.*;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cart extends JFrame
{

	private JPanel contentPane;
	private JTable table_1;
	private String itemName;
	private double price;
	private int qty;
	private int ID;
	private double total;
	private JLabel lblNewLabel_1;

	DBConnection conn = new DBConnection();
	Connection dbconn = conn.connectDB();

	private static final DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Cart frame = new Cart();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cart()
	{
		this.setTitle("Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));


		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 547, 153);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.setForeground(Color.BLACK);

		scrollPane.setViewportView(table_1);


		JLabel lblNewLabel = new JLabel("Total:");
		lblNewLabel.setBounds(299, 222, 46, 14);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(355, 222, 50, 14);
		contentPane.add(lblNewLabel_1);

		loadTable(dbconn);

		JButton btnNewButton = new JButton("Checkout");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				dispose();
				Checkout checkoutInstance = new Checkout();
				checkoutInstance.setVisible(true);
			}
		});
		btnNewButton.setBounds(409, 245, 148, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Remove Item");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				int row = table_1.getSelectedRow();
				Object x = table_1.getValueAt(row, 0);
				String name = x.toString();
				System.out.println(name);

				try
				{
					String query = "SELECT * FROM menuItems WHERE itemName = ?";
					PreparedStatement st = dbconn.prepareStatement(query);

					st.setString(1, name);

					ResultSet rs = st.executeQuery();
					if (rs.next())
					{
						int menuID = rs.getInt("itemID");
						System.out.println(menuID);
						PreparedStatement pst = dbconn.prepareStatement("delete FROM orderItems where itemID = ?");
						pst.setInt(1, menuID);
						pst.executeUpdate();
						loadTable(dbconn);
						JOptionPane.showMessageDialog(null, "Item Deleted");
						pst.close();

					} else
					{
						JOptionPane.showMessageDialog(contentPane, "No Item Found");
					}
				}
				catch (SQLException e1)
				{

				}
			}
		});
		btnNewButton_1.setBounds(409, 175, 149, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Continue Shopping");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				dispose();
				DashBoard dashboardInstance = new DashBoard();
				dashboardInstance.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(409, 209, 148, 23);
		contentPane.add(btnNewButton_2);
	}

	public void loadTable(Connection dbconn)
	{
		table_1.setModel(new DefaultTableModel(
				new Object[][]{},
				new String[]{
						"Item", "Quantity", "Price"
				}
		));
		try
		{
			String query = "SELECT * FROM orderItems";
			PreparedStatement st = dbconn.prepareStatement(query);

			ResultSet rs = st.executeQuery();

			while (rs.next())
			{
				ID = rs.getInt("itemID");
				qty = rs.getInt("itemQuantity");


				query = "SELECT * FROM menuItems WHERE itemID = " + ID + "";
				PreparedStatement sta = dbconn.prepareStatement(query);

				ResultSet rse = sta.executeQuery();
				if (rse.next())
				{
					itemName = rse.getNString("itemName");
					price = qty * (rse.getDouble("itemPrice"));
				}
				Object[] data = {itemName, qty, price};
				((DefaultTableModel) table_1.getModel()).addRow(data);
			}
			total = getTotal();
			lblNewLabel_1.setText(df.format(total));
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public double getTotal()
	{
		total = 0;
		for (int i = 0; i < table_1.getRowCount(); i++)
		{
			total = total + ((double) table_1.getValueAt(i, 2));
		}
		return total;
	}
}
