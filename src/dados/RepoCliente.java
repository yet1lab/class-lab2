//==========================================
	package dados;
	import java.util.*;
	import negocio.Cliente;
//==========================================
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
        
        public static void delCliente(String cpf){
            for(Cliente cliente : clientes){
                if (cliente.cpf == cpf){
                    clientes.remove(cliente);
                }
            }
        }
        
        public static void addCliente(String cpf, String nome){
            Cliente cliente = new Cliente(cpf,nome);
            clientes.add(cliente);
        }
}
