package miPrimerProyecto.elvisharnold.pe;

import javax.swing.Timer;

public class GameLoop {
    private Timer timer;
    private Board board;

    public GameLoop(Board board) {
        this.board = board;

        timer = new Timer(500, e -> {
            board.update();
            board.repaint();
        });
        timer.start();
    }
}
