package com.vttish.minescrap.generator;

import com.vttish.minescrap.api.common.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class RegistryClassGenerator {
    private final Path basePath;
    private final String packageName;

    private static final String CLASS_TEMPLATE = """
            package %s;
            
            import com.vttish.minescrap.api.common.Identifier;
            
            /**
            * AUTO-GENERATED CLASS - DO NOT EDIT MANUALLY.
            * Run dedicated generator to update this file.
            */
            public final class %s {
                private %s() {
                    throw new UnsupportedOperationException();
                }
            
            %s
            }
            """;

    public RegistryClassGenerator(Path sourceRoot, String packageName) {
        String packagePath = packageName.replace('.', '/');

        basePath = sourceRoot.resolve(packagePath);
        this.packageName = packageName;
    }

    public void generate(String className, Set<String> identifiers) {
        String fields = identifiers.stream()
                .map(this::createField)
                .collect(Collectors.joining("\n"));

        String javaCode = String.format(CLASS_TEMPLATE, packageName, className, className, fields);
        Path outputPath = basePath.resolve(className + ".java");

        try {
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, javaCode);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String createField(String identifier) {
        String value;

        if (identifier.startsWith(Identifier.DEFAULT_NAMESPACE + ":")) {
            value = identifier.substring(Identifier.DEFAULT_NAMESPACE.length() + 1);
        } else {
            value = identifier;
        }

        return String.format(
                "\tpublic static final Identifier %s = Identifier.of(Identifier.DEFAULT_NAMESPACE, \"%s\");",
                value.toUpperCase(),
                value
        );
    }
}
