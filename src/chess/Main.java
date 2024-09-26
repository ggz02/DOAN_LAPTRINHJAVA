/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.border.EmptyBorder;



public class Main extends JFrame implements MouseListener {
      static int count = 0;
	private static final long serialVersionUID = 1L;
	private static final int Height=660;
	private static final int Width=1110;
	private static Rook wr01,wr02,br01,br02;
	private static Knight wk01,wk02,bk01,bk02;
	private static Bishop wb01,wb02,bb01,bb02;
	private static Pawn wp[],bp[];
	private static Queen wq,bq;
	private static King wk,bk;
	private Cell c,previous;
	private int chance=0;
	private Cell boardState[][];
	private ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	private Player White=null,Black=null;
	private JPanel board=new JPanel(new GridLayout(8,8));
	private JPanel wdetails=new JPanel(new GridLayout(3,3));
	private JPanel bdetails=new JPanel(new GridLayout(3,3));
	private JPanel wcombopanel=new JPanel();
	private JPanel bcombopanel=new JPanel();
	private JPanel controlPanel,WhitePlayer,BlackPlayer,temp,displayTime,showPlayer,time;
	private JSplitPane split;
	private JLabel label,mov;
	private static JLabel CHNC;
	private Time timer;
	public static Main Mainboard;
	private boolean selected=false,end=false;
	private Container content;
	private ArrayList<Player> wplayer,bplayer;
	private ArrayList<String> Wnames=new ArrayList<String>();
	private ArrayList<String> Bnames=new ArrayList<String>();
	private JComboBox<String> wcombo,bcombo;
	private String wname=null,bname=null,winner=null;
	static String move;
	private Player tempPlayer;
	private JScrollPane wscroll,bscroll;
	private String[] WNames={},BNames={};
	private JSlider timeSlider;
	private BufferedImage image;
	private Button start,wselect,bselect,WNewPlayer,BNewPlayer;
	public static int timeRemaining=60;
       
        //Thuộc tính xây dựng giao diện mở đầu
      public Timer timerFUI;
      private static final int delayTimer = 1000;
      public JButton btnPlay;
      public JButton btnInfor;
      public JPanel panelBtnPlay ;
      public JPanel panelBtnInfor;
      public JLayeredPane jLayeredPane;  
        
