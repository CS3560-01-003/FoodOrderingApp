package LoginP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration extends JFrame
{
    private JTextField nametxt;
    private JTextField emailtxt;
    private JTextField address;
    private JTextField username;
    private JRadioButton buyerRadioButton;
    private JButton backButton;
    private JButton registerButton;
    private JPanel register;
    private JPasswordField password;
    private JTextField addresstxt;
    private JTextField usernametxt;
    private JTextField passwordtxt;
    private JRadioButton sellerRadioButton;
    //private JPanel


    public Registration(){
        Dimension size = register.getMaximumSize();
        setSize(size);
        add(register);

        DBConnection conn = new DBConnection();
        Connection dbconn = conn.connectDB();


        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name,email,address, user_name, password;

                boolean is_buyer = buyerRadioButton.isSelected();    //initialize
                boolean is_seller = sellerRadioButton.isSelected();

                name = nametxt.getText();
                email = emailtxt.getText();
                address = addresstxt.getText();
                user_name = usernametxt.getText();
                password = passwordtxt.getText();

                if(name.isEmpty() ||  //condition for if user doesn't fill out all the fields
                        email.isEmpty() ||
                        address.isEmpty() ||
                        user_name.isEmpty() ||
                        password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all information", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {

                    try
                    {
                        PreparedStatement st = dbconn.prepareStatement("SELECT * FROM user WHERE userName = ?");

                        st.setString(1, user_name);

                        ResultSet res = st.executeQuery(); //Making sure user chooses a unique username by excuting the above
                                                          // query of selecting a username and if it exits prompt user to choose another

                        if (res.next())
                        {
                            JOptionPane.showMessageDialog(null, "Username is already in use. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                        {                        //if username is unique then insert the data into users table.
                            PreparedStatement st2 = dbconn.prepareStatement("INSERT INTO user (userName,userAddress, loginUser, userEmail, loginPassword, accessType) VALUES (?, ?, ?, ?, ?, ?)");

                            st2.setString(1, name);
                            st2.setString(2, address);
                            st2.setString(3, user_name);
                            st2.setString(4, email);
                            st2.setString(5, password);

                            int accessType = 1;
                            if (is_seller)
                            {
                                accessType = 2;
                            }

                            st2.setInt(6, accessType);

                             //st2.executeQuery();

                            int res2 = st2.executeUpdate();


                            JOptionPane.showMessageDialog(null, "User account created.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                            nametxt.setText(null);
                            emailtxt.setText(null);
                            addresstxt.setText(null);
                            usernametxt.setText(null);
                            passwordtxt.setText(null);

                            st2.close();
                        }

                        res.close();
                        st.close();
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });



        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // for registrationForm
                dispose();
                register.setVisible(false);
                Login newLoginInstance = new Login();
               // newLoginInstance.setLocationRelativeTo(null);
               // newLoginInstance.setTitle("Main2");
                newLoginInstance.goBack(); //going back Login Window



            }
        });
    }
}
