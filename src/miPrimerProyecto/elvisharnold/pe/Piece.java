

package miPrimerProyecto.elvisharnold.pe;

import java.awt.Color;

public class Piece {
    private int[][] shape;
    private Color color;
    private int x;
    private int y;

    public Piece(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.x = 0;
        this.y = 0;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void rotateClockwise() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                rotatedShape[col][shape.length - 1 - row] = shape[row][col];
            }
        }
        shape = rotatedShape;
    }

    public void rotateCounterClockwise() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                rotatedShape[shape[row].length - 1 - col][row] = shape[row][col];
            }
        }
        shape = rotatedShape;
    }

    public boolean isColliding(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col;
                    int gridY = y + row;
                    if (gridY >= 0 && (gridX < 0 || gridX >= grid[0].length || gridY >= grid.length || grid[gridY][gridX] != null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCollidingLeft(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col - 1;
                    int gridY = y + row;
                    if (gridX < 0 || gridY >= grid.length || grid[gridY][gridX] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCollidingRight(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col + 1;
                    int gridY = y + row;
                    if (gridX >= grid[0].length || gridY >= grid.length || grid[gridY][gridX] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCollidingFloor(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col;
                    int gridY = y + row + 1;
                    if (gridY >= grid.length || grid[gridY][gridX] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTouchingFloor(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col;
                    int gridY = y + row + 1;
                    if (gridY >= grid.length || grid[gridY][gridX] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void addToGrid(Color[][] grid) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int gridX = x + col;
                    int gridY = y + row;
                    grid[gridY][gridX] = color;
                }
            }
        }
    }

    public static Piece getRandomPiece() {
        // Define las formas de las piezas y sus colores
        int[][][] shapes = {
            { { 1, 1, 1, 1 } }, // I
            { { 1, 1 }, { 1, 1 } }, // O
            { { 1, 1, 1 }, { 0, 1, 0 } }, // T
            { { 1, 1, 0 }, { 0, 1, 1 } }, // S
            { { 0, 1, 1 }, { 1, 1, 0 } }, // Z
            { { 1, 1, 1 }, { 1, 0, 0 } }, // L
            { { 1, 1, 1 }, { 0, 0, 1 } } // J
        };

        Color[] colors = {
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.GREEN,
            Color.RED,
            Color.ORANGE,
            Color.BLUE
        };

        // Genera una pieza aleatoria
        int randomIndex = (int) (Math.random() * shapes.length);
        int[][] shape = shapes[randomIndex];
        Color color = colors[randomIndex];
        return new Piece(shape, color);
    }
}
