# Modele 4:

## Refleksi
> Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Berdasarkan pertanyaan reflektif yang diajukan oleh Percival (2017) dalam submodul “Principles and Best Practice of Testing” pada bab “Evaluating Your Testing Objectives”, saya menilai bahwa alur Test-Driven Development (TDD) yang telah diterapkan cukup membantu dalam mengembangkan fitur pembayaran.

Namun, ada aspek dapat ditingkatkan, yaitu Kelengkapan Skenario Pengujian – Saat ini, pengujian yang dibuat sudah mencakup beberapa aspek utama, seperti menambah pembayaran, mengubah status pembayaran, dan mengambil data pembayaran berdasarkan ID. Namun, cakupan pengujian masih bisa ditingkatkan dengan menambahkan skenario negatif lainnya, seperti ketika metode pembayaran tidak valid atau ketika data pembayaran tidak lengkap.
>Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.

Fast – Pengujian yang dibuat cukup cepat karena hanya berfokus pada unit kecil dari sistem.
Independent– Beberapa pengujian masih memiliki sedikit ketergantunganHal ini bisa diperbaiki dengan lebih banyak menggunakan mocking atau pembuatan data yang lebih modular.
Repeatable – Pengujian bisa diulang, tetapi harus dipastikan bahwa data yang digunakan selalu dalam keadaan yang sama setiap kali pengujian dijalankan.
Self-Validating – Pengujian sudah cukup jelas dalam memberikan hasil validasi, tetapi bisa lebihlagi dengan menambahkan pesan atau log yang lebih informatif dalam kasus kegagalan.
Timely  – Jika menggunakan pendekatan TDD secara ketat, maka pengujian seharusnya dibuat sebelum implementasi kode, bukan setelahnya. Hal ini bisa lebih diperhatikan di iterasi berikutnya.

# Module 3 : Maintainability & OO Principles-v2

## Refleksi

Explain what principles you apply to your project! <br>
Prinsip solid yang digunakan:
- Single Responsibility Principle - Memisahkan CarController dengan Product Controller. Kedua itu memiliki fungsi yang berbeda sehingga harus di pisahkan
- Open Closed Principle - Menggunakan interface CarService sehingga implementasi dapat diganti untuk memperluas tanpa mengubah kode yang sudah ada
- Liskov Substitution Principle - CarServiceImpl mengimplementasikan CarService dengan benar, sehingga bisa digunakan sebagai pengganti CarService tanpa masalah.
- Interface Segregation Principle - CarService hanya berisi metode yang diperlukan untuk operasi mobil
- Dependency Inversion Principle - Modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah. Keduanya harus bergantung pada abstraksi. CarController bergantung pada CarService (abstraksi), bukan CarServiceImpl

Explain the advantages of applying SOLID principles to your project with examples.
ode menjadi lebih mudah dipahami, diuji, dan diperbaiki karena setiap kelas hanya memiliki satu tanggung jawab utama. Kita bisa menambahkan fungsi dengan inheritance atau polymorphism. Setiap kelas mengimplementasikan interface yang benar-benar dibutuhkan, sehingga tidak ada metode yang tidak digunakan.

Explain the disadvantages of not applying SOLID principles to your project with examples.
Kesulitan dalam Maintenance, Kesulitan dalam Testing, menguji CarController akan sulit karena kita harus menginisialisasi CarServiceImpl dan semua dependency-nya. Kode menjadi kaku, sulit diuji, dan rentan terhadap bug.


# Module 2 : CI/CD & DevOps

## Refleksi 

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
- Unused import <br>
  Import yang tidak digunakan memperberat kode dalam membacanya. Sehingga saya menghapus import yang tidak diperlukan agar kode menjadi lebih rapi dan bersih.
- Dokumentasi pada method kosong
  Method kosong yang tanpa dokumentasi dapat membingungkan pembaca, kegunaan dari method tersebut. Sehingga saya menambahkan komentar yang jelas pada method tersebut.
- Unnecessary Public Modifier
  Penggunaan modifier public tidak dibutuhkan pada interface, sehingga saya menghapus modifier publik agar mengikuti best practicenya.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Ya, saya sudah menerapkan implementasi dari CI/CD. Berkas yang terdapat dalam .github/workflows merupakan berkas yang mencakup proses CI. Setiap perubahan code yang ada pada repository akan diuji otomatis oleh github action untuk menjalankan unit test dan menganalisis code tersebut (ci.yml). Kemudian, code tersebut akan dilakukan scanning untuk pengecekean keamanan dan kualitas code menggunakan OSSF Scorecard (scorecard.yml) dan PMD (pmd.yml). Proses ini memastikan benaran dari code yang masuk ke repository.
Setelah Build berhasil, akan terjadi proses CD yaitu ia akan otomatis dideploy menggunakan DockerFile ke Koyeb. Deploy otomatis ini, memastikan bahwa setiap perubahan akan selalu tersedia tanpa dilakukan secara manual. Hal ini, tentu akan meningkatkan produktivitas dan efisiensi waktu karena dilakukan secara otomatis dan cepat.



# Module 1 : Coding Standards
## Refleksi 1

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

## Refleksi 2

- Setelah membuat dan menguji beberapa unit test saya merasakan bahwa code saya telah dapat dijalankan dengan lancar. Saya telah memikirkan beberapa cara agar semua dapat tercover dalam unit test yang saya buat.
- Unit test dibuat untuk mencover seluruh test yang dapat menguji sistem agar dapat berjalan dengan lancar. Walaupun jika unit test itu sedikit tetapi sudah mencover seluruh celah yang ada itu sudah termasuk cukup.
- Unit test dengan 100% code coverage belum tentu terbebas dari bug, namun apakah code tersebut telah berjalan dengan benar. Jika testcase hanya memanggil fungsi tanpa dilakukan verifikasi bug tetap saja dapat tersembunyi.
- Penambahan test functional baru sebagian besar mirip dengan yang sebelumnya dapat mengurangi kualitas dari test tersebut. Hal ini dapat diselesaikan dengan melakukan refactor baseclassnya. Dengan ini efisiensi dan kualitas dari code dapat menjadi maksimal
