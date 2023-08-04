package br.ufg.dlog.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConexao {
	public Connection buscaConexao() {
		Connection conexao = null;
        		String usuario = "transporte_dlog";
				String senha = "SkdD6Y&k3b";
				String bancoDeDados = "transporte_dlog_homolog";
				try {
					Class.forName("org.postgresql.Driver");
					conexao = DriverManager.getConnection("jdbc:postgresql://200.137.217.168:5432/"+bancoDeDados,
							usuario, senha);										
				} catch (Exception e) {
					e.printStackTrace();
				}
				return conexao;
	}

}
