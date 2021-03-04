
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics; 
import java.awt.Rectangle; 
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.awt.event.MouseMotionListener; 
import java.io.IOException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import javax.swing.JTabbedPane;

public class Homework extends JFrame implements MouseListener, MouseMotionListener{

	private int drag_status=0,c1,c2,c3,c4; 
	
	private BufferedImage img;
	private BufferedImage imageOriginal = ImageIO.read(new File("C:\\Users\\merve\\Desktop\\advFinal\\circle1.jpg"));
	private BufferedImage imageGx = ImageIO.read(new File("C:\\Users\\merve\\Desktop\\advFinal\\circle1.jpg"));
	private BufferedImage imageGy = ImageIO.read(new File("C:\\Users\\merve\\Desktop\\advFinal\\circle1.jpg"));
	private BufferedImage imageGxAndGy = ImageIO.read(new File("C:\\Users\\merve\\Desktop\\advFinal\\circle1.jpg"));
	private BufferedImage image8x8 = ImageIO.read(new File("C:\\Users\\merve\\Desktop\\advFinal\\circle1.jpg"));
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private int a=0,b=0,c=0,d=0,g=0,f=0;
	private int x = imageGx.getWidth();
    private int y = imageGx.getHeight();
	private int x2 = imageGy.getWidth();
    private int y2 = imageGy.getHeight();
	private int x3 = imageGxAndGy.getWidth();
    private int y3 = imageGxAndGy.getHeight();
	private static List<Double> listOfDegree = new ArrayList<Double>();
	private static List<Double> list = new ArrayList<Double>();
	
	//TAB5 
	public ImageIcon rescaleImage(File source)
	{
		int maxHeight,maxWidth;
		int newHeight = 0, newWidth = 0;        // Variables for the new height and width
		int priorHeight = 0, priorWidth = 0;
		BufferedImage image = null;
		ImageIcon sizeImage;

		try {
				image = ImageIO.read(source);        // get the image
		} catch (Exception e) {

				e.printStackTrace();
				System.out.println("Picture upload attempted & failed");
		}

		sizeImage = new ImageIcon(image);

		if(sizeImage != null)
		{
			priorHeight = sizeImage.getIconHeight(); 
			priorWidth = sizeImage.getIconWidth();
		}

			newWidth = priorWidth*3;
			newHeight = priorHeight*3;
		

		// Resize the image
		// 1. Create a new Buffered Image and Graphic2D object
		BufferedImage resizedImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImg.createGraphics();
		// 2. Use the Graphic object to draw a new image to the image in the buffer
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, 0, 0, newWidth, newHeight, null);
		g2.dispose();
		ImageIcon iconresized = new ImageIcon(resizedImg);

