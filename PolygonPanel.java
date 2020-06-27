import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PolygonPanel extends JPanel{
	
	ArrayList<PolygonDraw> arrList = new ArrayList<>();
	PolygonDraw p;
	
	public void createPanel() {
		JPanel panel = new JPanel();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Draw(g);
	}
	
	public void Draw(Graphics g) {
		for(int i=0; i<arrList.size();i++) {
			arrList.get(i).drawComponent(g);
			for(int j=0;j<arrList.get(i).getV();j++) {
				drawNthPoint(g, arrList.get(i), j);
			}
		}
	}
	
	public void drawNthPoint(Graphics g, PolygonDraw p, int i) {
		String name = Integer.toString((char) (0 + i));
		int x[] = p.getX();
		int y[] = p.getY();
		g.drawString(name, x[i]+5, y[i]+5);
	}
	
	public void drag(int a, int b, int h) {
		for(int i=0; i<arrList.size();i++) {
			int x[] = arrList.get(0).getX();
			x[h] = a;
			int y[] = arrList.get(0).getY();
			y[h] = b;
			PolygonDraw p = new PolygonDraw(x, y, arrList.get(0).getV());
			arrList.clear();
			arrList.add(p);
			Main.setAr=Double.toString(p.getArea());
			p.getLength();
			p.getAngle();
			repaint();
		}
	}
	
	public void addPoly(int v) {
		int x[] = new int[v];
		int y[] = new int[v];
		for(int i=0;i<v;i++) {
		 x[i]= (int)(300 + 100 * Math.cos(i * 2 * Math.PI / v));
		 y[i]= (int)(370 + 100 * Math.sin(i * 2 * Math.PI / v));
		}
		p = new PolygonDraw(x, y, v);
		arrList.clear();
		arrList.add(p);
		Main.setAr=Double.toString(p.getArea());
		p.getLength();
		p.getAngle();
		repaint();
	}
}
