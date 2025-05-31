package com.example.projet_tutorat_2025;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class Etudiant {
    @SerializedName("id")
    private int id;

    @SerializedName("nomEtu")
    private String nom;

    @SerializedName("preEtu")
    private String prenom;

    @SerializedName("mailEtu")
    private String mail;

    @SerializedName("telEtu")
    private String tel;

    @SerializedName("adrEtu")
    private String adresse;

    @SerializedName("cpEtu")
    private String codePostal;

    @SerializedName("vilEtu")
    private String ville;

    @SerializedName("logEtu")
    private String login;
    @SerializedName("nomEnt")
    private String nomEntreprise;

    @SerializedName("adrEnt")
    private String adresseEntreprise;

    @SerializedName("cpEnt")
    private String codePostalEntreprise;

    @SerializedName("vilEnt")
    private String villeEntreprise;
    @SerializedName("nomMaitre")
    private String nomMaitre;

    @SerializedName("preMaitre")
    private String prenomMaitre;

    @SerializedName("telMaitre")
    private String telMaitre;

    @SerializedName("mailMaitre")
    private String mailMaitre;

    @SerializedName("nomTut")
    private String nomTuteur;
    @SerializedName("preTut")
    private String prenomTuteur;


    private boolean success;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Etudiant(int id, String nom, String prenom, String mail, String tel, String adresse, String codePostal, String ville, String login, String nomEntreprise, String adresseEntreprise, String codePostalEntreprise, String villeEntreprise,String nomMaitre, String prenomMaitre, String telMaitre, String mailMaitre, String nomTuteur, String prenomTuteur, boolean success) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.tel = tel;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.login = login;
        this.nomEntreprise = nomEntreprise;
        this.adresseEntreprise = adresseEntreprise;
        this.codePostalEntreprise = codePostalEntreprise;
        this.villeEntreprise = villeEntreprise;
        this.nomMaitre = nomMaitre;
        this.prenomMaitre = prenomMaitre;
        this.telMaitre = telMaitre;
        this.mailMaitre = mailMaitre;
        this.nomTuteur = nomTuteur;
        this.prenomTuteur = prenomTuteur;
        this.success = success;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMail() { return mail; }
    public String getTel() { return tel; }
    public String getAdresse() { return adresse; }
    public String getCodePostal() { return codePostal; }
    public String getVille() { return ville; }
    public String getLogin() { return login; }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getAdresseEntreprise() {
        return adresseEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        this.adresseEntreprise = adresseEntreprise;
    }

    public String getCodePostalEntreprise() {
        return codePostalEntreprise;
    }

    public void setCodePostalEntreprise(String codePostalEntreprise) {
        this.codePostalEntreprise = codePostalEntreprise;
    }

    public String getVilleEntreprise() {
        return villeEntreprise;
    }

    public void setVilleEntreprise(String villeEntreprise) {
        this.villeEntreprise = villeEntreprise;
    }

    public String getNomMaitre() {
        return nomMaitre;
    }

    public void setNomMaitre(String nomMaitre) {
        this.nomMaitre = nomMaitre;
    }

    public String getPrenomMaitre() {
        return prenomMaitre;
    }

    public void setPrenomMaitre(String prenomMaitre) {
        this.prenomMaitre = prenomMaitre;
    }

    public String getTelMaitre() {
        return telMaitre;
    }

    public void setTelMaitre(String telMaitre) {
        this.telMaitre = telMaitre;
    }

    public String getMailMaitre() {
        return mailMaitre;
    }

    public void setMailMaitre(String mailMaitre) {
        this.mailMaitre = mailMaitre;
    }

    public String getNomTuteur() {
        return nomTuteur;
    }

    public void setNomTuteur(String nomTuteur) {
        this.nomTuteur = nomTuteur;
    }

    public String getPrenomTuteur() {
        return prenomTuteur;
    }

    public void setPrenomTuteur(String prenomTuteur) {
        this.prenomTuteur = prenomTuteur;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", tel='" + tel + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", login='" + login + '\'' +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", adresseEntreprise='" + adresseEntreprise + '\'' +
                ", codePostalEntreprise='" + codePostalEntreprise + '\'' +
                ", villeEntreprise='" + villeEntreprise + '\'' +
                ", nomMaitre='" + nomMaitre + '\'' +
                ", prenomMaitre='" + prenomMaitre + '\'' +
                ", telMaitre='" + telMaitre + '\'' +
                ", mailMaitre='" + mailMaitre + '\'' +
                ", nomTuteur='" + nomTuteur + '\'' +
                ", prenomTuteur='" + prenomTuteur + '\'' +
                ", success=" + success +
                '}';
    }
}


