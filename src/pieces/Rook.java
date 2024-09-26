/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;


public class Rook extends Piece{
	

	public Rook(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	

        //Tìm kiếm các phương án di chuyển của quân Xe
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{

            //vì quân xe có thể đi đc hàng ngang và hàng dọc không quan trọng số ô nếu gặp địch thì ăn, gặp cùng màu thì chỉ có thể ở sau quân cờ để tiếp viện cho quân cờ đó
            //(tức đứng sau quân cờ đó thôi)
            // nên ko cần ktr xem màu của quân cờ là gì 
            //chỉ cần ktr xem vị trí nó muốn đi bị chăn cùng màu hay khác màu
		
		possiblemoves.clear();
        //Xét trên 1 cột
            // tạo một biến để xét xem những ô nào ở phía trước có thể thêm vào mảng
		int tempx=x-1;
            //Tạo vòng lặp tên 1 cột 
            //tại sao tới trường hợp tempx =0 thì dừng lại
            //vì đã đi hết bàn cờ
		while(tempx>=0)
		{
                  //ô trước quân xe trống thì thêm ô đó vô mảng
			if(state[tempx][y].getpiece()==null)
				possiblemoves.add(state[tempx][y]);
                  //Nếu có quân cờ của cùng màu thì kết thúc vòng lặp tại vị trí đó
			else if(state[tempx][y].getpiece().getcolor()==this.getcolor())
				break;
                  //nếu khác màu thì có thể ăn luôn và kết thúc vòng lặp
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
                  // giảm xuống 1 để tiếp tục kiểm tra ô tiếp theo
			tempx--;
                        
                 //Tổng kết sẽ là cho một biến phụ để kiểm tra
                 //dùng vòng lặp while để kiểm tra từng ô trên một cột
                 //nếu thỏa các trường hợp thì xử lý theo từng trường hợp đó
                 //rồi giảm đó để ktr tiếp 
		}
            //khởi tạo lại biến để xét xem ô nào ở phía sau có thể thêm vào mảng
		tempx=x+1;
            //tại sao lại xét temp < 8 
            //vì bàn cờ có 8 dòng => tempx (những ô phía sau quân xe) được duyệt từ 1 - 7
            
		while(tempx<8)
		{
			if(state[tempx][y].getpiece()==null)
				possiblemoves.add(state[tempx][y]);
			else if(state[tempx][y].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[tempx][y]);
				break;
			}
			tempx++;
		}
                
         //Xét trên một dòng
            //// tạo một biến để xét xem những ô nào ở phía bên trái có thể thêm vào mảng
		int tempy=y-1;
		while(tempy>=0)
		{
			if(state[x][tempy].getpiece()==null)
				possiblemoves.add(state[x][tempy]);
			else if(state[x][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy--;
		}
            //Khởi tạo lại tempy để xét xem những ô nào ở phía bên phải có thể thêm vào mảng
		tempy=y+1;
		while(tempy<8)
		{
			if(state[x][tempy].getpiece()==null)
				possiblemoves.add(state[x][tempy]);
			else if(state[x][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				possiblemoves.add(state[x][tempy]);
				break;
			}
			tempy++;
		}
		return possiblemoves;
	}
}

