/*
E/15/208-E/15/211
CO225-Software Construction
Fractals: Natures building blocks
*/

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Julia extends JComponent implements ActionListener {

	private final int WIDTH = 800;
  private final int HEIGHT = 800;
  private final float SCALE = 400;

	private float hueOffset = 0;
	private BufferedImage buffer;
	private Timer timer;

	private double cRex;
	private double cImy;
	private int iterations;

	private Thread t1;
	private Thread t2;
	private Thread t3;
	private Thread t4;

	//constructor for the julia class
	public Julia(double cRex, double cImy, Integer iterations){
		//update the global variables with the arguments given to the constructor
		this.cRex = cRex;
		this.cImy = cImy;
		this.iterations = iterations;

		buffer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		timer = new Timer(1,this);

		renderJuliaSet();

		//create a new frame 'frame'
		JFrame frame =new JFrame("Julia Set");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public void addNotify(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		timer.start();
	}


	public void renderJuliaSet(){

		//apply thread to reducethe execution time by assuming as four same parts
		t1 = new Thread (new Runnable() {
      public void run() {
        for(int x = 0; x < 200; x++) {

          for(int y = 0; y < 800; y++) {
            int color = calculatePoint((x - WIDTH/2f)/SCALE,(y - HEIGHT/2f)/SCALE);
            buffer.setRGB(x,y,color);
          }

        }
      }
    });

    t2 = new Thread (new Runnable() {
      public void run() {
        for(int x = 200; x < 400; x++) {

          for(int y = 0; y < 800; y++) {
            int color = calculatePoint((x - WIDTH/2f)/SCALE,(y - HEIGHT/2f)/SCALE);
            buffer.setRGB(x,y,color);
          }

        }
      }
    });

    t3 = new Thread (new Runnable() {
      public void run() {
        for(int x = 400; x < 600; x++) {

          for(int y = 0; y < 800; y++) {
            int color = calculatePoint((x - WIDTH/2f)/SCALE,(y - HEIGHT/2f)/SCALE);
            buffer.setRGB(x,y,color);
          }

        }
      }
    });

    t4 = new Thread (new Runnable() {
      public void run() {
        for(int x = 600; x < 800; x++) {

          for(int y = 0; y < 800; y++) {
            int color = calculatePoint((x - WIDTH/2f)/SCALE,(y - HEIGHT/2f)/SCALE);
            buffer.setRGB(x,y,color);
          }

        }
      }
    });

		t1.start();
    t2.start();
    t3.start();
    t4.start();

    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (InterruptedException e) {
      System.out.println("Thread join() error");
    }
	}

	public int calculatePoint(double x,double y){
		int i = 0;

		for(i = 0; i < iterations; i++){
				if(((cRex > -1) && (cRex < 1)) && ((cImy > -1) && (cImy < 1)) ) {
			//the actual iteration, the real and imaginary part are calculated
			 double xNew = x*x - y*y + cRex;
			 double yNew = 2*x*y + cImy;

       x= xNew;
 			 y= yNew;


				//if ABS value of complex point > 2, point is not in the Mandelbrot set, it is outside the circle with radius 2: stop
			if((x*x + y*y) > 4) {
				break;
			}
			}
		}

		if(i == iterations){
			 return 0x00000000;
		}
		return Color.HSBtoRGB(((float)i/iterations + hueOffset), 1,1);
	}

	public void paint(Graphics g){
		g.drawImage(buffer,0,0,null);
	}

	public void actionPerformed(ActionEvent e){
		hueOffset = hueOffset + 0.01f;
		renderJuliaSet();
		repaint();
	}

	}
