package com.vttish.minescrap.generator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class GeneratorMain {
    public static void main(String[] args) {
        Path sourceRoot = Paths.get("src/main/java");
        String packageName = "com.vttish.minescrap.api.entity";

        RegistryClassGenerator generator = new RegistryClassGenerator(sourceRoot, packageName);

        System.out.println("Starting generation...");
        generator.generate("EntityType", Set.of("player", "zombie", "skeleton"));
        System.out.println("Generation finished successfully!");
    }
}
