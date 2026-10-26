import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PizzaGUIFrame extends JFrame
{
    // Constant
    private final double SMALL_PRICE = 8.0;
    private final double MEDIUM_PRICE = 12.0;
    private final double LARGE_PRICE = 16.0;
    private final double SUPER_PRICE = 20.0;
    private final double TOPPING_PRICE = 1.0;
    private final double TAX = 0.07;

    // Panels
    private JPanel mainPnl;
    private JPanel topPnl;
    private JPanel middlePnl;
    private JPanel bottomPnl;
    private JPanel headerPnl;
    private JPanel crustPnl;
    private JPanel sizePnl;
    private JPanel toppingsPnl;
    private JPanel receiptPnl;
    private JPanel buttonsPnl;

    // Screen dimensions / Main Panel
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;

    // Header Panel
    JLabel titleLbl;
    File headerFontFile = new File("src/fonts/KiwiSoda.ttf");
    Font headerFont = Font.createFont(Font.TRUETYPE_FONT, headerFontFile).deriveFont(Font.PLAIN, 72f);

    // Crust Panel
    JRadioButton thinCrustRB;
    JRadioButton regularCrustRB;
    JRadioButton deepDishCrustRB;

    // Size Panel
    JComboBox sizeCombo;

    // Toppings Panel
    JCheckBox pepperoniCB;
    JCheckBox sausageCB;
    JCheckBox hamCB;
    JCheckBox baconCB;
    JCheckBox anchoviesCB;
    JCheckBox ricottaCB;
    JCheckBox mushroomCB;
    JCheckBox onionCB;
    JCheckBox greenPeppersCB;
    JCheckBox basilCB;
    JCheckBox bananaPepperCB;
    JCheckBox pineappleCB;

    // Receipt Panel
    JTextArea receiptTA;
    JScrollPane receiptSP;
    File receiptFontFile = new File("src/fonts/JMH Typewriter.ttf");
    Font receiptFont = Font.createFont(Font.TRUETYPE_FONT, receiptFontFile).deriveFont(Font.PLAIN, 36f);

    // Buttons Panel
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;
    File buttonsFontFile = new File("src/fonts/KiwiSoda.ttf");
    Font buttonsFont = Font.createFont(Font.TRUETYPE_FONT, buttonsFontFile).deriveFont(Font.PLAIN, 48f);

    public PizzaGUIFrame() throws IOException, FontFormatException
    {
        // Organizational panels' setup
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        topPnl = new JPanel();
        topPnl.setLayout(new BorderLayout());
        middlePnl = new JPanel();
        middlePnl.setLayout(new GridLayout(1,3));
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new BorderLayout());

        // Build top panel
        buildHeaderPanel();
        topPnl.add(headerPnl, BorderLayout.CENTER);

        // Build middle panel
        buildCrustPanel();
        middlePnl.add(crustPnl);
        buildSizePanel();
        middlePnl.add(sizePnl);
        buildToppingsPanel();
        middlePnl.add(toppingsPnl);

        // Build bottom panel
        buildReceiptPanel();
        bottomPnl.add(receiptPnl, BorderLayout.NORTH);
        buildButtonsPanel();
        bottomPnl.add(buttonsPnl, BorderLayout.SOUTH);

        mainPnl.add(topPnl, BorderLayout.NORTH);
        mainPnl.add(middlePnl, BorderLayout.CENTER);
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);
        add(mainPnl);
        setSize((screenWidth / 4) * 3, (screenHeight / 4) * 3); // Panel should take up 75% of screen width/height
        setLocation(screenWidth / 8, screenHeight / 8); // Center panel
        setTitle("Pizza Order Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void buildHeaderPanel()
    {
        headerPnl = new JPanel();

        titleLbl = new JLabel("Pizza Order Form", JLabel.CENTER);
        titleLbl.setFont(headerFont);
        titleLbl.setForeground(Color.decode("#d4c604"));

        headerPnl.add(titleLbl);
    }

    private void buildCrustPanel()
    {
        // Build panel
        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(3,1));
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Select Crust Thickness"));

        // Build buttons
        thinCrustRB = new JRadioButton("Thin Crust");
        regularCrustRB = new JRadioButton("Regular Crust");
        deepDishCrustRB = new JRadioButton("Deep-dish Crust");

        // Add buttons
        crustPnl.add(thinCrustRB);
        crustPnl.add(regularCrustRB);
        crustPnl.add(deepDishCrustRB);

        // Set default selection
        regularCrustRB.setSelected(true);

        // Ensure only one option can be selected at once
        ButtonGroup crusts = new ButtonGroup();
        crusts.add(thinCrustRB);
        crusts.add(regularCrustRB);
        crusts.add(deepDishCrustRB);
    }

    private void buildSizePanel()
    {
        // Build panel
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Select Pizza Size"));

        // Add options
        sizeCombo = new JComboBox();
        sizeCombo.addItem("Small");
        sizeCombo.addItem("Medium");
        sizeCombo.addItem("Large");
        sizeCombo.addItem("Super");

        // Set default option
        sizeCombo.setSelectedIndex(1);

        // Add to panel
        sizePnl.add(sizeCombo);
    }

    private void buildToppingsPanel()
    {
        // Build panel
        toppingsPnl = new JPanel();
        toppingsPnl.setLayout(new GridLayout(4, 3));
        toppingsPnl.setBorder(new TitledBorder(new EtchedBorder(), "Select Toppings"));

        // Build checkbox options
        pepperoniCB = new JCheckBox("Pepperoni");
        sausageCB = new JCheckBox("Sausage");
        hamCB = new JCheckBox("Ham");
        baconCB = new JCheckBox("Bacon");
        anchoviesCB = new JCheckBox("Anchovies");
        ricottaCB = new JCheckBox("Ricotta Cheese");
        mushroomCB = new JCheckBox("Mushrooms");
        onionCB = new JCheckBox("Onions");
        greenPeppersCB = new JCheckBox("Green Peppers");
        basilCB = new JCheckBox("Basil");
        bananaPepperCB = new JCheckBox("Banana Peppers");
        pineappleCB = new JCheckBox("Pineapple");

        // Add checkboxes to panel
        toppingsPnl.add(pepperoniCB);
        toppingsPnl.add(sausageCB);
        toppingsPnl.add(hamCB);
        toppingsPnl.add(baconCB);
        toppingsPnl.add(anchoviesCB);
        toppingsPnl.add(ricottaCB);
        toppingsPnl.add(mushroomCB);
        toppingsPnl.add(onionCB);
        toppingsPnl.add(greenPeppersCB);
        toppingsPnl.add(basilCB);
        toppingsPnl.add(bananaPepperCB);
        toppingsPnl.add(pineappleCB);
    }

    private void buildReceiptPanel()
    {
        receiptPnl = new JPanel();
        receiptTA = new JTextArea(14, 40);
        receiptTA.setEditable(false);
        receiptTA.setFont(receiptFont);
        receiptSP = new JScrollPane(receiptTA);

        receiptPnl.add(receiptSP);
    }

    private void buildButtonsPanel()
    {
        buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new GridLayout(1,3));

        orderBtn = new JButton("Order");
        orderBtn.setFont(buttonsFont);
        orderBtn.addActionListener((ActionEvent ae) ->
        {
            if (pepperoniCB.isSelected() || sausageCB.isSelected() || hamCB.isSelected()
                || baconCB.isSelected() || anchoviesCB.isSelected() || ricottaCB.isSelected()
                || mushroomCB.isSelected() || onionCB.isSelected() || greenPeppersCB.isSelected()
                || basilCB.isSelected() || bananaPepperCB.isSelected() || pineappleCB.isSelected())
            {
                printReceipt();
                clearSelections();
            }
            else
            {
                JOptionPane.showMessageDialog(mainPnl, "Please select at least one topping.", "No topping selected!", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonsPnl.add(orderBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setFont(buttonsFont);
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            clearSelections();
        });
        buttonsPnl.add(clearBtn);

        quitBtn = new JButton("Quit");
        quitBtn.setFont(buttonsFont);
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int userChoice = JOptionPane.showConfirmDialog(mainPnl, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
            if (userChoice == 0)
            {
                System.exit(0);
            }
        });
        buttonsPnl.add(quitBtn);
    }

    private void printReceipt()
    {
        String size = "";
        String crust = "";
        double basePrice = 0.0;
        double subtotal = 0.0;

        size = (String) sizeCombo.getSelectedItem();

        if (thinCrustRB.isSelected())
        {
            crust = "Thin Crust";
        }
        else if (regularCrustRB.isSelected())
        {
            crust = "Regular Crust";
        }
        else
        {
            crust = "Deep-dish Crust";
        }

        basePrice = switch (size) {
            case "Small" -> SMALL_PRICE;
            case "Medium" -> MEDIUM_PRICE;
            case "Large" -> LARGE_PRICE;
            case "Super" -> SUPER_PRICE;
            default -> basePrice;
        };

        subtotal = basePrice;

        // Top of receipt
        receiptTA.append("=========================================\n");
        receiptTA.append(String.format("%s %s\t\t$%.2f\n", size, crust, basePrice));

        // Toppings
        if (pepperoniCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Pepperoni", TOPPING_PRICE));
        }
        if (sausageCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Sausage", TOPPING_PRICE));
        }
        if (hamCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Ham", TOPPING_PRICE));
        }
        if (baconCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Bacon", TOPPING_PRICE));
        }
        if (anchoviesCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Anchovies", TOPPING_PRICE));
        }
        if (ricottaCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t$%.2f\n", "Ricotta Cheese", TOPPING_PRICE));
        }
        if (mushroomCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Mushrooms", TOPPING_PRICE));
        }
        if (onionCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Onions", TOPPING_PRICE));
        }
        if (greenPeppersCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t$%.2f\n", "Green Peppers", TOPPING_PRICE));
        }
        if (basilCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Basil", TOPPING_PRICE));
        }
        if (bananaPepperCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t$%.2f\n", "Banana Peppers", TOPPING_PRICE));
        }
        if (pineappleCB.isSelected())
        {
            subtotal += TOPPING_PRICE;
            receiptTA.append(String.format("%s\t\t\t$%.2f\n", "Pineapple", TOPPING_PRICE));
        }

        // Bottom of receipt
        receiptTA.append(String.format("\nSub-total:\t\t\t$%.2f\n", subtotal));
        receiptTA.append(String.format("Tax:\t\t\t$%.2f\n", (subtotal * TAX)));
        receiptTA.append("------------------------------------------------------\n");
        receiptTA.append(String.format("Total:\t\t\t$%.2f\n", (subtotal + (subtotal * TAX))));
        receiptTA.append("=========================================\n\n");
    }

    private void clearSelections()
    {
        regularCrustRB.setSelected(true);

        sizeCombo.setSelectedIndex(1);

        pepperoniCB.setSelected(false);
        sausageCB.setSelected(false);
        hamCB.setSelected(false);
        baconCB.setSelected(false);
        anchoviesCB.setSelected(false);
        ricottaCB.setSelected(false);
        mushroomCB.setSelected(false);
        onionCB.setSelected(false);
        greenPeppersCB.setSelected(false);
        basilCB.setSelected(false);
        bananaPepperCB.setSelected(false);
        pineappleCB.setSelected(false);
    }
}
