package examples;

import br.com.etyllica.commons.context.Application;
import br.com.etyllica.commons.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.spriter.EtyllicaDrawer;
import br.com.etyllica.spriter.EtyllicaLoader;
import br.com.etyllica.util.PathHelper;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ExampleApplication extends Application {

    public static final List<Player> players = new ArrayList<Player>();
    public static EtyllicaLoader loader;
    public static EtyllicaDrawer drawer;
    public static boolean drawBoxes = false;
    public static boolean drawBones = true;

    public ExampleApplication(int w, int h) {
        super(w, h);
    }

    // Load from files
    public void load() {
        String root = "assets/animations/GreyGuy";
        String path = PathHelper.currentDirectory() + root + "/player.scml";
        System.out.println("Loading: " + path);

        File file = new File(path);

        try {
            FileInputStream fis = new FileInputStream(file);

            SCMLReader reader = new SCMLReader(fis);
            Data data = reader.getData();

            loader = new EtyllicaLoader(data);
            loader.loadAsStream = false;
            loader.load(file);

            drawer = new EtyllicaDrawer(loader);

            Player player = new Player(data.getEntity(0));
            player.translatePivot(500, 200);
            player.setScale(1f);
            //player.setAnimation("walk");
            //leftLowerLeg
            //rightLowerLeg

            players.add(player);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updateKeyboard(KeyEvent event) {
        super.updateKeyboard(event);

        if (event.isKeyDown(KeyEvent.VK_1)) {
            for (Player player : players) {
                player.setScale(1);
            }
        }
        if (event.isKeyDown(KeyEvent.VK_2)) {
            for (Player player : players) {
                player.setScale(0.8f);
            }
        }
        if (event.isKeyDown(KeyEvent.VK_3)) {
            for (Player player : players) {
                player.setScale(0.5f);
            }
        }
        if (event.isKeyDown(KeyEvent.VK_4)) {
            for (Player player : players) {
                player.setScale(0.2f);
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        drawer.setGraphics(graphics);

        for (Player player : players) {
            player.update();
        }

        for (Player player : players) {
            drawer.draw(player);
        }

        if (drawBones) {
            for (Player player : players) {
                drawer.drawBones(player);
            }
        }

        if (drawBoxes) {
            for (Player player : players) {
                drawer.drawBoxes(player);
            }
        }
    }

}