		// 3. Convert the buffered image into an ImageIcon for return
		return iconresized;
	}
	
	public static int  getGrayScale(int rgb) {
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;

		//calculating luminance
		int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);

		return gray;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homework frame = new Homework();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void draggedScreen()throws Exception { 
		int w = c1 - c3; 
		int h = c2 - c4; 
		w = w * -1; h = h * -1; 
		Robot robot=new Robot();
		img = robot.createScreenCapture(new Rectangle(c1, c2,w,h)); 
		File save_path=new File("C:\\Users\\merve\\Desktop\\advFinal\\crop.jpg"); 
		ImageIO.write(img, "JPG", save_path); 
		System.out.println("Cropped image saved successfully.");
	} 
	@Override 
	public void mouseClicked(MouseEvent arg0) { } 
	
	@Override 
	public void mouseEntered(MouseEvent arg0) { } 
	
	@Override 
	public void mouseExited(MouseEvent arg0) { } 
	
	@Override 
	public void mousePressed(MouseEvent arg0) { 
	
		repaint(); 
		c1=arg0.getX(); 
		c2=arg0.getY(); 
	} 
	
	@Override 
	public void mouseReleased(MouseEvent arg0) { 
		repaint(); 
		if(drag_status==1) { 
			c3=arg0.getX(); 
			c4=arg0.getY(); 
			try { 
				draggedScreen();
				} 
			catch(Exception e) { 
				e.printStackTrace(); }
		} 
	} 
	
	@Override 
	public void mouseDragged(MouseEvent arg0) { 
		repaint(); 
		drag_status=1; 
		c3=arg0.getX(); 
		c4=arg0.getY(); 
	} 
	
	@Override 
	public void mouseMoved(MouseEvent arg0) { } 
	
	public void paint(Graphics g){ 
		super.paint(g); 
		
		int w = (c1) - (c3);
		int h = c2 - c4; 
		w = w * -1; h = h * -1; 
		
		if(w<0) w = w * -1; 
		g.drawRect(c1, c2, w, h); 
	} 
	public Homework() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBounds(0, 0, 1000, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 980, 680);
		panel.add(tabbedPane);
		
		//Tab Original
		JPanel original = new JPanel();
		tabbedPane.addTab("Original", null, original, null);
		
		//adding original image
		original.setLayout(null);
		JLabel label = new JLabel(new ImageIcon(imageOriginal));
		//label.setBounds(-118, -66, 725, 410);
		label.setBounds(-20, -66, 725, 410);
		label.setVisible(true); 
		label.addMouseListener(this); 
		label.addMouseMotionListener( this ); 
		original.add(label);
		
		//X direction
		JPanel gx = new JPanel();
		tabbedPane.addTab("X-Direction", null, gx, null);
		
		//Y direction
		JPanel gy = new JPanel();
		tabbedPane.addTab("Y-Direction", null, gy, null);
		
		//X and Y direction
		JPanel edge = new JPanel();
		tabbedPane.addTab("X and Y Directions", null, edge, null);
		
		
		//Angle
		JPanel angle = new JPanel();
		tabbedPane.addTab("Angle", null, angle, null);
		
		//Double Image
		JPanel doubleimg = new JPanel();
		tabbedPane.addTab("Double Image", null, doubleimg, null);

		tabbedPane.addChangeListener(new ChangeListener() { 

                    public void stateChanged(ChangeEvent e) {

                        System.out.println(""+tabbedPane.getSelectedIndex());

                        if(tabbedPane.getSelectedIndex()==0) //original
                        {
                        	if(a<1){
								a++;
							}
                        }
                        if(tabbedPane.getSelectedIndex()==1) //gx
                        {
                        	if(b<1) {
                        		b++;
                           
								int maxGval = 0;
								int[][] edgeColors = new int[x][y];
								int maxGradient = -1;
								System.out.println(x); System.out.println(y);
								for (int i = 1; i < x - 1; i++) {
									for (int j = 1; j < y - 1; j++) {

										int val00 = getGrayScale(imageGx.getRGB(i - 1, j - 1));
										int val01 = getGrayScale(imageGx.getRGB(i - 1, j));
										int val02 = getGrayScale(imageGx.getRGB(i - 1, j + 1));

										int val10 = getGrayScale(imageGx.getRGB(i, j - 1));
										int val11 = getGrayScale(imageGx.getRGB(i, j));
										int val12 = getGrayScale(imageGx.getRGB(i, j + 1));

										int val20 = getGrayScale(imageGx.getRGB(i + 1, j - 1));
										int val21 = getGrayScale(imageGx.getRGB(i + 1, j));
										int val22 = getGrayScale(imageGx.getRGB(i + 1, j + 1));

										int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
												+ ((-2 * val10) + (0 * val11) + (2 * val12))
												+ ((-1 * val20) + (0 * val21) + (1 * val22));

									  
										double gval = Math.sqrt((gx * gx));
										int g = (int) gval;

										if(maxGradient < g) {
											maxGradient = g;
										}

										edgeColors[i][j] = g;
									}
								}

								double scale = 255.0 / maxGradient;

								for (int i = 1; i < x - 1; i++) {
									for (int j = 1; j < y - 1; j++) {
										int edgeColor = edgeColors[i][j];
										edgeColor = (int)(edgeColor * scale);
										edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

										imageGx.setRGB(i, j, edgeColor);
									}
								}

								System.out.println("max : " + maxGradient);
								
								File outputfile = new File("gx.png");
								try {
									ImageIO.write(imageGx, "png", outputfile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JLabel label = new JLabel(new ImageIcon(imageGx));
								label.setBounds(-118, -66, 725, 410);
								gx.add(label);
							}
                        
                        }
                        if(tabbedPane.getSelectedIndex()==2) //gy
                        {
							if(c<1){
								c++;
								 int maxGval = 0;
								 int[][] edgeColors = new int[x2][y2];
								 int maxGradient = -1;

								 for (int i = 1; i < x2 - 1; i++) {
									 for (int j = 1; j < y2 - 1; j++) {

										 int val00 = getGrayScale(imageGy.getRGB(i - 1, j - 1));
										 int val01 = getGrayScale(imageGy.getRGB(i - 1, j));
										 int val02 = getGrayScale(imageGy.getRGB(i - 1, j + 1));

										 int val10 = getGrayScale(imageGy.getRGB(i, j - 1));
										 int val11 = getGrayScale(imageGy.getRGB(i, j));
										 int val12 = getGrayScale(imageGy.getRGB(i, j + 1));

										 int val20 = getGrayScale(imageGy.getRGB(i + 1, j - 1));
										 int val21 = getGrayScale(imageGy.getRGB(i + 1, j));
										 int val22 = getGrayScale(imageGy.getRGB(i + 1, j + 1));

							  

										 int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
												 + ((0 * val10) + (0 * val11) + (0 * val12))
												 + ((1 * val20) + (2 * val21) + (1 * val22));

										 double gval = Math.sqrt(  (gy * gy));
										 int g = (int) gval;

										 if(maxGradient < g) {
											 maxGradient = g;
										 }

										 edgeColors[i][j] = g;
									 }
								 }

								 double scale = 255.0 / maxGradient;

								 for (int i = 1; i < x2 - 1; i++) {
									 for (int j = 1; j < y2 - 1; j++) {
										 int edgeColor = edgeColors[i][j];
										 edgeColor = (int)(edgeColor * scale);
										 edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

										 imageGy.setRGB(i, j, edgeColor);
									 }
								 }
								 System.out.println("max : " + maxGradient);

								 File outputfile = new File("gy.png");
								 try {
									ImageIO.write(imageGy, "png", outputfile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JLabel label = new JLabel(new ImageIcon(imageGy));
								label.setBounds(-118, -66, 725, 410);
								gy.add(label);

							}
                        }
                     	
                        if(tabbedPane.getSelectedIndex()==3) //gx and gy
                        {
                        	if(d<1) {
                        		d++;
								int maxGval = 0;
								int[][] edgeColors = new int[x3][y3];
								int maxGradient = -1;

								for (int i = 1; i < x3 - 1; i++) {
									for (int j = 1; j < y3 - 1; j++) {
										
										int val00 = getGrayScale(imageGxAndGy.getRGB(i - 1, j - 1));
										int val01 = getGrayScale(imageGxAndGy.getRGB(i - 1, j));
										int val02 = getGrayScale(imageGxAndGy.getRGB(i - 1, j + 1));

										int val10 = getGrayScale(imageGxAndGy.getRGB(i, j - 1));
										int val11 = getGrayScale(imageGxAndGy.getRGB(i, j));
										int val12 = getGrayScale(imageGxAndGy.getRGB(i, j + 1));

										int val20 = getGrayScale(imageGxAndGy.getRGB(i + 1, j - 1));
										int val21 = getGrayScale(imageGxAndGy.getRGB(i + 1, j));
										int val22 = getGrayScale(imageGxAndGy.getRGB(i + 1, j + 1));

										int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
												+ ((-2 * val10) + (0 * val11) + (2 * val12))
												+ ((-1 * val20) + (0 * val21) + (1 * val22));

										int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
												+ ((0 * val10) + (0 * val11) + (0 * val12))
												+ ((1 * val20) + (2 * val21) + (1 * val22));

										double gval = Math.sqrt((gx * gx) + (gy * gy));
										int g = (int) gval;
										
										/*double degree= Math.toDegrees(Math.atan(gx, gy));
										System.out.println(degree);
										listOfDegree.add(degree);
										System.out.println(listOfDegree.size());*/
										

										if(maxGradient < g) {
											maxGradient = g;
										}

										edgeColors[i][j] = g;
									}
								}

								double scale = 255.0 / maxGradient;

								for (int i = 1; i < x3 - 1; i++) {
									for (int j = 1; j < y3 - 1; j++) {
										int edgeColor = edgeColors[i][j];
										edgeColor = (int)(edgeColor * scale);
										edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

										imageGxAndGy.setRGB(i, j, edgeColor);
									}
								}
								
								System.out.println("max : " + maxGradient);

								File outputfile = new File("gxandgy.png");
								try {
									ImageIO.write(imageGxAndGy, "png", outputfile);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JLabel label = new JLabel(new ImageIcon(imageGxAndGy));
								label.setBounds(-118, -66, 725, 410);
								edge.add(label);
    						
    						 }
                        }
                        
                        if(tabbedPane.getSelectedIndex()==4) //angle
                        {
							if(f<1){
								f++;
									//Provide number of rows and column
									int row = 21;
									int col = 37;
									
									//width and height of each piece
									int eWidth = imageOriginal.getWidth() / col;
									int eHeight =imageOriginal.getHeight() / row;

									int x = 0;
									int y = 0;
									int i=0,j=0;

									for (int a = 0; a < row; a++) {
										y = 0;
										for (int b= 0; b < col; b++) {
											try {
												System.out.println("creating piece: "+a+" "+b);
												
												BufferedImage SubImage = imageOriginal.getSubimage(y, x, eWidth, eHeight);
												/*File outputfile = new File("C:\\Users\\DELL\\Desktop\\Eclipse Workspace\\FinalProject\\"+i+j+".jpg");
												ImageIO.write(SubImgage, "jpg", outputfile);*/
												int maxGval = 0;
												int[][] edgeColors = new int[x3][y3];
												int maxGradient = -1;

												for ( i = 1; i < SubImage.getWidth() - 1; i++) {
													for ( j = 1; j < SubImage.getHeight() - 1; j++) {

														int val00 = getGrayScale(SubImage.getRGB(i - 1, j - 1));
														int val01 = getGrayScale(SubImage.getRGB(i - 1, j));
														int val02 = getGrayScale(SubImage.getRGB(i - 1, j + 1));

														int val10 = getGrayScale(SubImage.getRGB(i, j - 1));
														int val11 = getGrayScale(SubImage.getRGB(i, j));
														int val12 = getGrayScale(SubImage.getRGB(i, j + 1));

														int val20 = getGrayScale(SubImage.getRGB(i + 1, j - 1));
														int val21 = getGrayScale(SubImage.getRGB(i + 1, j));
														int val22 = getGrayScale(SubImage.getRGB(i + 1, j + 1));

														int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
																+ ((-2 * val10) + (0 * val11) + (2 * val12))
																+ ((-1 * val20) + (0 * val21) + (1 * val22));
														int gy =  ((-1 * val00) + (-2 * val01) + (-1 * val02))
																+ ((0 * val10) + (0 * val11) + (0 * val12))
																+ ((1 * val20) + (2 * val21) + (1 * val22));
														System.out.println(gx +" "+gy);
														try {
														double ori= Math.toDegrees(Math.atan(-gy/gx));
														double mg = Math.sqrt(gx*gx+gy*gy);

				                                       System.out.println("DEGREE "+ori);
				                                       System.out.println("MAGNITUDE "+mg);
				                                       list.add(ori);
														}
														catch(ArithmeticException bc) {
															System.out.println("hata");
														}
														double gval = Math.sqrt((gx * gx) + (gy * gy)); //aynisi ustte yaziyo duzenlersin
														int g = (int) gval;
														if(maxGradient < g) {
															maxGradient = g;
														}
														edgeColors[i][j] = g;
													}
												}

												/*double scale = 255.0 / maxGradient;

												for ( i = 1; i < x3 - 1; i++) {
													for ( j = 1; j < y3 - 1; j++) {
														int edgeColor = edgeColors[i][j];
														edgeColor = (int)(edgeColor * scale);
														edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

														imageGxAndGy.setRGB(i, j, edgeColor);
													}
												}*/
												
												y += eWidth;
												
											} catch (Exception e1 ) {
												e1.printStackTrace();
											}
										}

										x += eHeight;
									}

							}
							}

                        if(tabbedPane.getSelectedIndex()==5) //double image
                        {
							if(g<1){
								g++;
								File resizefile = new File("C:\\Users\\merve\\Desktop\\advFinal\\crop.jpg");
								JLabel thumb = new JLabel();
								thumb.setIcon(rescaleImage(resizefile));
								doubleimg.add(thumb);
							}
                        }
                    }
             });
	}


}
