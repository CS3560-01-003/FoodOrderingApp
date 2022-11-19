package LoginP;

import AdminP.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import LoginP.DBConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Login extends javax.swing.JFrame
{
    private JPanel Main2;
    private JRadioButton buyerRadioButton;
    private JRadioButton sellerRadioButton;
    private JButton loginButton;
    private JButton cancelButton;
    private JPasswordField passwordTextField;
    private JTextField userNameTextField;
    private JButton registerButton;

    DBConnection conn;

   // public class GLogin()
   // {

  //  }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().Main2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
public Login()
{
    //initComponents();

        loginButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                DBConnection conn = new DBConnection(); // connecting ot database
                System.out.println("connected"); //connection test

                //get the inputted information from the text fields
                String user_name = userNameTextField.getText();
                String password = passwordTextField.getText();
                boolean is_buyer = buyerRadioButton.isSelected();
                boolean is_seller = sellerRadioButton.isSelected();

                Connection dbconn = conn.connectDB();

                int accessType = 1;
                if (is_seller)
                {
                    accessType = 2;
                }

                try
                {
                    PreparedStatement st = dbconn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ? AND accesstype = ?");

                    st.setString(1, user_name);
                    st.setString(2, password);
                    st.setInt(3, accessType);

                    ResultSet res = st.executeQuery();

                    if (res.next())
                    {
                        // display dashboard or new page after login.
                        dispose();
                    //    this.setVisible(false);
                       // Login.setVisible(false);

                    //   if (accessType == 2)
                   //    {
                   //   AdminP.Admin adminPanelInstance = new AdminP.Admin();
                     //    adminPanelInstance.setLocationRelativeTo(null);
                      //   adminPanelInstance.setTitle("Dashboard");
                      //     adminPanelInstance.setVisible(true);
                     //   }

                        /**     if(accessType == 1) {
                         Cart ShoppingCartInstance = new Cart(user_name);

                         Dashboard dashBoardInstance = new Dashboard(ShoppingCartInstance);
                         dashBoardInstance.setLocationRelativeTo(null);
                         dashBoardInstance.setTitle("Dashboard");
                         dashBoardInstance.setVisible(true);
                         } else {
                         AdminPanelHomePage adminPanelInstance = new AdminPanelHomePage();
                         adminPanelInstance.setLocationRelativeTo(null);
                         adminPanelInstance.setTitle("Dashboard");
                         adminPanelInstance.setVisible(true);
                         } **/
                    }// else
                //    {
                 //       JOptionPane.showMessageDialog(this, "Invalid username and/or password.", "", JOptionPane.ERROR_MESSAGE);
                  //  }

                    res.close();
                    st.close();

                }
                catch (SQLException ex)
                {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

             //   if (user_name.isEmpty() ||
                    //    password.isEmpty())
             //   {
                   // JOptionPane.showMessageDialog(this, "Username / Password should not be empty.", "", JOptionPane.ERROR_MESSAGE);
            //    } else
            //    {
            //        try
             //       {
             //           uLogin(user_name, password, is_buyer, is_seller);
             //       }
            //        catch (SQLException ex)
            //        {
                   //     Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            //        }
           //     }
            }

        //    private void uLogin(String user_name, String password, boolean is_buyer, boolean is_seller) throws SQLException
           // {

          //  }
            // @Override
            /**  public void actionPerformed(ActionEvent e)
             {
             String user_name = userNameTextField.getText();
             String password = passwordTextField.getText();
             boolean is_buyer = buyerRadioButton.isSelected();
             boolean is_seller = sellerRadioButton.isSelected();

             if (user_name.isEmpty() ||
             password.isEmpty())
             {
             JOptionPane.showMessageDialog(this, "Username / Password should not be empty.", "", JOptionPane.ERROR_MESSAGE);
             } else
             {
             try
             {
             uLogin(user_name, password, is_buyer, is_seller);
             }
             catch (SQLException ex)
             {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
             }
             }
             } **/


        });


    }


}

