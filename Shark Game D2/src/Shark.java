import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shark extends GameObject{
	
	Handler handler;
	Game game;

	public Shark(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement
		if(handler.isUp()) velY = -5;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = 5;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight()) velX = 5;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft()) velX = -5;
		else if(!handler.isRight()) velX = 0;
	}
	
	private void collision() {
		for(int ix = 0; ix < handler.object.size(); ix++) {
			
			GameObject tempObject = handler.object.get(ix);
			//If Game object is a block...
			if(tempObject.getId() == ID.Block) {
				//if player collides with block...
				if(getBounds().intersects(tempObject.getBounds())){
					x+= velX * -1;
					y += velY * -1;
				}
			}
			
			if(tempObject.getId() == ID.Crate) {
				//if player collides with crate...
				if(getBounds().intersects(tempObject.getBounds())){
					game.ammo += 10;
					handler.removeObject(tempObject);//remove wants collected
				}
			}
			
			if(tempObject.getId() == ID.Enemy) {
				//if player collides with crate...
				if(getBounds().intersects(tempObject.getBounds())){
					game.ammo += 10;
					handler.removeObject(tempObject);//remove wants collected
				}
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 48);
		
	}

	public Rectangle getBounds() {
		return new Rectangle (x, y, 32, 48);
	}

}
