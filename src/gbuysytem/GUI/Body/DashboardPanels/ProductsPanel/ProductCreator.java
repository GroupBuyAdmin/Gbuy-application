package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import gbuysytem.GUI.Body.DashboardPanels.ColorPalettes.GBuyPalette;
import gbuysytem.GUI.Body.DashboardPanels.Misc.GbuyProductDatabase;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedButton;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedCornerComboBox;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedCornerTextArea;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedCornerTextField;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedImageIcon;
import gbuysytem.GUI.Body.DashboardPanels.Misc.RoundedPanel;
import gbuysytem.GUI.Body.DashboardPanels.Misc.SingleProductContainer;
import gbuysytem.GUI.Body.fonts.GbuyFont;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProductCreator {
    private JFrame mainFrame;
    private JPanel masterPanel;

    private ProductsPanel productsPanel;
    private boolean editProduct;
    private Product product;

    //for product creation
    public ProductCreator(ProductsPanel productsPanel){
        this(productsPanel, false, null);
    }

    //for product editing
    public ProductCreator(ProductsPanel productsPanel, Product product){
        this(productsPanel, true, product);
    }

    public ProductCreator(ProductsPanel productsPanel, boolean editProduct, Product product){
        this.productsPanel = productsPanel;
        this.editProduct = editProduct;
        this.product = product;

        masterPanel = new JPanel();

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        headerPanel.setBackground(Color.white);
        
        JLabel headerLabel = new JLabel();
        if(!editProduct){
            headerLabel.setText("Add Product");
        }
        else{
            headerLabel.setText("Edit Product");
        }
        headerLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(20f));
        headerLabel.setForeground(GBuyPalette.CUSTOM_BLACK);
        headerPanel.add(headerLabel);

        CenterPanel centerPanel = new CenterPanel();
        centerPanel.setBackground(Color.white);

        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(headerPanel, BorderLayout.NORTH);
        masterPanel.add(centerPanel, BorderLayout.CENTER);
        masterPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        masterPanel.setBackground(Color.white);

        initFrame();

        if(editProduct){
            mainFrame.setVisible(true);
            centerPanel.initializeWithData();
        }
    }

    private void initFrame(){
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(1000,800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setContentPane(masterPanel);
        mainFrame.setVisible(true);
    }


    public boolean completedFields(CenterPanel c){
        ImagePanel imagePanel = c.getImagePanel();
        DetailsPanel detailsPanel = c.getDetailsPanel();

        if(imagePanel.getImageContainer().getImageLabel().getIcon() == null){ 
            return false;
        }

        if(detailsPanel.getFields().getTextFields().getNameTextField().getText().equals("")){ 
            return false;
        }

        if(detailsPanel.getFields().getTextFields().getDescTextArea().getText().equals("")){ 
            return false;
        }

        if(detailsPanel.getFields().getSubDescription().getPriceTextField().getText().equals("")){ 
            return false;
        }

        if(detailsPanel.getFields().getSubDescription().getQuantityTextField().getText().equals("")){ 
            return false;
        }

        return true;
    }

    public boolean validFields(CenterPanel c){
        DetailsPanel detailsPanel = c.getDetailsPanel();
        String message = "Invalid fields: ";
        boolean flag = true;

        try {
            Double.parseDouble(detailsPanel.getFields().getSubDescription().getPriceTextField().getText());
        } catch (Exception e) {
            message += " {Price}";
            flag = false;
        }

        try {
            Integer.parseInt(detailsPanel.getFields().getSubDescription().getQuantityTextField().getText());
        } catch (Exception e) {
            message += " {Quantity}";
            flag  = false;
        }

        if(!flag){
            JOptionPane.showMessageDialog(c, message);
        }

        return flag;
    }

    public static void main(String[] args) {
        ProductCreator p = new ProductCreator(new ProductsPanel());
    }

    class CenterPanel extends JPanel{
        ImagePanel imagePanel;
        DetailsPanel detailsPanel;

        public ImagePanel getImagePanel() {return imagePanel;}
        public DetailsPanel getDetailsPanel() {return detailsPanel;}

        public CenterPanel(){
            this.imagePanel = new ImagePanel();
            imagePanel.setBackground(Color.white);
            this.imagePanel.setPreferredSize(new Dimension(150, 300));
            this.detailsPanel = new DetailsPanel();
            imagePanel.setBackground(Color.white);

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 0.5;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(0, 0, 0, 20);
            add(imagePanel, gbc);
            
            gbc.gridx++;
            gbc.weightx = 0.4;
            gbc.anchor = GridBagConstraints.EAST;
            add(detailsPanel, gbc);

            //setup panel buttons

            JButton cancelButton = detailsPanel.getUploadPanel().getCancelButton();
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("exiting");
                    mainFrame.dispose();
                }
            });

            JButton uploadButton = detailsPanel.getUploadPanel().getUploadButton();
            uploadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //implement database here
                    if(completedFields(CenterPanel.this)){
                        if(validFields(CenterPanel.this)){
                            uploadProductToDatabase();
                            mainFrame.dispose();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(CenterPanel.this, "Please complete all fields");
                    }
                }

                private void uploadProductToDatabase() {
                    SingleProductContainer spc = new SingleProductContainer();

                    spc.productName = detailsPanel.getFields().getTextFields().getNameTextField().getText();
                    spc.productDescription = detailsPanel.getFields().getTextFields().getDescTextArea().getText();
                    JComboBox<String> comboBox = detailsPanel.getFields().getSubDescription().getComboBox();
                    spc.productCategory = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
                    spc.productPrice = Double.parseDouble(detailsPanel.getFields().getSubDescription().getPriceTextField().getText());
                    spc.productQuantity = Integer.parseInt(detailsPanel.getFields().getSubDescription().getQuantityTextField().getText());
                    spc.selectedFile = imagePanel.getIconButton().getFileChooser().getSelectedFile();

                    if(!editProduct){   //execute when adding a product
                        GbuyProductDatabase db = GbuyProductDatabase.getInstance();
                        db.insertProduct(spc);
                        productsPanel.updateDashboard();
                        JOptionPane.showMessageDialog(CenterPanel.this, spc.productName + " was added");
                    }
                    else{               //execute when editing a product
                        GbuyProductDatabase db = GbuyProductDatabase.getInstance();
                        db.editProduct(spc, product.getId());
                        productsPanel.updateDashboard();
                        JOptionPane.showMessageDialog(CenterPanel.this, "product " + product.getId() + " was edited");
                    }
                    
                }
            });

            JButton addButton = imagePanel.getIconButton().getButton();
            addButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    JFileChooser fChooser = imagePanel.getIconButton().getFileChooser();
                    int result = fChooser.showOpenDialog(CenterPanel.this);
                    if(result == JFileChooser.APPROVE_OPTION){
                        File selectedFile = fChooser.getSelectedFile();
                        ImageIcon selectedImage = new ImageIcon(selectedFile.getPath());
                        //resize the image to fit container
                        Image resizedImage = resizeImage(selectedImage);
                        ImageIcon resizedIcon = new ImageIcon(resizedImage);
                        imagePanel.imageContainer.getImageLabel().setIcon(resizedIcon);
                        imagePanel.imageContainer.refresh();
                        addButton.setText("Change Photo");
                        imagePanel.getIconButton().refresh();
                    }
                }
            });
        }

        private Image resizeImage(ImageIcon selectedImage) {
            int newHeight = 0;
            int preferredWidth = imagePanel.getWidth();
            int iconWidth = selectedImage.getIconWidth();
            int iconHeight = selectedImage.getIconHeight();
            if(iconWidth > preferredWidth){
                newHeight = iconHeight - (iconWidth - preferredWidth);
            }
            else{
                newHeight = iconHeight + (preferredWidth - iconWidth);
            }
            Image image = selectedImage.getImage().getScaledInstance(preferredWidth, newHeight, Image.SCALE_SMOOTH);
            return image;
        }

        private void initializeWithData() {             //execute if in edit mode
            int productId = product.getId();
            SingleProductContainer spc = new SingleProductContainer();

            GbuyProductDatabase db = GbuyProductDatabase.getInstance();
            db.getSingleProduct(productId, spc);

            //initialiazing fields
            detailsPanel.getFields().getTextFields().getNameTextField().setText(spc.productName);
            detailsPanel.getFields().getTextFields().getDescTextArea().setText(spc.productDescription);
            JComboBox<String> comboBox = detailsPanel.getFields().getSubDescription().getComboBox();
            comboBox.setSelectedItem(spc.productCategory);
            detailsPanel.getFields().getSubDescription().getPriceTextField().setText(String.valueOf(spc.productPrice));
            detailsPanel.getFields().getSubDescription().getQuantityTextField().setText(String.valueOf(spc.productQuantity));
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(spc.byteImage).getImage());
            Image resizedImage = resizeImage(imageIcon);
            ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
            imagePanel.getImageContainer().getImageLabel().setIcon(resizedImageIcon);
            imagePanel.imageContainer.refresh();
            imagePanel.iconButton.getButton().setText("Change Photo");
            imagePanel.getIconButton().refresh();
        }

    }

    class ImagePanel extends JPanel{
        ImageContainer imageContainer;
        IconButton iconButton;

        public IconButton getIconButton() {return iconButton;}
        public ImageContainer getImageContainer() {return imageContainer;}

        public ImagePanel(){
            setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            this.imageContainer = new ImageContainer();
            imageContainer.setBackground(Color.white);
            this.iconButton = new IconButton();
            iconButton.setBackground(Color.white);
            
            setLayout(new BorderLayout(0,20));
            add(imageContainer, BorderLayout.CENTER);
            add(iconButton, BorderLayout.SOUTH);
        }
        
        class ImageContainer extends JPanel{
            JLabel imageLabel;
            public JLabel getImageLabel() {return imageLabel;}

            public ImageContainer(){
                imageLabel = new JLabel("No Photo");
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(12f));
                setLayout(new BorderLayout());
   
                RoundedPanel r = new RoundedPanel();
                r.setLayout(new BorderLayout());
                r.setShady(false);
                r.add(imageLabel, BorderLayout.CENTER);
                r.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);

                add(r, BorderLayout.CENTER);
            }

            public void refresh(){
                revalidate();
                repaint();
            }
        }

        class IconButton extends JPanel{
            RoundedButton button;
            JFileChooser fileChooser;

            public JFileChooser getFileChooser() {return fileChooser;}
            public JButton getButton() {return button;}

            public IconButton(){
                this.button = new RoundedButton("Add Photo");
                button.setBorderColor(GBuyPalette.CUSTOM_BLUE);
                button.setButtonColor(Color.white);
                button.setForeground(GBuyPalette.CUSTOM_BLUE);
                button.setButtonFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                this.fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new ImageFileFilter());
                setLayout(new FlowLayout(FlowLayout.LEFT));
                add(button);
            }

            public void refresh(){
                revalidate();
                repaint();
            }

        }
    }

    class DetailsPanel extends JPanel{
        Fields fields;
        UploadPanel uploadPanel;

        public UploadPanel getUploadPanel() {return uploadPanel;}
        public Fields getFields() {return fields;}

        public DetailsPanel(){
            setLayout(new BorderLayout());
            this.fields = new Fields();
            fields.setBackground(Color.white);
            this.uploadPanel = new UploadPanel();
            uploadPanel.setBackground(Color.white);

            add(fields, BorderLayout.CENTER);
            add(uploadPanel, BorderLayout.SOUTH);
        }

        //inside Details Panel
        class Fields extends JPanel{
            TextFields textFields;
            SubDescription subDescription;
            
            public TextFields getTextFields() {return textFields;}
            public SubDescription getSubDescription() {return subDescription;}

            public Fields(){
                setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                this.textFields = new TextFields();
                textFields.setBackground(Color.white);
                textFields.setPreferredSize(new Dimension(25, 100));
                this.subDescription = new SubDescription();
                subDescription.setBackground(Color.white);

                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 0.5;
                gbc.weighty = 1.5;
                gbc.fill = GridBagConstraints.BOTH;
                
                add(textFields, gbc);

                gbc.gridy++;
                gbc.weighty = 0.2;
                add(subDescription, gbc);
            }

            //product name and product description
            class TextFields extends JPanel{
                RoundedCornerTextField nameTextField;
                RoundedCornerTextArea descTextArea;
                
                public JTextField getNameTextField() {return nameTextField;}
                public JTextArea getDescTextArea() {return descTextArea;}

                public TextFields(){
                    
                    JLabel nameFieldLabel = new JLabel("Product Name");
                    nameFieldLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));
                    nameFieldLabel.setHorizontalAlignment(JLabel.LEFT);

                    this.nameTextField = new RoundedCornerTextField();
                    nameTextField.setText("Enter name");
                    nameTextField.setFont(GbuyFont.MULI_SEMI_BOLD.deriveFont(14f));
                    nameTextField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                    nameTextField.setForeground(GBuyPalette.CUSTOM_BLUE);
                    nameTextField.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);

                    //inside descFieldPanel
                    JLabel descFieldLabel = new JLabel("Description");
                    descFieldLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));
                    descFieldLabel.setHorizontalAlignment(JLabel.LEFT);


                    this.descTextArea = new RoundedCornerTextArea();
                    descTextArea.setFont(GbuyFont.MULI_LIGHT.deriveFont(12f));
                    descTextArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                    descTextArea.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);

                    JScrollPane descScrollPanel = new JScrollPane(descTextArea);
                    descScrollPanel.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);
                    descScrollPanel.setBorder(BorderFactory.createEmptyBorder());

                    //layout
                    setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 0.5;

                    gbc.weighty = 0.2;
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.insets = new Insets(0, 0, 5, 0);
                    add(nameFieldLabel, gbc);
                    
                    gbc.weighty = 0.5;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 25, 0);
                    add(nameTextField, gbc);
                    
                    gbc.weighty = 0.2;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 5, 0);
                    add(descFieldLabel, gbc);
                    
                    gbc.weighty = 10;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 60, 0);
                    add(descScrollPanel, gbc);
                }

            }

            //product price, category, quantity
            class SubDescription extends JPanel{
                RoundedCornerTextField priceTextField;
                RoundedCornerTextField quantityTextField;
                RoundedCornerComboBox comboBox;

                public JComboBox<String> getComboBox() {return comboBox;}
                public JTextField getPriceTextField() {return priceTextField;}
                public JTextField getQuantityTextField() {return quantityTextField;}

                public SubDescription(){

                    //for categories drop down
                    JLabel categorylabel = new JLabel("Category");
                    categorylabel.setHorizontalAlignment(JLabel.LEFT);
                    categorylabel.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));
  
                    String[] categories = {"Electronics", "Clothing", "Books", "Home and Kitchen", "Sports"};
                    this.comboBox = new RoundedCornerComboBox(categories);
                    ImageIcon img = new ImageIcon("src/gbuysytem/GUI/Body/DashboardPanels/ProductsPanel/img/down.png");
                    Icon customDropdownIcon = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
                    comboBox.setCustomDropdownIcon(customDropdownIcon);
                    comboBox.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);
                    comboBox.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                    RoundedPanel comboBoxPanel = new RoundedPanel();
                    comboBoxPanel.setShady(false);
                    comboBoxPanel.setArcs(new Dimension(10, 10));
                    comboBoxPanel.setLayout(new BorderLayout());
                    comboBoxPanel.add(comboBox, BorderLayout.CENTER);
                    comboBoxPanel.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);

                    //for price
                    JLabel priceLabel = new JLabel("Price $");
                    priceLabel.setHorizontalAlignment(JLabel.LEFT);
                    priceLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                    this.priceTextField = new RoundedCornerTextField();
                    priceTextField.setText("0.00");
                    priceTextField.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);
                    priceTextField.setForeground(GBuyPalette.CUSTOM_BLUE);
                    priceTextField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                    priceTextField.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                    //for quantity
                    JLabel quantityLabel = new JLabel("Quantity");
                    quantityLabel.setHorizontalAlignment(JLabel.LEFT);
                    quantityLabel.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));
                    this.quantityTextField = new RoundedCornerTextField();
                    quantityTextField.setText("10");
                    quantityTextField.setBackground(GBuyPalette.CUSTOM_PALE_BLUE);
                    quantityTextField.setForeground(GBuyPalette.CUSTOM_BLUE);
                    quantityTextField.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
                    quantityTextField.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));


                    setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.fill = GridBagConstraints.BOTH;
                    
                    //row 1
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 2;
                    gbc.insets = new Insets(0, 0, 5, 0);
                    add(categorylabel, gbc);
                    
                    //row 2
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 2;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 10, 0);
                    add(comboBoxPanel, gbc);
                    
                    //row 3
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 1;
                    gbc.gridy++;
                    gbc.gridx = 0;
                    gbc.insets = new Insets(0, 0, 5, 0);
                    add(priceLabel, gbc);
                    
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 1;
                    gbc.gridx++;
                    add(quantityLabel, gbc);

                    //row 4
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 1;
                    gbc.gridy++;
                    gbc.gridx = 0;
                    gbc.insets = new Insets(0, 0, 0, 15);
                    add(priceTextField, gbc);
                    
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 1;
                    gbc.gridx++;
                    gbc.insets = new Insets(0, 0, 0, 0);
                    add(quantityTextField, gbc);
   
                }
            }
        }

        class UploadPanel extends JPanel{
            RoundedButton uploadButton;
            RoundedButton cancelButton;

            public JButton getUploadButton() {return uploadButton;}
            public JButton getCancelButton() {return cancelButton;}

            public UploadPanel(){
                this.uploadButton = new RoundedButton("Upload");
                uploadButton.setButtonColor(GBuyPalette.CUSTOM_BLUE);
                uploadButton.setForeground(Color.white);
                uploadButton.setDrawBorder(false);
                uploadButton.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                this.cancelButton = new RoundedButton("Cancel");
                cancelButton.setButtonColor(Color.white);
                cancelButton.setForeground(GBuyPalette.CUSTOM_BLUE);
                cancelButton.setDrawBorder(false);
                cancelButton.setFont(GbuyFont.MULI_BOLD.deriveFont(14f));

                setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

                add(uploadButton);
                add(cancelButton);
            }

        }
    }

    //helper class for imagefile filter
    private static class ImageFileFilter extends FileFilter {
        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true; // Allow directories to be displayed
            }

            String extension = getExtension(file);
            return extension != null && (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif"));
        }

        @Override
        public String getDescription() {
            return "Image Files (jpg, jpeg, png, gif)";
        }

        private String getExtension(File file) {
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(dotIndex + 1).toLowerCase();
            }
            return null;
        }
    }
}
