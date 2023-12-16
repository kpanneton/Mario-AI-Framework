package levelGenerators.BarbosaBoafoPannetonGenerator;

import engine.core.MarioLevelGenerator;
import engine.core.MarioLevelModel;
import engine.core.MarioTimer;

import java.util.Random;

public class LevelGenerator implements MarioLevelGenerator {

    // Constants for the level generation parameters
    private static final int WIDTH = 100;
    private static final int HEIGHT = 15; // Adjusted the height to provide more space
    private static final int GROUND_HEIGHT = 2;
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
        int totalBlocks = 50; // Adjust the total number of blocks as needed

        // Place spaced out blocks starting from the bottom
        Random random = new Random();
        for (int y = HEIGHT - GROUND_HEIGHT - 1; y >= 0; y--) {
            for (int i = 0; i < totalBlocks; i++) {
                int blockX = random.nextInt(WIDTH);

                // Check if the space is empty before placing the block
                if (level[y][blockX] == EMPTY_SPACE) {
                    level[y][blockX] = SNOW_GROUND;
                } else {
                    // If the space is not empty, try placing the block elsewhere
                    i--;
                }
            }
        }

        // Place starting platform
        int startPlatformWidth = 5;
        int startPlatformHeight = 1;
        int startPlatformX = WIDTH / 4 - startPlatformWidth / 2;  // Adjusted the position
        int startPlatformY = HEIGHT - GROUND_HEIGHT - startPlatformHeight;
        for (int x = startPlatformX; x < startPlatformX + startPlatformWidth; x++) {
            for (int y = startPlatformY; y < HEIGHT; y++) {
                level[y][x] = EMPTY_SPACE;
            }
        }

        // Place ice blocks
        int numIceBlocks = 2; // Adjust the number of ice blocks as needed
        for (int i = 0; i < numIceBlocks; i++) {
            int iceX = random.nextInt(WIDTH - 3);
            int iceY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);

            // Check if the space is empty before placing the ice block
            if (level[iceY][iceX] == EMPTY_SPACE) {
                level[iceY][iceX] = ICE_BLOCK;
            } else {
                // If the space is not empty, try placing the ice block elsewhere
                i--;
            }
        }

        // Place coins
        int numCoins = 5; // Adjust the number of coins as needed
        for (int i = 0; i < numCoins; i++) {
            int coinX = random.nextInt(WIDTH - 2);
            int coinY = random.nextInt(HEIGHT - GROUND_HEIGHT - 1);

            // Check if the space is empty before placing the coin
            if (level[coinY][coinX] == EMPTY_SPACE) {
                level[coinY][coinX] = COIN;
            } else {
                // If the space is not empty, try placing the coin elsewhere
                i--;
            }
        }

        return level;
    }
}


