package gui.modeloDesign;

import gui.componentes.PanelGradient;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import gui.menu.MenuOption;

public class AnimateBTT {
    
    private void mostrarCor(PanelGradient paneX,PanelGradient paneZ, JLabel lblx, String url){
        paneX.setBackground(new Color(211, 9, 63));
        paneZ.setBackground(new Color(200, 50, 90));
        lblx.setForeground(Color.WHITE);
        
        lblx.setIcon (new ImageIcon(getClass().getResource(url)));
    }
    
    private void estabelecCor(PanelGradient paneX,PanelGradient paneZ, JLabel lblx, String url){
        paneX.setBackground(new Color(109,26,54));
        paneZ.setBackground(new Color(109,26,54));
        lblx.setForeground(new Color(166,166,166));
        
        lblx.setIcon (new ImageIcon(getClass().getResource(url)));
    }
    
    public void animarAluguel(){
        mostrarCor(MenuOption.btt_alugar, MenuOption.btt_alugar, MenuOption.lbl_alugar, "/icones/db32px.png");
        estabelecCor(MenuOption.btt_manutencao, MenuOption.btt_manutencao, MenuOption.lbl_manuntencao, "/icones/rep32px.png");
        estabelecCor(MenuOption.btt_clientes, MenuOption.btt_clientes, MenuOption.lbl_clientes, "/icones/cliente32px.png");
        estabelecCor(MenuOption.btt_frota, MenuOption.btt_frota, MenuOption.lbl_frota, "/icones/carro32px.png");
    }
    
    public void AnimarFrota(){
        mostrarCor(MenuOption.btt_frota, MenuOption.btt_frota, MenuOption.lbl_frota, "/icones/carro32px.png");
        estabelecCor(MenuOption.btt_manutencao, MenuOption.btt_manutencao, MenuOption.lbl_manuntencao, "/icones/rep32px.png");
        estabelecCor(MenuOption.btt_clientes, MenuOption.btt_clientes, MenuOption.lbl_clientes, "/icones/cliente32px.png");
        estabelecCor(MenuOption.btt_alugar, MenuOption.btt_alugar, MenuOption.lbl_alugar, "/icones/db32px.png");
    }
    
    public void AnimarManuntencao(){
        mostrarCor(MenuOption.btt_manutencao, MenuOption.btt_manutencao, MenuOption.lbl_manuntencao, "/icones/rep32px.png");
        estabelecCor(MenuOption.btt_frota, MenuOption.btt_frota, MenuOption.lbl_frota, "/icones/carro32px.png");
        estabelecCor(MenuOption.btt_clientes, MenuOption.btt_clientes, MenuOption.lbl_clientes, "/icones/cliente32px.png");
        estabelecCor(MenuOption.btt_alugar, MenuOption.btt_alugar, MenuOption.lbl_alugar, "/icones/db32px.png");
    }
    
    public void AnimarClientes(){
        mostrarCor(MenuOption.btt_clientes, MenuOption.btt_clientes, MenuOption.lbl_clientes, "/icones/cliente32px.png");
        estabelecCor(MenuOption.btt_manutencao, MenuOption.btt_manutencao, MenuOption.lbl_manuntencao, "/icones/rep32px.png");
        estabelecCor(MenuOption.btt_frota, MenuOption.btt_frota, MenuOption.lbl_frota, "/icones/carro32px.png");
        estabelecCor(MenuOption.btt_alugar, MenuOption.btt_alugar, MenuOption.lbl_alugar, "/icones/db32px.png");
    }
}
