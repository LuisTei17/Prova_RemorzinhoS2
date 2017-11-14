package pojo;

import java.io.Serializable;

/**
 * Created by Jotaro on 11/14/2017.
 */

public class Televisao implements Serializable{
    private int id;
    private String marca;
    private String modelo;
    private String peso;
    private String resolucao;
    private boolean caption;
    private boolean digital;
    private boolean sap;

    public Televisao() {

    };

    public Televisao(String marca, String modelo, String peso, boolean caption, boolean digital, boolean sap, String resolucao) {
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.resolucao = resolucao;
        this.caption = caption;
        this.digital = digital;
        this.sap = sap;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public boolean iscaption() {
        return caption;
    }

    public void setcaption(boolean caption) {
        this.caption = caption;
    }

    public boolean isdigital() {
        return digital;
    }

    public void setdigital(boolean digital) {
        this.digital = digital;
    }

    public boolean issap() {
        return sap;
    }

    public void setsap(boolean sap) {
        this.sap = sap;
    }

    @Override
    public String toString() {
        String checkboxes = "";

        if(this.iscaption()) {
            checkboxes += " closed caption ";
        }
        if(this.isdigital()) {
            checkboxes += " conversor digital ";
        }
        if(this.issap()) {
            checkboxes += " funcao sap ";
        }
        return this.marca + ":" + this.modelo + ":" + this.peso + ":" + checkboxes + ":" + this.resolucao;
    }
}
