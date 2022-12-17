package SudokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registration extends JDialog {
    private JPanel regPanel;
    private JTextField tfEmail;
    private JTextField tfName;
    private JTextField tfPhone;
    private JTextField tfUserID;
    private JTextField tfGame;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton registerButton;
    private JButton cancelButton;


    Connection connection;
    Statement statement;
    String query;
    PreparedStatement preparedStatement;


    public Registration(JFrame parent){
       super(parent);
       setTitle("Welcome To Registration Form");
       setContentPane(regPanel);
       setMinimumSize(new Dimension(850, 525));
       setModal(true);
       setLocationRelativeTo(parent);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);



       registerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   registerUser();
               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }
       });
       cancelButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               dispose();
           }
       });
       setVisible(true);
   }

    public void registerUser() throws SQLException {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String userId = tfUserID.getText();
        String game = tfGame.getText();
        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || userId.isEmpty() || game.isEmpty() || address.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please Enter All Fields : ",
                    "Try Again!",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        if(!password.equals(confirmPassword)){
            JOptionPane.showMessageDialog(
                    this,
                    "Confirm Password Doesn't Match : ",
                    "Try Again !",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        user = addUserToDatabase(name, email, phone, userId, game, address, password);
        if(user!=null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to register new user : ",
                    "Try Again !",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return;
    }



    public User user;

    private User addUserToDatabase(String name, String email, String phone, String userId, String game, String address, String password) throws SQLException {

        final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
        final String USERNAME = "root";
        final String PASSWORD = "Apple@0827";
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            query = "insert into players(name, email, phone, userId, game, address, password)"+"values(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, userId);
            preparedStatement.setString(5, game);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.userId = userId;
                user.game = game;
                user.address = address;
                user.password = password;
            }
            statement.close();
            connection.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void main(String[] args) {
        Registration myForm = new Registration(null);
        User user = myForm.user;
        if(user!=null) System.out.println("Successfully Registered : "+user.name);
        else System.out.println("Registration Failed : ");
    }
}
