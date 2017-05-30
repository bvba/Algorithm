package connectFour;

import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel {
	static final int ROW = GameBoard.ROW;
	static final int COL = GameBoard.COL;
	static final int SIZE = GameBoard.SIZE;
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0 ; i < 8 ; ++i) {
			g.drawLine(SIZE * i, SIZE, SIZE * i, SIZE * (ROW + 1));	// 세로줄
			if(i < 7)
				g.drawLine(0, SIZE*(i+1), SIZE * COL, SIZE*(i+1));	// 가로줄
		}
		for(int i = 0 ; i < ROW ; ++i) {	// block 배치
			for(int j = 0 ; j < COL ; ++j) {
				if(GameBoard.blocks[i][j].no != 0) {	// block 있으면 draw
					g.setColor(GameBoard.blocks[i][j].c);
					g.drawOval(SIZE * j + 2, SIZE *ROW - SIZE * i + 2, SIZE - 4, SIZE - 4);
					g.fillOval(SIZE * j + 2, SIZE *ROW - SIZE * i + 2, SIZE - 4, SIZE - 4);
				}
			}
		}
		g.setColor(Color.black);
		switch(GameBoard.turn) {
		case 0 : g.drawString("Player 1 turn", 0, 400); break;
		case 1 : g.drawString("Player 2 turn", 0, 400); break;
		}
		g.setColor(Color.yellow);
		g.drawPolygon(new int[] {GameBoard.pos * 50, (GameBoard.pos + 1)*50, GameBoard.pos * 50 + 25}, new int[] {0, 0, 35}, 3);
		g.fillPolygon(new int[] {GameBoard.pos * 50, (GameBoard.pos + 1)*50, GameBoard.pos * 50 + 25}, new int[] {0, 0, 35}, 3);
		repaint();
	}
}
