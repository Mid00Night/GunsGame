       


package Texture;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteFile {
//    public static void main(String[] args) throws IOException{
//        String file_name = "E:\\3rd year\\gui\\Final section - Compressed\\Final section - Compressed\\src\\a.txt";
//        WriteFile data = new WriteFile( file_name );
//        //data.writeToFile( "This is another line of text" );
//        File f = new File(file_name);
//        Scanner input = new Scanner(f);
//        String a ;
//        cheakScore("mahmoud",300);
//        while(input.hasNext()){
//             a =input.nextLine();
//             System.out.println(a);
//        }
//        
//
//    }
    
    
    
    
    
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
        String file_name = "D:\\El Dok8\\Com.s\\CS304 Graphics\\FinalGmae\\src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = null;
        try {
            input = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        String [] score = new String[10];
        /*get score and set it to array*/
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
        /*check if the new score is high of one of this score*/
        for(int i = 0; i < score.length; i+=2){
                if(Integer.parseInt(score[i+1]) < a){
                return true;
            }
        }
        return false;
    }
    public static String[] getHigh() throws FileNotFoundException, IOException{
       String file_name = "D:\\El Dok8\\Com.s\\CS304 Graphics\\FinalGmae\\src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = new Scanner(f);
        String [] score = new String[10];
        /*get score and set it to array*/
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
        /*check if the new score is high of one of this score*/
       return score;
    }
    public static void cheakScore(String s,int a) throws FileNotFoundException, IOException{
        String file_name = "D:\\El Dok8\\Com.s\\CS304 Graphics\\FinalGmae\\src\\Texture\\scoreF.txt";
        File f = new File(file_name);
        Scanner input = new Scanner(f);
        String [] score = new String[10];
        /*get score and set it to array*/
        for(int i = 0; i < score.length; i++){
                score[i] = input.nextLine();
            }
        /*check if the new score is high of one of this score*/
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
        /*make file empty*/
        FileWriter write = new FileWriter(file_name,false);
        PrintWriter print_line = new PrintWriter(write);
        print_line.print("");
        /*write the new score*/
         write = new FileWriter(file_name,true);
         print_line = new PrintWriter(write);
        for(int i = 0; i < score.length; i++){
           print_line.println(score[i]);
        }
        print_line.close();
    }
}
