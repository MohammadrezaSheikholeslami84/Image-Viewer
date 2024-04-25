import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;

    String filePath = "Images";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 700);
        this.setVisible(true);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.BLACK);

        mainPanel();
    }

    public void mainPanel(){
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(null);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(200,200,300,300);

        // TODO
        selectFileButton = new JButton("Choose Image");
        selectFileButton.setFocusable(false);
        selectFileButton.addActionListener(this);

        showImageButton = new JButton("Show Image");
        showImageButton.setFocusable(false);
        showImageButton.addActionListener(this);

        brightnessButton = new JButton("Brightness");
        brightnessButton.setFocusable(false);
        brightnessButton.addActionListener(this);

        grayscaleButton = new JButton("Gray Scale");
        grayscaleButton.setFocusable(false);
        grayscaleButton.addActionListener(this);

        resizeButton = new JButton("Resize");
        resizeButton.setFocusable(false);
        resizeButton.addActionListener(this);

        closeButton = new JButton("Exit");
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        // Adding all buttons to Grid panel
        buttonsPanel.add(selectFileButton);
        buttonsPanel.add(showImageButton);
        buttonsPanel.add(brightnessButton);
        buttonsPanel.add(grayscaleButton);
        buttonsPanel.add(resizeButton);
        buttonsPanel.add(closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);

        // TODO
        showResizeButton = new JButton("Show Results");
        showResizeButton.setBounds(330,300,120,30);
        showResizeButton.setFocusable(false);
        showResizeButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50,300,100,30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        JLabel widthlabel = new JLabel("Width :");
        widthlabel.setBounds(100,95,100,30);
        widthlabel.setFont(new Font("Arial",Font.BOLD,16));
        widthlabel.setForeground(Color.red);

        widthTextField = new JTextField();
        widthTextField.setBounds(200,100,100,30);

        JLabel hightlabel = new JLabel("Height :");
        hightlabel.setBounds(100,195,100,30);
        hightlabel.setFont(new Font("Arial",Font.BOLD,16));
        hightlabel.setForeground(Color.red);

        heightTextField = new JTextField();
        heightTextField.setBounds(200,200,100,30);

        resizePanel.add(widthTextField);
        resizePanel.add(heightTextField);

        resizePanel.add(widthlabel);
        resizePanel.add(hightlabel);

        resizePanel.add(showResizeButton);
        resizePanel.add(backButton);

        this.add(resizePanel);
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);

        // TODO
        showBrightnessButton = new JButton("Show Results");
        showBrightnessButton.setBounds(330,300,120,30);
        showBrightnessButton.setFocusable(false);
        showBrightnessButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(50,300,100,30);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        JLabel brightnessText = new JLabel("<HTML>Enter f :<BR>(must be between 0 , 1)</HTML>");
        brightnessText.setBounds(70,100,200,200);
        brightnessText.setForeground(Color.red);
        brightnessText.setFont(new Font("Arial",Font.BOLD,14));

        brightnessTextField = new JTextField();
        brightnessTextField.setBounds(300,180,100,30);

        brightnessPanel.add(brightnessText);
        brightnessPanel.add(brightnessTextField);

        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);

        this.add(brightnessPanel);
    }

    public void chooseFileImage(){

        // TODO
        fileChooser.setCurrentDirectory(new File(filePath));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        // TODO
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(h, w, Image.SCALE_DEFAULT));
        JLabel  pictureLabel = new JLabel();
        pictureLabel.setIcon(imageIcon);
        pictureLabel.setToolTipText("hide picture");

        tempPanel.add(pictureLabel);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        // TODO
        BufferedImage bufferimage = ImageIO.read(file);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage imagenew = op.filter(bufferimage, null);

        ImageIcon image_gray = new ImageIcon(imagenew.getScaledInstance(h, w, Image.SCALE_DEFAULT));
        JLabel  image_gray_Label = new JLabel();
        image_gray_Label.setIcon(image_gray);

        tempPanel.add(image_gray_Label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        // TODO
        ImageIcon image_Resized_Icon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
        JLabel  image_Resized_Label = new JLabel();
        image_Resized_Label.setIcon(image_Resized_Icon);

        tempPanel.add(image_Resized_Label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f) throws IOException {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        BufferedImage bufferimage = ImageIO.read(file);
        RescaleOp rescaleOp = new RescaleOp(f , 0, null);
        BufferedImage newimage = rescaleOp.filter(bufferimage, null);

        ImageIcon image_Resized_Icon = new ImageIcon(newimage.getScaledInstance(w,h,Image.SCALE_DEFAULT));
        JLabel picturelabel = new JLabel();
        picturelabel.setIcon(image_Resized_Icon);

        tempPanel.add(picturelabel);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            // TODO
            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();

        } else if(e.getSource()== showImageButton){
            // TODO
            this.showOriginalImage();

        }
        else if(e.getSource()==grayscaleButton){
            // TODO
            try {
                this.grayScaleImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if(e.getSource()== showResizeButton){
            // TODO
            this.showResizeImage(Integer.parseInt(widthTextField.getText()),Integer.parseInt(heightTextField.getText()));

        }
        else if(e.getSource()==brightnessButton){
            // TODO
            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();

        }
        else if(e.getSource()== showBrightnessButton){
            // TODO
            try {
                showBrightnessImage(Float.parseFloat(brightnessTextField.getText()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if(e.getSource()== selectFileButton){
            // TODO
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}