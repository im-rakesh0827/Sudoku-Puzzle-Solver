package SudokuGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends JDialog implements ActionListener{

    private JButton buttonReset;
    private JButton buttonUndo;
    private JButton buttonReDo;
    private JButton buttonSolution;
    private JButton buttonCheckMoves;
    private JButton buttonExit;
    private JPanel sudokuPanel;

//    All the button :
    private JButton r0c0;
    private JButton r0c1;
    private JButton r0c2;
    private JButton r0c3;
    private JButton r0c4;
    private JButton r0c5;
    private JButton r0c6;
    private JButton r0c7;
    private JButton r0c8;


    private JButton r1c0;
    private JButton r1c1;
    private JButton r1c2;
    private JButton r1c3;
    private JButton r1c4;
    private JButton r1c5;
    private JButton r1c6;
    private JButton r1c7;
    private JButton r1c8;


    private JButton r2c0;
    private JButton r2c1;
    private JButton r2c2;
    private JButton r2c3;
    private JButton r2c4;
    private JButton r2c5;
    private JButton r2c6;
    private JButton r2c7;
    private JButton r2c8;


    private JButton r3c0;
    private JButton r3c1;
    private JButton r3c2;
    private JButton r3c3;
    private JButton r3c4;
    private JButton r3c5;
    private JButton r3c6;
    private JButton r3c7;
    private JButton r3c8;


    private JButton r4c0;
    private JButton r4c1;
    private JButton r4c2;
    private JButton r4c3;
    private JButton r4c4;
    private JButton r4c5;
    private JButton r4c6;
    private JButton r4c7;
    private JButton r4c8;
    private JButton r5c0;
    private JButton r5c1;
    private JButton r5c2;
    private JButton r5c3;
    private JButton r5c4;
    private JButton r5c5;
    private JButton r5c6;
    private JButton r5c7;
    private JButton r5c8;
    private JButton r6c0;
    private JButton r6c1;
    private JButton r6c2;
    private JButton r6c3;
    private JButton r6c4;
    private JButton r6c5;
    private JButton r6c6;
    private JButton r6c7;
    private JButton r6c8;
    private JButton r7c0;
    private JButton r7c1;
    private JButton r7c2;
    private JButton r7c3;
    private JButton r7c4;
    private JButton r7c5;
    private JButton r7c6;
    private JButton r7c7;
    private JButton r7c8;

    private JButton r8c0;
    private JButton r8c1;
    private JButton r8c2;
    private JButton r8c3;
    private JButton r8c4;
    private JButton r8c5;
    private JButton r8c6;
    private JButton r8c7;
    private JButton r8c8;


    JButton predefinedBtn [] = {
            r0c3, r0c6, r0c7, r0c8,
            r1c0, r1c4, r1c5, r1c6, r1c8,
            r2c0, r2c2, r2c4, r2c7,
            r3c4, r3c5, r3c7,
            r4c1, r4c2, r4c6, r4c7,
            r5c1, r5c3, r5c4,
            r6c1, r6c4, r6c6, r6c8,
            r7c0, r7c3, r7c4, r7c8,
            r8c0, r8c1, r8c2, r8c5
    };

    JButton emptyButton [] = {
            r0c0, r0c1, r0c2, r0c4, r0c5,
            r1c1, r1c2, r1c3, r1c7,
            r2c1, r2c3, r2c5, r2c6, r2c8,
            r3c0, r3c1, r3c2, r3c3, r3c6, r3c8,
            r4c0, r4c3, r4c4, r4c5, r4c8,
            r5c0, r5c2, r5c5, r5c6, r5c7, r5c8,
            r6c0, r6c2, r6c3, r6c5, r6c7,
            r7c1, r7c2, r7c5, r7c6, r7c7,
            r8c3, r8c4, r8c6, r8c7, r8c8
    };



    JButton buttonArray [][] ={
            {r0c0, r0c1, r0c2, r0c3, r0c4, r0c5, r0c6, r0c7, r0c8},
            {r1c0, r1c1, r1c2, r1c3, r1c4, r1c5, r1c6, r1c7, r1c8},
            {r2c0, r2c1, r2c2, r2c3, r2c4, r2c5, r2c6, r2c7, r2c8},
            {r3c0, r3c1, r3c2, r3c3, r3c4, r3c5, r3c6, r3c7, r3c8},
            {r4c0, r4c1, r4c2, r4c3, r4c4, r4c5, r4c6, r4c7, r4c8},
            {r5c0, r5c1, r5c2, r5c3, r5c4, r5c5, r5c6, r5c7, r5c8},
            {r6c0, r6c1, r6c2, r6c3, r6c4, r6c5, r6c6, r6c7, r6c8},
            {r7c0, r7c1, r7c2, r7c3, r7c4, r7c5, r7c6, r7c7, r7c8},
            {r8c0, r8c1, r8c2, r8c3, r8c4, r8c5, r8c6, r8c7, r8c8},
    };

    private String  ansArray [][] = {
            {"2","9","8","5","1","6","7","3","4"},
            {"4","1","3","2","7","8","5","6","9"},
            {"7","5","6","3","4","9","1","2","8"},
            {"8","2","1","4","3","5","6","9","7"},
            {"5","3","4","6","9","7","2","8","1"},
            {"9","6","7","1","8","2","3","4","5"},
            {"1","4","2","8","5","3","9","7","6"},
            {"3","7","5","9","6","4","8","1","2"},
            {"6","8","9","7","2","1","4","5","3"}
    };

    public SudokuFrame(JFrame parent){
        super(parent);
        setTitle("Welcome To Puzzle Game : ");
        setContentPane(sudokuPanel);
        setMinimumSize(new Dimension(800, 700));
        setModal(true);
        setLocationRelativeTo(parent);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for(JButton button : predefinedBtn){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                            SudokuFrame.this,
                            button.getText()+" Is Already Allocated Here !",
                            "Sudoku Puzzle",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });
        }

        for(JButton button:emptyButton){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                            SudokuFrame.this,
                            "Choose A Digit First & Try Again !",
                            "Sudoku Puzzle",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });
        }

        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonSolution)){
            for(int i=0; i<buttonArray.length; i++){
                for(int j=0; j<ansArray.length; j++){
//                    buttonArray[i][j]
                }
            }
        }

    }

    public static void main(String[] args) {

        SudokuFrame sudokuFrame = new SudokuFrame(null);

    }
}

