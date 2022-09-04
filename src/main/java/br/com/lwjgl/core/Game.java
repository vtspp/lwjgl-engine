package br.com.lwjgl.core;

import lombok.extern.slf4j.Slf4j;

@Slf4j
final class Game extends Component {
    private Window window;

    @Override
    protected void run () {
        log.info("Running {}", Game.class.getSimpleName());
        window = (Window) Component.getInstance().get(Window.class);

        while (!window.isClosed()) {
            clear();
        }

        window.destroy();
    }

    @Override
    protected void clear() {
        window.clear();
    }
}