# BankOtomasyonu

PROJE KONUSU:İNTERNET BANKACILIĞI OTOMASYONU
PROJENİN YAZILDIĞI DİLİ:JAVA
PROJEDE KULLANILAN VERİ TABANI:MYSQL 

!Github’a kullanım videosunu koydum isterseniz bakabilirsiniz.
</br>
!Verileri eklemek isterseniz GitHub’a MySQL yedeklerini koyacağım.


1-A)İNTERNET BANKACILIĞINA DUYULAN İHTİYAÇ
İnternet bankacılığı sayesinde, banka şubesine gitmeden, internet erişimi olan bir bilgisayar aracılığıyla birçok bankacılık işlemini yapabilirsiniz. Şubelerdeki kalabalık işlem yükünü azaltan ve personel artışından koruyan, maliyetleri düşüren bir sistemdir. Özellikle güvenlik konusunda alınan son önlemler ile hizmet oldukça güvenli hale getirilmiştir.


1-B)BU ALANDA YAPILMIŞ ÖRNEK UYGULAMA
Ülkemizde banka alanında çalışan Ziraat Bankası, Türkiye İş Bankası, Akbank gibi bankalar mevcut bunların hepsinin ülkemizde otomasyonları mevcut.
Ziraat Bankası 2019 da belirlenen sonuçlara göre Türkiye’de en çok kullanılan banka .Örnek olarak Ziraat Bankasının ekran görüntülerini ve sunduğu hizmetler:
 ![ziraat1](https://user-images.githubusercontent.com/41691766/128638584-873c241d-dd1e-480c-b958-41fb35d14284.png)
 
 
Detaylı bir hesap ara yüzü var her türlü hesabınıza buradan ulaşıp bakabilirsiniz.
![ziraat2](https://user-images.githubusercontent.com/41691766/128638588-ae19a22f-8a8e-401e-b409-097209bc14c9.png)

 
Para transferlerinden birden fazla seçenek bulduruyor böylece kullanıcı istediği işlemi daha rahat yapabiliyor.
![ziraat3](https://user-images.githubusercontent.com/41691766/128638591-e9f90ca7-3b11-48a5-88f0-c8abc1901246.png)
 
 
Ödemeler ekranında ise internet bankacılığın büyük avantajı olan  insanlara kolaylık sağlayıp zaman kayıplarının önüne geçen sistemleri bulunduruyor. Bu ekrandan ülkemizde bulunan birçok şeyin ödemesini rahatlıkla yapılabilir.


2.PROJEYİ OLUŞTURAN BÖLÜMLER

2-A)PROJE AÇIKLAMASI 
Bu projede tüm internet bankacılığının kullandığı çoğu fonksiyonu içerisinde barındıran bir banka otomasyonu. Banka otomasyonunun içinden kullanıcılar kayıt olup giriş yapabilecekler ve kullanıcı bilgileri bir veri tabanında saklanacak, hesaplarındaki mali detaylara bakabilecekler, para çekebilecekler, para yatırabilecekler, borçlarını görebilecekler, veri tabanında kayıtlı olan diğer kullanıcılar arasında havale yapabilecekler, kredi çekmek için faiz hesaplayabilecekler, rastgele şifre oluşturabilecekler  ve Qr kod ile para çekebilecek. 
PROJENİN ÖZELLİKLERİ 
• Müşteri sisteme yeni kayıt olabilir ya da giriş yapabilir. 
• Müşteri yeni şifre oluşturabilir ya da rastgele şifre edinebilir. 
• Müşteri başarılı bir şekilde giriş yapabildiyse eğer para çekme ve para yatırma 
işlemlerini gerçekleştirebilir. 
• Müşteri isterse kredi alabilir, borçlarını ödeyebilir veya başka bir hesaba havale 
yapabilir.
•  Müşteri Qr kod ile para çekebilecek.


2-B)UYGULAMA EKRANLARI

INTRO EKRANI:
Start.java
</br>
![start](https://user-images.githubusercontent.com/41691766/128638594-53dc0a4e-c2e9-4ded-8785-52b3911a4e83.png)
</br>
Uygulamayı başlattığımızda bankanın ismi ve simgesi bulunan bir açılış ekranı başlıyor.


GİRİŞ EKRANI:
LoginAtm.java
Fotoğraf1
</br>
 ![login](https://user-images.githubusercontent.com/41691766/128638596-be3ea117-e9ea-40ed-bac3-6ba63ab292eb.png)
 </br>
Bu ekranda kullanıcı üye ise T.C no’su ve şifresi ile hesabına giriş yapıyor. Eğer kullanıcı şifresini unuttuysa şifremi unuttum ekranına gide biliyor. Kullanıcı yeni üye ol diyerek üye olma ekranına geçebilecek.


ŞİFREMİ UNUTTUM EKRANI:
SifremiUnuttum.java
Fotoğraf2
</br>
![SifremiUnuttum](https://user-images.githubusercontent.com/41691766/128638598-fddaba09-4a57-4abc-a70d-0f213dacfd01.png) 
</br>
Kullanıcı T.C kimlik numarası ve üye olurken belirtiği anne kızlık soyadı ile şifresini tekrar öğrenebilecek.


YENİ ÜYE OL EKRANI:
YeniUye.java
Fotoğraf3
</br>
 ![YeniUye](https://user-images.githubusercontent.com/41691766/128638603-4b54f352-18c9-479b-9ebd-da5cc3ff66e5.png) 
</br>
Bu ekranda kullanıcı kişisel bilgilerini vererek yeni kayıt oluşturabilecek.


HESAP EKRANI:
Hesap.java
Fotoğraf4
</br>
![Hesap](https://user-images.githubusercontent.com/41691766/128638607-3e00ed5c-b318-48ce-838c-b33e7fe9a4c7.png) 
</br>
Bu ekranda kullanıcı sistemde bulunan özellikleri kolayca kullana bilecek.


PARA ÇEKME EKRANI:
ParaCek.java
Fotoğraf5
</br>
 ![ParaCek](https://user-images.githubusercontent.com/41691766/128638609-73c7d161-bf11-4c8c-9357-40e7ce2f4f7f.png)
</br>
Bu ekrandan kullanıcı hesabında olan parayı çekebilecek. Çekilen tutarı sistem hesaptan otomatik olarak düşecek. Hesapta bulunandan fazla çekme durumunda sistem uyarı verecek.


PARA YATİRMA EKRANI:
ParaYatir.java
Fotoğraf6
</br>
![ParaYatir](https://user-images.githubusercontent.com/41691766/128638615-9af71778-a87b-408b-8beb-4f29c01520eb.png) 
</br>
Bu ekranda kullanıcı hesabına para yatırabilir. Mevcut borçlarını ödemek içinde diğer ekrana geçiş yapabilir.


BORÇ ÖDEME EKRANI:
BorcOde.java
Fotoğraf7
</br>
![BorcOde](https://user-images.githubusercontent.com/41691766/128638617-975fee4b-1383-46cc-a5ad-0a7198f9123d.png) 
</br>
Bu ekrandan kullanıcı hesabında olan paradan borç ödemesini yapabilecek.


HAVALE EKRANI:
Havale.java
Fotoğraf8
</br>
![Havale](https://user-images.githubusercontent.com/41691766/128638619-68603f6e-bd3e-4ea0-8e95-cbd62051592b.png) 
</br>
Bu ekrandan kullanıcı istediği bir hesaba mevcut parasından istediği kadar tutarı yollayabilecek. Giden ve gelen para hesaplara otomatik işlenecek.




RANDOM ŞİFRE OLUŞTURMA EKRANI:
Hesap.java
</br>
 ![randomsifre](https://user-images.githubusercontent.com/41691766/128638621-00dd62bd-fd85-413b-83ee-13c0e8c1efb6.png)
</br>
Bu ekran ile kullanıcı banka şifresi için oluşturulan bir şifre görebilecek.

HESAP DETAYLARI EKRANI:
Hesap.java
</br>
 ![HesapDetaylari](https://user-images.githubusercontent.com/41691766/128638622-5b462222-a4b3-43cc-bef5-3fdfe7ac0a0a.png)
</br>
Bu ekran ile kullanıcı tüm bilgilerini görebilecek.

BORÇLARI GÖSTERME EKRANI:
Hesap.java
</br>
 ![BorclariGoster](https://user-images.githubusercontent.com/41691766/128638623-5a1463e7-4606-4d2f-b0b0-4c1088140d50.png)
</br>
Bu ekran ile kullanıcı tüm borçlarını görebilecek.


KREDİ ÇEKME EKRANI:
KrediHesapla.java
Fotoğraf9
</br>
![KrediHesapla](https://user-images.githubusercontent.com/41691766/128638626-1cf51dff-bb6a-45e2-8aae-233893df0fe9.png) 
</br>
Bu ekran ile kullanıcı bankanın belirlediği faiz oranı ile bir kredi çekebilir. Hesapla buttonu ile kendine göre en iyi miktarı bulduktan sonra krediye başvura bilir. Kredi onaylanınca hesabına para yatıyor ve borç kısmında mevcut borçlarına kredi yansıyor.


KULLANICI AYARLARI EKRANI:
KullaniciAyarlari.java
Fotoğraf10
</br>
 ![KullaniciAyarlari](https://user-images.githubusercontent.com/41691766/128638627-40d2b311-2f17-4d89-bd14-b51e95546a0e.png)
</br>
Bu ekran ile kullanıcı eski şifresini değiştirebiliyor.


QR KOD İŞLEM EKRANI:
QrKodIslemleri.java
Fotoğraf11
</br>
 ![QrKodIslemleri](https://user-images.githubusercontent.com/41691766/128638628-b0de3d27-4ed2-4f64-836b-bbe2b815c90c.png)
</br>
Bu ekran ile kullanıcı para çekmek için bir qr kod yaratabilir daha sonra bu qr kodu okut ile oluşan png’nin ismini .png kısmı hariç yazıp para çekebilirsin.
</br>
!C:\\BankQr\\ png bu klasörde oluşacak eğer test etmek isterseniz C diskinde BankQr adlı klasör oluşturmanız yeterli.
</br>
!Oluşan png yerini koddan değiştirmek için QrKodIslemleri.java doyanının 91,103 ci satırlarına o konumu belirtin



2-C)VERİ TASARIMI 

!Verileri eklemek isterseniz GitHub’a MySQL yedeklerini koyacağım.

TABLO TANIMLARI 
KİŞİLER TABLOSU: 
</br>
![VeriTablosu](https://user-images.githubusercontent.com/41691766/128638631-5c3402c9-b46e-421f-9859-e212d7edd702.png)
 </br>
KisiId: Primary key ve auto increment olarak tanımlanmıştır. Bu sayede 
kullanıcıların hepsi artan id değerleri alıp eşsiz olacak. 
Diğer sütunlarda kişiler için gerekli kişisel bilgileri var. 
</br>
Kişiler Tablosundaki Veriler: 
</br>
KisiAd: Kullanıcı adlarını tutan bir veri sütünü Varchar olarak tanımlandı. 
</br>
KisiSoyad: Kullanıcı Soyadlarını tutan bir veri sütunu Varchar olarak tanımlandı. 
</br>
KisiTc: Kullanıcı TC numaralarını tutan bir veri sütunu Varchar olarak tanımlandı. 
</br>
KisiIl: Kullanıcı doğum yerini tutan bir veri sütunu Varchar olarak tanımlandı. 
</br>
KisiAnneSoyad: Kullanıcı anne kızlık soyadını tutan bir veri sütunu Varchar 
olarak tanımlandı. 
</br>
KisiPara: Kullanıcının bakiyesini tutan bir veri sütunu Integer olarak tanımlandı. 
</br>
KisiBorc: Kullanıcının borcunu tutan bir veri sütunu Integer olarak tanımlandı. 
</br>
KisiTelefon: Kullanıcının telefon numarasını tutan bir veri sütunu Varchar olarak 
tanımlandı. 
</br>
KisiSifre: Kullanıcının şifresini tutan bir veri sütunu Varchar olarak tanımlandı. 
</br>
KisiCinsiyet: Kullanıcının cinsiyetini tutan bir veri sütunu Varchar olarak 
tanımlandı.
</br>


3)  UYGULAMANIN KULLANIMI HAKKINDA YARDIM BİLGİLERİ
Uygulamanın Java sınıflarından başlatırken Start.java ile başlatılırsa intro ekranın ile başlar.

Fotoğraf1 de belirtilen giriş ekranında kullanıcı üyeyse 11 haneli T.C kimlik numarası ve 6 haneli şifresi ile giriş yapabilir. Eğer kullanıcı şifresini unuttuysa buradan şifremi unuttum ekranına geçebilir. Giriş yapan kişi eğer üye değilse buradan üye olma formuna geçebilir.

Fotoğraf2 de şifresini unutan müşteri 11 haneli T.C kimlik numarası ve anne kızlık soyadı ile şifresini sorgulatabilir.

Fotoğraf3 de ilk kez giriş yapan müşteriler alanları belirtilen koşullara göre doldurup üye olabilir.

Fotoğraf4 de müşteri hesabından tek tıkla istediği işlemi yapabilmektedir. 

Fotoğraf5 de kullanıcı hesabından para çekebilir. Burada kullanıcın T.C no’su sistem tarafından otomatik girilir müşteri sadece çekmek istediği tutarı giriyor.

Fotoğraf6 da kullanıcı hesabına para yatırabilir ya da mevcut borçlarını ödemek içinde diğer ekrana geçiş yapabilir.

Fotoğraf7 de kullanıcı ödemek istediği borcunun miktarını yazıp ödeyebilir.

Fotoğraf8 de kullanıcı başka bir hesaba havale yapabilir. Bunun için karşı tarafın 11 haneli T.C no’sunu ve hesabındaki mevcut tutarın ne kadarını göndermek istediğini yazmalı.

Fotoğraf 9 da kullanıcı ilk olarak kredi tutarını sonra kaç ay içinde ödemek istediğini seçip hesapla buttonu ile kendisine aylık ödemesini , faiz oranını ve toplam geri ödemesini görebilir ve eğer kullanıcı krediyi isterse başvurabilir. Kredi onaylanınca hesabına para yatıyor ve borç kısmında mevcut borçlarına kredi yansıyor.

Fotoğraf10 da kullanıcı T.C no’ sunu girip koymak istediği yeni şifreyi belirterek şifresini değiştirebilir. Şifre 6 haneli olmalıdır.

Fotoğraf11 de kullanıcı hesabından çekmek istediği tutarı yazıp qr oluştur buttonu ile “C:\\BankQr\\”bu klasörün içinde oluşuyor.
!C:\\BankQr\\ png bu klasörde oluşacak eğer test etmek isterseniz C diskinde BankQr adlı klasör oluşturmanız yeterli.
!Oluşan png yerini koddan değiştirmek için QrKodIslemleri.java doyanının 91,103 ci satırlarına o konumu belirtin
