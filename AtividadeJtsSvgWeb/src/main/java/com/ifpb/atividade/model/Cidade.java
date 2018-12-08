/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.atividade.model;

/**
 *
 * @author Jose Carlos
 */
public class Cidade {
    private String nome;
    private String estado;
    private int populacao;
    private double densDemografica;
    private double area;
    private double perimetro;

    public Cidade(String nome, String estado, int populacao, double densDemografica, double area, double perimetro) {
        this.nome = nome;
        this.estado = estado;
        this.populacao = populacao;
        this.densDemografica = densDemografica;
        this.area = area;
        this.perimetro = perimetro;
    }

    

    public Cidade() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPolulacao(int polulacao) {
        this.populacao = polulacao;
    }

    public double getDensDemografica() {
        return densDemografica;
    }

    public void setDensDemografica(double densDemografica) {
        this.densDemografica = densDemografica;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    @Override
    public String toString() {
        return "Cidade{" + "nome=" + nome + ", estado=" + estado + ", polulacao=" + populacao + ", densDemografica=" + densDemografica + ", area=" + area + ", perimetro=" + perimetro + '}';
    }

    
    
    
}
