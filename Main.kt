package com.example

import kotlinx.serialization.json.Json

fun konwertujMase(masa: String): Double {
    return if (masa.endsWith("g")) {
        val masaPure = masa.removeSuffix("g").replace(",",".").toDouble()
        masaPure / 28.35

    } else if (masa.endsWith("ct")) {
        val masaPure = masa.removeSuffix("ct").replace(",",".").toDouble()
        masaPure / 141.75
    }

    else {
        throw IllegalArgumentException("Nieznana jednostka")
    }
}

fun odczytajDane(): Pair<List<Obiekt>, List<Kategoria>> {

    val kategorieJson = """[
  {
    "Typ": "Diament",
    "Wartość za uncję (USD)": 8000,
    "Czystość": "SI"
  },
  {
    "Typ": "Diament",
    "Wartość za uncję (USD)": 12000,
    "Czystość": "VVS"
  },
  {
    "Typ": "Diament",
    "Wartość za uncję (USD)": 15000,
    "Czystość": "IF"
  },
  {
    "Typ": "Rubin",
    "Wartość za uncję (USD)": 5000,
    "Czystość": "S"
  },
  {
    "Typ": "Rubin",
    "Wartość za uncję (USD)": 8000,
    "Czystość": "VS"
  },
  {
    "Typ": "Rubin",
    "Wartość za uncję (USD)": 12000,
    "Czystość": "IF"
  },
  {
    "Typ": "Złoto",
    "Wartość za uncję (USD)": 1900,
    "Czystość": "999"
  },
  {
    "Typ": "Złoto",
    "Wartość za uncję (USD)": 1800,
    "Czystość": "950"
  },
  {
    "Typ": "Złoto",
    "Wartość za uncję (USD)": 1600,
    "Czystość": "585"
  },
  {
    "Typ": "Srebro",
    "Wartość za uncję (USD)": 25,
    "Czystość": "999"
  },
  {
    "Typ": "Srebro",
    "Wartość za uncję (USD)": 22,
    "Czystość": "925"
  },
  {
    "Typ": "Szafir",
    "Wartość za uncję (USD)": 4000,
    "Czystość": "S"
  },
  {
    "Typ": "Szafir",
    "Wartość za uncję (USD)": 6000,
    "Czystość": "VS"
  },
  {
    "Typ": "Szmaragd",
    "Wartość za uncję (USD)": 7000,
    "Czystość": "VS"
  },
  {
    "Typ": "Szmaragd",
    "Wartość za uncję (USD)": 10000,
    "Czystość": "IF"
  },
  {
    "Typ": "Opal",
    "Wartość za uncję (USD)": 3000,
    "Czystość": "S"
  },
  {
    "Typ": "Opal",
    "Wartość za uncję (USD)": 5000,
    "Czystość": "VS"
  },
  {
    "Typ": "Platyna",
    "Wartość za uncję (USD)": 1200,
    "Czystość": "999"
  },
  {
    "Typ": "Platyna",
    "Wartość za uncję (USD)": 1100,
    "Czystość": "950"
  }
]"""
    val zbiorWejsciowyJson = """[
  {
    "Typ": "Diament",
    "Masa": "0,5ct",
    "Czystość": "SI",
    "Barwa": "Biała",
    "Pochodzenie": "Afganistan",
    "Właściciel": "Jan Kobolski"
  },
  {
    "Typ": "Rubin",
    "Masa": "1ct",
    "Czystość": "S",
    "Barwa": "Różowa",
    "Pochodzenie": "Chiny",
    "Właściciel": "Demokratyczny Rząd Partii Ludowej Chin"
  },
  {
    "Typ": "Złoto",
    "Masa": "2,38g",
    "Czystość": "535",
    "Barwa": "Złota",
    "Pochodzenie": "Polska",
    "Właściciel": "Parta"
  },
  {
    "Typ": "Szafir",
    "Masa": "0,8ct",
    "Czystość": "VS",
    "Barwa": "Niebieska",
    "Pochodzenie": "Indie",
    "Właściciel": "Krzysztof Kowalski"
  },
  {
    "Typ": "Diament",
    "Masa": "1,2ct",
    "Czystość": "VVS",
    "Barwa": "Przezroczysta",
    "Pochodzenie": "Rosja",
    "Właściciel": "Aleksandra Nowak"
  },
  {
    "Typ": "Rubin",
    "Masa": "0,7ct",
    "Czystość": "IF",
    "Barwa": "Czerwona",
    "Pochodzenie": "Birma",
    "Właściciel": "Międzynarodowy Dom Aukcyjny"
  },
  {
    "Typ": "Złoto",
    "Masa": "1g",
    "Czystość": "999",
    "Barwa": "Złota",
    "Pochodzenie": "USA",
    "Właściciel": "John Doe"
  },
  {
    "Typ": "Szafir",
    "Masa": "2ct",
    "Czystość": "S",
    "Barwa": "Niebieska",
    "Pochodzenie": "Sri Lanka",
    "Właściciel": "Małgorzata Ziemniak"
  },
  {
    "Typ": "Diament",
    "Masa": "2ct",
    "Czystość": "SI",
    "Barwa": "Biała",
    "Pochodzenie": "RPA",
    "Właściciel": "Anna Kowalska"
  },
  {
    "Typ": "Rubin",
    "Masa": "1,5ct",
    "Czystość": "VS",
    "Barwa": "Fioletowa",
    "Pochodzenie": "Tanzania",
    "Właściciel": "Firma Jubilerska „Klejnot”"
  },
  {
    "Typ": "Złoto",
    "Masa": "5g",
    "Czystość": "585",
    "Barwa": "Żółta",
    "Pochodzenie": "Kanada",
    "Właściciel": "Józef Zieliński"
  },
  {
    "Typ": "Szafir",
    "Masa": "1,1ct",
    "Czystość": "S",
    "Barwa": "Niebieska",
    "Pochodzenie": "Tajlandia",
    "Właściciel": "Skarb Państwa"
  },
  {
    "Typ": "Diament",
    "Masa": "0,25ct",
    "Czystość": "I1",
    "Barwa": "Biała",
    "Pochodzenie": "Kanada",
    "Właściciel": "Katarzyna Pawlak"
  },
  {
    "Typ": "Rubin",
    "Masa": "0,9ct",
    "Czystość": "VVS",
    "Barwa": "Różowa",
    "Pochodzenie": "Indie",
    "Właściciel": "Krzysztof Sobota"
  },
  {
    "Typ": "Złoto",
    "Masa": "2,5g",
    "Czystość": "750",
    "Barwa": "Złota",
    "Pochodzenie": "RPA",
    "Właściciel": "Anna Nowicka"
  },
  {
    "Typ": "Szafir",
    "Masa": "3ct",
    "Czystość": "IF",
    "Barwa": "Niebieska",
    "Pochodzenie": "Birma",
    "Właściciel": "Międzynarodowy Instytut Gemmologiczny"
  },
  {
    "Typ": "Diament",
    "Masa": "0,3ct",
    "Czystość": "I2",
    "Barwa": "Żółta",
    "Pochodzenie": "Brazylia",
    "Właściciel": "Pablo Rodriguez"
  },
  {
    "Typ": "Rubin",
    "Masa": "2ct",
    "Czystość": "SI",
    "Barwa": "Czerwona",
    "Pochodzenie": "Sri Lanka",
    "Właściciel": "Magdalena Kwiatkowska"
  },
  {
    "Typ": "Złoto",
    "Masa": "10g",
    "Czystość": "960",
    "Barwa": "Złota",
    "Pochodzenie": "Rosja",
    "Właściciel": "Vladimir Ivanowicz"
  },
  {
    "Typ": "Szafir",
    "Masa": "1,8ct",
    "Czystość": "VS",
    "Barwa": "Zielona",
    "Pochodzenie": "Afganistan",
    "Właściciel": "Janusz Chmielewski"
  },
  {
    "Typ": "Platyna",
    "Masa": "4g",
    "Czystość": "999",
    "Barwa": "Srebrna",
    "Pochodzenie": "RPA",
    "Właściciel": "Hanna Lewicka"
  },
  {
    "Typ": "Srebro",
    "Masa": "15g",
    "Czystość": "925",
    "Barwa": "Biała",
    "Pochodzenie": "Meksyk",
    "Właściciel": "Robert Kowalski"
  },
  {
    "Typ": "Szmaragd",
    "Masa": "1,4ct",
    "Czystość": "VS",
    "Barwa": "Zielona",
    "Pochodzenie": "Kolumbia",
    "Właściciel": "Piotr Zieliński"
  },
  {
    "Typ": "Opal",
    "Masa": "0,9ct",
    "Czystość": "S",
    "Barwa": "Tęczowa",
    "Pochodzenie": "Australia",
    "Właściciel": "Karolina Wiśniewska"
  },
  {
    "Typ": "Platyna",
    "Masa": "2,8g",
    "Czystość": "950",
    "Barwa": "Biała",
    "Pochodzenie": "Rosja",
    "Właściciel": "Anna Wójcik"
  },
  {
    "Typ": "Srebro",
    "Masa": "12g",
    "Czystość": "999",
    "Barwa": "Srebrna",
    "Pochodzenie": "Peru",
    "Właściciel": "Maria Nowak"
  },
  {
    "Typ": "Szmaragd",
    "Masa": "2,1ct",
    "Czystość": "IF",
    "Barwa": "Zielona",
    "Pochodzenie": "Zambia",
    "Właściciel": "Agnieszka Wysocka"
  },
  {
    "Typ": "Opal",
    "Masa": "1,2ct",
    "Czystość": "VS",
    "Barwa": "Niebieska",
    "Pochodzenie": "Etiopia",
    "Właściciel": "Tomasz Kwiatkowski"
  },
  {
    "Typ": "Platyna",
    "Masa": "5,6g",
    "Czystość": "950",
    "Barwa": "Szara",
    "Pochodzenie": "Kanada",
    "Właściciel": "Jakub Piotrowski"
  },
  {
    "Typ": "Srebro",
    "Masa": "8g",
    "Czystość": "835",
    "Barwa": "Szara",
    "Pochodzenie": "Polska",
    "Właściciel": "Rafał Lewandowski"
  },
  {
    "Typ": "Szmaragd",
    "Masa": "3ct",
    "Czystość": "VVS",
    "Barwa": "Zielona",
    "Pochodzenie": "Afganistan",
    "Właściciel": "Jan Kowalski"
  },
  {
    "Typ": "Opal",
    "Masa": "0,75ct",
    "Czystość": "SI",
    "Barwa": "Czerwona",
    "Pochodzenie": "Meksyk",
    "Właściciel": "Barbara Nowicka"
  },
  {
    "Typ": "Diament",
    "Masa": "0,6ct",
    "Czystość": "VVS",
    "Barwa": "Biała",
    "Pochodzenie": "Australia",
    "Właściciel": "Michał Nowak"
  },
  {
    "Typ": "Rubin",
    "Masa": "1,8ct",
    "Czystość": "SI",
    "Barwa": "Czerwona",
    "Pochodzenie": "Mozambik",
    "Właściciel": "Firma Jubilerska „Kamień”"
  },
  {
    "Typ": "Złoto",
    "Masa": "3,4g",
    "Czystość": "585",
    "Barwa": "Złota",
    "Pochodzenie": "Zambia",
    "Właściciel": "Lucyna Kowalska"
  },
  {
    "Typ": "Szafir",
    "Masa": "1,6ct",
    "Czystość": "VS",
    "Barwa": "Fioletowa",
    "Pochodzenie": "Kenia",
    "Właściciel": "Emilia Szymczak"
  },
  {
    "Typ": "Diament",
    "Masa": "1,5ct",
    "Czystość": "IF",
    "Barwa": "Przezroczysta",
    "Pochodzenie": "Botswana",
    "Właściciel": "Marcin Lewandowski"
  },
  {
    "Typ": "Rubin",
    "Masa": "1,1ct",
    "Czystość": "S",
    "Barwa": "Różowa",
    "Pochodzenie": "Birma",
    "Właściciel": "Natalia Zielińska"
  },
  {
    "Typ": "Złoto",
    "Masa": "6g",
    "Czystość": "999",
    "Barwa": "Biała",
    "Pochodzenie": "Peru",
    "Właściciel": "Adam Grzyb"
  },
  {
    "Typ": "Szafir",
    "Masa": "2,2ct",
    "Czystość": "IF",
    "Barwa": "Zielona",
    "Pochodzenie": "Tanzania",
    "Właściciel": "Łukasz Walczak"
  },
  {
    "Typ": "Diament",
    "Masa": "0,9ct",
    "Czystość": "SI",
    "Barwa": "Biała",
    "Pochodzenie": "RPA",
    "Właściciel": "Ewa Piotrowska"
  },
  {
    "Typ": "Rubin",
    "Masa": "1,4ct",
    "Czystość": "VVS",
    "Barwa": "Czerwona",
    "Pochodzenie": "Chiny",
    "Właściciel": "Dominik Majewski"
  },
  {
    "Typ": "Złoto",
    "Masa": "7,5g",
    "Czystość": "750",
    "Barwa": "Złota",
    "Pochodzenie": "Kolumbia",
    "Właściciel": "Andrzej Maciejewski"
  },
  {
    "Typ": "Platyna",
    "Masa": "3g",
    "Czystość": "999",
    "Barwa": "Srebrna",
    "Pochodzenie": "Kazachstan",
    "Właściciel": "Igor Petrenko"
  },
  {
    "Typ": "Srebro",
    "Masa": "5g",
    "Czystość": "925",
    "Barwa": "Srebrna",
    "Pochodzenie": "Mongolia",
    "Właściciel": "Magdalena Kowalczyk"
  },
  {
    "Typ": "Szmaragd",
    "Masa": "1,5ct",
    "Czystość": "VS",
    "Barwa": "Zielona",
    "Pochodzenie": "Kolumbia",
    "Właściciel": "Monika Gajewska"
  },
  {
    "Typ": "Opal",
    "Masa": "2ct",
    "Czystość": "IF",
    "Barwa": "Tęczowa",
    "Pochodzenie": "Australia",
    "Właściciel": "Dariusz Sobczak"
  }
]"""

    val obiekty = Json.decodeFromString<List<Obiekt>>(zbiorWejsciowyJson)
    val kategorie = Json.decodeFromString<List<Kategoria>>(kategorieJson)


    return Pair(obiekty,kategorie)


}

