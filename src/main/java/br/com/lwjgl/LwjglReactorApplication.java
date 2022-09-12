package br.com.lwjgl;

import br.com.lwjgl.core.Program;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LwjglReactorApplication {

	public static void main(String[] args) {
		try {
			Program.getInstance().run();
		}
		catch (Exception e) {
			// reset program
			log.error(e.getMessage());
			main(null);
		}
	}
}