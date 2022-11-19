package AdminP;

import LoginP.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//This is where the restaurant owners will be able to manipulate the Inventory
public class Admin
{
    private JPanel Main;
    private JTextField txtItem;
    private JTextField txtPrice;
    private JTextField txtDescription;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtId;
    private JScrollPane Inventory;
    private JButton logOutbutton;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("AdminP.Admin"); //Creating a frame for Admin
        frame.setContentPane(new AdminP.Admin().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    void InventoryTable()
    {
        DBConnection conn = new DBConnection();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        Object rowData[] = new Object[4]; //create an array for the attributes

        java.sql.Connection dbconn = conn.connectDB();
        // Connection dbconn = conn.connectDB();
        try
        {

            PreparedStatement pst = dbconn.prepareStatement("SELECT * FORM menuitem ORDER BY id ASC");

            ResultSet res = pst.executeQuery();

            while(res.next()) {  //
                rowData[0] = res.getInt("id");
                rowData[1] = res.getString("item");
                rowData[2] = res.getDouble("price");
                rowData[3] = res.getString("description");

                model.addRow(rowData);
            }
          //  res.close();
          //  pst.close();

            //table1.setModel(DbUtils.reusltSetToTableModel(rs));
        }catch (SQLException e){

            e.printStackTrace();
        }
    }

    public Admin()
    {


        DBConnection conn = new DBConnection();
        InventoryTable();
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String menuItem,price,decription;
//getting the inputted texts
                menuItem = txtItem.getText();
                price= txtPrice.getText();
                decription = txtDescription.getText();
                // ConnectDB();
                //Connecting to our database
                Connection dbconn = conn.connectDB();
                System.out.println("connected"); //connection test
                try{

                    PreparedStatement pst = dbconn.prepareStatement("insert INTO menuItem(item,price,description) " +
                            "Values(?,?,?)"); //SQL query to insert inputted items into our Table
                    pst.setString(1, menuItem);
                    pst.setString(2, price);
                    pst.setString(3, decription);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Menu Item Added!");
                    // table_load();
                    txtItem.setText("");
                    txtPrice.setText("");
                    txtDescription.setText("");
                    txtItem.requestFocus();

                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }


        });

        //Search for ID of menu then display them inside the textFields
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                String menuId = txtId.getText();
                Connection dbconn = conn.connectDB();

                try
                {

                    PreparedStatement pst = dbconn.prepareStatement("SELECT item,price,description FROM menuItem where idmenuitem = ? " );
                    pst.setString(1, menuId);
                    ResultSet rs = pst.executeQuery();
 if(rs.next()== true){

     String item = rs.getString(1);
     String price = rs.getString(2);
     String  description = rs.getString(3);

     //Set test fields with these respective

     txtItem.setText(item);
     txtItem.setText(price);
     txtDescription.setText(description);

 }

 else{   //Condition for the case in which "ID" is incorrect/does not exist.
     txtItem.setText("");
     txtDescription.setText("");
     txtItem.setText("");

     JOptionPane.showMessageDialog(null, "ID DOES NOT EXIST");
 }

                }catch(SQLException ex){

                }
            }
        });
        updateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {


            }
        });
    }
}
