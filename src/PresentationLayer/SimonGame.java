package PresentationLayer;

import BusinessLayer.SimonSequence;
import BusinessLayer.User;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.multi.MultiTextUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.function.IntConsumer;
public class SimonGame {


    private Label playerInfo = new Label("");
    private Font favoriteFont = new Font("Arial", Font.PLAIN, 50);
    private Label title = new Label("Simon Says!");
    private static final int width = 200;
    private static final int height = 200;
    public SimonGame() {
        title.setBounds(250, 10, 400, 100);
        title.setFont(favoriteFont);
        title.setForeground(Color.cyan.darker());
        dansMethod(0, x -> {
            try {
                buttonClick(x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        frame.setBackground(Color.WHITE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to end the game?", "End Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                    System.exit(0);
                }
            }
        });
        sequence.addRandomly();
        frame.add(title);
        playerInfo.setBounds(700, 250, 500 , 100);
        updateInfo();
        playerInfo.setFont(new Font("Arial", Font.PLAIN, 30));
        name.setBounds(700, 70, 100 , 30);
        text.setBounds(700, 100, 100 , 30);
        accept.setBounds(700, 140, 100, 30);
        accept.addActionListener(acceptListener);
        frame.add(text);
        frame.add(name);
        frame.add(playerInfo);
        frame.add(accept);
        frame.setSize(1500,900);//500 width and 600 height
        btnArr.stream().forEach(jb -> jb.addActionListener(listener));
        btnArr.stream().forEach(jb -> frame.add(jb));
        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            Thread.currentThread().sleep(1000);
            changeColorToBright(sequence.getSequence().get(0));
        }
        catch (Exception e){
        }
        nextTurn();
    }

    private JButton start = new JButton("Start");
    private SimonSequence sequence = new SimonSequence(new User(String.format("Guest%d", (int)(Math.random()*10000))));
    private int color = 0;
    private JFrame frame = new JFrame();
    private List<JButton> btnArr = new LinkedList<>(){
        {
            JButton j1 = new JButton();
            j1.setBackground(sequence.getColorsMap().get(0));
            j1.setBounds(200, 200, width, height);
            JButton j2 = new JButton();
            j2.setBackground(sequence.getColorsMap().get(1));
            j2.setBounds(400, 200, width, height);
            JButton j3 = new JButton();
            j3.setBackground(sequence.getColorsMap().get(2));
            j3.setBounds(200, 400, width, height);
            JButton j4 = new JButton();
            j4.setBackground(sequence.getColorsMap().get(3));
            j4.setBounds(400, 400, width, height);
            add(j1);
            add(j2);
            add(j3);
            add(j4);
        }
    };
    private IntConsumer clickButton;
    private Label name = new Label("name");
    private JTextArea text = new JTextArea("");
    private JButton accept = new JButton("accept");
    private boolean isDone = true;
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idx;
            try {
                if (e.getSource() instanceof JButton) {
                    idx = getBtnIdx((JButton) e.getSource());
                    color = idx;
                    clickButton.accept(0);
                }
            }
            catch (Exception ex){

            }
        }
    };

    ActionListener acceptListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequence.getUser().setName(text.getText());
            updateInfo();
        }
    };

    public void dansMethod(int x, IntConsumer aMethod) {
        clickButton = aMethod;
    }


    private void changeColorToBright(int idx) throws InterruptedException {
        btnArr.get(idx).setBackground(new Color(9304234));
        Thread.currentThread().sleep(1000);
        btnArr.get(idx).setBackground(sequence.getColorsMap().get(idx));
        Thread.currentThread().sleep(500);
    }

    public void changeName(String name){
        sequence.getUser().setName(name);
    }

    private int getBtnIdx(JButton b){
        int ret = 0;
        for (JButton button : btnArr){
            if(b.equals(button)){
                return ret;
            }
            ret++;
        }
        return ret;

    }

    public void messageBox(String message,String title){
        JOptionPane.showMessageDialog(null, message,title,1);
    }

    private void buttonClick(int x) throws InterruptedException {
        if(!sequence.isMatching(color)){
            messageBox("wrong input, you lost", "Game Ended");
            over = true;
            System.exit(0);
        }
    }

    public void nextTurn()  {
        try {
            if (sequence.isSequenceOver()) {
                sequence.addRandomly();
                List<Integer> list = sequence.getSequence();
                for (int i : list) {
                    changeColorToBright(i);
                }
                updateInfo();
            }
        }
        catch (Exception e){
        }
    }

    private void updateInfo(){
        playerInfo.setText(String.format("sequence: %d \n\n\n" +
                "player: %s", sequence.getSequence().size(), sequence.getUser().getName()));
    }
    private boolean over = false;

    public boolean notOver(){
        return !over;
    }
}
