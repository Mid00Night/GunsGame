package Texture;


import com.sun.opengl.util.j2d.TextRenderer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.media.opengl.*;
import sun.audio.AudioStream;
import   java.io.InputStream;
import java.util.BitSet;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;

public class AnimGLEventListener6 extends AnimListener {
    AudioStream audios;
    Anim anim;
    InputStream music;
    public static boolean ispause = false;
    GLAutoDrawable gldddd;
    
    TextRenderer renderer=new TextRenderer(new Font("SanasSerif",Font.BOLD,20));

   
    boolean visible[] = {false,false,false,false,false};
    int xlead [] ={0,0,0,0,0};
    int ylead [] ={0,0,0,0,0};
    boolean nopause=true;
  
    boolean visible2[] = {false,false,false,false,false};
    int xlead2 [] ={0,0,0,0,0};
    int ylead2 [] ={100,100,100,100,100};
    
    int vis = 0,vis2 = 0;
   
    long start,start2;
    
    boolean changeVis = true,changeVis2=true;
 
    int animationIndex = 0,animationIndex2 = 0;
    int maxWidth = 100;
    int maxHeight = 100;
   
    int x = maxWidth/2, y = 0,x2=maxWidth/2;
    
   
    String textureNames[] = {"Man1.png","Man2.png","Man3.png","Man4.png","BackG.png"
            ,"pause.png","lead.png","5.png","4.png","3.png","2.png","1.png","0.png","player1.png","player2.png"};
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
  
    AudioInputStream audioInputStream; 
    Clip clip;
    
    AnimGLEventListener6(Anim aThis) {
        anim = aThis;
    }
    public void init(GLAutoDrawable gld) {

        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
        
        gl.glEnable(GL.GL_TEXTURE_2D);  
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);	
        gl.glGenTextures(textureNames.length, textures, 0);
        
        for(int i = 0; i < textureNames.length; i++){
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);


