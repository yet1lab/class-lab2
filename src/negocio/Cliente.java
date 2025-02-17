/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author tlpof
 */
public class Cliente {
    
    public String cpf;
    public String nome;
    
    public Cliente (String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public void setDados(String cpf, String nome){
        this.nome = nome;
        this.cpf = cpf;
    }
}
