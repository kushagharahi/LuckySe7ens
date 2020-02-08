import java.applet.Applet;
import java.awt.Graphics;
import java.util.*;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*;

public class LuckySe7ens extends Frame implements MouseListener, MouseMotionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3596197593839018854L;
	Random rand = new Random();
	static int width = 800;
	static int	height	= 600;

	int fontSize = 20;

	int dicexPos = (width/2)-70;
	int dice2xPos = (width/2)+10;
	int diceyPos = (height/3)+(height/3);
	int dice2yPos = (height/3)+(height/3);

	int rollWidth = 100;
	int rollHeight = rollWidth/3;
	int rollxPos = (width/2) - rollWidth/2;
	int rollyPos = diceyPos+80;
	int rollStringyPos = rollyPos +(rollHeight/5+ fontSize);

	int luckyWidth = 100;
	int luckyHeight = luckyWidth/2;
	int luckyxPos = width/2 - luckyWidth/2;
	int luckyyPos = height/3;
	int luckyStringyPos = luckyyPos +(luckyHeight/5+ fontSize);

	int underWidth = 100;
	int underHeight = underWidth/ 2;
	int underxPos = width /2 - 200;
	int underyPos = height/3;
	int underStringyPos = underyPos +(underHeight/5+ fontSize);

	int overWidth = 100;
	int overHeight = overWidth/2;
	int overxPos = width/2 + 100;
	int overyPos = height/3;
	int overStringyPos = overyPos +(overHeight/5+ fontSize);

	int againWidth = 150;
	int againHeight = fontSize+10;
	int againxPos = (width/2) - (againWidth/2);
	int againyPos = (height/2) + fontSize*2+10;
	int againStringyPos = againyPos +(againHeight/5+ fontSize);

	Rectangle rollBox = new Rectangle(rollxPos, rollyPos, rollWidth, rollHeight);
	Rectangle underBox = new Rectangle(underxPos, underyPos, underWidth, underHeight);
	Rectangle luckyBox = new Rectangle(luckyxPos, luckyyPos, luckyWidth, luckyHeight);
	Rectangle overBox = new Rectangle(overxPos, overyPos, overWidth, overHeight);
	Rectangle againBox = new Rectangle(againxPos, againyPos, againWidth, againHeight);

	boolean roll = false;
	boolean rolled = false;
	boolean under = false;
	boolean lucky = false;
	boolean over = false;
	boolean again = false;

	boolean rollHover = false;
	boolean underHover = false;
	boolean luckyHover = false;
	boolean overHover = false;
	boolean againHover = false;

	public static void main(String args[])
	{
		LuckySe7ens luckySe7ens = new LuckySe7ens();
		luckySe7ens.addMouseListener(luckySe7ens);
		luckySe7ens.setSize(width, height);
		luckySe7ens.setVisible(true);
	}

	public void paint(Graphics g)
	{
		rolled = false;
		again = false;
		int d1 = 0;
		int	d2 = 0;
		int stringWidth = 0;
		int stringHeight = 0;

		drawLogo(g, width/2 - 400, 10);

		g.setFont(new Font("sansserif", Font.BOLD, fontSize));
		FontMetrics fm = g.getFontMetrics();

		stringWidth = fm.stringWidth("ROLL");
		g.drawString("ROLL", rollxPos+((rollWidth/2)-(stringWidth/2)),rollStringyPos);

		stringWidth = fm.stringWidth("Under");
		g.drawString("Under", underxPos+((underWidth/2)-(stringWidth/2)),underStringyPos);

		stringWidth = fm.stringWidth("Lucky");
		g.drawString("Lucky", luckyxPos+((luckyWidth/2)-(stringWidth/2)),luckyStringyPos);

		stringWidth = fm.stringWidth("Over");
		g.drawString("Over", overxPos+((overWidth/2)-(stringWidth/2)),overStringyPos);

		drawRect(g, underxPos, underyPos, underWidth, underHeight, under);
		drawRect(g, luckyxPos, luckyyPos, luckyWidth, luckyHeight, lucky);
		drawRect(g, overxPos, overyPos, overWidth, overHeight, over);
		drawRect(g, rollxPos, rollyPos, rollWidth, rollHeight, roll);

		drawDie(g, dicexPos, diceyPos, 1);
		drawDie(g, dice2xPos, dice2yPos, 1);

		if(((under == true || lucky == true || over == true) != true) && roll == true)
		{
			rolled = false;
			roll = false;
			g.setColor(Color.RED);
			stringWidth = fm.stringWidth("You must pick if the die are going to add up to be Under, Lucky, or Over!");
			g.drawString("You must pick if the die are going to add up to be Under, Lucky, or Over!", (width/2)-(stringWidth/2), (height/2)+stringHeight+10);
			
		}

		if(((under == true || lucky == true || over == true)!= false) && roll == true)
		{
			for(int x = 0; x < 10; x++)
			{
				int dice = rand.nextInt(6) + 1;
				int dice1 = rand.nextInt(6) + 1;
				drawDie(g, dicexPos, diceyPos, dice);
				drawDie(g, dice2xPos, dice2yPos, dice1);
				try
				{
					Thread.sleep(x+100);
				}
				catch(Exception e){}
			}
			d1 = getDice();
			d2 = getDice();
			int dieSum = d1 + d2;
			drawDie(g, dicexPos, diceyPos, d1);
			drawDie(g, dice2xPos, dice2yPos, d2);
			if(under == true && dieSum <7)
			{
				g.setColor(Color.GREEN);
				stringWidth = fm.stringWidth("WINNRAR!!");
				g.drawString("WINNRAR!!", (width/2)-(stringWidth/2), height/2);
				stringHeight = fm.getHeight();
				stringWidth = fm.stringWidth("Click on Under, Lucky, or Roll to play again!");
				g.drawString("Click on Under, Lucky, or Roll to play again!", (width/2)-(stringWidth/2), (height/2)+stringHeight+10);
			}
			else if(lucky == true && dieSum == 7)
			{
				g.setColor(Color.GREEN);
				stringWidth = fm.stringWidth("WINNRAR!!");
				g.drawString("WINNRAR!!", (width/2)-(stringWidth/2), height/2);
				stringHeight = fm.getHeight();
				stringWidth = fm.stringWidth("Click on Under, Lucky, or Roll to play again!");
				g.drawString("Click on Under, Lucky, or Roll to play again!", (width/2)-(stringWidth/2), (height/2)+stringHeight+10);
			}
			else if(over == true && dieSum > 7)
			{
				g.setColor(Color.GREEN);
				stringWidth = fm.stringWidth("WINNRAR!!");
				g.drawString("WINNRAR!!", (width/2)-(stringWidth/2), height/2);
				stringHeight = fm.getHeight();
				stringWidth = fm.stringWidth("Click on Under, Lucky, or Roll to play again!");
				g.drawString("Click on Under, Lucky, or Roll to play again!", (width/2)-(stringWidth/2), (height/2)+stringHeight+10);
			}
			else
			{
				g.setColor(Color.RED);
				stringWidth = fm.stringWidth("You lost, better luck next time! :(");
				g.drawString("You lost, better luck next time! :(", (width/2)-(stringWidth/2), height/2);
				stringHeight = fm.getHeight();
				stringWidth = fm.stringWidth("Click on Under, Lucky, or Roll to play again!");
				g.drawString("Click on Under, Lucky, or Roll to play again!", (width/2)-(stringWidth/2), (height/2)+stringHeight+10);
			}
			rolled = true;
		}
		if(rolled == true)
		{
			g.setColor(Color.BLACK);
			stringWidth = fm.stringWidth("Play Again?");
			g.drawString("Play Again?", againxPos+((againWidth/2)-(stringWidth/2)),againStringyPos);
			drawRect(g,againxPos, againyPos, againWidth, againHeight, again);

			if(again == true)
				reset();
		}

	}

	public int getDice()
	{
		int dice = rand.nextInt(6) + 1;
		return dice;
	}

	public void drawRect(Graphics g, int x, int y, int width, int height, boolean clicked)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, 10, 10);
        if(clicked != true)
        {
        g2D.draw(roundedRectangle);
        }else{
        Color tBlue = new Color(0, 0, 255, 150); //transparent blue
        g2D.setPaint(tBlue);
  	 	g2D.fill(roundedRectangle);
        }
	}


	public void hover(Graphics2D g2D, int x, int y, int width, int height)
	{

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width, height, 10, 10);
        Color tYellow = new Color(0, 240, 196, 0); //transparent blue
        g2D.setPaint(tYellow);
  	 	g2D.fill(roundedRectangle);

	}



	public void drawDie(Graphics g, int x, int y, int dice)
	{
		Image die = new ImageIcon(this.getClass().getResource("res/Die/" + dice + ".png")).getImage();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(die, x, y, null);
	}

	public void drawLogo(Graphics g, int x, int y)
	{
		Image logo = new ImageIcon(this.getClass().getResource("res/luckyse7ens.png")).getImage();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(logo, x, y, null);
	}


	public void reset()
	{
			roll = false;
			rolled = false;
			under = false;
			lucky = false;
			over = false;
			again = false;
			repaint();
	}
	public void deselectAll()
	{
		under = false;
		lucky = false;
		over = false;
		again = false;
	}

	    public void mousePressed(MouseEvent e)
	    {
	    	if(rollBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		roll = true;
	    		again = false;
	    	}

	    	else if(underBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		deselectAll();
	    		under = true;
	    	}

	    	else if(luckyBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		deselectAll();
	    		lucky = true;
	    	}

	    	else if(overBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		deselectAll();
	    		over = true;
	    	}

	    	else if(againBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		again = true;
	    	}
	    	else
				return;

			again = false;
			repaint();
	    }
	    public void mouseReleased(MouseEvent e)
	    {
	    	roll = false;
	    }
        public void mouseClicked(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) { }
        public void mouseDragged(MouseEvent e) { }
        public void mouseMoved(MouseEvent e)
        {
        	if(rollBox.contains(new Point(e.getX(),e.getY())))
	    	{
	    		rollHover = true;
	    	}

	    	else if(underBox.contains(new Point(e.getX(),e.getY())))
	    	{
				underHover = true;
	    	}

	    	else if(luckyBox.contains(new Point(e.getX(),e.getY())))
	    	{
				luckyHover = true;
	    	}

	    	else if(overBox.contains(new Point(e.getX(),e.getY())))
	    	{
				overHover = true;
	    	}

	    	else if(againBox.contains(new Point(e.getX(),e.getY())))
	    	{
				againHover = true;
	    	}
	    	else
				return;

			repaint();
        }

}


