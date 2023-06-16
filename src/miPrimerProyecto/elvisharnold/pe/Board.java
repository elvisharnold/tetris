package miPrimerProyecto.elvisharnold.pe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener {
    private final int ROWS = 20;
    private final int COLS = 10;
    private final int BLOCK_SIZE = 30;
    private Color[][] grid;
    private Piece currentPiece;
    private Timer timer;
    private boolean isGameOver;

    public Board() {
        grid = new Color[ROWS][COLS];
        isGameOver = false;

        timer = new Timer(500, this);
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el tablero
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                drawBlock(g, col * BLOCK_SIZE, row * BLOCK_SIZE, grid[row][col]);
            }
        }

        // Dibujar la pieza actual
        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();
            Color color = currentPiece.getColor();
            int pieceX = currentPiece.getX();
            int pieceY = currentPiece.getY();

            for (int row = 0; row < shape.length; row++) {
                for (int col = 0; col < shape[row].length; col++) {
                    if (shape[row][col] != 0) {
                        drawBlock(g, (pieceX + col) * BLOCK_SIZE, (pieceY + row) * BLOCK_SIZE, color);
                    }
                }
            }
        }

        // Dibujar el mensaje de Game Over
        if (isGameOver) {
            g.setColor(Color.RED);
            g.drawString("Game Over", 150, 250);
        }
    }

    private void drawBlock(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
    }

    public void update() {
        if (!isGameOver) {
            if (currentPiece == null || currentPiece.isTouchingFloor(grid)) {
                if (currentPiece != null) {
                    currentPiece.addToGrid(grid);
                }
                currentPiece = Piece.getRandomPiece();
                if (currentPiece.isColliding(grid)) {
                    isGameOver = true;
                    timer.stop();
                }
            } else {
                currentPiece.moveDown();
            }

            removeCompletedLines();
        }

        repaint();
    }

    private void removeCompletedLines() {
        for (int row = ROWS - 1; row >= 0; row--) {
            boolean isLineComplete = true;
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == null) {
                    isLineComplete = false;
                    break;
                }
            }
            if (isLineComplete) {
                for (int r = row; r > 0; r--) {
                    for (int c = 0; c < COLS; c++) {
                        grid[r][c] = grid[r - 1][c];
                    }
                }
                for (int c = 0; c < COLS; c++) {
                    grid[0][c] = null;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se utiliza
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            if (currentPiece != null && !currentPiece.isCollidingLeft(grid)) {
                currentPiece.moveLeft();
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (currentPiece != null && !currentPiece.isCollidingRight(grid)) {
                currentPiece.moveRight();
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (currentPiece != null && !currentPiece.isCollidingFloor(grid)) {
                currentPiece.moveDown();
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (currentPiece != null && !currentPiece.isColliding(grid)) {
                currentPiece.rotateClockwise();
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            if (currentPiece != null && !currentPiece.isColliding(grid)) {
                currentPiece.rotateCounterClockwise();
            }
        } else if (keyCode == KeyEvent.VK_R) {
            restartGame();
        }
        repaint();
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        // No se utiliza
    }

    public void restartGame() {
        grid = new Color[ROWS][COLS];
        currentPiece = null;
        isGameOver = false;
        timer.start();
        repaint();
    }
	
	public void movePieceLeft() {
		// TODO Auto-generated method stub
		
	}

	public void movePieceRight() {
		// TODO Auto-generated method stub
		
	}

	public void movePieceDown() {
		// TODO Auto-generated method stub
		
	}

	public void rotatePieceClockwise() {
		// TODO Auto-generated method stub
		
	}

	public void rotatePieceCounterClockwise() {
		// TODO Auto-generated method stub
		
	}
}
