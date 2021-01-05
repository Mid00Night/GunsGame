       


package Texture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {

    private String path ;
    private boolean append_to_file = false;

    public  WriteFile(String path) throws FileNotFoundException {
        
        this.path = path;
    }
    public WriteFile( String file_path , boolean append_value ) throws FileNotFoundException {
        

        path = file_path;
        append_to_file = append_value;

    }
    public void writeToFile( String textLine ) throws IOException {
        FileWriter write = new FileWriter(path,append_to_file);
        PrintWriter print_line = new PrintWriter(write);
        print_line.printf("%s"+"%n", textLine);
        print_line.close();
    }
    
    
    public static boolean isHigh(int a) {
        String file_name = "src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = null;
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        String [] score = new String[10];
       
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
       
        for(int i = 0; i < score.length; i+=2){
                if(Integer.parseInt(score[i+1]) < a){
                return true;
            }
        }
        return false;
    }
    public static String[] getHigh() throws FileNotFoundException, IOException{
       String file_name = "src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = new Scanner(f);
        String [] score = new String[10];
      
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
        
       return score;
    }
    public static void cheakScore(String s,int a) throws FileNotFoundException, IOException{
        String file_name = "src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = new Scanner(f);
        String [] score = new String[10];
       
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
       
        for(int i = 0; i < score.length; i+=2){
                if(Integer.parseInt(score[i+1]) < a){
                    System.out.println(s+" this"+(s==""));
                    if(s=="" || s=="null"||s==null||s==" ")
                        score[i]="player";
                    else{}
                        score[i] = s;
                score[i+1] = a+"";
                break;
            }
        }
        
        FileWriter write = new FileWriter(file_name,false);
        PrintWriter print_line = new PrintWriter(write);
        print_line.print("");
       
         write = new FileWriter(file_name,true);
         print_line = new PrintWriter(write);
        for(int i = 0; i < score.length; i++){
           print_line.println(score[i]);
        }
        print_line.close();
    }
}
