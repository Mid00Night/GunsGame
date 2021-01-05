package Texture;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package project;

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
//    public static void main(String[] args) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Anim.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//           Anim a = new Anim();
//    }


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
        
        //test area
        
        
        //test area
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
        
        //test area
        
        
        //test area
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
        
        //test area
        
        
        //test area
        setTitle("Guns Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
   
    
}
