package gui.menu;


import dados.LSetBook;
import dados.*;
import gui.modeloDesign.AnimateBTT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import negocio.Fachada;
import java.util.List;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import negocio.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;




public class MenuOption extends javax.swing.JFrame {
    
    //FACHADA
    Fachada fachada =                                                    new Fachada();
    Set<Carro> carros =                      ((RepoCarro) fachada.get("carros")).src();
    Set<Aluguel> alugueis =              ((RepoAluguel) fachada.get("alugueis")).src();
    Set<Manutencao> manutencoes =  ((RepoManutencao) fachada.get("manutencoes")).src();
    Set<Cliente> clientes =              ((RepoCliente) fachada.get("clientes")).src();
    
    AnimateBTT ColorOP = new AnimateBTT();

			
    public MenuOption() {
        initComponents();
        addRowToTableAluguel();
        
        tableClickFrota     ();
        tableClickManutencao();
        tableClickClientes  ();
                
        tableListener           (fTsearchFrota,tableFrota);
        tableListener(mTsearchManutencao, tableManutencao);
        tableListener    (cTsearchClientes, tableClientes);
        
        popularComboBoxClientes      (aCbClientes, aTfCPF);
        popularComboBoxCarros        (aCbPlaca,  aTfCarro);
        
        ColorOP.animarAluguel();
        }
        
    
    public void tableClickFrota(){
        tableFrota.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Verificar se a seleção não está em ajuste (evita duplicação de eventos)
                if (!event.getValueIsAdjusting()) {
                    // Obter a linha selecionada
                    int selectedRow = tableFrota.getSelectedRow();

                    // Verificar se uma linha foi selecionada
                    if (selectedRow != -1) {
                        // Recuperar os dados da linha selecionada
                        String marca = (String) tableFrota.getValueAt(selectedRow, 0);
                        String modelo = (String) tableFrota.getValueAt(selectedRow, 1);
                        String placa = (String) tableFrota.getValueAt(selectedRow, 2);
                        String ano = tableFrota.getValueAt(selectedRow, 3).toString();
                        String valor = tableFrota.getValueAt(selectedRow, 4).toString();

                        // Preencher os campos de texto com os dados da linha selecionada
                        TFMarca.setText(marca);
                        TFModelo.setText(modelo);
                        TFPlaca.setText(placa);
                        TFAno.setText(ano);
                        TFValor.setText(valor);
                    }
                }
            }
        });
    }
    
    public void tableClickManutencao(){
        tableManutencao.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Verificar se a seleção não está em ajuste (evita duplicação de eventos)
                if (!event.getValueIsAdjusting()) {
                    // Obter a linha selecionada
                    int selectedRow = tableManutencao.getSelectedRow();

                    // Verificar se uma linha foi selecionada
                    if (selectedRow != -1) {
                        // Recuperar os dados da linha selecionada
                        String placa = (String) tableManutencao.getValueAt(selectedRow, 2);

                        // Preencher os campos de texto com os dados da linha selecionada
                        mTfPlaca.setText(placa);
                    }
                }
            }
        });
    }
    
    public void tableClickClientes(){
        tableClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Verificar se a seleção não está em ajuste (evita duplicação de eventos)
                if (!event.getValueIsAdjusting()) {
                    // Obter a linha selecionada
                    int selectedRow = tableClientes.getSelectedRow();

                    // Verificar se uma linha foi selecionada
                    if (selectedRow != -1) {
                        // Recuperar os dados da linha selecionada
                        String nome = (String) tableClientes.getValueAt(selectedRow, 0);
                        String cpf = (String) tableClientes.getValueAt(selectedRow, 1);
                        String idade = tableClientes.getValueAt(selectedRow, 2).toString();
                        String email = (String) tableClientes.getValueAt(selectedRow, 3);
                        String telefone = (String) tableClientes.getValueAt(selectedRow, 4);

                        // Preencher os campos de texto com os dados da linha selecionada
                        cTfNome.setText(nome);
                        cTfCpf.setText(cpf);
                        cTfIdade.setText(idade);
                        cTfEmail.setText(email);
                        cTfTelefone.setText(telefone);
                    }
                }
            }
        });
    }
    
    public void tableListener(JTextField search, JTable table){
            search.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    filterTable(search, table);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    filterTable(search, table);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    filterTable(search, table);
                }
            });
            }
    
    private void filterTable(JTextField search, JTable table) {
        // Obter o texto do campo de pesquisa
        String query = search.getText().toLowerCase();

        // Obter o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Criar um TableRowSorter para filtrar a tabela
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Definir o filtro para pesquisar em todas as colunas
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
    
    public void addRowToTableFrota(){
        DefaultTableModel model = (DefaultTableModel) tableFrota.getModel();
        Set<Carro> list = carros;
        model.setRowCount(0);
        tableFrota.revalidate();
        Object rowData[] = new Object[6];
        for(Carro carro : list){
            rowData[0] = carro.get("marca");
            rowData[1] = carro.get("modelo");
            rowData[2] = carro.get("placa");
            rowData[3] = carro.get("ano");
            rowData[4] = carro.get("valor");
            rowData[5] = carro.get("estado");
            if(rowData[5] == "Disponivel"){model.addRow(rowData);}
            
        }
    }
    
    public void addRowToTableManutencao(){
        DefaultTableModel model = (DefaultTableModel) tableManutencao.getModel();
        Set<Manutencao> list = manutencoes;
        model.setRowCount(0);
        tableFrota.revalidate();
        Object rowData[] = new Object[5];
        for(Manutencao manutencao : list){
            Carro carro = manutencao.get("carro");
            
            rowData[0] = carro.get("marca");
            rowData[1] = carro.get("modelo");
            rowData[2] = carro.get("placa");
            rowData[3] = manutencao.get("valor");
            rowData[4] = manutencao.get("tipo");
            
            model.addRow(rowData);
        }
    }
    
    public void addRowToTableClientes(){
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        Set<Cliente> list = clientes;
        model.setRowCount(0);
        tableClientes.revalidate();
        Object rowData[] = new Object[5];
        for(Cliente cliente : list){
            rowData[0] = cliente.get("nome");
            rowData[1] = cliente.get("cpf");
            rowData[2] = cliente.get("idade");
            rowData[3] = cliente.get("email");
            rowData[4] = cliente.get("telefone");
            model.addRow(rowData);
        }
    }
    
    public void addRowToTableAluguel(){
        DefaultTableModel model = (DefaultTableModel) tableAluguel.getModel();
        Set<Aluguel> list = alugueis;
        model.setRowCount(0);
        tableAluguel.revalidate();
        Object rowData[]= new Object[7];
        for(Aluguel aluguel : alugueis){
            Cliente cliente = aluguel.get("cliente");
            Carro carro     = aluguel.get("carro");
            
            
            rowData[0] = cliente.get("nome");
            rowData[1] = cliente.get("cpf");
            rowData[2] = cliente.get("telefone");
            rowData[3] = (String) carro.get("marca") + " " + carro.get("modelo");
            rowData[4] = carro.get("placa");
            rowData[5] = aluguel.get("dias");
            rowData[6] = aluguel.get("valor");
            
            model.addRow(rowData);
        }
    }
    
    public void popularComboBoxClientes(JComboBox<String> comboBox, JTextField textFieldCPF) {
            // Limpar o comboBox antes de adicionar os novos itens
            comboBox.removeAllItems();
            
            // Adicionar primeiro Item
            comboBox.addItem("Selecione");

            // Obter a lista de clientes do repositório
            RepoCliente repoClientes = RepoCliente.getInstance();
            Set<Cliente> clientes = repoClientes.src();

            // Adicionar os nomes dos clientes ao comboBox
            for (Cliente cliente : clientes) {
                    comboBox.addItem((String) cliente.get("nome")); // Supondo que "nome" seja uma propriedade do cliente
   
        }
            // Adicionar um ActionListener ao comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter o nome selecionado no comboBox
                String nomeSelecionado = (String) comboBox.getSelectedItem();

                // Encontrar o cliente correspondente no repositório
                Cliente clienteSelecionado = repoClientes.obj("nome", nomeSelecionado);

                // Preencher o JTextField com o CPF do cliente selecionado
                if (clienteSelecionado != null) {
                    textFieldCPF.setText((String) clienteSelecionado.get("cpf")); // Supondo que "cpf" seja uma propriedade do cliente
                } else {
                    textFieldCPF.setText(""); // Limpar o campo se nenhum cliente for encontrado
                }
            }
        });
    }
    
    public void popularComboBoxCarros(JComboBox<String> comboBox, JTextField textField) {
        // Limpar o comboBox antes de adicionar os novos itens
        comboBox.removeAllItems();        
        
        // Adicionar primeiro Item
        comboBox.addItem("Selecione");
        
        RepoCarro repoCarro = RepoCarro.getInstance();
        
        // Adicionar os nomes dos clientes ao comboBox
        for (Carro carro : repoCarro.src()) {
            if(carro.get("estado") == "Disponivel") {comboBox.addItem((String) carro.get("placa"));} // Supondo que "nome" seja uma propriedade do cliente
            
        }
        // Adicionar um ActionListener ao comboBox
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obter o nome selecionado no comboBox
                String placa = (String) comboBox.getSelectedItem();

                // Encontrar o cliente correspondente no repositório
                Carro carroSelecionado = repoCarro.obj("placa", placa);

                // Preencher o JTextField com o CPF do cliente selecionado
                if (carroSelecionado != null) {
                    textField.setText((String) carroSelecionado.get("marca") + " " + carroSelecionado.get("modelo")); // Supondo que "cpf" seja uma propriedade do cliente
                } else {
                    textField.setText(""); // Limpar o campo se nenhum cliente for encontrado
                }
            }
        });
    }
    
    // ===============================
    //      FUNÇÕES DO PAINEL FROTA
    // ===============================

    
    public void salvarFrota(){
        // Obter a instância do repositório de carros
        RepoCarro repoCarro = RepoCarro.getInstance();

        // Verificar se já existe um carro com a mesma placa
        String placa = TFPlaca.getText();
        Carro carroExistente = repoCarro.obj("placa", placa);

        // Validação para não deixar dados em branco
        if (TFMarca.getText().equals("") || TFModelo.getText().equals("") || TFPlaca.getText().equals("")
                || TFValor.getText().equals("") || TFAno.getText().equals("")) {
            // Se qualquer uma dessas caixas estiverem em branco, mostrar mensagem.
            JOptionPane.showMessageDialog(this, "Por favor, preencha todas as caixas.");
        } else if (carroExistente != null) {
            // Se já existe um carro com a mesma placa, atualize os dados
            System.out.println("Carro com a mesma placa encontrado. Atualizando...");

            // Atualizar os dados do carro existente
            carroExistente.set("marca", TFMarca.getText());
            carroExistente.set("modelo", TFModelo.getText());
            carroExistente.set("ano", Integer.parseInt(TFAno.getText()));
            carroExistente.set("valor", Integer.parseInt(TFValor.getText()));
            carroExistente.set("estado", "Disponivel");

            // Atualizar a tabela
            addRowToTableFrota();

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Carro atualizado com sucesso!");
        } else {
            // Se não existe um carro com a mesma placa, adicionar um novo carro
            System.out.println("Nenhum carro com a mesma placa encontrado. Adicionando novo carro...");

            // Criar um novo objeto Carro com os dados inseridos
            String marca = TFMarca.getText();
            String modelo = TFModelo.getText();
            int ano = Integer.parseInt(TFAno.getText()); // Converter o ano para int
            int valor = Integer.parseInt(TFValor.getText()); // Converter o valor para int
            String estado = "Disponivel";

            // Adicionar o carro ao repositório
            repoCarro.add(marca, modelo, ano, placa);

            // Definir o valor e o estado do carro
            Carro novoCarro = repoCarro.obj("placa", placa); // Recuperar o carro adicionado
            novoCarro.set("valor", valor);
            novoCarro.set("estado", estado);

            // Atualizar a tabela
            addRowToTableFrota();

            // Mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Carro adicionado com sucesso!");

            // Limpar os campos após a inserção
            TFMarca.setText("");
            TFModelo.setText("");
            TFPlaca.setText("");
            TFAno.setText("");
            TFValor.setText("");
        }

        verificarFrota();
    }
    
    public void verificarFrota(){
        // Obter a instância do repositório de carros
        RepoCarro repoCarro = RepoCarro.getInstance();
        
        // Verificação: Imprimir o conteúdo do repositório no console
        System.out.println("Carros no repositório:");
        for (Carro carro : repoCarro.src()) {
            System.out.println(carro);
        }
    }
    
    public void deletarFrota(){
        // Obter a instância do repositório de carros
        RepoCarro repoCarro = RepoCarro.getInstance();

        // Verificar se já existe um carro com a mesma placa
        String placa = TFPlaca.getText();
        Carro carroExistente = repoCarro.obj("placa", placa);
        
        
        // Validação para não deixar placa em branco ou não encontrar um carro com essa placa
        if(TFPlaca.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Placa em branco");
        } else if(carroExistente == null){
            JOptionPane.showMessageDialog(this, "Carro com Placa Inexistente");
        } else{
            int confirmarDialogo = JOptionPane.showConfirmDialog(this, "Essa ação NÃO poderá ser desfeita.", "Você tem Certeza?", 0, 2);
            if(confirmarDialogo == 0){
                repoCarro.del("placa", placa);
                addRowToTableFrota();
            }
        }
        verificarFrota();
    }
    
    //=================================
    //      FUNÇÕES DE MANUTENÇÃO
    //=================================
    
    public void salvarManutencao(){
        // Obter a instância do repositório de carros
        RepoCarro repoCarro = RepoCarro.getInstance();
        RepoManutencao manutencoes = RepoManutencao.getInstance();
        // Verificar se já existe um carro com a mesma placa
        String placa = mTfPlaca.getText();
        Carro carroExistente = repoCarro.obj("placa", placa);
        
        // Validação para não deixar placa em branco ou não encontrar um carro com essa placa
        if(mTfPlaca.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Placa em branco");
        } else if(carroExistente == null){
            JOptionPane.showMessageDialog(this, "Carro com Placa Inexistente");
        } else{
            
            manutencoes.add(carroExistente, carroExistente.get("dano")); // Adicionar carro no RepoManutencao
            addRowToTableManutencao(); // Atualizar Tabela
            
            JOptionPane.showMessageDialog(this, "Carro enviado para Manutenção!");
            
            verificarManutencoes();
            
        }
    }
    
    public void encerrarManutencao() {
        // Obter a instância do repositório de carros e manutenções
        RepoCarro repoCarro = RepoCarro.getInstance();
        RepoManutencao manutencoes = RepoManutencao.getInstance();

        // Obter a placa do carro
        String placa = mTfPlaca.getText();

        // Verificar se o carro existe
        Carro carroExistente = repoCarro.obj("placa", placa);

        // Validação para não deixar placa em branco ou não encontrar um carro com essa placa
        if (placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Placa em branco");
        } else if (carroExistente == null) {
            JOptionPane.showMessageDialog(this, "Carro com Placa Inexistente");
        } else {
            // Confirmar a ação
            int confirmarDialogo = JOptionPane.showConfirmDialog(this, "Essa ação NÃO poderá ser desfeita.", "Você tem Certeza?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmarDialogo == JOptionPane.YES_OPTION) {
                // Remover a manutenção do repositório
                manutencoes.del("carro", carroExistente);

                // Atualizar o estado do carro para disponível
                carroExistente.set("estado", "Disponivel");

                // Depuração
                System.out.println("Manutenções depois de remover: " + manutencoes.src());

                // Atualizar a tabela de manutenções
                addRowToTableManutencao();

                // Mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Manutenção Finalizada!");
            }

            // Verificar manutenções (se necessário)
            verificarManutencoes();
            verificarFrota();
        }
    }
    
    public void verificarManutencoes(){
        // Obter a instância do repositório de carros
        RepoManutencao manutencoes = RepoManutencao.getInstance();
        
        // Verificação: Imprimir o conteúdo do repositório no console
        System.out.println("Carros em Manutenção:");
        for (Manutencao manut : manutencoes.src()) {
            System.out.println(manut);
        }
    }
    
    // =================================
    //       FUNÇÕES DE CLIENTES
    // =================================
    
    public void salvarCliente(){
        
      RepoCliente clientes = RepoCliente.getInstance();
      String cpf = cTfCpf.getText();
      Cliente cliente = clientes.obj("cpf", cpf);
      
      // Validação para não deixar em branco
      if (cTfNome.getText().equals("") ||cTfEmail.getText().equals("") ||cTfIdade.getText().equals("") 
              ||cTfEmail.getText().equals("") ||cTfTelefone.getText().equals("")){
          
          JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
          
      } else if(cliente != null){
          
          // Printar no Console
          System.out.println("Cliente localizado. Atualizando...");
          
          // Atualizar os dados de Cliente
          cliente.set("nome", cTfNome.getText());
          cliente.set("idade", Integer.parseInt(cTfIdade.getText()));
          cliente.set("email", cTfEmail.getText());
          cliente.set("telefone", cTfTelefone.getText());
          
          addRowToTableClientes();
          
          JOptionPane.showMessageDialog(this, "CLiente atualizado com sucesso!");
      }else {
          
          System.out.println("Cliente não localizado. Adicionando...");
          
          String nome = cTfNome.getText();
          int idade = Integer.parseInt(cTfIdade.getText());
          String email = cTfEmail.getText();
          String telefone = cTfTelefone.getText();
          
          clientes.add(nome, cpf, email, idade, telefone);
          
          addRowToTableClientes();
          
          JOptionPane.showMessageDialog(this, "Cliente Cadastrado Com Sucesso!");
      }
      verificarClientes();
      
    }
    
    public void desligarCliente(){
        
        RepoCliente clientes = RepoCliente.getInstance();
        String cpf = cTfCpf.getText();
        Cliente cliente = clientes.obj("cpf", cpf);
        
        if(cTfCpf.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Cpf em Branco.");
        }else if(cliente == null){
           JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }else{
            int confirmarDialogo = JOptionPane.showConfirmDialog(this, "Essa ação NÃO poderá ser desfeita.", "Você tem Certeza?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmarDialogo == JOptionPane.YES_OPTION) {
                
                clientes.del("cpf", cpf);
                addRowToTableClientes();
                
                JOptionPane.showMessageDialog(this, "Cliente desligado com Sucesso!");
            }
        verificarClientes();
        }
    }
    
    public void verificarClientes(){
        RepoCliente clientes = RepoCliente.getInstance();
        
        System.out.println("Clientes cadastrados:");
        for(Cliente cliente : clientes.src()){
            System.out.println(cliente);
        }
    }
    
    // ===================================
    //          FUNÇÕES DE ALUGUEL
    // ===================================
    
    public void alugar(){
        
        RepoCarro rcarros = RepoCarro.getInstance();
        RepoCliente rclientes = RepoCliente.getInstance();
        RepoAluguel ralugueis = RepoAluguel.getInstance();
        
        String placa = (String) aCbPlaca.getSelectedItem();
        Carro carro = rcarros.obj("placa", placa);
        
        String cpf = aTfCPF.getText();
        Cliente cliente = rclientes.obj("cpf",cpf);
        
        
        
        if(aTfDias.getText().equals("") || aCbClientes.getSelectedItem().equals("Selecione") 
                || aCbPlaca.getSelectedItem().equals("Selecione")){
            JOptionPane.showMessageDialog(this, "Preencha as opções.");
        } else {
            
            int dias = Integer.parseInt(aTfDias.getText());
            
            carro.set("estado", "Alugado");
            ralugueis.add(cliente, carro, dias);
            addRowToTableAluguel();
            popularComboBoxClientes(aCbClientes, aTfCPF);
            popularComboBoxCarros  (aCbPlaca, aTfCarro);
            

            JOptionPane.showMessageDialog(this, "Carro Alugado com Sucesso!");
        }
        verAlugueis();
    }
    
    
    private void devolverCarro(String placa, String cpf) {
        // Obter a instância do repositório de aluguéis
        RepoAluguel repoAluguel = RepoAluguel.getInstance();
        RepoCarro repoCarro = RepoCarro.getInstance();
        RepoCliente repoCliente = RepoCliente.getInstance();

        // Encontrar o aluguel correspondente à placa e ao cliente
        Carro carro = repoCarro.obj("placa", placa); // Pega carro usando a placa
        Cliente cliente = repoCliente.obj("cpf", cpf); // Pega cliente usando o CPF
        Aluguel aluguel = repoAluguel.obj("carro", carro); // Supondo que o método obj filtre por placa
        
        System.out.println(aluguel);
        
        if (aluguel != null) {
            // Solicitar ao operador que avalie o dano do carro
            String danoInput = JOptionPane.showInputDialog(
                    this,
                    "Avalie o dano do carro (0 a 5):\n"
                    + "0 - Nenhum dano\n"
                    + "1 - Danos leves\n"
                    + "2 - Danos moderados\n"
                    + "3 - Danos graves\n"
                    + "4 - Danos muito graves\n"
                    + "5 - Carro inutilizável",
                    "Avaliação de Dano",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Verificar se o operador cancelou a entrada
            if (danoInput == null) {
                JOptionPane.showMessageDialog(this, "Devolução cancelada.");
                return;
            }

            // Validar a entrada
            try {
                int nivelDeDano = Integer.parseInt(danoInput);

                if (nivelDeDano < 0 || nivelDeDano > 5) {
                    JOptionPane.showMessageDialog(this, "Valor inválido! O dano deve ser entre 0 e 5.");
                    return;
                } else if(nivelDeDano >= 1 && nivelDeDano <= 5){
                    int confirmarDialogo = JOptionPane.showConfirmDialog(this, "Opa! Parece que o cliente danificou o carro! "
                            + "\nVocê quer mandar para a manutenção?", "ALERTA", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(confirmarDialogo == 0){
                        RepoManutencao manut = RepoManutencao.getInstance();
                        carro.set("estado", "Em Manutenção");
                        repoAluguel.del("carro", carro); // Remover o aluguel do repositório
                        manut.add(carro, nivelDeDano);
                        addRowToTableAluguel();
                        JOptionPane.showMessageDialog(this, "Carro mandado pra Manutenção com sucesso!\nNível de dano: " + nivelDeDano);
                    }else{
                        // Realizar a devolução (exemplo: atualizar o estado do carro e remover o aluguel)
                        carro.set("estado", "Disponivel"); // Marcar o carro como disponível
                        carro.set("nivelDeDano", nivelDeDano); // Definir o nível de dano
                        repoAluguel.del("carro", carro); // Remover o aluguel do repositório
                        
                        // Atualizar a tabela de aluguéis e as opções de clientes e carros
                        addRowToTableAluguel();

                        popularComboBoxClientes(aCbClientes, aTfCPF);
                        popularComboBoxCarros(aCbPlaca, aTfCarro);

                        // Mensagem de sucesso
                        JOptionPane.showMessageDialog(this, "Carro devolvido com sucesso!\nNível de dano: " + nivelDeDano);
                    }
                    }else{
                
                    // Realizar a devolução (exemplo: atualizar o estado do carro e remover o aluguel)
                carro.set("estado", "Disponivel"); // Marcar o carro como disponível
                carro.set("nivelDeDano", nivelDeDano); // Definir o nível de dano
                repoAluguel.del("carro", carro); // Remover o aluguel do repositório

                // Atualizar a tabela de aluguéis e as opções de clientes e carros
                addRowToTableAluguel();
                
                popularComboBoxClientes      (aCbClientes, aTfCPF);
                popularComboBoxCarros        (aCbPlaca,  aTfCarro);
                

                // Mensagem de sucesso
                JOptionPane.showMessageDialog(this, "Carro devolvido com sucesso!\nNível de dano: " + nivelDeDano);
                }
                
                
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Entrada inválida! Digite um número entre 0 e 5.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Aluguel não encontrado.");
        }
    }
    
    
    public void verAlugueis(){
        RepoAluguel alugueis = RepoAluguel.getInstance();
        for(Aluguel aluguel : alugueis.src()){
            System.out.println(aluguel);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btt_alugar = new gui.componentes.PanelGradient();
        lbl_alugar = new javax.swing.JLabel();
        btt_frota = new gui.componentes.PanelGradient();
        lbl_frota = new javax.swing.JLabel();
        btt_manutencao = new gui.componentes.PanelGradient();
        lbl_manuntencao = new javax.swing.JLabel();
        btt_clientes = new gui.componentes.PanelGradient();
        lbl_clientes = new javax.swing.JLabel();
        btt_sair = new gui.componentes.PanelGradient();
        lbl_sair = new javax.swing.JLabel();
        abasMenu = new javax.swing.JTabbedPane();
        pageAlugar = new javax.swing.JPanel();
        fPainelFrota1 = new Panel_redondo.PanelRound();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAluguel = new javax.swing.JTable();
        fPainelFiltro2 = new Panel_redondo.PanelRound();
        aLblCliente = new javax.swing.JLabel();
        aCbClientes = new javax.swing.JComboBox<>();
        aTfCPF = new javax.swing.JTextField();
        aLblCPF = new javax.swing.JLabel();
        aTfCarro = new javax.swing.JTextField();
        aLblPlaca = new javax.swing.JLabel();
        aCbPlaca = new javax.swing.JComboBox<>();
        aLblCarro = new javax.swing.JLabel();
        aTfDias = new javax.swing.JTextField();
        aLblCarro1 = new javax.swing.JLabel();
        aBttonAlugar = new Panel_redondo.PanelRound();
        aLblSalvar = new javax.swing.JLabel();
        fIconeTitulo1 = new gui.componentes.PanelGradient();
        fTitulo2 = new javax.swing.JLabel();
        fTitulo3 = new javax.swing.JLabel();
        aBttnDevolver = new Panel_redondo.PanelRound();
        aLblDevolver = new javax.swing.JLabel();
        pageFrota = new javax.swing.JPanel();
        fPainelFrota = new Panel_redondo.PanelRound();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFrota = new javax.swing.JTable();
        fTsearchFrota = new javax.swing.JTextField();
        fPainelFiltro = new Panel_redondo.PanelRound();
        lblMarca = new javax.swing.JLabel();
        TFMarca = new javax.swing.JTextField();
        lblModelo = new javax.swing.JLabel();
        TFModelo = new javax.swing.JTextField();
        lblPlacaa = new javax.swing.JLabel();
        TFPlaca = new javax.swing.JTextField();
        lblAno = new javax.swing.JLabel();
        TFAno = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        TFValor = new javax.swing.JTextField();
        fBttnSalvar = new Panel_redondo.PanelRound();
        FlblSalvar = new javax.swing.JLabel();
        fTsIconClear = new javax.swing.JPanel();
        fIconeTitulo = new gui.componentes.PanelGradient();
        fTitulo1 = new javax.swing.JLabel();
        fTitulo = new javax.swing.JLabel();
        FbttnDeletar = new Panel_redondo.PanelRound();
        FlblDeletar = new javax.swing.JLabel();
        pageManuntencao = new javax.swing.JPanel();
        mPainelTable = new Panel_redondo.PanelRound();
        mSPTabela = new javax.swing.JScrollPane();
        tableManutencao = new javax.swing.JTable();
        mIconeTitulo = new gui.componentes.PanelGradient();
        mTitulo2 = new javax.swing.JLabel();
        mTitulo3 = new javax.swing.JLabel();
        MPainelFiltro = new Panel_redondo.PanelRound();
        MlblPlaca = new javax.swing.JLabel();
        mTfPlaca = new javax.swing.JTextField();
        mBttnFinalizar = new Panel_redondo.PanelRound();
        mLblFinalizar = new javax.swing.JLabel();
        mBttnSalvar1 = new Panel_redondo.PanelRound();
        FlblSalvar1 = new javax.swing.JLabel();
        mTsearchManutencao = new javax.swing.JTextField();
        mTsIconClear1 = new javax.swing.JPanel();
        pageClientes = new javax.swing.JPanel();
        cBttnRemover = new Panel_redondo.PanelRound();
        FlblDeletar1 = new javax.swing.JLabel();
        cBttnSalvar = new Panel_redondo.PanelRound();
        FlblSalvar2 = new javax.swing.JLabel();
        cPainelTabela = new Panel_redondo.PanelRound();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        pageManuntencao1 = new javax.swing.JPanel();
        mPainelTable1 = new Panel_redondo.PanelRound();
        mSPTabela1 = new javax.swing.JScrollPane();
        tableManutencao1 = new javax.swing.JTable();
        mIconeTitulo1 = new gui.componentes.PanelGradient();
        mTitulo4 = new javax.swing.JLabel();
        mTitulo5 = new javax.swing.JLabel();
        cTsearchClientes = new javax.swing.JTextField();
        cTsIconClear = new javax.swing.JPanel();
        fPainelFiltro1 = new Panel_redondo.PanelRound();
        cLblNome = new javax.swing.JLabel();
        cTfNome = new javax.swing.JTextField();
        cLblCpf = new javax.swing.JLabel();
        cTfCpf = new javax.swing.JTextField();
        cLblEmail = new javax.swing.JLabel();
        cTfEmail = new javax.swing.JTextField();
        cLbldade = new javax.swing.JLabel();
        cTfIdade = new javax.swing.JTextField();
        cLblTelefone = new javax.swing.JLabel();
        cTfTelefone = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Menu.setBackground(new java.awt.Color(109, 26, 54));
        Menu.setMinimumSize(new java.awt.Dimension(200, 720));
        Menu.setPreferredSize(new java.awt.Dimension(200, 720));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LocaRec");
        Menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        btt_alugar.setBackground(new java.awt.Color(109, 26, 54));
        btt_alugar.setColorGradient(new java.awt.Color(109, 26, 54));
        btt_alugar.setRadius(20);
        btt_alugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_alugarMousePressed(evt);
            }
        });
        btt_alugar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_alugar.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_alugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/db32px.png"))); // NOI18N
        lbl_alugar.setText("      Alugar");
        lbl_alugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_alugarMousePressed(evt);
            }
        });
        btt_alugar.add(lbl_alugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 32));

        Menu.add(btt_alugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 210, 188, 32));

        btt_frota.setBackground(new java.awt.Color(109, 26, 54));
        btt_frota.setColorGradient(new java.awt.Color(109, 26, 54));
        btt_frota.setMinimumSize(new java.awt.Dimension(178, 32));
        btt_frota.setRadius(20);
        btt_frota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_frotaMousePressed(evt);
            }
        });
        btt_frota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_frota.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_frota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/carro32px.png"))); // NOI18N
        lbl_frota.setText("       Frota");
        lbl_frota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_frotaMousePressed(evt);
            }
        });
        btt_frota.add(lbl_frota, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 0, 170, 32));

        Menu.add(btt_frota, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 290, 188, 32));

        btt_manutencao.setBackground(new java.awt.Color(109, 26, 54));
        btt_manutencao.setColorGradient(new java.awt.Color(109, 26, 54));
        btt_manutencao.setRadius(20);
        btt_manutencao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_manutencaoMousePressed(evt);
            }
        });
        btt_manutencao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_manuntencao.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_manuntencao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/rep32px.png"))); // NOI18N
        lbl_manuntencao.setText("      Manuntenção");
        lbl_manuntencao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_manuntencaoMousePressed(evt);
            }
        });
        btt_manutencao.add(lbl_manuntencao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 32));

        Menu.add(btt_manutencao, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 330, 188, 32));

        btt_clientes.setBackground(new java.awt.Color(109, 26, 54));
        btt_clientes.setColorGradient(new java.awt.Color(109, 26, 54));
        btt_clientes.setRadius(20);
        btt_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_clientesMousePressed(evt);
            }
        });
        btt_clientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_clientes.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        lbl_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cliente32px.png"))); // NOI18N
        lbl_clientes.setText("      Clientes");
        lbl_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_clientesMousePressed(evt);
            }
        });
        btt_clientes.add(lbl_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 0, 170, 32));

        Menu.add(btt_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 370, 188, 32));

        btt_sair.setBackground(new java.awt.Color(153, 153, 153));
        btt_sair.setColorGradient(new java.awt.Color(102, 102, 102));
        btt_sair.setRadius(20);
        btt_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btt_sairMousePressed(evt);
            }
        });
        btt_sair.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_sair.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        lbl_sair.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sair.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_sair.setText("SAIR");
        lbl_sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_sairMousePressed(evt);
            }
        });
        btt_sair.add(lbl_sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 0, 170, 32));

        Menu.add(btt_sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 680, 188, 32));

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 720));

        abasMenu.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        pageAlugar.setBackground(new java.awt.Color(255, 255, 255));
        pageAlugar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fPainelFrota1.setBackground(new java.awt.Color(230, 232, 230));
        fPainelFrota1.setAlignmentX(2.0F);
        fPainelFrota1.setAlignmentY(2.0F);
        fPainelFrota1.setRoundBottomLeft(25);
        fPainelFrota1.setRoundBottomRight(25);
        fPainelFrota1.setRoundTopLeft(25);

        tableAluguel.setBackground(new java.awt.Color(255, 255, 255));
        tableAluguel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "CPF", "Contato", "Carro", "Placa", "Dias", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAluguel.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tableAluguel);
        if (tableAluguel.getColumnModel().getColumnCount() > 0) {
            tableAluguel.getColumnModel().getColumn(5).setPreferredWidth(3);
        }

        javax.swing.GroupLayout fPainelFrota1Layout = new javax.swing.GroupLayout(fPainelFrota1);
        fPainelFrota1.setLayout(fPainelFrota1Layout);
        fPainelFrota1Layout.setHorizontalGroup(
            fPainelFrota1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fPainelFrota1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        fPainelFrota1Layout.setVerticalGroup(
            fPainelFrota1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fPainelFrota1Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pageAlugar.add(fPainelFrota1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, 480));

        fPainelFiltro2.setBackground(new java.awt.Color(230, 232, 230));
        fPainelFiltro2.setMinimumSize(new java.awt.Dimension(340, 480));
        fPainelFiltro2.setRoundBottomLeft(25);
        fPainelFiltro2.setRoundBottomRight(25);
        fPainelFiltro2.setRoundTopLeft(25);
        fPainelFiltro2.setRoundTopRight(25);
        fPainelFiltro2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aLblCliente.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        aLblCliente.setText("Cliente");
        fPainelFiltro2.add(aLblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 150, 20));

        aCbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fPainelFiltro2.add(aCbClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 210, 30));

        aTfCPF.setEditable(false);
        fPainelFiltro2.add(aTfCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 210, 30));

        aLblCPF.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        aLblCPF.setText("CPF");
        fPainelFiltro2.add(aLblCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 150, 20));

        aTfCarro.setEditable(false);
        fPainelFiltro2.add(aTfCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 210, 30));

        aLblPlaca.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        aLblPlaca.setText("Placa");
        fPainelFiltro2.add(aLblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 150, 20));

        aCbPlaca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        fPainelFiltro2.add(aCbPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 210, 30));

        aLblCarro.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        aLblCarro.setText("Carro");
        fPainelFiltro2.add(aLblCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 150, 20));

        aTfDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aTfDiasActionPerformed(evt);
            }
        });
        fPainelFiltro2.add(aTfDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 210, 30));

        aLblCarro1.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        aLblCarro1.setText("Dias *");
        fPainelFiltro2.add(aLblCarro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 150, 20));

        pageAlugar.add(fPainelFiltro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 340, 410));

        aBttonAlugar.setBackground(new java.awt.Color(255, 102, 102));
        aBttonAlugar.setRoundBottomLeft(20);
        aBttonAlugar.setRoundBottomRight(20);
        aBttonAlugar.setRoundTopLeft(20);
        aBttonAlugar.setRoundTopRight(20);
        aBttonAlugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aBttonAlugarMousePressed(evt);
            }
        });
        aBttonAlugar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aLblSalvar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        aLblSalvar.setForeground(new java.awt.Color(255, 255, 255));
        aLblSalvar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aLblSalvar.setText("Alugar");
        aBttonAlugar.add(aLblSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 110, 40));

        pageAlugar.add(aBttonAlugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, 150, 40));

        fIconeTitulo1.setBackground(new java.awt.Color(153, 0, 0));
        fIconeTitulo1.setColorGradient(new java.awt.Color(255, 51, 51));
        fIconeTitulo1.setGradientType(gui.componentes.PanelGradient.GradientType.DIAGONAL_1);
        fIconeTitulo1.setMinimumSize(new java.awt.Dimension(64, 64));
        fIconeTitulo1.setRadius(20);
        pageAlugar.add(fIconeTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 64, 64));

        fTitulo2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        fTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fTitulo2.setText("Páginas de Aluguéis!");
        pageAlugar.add(fTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 270, 30));

        fTitulo3.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        fTitulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fTitulo3.setText("Vamos alugar um carro Hoje?");
        pageAlugar.add(fTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 20));

        aBttnDevolver.setBackground(new java.awt.Color(255, 102, 102));
        aBttnDevolver.setRoundBottomLeft(20);
        aBttnDevolver.setRoundBottomRight(20);
        aBttnDevolver.setRoundTopLeft(20);
        aBttnDevolver.setRoundTopRight(20);
        aBttnDevolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aBttnDevolverMousePressed(evt);
            }
        });
        aBttnDevolver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aLblDevolver.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        aLblDevolver.setForeground(new java.awt.Color(255, 255, 255));
        aLblDevolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aLblDevolver.setText("Devolver");
        aBttnDevolver.add(aLblDevolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        pageAlugar.add(aBttnDevolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 150, 40));

        abasMenu.addTab("tab4", pageAlugar);

        pageFrota.setBackground(new java.awt.Color(255, 255, 255));
        pageFrota.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fPainelFrota.setBackground(new java.awt.Color(230, 232, 230));
        fPainelFrota.setAlignmentX(2.0F);
        fPainelFrota.setAlignmentY(2.0F);
        fPainelFrota.setRoundBottomLeft(25);
        fPainelFrota.setRoundBottomRight(25);
        fPainelFrota.setRoundTopLeft(25);

        tableFrota.setBackground(new java.awt.Color(255, 255, 255));
        tableFrota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marca", "Modelo", "Placa", "Ano", "Valor", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableFrota.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tableFrota);

        javax.swing.GroupLayout fPainelFrotaLayout = new javax.swing.GroupLayout(fPainelFrota);
        fPainelFrota.setLayout(fPainelFrotaLayout);
        fPainelFrotaLayout.setHorizontalGroup(
            fPainelFrotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fPainelFrotaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        fPainelFrotaLayout.setVerticalGroup(
            fPainelFrotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fPainelFrotaLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pageFrota.add(fPainelFrota, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, 480));

        fTsearchFrota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fTsearchFrotaActionPerformed(evt);
            }
        });
        pageFrota.add(fTsearchFrota, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 300, 30));

        fPainelFiltro.setBackground(new java.awt.Color(230, 232, 230));
        fPainelFiltro.setMinimumSize(new java.awt.Dimension(340, 480));
        fPainelFiltro.setRoundBottomLeft(25);
        fPainelFiltro.setRoundBottomRight(25);
        fPainelFiltro.setRoundTopLeft(25);
        fPainelFiltro.setRoundTopRight(25);
        fPainelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMarca.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblMarca.setText("Marca*");
        fPainelFiltro.add(lblMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 70, 20));

        TFMarca.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro.add(TFMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 210, 30));

        lblModelo.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblModelo.setText("Modelo*");
        fPainelFiltro.add(lblModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 90, 20));

        TFModelo.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro.add(TFModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 210, 30));

        lblPlacaa.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblPlacaa.setText("Placa*");
        fPainelFiltro.add(lblPlacaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 70, 20));

        TFPlaca.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro.add(TFPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 210, 30));

        lblAno.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblAno.setText("Ano*");
        fPainelFiltro.add(lblAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 70, 20));

        TFAno.setBackground(new java.awt.Color(255, 255, 255));
        TFAno.setActionCommand("<Not Set>");
        TFAno.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        TFAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFAnoKeyTyped(evt);
            }
        });
        fPainelFiltro.add(TFAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 80, 30));

        lblValor.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        lblValor.setText("Valor*");
        fPainelFiltro.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 70, 20));

        TFValor.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro.add(TFValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 100, 30));

        pageFrota.add(fPainelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 340, 410));

        fBttnSalvar.setBackground(new java.awt.Color(255, 102, 102));
        fBttnSalvar.setRoundBottomLeft(20);
        fBttnSalvar.setRoundBottomRight(20);
        fBttnSalvar.setRoundTopLeft(20);
        fBttnSalvar.setRoundTopRight(20);
        fBttnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fBttnSalvarMousePressed(evt);
            }
        });
        fBttnSalvar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FlblSalvar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        FlblSalvar.setForeground(new java.awt.Color(255, 255, 255));
        FlblSalvar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlblSalvar.setText("Salvar");
        fBttnSalvar.add(FlblSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        pageFrota.add(fBttnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, 150, 40));

        fTsIconClear.setBackground(new java.awt.Color(255, 102, 102));
        fTsIconClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fTsIconClearMousePressed(evt);
            }
        });

        javax.swing.GroupLayout fTsIconClearLayout = new javax.swing.GroupLayout(fTsIconClear);
        fTsIconClear.setLayout(fTsIconClearLayout);
        fTsIconClearLayout.setHorizontalGroup(
            fTsIconClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        fTsIconClearLayout.setVerticalGroup(
            fTsIconClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pageFrota.add(fTsIconClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 90, 30, 30));

        fIconeTitulo.setBackground(new java.awt.Color(153, 0, 0));
        fIconeTitulo.setColorGradient(new java.awt.Color(255, 51, 51));
        fIconeTitulo.setGradientType(gui.componentes.PanelGradient.GradientType.DIAGONAL_1);
        fIconeTitulo.setMinimumSize(new java.awt.Dimension(64, 64));
        fIconeTitulo.setRadius(20);
        pageFrota.add(fIconeTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 64, 64));

        fTitulo1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        fTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fTitulo1.setText("Gerenciador de Frota!");
        pageFrota.add(fTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 270, 30));

        fTitulo.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        fTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fTitulo.setText("Gerencie os seus carros aqui!");
        pageFrota.add(fTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 20));

        FbttnDeletar.setBackground(new java.awt.Color(255, 102, 102));
        FbttnDeletar.setRoundBottomLeft(20);
        FbttnDeletar.setRoundBottomRight(20);
        FbttnDeletar.setRoundTopLeft(20);
        FbttnDeletar.setRoundTopRight(20);
        FbttnDeletar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FbttnDeletarMousePressed(evt);
            }
        });
        FbttnDeletar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FlblDeletar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        FlblDeletar.setForeground(new java.awt.Color(255, 255, 255));
        FlblDeletar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlblDeletar.setText("Deletar");
        FbttnDeletar.add(FlblDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        pageFrota.add(FbttnDeletar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 150, 40));

        abasMenu.addTab("tab1", pageFrota);

        pageManuntencao.setBackground(new java.awt.Color(255, 255, 255));
        pageManuntencao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mPainelTable.setBackground(new java.awt.Color(230, 232, 230));
        mPainelTable.setAlignmentX(2.0F);
        mPainelTable.setAlignmentY(2.0F);
        mPainelTable.setRoundBottomLeft(10);
        mPainelTable.setRoundBottomRight(10);
        mPainelTable.setRoundTopLeft(25);
        mPainelTable.setRoundTopRight(25);

        tableManutencao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marca", "Modelo", "Placa", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mSPTabela.setViewportView(tableManutencao);

        javax.swing.GroupLayout mPainelTableLayout = new javax.swing.GroupLayout(mPainelTable);
        mPainelTable.setLayout(mPainelTableLayout);
        mPainelTableLayout.setHorizontalGroup(
            mPainelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mPainelTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mSPTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        mPainelTableLayout.setVerticalGroup(
            mPainelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mPainelTableLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(mSPTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pageManuntencao.add(mPainelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 152, -1, -1));

        mIconeTitulo.setBackground(new java.awt.Color(153, 0, 0));
        mIconeTitulo.setColorGradient(new java.awt.Color(255, 51, 51));
        mIconeTitulo.setGradientType(gui.componentes.PanelGradient.GradientType.DIAGONAL_1);
        mIconeTitulo.setMinimumSize(new java.awt.Dimension(64, 64));
        mIconeTitulo.setRadius(20);
        pageManuntencao.add(mIconeTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 64, 64));

        mTitulo2.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        mTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mTitulo2.setText("Cheque as Manutenções aqui!");
        pageManuntencao.add(mTitulo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 20));

        mTitulo3.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        mTitulo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mTitulo3.setText("Aba de Manutenção");
        pageManuntencao.add(mTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 270, 30));

        MPainelFiltro.setBackground(new java.awt.Color(230, 232, 230));
        MPainelFiltro.setMinimumSize(new java.awt.Dimension(340, 480));
        MPainelFiltro.setRoundBottomLeft(25);
        MPainelFiltro.setRoundBottomRight(25);
        MPainelFiltro.setRoundTopLeft(25);
        MPainelFiltro.setRoundTopRight(25);
        MPainelFiltro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MlblPlaca.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        MlblPlaca.setText("Placa do Carro*");
        MPainelFiltro.add(MlblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 150, 20));

        mTfPlaca.setBackground(new java.awt.Color(255, 255, 255));
        MPainelFiltro.add(mTfPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 260, 30));

        mBttnFinalizar.setBackground(new java.awt.Color(255, 102, 102));
        mBttnFinalizar.setRoundBottomLeft(20);
        mBttnFinalizar.setRoundBottomRight(20);
        mBttnFinalizar.setRoundTopLeft(20);
        mBttnFinalizar.setRoundTopRight(20);
        mBttnFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mBttnFinalizarMousePressed(evt);
            }
        });
        mBttnFinalizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mLblFinalizar.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        mLblFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        mLblFinalizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mLblFinalizar.setText("Finalizar");
        mBttnFinalizar.add(mLblFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        MPainelFiltro.add(mBttnFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 150, 40));

        mBttnSalvar1.setBackground(new java.awt.Color(255, 102, 102));
        mBttnSalvar1.setRoundBottomLeft(20);
        mBttnSalvar1.setRoundBottomRight(20);
        mBttnSalvar1.setRoundTopLeft(20);
        mBttnSalvar1.setRoundTopRight(20);
        mBttnSalvar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mBttnSalvar1MousePressed(evt);
            }
        });
        mBttnSalvar1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FlblSalvar1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        FlblSalvar1.setForeground(new java.awt.Color(255, 255, 255));
        FlblSalvar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlblSalvar1.setText("Salvar");
        mBttnSalvar1.add(FlblSalvar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        MPainelFiltro.add(mBttnSalvar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 150, 40));

        pageManuntencao.add(MPainelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 340, 200));

        mTsearchManutencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTsearchManutencaoActionPerformed(evt);
            }
        });
        pageManuntencao.add(mTsearchManutencao, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 300, 30));

        mTsIconClear1.setBackground(new java.awt.Color(255, 102, 102));
        mTsIconClear1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mTsIconClear1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout mTsIconClear1Layout = new javax.swing.GroupLayout(mTsIconClear1);
        mTsIconClear1.setLayout(mTsIconClear1Layout);
        mTsIconClear1Layout.setHorizontalGroup(
            mTsIconClear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        mTsIconClear1Layout.setVerticalGroup(
            mTsIconClear1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pageManuntencao.add(mTsIconClear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 90, 30, 30));

        abasMenu.addTab("tab2", pageManuntencao);

        pageClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cBttnRemover.setBackground(new java.awt.Color(255, 102, 102));
        cBttnRemover.setRoundBottomLeft(20);
        cBttnRemover.setRoundBottomRight(20);
        cBttnRemover.setRoundTopLeft(20);
        cBttnRemover.setRoundTopRight(20);
        cBttnRemover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cBttnRemoverMousePressed(evt);
            }
        });
        cBttnRemover.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FlblDeletar1.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        FlblDeletar1.setForeground(new java.awt.Color(255, 255, 255));
        FlblDeletar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlblDeletar1.setText("Remover");
        cBttnRemover.add(FlblDeletar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        pageClientes.add(cBttnRemover, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 150, 40));

        cBttnSalvar.setBackground(new java.awt.Color(255, 102, 102));
        cBttnSalvar.setRoundBottomLeft(20);
        cBttnSalvar.setRoundBottomRight(20);
        cBttnSalvar.setRoundTopLeft(20);
        cBttnSalvar.setRoundTopRight(20);
        cBttnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cBttnSalvarMousePressed(evt);
            }
        });
        cBttnSalvar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FlblSalvar2.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        FlblSalvar2.setForeground(new java.awt.Color(255, 255, 255));
        FlblSalvar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlblSalvar2.setText("Salvar");
        cBttnSalvar.add(FlblSalvar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        pageClientes.add(cBttnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, 150, 40));

        cPainelTabela.setBackground(new java.awt.Color(230, 232, 230));
        cPainelTabela.setAlignmentX(2.0F);
        cPainelTabela.setAlignmentY(2.0F);
        cPainelTabela.setRoundBottomLeft(20);
        cPainelTabela.setRoundBottomRight(20);
        cPainelTabela.setRoundTopLeft(20);
        cPainelTabela.setRoundTopRight(20);

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Idade", "Email", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(1).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setPreferredWidth(5);
        }

        javax.swing.GroupLayout cPainelTabelaLayout = new javax.swing.GroupLayout(cPainelTabela);
        cPainelTabela.setLayout(cPainelTabelaLayout);
        cPainelTabelaLayout.setHorizontalGroup(
            cPainelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPainelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
                .addContainerGap())
        );
        cPainelTabelaLayout.setVerticalGroup(
            cPainelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPainelTabelaLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pageClientes.add(cPainelTabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 152, -1, -1));

        pageManuntencao1.setBackground(new java.awt.Color(255, 255, 255));
        pageManuntencao1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mPainelTable1.setBackground(new java.awt.Color(230, 232, 230));
        mPainelTable1.setAlignmentX(2.0F);
        mPainelTable1.setAlignmentY(2.0F);
        mPainelTable1.setRoundBottomLeft(10);
        mPainelTable1.setRoundBottomRight(10);
        mPainelTable1.setRoundTopLeft(25);
        mPainelTable1.setRoundTopRight(25);

        tableManutencao1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marca", "Modelo", "Placa", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mSPTabela1.setViewportView(tableManutencao1);

        javax.swing.GroupLayout mPainelTable1Layout = new javax.swing.GroupLayout(mPainelTable1);
        mPainelTable1.setLayout(mPainelTable1Layout);
        mPainelTable1Layout.setHorizontalGroup(
            mPainelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mPainelTable1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mSPTabela1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        mPainelTable1Layout.setVerticalGroup(
            mPainelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mPainelTable1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(mSPTabela1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pageManuntencao1.add(mPainelTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 152, -1, -1));

        mIconeTitulo1.setBackground(new java.awt.Color(153, 0, 0));
        mIconeTitulo1.setColorGradient(new java.awt.Color(255, 51, 51));
        mIconeTitulo1.setGradientType(gui.componentes.PanelGradient.GradientType.DIAGONAL_1);
        mIconeTitulo1.setMinimumSize(new java.awt.Dimension(64, 64));
        mIconeTitulo1.setRadius(20);
        pageManuntencao1.add(mIconeTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 64, 64));

        mTitulo4.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        mTitulo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mTitulo4.setText("Gerencie seus Clientes aqui!");
        pageManuntencao1.add(mTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 270, 20));

        mTitulo5.setFont(new java.awt.Font("Inter", 1, 24)); // NOI18N
        mTitulo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mTitulo5.setText("Aba de Clientes!");
        pageManuntencao1.add(mTitulo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 270, 30));

        cTsearchClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTsearchClientesActionPerformed(evt);
            }
        });
        pageManuntencao1.add(cTsearchClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 300, 30));

        cTsIconClear.setBackground(new java.awt.Color(255, 102, 102));
        cTsIconClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cTsIconClearMousePressed(evt);
            }
        });

        javax.swing.GroupLayout cTsIconClearLayout = new javax.swing.GroupLayout(cTsIconClear);
        cTsIconClear.setLayout(cTsIconClearLayout);
        cTsIconClearLayout.setHorizontalGroup(
            cTsIconClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        cTsIconClearLayout.setVerticalGroup(
            cTsIconClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        pageManuntencao1.add(cTsIconClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 90, 30, 30));

        fPainelFiltro1.setBackground(new java.awt.Color(230, 232, 230));
        fPainelFiltro1.setMinimumSize(new java.awt.Dimension(340, 480));
        fPainelFiltro1.setRoundBottomLeft(25);
        fPainelFiltro1.setRoundBottomRight(25);
        fPainelFiltro1.setRoundTopLeft(25);
        fPainelFiltro1.setRoundTopRight(25);
        fPainelFiltro1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cLblNome.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        cLblNome.setText("Nome*");
        fPainelFiltro1.add(cLblNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 70, 20));

        cTfNome.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro1.add(cTfNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 210, 30));

        cLblCpf.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        cLblCpf.setText("CPF*");
        fPainelFiltro1.add(cLblCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 90, 20));

        cTfCpf.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro1.add(cTfCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 210, 30));

        cLblEmail.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        cLblEmail.setText("Email*");
        fPainelFiltro1.add(cLblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 70, 20));

        cTfEmail.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro1.add(cTfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 210, 30));

        cLbldade.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        cLbldade.setText("Idade*");
        fPainelFiltro1.add(cLbldade, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 70, 20));

        cTfIdade.setBackground(new java.awt.Color(255, 255, 255));
        cTfIdade.setActionCommand("<Not Set>");
        cTfIdade.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        cTfIdade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cTfIdadeKeyTyped(evt);
            }
        });
        fPainelFiltro1.add(cTfIdade, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 80, 30));

        cLblTelefone.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        cLblTelefone.setText("Telefone*");
        fPainelFiltro1.add(cLblTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 90, 20));

        cTfTelefone.setBackground(new java.awt.Color(255, 255, 255));
        fPainelFiltro1.add(cTfTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 120, 30));

        pageManuntencao1.add(fPainelFiltro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 340, 390));

        pageClientes.add(pageManuntencao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 720));

        abasMenu.addTab("tab3", pageClientes);

        getContentPane().add(abasMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_frotaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_frotaMousePressed
        abasMenu.setSelectedIndex(1);
        ColorOP.AnimarFrota();
        addRowToTableFrota();
    }//GEN-LAST:event_lbl_frotaMousePressed

    private void lbl_manuntencaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_manuntencaoMousePressed
        abasMenu.setSelectedIndex(2);
        ColorOP.AnimarManuntencao();
        addRowToTableManutencao();
    }//GEN-LAST:event_lbl_manuntencaoMousePressed

    private void lbl_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clientesMousePressed
        abasMenu.setSelectedIndex(3);
        ColorOP.AnimarClientes();
        addRowToTableClientes();
    }//GEN-LAST:event_lbl_clientesMousePressed

    private void btt_frotaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_frotaMousePressed
        abasMenu.setSelectedIndex(1);
        ColorOP.AnimarFrota();
        addRowToTableFrota();
    }//GEN-LAST:event_btt_frotaMousePressed

    private void btt_manutencaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_manutencaoMousePressed
        abasMenu.setSelectedIndex(2);
        ColorOP.AnimarManuntencao();
        addRowToTableManutencao();
    }//GEN-LAST:event_btt_manutencaoMousePressed

    private void btt_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_clientesMousePressed
        abasMenu.setSelectedIndex(3);
        ColorOP.AnimarClientes();
        addRowToTableClientes();
    }//GEN-LAST:event_btt_clientesMousePressed

    private void lbl_sairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sairMousePressed

        System.exit(0);
       
    }//GEN-LAST:event_lbl_sairMousePressed

    private void btt_sairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_sairMousePressed
        
        System.exit(0);
    }//GEN-LAST:event_btt_sairMousePressed

    private void fTsearchFrotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fTsearchFrotaActionPerformed
        
    }//GEN-LAST:event_fTsearchFrotaActionPerformed

    private void fBttnSalvarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fBttnSalvarMousePressed
        salvarFrota();
    }//GEN-LAST:event_fBttnSalvarMousePressed

    private void TFAnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFAnoKeyTyped
        // Definir variáveis para comparar
        char c = evt.getKeyChar();
        String textoAtual = TFAno.getText();
        
        // Verificar se o caractere digitado é um número
        if(!Character.isDigit(c)){
            evt.consume(); // Consumir o evento (não permitir a digitação)
        }
        
        // Verificar o comprimento atual do texto no campo
        if(textoAtual.length()>=4){
            evt.consume(); // Consumir o evento (não permitir mais que 4 dígitos)
        }
    }//GEN-LAST:event_TFAnoKeyTyped

    private void fTsIconClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fTsIconClearMousePressed
        fTsearchFrota.setText("");
    }//GEN-LAST:event_fTsIconClearMousePressed

    private void mBttnSalvar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mBttnSalvar1MousePressed
        salvarManutencao();
    }//GEN-LAST:event_mBttnSalvar1MousePressed

    private void mBttnFinalizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mBttnFinalizarMousePressed
        encerrarManutencao();
    }//GEN-LAST:event_mBttnFinalizarMousePressed

    private void mTsearchManutencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTsearchManutencaoActionPerformed
       
    }//GEN-LAST:event_mTsearchManutencaoActionPerformed

    private void mTsIconClear1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mTsIconClear1MousePressed
        mTsearchManutencao.setText("");
    }//GEN-LAST:event_mTsIconClear1MousePressed

    private void cTsearchClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTsearchClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cTsearchClientesActionPerformed

    private void cTsIconClearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cTsIconClearMousePressed
        cTsearchClientes.setText("");
    }//GEN-LAST:event_cTsIconClearMousePressed

    private void cTfIdadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cTfIdadeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_cTfIdadeKeyTyped

    private void cBttnRemoverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cBttnRemoverMousePressed
        desligarCliente();
    }//GEN-LAST:event_cBttnRemoverMousePressed

    private void cBttnSalvarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cBttnSalvarMousePressed
        salvarCliente();
    }//GEN-LAST:event_cBttnSalvarMousePressed

    private void FbttnDeletarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FbttnDeletarMousePressed
        deletarFrota();
    }//GEN-LAST:event_FbttnDeletarMousePressed

    private void aBttonAlugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aBttonAlugarMousePressed
        alugar();
    }//GEN-LAST:event_aBttonAlugarMousePressed

    private void aBttnDevolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aBttnDevolverMousePressed
        // Obter a linha selecionada na tabela
        int selectedRow = tableAluguel.getSelectedRow();
        
        System.out.println(selectedRow);
        // Verificar se uma linha foi selecionada
        if (selectedRow != -1) { // -1 significa que nenhuma linha foi selecionada
            // Recuperar os dados da linha selecionada
            String placa = (String) tableAluguel.getValueAt(selectedRow, 4); 
            String cpf = (String) tableAluguel.getValueAt(selectedRow, 1);
            System.out.println(placa);
            // Exibir os dados (apenas para depuração)
            System.out.println("Placa do carro selecionado: " + placa);

            // Realizar a ação de devolução (exemplo)
            int confirmarDialogo = JOptionPane.showConfirmDialog(this, "Essa ação NÃO poderá ser desfeita.", "Você tem Certeza?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmarDialogo == JOptionPane.YES_OPTION) {

                devolverCarro(placa,cpf);
            }
            
        } else {
            // Nenhuma linha selecionada
            JOptionPane.showMessageDialog(this, "Nenhum aluguel selecionado.");
        }
            
    }//GEN-LAST:event_aBttnDevolverMousePressed

    private void lbl_alugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_alugarMousePressed
        abasMenu.setSelectedIndex(0);
        ColorOP.animarAluguel();
        addRowToTableAluguel();
    }//GEN-LAST:event_lbl_alugarMousePressed

    private void btt_alugarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btt_alugarMousePressed
        abasMenu.setSelectedIndex(0);
        ColorOP.animarAluguel();
        addRowToTableAluguel();
    }//GEN-LAST:event_btt_alugarMousePressed

    private void aTfDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aTfDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aTfDiasActionPerformed


   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOption().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Panel_redondo.PanelRound FbttnDeletar;
    private javax.swing.JLabel FlblDeletar;
    private javax.swing.JLabel FlblDeletar1;
    private javax.swing.JLabel FlblSalvar;
    private javax.swing.JLabel FlblSalvar1;
    private javax.swing.JLabel FlblSalvar2;
    private Panel_redondo.PanelRound MPainelFiltro;
    private javax.swing.JPanel Menu;
    private javax.swing.JLabel MlblPlaca;
    private javax.swing.JTextField TFAno;
    private javax.swing.JTextField TFMarca;
    private javax.swing.JTextField TFModelo;
    private javax.swing.JTextField TFPlaca;
    private javax.swing.JTextField TFValor;
    private Panel_redondo.PanelRound aBttnDevolver;
    private Panel_redondo.PanelRound aBttonAlugar;
    private javax.swing.JComboBox<String> aCbClientes;
    private javax.swing.JComboBox<String> aCbPlaca;
    private javax.swing.JLabel aLblCPF;
    private javax.swing.JLabel aLblCarro;
    private javax.swing.JLabel aLblCarro1;
    private javax.swing.JLabel aLblCliente;
    private javax.swing.JLabel aLblDevolver;
    private javax.swing.JLabel aLblPlaca;
    private javax.swing.JLabel aLblSalvar;
    private javax.swing.JTextField aTfCPF;
    private javax.swing.JTextField aTfCarro;
    private javax.swing.JTextField aTfDias;
    private javax.swing.JTabbedPane abasMenu;
    public static gui.componentes.PanelGradient btt_alugar;
    public static gui.componentes.PanelGradient btt_clientes;
    public static gui.componentes.PanelGradient btt_frota;
    public static gui.componentes.PanelGradient btt_manutencao;
    public static gui.componentes.PanelGradient btt_sair;
    private Panel_redondo.PanelRound cBttnRemover;
    private Panel_redondo.PanelRound cBttnSalvar;
    private javax.swing.JLabel cLblCpf;
    private javax.swing.JLabel cLblEmail;
    private javax.swing.JLabel cLblNome;
    private javax.swing.JLabel cLblTelefone;
    private javax.swing.JLabel cLbldade;
    private Panel_redondo.PanelRound cPainelTabela;
    private javax.swing.JTextField cTfCpf;
    private javax.swing.JTextField cTfEmail;
    private javax.swing.JTextField cTfIdade;
    private javax.swing.JTextField cTfNome;
    private javax.swing.JTextField cTfTelefone;
    private javax.swing.JPanel cTsIconClear;
    private javax.swing.JTextField cTsearchClientes;
    private Panel_redondo.PanelRound fBttnSalvar;
    private gui.componentes.PanelGradient fIconeTitulo;
    private gui.componentes.PanelGradient fIconeTitulo1;
    private Panel_redondo.PanelRound fPainelFiltro;
    private Panel_redondo.PanelRound fPainelFiltro1;
    private Panel_redondo.PanelRound fPainelFiltro2;
    private Panel_redondo.PanelRound fPainelFrota;
    private Panel_redondo.PanelRound fPainelFrota1;
    private javax.swing.JLabel fTitulo;
    private javax.swing.JLabel fTitulo1;
    private javax.swing.JLabel fTitulo2;
    private javax.swing.JLabel fTitulo3;
    private javax.swing.JPanel fTsIconClear;
    private javax.swing.JTextField fTsearchFrota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblPlacaa;
    private javax.swing.JLabel lblValor;
    public static javax.swing.JLabel lbl_alugar;
    public static javax.swing.JLabel lbl_clientes;
    public static javax.swing.JLabel lbl_frota;
    public static javax.swing.JLabel lbl_manuntencao;
    public static javax.swing.JLabel lbl_sair;
    private Panel_redondo.PanelRound mBttnFinalizar;
    private Panel_redondo.PanelRound mBttnSalvar1;
    private gui.componentes.PanelGradient mIconeTitulo;
    private gui.componentes.PanelGradient mIconeTitulo1;
    private javax.swing.JLabel mLblFinalizar;
    private Panel_redondo.PanelRound mPainelTable;
    private Panel_redondo.PanelRound mPainelTable1;
    private javax.swing.JScrollPane mSPTabela;
    private javax.swing.JScrollPane mSPTabela1;
    private javax.swing.JTextField mTfPlaca;
    private javax.swing.JLabel mTitulo2;
    private javax.swing.JLabel mTitulo3;
    private javax.swing.JLabel mTitulo4;
    private javax.swing.JLabel mTitulo5;
    private javax.swing.JPanel mTsIconClear1;
    private javax.swing.JTextField mTsearchManutencao;
    private javax.swing.JPanel pageAlugar;
    private javax.swing.JPanel pageClientes;
    private javax.swing.JPanel pageFrota;
    private javax.swing.JPanel pageManuntencao;
    private javax.swing.JPanel pageManuntencao1;
    private javax.swing.JTable tableAluguel;
    private javax.swing.JTable tableClientes;
    private javax.swing.JTable tableFrota;
    private javax.swing.JTable tableManutencao;
    private javax.swing.JTable tableManutencao1;
    // End of variables declaration//GEN-END:variables
}

