package connectFour;

import java.awt.event.*;

import javax.swing.*;

public class Panel extends JPanel {
	JLabel l1 = new JLabel("Change Color");
	JButton changeP1Color = new JButton("Player 1");
	JButton changeP2Color = new JButton("Player 2");
	JButton takeBack = new JButton("무르기");
	JButton giveUp = new JButton("기권");
	
	MyListener listener = new MyListener();
	
	Panel() {
		this.setLayout(null);

		l1.setBounds(20, 50, 100, 20);
		add(l1);

		changeP1Color.setBounds(20, 80, 80, 20);
		changeP1Color.addActionListener(listener);
		add(changeP1Color);
		
		changeP2Color.setBounds(20, 120, 80, 20);
		changeP2Color.addActionListener(listener);
		add(changeP2Color);

		takeBack.setBounds(20, 210, 80, 20);
		takeBack.addActionListener(listener);
		add(takeBack);

		giveUp.setBounds(20, 250, 80, 20);
		giveUp.addActionListener(listener);
		add(giveUp);

		GamePanel gp = new GamePanel();
		gp.setBounds(180, 30, 600, 500);
		add(gp);
	}
	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == changeP1Color)
				GameBoard.b[1].c = JColorChooser.showDialog(new JFrame(), "Choose a color", GameBoard.b[1].c);
			if(e.getSource() == changeP2Color)
				GameBoard.b[2].c = JColorChooser.showDialog(new JFrame(), "Choose a color", GameBoard.b[2].c);
			if(e.getSource() == giveUp) {
				GameOver gg = new GameOver();
			}
			if(e.getSource() == takeBack) {
				if(GameBoard.log[2][0] != -1) {
					GameBoard.turn = (GameBoard.turn + 1) % 2;
					GameBoard.blocks[GameBoard.log[2][1]][GameBoard.log[2][0]] = GameBoard.b[0];

					for (int i = 2; i > 0; --i) {
						GameBoard.log[i][0] = GameBoard.log[i - 1][0];
						GameBoard.log[i][1] = GameBoard.log[i - 1][1];
					}
					GameBoard.log[0][0] = -1;
					GameBoard.log[0][1] = -1;
				}
			}
		}
	}
}
