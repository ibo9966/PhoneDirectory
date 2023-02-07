package com.example.rehberim

import android.content.ContentValues

class Kisilerdao {

    fun kisiSİL(vt: VeritabaniYardimcisi, kisi_id: Int) {

        val db = vt.writableDatabase
        db.delete("kisiler", "kisi_id=?", arrayOf(kisi_id.toString()))
        db.close()

    }

    fun kisiEkle(vt: VeritabaniYardimcisi, kisi_ad: String, kisi_tel: String) {

        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("kisi_ad", kisi_ad)
        values.put("kisi_tel", kisi_tel)
        db.insertOrThrow("kisiler",null,values)
        db.close()
    }
    fun kisiGuncelle(vt: VeritabaniYardimcisi,kisi_id:Int, kisi_ad: String, kisi_tel: String) {

        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("kisi_ad", kisi_ad)
        values.put("kisi_tel", kisi_tel)
        db.update("kisiler",values,"kisi_id=?", arrayOf(kisi_id.toString()))

        db.close()
    }

    fun tumKisiler(vt:VeritabaniYardimcisi): ArrayList<Kisiler>{
        val db = vt.writableDatabase
        val kisilerListe = ArrayList<Kisiler>()
        val c = db.rawQuery("SELECT * FROM kisiler",null)

        while (c.moveToNext()){
            val kisi = Kisiler(c.getInt(c.getColumnIndexOrThrow("kisi_id"))
                ,c.getString(c.getColumnIndexOrThrow("kisi_ad"))
                ,c.getString(c.getColumnIndexOrThrow("kisi_tel")))

            kisilerListe.add(kisi)
        }
        return kisilerListe
    }

    fun kisiAra(vt:VeritabaniYardimcisi,aramaKelime:String): ArrayList<Kisiler>{
        val db = vt.writableDatabase
        val kisilerListe = ArrayList<Kisiler>()
        val c = db.rawQuery("SELECT * FROM kisiler WHERE kisi_ad LIKE '%$aramaKelime%'",null)

        while (c.moveToNext()){
            val kisi = Kisiler(c.getInt(c.getColumnIndexOrThrow("kisi_id"))
                ,c.getString(c.getColumnIndexOrThrow("kisi_ad"))
                ,c.getString(c.getColumnIndexOrThrow("kisi_tel")))

            kisilerListe.add(kisi)
        }
        return kisilerListe
    }

}