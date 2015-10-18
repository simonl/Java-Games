package Graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Utils.Refs;

import Functional.Func;
import Functional.Ref;
import Functional.Pair;
import Game.Coord;

public class Graph {
	
	private final static Func<Double, Double> f = new Func<Double, Double>() {
		
		public Double invoke(final Double x) {
			
			return x * x;
		}
	};

	@SuppressWarnings("serial")
	public static void main(final String... args) {
		new SaneJFrame(new GraphPanel(f, 0, 100)).repaint();
	}
	
	@SuppressWarnings("serial")
	private static class GraphPanel extends JPanel {

		private final int sizeX = 500;
		private final int sizeY = 500;
		
		private final int minX;
		private final int maxX;
		private final int maxY;
		private final int minY;
		
		private final Coord[] plotted;
		
		public GraphPanel(final Func<Double, Double> f, final int pMinX, final int pMaxX) {

			this.setSize(sizeX, sizeY);
			
			
			this.minX = pMinX;
			this.maxX = pMaxX;

			final double step = (maxX - minX) / sizeX;
			
			final double[] xs = new double[sizeX];
			for(int i = 0; i < xs.length; i++)
				xs[i] = i * step;
			
			final RCoord[] cs = new RCoord[sizeX];
			for(int i = 0; i < cs.length; i++)
				cs[i] = new RCoord(xs[i], f.invoke(xs[i]));	
			
			
			plotted = new Coord[maxX - minX];
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int x = minX; x < maxX; x++) {
				final int y = f.invoke((double) x).intValue();
				
				plotted[x - minX] = new Coord(x, y);
				
				if(y > max) max = y;
				if(y < min) min = y;
			}

			this.maxY = max;
			this.minY = min;
			
			for(int i = 0; i < plotted.length; i++)
				plotted[i] = graphToScreen(plotted[i]);
			
			this.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(final MouseEvent me) {
					mouseMoved(me);
				}

				@Override
				public void mouseMoved(final MouseEvent me) {
					mouse.write(new Coord(me.getX(), me.getY()));
					repaint();
				} 
				
			});
		}    
		
		private Ref<Coord> mouse = Refs.box(Coord.origin);
		
		public void paint(final Graphics g) {
			
			
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 500, 500);
						
			Coord prev = plotted[0];
			for(int i = 1; i < plotted.length; i++) {
				final Coord current = plotted[i];
				g.drawLine(prev.x, prev.y, current.x, current.y);
				prev = current;
			}
			
			final Coord onScreen = mouse.read();
			final Coord onGraph = screenToGraph(onScreen);
			
			final String caption = onGraph.toString();
			g.drawString(caption, onScreen.x, 15 + onScreen.y);
		}
		
		private Coord graphToScreen(final Coord coord) {
			return new Coord(coord.x * sizeX / (maxX - minX), coord.y * sizeY / (maxY - minY));
		}
		
		private Coord screenToGraph(final Coord coord) {
			return new Coord(coord.x * (maxX - minX) / sizeX, coord.y * (maxY - minY) / sizeY);
		}
	}
	
	private static class RCoord {
		final double x;
		final double y;
		
		public RCoord(final double x, final double y) {
			this.x = x;
			this.y = y;
		}
	}

}
