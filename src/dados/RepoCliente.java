/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

import java.util.*;
import negocio.Cliente;
/**
 *
 * @author tlpof
 */
public class RepoCliente {
    
        public static ArrayList<Cliente> clientes;
        
        public static Cliente getCliente(String cpf){
            for(Cliente cliente : clientes){
                if (cliente.cpf == cpf){
                    return cliente;
                }
            }
            return null;
        }
        
        public static void del(String cpf){
            for(Cliente cliente : clientes){
                if (cliente.cpf == cpf){
                    clientes.remove(cliente);
                }
            }
        }
        
        public static void add(String cpf, String nome){
            Cliente cliente = new Cliente(cpf,nome);
            clientes.add(cliente);
        }
}
