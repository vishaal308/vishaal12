import java.util.*;

public class MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final Random random = new Random();

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
        generateMaze(0, 0);
    }

    private void generateMaze(int x, int y) {
        // Directions: up, right, down, left
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        Integer[] dirs = {0, 1, 2, 3};
        Collections.shuffle(Arrays.asList(dirs), random);

        maze[y][x] = 1; // Mark as visited

        for (int dir : dirs) {
            int nx = x + dx[dir] * 2;
            int ny = y + dy[dir] * 2;

            if (withinBounds(nx, ny) && maze[ny][nx] == 0) {
                maze[y + dy[dir]][x + dx[dir]] = 1; // carve path between cells
                generateMaze(nx, ny);
            }
        }
    }

    private boolean withinBounds(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public void printMaze() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(maze[y][x] == 1 ? "  " : "█");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int width = 21;  // must be odd
        int height = 11; // must be odd
        MazeGenerator m = new MazeGenerator(width, height);
        m.printMaze();
    }
}