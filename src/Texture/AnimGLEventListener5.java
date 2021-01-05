package Texture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

//import WriteFile;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;

public class AnimGLEventListener5 extends AnimListener {
    AudioStream audios;
    InputStream music;
    public static boolean ispause = false;
    GLAutoDrawable gldddd;
    Anim anim;
    TextRenderer renderer=new TextRenderer(new Font("SanasSerif",Font.BOLD,20));

    // mt8erat al lead al player 1
    boolean visible[] = {false,false,false,false,false};
    int xlead [] ={0,0,0,0,0};
    int ylead [] ={0,0,0,0,0};
    int heals=0;
    boolean gameOver;
    //zombie variable
    int xZombie[]           = new int[2];
    int yZombie[]           = new int[2];
    int zombieGenerator     = 2;
    boolean zombieVisible[] = new boolean[2];
    int animateZombie []    = new int[2];
    
    int vis = 0;
    //al start bta3 w2t ma la rosas be5ls
    long start;
    
    boolean changeVis = true,changeVis2=true;
    //animation player 1 and 2
    int animationIndex = 00;
    int maxWidth = 100;
    int maxHeight = 100;
    // x and y ll player 1 and 2
    int x = maxWidth/2, y = 0;
    //
    int score=0;
    //time
    String time =java.time.LocalTime.now()+"";
    
    boolean isEasy = true;
       
    // Download enemy textures from https://craftpix.net/freebies/free-monster-2d-game-items/
    String textureNames[] = {   "Man1.png","Man2.png","Man3.png","Man4.png","Back.png","pause.png","lead.png",
                                "5.png","4.png","3.png","2.png","1.png","0.png"
                                ,"z1.png","z2.png","z3.png","z4.png","backG.png","gameOver.png"
                                ,"h1.png","h2.png","h3.png","h4.png","h5.png","h6.png"
    };
    TextureReader.Texture texture[] = new TextureReader.Texture[textureNames.length];
    int textures[] = new int[textureNames.length];
    //music
    AudioInputStream audioInputStream; 
    Clip clip;
    /*
    ** set the way
    */

    AnimGLEventListener5(Anim aThis) {
        anim = aThis;
    }
    
    /*
     5 means gun in array pos
     x and y coordinate for gun 
     */
    public void init(GLAutoDrawable gld) {
        initial();
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);    //This Will Clear The Background Color To Black
        
        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);	
        gl.glGenTextures(textureNames.length, textures, 0);
        
        for(int i = 0; i < textureNames.length; i++){
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i] , true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

