package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import br.com.etyllica.core.context.Application;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.spriter.EtyllicaDrawer;
import br.com.etyllica.spriter.EtyllicaLoader;
import br.com.etyllica.util.PathHelper;

import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

public class MonsterApplication extends Application {

	Player player;
	public static EtyllicaLoader loader;
	public static EtyllicaDrawer drawer;
	public static boolean drawBoxes = false;
	public static boolean drawBones = true;

	public boolean walkingRight = false;
	public boolean walkingLeft = false;

	public MonsterApplication(int w, int h) {
		super(w, h);
	}

	public void update(long now) {
		if (walkingRight || walkingLeft) {
			if (!"run".equals(player.getAnimation().name)) {
				player.setAnimation("run");	
			}
			if (walkingLeft) {
				if (player.flippedX() != -1) {
					player.flipX();
				}
			} else if (walkingRight) {
				if (player.flippedX() == -1) {
					player.flipX();
				}
			}
		} else {
			player.setAnimation("idle");
		}

	}

	public void load() {
		String path = PathHelper.currentDirectory()+"../assets/animations/monster/basic.scml";
		System.out.println("Loading: "+path);

		File file = new File(path);

		try {
			FileInputStream fis = new FileInputStream(file);

			SCMLReader reader = new SCMLReader(fis);
			Data data = reader.getData();

			loader = new EtyllicaLoader(data);
			loader.load(new File(path));

			drawer = new EtyllicaDrawer(loader);

			player = new Player(data.getEntity(0));
			player.translatePivot(500, 200);
			player.setScale(1f);
			/*for (Animation animation : player.getAnimations()) {
				System.out.println(animation.name);
			}*/
			//player.setAnimation("walk");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateKeyboard(KeyEvent event) {
		super.updateKeyboard(event);

		if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
			walkingRight = true;
		} else if (event.isKeyUp(KeyEvent.VK_RIGHT)) {
			walkingRight = false;
		}

		if (event.isKeyDown(KeyEvent.VK_LEFT)) {
			walkingLeft = true;
		} else if (event.isKeyUp(KeyEvent.VK_LEFT)) {
			walkingLeft = false;
		}

		if (event.isKeyDown(KeyEvent.VK_1)) {
			player.setScale(1);
		}
		if (event.isKeyDown(KeyEvent.VK_2)) {
			player.setScale(0.8f);
		}
		
		if (event.isKeyUp(KeyEvent.VK_X)) {
			player.flipX();
		}
		
		if (event.isKeyUp(KeyEvent.VK_Y)) {
			player.flipY();
		}

	}

	@Override
	public void draw(Graphics graphics) {
		drawer.setGraphics(graphics);

		player.update();
		drawer.draw(player);

		if (drawBones) {
			drawer.drawBones(player);
		}

		if (drawBoxes) {
			drawer.drawBoxes(player);
		}
	}

}
