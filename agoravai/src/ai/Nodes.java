package ai;

import java.util.ArrayList;

public class Nodes {
	private int x;
	private int y;
	private int grau;
	private ArrayList<Nodes> children = new ArrayList<Nodes>();
	public Nodes(int x, int y, int grau) {
		this.x = x;
		this.y = y;
		this.grau = grau;
		
	}
	
	public void addChild(Nodes child) {
		this.children.add(child);
	}
	
	public ArrayList<Nodes> getChildren(){
		return this.children;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getGrau() {
		return grau;
	}

	public void setGrau(int grau) {
		this.grau = grau;
	}
}
