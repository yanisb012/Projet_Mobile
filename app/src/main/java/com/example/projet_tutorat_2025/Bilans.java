package com.example.projet_tutorat_2025;

import com.google.gson.annotations.SerializedName;

public class Bilans {

    @SerializedName("id")
    private int id;
    @SerializedName("idEtu")
    private int idEtu;
    @SerializedName("datBil1")
    private String datebilan1;
    @SerializedName("noteEnt1")
    private float noteEnt1;

    @SerializedName("noteDos1")
    private float noteDossier1;

    @SerializedName("noteOral1")
    private float noteOral1;

    @SerializedName("moyBil1")
    private float moyenne;
    @SerializedName("remaBil1")
    private String remarque1;
    @SerializedName("datBil2")
    private String datebilan2;
    @SerializedName("noteDos2")
    private float noteDossier2;
    @SerializedName("noteOral2")
    private float noteOral2;

    @SerializedName("moyBil2")
    private float moyenne2;

    @SerializedName("remaBil2")
    private String remarque2;
    @SerializedName("sujMem")
    private String sujetmemoire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }

    public float getNoteEnt1() {
        return noteEnt1;
    }

    public void setNoteEnt1(float noteEnt1) {
        this.noteEnt1 = noteEnt1;
    }

    public float getNoteDossier1() {
        return noteDossier1;
    }

    public void setNoteDossier1(float noteDossier1) {
        this.noteDossier1 = noteDossier1;
    }

    public float getNoteOral1() {
        return noteOral1;
    }

    public void setNoteOral1(float noteOral1) {
        this.noteOral1 = noteOral1;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public String getRemarque1() {
        return remarque1;
    }

    public void setRemarque1(String remarque1) {
        this.remarque1 = remarque1;
    }

    public float getNoteDossier2() {
        return noteDossier2;
    }

    public void setNoteDossier2(float noteDossier2) {
        this.noteDossier2 = noteDossier2;
    }

    public float getNoteOral2() {
        return noteOral2;
    }

    public void setNoteOral2(float noteOral2) {
        this.noteOral2 = noteOral2;
    }

    public float getMoyenne2() {
        return moyenne2;
    }

    public void setMoyenne2(float moyenne2) {
        this.moyenne2 = moyenne2;
    }

    public String getRemarque2() {
        return remarque2;
    }

    public void setRemarque2(String remarque2) {
        this.remarque2 = remarque2;
    }

    public String getSujetmemoire() {
        return sujetmemoire;
    }

    public void setSujetmemoire(String sujetmemoire) {
        this.sujetmemoire = sujetmemoire;
    }

    public String getDatebilan1() {
        return datebilan1;
    }

    public void setDatebilan1(String datebilan1) {
        this.datebilan1 = datebilan1;
    }

    public String getDatebilan2() {
        return datebilan2;
    }

    public void setDatebilan2(String datebilan2) {
        this.datebilan2 = datebilan2;
    }

    public Bilans(int id, int idEtu, String datebilan1, float noteEnt1, float noteDossier1, float noteOral1, float moyenne, String remarque1, String datebilan2, float noteDossier2, float noteOral2, float moyenne2, String remarque2, String sujetmemoire) {
        this.id = id;
        this.idEtu = idEtu;
        this.datebilan1=datebilan1;
        this.noteEnt1 = noteEnt1;
        this.noteDossier1 = noteDossier1;
        this.noteOral1 = noteOral1;
        this.moyenne = moyenne;
        this.remarque1 = remarque1;
        this.datebilan2=datebilan2;
        this.noteDossier2 = noteDossier2;
        this.noteOral2 = noteOral2;
        this.moyenne2 = moyenne2;
        this.remarque2 = remarque2;
        this.sujetmemoire=sujetmemoire;
    }

    @Override
    public String toString() {
        return "Bilan{" +
                "id=" + id +
                ", idEtu=" + idEtu +
                ", datebilan1='" + datebilan1 + '\'' +
                ", noteEnt1=" + noteEnt1 +
                ", noteDossier1=" + noteDossier1 +
                ", noteOral1=" + noteOral1 +
                ", moyenne=" + moyenne +
                ", remarque1='" + remarque1 + '\'' +
                ", datebilan2='" + datebilan2 + '\'' +
                ", noteDossier2=" + noteDossier2 +
                ", noteOral2=" + noteOral2 +
                ", moyenne2=" + moyenne2 +
                ", remarque2='" + remarque2 + '\'' +
                ", sujetmemoire='" + sujetmemoire + '\'' +
                '}';
    }
}