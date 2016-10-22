package br.com.etyllica.spriter;

import br.com.etyllica.layer.ImageLayer;

import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.FileReference;
import com.brashmonkey.spriter.Loader;

public class EtyllicaLoader extends Loader<ImageLayer> {

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

        return new ImageLayer(path, true);
    }
}
