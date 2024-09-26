/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;



public class Cell extends JPanel implements Cloneable{
	
	private static final long serialVersionUID = 1L;
        //kiểm tra phương án hợp lệ
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
       //tọa độ
	int x,y; 
        //có được chọn hay chưa
	private boolean isSelected=false;
        //Có bị chiếu hay chưa
	private boolean ischeck=false;
	

	//thiết lập giao diện bàn cờ
	public Cell(int x,int y,Piece p)
	{		
		this.x=x;
		this.y=y;
		
		setLayout(new BorderLayout());
	
	 if((x+y)%2==0)
	  setBackground(new Color(227,193,111 ));
	
	 else
	  setBackground(new Color (184,139,74));
	 
	 if(p!=null)
		 setPiece(p);
	}
	
       //Thiết lập giao diện bàn cờ

	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x=cell.x;
		this.y=cell.y;
		setLayout(new BorderLayout());
		if((x+y)%2==0)
			setBackground(new Color(227,193,111 ));
		else
			setBackground(new Color (184,139,74));
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}
	
      //Thiết lập quân cờ vào bàn cờ
	public void setPiece(Piece p)    
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
        //Xóa quân cờ
	public void removePiece()      
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	
       //Lấy quân cờ
	public Piece getpiece()    
	{
		return this.piece;
	}
	
      //Hiển thị quân cờ được chọn
	public void select()       
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected=true;
	}
	
      //Kiểm tra quân cờ có được chọn hay không
	public boolean isselected()   
	{
		return this.isSelected;
	}
	
       //Hủy bỏ chọn
	public void deselect()     
	{
		this.setBorder(null);
		this.isSelected=false;
	}
	
       //Thiết lập các vị trí di chuyển hợp lệ 
	public void setpossibledestination()    
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
      //Xóa các phương án di chuyển ko hợp lệ  
	public void removepossibledestination()      
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
      //Kiểm tra phương án có hợp lệ hay không
	public boolean ispossibledestination()    
	{
		return this.ispossibledestination;
	}
	
        //Thiết lập giao diện cho quân Vua khi bị chiếu
	public void setcheck()    
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	//Hủy bỏ giao diện chiếu quay về giao diện bình thường
	public void removecheck()   
	{
		this.setBorder(null);
		if((x+y)%2==0)
	  setBackground(new Color(227,193,111 ));
		else
	  setBackground(new Color (184,139,74));
		this.ischeck=false;
	}
	
       //Kiểm tra có bị chiếu hay không 
	public boolean ischeck()    
	{
		return ischeck;
	}
}
