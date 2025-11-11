package Entities;

import Strategies.BestFitStrategy;
import Strategies.CircularFitStrategy;
import Strategies.FirstFitStrategy;
import Strategies.WorstFitStrategy;

public class MMUFactory {
    public static MMU create(Memory memory, String strategyName) {
        return switch (strategyName.toLowerCase()) {
            case "first-fit" -> new MMU(memory, new FirstFitStrategy());
            case "best-fit" -> new MMU(memory, new BestFitStrategy());
            case "worst-fit" -> new MMU(memory, new WorstFitStrategy());
            case "circular-fit" -> new MMU(memory, new CircularFitStrategy());
            default -> throw new IllegalArgumentException("Unknown strategy: " + strategyName);
        };
    }

}
