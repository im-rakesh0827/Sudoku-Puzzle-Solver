package SudokuGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserForm extends JDialog{

    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton registerButton;
    private JButton loginButton;
    private JPanel userFormPanel;
    private JButton sudokuButton;
    private JButton ticTacToeButton;

    public UserForm(JFrame parent){
        super(parent);
        setTitle("User Register/Login Form");
        setContentPane(userFormPanel);
        setMinimumSize(new Dimension(850, 525));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                user = getAuthenticateUser(email, password);

                if(user!=null){
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(
                            UserForm.this,
                            "Invalid Email Or Password !",
                            "Enter Valid Details :",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registration reg = new Registration(null);
                User user = reg.user;
                if(user!=null) System.out.println("Successfully Registered : "+user.name);
                else System.out.println("Registration Failed : ");
            }
        });
        setVisible(true);
        sudokuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SudokuFrame sudokuFrame = new SudokuFrame(null);
            }
        });
    }

    public User user;
    private User getAuthenticateUser(String email, String password) {
        user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
        final String USERNAME = "root";
        final String PASSWORD = "Apple@0827";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement  = connection.createStatement();
            String sql = "select * from sudokuPlayer where email=? and password=?";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setString(1, email);
            prepareStatement.setString(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.userId = resultSet.getString("userId");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void main(String[] args) {
        UserForm userForm = new UserForm(null);
        User user = userForm.user;
        if(user!=null){
            System.out.println("Authentication Successful : "+user.name);
            System.out.println("User's Details : ");
            System.out.println("Email : "+user.email);
            System.out.println("Phone : "+user.phone);
            System.out.println("UserId : "+user.userId);
            System.out.println("Address "+user.address);
        }else{
            System.out.println("Authentication Cancelled !");
        }

    }
}

