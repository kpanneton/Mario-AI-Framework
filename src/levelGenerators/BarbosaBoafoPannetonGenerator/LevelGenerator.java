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
    private static final int NUM_PLATFORMS = 3;  // Reduce the number of platforms
    private static final int NUM_ICE_BLOCKS = 2;  // Reduce the number of ice blocks
    private static final int NUM_COINS = 5;
    private static final int SNOW_GROUND = 5;
    private static final int ICE_BLOCK = 6;
    private static final int EMPTY_SPACE = 0;
    private static final int COIN = 7;

    @Override
    public String getGeneratorName() {
        return "BarbosaBoafoPannetonGenerator";
    }

    @Override
    public String getGeneratedLevel(MarioLevelModel model, MarioTimer timer) {
        int[][] level = generateColdThemeLevel();

        // Map integer values to their corresponding symbols
        char[] symbols = {'#', ' ', ' ', ' ', ' ', ' ', 'I', 'C'};

        // Convert the 2D array to a string representation
        StringBuilder levelString = new StringBuilder();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                levelString.append(symbols[level[y][x]]);
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
                level[y][x] = SNOW_GROUND;
            }
        }

        // Place starting platform
        int startPlatformWidth = 5;
        int startPlatformHeight = 1;
        int startPlatformX = WIDTH / 2 - startPlatformWidth / 2;
        int startPlatformY = HEIGHT - GROUND_HEIGHT - startPlatformHeight;
        for (int x = startPlatformX; x < startPlatformX + startPlatformWidth; x++) {
            for (int y = startPlatformY; y < startPlatformY + startPlatformHeight; y++) {
                level[y][x] = EMPTY_SPACE;
            }
        }

        // Place ice blocks
        Random random = new Random();
        for (int i = 0; i < NUM_ICE_BLOCKS; i++) {
            int iceX = random.nextInt(WIDTH - 3);
            int iceY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);
            level[iceY][iceX] = ICE_BLOCK;
        }

        // Place platforms
        for (int i = 0; i < NUM_PLATFORMS; i++) {
            int platformWidth = random.nextInt(10) + 5;
            int platformHeight = random.nextInt(3) + 1;
            int platformX = random.nextInt(WIDTH - platformWidth);
            int platformY = random.nextInt(HEIGHT - GROUND_HEIGHT - platformHeight);

            // Place the platform above the snow-covered ground
            for (int x = platformX; x < platformX + platformWidth; x++) {
                for (int y = platformY; y < platformY + platformHeight; y++) {
                    level[y][x] = EMPTY_SPACE;
                }
            }
        }

        // Place coins
        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = random.nextInt(WIDTH - 2);
            int coinY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);
            level[coinY][coinX] = COIN;
        }

        return level;
    }
}
