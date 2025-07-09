# Web Automation Gauge Default


Default Web framework'üdür. Yeni projelere adaptasyon için aşağıdaki adımları takip ederek kurulum yapabilirsiniz.


## Default Framework'ün Klonlanması

Projeyi IntelliJ terminali üzerinden aşağıdaki gibi klonlayabilirsiniz.

```
  git clone http://gitlab.virgosol.com/automation/templates/web-automation-gauge-default.git
```
Ayrıca sadece linki kopyalayarak `File -> New -> Project From Version Control` menüsü aracılığı ile de klonlayabilirisiniz.
```
  http://gitlab.virgosol.com/automation/templates/web-automation-gauge-default.git
```

### Klonlama işlemini tamamladıktan sonra proje dizini aşağıdaki gibi olmalıdır.

<img src="https://i.ibb.co/bPvWHV6/Screenshot-2023-01-18-at-15-53-49.png" width="250" alt="Directory Picture"/>

### Proje dizininde kullanacağımız alanların açıklamaları:

* `specs`: Gauge ile BDD yaklaşımın uygulanadığı alandır.  

<img src="https://i.ibb.co/rmsn6Qz/specs.png" alt="specs" width="250">

* Bu dizin altında bulunan `Concepts` klasörüne .cpt dosyaları, `Scenarios` klasörüne ise .spec dosyaları yazılmalıdır. Dizin içerisinde `example.cpt` ve `example.spec` dosyalarının bulabilirsiniz.
* Concept ve Scenario içine yazılan `.cpt` ve `.spec` dosyalarını aşağıdaki gibi Page Object Model (POM) yapısında oluşturabilirsiniz.

<img src="https://i.ibb.co/F4cNQT5/pom.png" alt="pom" width="250">

* `src`: `java` dizini altında Step Implementation class'ı bulunur. `resources` altında ise projeyi yönettiğimiz konfigürasyonları ayarlarız.

<img src="https://i.ibb.co/FWhwRrd/src.png" alt="src" width="250">

* `StepImpl`: Library'de yazılan generic methodlar dışında custom methodlar yazmak için kullanılır.
  * `Library'de yazılan methodlara nasıl ulaşırım?`
  
Arka plandaki methodları `<key> li` yada `li` yazarak ulaşabilirsiniz. 
  
<img src="https://i.ibb.co/B4fkhhL/Screenshot-2023-01-18-at-16-26-29.png" alt="methods" width="500">

* `resources`: Projemizi yöneteceğimiz kısımdır.

  <img src="https://i.ibb.co/F4jdvHp/resources.png" alt="resources" width="250">

  * `element-infos`: Locatorları `key`-`value`-`type` formatında tuttuğumuz json dosyalarının bulunduğu yerdir. Duplicate `key` yapısına izin vermez.
  
    <img src="https://i.ibb.co/rdsP2Pg/elements.png" alt="elements" width="500">
  * `client-secrets.json`: Bu alanda değişiklik yapılmayacaktır. Entegrasyon parametreleri kullanılmaktadır.
  * `logback.groovy`: Bu alanda değişiklik yapılmayacaktır. Log düzenleme parametreleri kullanılmaktadır.
  * `qa-web.yaml`: Aşağıda örnek bir `qa-web.yaml` şablonu bulunmaktadır.
  
    - browserType=`Chrome`      
    - headless=`true`
    - headlessWindowSize=`1920,1080`
    - #hubUrl=`https://yasindeer_MsNSro:vxjLt5qxLyNaXDX3eg8K@hub-cloud.browserstack.com/wd/hub`
    - testUrl=`http://cloudarchive.test.biscozum.com.tr/signin/noCaptcha`
    - pageTimeOut=`50`
    - scriptTimeOut=`50`
    - implicitlyTimeOut=`15`
    - pollingTime=`250`
    - explicitTimeOut=`30`
    - waitPageLoad=`true`
    - waitAjax=`true`
    - waitAngular=`false`
    - disableTimeoutException=`false`
    - disableQuitDriver=`false`
    
