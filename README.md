# Refleksi 1

### Clean code principle yang sudah diterapkan pada code ini
- Meaningful names <br>
  Penggunaan nama variabel, class, dan method yang sudah jelas pada code ini sehingga code dapat dengan mudah dibaca dan dipahami baik itu saat membuat maupun saat melanjutkan pekerjaan ini. Contohnya pada method add dan delete product.
- Function <br>
  Penggunaan fungsi yang spesifik dapat mempermudah dan memperindah code yang ada. Fungsi yang spesifik dapat mempermudah pekerjaan ingin melakukan pemanggilan fungsi tersebut. Dan juga dapat dipastikan fungsi tersebut singkat, padat dan jelas.
- Objects and datea structure <br>
  Penggunaan tingkat akses dari kelas dan objek lainnya seperti private dapat mencegah pengguna untuk pengaksesan langsung. Dan penggunaan struktur data seperti list digunakan agar data dapat disimpan secara terorganisir.
- Use of Annotations <br>
  Digunakan untuk mengelola depedensi dari spring

### Secure Coding Practice yang sudah diterapkan pada code ini
- Penggunaan UUID pada productID membuatnya menjadi lebih aman 
- Penggunaan Thymeleaf dapat secara otomatis dalam mencegah serangan XSS(Cross-Site Scripting)

### Hal yang harus ditingkatkan
  Penggunaan error handling di setiap celah yang dibutuhkan harus ditingkatkan agar code bisa lebih mudah untuk diperbaiki.

# Refleksi 2

- Setelah membuat dan menguji beberapa unit test saya merasakan bahwa code saya telah dapat dijalankan dengan lancar. Saya telah memikirkan beberapa cara agar semua dapat tercover dalam unit test yang saya buat.
- Unit test dibuat untuk mencover seluruh test yang dapat menguji sistem agar dapat berjalan dengan lancar. Walaupun jika unit test itu sedikit tetapi sudah mencover seluruh celah yang ada itu sudah termasuk cukup.
- Unit test dengan 100% code coverage belum tentu terbebas dari bug, namun apakah code tersebut telah berjalan dengan benar. Jika testcase hanya memanggil fungsi tanpa dilakukan verifikasi bug tetap saja dapat tersembunyi.
- Penambahan test functional baru sebagian besar mirip dengan yang sebelumnya dapat mengurangi kualitas dari test tersebut. Hal ini dapat diselesaikan dengan melakukan refactor baseclassnya. Dengan ini efisiensi dan kualitas dari code dapat menjadi maksimal