fun obliczWartoscNaJednostke(obiekty: List<Obiekt>, kategorie: List<Kategoria>): List<Pair<Obiekt,Double>> {

    val result: MutableList<Pair<Obiekt,Double>> = mutableListOf()


    for(obiekt in obiekty) {
        val kategoria = kategorie.find { it.Typ == obiekt.Typ && it.Czystość == obiekt.Czystość  }
        if(kategoria != null) {
            val masa = konwertujMase(obiekt.Masa)
            val wartoscNaJedostke = masa * kategoria.`Wartość za uncję (USD)`
            result.add(Pair(obiekt,wartoscNaJedostke))

        }
    }
    return result


}

fun top5WinnerSuko(wartosciObiektow: List<Pair<Obiekt, Double>>): List<Pair<Obiekt, Double>> {

    return wartosciObiektow.sortedByDescending { it.second }.take(5)
}

fun wynik(wartosciObiektow: List<Pair<Obiekt, Double>>) {
    println("Top 5 obiektów o największej wartości na jednostkę: ")
    wartosciObiektow.forEach {
        println("Typ: ${it.first.Typ}, Masa: ${it.first.Masa}, Czystość: ${it.first.Czystość}, Barwa: ${it.first.Barwa}, Pochodzenie: ${it.first.Pochodzenie}, Właściciel: ${it.first.Właściciel}  ")
    }
}





fun main() {
    val (obiekty, kategorie) = odczytajDane()
    val wartosci = obliczWartoscNaJednostke(obiekty,kategorie)
    val najlepszeObiekty = top5WinnerSuko(wartosci)
    wynik(najlepszeObiekty)

}