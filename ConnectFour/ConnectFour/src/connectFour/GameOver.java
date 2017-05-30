package connectFour;

import javax.swing.*;

public class GameOver extends JFrame {
	GameOver() {
	setTitle("Game Over");
	setSize(300, 150);
	
	JPanel p = new JPanel();
	add(p);
	
	JLabel l1 = new JLabel("Player" + ((GameBoard.turn + 1) % 2 + 1) + " Win!");
	p.add(l1);
	
	setVisible(true);
	}
}
