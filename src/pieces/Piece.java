/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;




public abstract class Piece implements Cloneable{



	private int color;
	private String id=null;
	private String path;
     //Lưu trữ danh sách các phương án có thể di chuyển
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  
     //Tìm kiếm các phương án di chuyển của quân cờ
	public abstract ArrayList<Cell> move(Cell pos[][],int x,int y);  
	
      //thiết lập id cho quân cờ
	public void setId(String id)
	{
		this.id=id;
	}
	
	//Thiết lập địa chỉ hình
	public void setPath(String path)
	{
		this.path=path;
	}
	
	//thiết lập màu cho quân cờ
	public void setColor(int c)
	{
		this.color=c;
	}
	
	//Lấy địa chỉ hình
	public String getPath()
	{
		return path;
	}
	
	//Lấy id của quân cờ
	public String getId()
	{
		return id;
	}
	
	//Lấy màu của quân cờ
	public int getcolor()
	{
		return this.color;
	}
	
	//Lấy bản sao của một quân cờ
	public Piece getcopy() throws CloneNotSupportedException
	{
		return (Piece) this.clone();
	}
}
