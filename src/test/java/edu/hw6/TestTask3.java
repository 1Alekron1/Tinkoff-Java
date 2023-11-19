package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AbstractFilter Test")
class TestTask3 {

    private static final Path TEST_DIRECTORY = Paths.get("./test_directory");

    @BeforeEach
    void setUp() throws IOException {
        // Создаем тестовую директорию и несколько тестовых файлов
        Files.createDirectory(TEST_DIRECTORY);
        Files.write(TEST_DIRECTORY.resolve("file.txt"), "TEST".getBytes());
        Files.write(TEST_DIRECTORY.resolve("image.png"), new byte[] {0x76, 0x50, 0x4E, 0x47});
    }

    @AfterEach
    void tearDown() throws IOException {
        // Удаляем тестовую директорию и все ее содержимое после всех тестов
        Files.walk(TEST_DIRECTORY)
            .sorted(Comparator.reverseOrder())
            .map(Path::toFile)
            .forEach(File::delete);
    }

    @Test
    @DisplayName("Regular File Filter")
    void regularFileFilter() throws IOException {
        List<Path> filteredFiles = filterWith(AbstractFilter.regularFile());
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt"),
            TEST_DIRECTORY.resolve("image.png")
        );
    }

    @Test
    @DisplayName("Readable File Filter")
    void readableFileFilter() throws IOException {
        // Assuming all files created are readable by default
        List<Path> filteredFiles = filterWith(AbstractFilter.readable());
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt"),
            TEST_DIRECTORY.resolve("image.png")
        );
    }

    @Test
    @DisplayName("Size Greater Than Filter")
    void sizeGreaterThanFilter() throws IOException {
        List<Path> filteredFiles = filterWith(AbstractFilter.sizeGreaterThan(-1));
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt"),
            TEST_DIRECTORY.resolve("image.png")
        );
    }

    @Test
    @DisplayName("Has Extension Filter")
    void hasExtensionFilter() throws IOException {
        List<Path> filteredFiles = filterWith(AbstractFilter.hasExtension("png"));
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("image.png")
        );
    }

    @Test
    @DisplayName("Regex Matches Filter")
    void regexMatchesFilter() throws IOException {
        List<Path> filteredFiles = filterWith(AbstractFilter.regexMatches(".*\\.txt"));
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt")
        );
    }

    @Test
    @DisplayName("Magic Number Filter")
    void magicNumberFilter() throws IOException {
        // Assuming magic number for a text file is not present in the image
        List<Path> filteredFiles =
            filterWith(AbstractFilter.magicNumber((byte) 'T', (byte) 'E', (byte) 'S', (byte) 'T'));
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt")
        );
    }

    @Test
    @DisplayName("Glob Matches Filter")
    void globMatchesFilter() throws IOException {
        List<Path> filteredFiles = filterWith(AbstractFilter.globMatches("*.txt"));
        assertThat(filteredFiles).containsExactlyInAnyOrder(
            TEST_DIRECTORY.resolve("file.txt")
        );
    }

    private List<Path> filterWith(AbstractFilter filter) throws IOException {
        List<Path> filteredFiles = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIRECTORY, filter::accept)) {
            entries.forEach(filteredFiles::add);
        }
        return filteredFiles;
    }
}
