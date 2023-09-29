package com.test.blogricetter.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


//JAVA BEAN
@Entity
public class Ricetta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Il titolo non può essere vuoto")
    private String titolo;

    @NotBlank(message = "Gli ingredienti non possono essere vuoti")
    private String ingredienti;
    //@URL(message = "L'URL della foto non è valido")
    @NotBlank(message = "l'URL non può essere vuoto")
    private String urlFoto;
     @Min(1)
    private int tempoPreparazione;
    @Min(1)

    private int porzioni;
    @NotBlank(message = " non può essere vuoto")

    private String testoRicetta;
    private LocalDate dataCreazione;
    @ManyToOne
    private Categoria categoria;
    // GETTER E SETTER

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getTempoPreparazione() {
        return tempoPreparazione;
    }

    public void setTempoPreparazione(int tempoPreparazione) {
        this.tempoPreparazione = tempoPreparazione;
    }

    public int getPorzioni() {
        return porzioni;
    }

    public void setPorzioni(int porzioni) {
        this.porzioni = porzioni;
    }

    public String getTestoRicetta() {
        return testoRicetta;
    }

    public void setTestoRicetta(String testoRicetta) {
        this.testoRicetta = testoRicetta;
    }
}
