# Java Blackjack

<!-- ![alt text](https://i.gyazo.com/3a1507e557e118fda349d7a4990234d0.png "Kuvakaappaus pelistä")-->

## Asennus
Peliä pääsee pelaamaan joko kloonaamalla repon omalle ohjelmointiympäristölle ja suorittamalla ohjelma sitä kautta
tai lataamalla ja suorittamalla Blackjack.exe-tiedosto
[latauslinkistä](https://github.com/mabenj/Blackjack-Java/raw/master/Blackjack.exe "Blackjack.exe").


## Miten pelata
Aluksi peli kysyy käyttäjältä nimeä. Tämän jälkeen aukeaa itse pelin ikkuna, jonka alaosassa sijaitsee pelaamiseen tarvittavat napit.

Ennen kuin pelaajalle on mahdollista jakaa kortit, on pelaajan asetettava ja vahvistettava jokin panos. Panosta voi korottaa vasemman
alakulman napeista. 

![alt text](https://i.gyazo.com/fa2180981eabbec34b6486039a60e384.png "Korota panosta klikkaamalla näitä nappeja")

Kun panos on sopiva, täytyy panos vielä vahvistaa painamalla `Confirm Bet` / `Vahvista panos` -nappia.

Panoksen voi nollata painamalla `Clear Bet` / `Tyhjennä panos` -nappia.

![alt text](https://i.gyazo.com/596b17284fb05c4ba22be075ac53be64.png "Vahvista tai tyhjennä panos klikkaamalla näitä nappeja")

Jos panos on vahvistettu, pelaajalle voidaan jakaa kortit painamalla `Deal` / `Jaa` -nappia.

`Hit`/ `Anna` -napista pelaaja ottaa yhden kortin lisää.

`Stand` / `Jää` -napista pelaaja siirtää vuoron jakajalle.

![alt text](https://i.gyazo.com/4a037f6ed44f1e8e50b29c3d2b58de2e.png "Jaa, ota lisää tai jää näistä napeista")

Oikean reunan lippuja painamalla voi pelaaja halutessaan vaihtaa käyttöliittymän kieltä.

![alt text](https://i.gyazo.com/a351ba0df68d473176f1335d20345f48.png "Vaihda kieltä näistä napeista")


## Blackjackin säännöt
### Pelin tarkoitus
Blackjackissa pelaajat pyrkivät saamaan korteillaan suuremman summan kuin jakaja. Korttien summan tulee olla mahdollisimman lähelle 21,
mutta yli ei saa mennä - kaikki kädet, joiden summa on yli 21, häviävät automaattisesti.

Pelaaja voittaa, jos pysyy alle 21:n ja saa suuremman summan kuin jakaja tai jos jakaja menee yli 21:n. Jakaja voittaa, jos pelaaja 
menee yli 21:n tai saa suuremman summan kuin pelaaja. Tasatilanteessa (englanniksi "push") pelaaja saa panoksensa takaisin.

#### Korttien arvot:
- numerokortit: sama kuin kortin numero (2-10)
- kuvakortit, paitsi ässä: 10
- ässä: 1 tai 11 pelaajan toiveesta riippuen
Jos pelaajalle jaettujen kahden ensimmäisen kortin arvojen summa on 21 (ts. kymppi tai kuvakortti + ässä), on pelaajalla blackjack. 
Tällä hän voittaa jakajan kaikki muut kädet paitsi jakajan oman blackjackin, jolloin peli menee tasan. Jos pelaajalla on blackjack 
ja jakajalla ei, pelaaja saa panoksensa takaisin voittosuhteella 3:2.

### Ohjeet pelin kulkuun
Pelin alussa pelaaja asettaa panoksen, jonka jälkeen jakaja jakaa pelaajille ja itselleen kaksi korttia. Jakajan omista korteista
toinen on kuvapuoli ylöspäin ja toinen alaspäin. 

Kun aloituskortit on jaettu, toimitaan seuraavalla tavalla:
- mikäli pelaajalla on blackjack ja jakajalla ei, pelaaja voittaa
- mikäli jakajalla on blackjack ja pelaajalla ei, pelaaja häviää
- mikäli pelaajalla ja jakajalla on blackjack molemmilla, on peli tasan (push) ja pelaaja saa panoksensa takaisin*
- mikäli tilanne ei ole yksikään yllä mainitusta kolmesta tilanteesta, peli jatkuu yhdellä alla olevista toiminnoista. 
Ensin toimivat pelaajat ja vasta sitten jakaja.

### Pelaajan toiminnot
Kun pelaajalle on jaettu kaksi ensimmäistä korttia, hänellä on kaksi toimintoa käytössään. Nämä on esitelty alla. 
Suomenkieliset termit on merkitty sulkuihin englanninkielisten termien jälkeen.

**Hit** (anna) - Pelaaja ottaa yhden kortin lisää.

**Stand** (jää) - Pelaaja on tyytyväinen omaan käteensä eikä hän halua lisäkortteja. Vuoro siirtyy jakajalle.


Pelaaja voi jatkaa toimintaansa niin pitkään kunnes hän valitsee standin tai käden arvo ylittää 21. Pelaajan toimittua on jakajan 
vuoro toimia. Mikäli jakajan käsien arvo ylittää 21 tai pelaajan käsi on lähempänä 21:tä, pelaaja voittaa panoksensa kaksinkertaisena
takaisin.

*Lähde: [blackjacksivut.com](http://blackjacksivut.com/blackjack-ohjeet-ja-saannot.html "blackjacksivut.com")*

### Tärkeitä seikkoja tästä blackjack-pelistä:
- peliä pelataan kolmella pakalla
- jakaja nostaa jos korttien summa on 16 tai alle
- jakaja jää jos korttien summa on 17 tai yli
- jos jakajan ensimmäinen kortti on 10, kuvakortti tai ässä, jakaja tarkistaa toisen kortin blackjackin varalta. Jos jakajalla sattuu
olemaan blackjack, jakaja voitaa erän ellei pelaajallakin satu olemaan blackjack
