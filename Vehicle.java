package TractorForm;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {

	protected float _startPosX;
	protected float _startPosY;
	protected int _ScreenWidth;
	protected int _ScreenHeight;
	public int MaxSpeed;
	public int Weight;
	public Color MainColor;
 
	public int getMaxSpeed() {
		return MaxSpeed;
	}

	public int getWeight() {
		return Weight;
	}

	public Color getMainColor() {
		return MainColor;
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_ScreenWidth = width;
		_ScreenHeight = height;
	}

	public void SwitchColor(Graphics g) {
		MainColor = Color.cyan;
	}

	public void SetMainColor(Color color) {
		MainColor = color;
	}
	
	public abstract void DrawTractor(Graphics g);

	public abstract void MoveTransport(Direction direction);
	
	public abstract String getInfo();
}
