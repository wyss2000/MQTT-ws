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
 
public class ConexaoAccessJava8 {
 
	public static void main(String[] args) {
		Connection con = null;
		try {		
 
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
			con = DriverManager.getConnection(database);
 
			// Faz a leitura dos metadados do Banco
			DatabaseMetaData d = con.getMetaData();
 
			ResultSet rs = d.getTables(null, null, "%", null);
 
			while (rs.next()) {
				System.out.println(rs.getString(3));
			}
 
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
