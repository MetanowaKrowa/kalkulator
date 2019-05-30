import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Stack;

public class Kalkulator extends JFrame implements ActionListener {

    static Stack stack = new Stack();

    JLabel display;
    double arg1 = 0, wynik;
    boolean jestkropka = false;
    JButton[] c = new JButton[10];
    JButton plus, minus, razy, dziel, rowne, kropka, back, plusminus, clear, odwrot, sqrt;
    char operator = '0';

    public Kalkulator() {
        super("Kalkulator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        this.setBounds(10, 10, 500, 500);

        Font f = new Font("Arial", Font.BOLD, 20);
        display = new JLabel("0", JLabel.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 44));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setOpaque(true);
        add(display, BorderLayout.NORTH);

        for (int i = 0; i < 10; i++) {
            c[i] = new JButton("" + i);
            c[i].addActionListener(this);
            c[i].setFont(f);
        }

        plus = new JButton("+");
        minus = new JButton("-");
        dziel = new JButton("/");
        razy = new JButton("*");
        rowne = new JButton("=");
        kropka = new JButton(".");
        back = new JButton("Bsp");
        plusminus = new JButton("+/-");
        clear = new JButton("C");
        odwrot = new JButton("1/x");
        sqrt = new JButton("sqrt");

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5, 5));
        add(p, BorderLayout.CENTER);
        p.add(back);
        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(new JLabel(""));
        p.add(clear);
        p.add(c[7]);
        p.add(c[8]);
        p.add(c[9]);
        p.add(dziel);
        p.add(sqrt);
        p.add(c[4]);
        p.add(c[5]);
        p.add(c[6]);
        p.add(razy);
        p.add(new JLabel(""));
        p.add(c[1]);
        p.add(c[2]);
        p.add(c[3]);
        p.add(minus);
        p.add(odwrot);
        p.add(c[0]);
        p.add(plusminus);
        p.add(kropka);
        p.add(plus);
        p.add(rowne);

        sqrt.setFont(f);
        back.setFont(f);
        clear.setFont(f);
        dziel.setFont(f);
        razy.setFont(f);
        minus.setFont(f);
        odwrot.setFont(f);
        plusminus.setFont(f);
        kropka.setFont(f);
        plus.setFont(f);
        rowne.setFont(f);

        back.addActionListener(this);
        clear.addActionListener(this);
        dziel.addActionListener(this);
        sqrt.addActionListener(this);
        razy.addActionListener(this);
        minus.addActionListener(this);
        odwrot.addActionListener(this);
        plusminus.addActionListener(this);
        kropka.addActionListener(this);
        plus.addActionListener(this);
        rowne.addActionListener(this);

        this.setVisible(true);

    }

    public static void main(String[] args) {
       new Kalkulator();
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == odwrot && Double.parseDouble(display.getText()) != 0)
            display.setText(Double.toString(1 / Double.parseDouble(display.getText())));

        if (e.getSource() == clear) {
            display.setText("0");
            jestkropka = false;
        }

        if (e.getSource() == plusminus && Double.parseDouble(display.getText()) != 0) {
            if (display.getText().charAt(0) == '-')
                display.setText(display.getText().substring(1));
            else
                display.setText('-' + display.getText());
        }

        if (e.getSource() == kropka && !jestkropka) {
            display.setText(display.getText() + '.');
            jestkropka = true;
        }
        if (e.getSource() == back) {
            if (display.getText().length() == 1 || display.getText().length() == 2 && display.getText().charAt(0) == '-')
                display.setText("0");
            else {
                if (display.getText().charAt(display.getText().length() - 1) == '.')
                    jestkropka = false;
                display.setText(display.getText().substring(0, display.getText().length() - 1));
            }
        }
        if (e.getActionCommand().charAt(0) >= '0' && e.getActionCommand().charAt(0) <= '9'
                && e.getActionCommand().length() == 1 && display.getText().length() < 17);
        if (display.getText().charAt(0) == '0' && display.getText().length() == 1)
            display.setText(e.getActionCommand());
//            System.out.println("dupa");
        else if (e.getSource() == rowne) {
            String evaluatedONPString = toOdwroconaNotacjaPolska(display.getText());
            display.setText("tu ma byc ONP " + evaluatedONPString);
            evaluujWyrazenieONP(evaluatedONPString);
        }
        else
            display.setText(display.getText() + e.getActionCommand());


    }

    private static String getFromStack(){
        String result = "";
        String a = null;
        if(!stack.empty()) {
            a = (String) stack.pop();
            while(!a.equals("(")) {
                result = result + " " + a;
                if(stack.empty()) break;
                a = (String) stack.pop();
            }
        }
        if(result.length() > 0) {
            result = " " + result;
        }
        return result;
    }

    private String toOdwroconaNotacjaPolska(String currentDisplayValue) {
        boolean znak = true;
        String result = " ";
        char[] stringAsCharArray = currentDisplayValue.toCharArray();
        for(int i = 0; i< stringAsCharArray.length; i++) {
            if(currentDisplayValue.charAt(i) == '(') {
                stack.push("(");
                znak = true;
                result += " ";
            }else if(currentDisplayValue.charAt(i) == ')'){
                result += " " + getFromStack();
                znak = true;
            }else {
                if(znak && currentDisplayValue.charAt(i) == '-'){
                    result += " ";
                }
                result += currentDisplayValue.charAt(i);
                znak = false;
            }
        }
        return currentDisplayValue;
    }

    private String evaluujWyrazenieONP(String ONPString){
        return ONPString;
    }

}

