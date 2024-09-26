/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;


public class Pawn extends Piece{
	

	public Pawn(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
//Tìm kiếm các phương án di chuyển của quân Tốt
	@Override
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		
		possiblemoves.clear();
                //nếu là quân trắng thì
		if(getcolor()==0)
		{      //nếu x = 0  tức là quân trắng đã ở cuối bàn cờ bên kia không thể đi được nữa nên phải gọi lại possiblemoves
			if(x==0)
				return possiblemoves;
                   // tại sao lại là x - 1 vì x là số hàng mà muốn tốt trắng đi lên trên thì phải trừ 1 hàng do quân trắng năm ở hàng 7,8 nên phải lụi là tức là đi tiếp đối với trắng
                   // nếu ở trước tốt không có ai thì add vào các nước co thể đi
			if(state[x-1][y].getpiece()==null)
			{      //bước add các nước có thể đi khi add dô rồi thì tức là tốt có thể đi vào đó
				possiblemoves.add(state[x-1][y]);
                       //Nếu quân tốt đang nằm ở hàng thứ 6 tức là ở vị trí xếp cờ ban đầu thì nó có thể đi lên đc 2 bước là nên dòng thứ 4. nếu dòng 4 mà trống thì sẽ lên đc
				if(x==6)
				{
					if(state[4][y].getpiece()==null)
						possiblemoves.add(state[4][y]);
				}
			}
                        // Tại sao x phải lớn hơn 0 vì ko xát mép rồi sao mà còn bên trái của 0 nữa mà ăn
                        //Tức là nếu x nằm ở giữa từ 1,7 thì nếu phía bên trái mà có quân cờ khác và khác nước thì thêm nó vào mảng có thể đi được
			if((y>0)&&(state[x-1][y-1].getpiece()!=null)&&(state[x-1][y-1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x-1][y-1]);
                        // Tại sao x phải < 7 vì 7 là xát mép bên phải rồi sao mà còn bên phải của 7 nữa mà ăn
                        //Tức là nếu x nằm ở giữa từ 6-0 thì nếu phía bên phải mà có quân cờ khác và khác nước thì thêm nó vào mảng có thể đi được
			if((y<7)&&(state[x-1][y+1].getpiece()!=null)&&(state[x-1][y+1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x-1][y+1]);
		}
            //ngược lại
		else
		{
			if(x==8)
				return possiblemoves;
			if(state[x+1][y].getpiece()==null)
			{
				possiblemoves.add(state[x+1][y]);
				if(x==1)
				{
					if(state[3][y].getpiece()==null)
						possiblemoves.add(state[3][y]);
				}
			}
			if((y>0)&&(state[x+1][y-1].getpiece()!=null)&&(state[x+1][y-1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x+1][y-1]);
			if((y<7)&&(state[x+1][y+1].getpiece()!=null)&&(state[x+1][y+1].getpiece().getcolor()!=this.getcolor()))
				possiblemoves.add(state[x+1][y+1]);
		}
		return possiblemoves;
	}
}