//                mipmapsFromPNG(gl, new GLU(), texture[i]);
                new GLU().gluBuild2DMipmaps(
                    GL.GL_TEXTURE_2D,
                    GL.GL_RGBA, // Internal Texel Format,
                    texture[i].getWidth(), texture[i].getHeight(),
                    GL.GL_RGBA, // External format from image,
                    GL.GL_UNSIGNED_BYTE,
                    texture[i].getPixels() // Imagedata
                    );
            } catch( IOException e ) {
              System.out.println(e);
              e.printStackTrace();
            }
        }
                 // music              
        try {
           music = new FileInputStream(new File("[ONTIVA.COM] Dying Light OST - Main Menu Theme 1-HQ.wav"));
            audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\[ONTIVA.COM] Dying Light OST - Main Menu Theme 1-HQ.wav").getAbsoluteFile());
         //    create clip reference 
            clip = AudioSystem.getClip(); 
          
           //  open audioInputStream to the clip 
            clip.open(audioInputStream); 
          
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
            audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }   
        /*tgweda*/
        gl.glLoadIdentity(); 
        gl.glOrtho(-maxWidth/2,  maxWidth/2,-maxHeight/2, maxHeight/2,-1,1);

    }
    
    public void initial(){
        for(int i = 0;i < xlead.length;i++){
            xlead[i]=-10;
            ylead[i]=-10;
        }
        for(int i = 0;i < xZombie.length;i++){
            xZombie[i]=0;
            yZombie[i]=0;
        }
    }
    
    public void display(GLAutoDrawable gld) {
        gldddd = gld;
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        gl.glLoadIdentity(); 
        
        DrawBackground(gl);
        /*draw al score*/
        renderer.beginRendering(gld.getWidth(),gld.getHeight());
        
        renderer.draw("score : "+score, 570, 640);
        renderer.endRendering();
        
        handleKeyPress();
        
        animationIndex = animationIndex % 4;
        
            if(vis == 5){
                changeVis = false;
                long end = System.currentTimeMillis();
                
                if(end - start >= 1000){
                    vis++;
                    
                    changeVis = true;
                }
                    
            }
            
            vis=vis%6;
                    
            
            //Draw vis byrsm 3dd al rosas aly m3ak bast5dam al mt8er vis
            DrawVis(gl);
        
            
            
            this.moveLead(gl, visible, xlead, ylead,1);
            DrawSprite(gl, x, y, animationIndex, 1);
            
            
            /*
            ** zombie section 
            */
//            checkHeal();
            generateZombie(gl, zombieVisible,xZombie,yZombie);
            zombieGenerator++;
            zombieAnimate(animateZombie);
            moveZombie(gl, zombieVisible, xZombie, yZombie, animateZombie);
            coleshon();
            
            
            try {
            DrawTime();
            //move lead bersm al rosas w y7rkha
        } catch (ParseException ex) {
            Logger.getLogger(AnimGLEventListener5.class.getName()).log(Level.SEVERE, null, ex);
        }
            DrawHeals(gl);
            /*
            ** pause
            */
            if(isEasy)
                if(score==5){
                 //  System.out.println("LEVEL UP"); 
                makeHard1();
                 
                        }                    
                if(score==15){
                    //System.out.println("LEVEL UP");    
                makeHard1();
                        }                    
                if(score==25){
                    //System.out.println("LEVEL UP");    
                makeHard3();
                        }                    
            if(ispause)
            {
                DrawPause(gl);
            }
            if(gameOver){
                DrawGameOver(gl);
                Anim.animator.stop();
            
                if(WriteFile.isHigh(this.score)){
                    String inStr = JOptionPane.showInputDialog(null, "you score a new high score inter your name","Input Dialog", JOptionPane.PLAIN_MESSAGE);
                    try {
                    WriteFile.cheakScore(inStr, score);
                    System.out.println(inStr);
                }   catch (IOException ex) {
                        Logger.getLogger(AnimGLEventListener5.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                
            
                //DrawGameOver(gl);
            }
            
}
    }
    public void makeHard1(){
        int i=5;
        
        boolean vis [] = new boolean[i];
        int x [] = new int[i];
        int y [] = new int[i];
        int a [] = new int[i];
        System.arraycopy(this.xZombie, 0, x, 0, this.xZombie.length);
        this.xZombie = x;
        System.arraycopy(this.yZombie, 0, y, 0, this.yZombie.length);
        this.yZombie = y;
        System.arraycopy(this.animateZombie, 0, a, 0, this.animateZombie.length);
        this.animateZombie = a;
        System.arraycopy(this.zombieVisible, 0, vis, 0, this.zombieVisible.length);
        this.zombieVisible = vis;
         
//        JOptionPane.showMessageDialog(null,"Level one complete");
//        new Game().play();
        
        
        
    }
    public void makeHard2(){
        int i=10;
        
        boolean vis [] = new boolean[i];
        int x [] = new int[i];
        int y [] = new int[i];
        int a [] = new int[i];
        System.arraycopy(this.xZombie, 0, x, 0, this.xZombie.length);
        this.xZombie = x;
        System.arraycopy(this.yZombie, 0, y, 0, this.yZombie.length);
        this.yZombie = y;
        System.arraycopy(this.animateZombie, 0, a, 0, this.animateZombie.length);
        this.animateZombie = a;
        System.arraycopy(this.zombieVisible, 0, vis, 0, this.zombieVisible.length);
        this.zombieVisible = vis;
         
       
        
        
        
    }
    public void makeHard3(){
        int i=15;
        
        boolean vis [] = new boolean[i];
        int x [] = new int[i];
        int y [] = new int[i];
        int a [] = new int[i];
        System.arraycopy(this.xZombie, 0, x, 0, this.xZombie.length);
        this.xZombie = x;
        System.arraycopy(this.yZombie, 0, y, 0, this.yZombie.length);
        this.yZombie = y;
        System.arraycopy(this.animateZombie, 0, a, 0, this.animateZombie.length);
        this.animateZombie = a;
        System.arraycopy(this.zombieVisible, 0, vis, 0, this.zombieVisible.length);
        this.zombieVisible = vis;
         
       
        
        
        
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    public void DrawTime() throws ParseException{
//        int sec1=Integer.parseInt((java.time.LocalTime.now()).toString().substring(6,8)),
//        min1=Integer.parseInt((java.time.LocalTime.now()).toString().substring(3,5));
        String time1 = time;
        String time2 = java.time.LocalTime.now()+"";
        
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime(); 
        
        String fi = String.format("%02d:%02d", 
            TimeUnit.MILLISECONDS.toMinutes(difference),
            TimeUnit.MILLISECONDS.toSeconds(difference) - 
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference))-4);
        
        renderer.beginRendering(gldddd.getWidth(),gldddd.getHeight());
        renderer.draw(fi, 600, 620);
        renderer.endRendering();
        
    
    }
    /*============================================================================
    **                             player                                        ||
    **============================================================================
    */
    public void DrawSprite(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);	// Turn Blending On
        
        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.1*scale, 0.1*scale, 1);
            

            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
    
     public void DrawVis(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[vis+7]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(-.90, .90, 0);
        gl.glScaled(0.1, 0.1, 1);

            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[17]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
    /*
    ** Draw pause 
    */
    public void DrawPause(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[5]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
    
    public void DrawGameOver(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[18]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glScaled(0.6, 0.6, 1);

            gl.glBegin(GL.GL_QUADS);
            
            // Front Face
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
    /*
    ** Draw lead 
    */
   public void DrawLead(GL gl,int x, int y){
       float scalex=118f,scaley=442f;
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[6]);	// Turn Blending On

        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.0001*scaley, 0.0001*scaley, 1);
            

            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
   
   /*
   ** move the lead
   ** 
   */
   public void moveLead(GL gl,boolean isvisiable[] ,int xLead[],int yLead[],int no){
       if(no==1){
       for(int i=0;i<isvisiable.length ;i++ ){
            if(isvisiable[i]==true){
                
                DrawLead(gl,xLead[i],yLead[i]=yLead[i]+3);
                if( yLead[i]>=100  || yLead[i]<=0 ){
                    visible[i]= false;
                
                    
                }
            }
       }
       }
       
   }
    /*
    ** set the lead visiable and set it x and y 
    */
    public void setLead(boolean visible[],int x[],int y[]){
        for(int i=0;i<visible.length;i++){
            if(visible[i]==false){
                visible[i] = true;
                x[i]=this.x;
                y[i]=this.y;
                
                vis++;
                break;
            }
        }
    } 
    
    public void DrawHeals(GL gl){
        gl.glEnable(GL.GL_BLEND);	
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[heals+19]);	// Turn Blending On

        gl.glPushMatrix();
        gl.glTranslated(-.70, .70, 0);
        gl.glScaled(0.1, 0.1, 1);

            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
    
    
    /*============================================================================
    **                             zombie                                       ||
    **============================================================================
    */
    
    public void DrawZombie(GL gl,int x, int y, int index, float scale){
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index+13]);	// Turn Blending On
        
        gl.glPushMatrix();
            gl.glTranslated( x/(maxWidth/2.0) - 0.9, y/(maxHeight/2.0) - 0.9, 0);
            gl.glScaled(0.1*scale, 0.1*scale, 1);
            gl.glRotatef(-90, 0, 0, 1);

            gl.glBegin(GL.GL_QUADS);
            // Front Face
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
    
    public void generateZombie(GL gl,boolean a [],int x [],int y []){
        if(zombieGenerator >= 12){
            for(int i =0; i < a.length; i++){
                if(!a[i]){
                    
                    a[i] = true;
                    zombieGenerator=0;
                    x[i]=(int)(Math.random()*90);
                    y[i]=100;
                    
                    break;
                }
            }
        }
    }
    
    public void zombieAnimate(int a []){
        for(int i = 0; i < a.length;i++){
            a[i]=a[i]%4;
        }
    }
    
    public void moveZombie(GL gl,boolean visible[],int x [],int y[] ,int animate []){
        for(int i =0;i < x.length;i++){
            if(visible[i]){
                DrawZombie(gl ,x[i], y[i]=y[i]-1, animate[i], 1f);
                animate[i]++;
                
                if(y[i] <= 10){
                    heals++;
                    visible[i] = false;
                    if(heals==5){
                        DrawHeals(gl);
                        gameOver = true;
                        
                    }
                }
        
            }
        }   
    }
    /*
    ** effects
    */
    public void coleshon(){
        
        for(int i =0;i<xlead.length;i++)
            for(int j = 0; j< xZombie.length; j++){
                if(zombieVisible[j]){
                    if((xlead[i]<xZombie[j]+5)&&(xlead[i]>xZombie[j]-5)&&(ylead[i]<=yZombie[j]+2)&&(ylead[i]>=yZombie[j]-1)){
                   
                    zombieVisible[j]=false;
                    visible[i]=false;
                    xlead[i]=-10;
                    ylead[i]=-10;
                        System.out.println("killed");
                    score++;
                    }
                }
        }
    }
    
    
    /*
    **
    */
    
    
    /*
     * KeyListener
     */    

    public void handleKeyPress() {

        if (isKeyPressed(KeyEvent.VK_LEFT)) {
            if (x > 0) {
                x-=3;
                
            }
            animationIndex++;
        }else
        if (isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (x < maxWidth-10) {
                x+=3;
                

            }
            animationIndex++;
        }
        /*
        ** player 2
        */
        
        if (isKeyPressed(KeyEvent.VK_SPACE)) {
            
            if(changeVis){
            this.setLead(visible, this.xlead, this.ylead);
            start = System.currentTimeMillis();
            }
        }
        
        
    }

    public BitSet keyBits = new BitSet(256);
 
    @Override 
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
        if(!gameOver){
        if (isKeyPressed(KeyEvent.VK_P)) {
           if(!ispause){
            ispause = !ispause;
            gldddd.repaint();
            Anim.animator.stop();
            
           }else{
            ispause = !ispause;
            gldddd.repaint();
            Anim.animator.start();
            

           }
        }
           
              
        }
        if(gameOver){
            if (isKeyPressed(KeyEvent.VK_ENTER)){
                  Game.gameplay();
                  anim.dispose();
                //  this.clip.stop();
              }
        }
        
        if (isKeyPressed(KeyEvent.VK_ESCAPE)){
                  Game.gameplay();
                  anim.dispose();
                //  this.clip.stop();
        }
        if (isKeyPressed(KeyEvent.VK_ALT) && isKeyPressed(KeyEvent.VK_F4) ){
                  Game.gameplay();
                  anim.dispose();
                  System.exit(0);
                //  this.clip.stop();
        }

    } 
 
    @Override 
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    } 
 
    @Override 
    public void keyTyped(final KeyEvent event) {
        // don't care 
    } 
 
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}
