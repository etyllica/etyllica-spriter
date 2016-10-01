

import br.com.etyllica.Etyllica;
import br.com.etyllica.core.context.Application;
import examples.ExampleApplication;

public class SpriterExample extends Etyllica {

	private static final long serialVersionUID = 1L;

	public SpriterExample() {
		super(1280, 720);
	}

	public static void main(String[] args){
		SpriterExample app = new SpriterExample();
		app.setTitle("Spriter Example");
		app.init();
	}

	@Override
	public Application startApplication() {
		initialSetup("../");
		return new ExampleApplication(w, h);
	}
	
}
