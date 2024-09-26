/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;



public class Bishop extends Piece{
	

	public Bishop(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	

	//Tìm kiếm các phương án di chuyển của quân Tượng
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
            //quy luật của quân tượng là chỉ đi được trên đường chéo(có thể là đường chéo trái và đường chéo phải tùy ý muốn của người chơi và vị trí của quân cờ)  không quan trọng số ô nếu gặp địch thì ăn, gặp cùng màu 
            //thì chỉ có thể ở sau quân cờ để tiếp viện cho quân cờ đó
            //(tức đứng sau quân cờ đó thôi)
            // nên ko cần ktr xem màu của quân cờ là gì 
            //chỉ cần ktr xem vị trí nó muốn đi là thuộc đường chéo nào và vị trí đó đã có quân cờ đồng minh hay quân cờ địch
       
            
		possiblemoves.clear();
		int tempx=x+1,tempy=y-1;

                //xét các vị trí quân tượng có thể đi theo đường chéo phải dưới quân Tượng
		while(tempx<8&&tempy>=0)
		{
                  // nếu vị trí ô đó rỗng thì thêm vào possiblemoves
			if(state[tempx][tempy].getpiece()==null)
			{
				possiblemoves.add(state[tempx][tempy]);
			}
                        //nếu vị trí ô đó là quân cờ cùng màu thì kết thúc
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{     //nếu khác màu thì có thể ăn quân cờ đó và kết thúc vòng lặp tại vị trí đó
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
                  //giảm biến xét để tiếp tục kiểm tra
			tempx++;
			tempy--;
		}
		tempx=x-1;tempy=y+1;         
            //Xét các vị trí quân tượng có thề đi theo đường chéo phải trên quân Tượng
		while(tempx>=0&&tempy<8)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}
		tempx=x-1;tempy=y-1;
                //Xét các vị trí quân tượng có thề đi theo đường chéo trái trên quân Tượng
                // tại sao lại xét trường hợp tempx >=0 và tempy >=0
                //vì ở vị tró đó là ở góc đầu góc trái của bàn cờ rồi không thể đi về phía đường chéo bên trái nữa
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}
                //Xét các vị trí quân quân Tượng có thể đi theo đường chéo trái dưới quân Tượng
		tempx=x+1;tempy=y+1;
                 // tại sao lại xét trường hợp tempx < 8 và tempy < 8
                //vì ở vị tró đó là ở góc cuối bên trái của bàn cờ rồi không thể đi về phía đường chéo bên phải nữa
            while(tempx<8&&tempy<8)
		{
			if(state[tempx][tempy].getpiece()==null)
				possiblemoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}
