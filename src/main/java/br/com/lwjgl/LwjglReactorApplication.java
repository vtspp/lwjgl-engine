package br.com.lwjgl;

import br.com.lwjgl.core.Program;
import br.com.lwjgl.util.EngineFileRead;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LwjglReactorApplication {

	public static void main(String[] args) {
		try {
			String fragment = EngineFileRead.read("fragment-shader.frag");
			System.out.println(fragment);

			String vertex = EngineFileRead.read("vertex-shader.vert");
			System.out.println(vertex);

			Program.getInstance().run();
		}
		catch (Exception e) {
			// reset program
			log.error(e.getMessage());
			main(null);
		}
	}
}