/**
 * The type1 program implements java graphic packages to create a
 * virtual keyboard which contains uppercase & lowercase letters, and special characters
 * that can be typed with and without the use of the shift key
 * @author Ebrahim Shahid
 * @version 1.0
 * @since 03-08-2020
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class type1 extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;

    // creating map for buttons
    Map<Integer, JButton> map = new HashMap<>();

    // Individual keyboard rows
    String firstRow[] = { "~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "fill",
            "BackSpace" };
    String secondRow[] = { "Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\" };
    String thirdRow[] = { "Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "fill",
            "fill", "Enter" };
    String fourthRow[] = { "Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?", "blank", "^" };
    String fifthRow[] = { "blank", "blank", "fill", "fill", "fill", "fill", "fill", "fill", "fill",
            "fill", "", "<", "v", ">" };
    String strText = "";
    // all keys without shift key press
    String noShift = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
    // special characters on keyboard that has to be addressed during key press
    String specialChars = "~-+[]\\;',.?";
    int keycode;
    JTextArea text = new JTextArea();

    // Jbuttons corresponding to each individual rows
    JButton first[];
    JButton second[];
    JButton third[];
    JButton fourth[];
    JButton fifth[];

    Color cc = new JButton().getBackground(); // default color of the button to be repainted when key released

    /**
     * This is the main driver class that makes 1 object keybaord for testing
     * @param args
     */
    public static void main(String[] args) throws ExceptionInInitializerError {
        // launch typing word
        type1 a = new type1();
    }// end of main

    /**
     * No argument constructor to create frame
     */
    public type1() throws InvalidParameterException {
        super("Type - My JAC444"); // program label
        gradientBg(); // Calling gradientBg method
        init(); // calling gradientBg method
    }// end of Constructor

    /** i
     * The init method will be used to display a message on the GUI explaining the application
     * JButtons will also create the virtual buttons of the keyboard (hidden shift keys)
     * @return Nothing
     */
    public final void init(){

        // set the info label on top
        JLabel info = new JLabel(
                "<html>&nbsp;&nbsp;Type some text using your keyboard.The keys "
                        + "you press will be highlighted and text will be displayed."
                        + "<br> &nbsp;&nbsp;Note : Clicking the buttons with your "
                        + "mouse will not perform any action. <br><br> </html>");

        // set the bold font for info
        info.setFont(new Font("Verdana", Font.BOLD, 12));

        // set the layout and place component in place and pack it
        setLayout(new BorderLayout());

        // Various panel for the layout
        JPanel jpNorth = new JPanel();
        JPanel jpWest = new JPanel();
        JPanel jpEast = new JPanel();
        JPanel jpCenter = new JPanel();
        JPanel jpKeyboard = new JPanel(new GridBagLayout());
        JPanel jpNote = new JPanel();

        // adding Panels
        add(jpNorth, BorderLayout.NORTH);
        add(jpNote);

        // adding Panels
        add(jpWest, BorderLayout.WEST);
        add(jpEast, BorderLayout.EAST);

        // adding Panels
        add(jpCenter, BorderLayout.CENTER);
        add(jpKeyboard, BorderLayout.SOUTH);

        // setting layout and adding BorderLayout
        jpNorth.setLayout(new BorderLayout());
        jpNorth.add(info, BorderLayout.SOUTH);

        // setting layout and adding BorderLayout
        jpCenter.setLayout(new BorderLayout());

        //jpCenter.add(text, BorderLayout.WEST);
        jpCenter.add(text, BorderLayout.CENTER);

        // setting preferredsize
        jpCenter.setPreferredSize(new Dimension(200, 140));

        // creating buttons
        first = new JButton[firstRow.length];
        second = new JButton[secondRow.length];
        third = new JButton[thirdRow.length];
        fourth = new JButton[fourthRow.length];
        fifth = new JButton[fifthRow.length];

        // adding buttons
        addKeys(jpKeyboard, 0, firstRow, first);
        addKeys(jpKeyboard, 1, secondRow, second);
        addKeys(jpKeyboard, 2, thirdRow, third);
        addKeys(jpKeyboard, 3, fourthRow, fourth);
        addKeys(jpKeyboard, 4, fifthRow, fifth);

        // setting preferredSize
        jpKeyboard.setPreferredSize(new Dimension(350, 200));

        // fixing background and panels overflow
        info.setOpaque(false);
        jpNote.setOpaque(false);
        jpWest.setOpaque(false);
        jpEast.setOpaque(false);
        jpNorth.setOpaque(false);
        jpCenter.setOpaque(false);
        jpKeyboard.setOpaque(false);

        // add listeners
        text.addKeyListener(this);
        // setting default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set non re-sizable
        this.setResizable(false);
        // set size of the content pane ie frame
        this.getContentPane().setPreferredSize(new Dimension(700, 310));
        // putting everything together
        pack();
        // brings this Window to the front and may make it the focused Window
        this.toFront();
        // Window appears center
        this.setLocationRelativeTo(null);
        // setting visible
        this.setVisible(true);
    }// end of init


    /**
     * This is the background gradient method which customizes pre-existing methods
     *
     */
    public final void gradientBg(){
        JPanel contentPane = new JPanel() { // Creating gradient background
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2d = (Graphics2D) grphcs;
                Dimension d = this.getSize();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(),
                        0, d.height, getBackground().darker().darker());

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, d.width, d.height);

                super.paintComponent(grphcs);
            }
        };
        contentPane.setOpaque(false);
        setContentPane(contentPane);
    }// end of gradientBg

    /**
     * KeyPressed method for signalling to the compiler that a key was pressed and to keep track of each button pressed
     * @param evt
     */
    @Override //Overriden from java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
    public void keyPressed(KeyEvent evt) { // Invoked when a key has been pressed.
        keycode = evt.getKeyCode();
        strText = String.format("%s", KeyEvent.getKeyText(evt.getKeyCode()));

        JButton btn = map.get(keycode);
        if (btn != null) {
            map.get(keycode).setBackground(new Color(135,206,235));
        }
    }// end of key pressed

    /**
     * KeyReleased method to signal to the compiler that the character pressed was released
     * however, holding down a key will repeat it
     * @param evt
     */
    @Override
    public void keyReleased(KeyEvent evt) { // Invoked when a key has been released
        keycode = evt.getKeyCode();
        strText = String.format("%s", KeyEvent.getKeyText(evt.getKeyCode()));

        JButton btn = map.get(keycode);
        if (btn != null) {
            map.get(keycode).setBackground(Color.WHITE);
        }
    }// end of keyReleased

    /**
     * the keyTyped method will make the compiler consider every character typed as an event to
     * eventually highlight that on the GUI
     * @param evt
     */
    @Override
    public void keyTyped(KeyEvent evt) { // Invoked when a key has been typed

        strText = String.format("%s", evt.getKeyChar());

    }// end of key typed


    /**
     * The addKeys method will be used place the keybaord buttons on a panel with white background
     * @param parent
     * @param row
     * @param keys
     * @param buttons
     * @return Nothing
     */
    protected final void addKeys(JPanel parent, int row, String[] keys, JButton[] buttons) { // adds the buttons to create screen keyboard
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;

        int gap = 0;
        for (int index = 0; index < keys.length; index++) {
            String key = keys[index];
            if ("blank".equalsIgnoreCase(key)) {
                gbc.gridx++;
            } else if ("fill".equalsIgnoreCase(key)) {
                gbc.gridwidth++;
                gap++;
            } else {
                JButton btn = new JButton(key);
                buttons[index] = btn;
                parent.add(btn, gbc);
                gbc.gridx += gap + 1;
                gbc.gridwidth = 1;
                gap = 0;

                btn.setBackground(Color.WHITE);
                map.put(getKeyCode(key), btn);
            }
        }
    }// end of addKeys


    /**
     * The getKeyCode method checks for virtual key code events using switch cases
     * @param key
     * @return Nothing
     */
    private int getKeyCode(String key) {
        switch (key) {
            case "BackSpace":
                return KeyEvent.VK_BACK_SPACE;
            case "Tab":
                return KeyEvent.VK_TAB;
            case "Caps":
                return KeyEvent.VK_CAPS_LOCK;
            case "Enter":
                return KeyEvent.VK_ENTER;
            case "Shift":
                return KeyEvent.VK_SHIFT;
            case "":
                return KeyEvent.VK_SPACE;
            case "+":
                return KeyEvent.VK_EQUALS;
            case ":":
                return KeyEvent.VK_SEMICOLON;
            case "\"":
                return KeyEvent.VK_QUOTE;
            case "?":
                return KeyEvent.VK_SLASH;
            case "~":
                return KeyEvent.VK_BACK_QUOTE;
            case "^":
                return KeyEvent.VK_UP;
            case "v":
                return KeyEvent.VK_DOWN;
            case "<":
                return KeyEvent.VK_LEFT;
            case ">":
                return KeyEvent.VK_RIGHT;
            default:
                return (int) key.charAt(0);
        }// end of switch
    }// end of getKeyCode
}// end of type class
