/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

// Cung cấp các hàm để theo dõi các người chơi
// Object của class được update và ghi lại vào File ChessData sau mỗi lượt chơi
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;



//Khi lưu đối tượng (Object) xuống file, phải implements Serializable.
public class Player implements Serializable {
    
//serialVersionUID: thể hiện phiên bản của đối tượng được lưu vào file. 
//Khi đọc dữ liệu từ file, ép kiểu về đối tượng hay lưu đối tượng vào file thì 
//ố serialVersionUID giữa phiên bản class Player và phiên bản lưu trong file phải trùng nhau.
    private static final long serialVersionUID = 1L;
    
    private String name;
    private Integer gamesplayed;
    private Integer gameswon;
	
    //Constructor
    public Player(String name) {
        this.name = name.trim();
        gamesplayed = new Integer(0);
        gameswon = new Integer(0);
    }
    
    //Getter tên người chơi
    public String name() {
	return name;
    }
	
    //Getter số lần người chơi đã chơi
    public Integer gamesplayed() {
	return gamesplayed;
    }
	
    //Getter số lần chơi thắng
    public Integer gameswon() {
	return gameswon;
    }
	
    //Update số lần chơi bằng cách tăng số lần người chơi đã chơi
    public void updateGamesPlayed() {
        gamesplayed++;
    }
	
    //Update số lần thắng bằng cách tăng số lần chơi thắng
    public void updateGamesWon() {
            gameswon++;
    }
    
    //Hàm được sử dụng để lấy danh sách các người chơi
    public static ArrayList<Player> fetch_players() {
        Player tempplayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {//tạo File tên infile        //getProperty("user.dir"): lấy đường dẫn file của người dùng
            File infile = new File(System.getProperty("user.dir") + File.separator + "chessdataplayer.dat");
            //đọc file
            input = new ObjectInputStream(new FileInputStream(infile));
            try {
                while (true) {
                    //thêm player vào file
                    tempplayer = (Player) input.readObject();
                    players.add(tempplayer);
                }
            } catch (EOFException e) {
                input.close();
            }
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            try {
                input.close();
            } catch (IOException e1) {}
            JOptionPane.showMessageDialog(null, "Không thể đọc được File!!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File Game Player bị hỏng !! Click OK để tạo File mới");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return players;
    }
    
    //Hàm update Player
    public void Update_Player() {
        
        
        
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player temp_player;
        File inputfile = null;
        File outputfile = null;
        
        
        
//        Tệp đầu vào chessdataplayer.dat: đọc để lấy dữ liệu
//        Tệp đầu ra tempfile.dat: ghi vào dữ liệu đã được xử lý
//        Sử dụng hai tệp khác nhau để bảo toàn dữ liệu gốc trong 
//        chessdataplayer.dat trong khi dữ liệu được xử lý 
//        được ghi vào tempfile.dat
        try {
            //tạo file inputfile để đọc file
            inputfile = new File(System.getProperty("user.dir") + File.separator + "chessdataplayer.dat");
            //tạo file outputfile để ghi file
            outputfile = new File(System.getProperty("user.dir") + File.separator + "tempfile.dat");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "Quyền đọc-ghi bị từ chối !! Chương trình không thể bắt đầu");
            System.exit(0);
        }
        boolean playerdonotexist;
        try {
            if (outputfile.exists() == false) { //nếu file để ghi vào không tồn tại
                outputfile.createNewFile(); //thì tạo file để ghi vào
            }
//            Nếu tệp đầu vào chessdataplayer.dat không tồn tại, chương trình sẽ tạo tệp đầu ra 
//            mới và ghi một đối tượng vào tệp đó bằng cách sử dụng ObjectOutputStream. 
//            Nếu tệp đã tồn tại, chương trình sẽ đọc đối tượng từ tệp đầu vào bằng 
//            ObjectInputStream và ghi nó vào tệp đầu ra bằng ObjectOutputStream.
            if (inputfile.exists() == false) {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile, true));
                output.writeObject(this);
            } else {
                input = new ObjectInputStream(new FileInputStream(inputfile));
                output = new ObjectOutputStream(new FileOutputStream(outputfile));
                playerdonotexist = true;
                try {
                    while (true) { //ép các Object trong file input thành list Player
                        temp_player = (Player) input.readObject();
                        //nếu tên Player bất kỳ trong list temp_player = tên các player ban đầu thì update
                        if (temp_player.name().equals(name())) {
                            output.writeObject(this);
                            playerdonotexist = false;
                        } else {
                            output.writeObject(temp_player);
                        }
                    }
                } catch (EOFException e) {
                    input.close();
                }
                if (playerdonotexist) {
                    output.writeObject(this);
                }
            }
            inputfile.delete(); //xóa file inputfile
            output.close();
            //tạo file mới để lưu dữ liệu Player vừa mới update
            File newf = new File(System.getProperty("user.dir") + File.separator + "chessdataplayer.dat");
            //đổi tên outputfile 
            if (outputfile.renameTo(newf) == false) {
                System.out.println("File Renameing Unsuccessful");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception e) {

        }
    }
}

