//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Fall 2024
//
// Author:   Sahana Ramesh
// Email:    ramesh37@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Aditya Kolsur through Piazza
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

// 3.2
import java.util.Random;
// 3.3
import java.io.File;
import processing.core.PImage;

public class WalkingSim {
	// 3.2
	private static Random randGen;
	private static int bgColor;
	
	// 4.1
	private static PImage[] frames;
	
	// 4.2
	private static Walker[] walkers;
	
	public static void main(String[] args) {
		Utility.runApplication();
	}
	
	
	// 3.1
	public static void setup(){
		// 3.2
		randGen = new Random();
		bgColor = randGen.nextInt();
		
		// 4.1
		frames = new PImage[Walker.NUM_FRAMES];
		walkers = new Walker[8];
		
		for(int i = 0; i < frames.length; i++) {
			frames[i] = Utility.loadImage("images" + File.separator + "walk-" + i + ".png");
		}
		
		// 4.3
		for(int i = 0; i < randGen.nextInt(1, walkers.length); i++) {
			walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
		}
	}
	
	// 3.1
	public static void draw(){
		Utility.background(bgColor);
		
		// 4.3
		for(Walker w : walkers) {
			if(w != null) {
				if(w.isWalking()) {
					if((w.getPositionX() + 3) > Utility.width()) {
						w.setPositionX(0);
					}
					else {
						w.setPositionX(w.getPositionX() + 3);
					}
				}
				Utility.image(frames[w.getCurrentFrame()], w.getPositionX(), w.getPositionY());
			}
		}
		
		// 4.5
		for(Walker w : walkers) {
			if(w != null) {
				if(w.isWalking()) {
					w.update();
				}
			}
		}

		
	}
	
	// 4.4
	public static boolean isMouseOver(Walker w) {
		return (Utility.mouseX() >= (w.getPositionX() - frames[0].width/2) && Utility.mouseX() <= (w.getPositionX() + frames[0].width/2)) 
				&& (Utility.mouseY() >= (w.getPositionY() - frames[0].height/2) && Utility.mouseY() <= (w.getPositionY() + frames[0].height/2));
	}
	
	// 4.5
	public static void mousePressed() {
		for(Walker w : walkers) {
			if(w != null) {
				if(isMouseOver(w)) {
					w.setWalking(true);
					break;
				}
			}
		}
	}
	
	// 5.1
	public static void keyPressed(char c) {
		if((c == 'a') && (walkers[walkers.length - 1] == null)) {
			for(int i = 0; i < walkers.length; i++) {
				if(walkers[i] == null) {
					walkers[i] = new Walker(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height()));
					break;
				}
			}
			
		}
		if(c == 's') {
			for(Walker w : walkers) {
				if(w != null) {
					w.setWalking(false);
				}
			}
		}
	}
}