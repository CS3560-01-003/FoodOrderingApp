package LoginP;

import AdminP.*;
import UserP.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame
{
    private JPanel Main2;
    private JRadioButton buyerRadioButton;
    private JRadioButton sellerRadioButton;
    private JButton loginButton;
    private JPasswordField passwordTextField;
    private JTextField userNameTextField;
    private JButton registerButton;
    private JLabel message;

    DBConnection conn;
    static JFrame frame = new JFrame("Login");
    public static void main(String[] args)
    {

        frame.setContentPane(new Login().Main2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, 750, 328);
        frame.setVisible(true);
    }

    public Login()
    {
        //initComponents();
        DBConnection conn = new DBConnection(); // connecting to database
            loginButton.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {

                    System.out.println("connected"); //connection test*/

                    //get the inputted information from the text fields
                    String user_name = userNameTextField.getText();
                    String password = passwordTextField.getText();
                    boolean is_buyer = buyerRadioButton.isSelected();
                    boolean is_seller = sellerRadioButton.isSelected();
                    System.out.println(user_name);
                    System.out.println(password);

                    try
                    {
                        String accessType = "1";
                        if(is_seller) { accessType = "2"; }
                        Connection dbconn = conn.connectDB();
                        String query = "SELECT * FROM user WHERE loginUser = ?  AND loginPassword = ?  AND accessType = ? ";
                        PreparedStatement pst = dbconn.prepareStatement(query);
                       // Statement pst = dbconn.createStatement(query);

                   //  pst.setString(1, user_name);

                        pst.setString(1, user_name);
                        pst.setString(2, password);
                     //  pst.setInt(3,accessType);
                        pst.setString(3, accessType);

                        ResultSet rs = pst.executeQuery();

                        if(rs.next()){
                            dispose();
                            frame.setVisible(false);
                            if (accessType == "1")
                            {

                                // cross checking incoming date with the db users data - is admin in db
                                // if matches login

                                // else we ask the user to renter credentials

                                DashBoard userDashboard = new DashBoard();
                                // set it visible and kill the previous form/window
                                userDashboard.setVisible(true);
                                //success
                                // Render the dashboard that belongs to user view
                                // display data for the user

                            }
                            else {
                                // cross checking incoming date with the db users data
                                // if matches login
                                // else we ask the user to renter credentials

                                //success
                                // Render the dashboard that belongs to user view
                                // display data for the user
                                Admin adminPanelInstance = new Admin();
                                adminPanelInstance.setTitle("DashBoard");
                                adminPanelInstance.setVisible(true);


                                /*adminPanelInstance.dispose();
                                adminPanelInstance.setVisible(false);*/
                            }
                            frame.dispose();
                            frame.setVisible(false);

                        }
                        else {
                            /*JOptionPane.showMessageDialog(null,"Invalid username and/ password","",
                                JOptionPane.ERROR_MESSAGE );*/
                            message.setText("Invalid credentials, please try again");
                            message.setForeground(Color.RED);
                            //frame.pack();

                        }
                        rs.close();
                        pst.close();
                    }catch(SQLException sqlException){
                        sqlException.printStackTrace();
                    }

                }

            });
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Registration newRInstance = new Registration();
                newRInstance.setLocationRelativeTo(null);
                newRInstance.setTitle("Registration");
                newRInstance.setVisible(true);

                frame.dispose();
                frame.setVisible(false);
            }
        });
    }

    public void goBack(){
        Dimension size = Main2.getMaximumSize();
        frame.setSize(size);
        //frame.add(Main2);
        frame.setVisible(true);
    }

    private void createUIComponents()
    {
        // TODO: place custom component creation code here
    }
}
