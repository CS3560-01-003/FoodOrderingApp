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
    private JTextField firstNametxt;
    private JTextField middleNametxt;
    private JTextField lastNametxt;
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
                String firstName,middleName,lastName,address, user_name, password;

                boolean is_buyer = buyerRadioButton.isSelected();    //initialize
                boolean is_seller = sellerRadioButton.isSelected();

                firstName = firstNametxt.getText();
                middleName = middleNametxt.getText();
                lastName= lastNametxt.getText();
                address = addresstxt.getText();
                user_name = usernametxt.getText();
                password = passwordtxt.getText();

                if(firstName.isEmpty() ||  //condition for if user doesn't fill out all the fields
                        lastName.isEmpty() ||
                        address.isEmpty() ||
                        user_name.isEmpty() ||
                        password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all information", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {

                    try
                    {
                        PreparedStatement st = dbconn.prepareStatement("SELECT * FROM users WHERE username = ?");

                        st.setString(1, user_name);

                        ResultSet res = st.executeQuery(); //Making sure user chooses a unique username by excuting the above
                                                          // query of selecting a username and if it exits prompt user to choose another

                        if (res.next())
                        {
                            JOptionPane.showMessageDialog(null, "Username is already in use. Please choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                        {                        //if username is unique then insert the data into users table.
                            PreparedStatement st2 = dbconn.prepareStatement("INSERT INTO users (firstname, middlename, lastname, address, username, password, accesstype) VALUES (?, ?, ?, ?, ?, ?, ?)");

                            st2.setString(1, firstName);
                            st2.setString(2, middleName);
                            st2.setString(3, lastName);
                            st2.setString(4, address);
                            st2.setString(5, user_name);
                            st2.setString(6, password);

                            int accessType = 1;
                            if (is_seller)
                            {
                                accessType = 2;
                            }

                            st2.setInt(7, accessType);

                             //st2.executeQuery();

                            int res2 = st2.executeUpdate();


                            JOptionPane.showMessageDialog(null, "User account created.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                            firstNametxt.setText(null);
                            middleNametxt.setText(null);
                            lastNametxt.setText(null);
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
                newLoginInstance.setVisible(true); //going back Login Window



            }
        });
    }
}
