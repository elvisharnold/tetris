package miPrimerProyecto.elvisharnold.pe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private Board board;

    public InputHandler(Board board) {
        this.board = board;
        board.setFocusable(true);
        board.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se utiliza
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            board.movePieceLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            board.movePieceRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            board.movePieceDown();
        } else if (keyCode == KeyEvent.VK_UP) {
            board.rotatePieceClockwise();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            board.rotatePieceCounterClockwise();
        } else if (keyCode == KeyEvent.VK_R) {
            board.restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No se utiliza
    }
}

