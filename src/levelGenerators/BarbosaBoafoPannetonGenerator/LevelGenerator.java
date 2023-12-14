package levelGenerators.BarbosaBoafoPannetonGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.Random;

public class LevelGenerator implements MarioLevelGenerator {

    // Constants for the level generation parameters
    private static final int WIDTH = 320;
    private static final int HEIGHT = 15;
    private static final int GROUND_HEIGHT = 2;
    private static final int NUM_PLATFORMS = 5;
    private static final int NUM_ICE_BLOCKS = 3;
    private static final int NUM_COINS = 5;

    @Override
    public String getGeneratorName() {
        return "BarbosaBoafoPannetonGenerator";
    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        int[][] level = generateColdThemeLevel();

        // Convert the 2D array to a string representation
        StringBuilder levelString = new StringBuilder();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                levelString.append(level[y][x]);
            }
            levelString.append("\n");
        }

        // Return the string representation of the generated level
        return levelString.toString();
    }

    private int[][] generateColdThemeLevel() {
    int[][] level = new int[HEIGHT][WIDTH];

    // Place snow-covered ground tiles
    for (int x = 0; x < WIDTH; x++) {
        for (int y = HEIGHT - GROUND_HEIGHT; y < HEIGHT; y++) {
            level[y][x] = 5;  // 5 represents snow-covered ground
        }
    }

    // Place ice blocks
    Random random = new Random();
    for (int i = 0; i < NUM_ICE_BLOCKS; i++) {
        int iceX = random.nextInt(WIDTH - 3);
        int iceY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);
        level[iceY][iceX] = 6;  // 6 represents ice block
    }

    // Place platforms
    for (int i = 0; i < NUM_PLATFORMS; i++) {
        int platformWidth = random.nextInt(10) + 5;
        int platformHeight = random.nextInt(3) + 1;
        int platformX = random.nextInt(WIDTH - platformWidth);
        int platformY = random.nextInt(HEIGHT - GROUND_HEIGHT - platformHeight);

        // Fill the entire bottom row with snow-covered ground
        for (int x = 0; x < WIDTH; x++) {
            level[HEIGHT - GROUND_HEIGHT - 1][x] = 5;
        }

        // Place the platform above the snow-covered ground
        for (int x = platformX; x < platformX + platformWidth; x++) {
            for (int y = platformY; y < platformY + platformHeight; y++) {
                level[y][x] = 0;  // 0 represents an empty space above the ground
            }
        }
    }

    // Place coins
    for (int i = 0; i < NUM_COINS; i++) {
        int coinX = random.nextInt(WIDTH - 2);
        int coinY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);
        level[coinY][coinX] = 7;  // 7 represents a coin
    }

    return level;
    }
}