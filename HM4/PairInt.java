// Name : Xunzhi Li
// Id: 10457500
// Homework 3:Maze
package hw4;
public class PairInt {
	private int x;
	private int y;
	
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object p){
		if (!(p instanceof PairInt)) return false;
		PairInt P = (PairInt) p;
		return P.getX()==this.x && P.getY()==this.y;
    }
	@Override
    public String toString() {
		return "(" + x + ", " + y + ")";

    }

    public PairInt copy(){
        PairInt new_one = new PairInt(x, y);
        return new_one;
    }


}
