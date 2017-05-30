package connectFour;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameBoard extends JFrame implements KeyListener {
	public static final int ROW = 6;
	public static final int COL = 7;
	public static final int SIZE = 50;	// 격자 간격, 도형 크기
	public static Block[][] blocks = new Block[ROW][COL];
	public static Block b[] = {new Block(0, new Color(0x000000)), new Block(1, new Color(0xffffff)), new Block(2, new Color(0x000000))};
	public static int pos = 0;
	public static int turn = 0;
	public static int[][] log = {{-1, -1}, {0, 0}, {0, 0}};
	
	public static void main(String[] args) {
		turn = pos = 0;
		for(int i = 0 ; i < ROW ; ++i)
			for(int j = 0 ; j < COL ; ++j)
				blocks[i][j] = new Block(b[0]);
		GameBoard g = new GameBoard();
		printBlocks();
	}
	
	public GameBoard() {
		setTitle("Connect Four");
		setLocation(0, 0);
		setSize(600, 500);
		
		Panel p = new Panel();
		add(p);
		
		this.addKeyListener(this);	
		requestFocus();
		setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode) {
		case KeyEvent.VK_LEFT :
			pos = (pos+6) % 7;
			System.out.printf("Pos : %d\n", pos + 1);
			break;
		case KeyEvent.VK_RIGHT :
			pos = (pos+1) % 7;
			System.out.printf("Pos : %d\n", pos + 1);
			break;
		case KeyEvent.VK_SPACE :
			if(drop()) {
				boolean gameOver = chkGameOver(log[2][0], log[2][1]);
				if(gameOver) {
					GameOver gg = new GameOver();
				}
				System.out.println("chkGameOver : " + gameOver);
			}
			printBlocks();
			repaint();
			break;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {	}
	@Override
	public void keyReleased(KeyEvent e) {	}
	
	public boolean drop() {
		if(blocks[ROW - 1][pos].no != 0)	return false;
		for(int i = ROW - 1 ; i >= 0 ; --i) {
			if(i == 0 || blocks[i - 1][pos].no != 0) {
				blocks[i][pos] = b[turn + 1];
				turn = (turn + 1) % 2;
				writeLog(pos, i);
				System.out.print(log[2][0] + ", "+ log[2][1] + '\n' + log[1][0] + ", "+ log[1][1] + '\n' + log[0][0] + ", "+ log[0][1] + '\n');
				return true;
			}
		}
		return false;
	}
	public void writeLog(int x, int y) {
		for(int i = 0 ; i < 2 ; ++i) {
			log[i][0] = log[i + 1][0];
			log[i][1] = log[i + 1][1];
		}
		log[2][0] = x;
		log[2][1] = y;
	}
	public static void printBlocks() {
		for(int i = ROW - 1 ; i >= 0 ; --i) {
			for(int j = 0 ; j < COL ; ++j)
				System.out.printf(" %d", blocks[i][j].no);
			System.out.printf("\n");
		}
		System.out.printf("---------------\n");
	}
	public static boolean chkGameOver(int x, int y) {
		int player = (turn + 1) % 2 + 1, cnt = 0, tx, ty;
		// 1 모양 승패 체크
		if(y >= 3) {
			for(int i = 0 ; i < 4 ; ++i) {
				if(blocks[y - i][x].no == player) cnt++;
				else break;
			}
			if(cnt == 4) return true;
			cnt = 0;
		}
		
		// - 모양 승패 체크
		tx = x;
		while (tx > 0 && blocks[y][tx - 1].no == player)
			tx--;
		if (tx < 4) {
			for(int i = 0; i < 4; ++i) {
				if (blocks[y][tx + i].no == player)	cnt++;
				else	break;
			}
			if (tx < 3 && blocks[y][tx + 4].no == player)	cnt++;
			if (cnt == 4)	return true;
			cnt = 0;
		}
		
		// / 모양 체크
		tx = x;
		ty = y;
		while(tx > 0 && ty > 0 && blocks[ty - 1][tx - 1].no == player) {
			tx--;
			ty--;
		}
		if(tx < 4 && ty < 3) {
			for(int i = 0 ; i < 4 ; ++i) {
				if(blocks[ty + i][tx + i].no == player)	cnt++;
				else	break;
			}
			if(tx < 3 && ty < 2 && blocks[ty + 4][tx + 4].no == player)	cnt++;
			if(cnt == 4)	return true;
			cnt = 0;
		}
		
		
		// \ 모양 체크
		tx = x;
		ty = y;
		while(tx > 0 && ty < 5 && blocks[ty + 1][tx - 1].no == player) {
			tx--;
			ty++;
		}
		if(tx < 4 && ty > 2) {
			for(int i = 0 ; i < 4 ; ++i) {
				if(blocks[ty - i][tx + i].no == player)	cnt++;
				else	break;
			}
			if(tx < 3 && ty > 3 && blocks[ty - 4][tx + 4].no == player)	cnt++;
			if(cnt == 4)	return true;
		}
		return false;
	}
}