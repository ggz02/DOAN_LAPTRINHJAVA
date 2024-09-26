/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pieces;

import java.util.ArrayList;

import chess.Cell;


public class King extends Piece{
	//Tọa độ của quân Vua
	private int x,y; 
	
	public King(String i,String p,int c,int x,int y)
	{
		setx(x);
		sety(y);
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//thiêt lập tọa độ x cho quân Vua
	public void setx(int x)
	{
		this.x=x;
	}
      //Thiết lập tọa độ y cho quân Vua
	public void sety(int y)
	{
		this.y=y;
	}
      //lấy tọa độ x của quân Vua
	public int getx()
	{
		return x;
	}
      //Lấy tọa độ y của quân Vua
	public int gety()
	{
		return y;
	}
	//Tìm kiếm các phương án di chuyển của quân Vua
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
            //Quân vua có thể di chuyển tất cả các hướng đi nhưng chỉ được đi 1 ô
            //Vì vậy khi tổng hợp lại thì sẽ có 8 trường hợp có thể đi
		possiblemoves.clear();
            //tạo mảng lưu tọa độ x mà quân vua có thể di chuyển được
		int posx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
            //tạo mảng lưu tọa độ y mà quân vua có thể di chuyển được
		int posy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
                //kiểm tra từng cặp tọa độ nếu trường hợp nào thỏa thì thêm vào possiblemoves
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getpiece()==null||state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor()))
					possiblemoves.add(state[posx[i]][posy[i]]);
		return possiblemoves;
	}
	
	
	

    //Kiểm tra quân Vua có bị quân cờ nào chiếu không
        public boolean isindanger(Cell state[][])
    {
		
       //Kiểm tra quân vua có đang bị tấn công bởi các quân xe và hậu hay không
       //kiểm tra từ hàng thứ 0 - hàng thứ 7 trên cùng một cột
    	for(int i=x+1;i<8;i++)
    	{
            //nếu rỗng thì tiếp tục duyệt sang ô kế tiếp
    		if(state[i][y].getpiece()==null)
    			continue;
            //Nếu trùng màu với quân vua đang ktr thì sẽ ko có quân xe hay hậu nào đang tấn công vua
    		else if(state[i][y].getpiece().getcolor()==this.getcolor())
    			break;
           //Nếu khác màu
    		else
    		{
                 //Nếu đúng, vị trí (x, y) đang bị chiếu bởi quân Rook hoặc Queen, vòng lặp sẽ kết thúc và phương thức trả về giá trị true.
    			if ((state[i][y].getpiece() instanceof Rook) || (state[i][y].getpiece() instanceof Queen))
    				return true;
                 //nếu không phải vòng lặp cũng sẽ dừng lại và kết thúc
    			else
    				break;
    		}
    	}
     //Kiểm tra quân vua có đang bị tấn công bởi các quân xe và hậu hay không
        //kiểm tra từ hàng thứ 7 - hàng thứ 0 trên cùng một cột
    	for(int i=x-1;i>=0;i--)
    	{
    		if(state[i][y].getpiece()==null)
    			continue;
    		else if(state[i][y].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[i][y].getpiece() instanceof Rook) || (state[i][y].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
        //Kiểm tra quân vua có đang bị tấn công bởi các quân xe và hậu hay không
        //kiểm tra từ cột thứ 0 - 7 trên cùng 1 hàng 
    	for(int i=y+1;i<8;i++)
    	{
    		if(state[x][i].getpiece()==null)
    			continue;
    		else if(state[x][i].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[x][i].getpiece() instanceof Rook) || (state[x][i].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
        //Kiểm tra quân vua có đang bị tấn công bởi các quân xe và hậu hay không
        //kiểm tra từ cột thứ 7 - 0 trên cùng 1 hàng 
    	for(int i=y-1;i>=0;i--)
    	{
    		if(state[x][i].getpiece()==null)
    			continue;
    		else if(state[x][i].getpiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[x][i].getpiece() instanceof Rook) || (state[x][i].getpiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	
      //kiểm tra quân vua có đang bị chiếu bởi các đường chéo hay không
      //kiểm tra trên đường chéo phải dưới quân Tượng hoặc Hậu
    	int tempx=x+1,tempy=y-1;
        //vị trí đó thuộc bàn cờ
		while(tempx<8&&tempy>=0)
		{
                  //nếu tại vị trí đó rỗng thì tiếp tục kiểm tra các ô khác thuộc đường chéo phải
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx++;
				tempy--;
			}
                  //Nếu tại vị trí đó là ô các quân cờ cùng màu với quân vua thì dừng lại
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
                 //Nếu tại vị trí đó là quân cờ khác màu
			else
			{
                        //Nếu tại vị trí đó là quân tượng hoặc quân hậu thì trả về true và kết thúc 
				if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
                       //Nếu không phải thì cũng dừng lại
    			else
    				break;
			}
		}
           //kiểm tra trên đường chéo phải trên quân Tượng hoắc Hậu
		tempx=x-1;tempy=y+1;
		while(tempx>=0&&tempy<8)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx--;
				tempy++;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
             //kiểm tra trên đường chéo trái trên quân Tượng hoặc Hậu
		tempx=x-1;tempy=y-1;
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx--;
				tempy--;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
             //kiểm tra trên đường chéo trái dưới quân Tượng hoạc Hậu
		tempx=x+1;tempy=y+1;
		while(tempx<8&&tempy<8)
		{
			if(state[tempx][tempy].getpiece()==null)
			{
				tempx++;
				tempy++;
			}
			else if(state[tempx][tempy].getpiece().getcolor()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		
            //Kiểm tra xem có thuộc một trong những trường hợp đi của quân mã hay không
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if(state[posx[i]][posy[i]].getpiece()!=null && state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor() && (state[posx[i]][posy[i]].getpiece() instanceof Knight))
				{
					return true;
				}
		
		

            //Kiểm tra xem có thuộc một trong những trường hợp có thể bị di chuyển của vua hay không
		int pox[]={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
		int poy[]={y-1,y+1,y,y+1,y-1,y+1,y-1,y};
		{
			for(int i=0;i<8;i++)
				if((pox[i]>=0&&pox[i]<8&&poy[i]>=0&&poy[i]<8))
					if(state[pox[i]][poy[i]].getpiece()!=null && state[pox[i]][poy[i]].getpiece().getcolor()!=this.getcolor() && (state[pox[i]][poy[i]].getpiece() instanceof King))
					{
						return true;
					}
		}
                
            //Kiểm tra xem có thuộc một trong các đường chéo của quân Tốt không
           //Nếu quân Vua màu trắng
		if(getcolor()==0)
		{
                   //kiểm tra đường chéo trái trên quân Vua
			if(x>0&&y>0&&state[x-1][y-1].getpiece()!=null&&state[x-1][y-1].getpiece().getcolor()==1&&(state[x-1][y-1].getpiece() instanceof Pawn))
				return true;
                  //Đường chéo phải trên quân Vua
			if(x>0&&y<7&&state[x-1][y+1].getpiece()!=null&&state[x-1][y+1].getpiece().getcolor()==1&&(state[x-1][y+1].getpiece() instanceof Pawn))
				return true;
		}
                //Nếu là Vua đen
		else
		{   //Kiểm tra đường chéo phải dưới quân Vua
			if(x<7&&y>0&&state[x+1][y-1].getpiece()!=null&&state[x+1][y-1].getpiece().getcolor()==0&&(state[x+1][y-1].getpiece() instanceof Pawn))
				return true;
                   //Kiểm tra đường chéo trái dưới quân Vua
			if(x<7&&y<7&&state[x+1][y+1].getpiece()!=null&&state[x+1][y+1].getpiece().getcolor()==0&&(state[x+1][y+1].getpiece() instanceof Pawn))
				return true;
		}
    	return false;
    }
}
