package br.com.etyllica.spriter;

import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.FileReference;
import com.brashmonkey.spriter.Loader;

import java.io.IOException;
import java.io.InputStream;

import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.layer.StaticLayer;
import br.com.etyllica.loader.image.ImageLoader;
import br.com.etyllica.util.PathHelper;

public class EtyllicaLoader extends Loader<ImageLayer> {

    public static boolean asyncLoad = false;

    /**
     * Creates a loader with the given Spriter data.
     *
     * @param data the generated Spriter data
     */
    public EtyllicaLoader(Data data) {
        super(data);
    }

    @Override
    protected ImageLayer loadResource(FileReference ref) {

        String pathPrefix;

        if (super.root == null || super.root.isEmpty()) {
            pathPrefix = "";
        } else {
            pathPrefix = super.root + java.io.File.separator;
        }

        String path = pathPrefix + data.getFile(ref).name;

        try {
            //Avoid this line in Android
            InputStream stream = null;
            if (!asyncLoad) {
                stream = PathHelper.loadAsset(path);
            }
            StaticLayer layer = ImageLoader.getInstance().loadImage(stream, path);
            ImageLayer imageLayer = new ImageLayer();
            imageLayer.cloneLayer(layer);

            return imageLayer;
        } catch (IOException e) {
            System.err.println("Asset not found: " + path);
            e.printStackTrace();
        }

        return null;
    }
}
