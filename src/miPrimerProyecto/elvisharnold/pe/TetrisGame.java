package miPrimerProyecto.elvisharnold.pe;

import javax.swing.JFrame;

public class TetrisGame {
    public static void main(String[] args) {
        // Crear una instancia de JFrame para representar la ventana del juego
        JFrame frame = new JFrame("Tetris");
        frame.setSize(317, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de la clase Board que representa el tablero de juego
        Board board = new Board();

        // Agregar el tablero al JFrame
        frame.add(board);

        // Crear una instancia de la clase InputHandler para manejar la entrada del jugador
        InputHandler inputHandler = new InputHandler(board);
        frame.addKeyListener(inputHandler);

        // Hacer que el tablero escuche los eventos de teclado para mover y rotar las piezas
        board.addKeyListener(inputHandler);

        // Mostrar la ventana del juego
        frame.setVisible(true);
    }
}

