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

            //  JTable Table= new JTable();
            try
            {

                PreparedStatement pst = dbconn.prepareStatement("SELECT * FROM menuitem");

                ResultSet res = pst.executeQuery();

                table1.setModel(DbUtils.resultSetToTableModel(res)); //need to download rsxml


            }

            catch (SQLException e)
            {

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
  String item, price, description, menuId;
                item = txtItem.getText();
                price = txtPrice.getText();
                description = txtDescription.getText();
                menuId = txtId.getText();

try {
                  //given the inputted id number update the item,price and description
    PreparedStatement pst = dbconn.prepareStatement("update menuItem set item,price,description where idmenuitem = ?");
         pst.setString(1, item);
         pst.setString(2, price);
         pst.setString(3, description);
         pst.setString(4, menuId);
        pst.executeQuery();


        JOptionPane.showMessageDialog(null,"Item Updated");

          InventoryTable();// Calling this table so it can show the updated table

            txtItem.setText("");
            txtPrice.setText("");
            txtDescription.setText("");

            txtItem.requestFocus();
            txtId.setText("");
}catch(SQLException ex){


}
            }
        });
                      deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String menuID = txtId.getText();
    try{

        PreparedStatement pst = dbconn.prepareStatement("delete * FROM menuItem where idmenuitem = ?");
          pst.setString(1, menuID);
          pst.executeQuery();
        JOptionPane.showMessageDialog(null,"Item Deleted");

        InventoryTable();


        txtItem.setText("");
        txtPrice.setText("");
        txtDescription.setText("");

        txtItem.requestFocus();
        txtId.setText("");

    }catch(SQLException e1){

e1.printStackTrace();
    }
            }
        });

    }
}
