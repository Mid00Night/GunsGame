package Texture;



import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import javax.media.opengl.*;
import javax.swing.*;

public class Anim extends JFrame {
    AnimListener listener;
    AnimListener listener2;
    public static Animator animator;
    
    

    public Anim() {
        GLCanvas glcanvas;
         listener = new AnimGLEventListener5(this);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();
        
        
        
        
        
        setTitle("Guns Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
    public Anim(boolean a) {
        GLCanvas glcanvas;
         listener = new AnimGLEventListener5(this);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(10);
        animator.add(glcanvas);
        animator.start();
        
       
        
        
        
        setTitle("Guns Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
public Anim(int a) {
        GLCanvas glcanvas;
        listener2 = new AnimGLEventListener6(this);
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener2);
        glcanvas.addKeyListener(listener2);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();
        
        
        
        
       
        setTitle("Guns Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
   
    
}