                new GLU().gluBuild2DMipmaps(
                    GL.GL_TEXTURE_2D,
                    GL.GL_RGBA, 
                    texture[i].getWidth(), texture[i].getHeight(),
                    GL.GL_RGBA, 
                    GL.GL_UNSIGNED_BYTE,
                    texture[i].getPixels() 
                    );
            } catch( IOException e ) {
              System.out.println(e);
              e.printStackTrace();
            }
        }
           
        try {
            music = new FileInputStream(new File("[ONTIVA.COM] Cyberpunk 2077 - Main Menu Theme-HQ.wav"));
            audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\[ONTIVA.COM] Cyberpunk 2077 - Main Menu Theme-HQ.wav").getAbsoluteFile());
   
            clip = AudioSystem.getClip(); 
          
   
            clip.open(audioInputStream); 
          
            clip.loop(Clip.LOOP_CONTINUOUSLY); 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        } 
       
        gl.glLoadIdentity(); 
        gl.glOrtho(-maxWidth/2,  maxWidth/2,-maxHeight/2, maxHeight/2,-1,1);

    }
    
    public void display(GLAutoDrawable gld) {
        gldddd = gld;
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); 
        
        
        
        DrawBackground(gl);
     
        
        
        handleKeyPress();
        
        animationIndex = animationIndex % 4;
        animationIndex2 = animationIndex2 % 4;
            if(vis == 5){
                changeVis = false;
                long end = System.currentTimeMillis();
                
                if(end - start >= 2000){
                    vis++;
                    
                    changeVis = true;
                }
                    
            }
            if(vis2 == 5){
                changeVis2 = false;
                long end2 = System.currentTimeMillis();
                
                if(end2 - start2 >= 2000){
                    vis2++;
                    
                    changeVis2 = true;
                    
                }
                    
            }
            vis=vis%6;
            vis2=vis2%6;        
            
            
            DrawBulletsNumber(gl);
            DrawBulletsNumber2(gl);
           
            
            moveBullet(gl, visible, xlead, ylead,1);
             moveBullet(gl, visible2, xlead2, ylead2,2);

              DrawPlayer2(gl, x2, 90, animationIndex2, 1);
           DrawPlayer1(gl, x, y, animationIndex, 1);
            if(ispause)
            {
                DrawPause(gl);
            }
            for(int qq = 0; qq < 5;qq++){
                
                if((xlead[qq]>x2-5 && xlead[qq]<x2+5)&& ylead[qq]<105 &&ylead[qq]>95){
                    DrawPlayer1Win(gl);
                    nopause=false;
                }
                else if((xlead2[qq]>x-5 && xlead2[qq]<x+5)&& ylead2[qq]<5 &&ylead2[qq]>-5){
                    DrawPlayer1Win(gl);
                    nopause=false;
                }
                if(ylead[qq]>=100){
                    ylead[qq]=0;
                }
                if(ylead2[qq]<=0){
                    ylead2[qq]=105;
                }
            }
            
}
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    
    public void DrawPlayer1(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	
        
        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.1*scale, 0.1*scale, 1);
            

            gl.glBegin(GL.GL_QUADS);
          
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawPlayer2(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	
        
        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.1*scale, 0.1*scale, 1);
            
                gl.glRotatef(180f, 0, 0, 1);

            gl.glBegin(GL.GL_QUADS);
         
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
     public void DrawBulletsNumber(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[vis+7]);	

        gl.glPushMatrix();
        gl.glTranslated(-.90, .90, 0);
        gl.glScaled(0.1, 0.1, 1);

            gl.glBegin(GL.GL_QUADS);
        
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
     public void DrawBulletsNumber2(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[vis2+7]);	

        gl.glPushMatrix();
        gl.glTranslated(.90, .90, 0);
        gl.glScaled(0.1, 0.1, 1);

            gl.glBegin(GL.GL_QUADS);
           
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawBackground(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[4]);	

        gl.glPushMatrix();
            gl.glBegin(GL.GL_QUADS);
        
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
    
    public void DrawPause(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[5]);	

        gl.glPushMatrix();
            gl.glBegin(GL.GL_QUADS);
       
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawPlayer1Win(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[13]);	

        gl.glPushMatrix();
        gl.glScaled(0.6, 0.6, 1);
            gl.glBegin(GL.GL_QUADS);
        
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        Anim.animator.stop();
        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawPlayer2Win(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[14]);	

        gl.glPushMatrix();
        gl.glScaled(0.6, 0.6, 1);
            gl.glBegin(GL.GL_QUADS);
          
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
   
   public void DrawBullet(GL gl,int x, int y){
       float scalex=118f,scaley=442f;
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[6]);	

        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.0001*scaley, 0.0001*scaley, 1);
            

            gl.glBegin(GL.GL_QUADS);

                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
   public void DrawBullet2(GL gl,int x, int y){
       float scalex=118f,scaley=442f;
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[6]);	

        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.0001*scaley, 0.0001*scaley, 1);
            gl.glRotatef(180f, 0, 0, 1);

            gl.glBegin(GL.GL_QUADS);
       
                gl.glTexCoord2f(0.0f, 0.0f);
                gl.glVertex3f(-1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 0.0f);
                gl.glVertex3f(1.0f, -1.0f, -1.0f);
                gl.glTexCoord2f(1.0f, 1.0f);
                gl.glVertex3f(1.0f, 1.0f, -1.0f);
                gl.glTexCoord2f(0.0f, 1.0f);
                gl.glVertex3f(-1.0f, 1.0f, -1.0f);
            gl.glEnd();
        gl.glPopMatrix();
        
        gl.glDisable(GL.GL_BLEND);
    }
  
   public void moveBullet(GL gl,boolean isvisiable[] ,int xLead[],int yLead[],int no){
       if(no==1){
       for(int i=0;i<isvisiable.length ;i++ ){
            if(isvisiable[i]==true){
                
                DrawBullet(gl,xLead[i],yLead[i]=yLead[i]+5);
                if( yLead[i]>=100  || yLead[i]<=0 ){
                isvisiable[i]= false;
                
                    
                }
            }
       }
       }
       if(no==2){
       for(int i=0;i<isvisiable.length ;i++ ){
            if(isvisiable[i]==true){
                
                DrawBullet2(gl,xLead[i],yLead[i]=yLead[i]-5);
                if( yLead[i]>=100  || yLead[i]<=0 ){
                isvisiable[i]= false;
                
                    
                }
            }
       }
       }
   }
  
    public void setBullet(boolean visible[],int x[],int y[]){
        for(int i=0;i<visible.length;i++){
            if(visible[i]==false){
                visible[i] = true;
                x[i]=this.x;
                y[i]=0;
                
                vis++;
                
                break;
            }
        }
    }
    public void setBullet2(boolean visible[],int x[],int y[]){
        for(int i=0;i<visible.length;i++){
            if(visible[i]==false){
                visible[i] = true;
                x[i]=this.x2;
                y[i]=95;
                
                vis2++;
                
                break;
            }
        }
    }
   
    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 0) {
                x--;
                
            }
            animationIndex++;
        }else
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth-10) {
                x++;
                

            }
            animationIndex++;
        }
     
        if (isKeyPressed(KeyEvent.VK_A)) {
            if (x2 > 0) {
                x2--;
                
            }
            animationIndex2++;
        }else
        if (isKeyPressed(KeyEvent.VK_D)) {
            if (x2 < maxWidth-10) {
                x2++;
                

            }
            animationIndex2++;
        }
        if (isKeyPressed(KeyEvent.VK_SPACE)) {
            
            if(changeVis){
            this.setBullet(visible, this.xlead, this.ylead);
            start = System.currentTimeMillis();
            }
        }
        if (isKeyPressed(KeyEvent.VK_F)) {
            
            if(changeVis2){
            this.setBullet2(visible2, this.xlead2, this.ylead2);
            start2 = System.currentTimeMillis();
            
            }
        }
        
    }

    public BitSet keyBits = new BitSet(256);
 
    @Override 
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
        
       if (isKeyPressed(KeyEvent.VK_P)) {
       if(nopause){  
           if(!ispause){
            ispause = !ispause;
            gldddd.repaint();
            Anim.animator.stop();
            
           }
           
           else{
            ispause = !ispause;
            gldddd.repaint();
            Anim.animator.start();
            

           }
       }
        
        }
       
        if(!nopause){
            if (isKeyPressed(KeyEvent.VK_ESCAPE)){
                  Game.gameplay();
                  anim.dispose();
               
              }
        }
        
        if ((isKeyPressed(KeyEvent.VK_F4))&& isKeyPressed(KeyEvent.VK_ALT)){
                  Game.gameplay();
                  anim.dispose();
                  System.exit(0);
                
        }
        
            if (isKeyPressed(KeyEvent.VK_ESCAPE)){
                  Game.gameplay();
                  anim.dispose();
               
              }
        
    } 
 
    @Override 
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    } 
 
    @Override 
    public void keyTyped(final KeyEvent event) {
      
    } 
 
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}
