/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.eclipse.paho.sample.utility;
 
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
 
public class ConexaoAccessJava8 {
    public Statement stm;//preparar e realizar pesquisa no banco de dados
    public ResultSet res;//responsavel por armazenar o resultado de uma pesquisa passada pelo statement
    public Connection conn;//resposanvel por realizar a conexao com o banco de dados
    
    public void conexao(){//metodo responsavel por realizar a conexao com o banco
        try {//tentativa inicial
            // Nome do arquivo
            String filename = "C:\\Users\\Wesley\\Documents\\NetBeansProjects\\paho.mqtt.java\\MQTT.accdb";

            File arquivo = new File(filename);

            // Verifica se o arquivo não existe.
            if (!arquivo.exists()) {
                    System.err.println("Arquivo não existe!");
            }

            String database = "jdbc:ucanaccess://" + filename.trim();

            System.out.println(database);

            // Realiza a conexão com o banco de dados
            conn = DriverManager.getConnection(database);

            // Faz a leitura dos metadados do Banco
            DatabaseMetaData d = conn.getMetaData();

            ResultSet rs = d.getTables(null, null, "%", null);

            while (rs.next()) {
                    System.out.println(rs.getString(3));
            }

            rs.close();
    } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void executaSQL(String sql){
        try {
            stm = conn.createStatement(res.TYPE_SCROLL_INSENSITIVE, res.CONCUR_READ_ONLY);
            res = stm.executeQuery(sql);
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL!\n Erro"+ex.getMessage());
        }
    }
        
    public void desconecta(){//metodo para fechar a conexao com o banco de dados
        try {
            conn.close();//fecha a conexão
            JOptionPane.showMessageDialog(null, "Desconectado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a Conexão!\n Erro"+ex.getMessage());
        }
    }
}
