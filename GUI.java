
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
//import javax.swing.plaf.ColorChooserUI;


public class GUI{
    final private Font MAINFONT = new Font("Lato", Font.BOLD, 16);
    JTextArea chatBox;
    String text;
    public void GUI1(){
        //TEXT
        JLabel textMes = new JLabel();
        textMes.setFont(MAINFONT);

        //TEXT AREA
        JTextArea MainChat = new JTextArea();
        MainChat.setEditable(false);
        MainChat.setFont(MAINFONT);
        MainChat.setBackground(Color.lightGray);
        MainChat.setBounds(175, 0, 500, 600);
        MainChat.setLineWrap(true);
        

        //CHAT BOX AND SEND BUTTON AND OPTIONS
        chatBox = new JTextArea();
        chatBox.setFont(MAINFONT);

        JButton btnSend = new JButton("Send");
        btnSend.setFont(MAINFONT);
        btnSend.setSize(30, 30);
        btnSend.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                text = chatBox.getText();
                if(chatBox.getText().length() < 1){

                }
                else if(chatBox.getText().equals(".clear")){
                    MainChat.setText("cleared all messages\n");
                    chatBox.setText("");
                }
                else{
                    MainChat.append("\t User1: " + text + "\n");
                    chatBox.setText("");
                }
                
            }
            

        });

        JButton btnOptions = new JButton("Options");
        btnOptions.setFont(MAINFONT);
        btnOptions.setSize(30, 30);
        //btnOptions.addActionListener(new ActionListener());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3, 5, 5));
        bottomPanel.add(chatBox);
        bottomPanel.add(btnOptions);
        bottomPanel.add(btnSend);

        //LEFT PANEL OF PEOPLE
        ImageIcon icon = new ImageIcon("profile.png");
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back       

        JLabel label1 = new JLabel();
        label1.setIcon(icon);
        label1.setText("\nGROUP NAME");
        label1.setFont(MAINFONT);

        JPanel leftPanel = new JPanel();
        leftPanel.setSize(75, 900);
        leftPanel.setBackground(new Color(128, 128, 225));
        leftPanel.setLayout(new BorderLayout(10, 15));
        leftPanel.add(label1,BorderLayout.NORTH);

        


        //MAIN PANNEL
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(128, 128, 225));
        panel.add(bottomPanel, BorderLayout.SOUTH);
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(MainChat, BorderLayout.CENTER);
        
        

        JFrame frame = new JFrame();
        frame.setSize(900, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Chats");
        frame.setSize(900, 1000);
        frame.setVisible(true);
        frame.add(panel);
        //frame.add(CenterTextBox, BorderLayout.CENTER);


    }
    public static void main(String[] args){
        GUI myGUI = new GUI();
        myGUI.GUI1();
    }
}