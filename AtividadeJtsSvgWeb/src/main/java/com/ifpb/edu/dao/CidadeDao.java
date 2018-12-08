/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.edu.dao;

import com.ifpb.edu.conexao.ConexaoAtividadeDb;
import com.ifpb.edu.conexao.ConexaoBrasilDb;
import com.ifpb.edu.model.Cidade;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class CidadeDao {
    
    private WKTReader reader;
    private ConexaoBrasilDb connect;
    private ConexaoAtividadeDb connect2;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public CidadeDao(){
        reader = new WKTReader();
        connect = new ConexaoBrasilDb();
        connect2 = new ConexaoAtividadeDb();
        stmt = null;
        rs = null;
    }
    
    public int getPopulacao(String estado, String cidade) throws SQLException, ClassNotFoundException{
        
        int populacao = 0;
        
        Connection con = connect2.getConnection();
        String sql = "SELECT c.populacao_2010 "
                + "FROM cidades c JOIN estados e ON c.estado_id = e.id "
                + "WHERE c.nome ilike ? and e.nome ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1,cidade);
        stmt.setString(2, estado);
        rs = stmt.executeQuery();
        
        if(rs.next()){
            populacao = rs.getInt("populacao_2010");
        }
        
        rs.close();
        stmt.close();
        con.close();
         if(populacao > 0)
            return populacao;
         return 0;
        
    }
    
    public Geometry getGeoCidade(String cidade, int codIbge) throws SQLException, ClassNotFoundException, ParseException{
        
        String wkt = null;
        Connection con = connect.getConnection();
        String sql = "SELECT ST_AsEWKT(geom) "
                + "FROM municipio "
                + "WHERE nm_municip ilike ? AND cd_geocmu ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1,cidade);
        stmt.setString(2,String.valueOf(codIbge));
        rs = stmt.executeQuery();
        
        if(rs.next()){
            wkt = rs.getString(1);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        if(wkt != null)
            return reader.read(wkt);
        else
            return null;
        
        
    }
    
    public int codigoIbge (String estado, String cidade) throws SQLException, ClassNotFoundException{
    
        int codigo = 0;
        
        Connection con = connect2.getConnection();
        String sql = "SELECT c.codigo_ibge "
                + "FROM cidades c JOIN estados e ON c.estado_id = e.id "
                + "WHERE c.nome ilike ? and e.nome ilike ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1,cidade);
        stmt.setString(2, estado);
        rs = stmt.executeQuery();
        
        if(rs.next()){
            codigo = rs.getInt(1);
        }
        
        rs.close();
        stmt.close();
        con.close();
        
        if(codigo > 0)
            return codigo;
        return 0;
    
    } 
    
    public Cidade getCidade(String estado, String cidade) throws SQLException, ClassNotFoundException, ParseException{
        
        int codigoIbge = codigoIbge(estado, cidade);
        Geometry geo = getGeoCidade(cidade, codigoIbge);
        
        if(geo != null){
            int populacao = getPopulacao(estado, cidade);
            double area = geo.getArea() * Math.pow(40075/360, 2);
            double densDemografica = populacao / area;
            double perimetro = geo.getLength() * (40075/360);

            Cidade c = new Cidade(cidade, estado, populacao, densDemografica, area, perimetro);
            if(c != null)
                return c;
        }
    
        return null;
        
    }
    
}

