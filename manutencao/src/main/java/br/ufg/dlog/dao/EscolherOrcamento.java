package br.ufg.dlog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EscolherOrcamento extends DaoConexao{
	
	public void atribuirNao(Long fk_defeitosRelatqados) {
		try { Connection connection = buscaConexao();
		
		       PreparedStatement pstmt = connection
		    		   .prepareStatement("update orcamentos set orc_atribuido='N' where fk_defeitos_relatados=?");		       
		       pstmt.setLong(1, fk_defeitosRelatqados);		     
		       pstmt.execute();
		       pstmt.close();
		       connection.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
