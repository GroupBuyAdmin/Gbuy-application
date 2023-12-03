package gbuysytem.GUI.Body.DashboardPanels.ProductsPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import gbuysytem.GUI.Body.DashboardPanels.Misc.GbuyProductDatabase;
import gbuysytem.GUI.Body.DashboardPanels.Misc.SingleProductContainer;

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
        JLabel headerLabel = new JLabel("Add Product");
        headerPanel.add(headerLabel);

        CenterPanel centerPanel = new CenterPanel();

        masterPanel.setLayout(new BorderLayout());
        masterPanel.add(headerPanel, BorderLayout.NORTH);
        masterPanel.add(centerPanel, BorderLayout.CENTER);
        
        initFrame();

        if(editProduct){
            mainFrame.setVisible(true);
            centerPanel.initializeWithData();
        }
    }



    private void initFrame(){
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800,600);
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

    // public static void main(String[] args) {
    //     ProductCreator p = new ProductCreator(new ProductsPanel());
    // }

    class CenterPanel extends JPanel{
        ImagePanel imagePanel;
        DetailsPanel detailsPanel;

        public ImagePanel getImagePanel() {return imagePanel;}
        public DetailsPanel getDetailsPanel() {return detailsPanel;}

        public CenterPanel(){
            this.imagePanel = new ImagePanel();
            this.imagePanel.setPreferredSize(new Dimension(150, 300));
            this.detailsPanel = new DetailsPanel();

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 0.5;
            gbc.fill = GridBagConstraints.BOTH;
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

                    if(!editProduct){
                        GbuyProductDatabase db = new GbuyProductDatabase();
                        db.insertProduct(spc.productName, spc.productCategory, spc.productPrice, spc.productDescription, spc.productQuantity, spc.selectedFile);
                        productsPanel.updateDashboard();
                        JOptionPane.showMessageDialog(CenterPanel.this, spc.productName + " was added");
                    }
                    else{
                        GbuyProductDatabase db = new GbuyProductDatabase();
                        db.editProduct(spc.productName, spc.productCategory, spc.productPrice, spc.productDescription, spc.productQuantity, spc.selectedFile, product.getId());
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

        private void initializeWithData() {
            int productId = product.getId();
            SingleProductContainer spc = new SingleProductContainer();

            GbuyProductDatabase db = new GbuyProductDatabase();
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
            this.iconButton = new IconButton();
            
            setLayout(new BorderLayout());
            add(imageContainer, BorderLayout.CENTER);
            add(iconButton, BorderLayout.SOUTH);
        }
        
        class ImageContainer extends JPanel{
            JLabel imageLabel;
            public JLabel getImageLabel() {return imageLabel;}

            public ImageContainer(){
                imageLabel = new JLabel();
                setLayout(new BorderLayout());
                add(imageLabel, BorderLayout.CENTER);
            }

            public void refresh(){
                revalidate();
                repaint();
            }
        }

        class IconButton extends JPanel{
            JButton button;
            JFileChooser fileChooser;

            public JFileChooser getFileChooser() {return fileChooser;}
            public JButton getButton() {return button;}

            public IconButton(){
                this.button = new JButton("Add Photo");
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
            this.uploadPanel = new UploadPanel();

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
                textFields.setPreferredSize(new Dimension(25, 100));
                this.subDescription = new SubDescription();

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
                JTextField nameTextField;
                JTextArea descTextArea;
                
                public JTextField getNameTextField() {return nameTextField;}
                public JTextArea getDescTextArea() {return descTextArea;}

                public TextFields(){
                    
                    JLabel nameFieldLabel = new JLabel("Product Name");
                    nameFieldLabel.setHorizontalAlignment(JLabel.LEFT);

                    this.nameTextField = new JTextField();


                    //inside descFieldPanel
                    JLabel descFieldLabel = new JLabel("Product Description");
                    descFieldLabel.setHorizontalAlignment(JLabel.LEFT);


                    this.descTextArea = new JTextArea();
                    descTextArea.setLineWrap(true);
                    descTextArea.setWrapStyleWord(true);

                    JScrollPane descScrollPanel = new JScrollPane(descTextArea);

                    //layout
                    setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 0.5;

                    gbc.weighty = 0.2;
                    gbc.fill = GridBagConstraints.BOTH;
                    add(nameFieldLabel, gbc);
                    
                    gbc.weighty = 0.5;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 10, 0);
                    add(nameTextField, gbc);
                    
                    gbc.weighty = 0.2;
                    gbc.gridy++;
                    gbc.insets = new Insets(0, 0, 0, 0);
                    add(descFieldLabel, gbc);
                    
                    gbc.weighty = 10;
                    gbc.gridy++;
                    add(descScrollPanel, gbc);
                }

            }

            //product price, category, quantity
            class SubDescription extends JPanel{
                JTextField priceTextField;
                JTextField quantityTextField;
                JComboBox<String> comboBox;

                public JComboBox<String> getComboBox() {return comboBox;}
                public JTextField getPriceTextField() {return priceTextField;}
                public JTextField getQuantityTextField() {return quantityTextField;}

                public SubDescription(){

                    //for categories drop down
                    JLabel categorylabel = new JLabel("Category");
                    categorylabel.setHorizontalAlignment(JLabel.LEFT);
  
                    String[] categories = {"Electronics", "Clothing", "Books", "Home and Kitchen", "Sports"};
                    this.comboBox = new JComboBox<>(categories);

                    //for price
                    JLabel priceLabel = new JLabel("Price");
                    priceLabel.setHorizontalAlignment(JLabel.LEFT);
                    this.priceTextField = new JTextField();

                    //for quantity
                    JLabel quantityLabel = new JLabel("Quantity");
                    quantityLabel.setHorizontalAlignment(JLabel.LEFT);
                    this.quantityTextField = new JTextField();

                    setLayout(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();

                    gbc.fill = GridBagConstraints.BOTH;
                    
                    //row 1
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 2;
                    add(categorylabel, gbc);
                    
                    //row 2
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 2;
                    gbc.gridy++;
                    add(comboBox, gbc);

                    //row 3
                    gbc.weightx = 0.5;
                    gbc.weighty = 0.5;
                    gbc.gridwidth = 1;
                    gbc.gridy++;
                    gbc.gridx = 0;
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
                    gbc.insets = new Insets(0, 0, 0, 10);
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
            JButton uploadButton;
            JButton cancelButton;

            public JButton getUploadButton() {return uploadButton;}
            public JButton getCancelButton() {return cancelButton;}

            public UploadPanel(){
                this.uploadButton = new JButton("Upload");
                this.cancelButton = new JButton("Cancel");

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
