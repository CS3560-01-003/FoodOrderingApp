package UserP;

import LoginP.Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;

public class Options extends JFrame {

    private JPanel contentPane;

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
    public Options() {
        this.setTitle("Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 329);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 0, 0, 0));

        JButton logOffButton = new JButton("Log Off");
        logOffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                Login loginInstance = new Login();
                loginInstance.goBack();
            }
        });
        contentPane.add(logOffButton);

        JButton orderAgainButton = new JButton("Order Again");
        orderAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                DashBoard userDashboard = new DashBoard();
                userDashboard.setVisible(true);
            }
        });
        contentPane.add(orderAgainButton);
    }

}

