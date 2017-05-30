package connectFour;

import java.awt.Color;

class Block {
	public int no;
	public Color c;
	public Block(Block b) {
		no = b.no;
		c = b.c;
	}
	public Block(int no, Color c) {
		this.no = no;
		this.c = c;
	}
	@Override
	public String toString() {
		return "Plyer No : " + no + 
				"\nColor : r=" + c.getRed() + 
				", g=" + c.getGreen() + 
				", b=" + c.getBlue()+ '\n';
	}
}