`Açıklamaları`
---------------------------
    - browserType = Chrome, Firefox, Edge tarayıcıları seçilebilir.      
    - headless = Headless koşumlar için kullanılır.(Headless = true --> headless koşum yapılacak anlamındadır)
    - headlessWindowSize = Headless koşumlar default olarak 800,600 de koşarlar. Ekran boyutunu custom olarak ayarlayabilirsiniz.
    - hubUrl = Remote koşumlar yapmak için hub adresi tanımlanacaktır.
    - testUrl = Test yapılacak ortamın adresi tanımlanacaktır.
    - pageTimeOut = Sayfa yüklenme zamanı sn cinsinden girilecektir.
    - scriptTimeOut = Scriptlerin cevap verdiği zamanı sn cinsinden girilecektir.
    - pollingTime = Yoklama zamanının girileceği alandır. ms cinsinden girilecektir.
    - explicitTimeOut = Explict bekleme zamanı sn cinsinden girilecektir.
    - waitPageLoad= Sayfanın tam yüklenmesini belirttiğimiz alandır.?
    - waitAjax = ?
    - waitAngular = ?
    - disableTimeoutException = ?
    - disableQuitDriver = Session sonlandırmasının belirlendiği kısımdır.
-----------------------------------------

  * `remote-settings.yaml`: 
BrowserStack, SauceLabs, LambdaTest gibi remote ortamlarda koşumların capability'lerinin belirlenmesinde kullanılır. 
Hangi platform kullanılacaksa  o platforma ait `use` parametresi `true` olmalıdır.

   - platforms:
     - type: browserstack
     - use: true
     - options:
       - os: Windows
       - osVersion: 11
       - projectName: Test
       - buildName: Test
       - sessionName: Test
       - local: false
       - video: true
       - debug: true
       - consoleLogs: info
       - networkLogs: true
       - seleniumVersion: 4.6.0
       - seleniumLogs: false
       - telemetryLogs: false
     - capabilities:
       - browserName: Firefox
       - browserVersion: 108

 * `report.yaml`: Mail, Klov ve raporlama işlemlerinin konfigürasyonlarının ayarlandığı kısımdır.

- reportDirectory: target/Virgosol_Report_%rp_%date
- reportFileName: slnarchReport_%rp_%date
- disableReport: false
- sendEmail: true
- recordVideoFail: false
- recordVideoPass: false
- takeScreenShotFail: true
- takeScreenShotPass: true
- beforeDeleteEachTestResult: true
- afterDeleteEachTestResult: false
- deleteZipEachTestResult: true
- appendExistingReport: true
- host: smtp.gmail.com
- port: 465
- auth: true
- username: qa.report@virgosol.com
- password: sPCsMms6Xbh2CFX
- mailType: SSL
- from: qa.report@virgosol.com
- to: furkan.ovut@virgosol.com,yasin.deger@virgosol.com
- uploadDrive: false
- driveShareTo:
- addAttachment: false
- subject: Web Automation ${date} ${time}
- message: html
- useKlov: true
- klovProject: Web Automation
- #klovMongoUri : mongodb://vrgadmin:vrgmng@135.181.73.110:21325
- #klovHost: http://135.181.73.110:8982
- klovMongoUri: mongodb://vrgadmin:vrgmng21**@localhost:21325
- klovHost: http://localhost:8092
- useQAMaster: false
- qaMasterUrl: http://135.181.73.110:7911
- qaMasterUser: klov
- qaMasterPass: vrg_klov

`Açıklamaları:`
----------------
* `disableReport:`  true olduğu durumda rapor oluşturmayacak şekilde düzenlenmiştir.
* `sendEmail:` true olduğunda `to` alanında bulunan mail adreslerine test sonuçlarını mail olarak gönderecektir.
* `recordVideoFail:` ve `recordVideoPass:` fail veya pass duruna göre video kaydı alacak şekilde düzenlenmiştir. (Masaüstü kaydı aldığı için headless modda video kaydı almaz.)
* `takeScreenShotFail:` ve `takeScreenShotPass:` fail veya pass duruna göre video kaydı alacak şekilde düzenlenmiştir.
* `useKlov:` Klov raporu oluşturması için true olmalıdır.
* `userQAMaster:` QA Master uygulamasını kullanmak için true olmalıdır.

----------------


 * `values.json` : Projede kullanılacak Kullanıcı Adı, Şifre gibi alanlarda kullanılacak parametrelerin yönetildiği alandır.

