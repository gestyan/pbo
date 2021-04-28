
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class DocumentHandling {
    public void updateFile(){
        try{
            FileOutputStream fos;
            fos = new FileOutputStream("./files/objek.txt");
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            Mahasiswa mhs = new Mahasiswa();
            mhs.setName("Gestyan Ramadhan");
            oos.writeObject(mhs);
            oos.close();
            fos.close(); 
        }catch(FileNotFoundException ex){
            
        }catch(IOException ex){
            
        }
    }
}
