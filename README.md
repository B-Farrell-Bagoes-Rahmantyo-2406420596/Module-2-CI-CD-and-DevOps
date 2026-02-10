# Exercise 1
## Refleksi
Pada exercise 1 ini, saya belajar cukup banyak mengenai clean coding dan struktur Spring Web.  
Saya mengusahakan menerapkan prinsip-prinsip clean code seperti penamaan variable yang jelas, 
penamaan function yang to the point, membuat satu function mengerjakan satu hal.  
Untuk secure coding, saya tidak mengimplementasikan secara sadar, basisnya hanya mengextend tutorialnya. 
Jika dari dependencies thymeleaf sudah menyediakan hal seperti input validation, sterialize input atau bisa saja
terdapat prinsip-prinsip yang saya tidak sadar ternyata telah diterapkan.  
Dan terakhir, saya rasa untuk fungsi mengenai delete/edit saya rasa belum cukup optimal. Selain itu, saya tidak menghandle jika returnnya null.  

# Exercise 2
## Refleksi
Ada rasa kepuasan melihat unit test saya hijau semua. Menurut saya, jumlah unit test yang perlu dilakukan itu tidak pasti.  
Membuat unit test bukan masalah kuantitas, melainkan kualitas test yang menguji kode yang telah dibuat. Begitu juga dengan coveragenya, 
coverage 100% tidak menandakan kode kita sempurna atau tidak bermasalah, bisa saja ada skenario yang belum dipikirkan untuk diujikan dan sebagainya.  
  
Jika setupnya mirip dan ada beberapa kode yang dapat diulang, bisa saja kita membuat sebuah class yang menyimpan variable-variable yang dibutuhkan 
untuk menghilangkan redundasi pada penulisan kode kita. Masalah-masalah yang dapat ditimbulkan seperti jika test kita sudah cukup banyak, 
kita makin sulit membaca kode yang semakin panjang dan kompleks dan artinya satu per satu. Walaupun saya sendiri, tidak menerapkan ini pada exercise 2.




