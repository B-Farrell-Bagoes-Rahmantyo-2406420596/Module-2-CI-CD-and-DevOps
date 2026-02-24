## Deployment Link
https://e-shop-rekkin-d55034d8dfdd.herokuapp.com/

# Refleksi 1 - Module 1
---
Pada exercise 1 ini, saya belajar cukup banyak mengenai clean coding dan struktur Spring Web.  
Saya mengusahakan menerapkan prinsip-prinsip clean code seperti penamaan variable yang jelas, 
penamaan function yang to the point, membuat satu function mengerjakan satu hal.  
Untuk secure coding, saya tidak mengimplementasikan secara sadar, basisnya hanya mengextend tutorialnya. 
Jika dari dependencies thymeleaf sudah menyediakan hal seperti input validation, sterialize input atau bisa saja
terdapat prinsip-prinsip yang saya tidak sadar ternyata telah diterapkan.  
Dan terakhir, saya rasa untuk fungsi mengenai delete/edit saya rasa belum cukup optimal. Selain itu, saya tidak menghandle jika returnnya null.  

# Refleksi 2 - Module 1
---
Ada rasa kepuasan melihat unit test saya hijau semua. Menurut saya, jumlah unit test yang perlu dilakukan itu tidak pasti.  
Membuat unit test bukan masalah kuantitas, melainkan kualitas test yang menguji kode yang telah dibuat. Begitu juga dengan coveragenya, 
coverage 100% tidak menandakan kode kita sempurna atau tidak bermasalah, bisa saja ada skenario yang belum dipikirkan untuk diujikan dan sebagainya.  
  
Jika setupnya mirip dan ada beberapa kode yang dapat diulang, bisa saja kita membuat sebuah class yang menyimpan variable-variable yang dibutuhkan 
untuk menghilangkan redundasi pada penulisan kode kita. Masalah-masalah yang dapat ditimbulkan seperti jika test kita sudah cukup banyak, 
kita makin sulit membaca kode yang semakin panjang dan kompleks dan artinya satu per satu. Walaupun saya sendiri, tidak menerapkan ini pada exercise 2.

# Refleksi 1 - Module 2
---
Untuk mengimprove code quality:
- Satu-satunya yang saya update dari module 1 adalah menambahkan null handling pada product controller
- Selain itu, code coverage sudah saya improve hingga 100%
- Saya juga merefactor filename untuk menyesuaikan dengan code controller
- Saya membereskan beberapa warning unused parameter/variable walau mungkin belum semua

Selain itu CI yang sudah saya implement meliputi :
- Gradlew Linter Test
- SonarQube Test
- OSSF Scorecard

Linter test menghandle unit test dan functional test, Sonar menghandle code quality, 
OSSF Scorecard menilai security walau saya ambil templatenya jadi banyak security belum aman, tetapi tetap lolos. 
Mungkin Config OSSF perlu diperketat dan juga saya rasa CI yang membantu proses PR juga dapat membantu.