	public static void main(String[] args){
	//Khởi tạo các giá trị cho các quân cờ
	wr01=new Rook("WR01","White_Rook.png",0);
	wr02=new Rook("WR02","White_Rook.png",0);
	br01=new Rook("BR01","Black_Rook.png",1);
	br02=new Rook("BR02","Black_Rook.png",1);
	wk01=new Knight("WK01","White_Knight.png",0);
	wk02=new Knight("WK02","White_Knight.png",0);
	bk01=new Knight("BK01","Black_Knight.png",1);
	bk02=new Knight("BK02","Black_Knight.png",1);
	wb01=new Bishop("WB01","White_Bishop.png",0);
	wb02=new Bishop("WB02","White_Bishop.png",0);
	bb01=new Bishop("BB01","Black_Bishop.png",1);
	bb02=new Bishop("BB02","Black_Bishop.png",1);
	wq=new Queen("WQ","White_Queen.png",0);
	bq=new Queen("BQ","Black_Queen.png",1);
	wk=new King("WK","White_King.png",0,7,4);
	bk=new King("BK","Black_King.png",1,0,4);
	wp=new Pawn[8];
	bp=new Pawn[8];
	for(int i=0;i<8;i++)
	{
		wp[i]=new Pawn("WP0"+(i+1),"White_Pawn.png",0);
		bp[i]=new Pawn("BP0"+(i+1),"Black_Pawn.png",1);
	}
	
         Mainboard = new Main();
         Mainboard.setTitle("Game Chess by Giang & Tam");
         Mainboard.setSize(1100,660);
         Mainboard.setVisible(true);	
         Mainboard.setResizable(false);
         Mainboard.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
        
          public void runUI() {
        jLayeredPane = new JLayeredPane();
        jLayeredPane.setBounds(0, 0, 1100, 660);
        add(jLayeredPane);

        JPanel panelHome = new JPanel();
        panelHome.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(this.getClass().getResource("d.png")));
        panelHome.add(label);
        label.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelHome.setBounds(0,0,1100,700);
        jLayeredPane.add(panelHome,JLayeredPane.DEFAULT_LAYER);
        panelBtnPlay = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        btnPlay = new JButton();
        btnPlay.setIcon(new ImageIcon(this.getClass().getResource("play2.png")));
        btnPlay.setBorder(null);
        btnPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPlay.setPreferredSize(new Dimension(113,112));
        panelBtnPlay.add(btnPlay);
        panelBtnPlay.setBounds(508, 450, 113, 112);
        jLayeredPane.add(panelBtnPlay,JLayeredPane.DRAG_LAYER);
        panelBtnInfor = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jLayeredPane.add(panelBtnInfor,JLayeredPane.DRAG_LAYER);
        btnInfor = new JButton();
        btnInfor.setIcon(new ImageIcon(this.getClass().getResource("i2.png")));
        btnInfor.setBorder(null);
        btnInfor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInfor.setPreferredSize(new Dimension(39,39));
        panelBtnInfor.add(btnInfor);
        panelBtnInfor.setBounds(20,20,39,39);
        
        wplayer= Player.fetch_players();
        Iterator<Player> witr=wplayer.iterator();
        while(witr.hasNext())
            Wnames.add(witr.next().name());
				
         bplayer= Player.fetch_players();
         Iterator<Player> bitr=bplayer.iterator();
         while(bitr.hasNext())
		Bnames.add(bitr.next().name());
                
        WNames=Wnames.toArray(WNames);	
        BNames=Bnames.toArray(BNames);

        wcombo=new JComboBox<String>(WNames);
        bcombo=new JComboBox<String>(BNames);
        wcombo.setVisible(false);
        bcombo.setVisible(false);
        
        btnPlay.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        // Lớn hơn kích thước ban đầu
        int newWidth = (int) (112 * 1.05);
        int newHeight = (int) (111 * 1.05);
        int x = panelBtnPlay.getX();
        int y = panelBtnPlay.getY();
        int newX = x - (newWidth - panelBtnPlay.getWidth()) / 2;
        int newY = y - (newHeight - panelBtnPlay.getHeight()) / 2;
        btnPlay.setPreferredSize(new Dimension(newWidth, newHeight));
        btnPlay.revalidate();
        btnPlay.repaint();
        panelBtnPlay.setBounds(newX, newY, newWidth, newHeight);
        panelBtnPlay.revalidate();
        panelBtnPlay.repaint();
            
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Trả lại kích thước ban đầu
        int newWidth = 112;
        int newHeight = 111;
        btnPlay.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnPlay.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnPlay.revalidate();
        panelBtnPlay.repaint();
        btnPlay.revalidate();
        btnPlay.repaint();
        panelBtnPlay.setBounds(508, 450, newWidth, newHeight);
        panelBtnPlay.revalidate();
        panelBtnPlay.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int newWidth = (int) (btnPlay.getWidth() * 0.85);
        int newHeight = (int) (btnPlay.getWidth() * 0.85);
        int x = panelBtnPlay.getX();
        int y = panelBtnPlay.getY();
        int newX = x - (newWidth - panelBtnPlay.getWidth()) / 2;
        int newY = y - (newHeight - panelBtnPlay.getHeight()) / 2;
        btnPlay.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnPlay.repaint();
        btnPlay.revalidate();
        btnPlay.repaint();
        panelBtnPlay.setBounds(508, 450, newWidth, newHeight);
        panelBtnPlay.revalidate();
        panelBtnPlay.repaint();
    }
}); 
        
      btnInfor.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
        // Lớn hơn kích thước ban đầu
        int newWidth = (int) (39 * 1.05);
        int newHeight = (int) (39 * 1.05);
        int x = panelBtnInfor.getX();
        int y = panelBtnInfor.getY();
        int newX = x - (newWidth - panelBtnInfor.getWidth()) / 2;
        int newY = y - (newHeight - panelBtnInfor.getHeight()) / 2;
        btnInfor.setPreferredSize(new Dimension(newWidth, newHeight));
        btnInfor.revalidate();
        btnInfor.repaint();
        panelBtnInfor.setBounds(newX, newY, newWidth, newHeight);
        panelBtnInfor.revalidate();
        panelBtnInfor.repaint();
            
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Trả lại kích thước ban đầu
        int newWidth = 39;
        int newHeight = 39;
        btnInfor.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnInfor.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnInfor.revalidate();
        panelBtnInfor.repaint();
        btnInfor.revalidate();
        btnInfor.repaint();
        panelBtnInfor.setBounds(20, 20, newWidth, newHeight);
        panelBtnInfor.revalidate();
        panelBtnInfor.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int newWidth = (int) (btnInfor.getWidth() * 0.85);
        int newHeight = (int) (btnInfor.getWidth() * 0.85);
        int x = panelBtnInfor.getX();
        int y = panelBtnInfor.getY();
        int newX = x - (newWidth - panelBtnInfor.getWidth()) / 2;
        int newY = y - (newHeight - panelBtnInfor.getHeight()) / 2;
        btnInfor.setPreferredSize(new Dimension(newWidth, newHeight));
        panelBtnInfor.repaint();
        btnInfor.revalidate();
        btnInfor.repaint();
        panelBtnInfor.setBounds(20, 20, newWidth, newHeight);
        panelBtnInfor.revalidate();
        panelBtnInfor.repaint();
    }
});
      
      btnInfor.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
              btnInforActionEvent(e);
          }
      });
      
      btnPlay.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e) {
              btnPlayActionEvent(e);
          }
      });
    }
    
    public void btnInforActionEvent(ActionEvent evt) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Thông tin");
        int xDialogCenter = Mainboard.getX() + (Width / 2) - 120;
        int yDialogCenter = Mainboard.getY() + (Height / 2) - 100;
        dialog.setBounds(xDialogCenter, yDialogCenter, 250, 150);
        
        JPanel mainGui = new JPanel();
        mainGui.setPreferredSize(new Dimension(250,150));
        mainGui.setLayout(new GridLayout(2,1));
        mainGui.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainGui.setBorder(BorderFactory.createTitledBorder(null,"Thông tin DEV",TitledBorder.TOP,TitledBorder.CENTER,new Font("Popins",Font.BOLD,20)));
        JLabel name1 = new JLabel();
        name1.setText("Phạm Minh Tâm - 2121005223");
        name1.setFont(new Font("times new roman",3,14));
        name1.setHorizontalAlignment(JLabel.CENTER);
        mainGui.add(name1);
        
        JLabel name2 = new JLabel();
        name2.setText("Nguyễn Trà Giang - 2121001008");
        name2.setFont(new Font("times new roman",3,14));
        name2.setHorizontalAlignment(JLabel.CENTER);
        
        mainGui.add(name2);
        dialog.setContentPane(mainGui);
        dialog.pack();
        dialog.setVisible(true);
    }
    
    public void btnPlayActionEvent(ActionEvent evt) {
        timerFUI = new Timer(delayTimer,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLayeredPane.setVisible(false); 
                wcombo.setVisible(true);
                bcombo.setVisible(true);
            }
        });
        timerFUI.start();
    }
	

	private Main()
    {
            count++;
            if (count == 1) {
            runUI();
            } else {
            wplayer= Player.fetch_players();
            Iterator<Player> witr=wplayer.iterator();
            while(witr.hasNext())
                Wnames.add(witr.next().name());

             bplayer= Player.fetch_players();
             Iterator<Player> bitr=bplayer.iterator();
             while(bitr.hasNext())
                    Bnames.add(bitr.next().name());

            WNames=Wnames.toArray(WNames);	
            BNames=Bnames.toArray(BNames);

            wcombo=new JComboBox<String>(WNames);
            bcombo=new JComboBox<String>(BNames);
            }
		timeRemaining=60;
            //tạo thanh trượt
		timeSlider = new JSlider();
		move="White";
		wname=null;
		bname=null;
		winner=null;
		board=new JPanel(new GridLayout(8,8));
		wdetails=new JPanel(new GridLayout(3,3));
		bdetails=new JPanel(new GridLayout(3,3));
		bcombopanel=new JPanel();
		wcombopanel=new JPanel();
		Wnames=new ArrayList<String>();
		Bnames=new ArrayList<String>();
		board.setMinimumSize(new Dimension(800,700));
		ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
		this.setIconImage(img.getImage());
		
            //Chỉnh time ở đầu giao diện
		timeSlider.setMinimum(1);
		timeSlider.setMaximum(15);
		timeSlider.setValue(1);
		timeSlider.setMajorTickSpacing(2);
		timeSlider.setPaintLabels(true);
		timeSlider.setPaintTicks(true);
		timeSlider.addChangeListener(new TimeChange());
		
		

		
		Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		pieces.Piece P;
		content=getContentPane();

		content.setBackground(Color.black);
		controlPanel=new JPanel();
		content.setLayout(new BorderLayout());
		controlPanel.setLayout(new GridLayout(3,3));
		controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,TitledBorder.CENTER, new Font("Lucida Calligraphy",Font.PLAIN,20), Color.ORANGE));
		
            //Giao diện đầu vào chọn người người chơi các thứ các kiểu
		WhitePlayer=new JPanel();
		WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.RED));
		WhitePlayer.setLayout(new BorderLayout());
		
		BlackPlayer=new JPanel();
		BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.BLUE));
            BlackPlayer.setLayout(new BorderLayout());
		
           JPanel whitestats=new JPanel(new GridLayout(3,3));
		JPanel blackstats=new JPanel(new GridLayout(3,3));

		wscroll=new JScrollPane(wcombo);
		bscroll=new JScrollPane(bcombo);
		wcombopanel.setLayout(new FlowLayout());
		bcombopanel.setLayout(new FlowLayout());
		wselect=new Button("Select");
		bselect=new Button("Select");
		wselect.addActionListener(new SelectHandler(0));
		bselect.addActionListener(new SelectHandler(1));
		WNewPlayer=new Button("New Player");
		BNewPlayer=new Button("New Player");
		WNewPlayer.addActionListener(new Handler(0));
		BNewPlayer.addActionListener(new Handler(1));
		wcombopanel.add(wscroll);
		wcombopanel.add(wselect);
		wcombopanel.add(WNewPlayer);
		bcombopanel.add(bscroll);
		bcombopanel.add(bselect);
		bcombopanel.add(BNewPlayer);
		WhitePlayer.add(wcombopanel,BorderLayout.NORTH);
		BlackPlayer.add(bcombopanel,BorderLayout.NORTH);
		whitestats.add(new JLabel("Name   :"));
		whitestats.add(new JLabel("Played :"));
		whitestats.add(new JLabel("Won    :"));
		blackstats.add(new JLabel("Name   :"));
		blackstats.add(new JLabel("Played :"));
		blackstats.add(new JLabel("Won    :"));
		WhitePlayer.add(whitestats,BorderLayout.WEST);
		BlackPlayer.add(blackstats,BorderLayout.WEST);
		controlPanel.add(WhitePlayer);
		controlPanel.add(BlackPlayer);
                
		showPlayer=new JPanel(new FlowLayout());  
		showPlayer.add(timeSlider);
		JLabel setTime=new JLabel("Set Timer(in mins):"); 
		start=new Button("Start");
		start.setBackground(Color.black);
		start.setForeground(Color.white);
	    start.addActionListener(new START());
		start.setPreferredSize(new Dimension(120,40));
		setTime.setFont(new Font("Arial",Font.BOLD,16));
                
           //chữ ở dưới move khi mới bắt đầu
		label = new JLabel("Time Starts now", JLabel.CENTER);
		  label.setFont(new Font("SERIF", Font.BOLD, 30));
	      displayTime=new JPanel(new FlowLayout());
	      time=new JPanel(new GridLayout(3,3));
	      time.add(setTime);
	      time.add(showPlayer);
	      displayTime.add(start);
	      time.add(displayTime);
	      controlPanel.add(time);
		board.setMinimumSize(new Dimension(700,700));
                
                
                
            // Xét vị trí quân cờ trên bàn cờ
		boardState=new Cell[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	
				P=null;    
				if(i==0&&j==0)
					P=br01;
				else if(i==0&&j==7)
					P=br02;
				else if(i==7&&j==0)
					P=wr01;
				else if(i==7&&j==7)
					P=wr02;
				else if(i==0&&j==1)
					P=bk01;
				else if (i==0&&j==6)
					P=bk02;
				else if(i==7&&j==1)
					P=wk01;
				else if (i==7&&j==6)
					P=wk02;
				else if(i==0&&j==2)
					P=bb01;
				else if (i==0&&j==5)
					P=bb02;
				else if(i==7&&j==2)
					P=wb01;
				else if(i==7&&j==5)
					P=wb02;
				else if(i==0&&j==4)
					P=bk;
				else if(i==0&&j==3)
					P=bq;
				else if(i==7&&j==4)
					P=wk;
				else if(i==7&&j==3)
					P=wq;
				else if(i==1)
				P=bp[j];
				else if(i==6)
					P=wp[j];
				cell=new Cell(i,j,P);
				cell.addMouseListener(this);
				board.add(cell);
				boardState[i][j]=cell;
			}
		
            //Layout bên trái khi game không hoạt động
		temp=new JPanel(){
			private static final long serialVersionUID = 1L;
			     
			@Override
		    public void paintComponent(Graphics g) {
				  try {
			          image = ImageIO.read(this.getClass().getResource("clash.jpg"));
			       } catch (IOException ex) {
			            System.out.println("not found");
			       }
			   
				g.drawImage(image, 0, 0, null);
			}         
	    };

		temp.setMinimumSize(new Dimension(800,700));
		controlPanel.setMinimumSize(new Dimension(285,700));
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,temp, controlPanel);
		
            content.add(split);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	

      // Thuật toán thay đổi người chơi khi hết thời gian
	public void changechance()
	{
		if (boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
		{
			chance^=1;
			gameend();
		}
		if(destinationlist.isEmpty()==false)
			cleandestinations(destinationlist);
		if(previous!=null)
			previous.deselect();
		previous=null;
		chance^=1;
		if(!end && timer!=null)
		{
			timer.reset();
			timer.start();
			showPlayer.remove(CHNC);
			if(Main.move=="White")
				Main.move="Black";
			else
				Main.move="White";
			CHNC.setText(Main.move);
			showPlayer.add(CHNC);
		}
	}
	
	//Phương thức trả về vua đen hoặc vua trắng phụ thuộc vào màu đang xét
	private King getKing(int color)
	{
		if (color==0)
			return wk;
		else
			return bk;
	}
	
	
        // Phương thức xóa các phương án khả dĩ
    private void cleandestinations(ArrayList<Cell> destlist)      
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removepossibledestination();
    }
    
  
    //Phương thức hiện các phương án khả dỉ
    private void highlightdestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setpossibledestination();
    }
    
    

     //kiểm tra xem nước đi tiếp theo có đưa Vua vào trạng thái bị chiếu không
    private boolean willkingbeindanger(Cell fromcell,Cell tocell)
    {
     //Tạo mảng newboardstate: Lưu trạng thái hiện tại của bàn cờ
    	Cell newboardstate[][] = new Cell[8][8];
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
                 //Sao chép trạng thái hiện tại của bàn cờ
    		{	try {
                    newboardstate[i][j] = new Cell(boardState[i][j]);
                }catch (CloneNotSupportedException e){
                e.printStackTrace();
                System.out.println("There is a problem with cloning !!"); }
                }
                //Nếu ô cờ muốn di chuyển đến đã có quân cờ, loại bỏ ô đó khỏi các phương án di chuyển 
            if(newboardstate[tocell.x][tocell.y].getpiece()!=null)
			newboardstate[tocell.x][tocell.y].removePiece();
            //Đặt quân cờ vào vị trí mới
		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
            //Nếu quân cờ được di chuyển là Vua thì set tọa độ mới cho quân Vua
		if(newboardstate[tocell.x][tocell.y].getpiece() instanceof King)
		{
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
		}
            //Xóa quân cờ ở ô cũ 
		newboardstate[fromcell.x][fromcell.y].removePiece();
            //Nếu ví trí nước đi tiếp theo có thể làm vua bị chiếu thì return true 
		if (((King)(newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate)==true)
			return true;
		else
			return false;
    }
    
     // Các phương án di chuyển an toàn nếu vua bị chiếu của bên di chuyển
    private ArrayList<Cell> filterdestination(ArrayList<Cell> destlist, Cell fromcell) {
        //Khởi tạo một danh sách newlist: Lưu trữ các phương án di chuyển khả dĩ từ vị trí ban đầu của quân cờ
        ArrayList<Cell> newlist = new ArrayList<Cell>();
        //Tạo mảng newboardstate: Lưu trạng thái hiện tại của bàn cờ
        Cell newboardstate[][] = new Cell[8][8];
        //Sử dụng ListIterator: Lặp qua danh sách các phương án di chuyển khả dĩ từ vị trí ban đầu của quân cờ
        ListIterator<Cell> it = destlist.listIterator();
        int x, y;
        while (it.hasNext()) {
            //Sao chép trạng thái hiện tại của bàn cờ
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    try {
                        newboardstate[i][j] = new Cell(boardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //Trong mỗi vòng lặp, lấy ô tiếp theo trong danh sách các ô mà quân cờ có thể di chuyển
            Cell tempc = it.next();
            //Nếu ô cờ muốn di chuyển đến đã có quân cờ, loại bỏ ô đó khỏi các phương án di chuyển di chuyển
            if (newboardstate[tempc.x][tempc.y].getpiece() != null) {
                newboardstate[tempc.x][tempc.y].removePiece();
            }
            //Đặt quân cờ vào vị trí mới
            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
            //Lưu tọa độ quân Vua của bên di chuyển (Trắng/Đen) ở trạng thái hiện tại của bàn cờ
            x = getKing(chance).getx();
            y = getKing(chance).gety();
            //Nếu quân cờ được di chuyển là Vua thì set tọa độ mới cho quân Vua
            if (newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King) {
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
                ((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
                x = tempc.x;
                y = tempc.y;
            }
            //Xóa quân cờ ở ô cũ 
            newboardstate[fromcell.x][fromcell.y].removePiece();
            //Kiểm tra xem sau khi di chuyển quân cờ, Vua của bên di chuyển chance (Trắng/Đen) có bị chiếu không,
            if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) == false)) {
                //Nếu không thì lưu vào danh sách các phương án di chuyển khả dĩ
                newlist.add(tempc);
            }
        }
        return newlist;
    }
    
    // Các phương án di chuyển an toàn nếu vua bị chiếu 
    private ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
    {
      //Khởi tạo một danh sách newlist: Lưu trữ các phương án di chuyển khả dĩ từ vị trí ban đầu của quân cờ
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
      //Tạo mảng newboardstate: Lưu trạng thái hiện tại của bàn cờ
    	Cell newboardstate[][] = new Cell[8][8];
     //Sử dụng ListIterator: Lặp qua danh sách các phương án di chuyển khả dĩ từ vị trí ban đầu của quân cờ
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
            //Sao chép trạng thái hiện tại của bàn cờ
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try {
                            newboardstate[i][j] = new Cell(boardState[i][j]);
                        }catch (CloneNotSupportedException e){
                           e.printStackTrace();}
                        }
            //Trong mỗi vòng lặp, lấy ô tiếp theo trong danh sách các ô mà quân cờ có thể di chuyển
    		Cell tempc = it.next();
            //Nếu ô cờ muốn di chuyển đến đã có quân cờ, loại bỏ ô đó khỏi các phương án di chuyển di chuyển
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
            //Đặt quân cờ vào vị trí mới
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
           //Lưu tọa độ quân Vua của bên di chuyển (Trắng/Đen) ở trạng thái hiện tại của bàn cờ
    		x=getKing(color).getx();
    		y=getKing(color).gety();
            //Nếu quân cờ được di chuyển là Vua thì set tọa độ mới cho quân Vua
    		if(newboardstate[tempc.x][tempc.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
            //Xóa quân cờ ở vị trí cũ
    		newboardstate[fromcell.x][fromcell.y].removePiece();
            //Kiểm tra xem sau khi di chuyển quân cờ, Vua của bên di chuyển chance (Trắng/Đen) có bị chiếu không,
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
            //Nếu không thì lưu vào danh sách các phương án di chuyển khả dĩ
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    // Kiểm tra xem vua có bị chiếu bí hay ko nếu bị chiếu bí rồi thì return true kết thúc trò chơi
    public boolean checkmate(int color)
    {
      //Khởi tạo một danh sách newlist: Lưu trữ các phương án di chuyển khả dĩ của bên di chuyển
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{       
                  //Đối với mỗi ô cờ trên bàn cờ, nếu có quân cờ và cùng màu với lượt di chuyển 
    			if (boardState[i][j].getpiece()!=null && boardState[i][j].getpiece().getcolor()==color)
    			{
                        //Xóa danh sách di chuyển hiện tại
    				dlist.clear();
                       //lấy danh sách các nước đi hợp lệ của quân cờ ở vị trí [i,j] bằng cách gọi phương thức move() của đối tượng quân cờ
    				dlist=boardState[i][j].getpiece().move(boardState, i, j);
                        //lọc danh sách các nước đi có gây nguy hiểm
    				dlist=incheckfilter(dlist,boardState[i][j],color);
                       //Nếu danh sách mới này có ít nhất một nước đi khả dĩ để bảo vệ Vua,
    				if(dlist.size()!=0)
                             //trả về false tức là vua chưa bị chiếu hết
    					return false;
    			}
    		}
    	}
      //Sau khi kiểm tra tất cả các trường hợp mà vẫn ko quân cờ nào có thể bảo vệ cho vua của lượt di chuyển đang kiểm tra thì coi như chiếu hết
    	return true;
    }
	
    
    @SuppressWarnings("deprecation")
	private void gameend()
    {
      //xóa các phương án khã dĩ có thể di chuyển
    	cleandestinations(destinationlist);
      //ngừng chỉnh sửa 
    	displayTime.disable();
       //dừng thời gian
    	timer.countdownTimer.stop();
      //Nếu có quân cờ được chọn trước đó
    	if(previous!=null)
            //thì xóa chọn quân cờ đó
    		previous.removePiece();
        //Nếu bên trắng thắng thì cập nhật lại thông tin bên trắng
    	if(chance==0)
		{	
                 White.updateGamesWon();
			White.Update_Player();
			winner=White.name();
		}
        //Ngược lại den thắng thì cập nhật lại thông tin bên trắng
		else
		{
			Black.updateGamesWon();
			Black.Update_Player();
			winner=Black.name();
		}
        //Hiện thông báo người chiến thắng
		JOptionPane.showMessageDialog(board,"Checkmate!!!\n"+winner+" wins");
         //đưa về giao diện ban đầu
		WhitePlayer.remove(wdetails);
		BlackPlayer.remove(bdetails);
		displayTime.remove(label);
		
		displayTime.add(start);
		showPlayer.remove(mov);
		showPlayer.remove(CHNC);
		showPlayer.revalidate();
		showPlayer.add(timeSlider);
		
		split.remove(board);
		split.add(temp);
		WNewPlayer.enable();
		BNewPlayer.enable();
		wselect.enable();
		bselect.enable();
		end=true;
            Mainboard.disable();
		Mainboard.dispose();
            Mainboard = new Main();
            Mainboard.setSize(1100,660);
            Mainboard.setVisible(true);	
            Mainboard.setResizable(false);

    }
    

      // Sự kiện xảy ra khi click vào một quân cờ
	@Override
	public void mouseClicked(MouseEvent arg0){
            //Khởi tạo đối tượng sẽ diên ra sự kiện
		c=(Cell)arg0.getSource();
            //Nếu chưa có ô cờ nào được chọn trước đó
		if (previous==null)
		{     //Nếu ô cờ diễn ra sự kiện có đang chứa quân cờ
			if(c.getpiece()!=null)
			{      //Nếu nó là quân cờ khác màu với lượt bên người chơi di chuyển
				if(c.getpiece().getcolor()!=chance)
                             //trả về rỗng;
					return;
                        //Nếu cùng màu với lượt chơi của người di chuyển
                        //ô cờ đó được chọn
				c.select();
                        //Khởi tạo giá trị previous là ô cờ được chọn
				previous=c;
                        //Xóa các phương án khã dĩ trước đó
				destinationlist.clear();
                        //Luu các phương án khả dĩ của quân cờ được chọn
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
                        //Nếu quân cờ được chọn để bắt sự kiện là vua
				if(c.getpiece() instanceof King)
                              //lưu các phương án khã dĩ là các Các phương án di chuyển an toàn cho vua của bên di chuyển
					destinationlist=filterdestination(destinationlist,c);
				else
				{     //Kiểm tra bàn cờ của bên di chuyển có đang bị chiếu không
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
                             //nó sẽ lọc các phương án di chuyển mà quân cờ đó có thể di chuyển đến sao cho tướng không bị chiếu.
						destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
                             //Nếu không sẽ kiểm tra xem các phương án khả dĩ có phương án nào làm cho tướng bị chiếu nếu di chuyển đến đó
					else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
                                   //nó sẽ loại bỏ các phương án đó.
						destinationlist.clear();
				}
                       //Hiện gợi ý các phương án di chuyển 
				highlightdestinations(destinationlist);
			}
		}
            //Đã chọn được quân cờ trước đó
		else
		{   
                    //Nếu ô cờ diễn ra sự kiện trùng với quân cờ đã được chọn trước đó thì hành động đó được xem là hủy chọn nên nó sẽ hủy các chức năng 
			if(c.x==previous.x && c.y==previous.y)
			{
                        //Hủy chọn ở ô diễn ra sự kiện đó
				c.deselect();
                        //Xóa danh sách gợi ý các phương án khã dĩ
				cleandestinations(destinationlist);
                        //Xóa các phương án khã dĩ
				destinationlist.clear();
                        //Khởi tạo lại giá trị đã được chọn trc đó là rỗng
				previous=null;
			}
                  //Nếu ô cờ được chọn diễn ra sự kiện là rỗng hoặc là ô chưa của một quân cờ khác bên đối thủ
			else if(c.getpiece()==null||previous.getpiece().getcolor()!=c.getpiece().getcolor())
			{
                        //Kiểm tra xem ô cờ đó khi di chuyển có hợp lệ hay không
				if(c.ispossibledestination())
				{
                              //Nếu ô đó có quân cờ
					if(c.getpiece()!=null)
                              //xóa quân cờ
                               c.removePiece();
                              //set ô đó chưa quân cờ đã chọn trước đó
					c.setPiece(previous.getpiece());
                              //
                             //Nếu quân cờ trước đó còn đang kiểm tra bị chiếu hay không
					if (previous.ischeck())
                                 //xóa kiểm tra
						previous.removecheck();
                             //Xóa quân cờ ở vị trí cũ
					previous.removePiece();
                             //
                             //nếu quân vua của bên đối thủ có bị chiếu
					if(getKing(chance^1).isindanger(boardState))
					{      //Thì setcheck(dưới quân vua sẽ có nền màu đỏ) cho quân vua đó
						boardState[getKing(chance^1).getx()][getKing(chance^1).gety()].setcheck();
                                   //Nếu vua của bên đối thủ bị chiếu hết
						if (checkmate(getKing(chance^1).getcolor()))
						{
                                        //Hủy chọn quân cờ trước đó
							previous.deselect();
                                         //Nếu đã có quân cờ được chọn trước đó
							if(previous.getpiece()!=null)
                                         //thì xóa quân cờ đó
								previous.removePiece();
                                         //kết thúc trò chơi
							gameend();
						}
					}
                             //Nếu quân vua của bên đang di chuyển không bị chiếu
					if(getKing(chance).isindanger(boardState)==false)
                                    //Thì xóa kiểm tra cho quân vua đó    
						boardState[getKing(chance).getx()][getKing(chance).gety()].removecheck();
                             //Nếu quân cờ được chọn để diễn ra sự kiện là quân vua 
					if(c.getpiece() instanceof King)
					{
                                    //cập nhật vị trí mới cho quân vua
						((King)c.getpiece()).setx(c.x);
						((King)c.getpiece()).sety(c.y);
					}
                             //Chuyển lượt chơi
					changechance();
                             //Nếu chưa hết thời gian
					if(!end)
					{
                                   //Reset lại thời gian
						timer.reset();
						timer.start();
					}
				}
 
                       //Nếu đã có quân cờ được chọn trước đó
				if(previous!=null)
				{
                             //Xóa chọn
					previous.deselect();
                             //cập nhật lại quân cờ được chọn trước đó là rỗng
					previous=null;
				}
                        //Xóa các ô gợi ý các phương án khã dĩ
				cleandestinations(destinationlist);
                        //Xóa danh sách gợi ý
				destinationlist.clear();
			}
                   //Nếu quân cờ đã được chọn trước đó cùng màu với ô cờ chứa quân cờ thực hiện sự kiện
			else if(previous.getpiece().getcolor()==c.getpiece().getcolor())
			{
                       //Xóa chọn cho quân cờ đã chọn trước đó
				previous.deselect();
                        //Xóa các ô gợi ý các phương án khã dĩ 
				cleandestinations(destinationlist);
                        //Xóa danh sách gợi ý
				destinationlist.clear();
                       //Xác nhận ô cờ được chọn
				c.select();
                       //Khởi tạo lại quân cờ đã chọn trước đó là quân cờ thực hiện sự kiện hiện tại
				previous=c;
                       //Lưu các phương án khã dĩ của quân cờ thực hiện sự kiện
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
                       //Nếu ô cờ thực hiện sự kiện có quân cờ là vua
				if(c.getpiece() instanceof King)
                             ////Lưu các phương án khã dĩ an toàn cho vua vào danh sách các phương án khả dĩ
					destinationlist=filterdestination(destinationlist,c);
				else
				{
                             ////Kiểm tra quân vua của người chơi hiện tại có đang bị chiếu không
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
                                    //nó sẽ lọc các phương án di chuyển mà quân cờ đó có thể di chuyển đến sao cho tướng không bị chiếu.
						destinationlist = new ArrayList<Cell>(filterdestination(destinationlist,c));
                                   //Nếu không sẽ kiểm tra xem các phương án khả dĩ có phương án nào làm cho vua bị chiếu nếu di chuyển đến đó không
					else if(destinationlist.isEmpty()==false && willkingbeindanger(c,destinationlist.get(0)))
                                   //Nếu có thì xóa phương án đó
						destinationlist.clear();
				}
                       //Hiện gợi ý các phương án di chuyển đó
				highlightdestinations(destinationlist);
			}
		}
            //Nếu ô cờ được chọn có quân cờ là vua
		if(c.getpiece()!=null && c.getpiece() instanceof King)
		{
                   //Xét vị trí mới cho quân vua
			((King)c.getpiece()).setx(c.x);
			((King)c.getpiece()).sety(c.y);
		}
	}
    
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {

	}
	@Override
	public void mousePressed(MouseEvent arg0) {

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	
	class START implements ActionListener
	{

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
            //Nếu chưa hiện chọn người chơi thì hiện thông báo
		if(White==null||Black==null)
			{JOptionPane.showMessageDialog(controlPanel, "Vui lòng chọn người chơi");
			return;}
            //Tránh tương tác thông thi ở giao diện trò chơi
		White.updateGamesPlayed();
		White.Update_Player();
		Black.updateGamesPlayed();
		Black.Update_Player();
		WNewPlayer.disable();
		BNewPlayer.disable();
		wselect.disable();
		bselect.disable();
            //chuyển đổi giao diện
		split.remove(temp);
		split.add(board);
            //Xóa thanh slider thời gian
		showPlayer.remove(timeSlider);
            //chuyển thành các chữ move diễn tả lượt của người chơi bên nào
		mov=new JLabel("Move:");
		mov.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
		mov.setForeground(Color.red);
		showPlayer.add(mov);
		CHNC=new JLabel(move);
		CHNC.setFont(new Font("Comic Sans MS",Font.BOLD,20));
		CHNC.setForeground(Color.blue);
		showPlayer.add(CHNC);
		displayTime.remove(start);
		displayTime.add(label);
		timer=new Time(label);
		timer.start();
	}
	}
	
	class TimeChange implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			timeRemaining=timeSlider.getValue()*60;
		}
	}
	
	
	class SelectHandler implements ActionListener
	{
		private int color;
		
		SelectHandler(int i)
		{
			color=i;
		}
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
                       //Khởi tạo biến tempPlayer với giá trị null
				tempPlayer=null;
                       //Lưu trữ tên người chơi được chọn
				String n=(color==0)?wname:bname;
                        // khởi tạo các biến jc và ojc để truy cập các JComboBox của hai màu cờ.
				JComboBox<String> jc=(color==0)?wcombo:bcombo;
                       //danh sách người chơi.
				JComboBox<String> ojc=(color==0)?bcombo:wcombo;
                        //danh sách người chơi đối thủ
				ArrayList<Player> pl=(color==0)?wplayer:bplayer;
                       //Lấy tất cả các người chơi được lưu trữ
				ArrayList<Player> opl=Player.fetch_players();
                       //danh sách người chơi của màu còn lại rỗng, thì kết thúc
				if(opl.isEmpty())
					return;
                            //lưu các hiển thị thông tin chi tiết của người chơi được chọn
				JPanel det=(color==0)?wdetails:bdetails;
                             //tương ứng với màu của người chơi
				JPanel PL=(color==0)?WhitePlayer:BlackPlayer; 
                        //nếu đã chọn người chơi trước đó,
				if(selected==true)
                             //xóa pannel hiển thị chi tiết người chơi
					det.removeAll();
                       //Lấy tên người chơi được chọn
				n=(String)jc.getSelectedItem();
                       //dùng để lặp qua qua các phần tử trong danh sách đối thủ
				Iterator<Player> it=pl.iterator();
                       //dùng để lặp qua danh sách được lưu trữ
				Iterator<Player> oit=opl.iterator();
                        //tìm kiếm trong danh sách người chơi của màu đối lập 
				while(it.hasNext())
				{	
					Player p=it.next();
					if(p.name().equals(n))
                                   //Nếu tìm thấy thì gán cho biến tempPlayer
						{tempPlayer=p;
						break;}
				}
				while(oit.hasNext())
				{	
					Player p=oit.next();
                             //Nếu được tìm thấy
					if(p.name().equals(n))
                                   //Xóa người chơi được chọn khỏi danh sách người chơi của màu đối lập.
						{opl.remove(p);
						break;}
				}
				//Kiểm tra nếu tempPlayer bằng null thì thoát khỏi phương thức.
				if(tempPlayer==null)
					return;
                       //Gán người chơi được chọn vào biến White hoặc Black tùy vào màu của người chơi được chọn.
				if(color==0)
					White=tempPlayer;
				else
					Black=tempPlayer;
                       //Gán danh sách người chơi của màu đối lập vào biến bplayer.
				bplayer=opl;
                        //Xóa tất cả các item trong combobox của danh sách người chơi của màu đối lập.
				ojc.removeAllItems();
                        //Thêm các item tương ứng với tên của các người chơi trong danh sách người chơi của màu đối lập vào combobox của danh sách người chơi của màu hiện tại.
				for (Player s:opl)
					ojc.addItem(s.name());
                        //Thêm các thoongtin chi tiết cho người chơi được chọn
				det.add(new JLabel(" "+tempPlayer.name()));
				det.add(new JLabel(" "+tempPlayer.gamesplayed()));
				det.add(new JLabel(" "+tempPlayer.gameswon()));
				//Cập nhật lại giao diện
				PL.revalidate();
				PL.repaint();
				PL.add(det);
				selected=true;
			}
			
		}
		
		
		
		class Handler implements ActionListener{
			private int color;
			Handler(int i)
			{
				color=i;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
                            // lưu trữ tên người chơi.
				String n=(color==0)?wname:bname;
                            // lưu trữ panel hiển thị thông tin người chơi (WhitePlayer hoặc BlackPlayer).
				JPanel j=(color==0)?WhitePlayer:BlackPlayer;
                            //lưu trữ danh sách các người chơi đã lưu trữ trước đó.
				ArrayList<Player> N=Player.fetch_players();
                            // sử dụng để duyệt danh sách các người chơi 
				Iterator<Player> it=N.iterator();
                            // //lưu các hiển thị thông tin chi tiết của người chơi được
				JPanel det=(color==0)?wdetails:bdetails;
                       //Hiển thị hộp thoại nhập tên người chơi 
				n=JOptionPane.showInputDialog(j,"Enter your name");
					//Kiểm tra nếu người dùng đã nhập
					if(n!=null)
					{
					//Kiểm tra tên vùa nhập với danh sách các người chơi
					while(it.hasNext())
					{
                                   //Nếu trùng thì hiện thông báo 
						if(it.next().name().equals(n))
						{JOptionPane.showMessageDialog(j,"Tên người chơi đã tồn tại");
						return;}
					}
                                  //Nếu tên người chơi chưa có trong danh sách
						if(n.length()!=0)
                                   //Tạo người chơi mới
						{Player tem=new Player(n);
                                   //Lưu trữ người chơi đó
						tem.Update_Player();
                                   //Nếu màu sắc của người chơi là màu trắng thì gán cho dối tượng White(Người chơi bên trắng)
						if(color==0)
							White=tem;
                                   //ngược lại
						else
							Black=tem;
						}
     
						else return;
					}
                      //Nếu người dùng ko nhập tên thì kết thúc
				else
					return;
                       //Hiển thị các giá trị rỗng
				det.removeAll();
				det.add(new JLabel(" "+n));
				det.add(new JLabel(" 0"));
				det.add(new JLabel(" 0"));
				j.revalidate();
				j.repaint();
				j.add(det);
				selected=true;
			}
			}	 
}


