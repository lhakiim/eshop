package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // Metode ini digunakan untuk menguji apakah konteks aplikasi Spring Boot dapat dimuat dengan benar.
        // Jika konteks gagal dimuat, maka pengujian ini akan gagal.
    }

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }
}
