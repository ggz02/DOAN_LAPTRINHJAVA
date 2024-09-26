/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;



public class Knight extends Piece{
	
	
	public Knight(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
        
      //do quân mã có cách di chuyển đặc biết là đi theo chữ L nên khi tổng hợp lại sẽ có 8 nước có thể đi dành cho quân mã 
      
	//Tìm kiếm các phương án di chuyển của quân mã
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
            //tạo mảng posx lưu trữ tọa độ x của 8 nước đi mà quân mã có thể đi
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
            //tạo mảng posy lưu trữ tọa độ y của 8 nước đi mà quân mã có thể đi
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
            //dùng vòng lặp for để xét ở từng tọa độ 
		for(int i=0;i<8;i++)
                    
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
                        //nếu tọa đọ x,y của quân mã mà rỗng hoặc ở tọa độ đó có quân cờ khác thì thêm đó vào mảng
				if((state[posx[i]][posy[i]].getpiece()==null||state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor()))
				{
                             
					possiblemoves.add(state[posx[i]][posy[i]]);
				}
		return possiblemoves;
	}
}